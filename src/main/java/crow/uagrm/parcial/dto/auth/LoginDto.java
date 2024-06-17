package crow.uagrm.parcial.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDto {
  
  @NotNull(message = "Ingrese un email en el campo email")
  @Email(message = "Ingrese un email valido")
  private String email;

  @NotNull(message = "Ingrese un email en el campo contrasenha")
  private String password;

  private String tokenFMC;
}
