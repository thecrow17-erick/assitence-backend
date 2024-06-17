package crow.uagrm.parcial.response.school.typePeriod;

import crow.uagrm.parcial.models.TypePeriod;
import lombok.Data;

@Data
public class CreateTypePeriodResponse {
  private TypePeriod typePeriod;

  public CreateTypePeriodResponse(TypePeriod typePeriod){
    this.typePeriod = typePeriod;
  }
}
