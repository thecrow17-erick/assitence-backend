package crow.uagrm.parcial.dto.workloads.workload;

import java.util.List;

import crow.uagrm.parcial.dto.workloads.matter.MatterDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateWordloadDto {
  @NotNull(message = "el docente es requerido")
  private Long user_id;

  @NotNull(message = "el periodo es requerido")
  private Long period_id;

  @Size(min = 1, message = "minimo 1 materia de carga se requiere")
  private List<MatterDto> matters;
}
