package com.rookie.im.common.annotation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/1814:16
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(StringUtils.isEmpty(value)){
            return true;
        }else{
            return MobileValidate.isMoblie(value);
        }
    }
}
