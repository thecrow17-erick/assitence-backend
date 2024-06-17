package crow.uagrm.parcial.dto.school.module;

import crow.uagrm.parcial.validator.DigitsOnly.DigitsOnly;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateModuleDto {
  
  @NotNull(message = "Ingrese una descripcion")
  @Size(min = 10,max = 255, message = "Ingrese al menos 10 caracteres para describir el modulo, menor o igual a 255 caracteres para describirlos")
  private String description;

  @NotNull(message = "Ingrese una numero del modulo")
  @Size(min = 2, max=10, message = "El modulo debe ser al menos de dos digitos")
  @DigitsOnly(message = "El numero del modulo solo ingrese solamente numeros")
  private String nro;
}
