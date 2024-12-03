package pl.majewski.countryinfovolvo.exception;

import org.springframework.http.HttpStatus;

public class InvalidCountryCodeException extends ServiceException {
    public InvalidCountryCodeException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
