package crow.uagrm.parcial.response.school.management;

import crow.uagrm.parcial.models.Management;
import lombok.Data;

@Data
public class CreateManagementResponse {
  private Management management;

  public CreateManagementResponse(Management management){
    this.management = management;
  }
}
