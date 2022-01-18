package com.example.authur.common.validator;

import com.example.authur.common.annotation.IsMobile;
import com.example.authur.common.entity.RegexpConstant;
import com.example.authur.common.utils.AuthurUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description:
 * @Author: jibing.Li
 * @Date: 2022/1/18 16:31
 */
public class MobileValidator implements ConstraintValidator<IsMobile, String> {

    @Override
    public void initialize(IsMobile isMobile) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isBlank(s)) {
                return true;
            } else {
                String regex = RegexpConstant.MOBILE_REG;
                return AuthurUtils.match(regex, s);
            }
        } catch (Exception e) {
            return false;
        }
    }
}
