package crow.uagrm.parcial.dto.teacher;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MarkAssistsDto {
  
  @NotNull(message = "ingrese el codigo qr")
  private UUID codeQr;
}
