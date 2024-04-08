package al.lhind.tab.claim.exception;

import org.springframework.http.HttpStatus;

public final class NotFoundException extends CustomException {
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
