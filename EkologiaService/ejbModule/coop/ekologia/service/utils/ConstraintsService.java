package coop.ekologia.service.utils;

import javax.ejb.Stateless;

import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.DoubleValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.commons.validator.routines.UrlValidator;

@Stateless
public class ConstraintsService implements ConstraintsServiceInterface {
	@Override
	public boolean isEmpty(String value) {
		return value == null || value.isEmpty();
	}

	@Override
	public boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}

	@Override
	public boolean isInteger(String value) {
		return IntegerValidator.getInstance().isValid(value);
	}

	@Override
	public boolean isNotInteger(String value) {
		return !isInteger(value);
	}

	@Override
	public boolean isDouble(String value) {
		return DoubleValidator.getInstance().isValid(value);
	}

	@Override
	public boolean isNotDouble(String value) {
		return !isDouble(value);
	}

	@Override
	public boolean isDate(String value) {
		return DateValidator.getInstance().isValid(value, "yyyy-MM-dd");
	}

	@Override
	public boolean isNotDate(String value) {
		return !isDate(value);
	}

	@Override
	public boolean isDatetime(String value) {
		return DateValidator.getInstance().isValid(value,
				"yyyy-MM-dd\\THH:mm:ss");
	}

	@Override
	public boolean isNotDatetime(String value) {
		return !isDate(value);
	}

	@Override
	public boolean isUrl(String value) {
		return UrlValidator.getInstance().isValid(value);
	}

	@Override
	public boolean isNotUrl(String value) {
		return !isUrl(value);
	}

    @Override
    public boolean isEmail(String value) {
        return EmailValidator.getInstance().isValid(value);
    }

    @Override
    public boolean isNotEmail(String value) {
        return !isEmail(value);
    }

    @Override
    public boolean isSecuredPassword(String value) {
        return value.length() >= 8 && value.matches("(.*)[a-zA-Z](.*)") && value.matches("(.*)[0-9](.*)");
    }

    @Override
    public boolean isNotSecuredPassword(String value) {
        return !isSecuredPassword(value);
    }
}
