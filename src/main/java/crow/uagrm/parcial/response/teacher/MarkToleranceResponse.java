package crow.uagrm.parcial.response.teacher;

import crow.uagrm.parcial.models.Tolerance;
import lombok.Data;

@Data
public class MarkToleranceResponse {
  private Tolerance tolerance;


  public MarkToleranceResponse(Tolerance assistsVirtual){
    this.tolerance = assistsVirtual;
  }
}
