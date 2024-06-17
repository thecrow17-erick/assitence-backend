package crow.uagrm.parcial.response.school.group;

import java.util.List;

import crow.uagrm.parcial.models.Group;
import lombok.Data;

@Data
public class ListGroupResponse {
  private List<Group> groups;

  public ListGroupResponse(List<Group> groups){
    this.groups = groups;
  }

}
