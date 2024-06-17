package crow.uagrm.parcial.response.school.management;

import java.util.List;

import crow.uagrm.parcial.models.Management;
import lombok.Data;

@Data
public class ListManagementResponse {
  private List<Management> managements; 

  public ListManagementResponse(List<Management> managements){
    this.managements = managements;
  }

}
