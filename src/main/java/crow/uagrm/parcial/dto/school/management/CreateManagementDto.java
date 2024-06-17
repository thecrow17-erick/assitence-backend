package crow.uagrm.parcial.dto.school.management;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateManagementDto {
  @NotNull(message = "Ingrese un nombre")
  @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
  private String name;

  @NotNull(message = "Ingrese una descripcion")
  @Size(min = 5,max = 255 ,message = "El nombre debe tener al menos 5 caracteres,maximo 255 caracteres")
  private String description;
}
