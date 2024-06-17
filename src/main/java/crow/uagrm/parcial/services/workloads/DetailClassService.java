package crow.uagrm.parcial.services.workloads;

import java.time.DayOfWeek;
import java.time.LocalTime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crow.uagrm.parcial.dto.workloads.day.DayTimeDto;
import crow.uagrm.parcial.models.Classroom;
import crow.uagrm.parcial.models.DetailClass;
import crow.uagrm.parcial.models.DetailWork;
import crow.uagrm.parcial.models.Workload;
import crow.uagrm.parcial.repository.worloads.DetailClassRepository;
import crow.uagrm.parcial.services.school.ClassroomService;

@Service
public class DetailClassService {
  
  @Autowired
  private DetailClassRepository detailClassRepository;

  @Autowired
  private ClassroomService classroomService;

  @Autowired
  private AssistService assistService;

  public void createDetailClassDays(DetailWork detailWork, List<DayTimeDto> dayTimeDtos,Workload workload){
    for (DayTimeDto  dayTimeDto: dayTimeDtos) {
      var findClassroom = this.classroomService.findByIdClassroom(dayTimeDto.getClassroom_id());
      var detailCreate = this.createDetailClass(
        dayTimeDto.getDay(), dayTimeDto.getStart_time(), dayTimeDto.getEnd_time(), findClassroom, detailWork
      );
      this.assistService.createAssists(workload, detailCreate, detailWork.getMatter());
    }

  }
  public DetailClass createDetailClass(
    DayOfWeek dayNames,
    LocalTime startTime,
    LocalTime endTime,
    Classroom classroom,
    DetailWork detailWork
  ){
    return this.detailClassRepository.save(
      new DetailClass(dayNames, startTime, endTime, classroom, detailWork)
    );
  }

  public List<DetailClass> findDetailClassWork(DetailWork detailWork){
    return this.detailClassRepository.findAllByDetailWork(detailWork);
  }
}
