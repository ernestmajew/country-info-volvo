package pl.majewski.countryinfovolvo.exception;

import org.springframework.http.HttpStatus;

public class CountryNotFoundException extends ServiceException {
    public CountryNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
