package crow.uagrm.parcial.response.school.period;

import java.util.List;

import crow.uagrm.parcial.models.Period;
import lombok.Data;

@Data
public class ListPeriodResponse {
  private List<Period> periods; 

  public ListPeriodResponse(List<Period> periods){
    this.periods = periods;
  }
}
