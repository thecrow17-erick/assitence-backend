package crow.uagrm.parcial.dto.workloads.workload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ViewUserWorkloadDto {
  
  @NotNull(message = "Ingrese un usuario")
  private Long user_id;
}
