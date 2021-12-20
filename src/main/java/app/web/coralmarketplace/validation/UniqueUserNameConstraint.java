package app.web.coralmarketplace.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Interface for validation of unique user name.
 */
@Documented
@Constraint(validatedBy = UniqueUserNameValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUserNameConstraint {
    String message() default "The name of the user must be unique.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
