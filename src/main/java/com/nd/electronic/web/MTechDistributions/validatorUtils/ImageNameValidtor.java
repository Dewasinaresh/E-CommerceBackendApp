package com.nd.electronic.web.MTechDistributions.validatorUtils;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;

public class ImageNameValidtor implements ConstraintValidator<ImageName,String> {

    @Override
    public boolean isValid(String values, ConstraintValidatorContext constraintValidatorContext) {

        Logger log= LoggerFactory.getLogger(ImageNameValidtor.class);
        log.info("Massage name from Custom validator"+values);

        //Logic we can use different class
            if(!values.isBlank())
                return true;
            else
                return false;

    }
}
