package crow.uagrm.parcial.response.school.typePeriod;

import java.util.List;

import crow.uagrm.parcial.models.TypePeriod;
import lombok.Data;

@Data
public class ListTypePeriodResponse {
  private List<TypePeriod> typePeriods; 

  public ListTypePeriodResponse(List<TypePeriod> typePeriods){
    this.typePeriods = typePeriods;
  }

}
