package crow.uagrm.parcial.validator.BoliviaPhone;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = BoliviaPhoneValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface BoliviaPhoneNumber {
  String message() default "Invalid Bolivian phone number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


