package by.team34.entity.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {

    @Override
    public final void initialize(Name paramA) {
    }

    @Override
    public final boolean isValid(String name, ConstraintValidatorContext ctx) {
        if (name == null) {
            return false;
        }
        return name.matches("^[A-Za-zА-Яа-я]{2,20}$");
    }

}