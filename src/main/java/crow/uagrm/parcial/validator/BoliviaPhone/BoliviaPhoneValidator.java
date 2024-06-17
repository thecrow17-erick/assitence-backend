package crow.uagrm.parcial.validator.BoliviaPhone;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;



public class BoliviaPhoneValidator implements ConstraintValidator<BoliviaPhoneNumber, String>{
  private static final String BOLIVIAN_PHONE_REGEX = "^(6|7)\\d{7}$|^\\d{7}$";

    @Override
    public void initialize(BoliviaPhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null) {
            return false;
        }
        return Pattern.matches(BOLIVIAN_PHONE_REGEX, phoneNumber);
    }
}
