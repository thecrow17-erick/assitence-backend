package crow.uagrm.parcial.services.teachers;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import crow.uagrm.parcial.error.exception.BadRequestException;
import crow.uagrm.parcial.error.exception.NotFoundException;
import crow.uagrm.parcial.models.Assists;
import crow.uagrm.parcial.models.AssistsVirtual;
import crow.uagrm.parcial.models.DetailClass;
import crow.uagrm.parcial.models.DetailWork;
import crow.uagrm.parcial.models.Tolerance;
import crow.uagrm.parcial.repository.worloads.AssistRepositary;
import crow.uagrm.parcial.repository.worloads.AssistVirtualRepository;
import crow.uagrm.parcial.repository.worloads.ToleranceRepository;
import crow.uagrm.parcial.services.azure.AzureService;
import crow.uagrm.parcial.services.school.ClassroomService;
import crow.uagrm.parcial.services.school.PeriodService;
import crow.uagrm.parcial.services.user.UserService;
import crow.uagrm.parcial.services.workloads.DetailClassService;
import crow.uagrm.parcial.services.workloads.DetailWorkService;
import crow.uagrm.parcial.services.workloads.WorkloadService;
import crow.uagrm.parcial.utils.TypeAsistence;

@Service
public class AssistsTeacherService {
  @Autowired
  private ToleranceRepository toleranceRepository;
  @Autowired
  private AssistRepositary assistRepositary;
  @Autowired
  private AssistVirtualRepository assistVirtualRepository;
  @Autowired
  private PeriodService periodService;
  @Autowired
  private UserService userService;
  @Autowired
  private WorkloadService workloadService;
  @Autowired
  private DetailWorkService detailWorkService;
  @Autowired
  private DetailClassService detailClassService;
  @Autowired 
  private ClassroomService classroomService;
  @Autowired
  private AzureService azureService;

  public List<Assists> allAssistsPending(Long id){
    List<Assists> assists = new ArrayList<>();
    var user = this.userService.findUserById(id);
    var period = this.periodService.finOnePeriodTrue();
    var work = this.workloadService.findWorkUserPeriod(user, period);
    var detailsWorks= this.detailWorkService.findDetailWorks(work);
    for (DetailWork detailWork : detailsWorks) {
      this.findDetailClass(assists,detailWork);
    }
    return assists;
  }

  public void findDetailClass(List<Assists> assists,DetailWork detailWork ){
    List<DetailClass> detailClass =  this.detailClassService.findDetailClassWork(detailWork);
    for (DetailClass  detail: detailClass) {
      this.findAssists( assists,detail);
    }
  }

  public void findAssists(List<Assists> assists,DetailClass  detail){
    List<Assists> findAssist = this.assistRepositary.findByDetailClass(detail);
    for (Assists assists2 : findAssist) {
      LocalDate now = LocalDate.now();
      Boolean isEqual = now.isEqual(assists2.getTime().toLocalDate());
      if(isEqual && assists2.getAssistsStatus() == TypeAsistence.ESPERA){
        assists.add(assists2);
      }
    }
  }

  public Assists findAssistsById(Long id){
    var findAssists = this.assistRepositary.findById(id);
    if(findAssists.isEmpty()){
      throw new NotFoundException(
        "La asistencia no se encuentra en la bd"
      );
    }
    return findAssists.get();
  }

  public Assists markAssistanceQr(UUID codeQr, Long id){
    var classroom = this.classroomService.findQRClassroom(codeQr);
    System.out.println(classroom);
    var findAssist = this.findAssistsById(id);

    if(findAssist.getAssistsStatus() != TypeAsistence.ESPERA){
      throw new BadRequestException(
        "La asistencia ya esta marcada"
      );
    }
    this.isValidTime(findAssist, TypeAsistence.ASISTENCIA);

    findAssist.setTime(LocalDateTime.now());
    return this.assistRepositary.save(
      findAssist
    );
  }

  public void isValidTime(Assists assists, TypeAsistence typeAsistence){
    LocalTime starTime = assists.getDetailClass().getStartTime().plus(Duration.ofMinutes(-10));
    LocalTime endFirst = assists.getDetailClass().getStartTime().plus(Duration.ofMinutes(10));
    LocalTime endSecond = assists.getDetailClass().getEndTime();
    LocalTime now = LocalTime.now();
    Boolean isValid = this.isTimeInRange(LocalTime.now(),starTime,endFirst);
    if(isValid){
      assists.setAssistsStatus(typeAsistence);
    }
    isValid = this.isTimeInRange(LocalTime.now(),endFirst,endSecond);
    if(isValid){
      assists.setAssistsStatus(TypeAsistence.ATRASADO);
    }
    isValid = now.isAfter(endSecond);
    if(isValid){
      throw new BadRequestException(
        "se paso de la hora de asistencia"
      );
    }
    isValid = now.isBefore(starTime);
    if(isValid){
      throw new BadRequestException(
        "Se puede marcar desde 10 minutos antes de iniciar la clase"
      );
    }
  }

  public Boolean isTimeInRange(LocalTime targetTime, LocalTime startTime, LocalTime endTime) {
    return !targetTime.isBefore(startTime) && !targetTime.isAfter(endTime);
  }

  public AssistsVirtual markAssistsVirtual(Long id, MultipartFile file){
    var findAssist = this.findAssistsById(id);

    if(findAssist.getAssistsStatus() != TypeAsistence.ESPERA){
      throw new BadRequestException(
        "La asistencia ya esta marcada"
      );
    }
    this.isValidTimeTolerance(findAssist, TypeAsistence.ASISTENCIA_VIRTUAL);
    
    String path = UUID.randomUUID()+"."+file.getContentType().split("/")[1];

    var createVirtual = new AssistsVirtual(
      path.toString(),
      findAssist
    );

    try {
      String res = this.azureService.uploadFile("moiso", path, file);  
      System.out.println(res);
    } catch (IOException e) {
      e.getMessage();
      throw new BadRequestException(
        "Algo salio mal al subir la imagen"
      );
    }
    findAssist.setTime(LocalDateTime.now());
    this.assistRepositary.save(
      findAssist
    );
    return this.assistVirtualRepository.save(
      createVirtual
    );
  }

  public Tolerance markToleranceVirtual(Long id, MultipartFile file){
    var findAssist = this.findAssistsById(id);

    if(findAssist.getAssistsStatus() != TypeAsistence.ESPERA){
      throw new BadRequestException(
        "La asistencia ya esta marcada"
      );
    }
    this.isValidTime(findAssist, TypeAsistence.LICENCIA);
    
    String path = UUID.randomUUID()+"."+file.getContentType().split("/")[1];

    var createVirtual = new Tolerance(
      path.toString(),
      findAssist
    );

    try {
      String res = this.azureService.uploadFile("moiso", path, file);  
      System.out.println(res);
    } catch (IOException e) {
      e.getMessage();
      throw new BadRequestException(
        "Algo salio mal al subir la imagen"
      );
    }
    findAssist.setTime(LocalDateTime.now());
    this.assistRepositary.save(
      findAssist
    );
    return this.toleranceRepository.save(
      createVirtual
    );
  }
  
  
  public void isValidTimeTolerance(Assists assists, TypeAsistence typeAsistence){
    LocalTime starTime = assists.getDetailClass().getStartTime().plus(Duration.ofMinutes(-10));
    LocalTime endFirst = assists.getDetailClass().getStartTime().plus(Duration.ofMinutes(10));
    LocalTime endSecond = endFirst.plus(Duration.ofMinutes(20));
    LocalTime now = LocalTime.now();
    Boolean isValid = this.isTimeInRange(LocalTime.now(),starTime,endSecond);
    if(isValid){
      assists.setAssistsStatus(typeAsistence);
    }

    isValid = now.isAfter(endSecond);
    if(isValid){
      throw new BadRequestException(
        "se paso de la hora de tolerancia"
      );
    }
    isValid = now.isBefore(starTime);
    if(isValid){
      throw new BadRequestException(
        "Se puede marcar desde 10 minutos antes de iniciar la clase"
      );
    }
  }

}

