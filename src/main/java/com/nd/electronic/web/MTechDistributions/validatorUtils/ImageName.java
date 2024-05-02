package com.nd.electronic.web.MTechDistributions.validatorUtils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidtor.class)
public @interface ImageName  {

    String message() default "{Image name is  not empty}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};




}
