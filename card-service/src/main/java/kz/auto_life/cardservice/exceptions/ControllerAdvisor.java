package kz.auto_life.cardservice.exceptions;

import kz.auto_life.cardservice.payload.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AmountException.class)
    public ResponseEntity<ResponseMessage> handleExistsException(AmountException ex) {
        return new ResponseEntity<>(ex.getResponse(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ResponseMessage> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return new ResponseEntity<>(ex.getResponse(), HttpStatus.BAD_REQUEST);
    }
}
