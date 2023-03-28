package kz.auto_life.authservice.exceptions;

import io.swagger.v3.core.util.Json;
import kz.auto_life.authservice.payload.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ExistsException.class)
    public ResponseEntity<ResponseMessage> handleExistsException(ExistsException ex) {
        return new ResponseEntity<>(ex.getResponse(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ResponseMessage> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return new ResponseEntity<>(ex.getResponse(), HttpStatus.BAD_REQUEST);
    }
}
