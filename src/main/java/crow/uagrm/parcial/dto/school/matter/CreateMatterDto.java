package crow.uagrm.parcial.dto.school.matter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateMatterDto {
  @NotNull(message = "Ingrese un nombre")
  @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
  private String name;


  @NotNull(message = "Ingrese un code")
  @Size(min = 6, message = "El code debe tener al menos 6 caracteres")
  private String code;

  @NotNull(message = "Ingrese alguna carrera")
  private Long career_id;
}
