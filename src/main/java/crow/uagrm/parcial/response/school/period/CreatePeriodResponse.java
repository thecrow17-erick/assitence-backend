package crow.uagrm.parcial.response.school.period;

import crow.uagrm.parcial.models.Period;
import lombok.Data;

@Data
public class CreatePeriodResponse {
  private Period period;


  public CreatePeriodResponse(Period period){
    this.period = period;
  }
}
