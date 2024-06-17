package crow.uagrm.parcial.dto.school.classroom;

import crow.uagrm.parcial.validator.DigitsOnly.DigitsOnly;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateClassroomDto {
  @NotNull(message = "Ingrese una descripcion del aula")
  @Size(min = 10,max = 255, message = "Ingrese al menos 10 caracteres para describir el aula, menor o igual a 255 caracteres para describirlos")
  private String description;

  @NotNull(message = "Ingrese una numero del aula")
  @Size(min = 2, max=10, message = "El aula debe ser al menos de dos digitos, maximo como diez")
  @DigitsOnly(message = "El numero del aula solo ingrese solamente numeros")
  private String nro;

  @NotNull(message = "Ingrese a que modulo pertenece el aula")
  private Long module_id;
}
