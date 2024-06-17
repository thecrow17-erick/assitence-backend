package crow.uagrm.parcial.services.workloads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import crow.uagrm.parcial.dto.workloads.workload.CreateWordloadDto;
import crow.uagrm.parcial.error.exception.BadRequestException;
import crow.uagrm.parcial.models.Period;
import crow.uagrm.parcial.models.User;
import crow.uagrm.parcial.models.Workload;
import crow.uagrm.parcial.repository.worloads.WorkloadRepository;
import crow.uagrm.parcial.services.school.PeriodService;
import crow.uagrm.parcial.services.user.UserService;

@Service
public class WorkloadService {
  @Autowired
  private WorkloadRepository workloadRepository;

  @Autowired
  private DetailWorkService detailWorkService;

  @Autowired
  private UserService userService;

  @Autowired
  private PeriodService periodService;

  public Page<Workload> findWorkLoadUser(
    int skip,
    int limit,
    Long user_id 
  ){
    Pageable pageable = PageRequest.of(skip, limit);
    var userFind = this.userService.findUserById(user_id);
    return this.workloadRepository.findAllByUser(pageable, userFind);
  }

  public Workload createWorkLoad(CreateWordloadDto createWordloadDto){
    var findUser = this.userService.findUserById(createWordloadDto.getUser_id());
    var findPeriod = this.periodService.findByIdPeriod(createWordloadDto.getPeriod_id());
    
    var isValid = this.findUserPeriod(findUser,findPeriod);
    if(isValid){
      throw new BadRequestException(
      "El docente ya tiene una carga horaria en este periodo"
      );
    }
    var createWorkload = this.createWorkLoad(findUser,findPeriod);

    this.detailWorkService.detailCreateWorks(createWordloadDto.getMatters(),createWorkload);

    return createWorkload;
  }

  public Workload createWorkLoad(User user, Period period){
    var createWorkload = new Workload(user, period);
    return this.workloadRepository.save(createWorkload);
  }

  public Boolean findUserPeriod(User user,Period period){
    var findWorkUser = this.workloadRepository.findByUser(user);
    var findPeriod = this.workloadRepository.findByPeriod(period);
    if(findPeriod.isPresent() && findWorkUser.isPresent()){
      if(findPeriod.get().getId() == findWorkUser.get().getId()){
        return true;
      }
    }
    return false;
  }

  public Workload findWorkUserPeriod(User user,Period period){
    var findWorkUser = this.workloadRepository.findByUser(user);
    if(!findWorkUser.isPresent()){
      throw new BadRequestException(
        "el usuario no tiene una carga horaria"
      );
    }
    if(findWorkUser.get().getPeriod().getId() != period.getId()){
      throw new BadRequestException(
        "el usuario no tiene una carga horaria"
      );
    }
    return findWorkUser.get();
  }
}
