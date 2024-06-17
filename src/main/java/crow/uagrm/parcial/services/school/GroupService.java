package crow.uagrm.parcial.services.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import crow.uagrm.parcial.dto.school.group.CreateGroupDto;
import crow.uagrm.parcial.error.exception.BadRequestException;
import crow.uagrm.parcial.error.exception.NotFoundException;
import crow.uagrm.parcial.models.Group;
import crow.uagrm.parcial.repository.school.GroupRepository;

@Service
public class GroupService {
  
  @Autowired
  private GroupRepository groupRepository;


  public List<Group> findAllGroups(){
    return this.groupRepository.findAll();
  }

  public Group createdGroup(CreateGroupDto createGroupDto){
    var findGruop = this.groupRepository.findByName(createGroupDto.getName().toLowerCase());
    if(findGruop.isPresent()){
      throw new BadRequestException(
        "group is invalid"
      );
    }
    
    var groupCreate = new Group(
      createGroupDto.getName().toLowerCase()
    );
    return this.groupRepository.save(groupCreate);
  }

  public Group findGroupById(Long id){
    var findGroup = this.groupRepository.findById(id);
    if(findGroup.isEmpty()){
      throw new NotFoundException(
        "group not found"
      );
    }
    return findGroup.get();
  }
}
