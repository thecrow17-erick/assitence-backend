package crow.uagrm.parcial.response.teacher;

import crow.uagrm.parcial.models.AssistsVirtual;
import lombok.Data;

@Data
public class MarkAssistVirtualResponse {
  private AssistsVirtual assistsVirtual;


  public MarkAssistVirtualResponse(AssistsVirtual assistsVirtual){
    this.assistsVirtual = assistsVirtual;
  }
}
