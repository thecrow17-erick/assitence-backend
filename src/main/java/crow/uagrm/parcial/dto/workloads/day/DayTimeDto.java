package crow.uagrm.parcial.dto.workloads.day;

import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DayTimeDto {
  
  @NotNull(message = "El dia es requerido")
  private DayOfWeek day;

  @NotNull(message = "el inicio de la hora de clase es requerido")

  private LocalTime start_time;

  @NotNull(message = "el final de la hora de clase es requerido")
  private LocalTime end_time;

  @NotNull(message = "es requerido el aula")
  private Long classroom_id;
}
