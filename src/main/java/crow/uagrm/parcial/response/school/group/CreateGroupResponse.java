package crow.uagrm.parcial.response.school.group;

import crow.uagrm.parcial.models.Group;
import lombok.Data;

@Data
public class CreateGroupResponse {
  private Group group;


  public CreateGroupResponse(Group group){
    this.group = group;
  }
}
