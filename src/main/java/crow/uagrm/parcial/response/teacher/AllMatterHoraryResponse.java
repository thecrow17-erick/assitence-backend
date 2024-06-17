package crow.uagrm.parcial.response.teacher;

import java.util.List;

import crow.uagrm.parcial.models.DetailWork;
import lombok.Data;

@Data
public class AllMatterHoraryResponse {
  private List<DetailWork> detailWorks;

  public AllMatterHoraryResponse(List<DetailWork> detailWorks){
    this.detailWorks = detailWorks;
  }
}

