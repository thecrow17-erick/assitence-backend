package crow.uagrm.parcial.services.workloads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import crow.uagrm.parcial.dto.workloads.matter.MatterDto;
import crow.uagrm.parcial.models.DetailWork;
import crow.uagrm.parcial.models.Group;
import crow.uagrm.parcial.models.Matter;
import crow.uagrm.parcial.models.Workload;
import crow.uagrm.parcial.repository.worloads.DetailWorkRepository;
import crow.uagrm.parcial.services.school.GroupService;
import crow.uagrm.parcial.services.school.MatterService;

@Service
public class DetailWorkService {
  
  @Autowired
  private DetailWorkRepository detailWorkRepository;

  @Autowired
  private GroupService groupService;

  @Autowired
  private MatterService matterService;

  @Autowired
  private DetailClassService detailClassService;

  public void detailCreateWorks(List<MatterDto> matterDtos, Workload workload){
    for (MatterDto matterDto : matterDtos) {
      var findGroup = this.groupService.findGroupById(matterDto.getGroup_id());
      var findMatter = this.matterService.findMatterById(matterDto.getMatter_id());
      var createDetail = this.createDetailWork(
        workload,
        findGroup,
        findMatter
      );
      this.detailClassService.createDetailClassDays(createDetail, matterDto.getDays(),workload);

    }
  }

  public DetailWork createDetailWork(
    Workload workload,
    Group group,
    Matter matter
  ){
    return this.detailWorkRepository.save(
      new DetailWork(workload, group, matter)
    );
  }


  public List<DetailWork> findDetailWorks(Workload workload){
    return this.detailWorkRepository.findAllByWorkload(workload);
  }

}
