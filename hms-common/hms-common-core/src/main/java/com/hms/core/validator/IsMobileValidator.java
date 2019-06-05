package com.hms.core.validator;

import com.hms.core.annotation.IsMobile;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

	private boolean required = false;
	private Pattern pattern = Pattern.compile("1(([38]\\d)|(5[^4&&\\d])|(4[579])|(7[0135678]))\\d{8}");

	@Override
	public void initialize(IsMobile mobile) {
		required = mobile.required();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		if (StringUtils.isEmpty(value)) {
			return false;
		}
		return value.matches("^\\d{11}$");
	}

}
