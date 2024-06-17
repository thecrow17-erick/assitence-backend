package crow.uagrm.parcial.services.teachers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import crow.uagrm.parcial.models.DetailClass;
import crow.uagrm.parcial.models.DetailWork;
import crow.uagrm.parcial.services.school.PeriodService;
import crow.uagrm.parcial.services.user.UserService;
import crow.uagrm.parcial.services.workloads.DetailClassService;
import crow.uagrm.parcial.services.workloads.DetailWorkService;
import crow.uagrm.parcial.services.workloads.WorkloadService;

@Service
public class HoraryService {
  
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


  public List<DetailClass> findByClassUser(Long id){
    List<DetailClass> details = new ArrayList<>();
    var user = this.userService.findUserById(id);
    var period = this.periodService.finOnePeriodTrue();
    var work = this.workloadService.findWorkUserPeriod(user, period);
    var detailsWorks= this.detailWorkService.findDetailWorks(work);
    for (DetailWork detailWork : detailsWorks) {
      var detailClass = this.detailClassService.findDetailClassWork(detailWork);
      if(detailClass.size() > 0){
        details.addAll(detailClass);
      }
    }
    return details;
  }

  public List<DetailWork> findByDetailWorkUser(Long id){
    var user = this.userService.findUserById(id);
    var period = this.periodService.finOnePeriodTrue();
    var work = this.workloadService.findWorkUserPeriod(user, period);
    var detailsWorks= this.detailWorkService.findDetailWorks(work);
    return detailsWorks;
  }


}
