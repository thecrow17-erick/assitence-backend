package crow.uagrm.parcial.response.teacher;

import java.util.List;

import crow.uagrm.parcial.models.DetailClass;
import lombok.Data;

@Data
public class AllHoraryResponse {
  private List<DetailClass> detailClasses;

  public AllHoraryResponse(List<DetailClass> detailClasses){
    this.detailClasses = detailClasses;
  }
}

