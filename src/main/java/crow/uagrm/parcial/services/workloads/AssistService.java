package crow.uagrm.parcial.services.workloads;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import crow.uagrm.parcial.dto.firebase.FirebaseMessageDto;
import crow.uagrm.parcial.error.exception.NotFoundException;
import crow.uagrm.parcial.models.Assists;
import crow.uagrm.parcial.models.DetailClass;
import crow.uagrm.parcial.models.Matter;
import crow.uagrm.parcial.models.Workload;
import crow.uagrm.parcial.repository.worloads.AssistRepositary;
import crow.uagrm.parcial.services.firebase.FirebaseMessageService;
import crow.uagrm.parcial.services.user.UserService;
import crow.uagrm.parcial.utils.TypeAsistence;

@Service
public class AssistService {
  
  
  @Autowired
  private ThreadPoolTaskScheduler taskScheduler;

  @SuppressWarnings("unused")
  private ScheduledFuture<?> future;


  @Autowired
  private AssistRepositary assistRepositary;
  @Autowired
  private FirebaseMessageService firebaseMessageService;
  @Autowired
  private UserService userService;
  
  public void createAssists(
    Workload workload,
    DetailClass detailClass,
    Matter matter
  ){
    LocalDate currentDate = workload.getPeriod().getStartDate();

    while (!currentDate.isAfter(workload.getPeriod().getEndDate())) {
      if(currentDate.getDayOfWeek() == detailClass.getDay()){
        Instant dateInit = this.transformLocal(currentDate, detailClass.getStartTime());
        Instant dateFinish = this.transformLocal(currentDate, detailClass.getEndTime());
        var createAssists = this.createAsissts(LocalDateTime.of(currentDate, detailClass.getStartTime()), detailClass);

        this.SendBeforeClass(
          workload.getUser().getId(),matter ,dateInit.plus(Duration.ofMinutes(-15))
        );

        this.SendAfterClass(
          workload.getUser().getId(), createAssists.getId(),matter,dateFinish.plus(Duration.ofMinutes(15))
        );
      }
      currentDate = currentDate.plusDays(1);
    }
  }

  private void SendAfterClass(Long user, Long assists, Matter matter,Instant dateFinish){
    this.future = this.taskScheduler.schedule(()->{
      var userFind = this.userService.findUserById(user);
      var findeAsistent = this.findAssistsById(assists);
      if(findeAsistent.getAssistsStatus() == TypeAsistence.ESPERA){
        findeAsistent.setAssistsStatus(TypeAsistence.NO_ASISTIO);
        findeAsistent.setTime(LocalDateTime.now());
        this.assistRepositary.save(
          findeAsistent
        );
        if(userFind.getTokenFMC() != null){
          var message = this.firebaseMessageService.sendNotificationByToken(
            new FirebaseMessageDto(
              "Acabas de faltar a tu clase de "+matter.getName(), 
            "Acabas de faltarte a tu clase!", 
            userFind.getTokenFMC())
          );
          System.out.println(message);
        }
      }
    }, dateFinish);
  }

  private void SendBeforeClass(Long user,Matter matter ,Instant dateInit){
    this.future = this.taskScheduler.schedule(()->{
      var userFind = this.userService.findUserById(user);
      if(userFind.getTokenFMC() != null){
        var message = this.firebaseMessageService.sendNotificationByToken(
          new FirebaseMessageDto(
            "su clase de "+matter.getName()+" esta por comenzar, no te olvides de marcar asistencia. ", 
          "Su clase esta por comenzar, en 15 minutos!", 
          userFind.getTokenFMC())
        );
        System.out.println(message);
      }
    }, dateInit);
  }

  private Instant transformLocal(LocalDate date, LocalTime time){
    LocalDateTime localDateTime = LocalDateTime.of(date, time);
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()).toInstant();
  }

  public Assists findAssistsById(Long id){
    var find = this.assistRepositary.findById(id);
    if(find.isEmpty()){
      throw new NotFoundException(
        "asistencia not found"
      );
    }
    return find.get();
  }

  public Assists createAsissts(
    LocalDateTime time,
    DetailClass detailClass
  ){
    return this.assistRepositary.save(
      new Assists(time, TypeAsistence.ESPERA, detailClass)
    );
  }



}
