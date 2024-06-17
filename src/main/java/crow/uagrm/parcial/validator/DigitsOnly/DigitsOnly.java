package crow.uagrm.parcial.validator.DigitsOnly;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


@Constraint(validatedBy = DigitsOnlyValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface DigitsOnly {
  String message() default "El campo solo debe contener dígitos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
