package crow.uagrm.parcial.response.teacher;

import crow.uagrm.parcial.models.Assists;
import lombok.Data;

@Data
public class MarkAssistResponse {
  private Assists assists;

  public MarkAssistResponse(Assists assists){
    this.assists = assists;
  }
}
