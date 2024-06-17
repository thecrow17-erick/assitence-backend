package crow.uagrm.parcial.response.school.career;

import crow.uagrm.parcial.models.Career;
import lombok.Data;


@Data
public class CareerCreateResponse {
  private Career career;

  public CareerCreateResponse(Career career){
    this.career = career;
  }
}
