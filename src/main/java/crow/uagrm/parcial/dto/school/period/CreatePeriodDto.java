package crow.uagrm.parcial.dto.school.period;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreatePeriodDto {
  @NotNull(message = "ingrese una fecha de inicio")
  private LocalDate init_time;

  @NotNull(message = "ingrese una fecha de finalizacion")
  private LocalDate finish_time;

  @NotNull(message = "ingrese un nombre para el periodo")
  @Size(min = 3, max = 40,message = "minimo 3 caracteres, maximo 50 caracteres")
  private String name;

  // @NotBlank(message = "ingrese un nombre para el periodo")
  @NotNull(message = "Ingrese un tipo de periodo")
  private Long type_period_id;

  // @NotBlank(message = "ingrese un nombre para el periodo")
  @NotNull(message = "Ingrese a que gestion pertenece")
  private Long management_id;
}

