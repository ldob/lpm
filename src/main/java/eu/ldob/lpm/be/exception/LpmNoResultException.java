package eu.ldob.lpm.be.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class LpmNoResultException extends RuntimeException {
    public LpmNoResultException() {
        super();
    }
    public LpmNoResultException(String message, Throwable cause) {
        super(message, cause);
    }
    public LpmNoResultException(String message) {
        super(message);
    }
    public LpmNoResultException(Throwable cause) {
        super(cause);
    }
}
