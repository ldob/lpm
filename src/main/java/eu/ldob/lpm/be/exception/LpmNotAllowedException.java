package eu.ldob.lpm.be.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class LpmNotAllowedException extends Exception {
    public LpmNotAllowedException() {
        super();
    }
    public LpmNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }
    public LpmNotAllowedException(String message) {
        super(message);
    }
    public LpmNotAllowedException(Throwable cause) {
        super(cause);
    }
}
