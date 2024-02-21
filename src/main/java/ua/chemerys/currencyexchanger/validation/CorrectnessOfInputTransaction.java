package ua.chemerys.currencyexchanger.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CorrectnessOfInputTransactionConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CorrectnessOfInputTransaction {

    String message() default "Incorrect input of the transaction's data";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
