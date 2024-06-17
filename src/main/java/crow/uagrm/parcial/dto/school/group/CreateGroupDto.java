package crow.uagrm.parcial.dto.school.group;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateGroupDto {
  @NotNull(message = "Ingrese un nombre para el grupo")
  @Size(min = 2, max=10,message = "minimo 2 caracteres, un maximo de 10")
  private String name;
}
