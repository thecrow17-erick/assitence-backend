package crow.uagrm.parcial.response.school.matter;

import crow.uagrm.parcial.models.Matter;
import lombok.Data;

@Data
public class MatterCreateResponse {
  private Matter matter;

  public MatterCreateResponse(Matter matter){
    this.matter = matter;
  }
}
