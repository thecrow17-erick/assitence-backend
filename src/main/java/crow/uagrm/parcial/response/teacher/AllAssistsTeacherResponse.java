package crow.uagrm.parcial.response.teacher;

import java.util.List;

import crow.uagrm.parcial.models.Assists;
import lombok.Data;

@Data
public class AllAssistsTeacherResponse {
  private List<Assists> assists;

  public AllAssistsTeacherResponse(List<Assists> assists){
    this.assists = assists;
  }
}
