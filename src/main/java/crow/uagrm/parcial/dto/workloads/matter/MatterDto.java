package crow.uagrm.parcial.dto.workloads.matter;

import java.util.List;

import crow.uagrm.parcial.dto.workloads.day.DayTimeDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MatterDto {
  @NotNull(message = "es requerido la materia")
  private Long matter_id;

  @NotNull(message = "es requerido un grupo")
  private Long group_id;
  
  @Size(min = 1, message = "minimo 1 dia es requerido")
  private List<DayTimeDto> days;
}
