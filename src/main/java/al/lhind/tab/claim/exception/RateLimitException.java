package al.lhind.tab.claim.exception;

import org.springframework.http.HttpStatus;

public class RateLimitException extends CustomException{
    public RateLimitException(String message) {
        super(message, HttpStatus.TOO_MANY_REQUESTS);
    }
}
