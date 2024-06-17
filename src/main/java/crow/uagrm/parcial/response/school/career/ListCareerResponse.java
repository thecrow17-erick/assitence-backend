package crow.uagrm.parcial.response.school.career;

import java.util.List;

import crow.uagrm.parcial.models.Career;
import lombok.Data;

@Data
public class ListCareerResponse {
  private List<Career> careers; 

  public ListCareerResponse(List<Career> careers){
    this.careers = careers;
  }

}
