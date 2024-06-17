package crow.uagrm.parcial.dto.user;


import crow.uagrm.parcial.validator.BoliviaPhone.BoliviaPhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserDto {
    @NotNull(message = "Ingrese un nombre")
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    private String name;

    @NotNull(message = "Ingrese un email")
    @Email(message = "Ingrese un correo válido")
    private String email;

    @NotNull(message = "Ingese un numero de telefono")
    @Size(min = 8,max = 8,message = "Ingrese un numero valido de 8 caracteres")
    @BoliviaPhoneNumber(message = "Ingrese un numero que pertecene a bolivia")
    private String phone;
}