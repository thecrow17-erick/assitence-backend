package crow.uagrm.parcial.response.school.matter;

import java.util.List;

import crow.uagrm.parcial.models.Matter;
import lombok.Data;

@Data
public class ListMatterResponse {
  private List<Matter> matters; 

  public ListMatterResponse(List<Matter> matters){
    this.matters = matters;
  }

}
