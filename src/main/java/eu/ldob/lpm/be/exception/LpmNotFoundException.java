package eu.ldob.lpm.be.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LpmNotFoundException extends Exception {
    public LpmNotFoundException() {
        super();
    }
    public LpmNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public LpmNotFoundException(String message) {
        super(message);
    }
    public LpmNotFoundException(Throwable cause) {
        super(cause);
    }
}
