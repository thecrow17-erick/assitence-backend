package crow.uagrm.parcial.validator.DigitsOnly;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DigitsOnlyValidator implements ConstraintValidator<DigitsOnly, String> {
  @Override
    public void initialize(DigitsOnly constraintAnnotation) {
        // Inicialización si es necesario
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // El valor nulo se considera válido, usa @NotNull para verificar si es obligatorio
        }
        return value.matches("\\d+"); // Verifica si el valor solo contiene dígitos
    }
}
