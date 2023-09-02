package simple.microservice.domain.helpers;

import simple.microservice.domain.helpers.validation.error.CpfValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {CpfValidator.class}
)
public @interface Cpf {
    String message() default "{cpf.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}