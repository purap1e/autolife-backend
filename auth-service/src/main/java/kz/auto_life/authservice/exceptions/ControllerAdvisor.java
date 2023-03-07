package kz.auto_life.authservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(PhoneExistsException.class)
    public ResponseEntity<Object> handleUsernameExistsException(PhoneExistsException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("errorCode", "phone_exists");
        body.put("errorDescription", String.format("The phone '%s' already exists", ex.getPhone()));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UinExistsException.class)
    public ResponseEntity<Object> handleUinExistsException(UinExistsException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("errorCode", "uin_exists");
        body.put("errorDescription", String.format("The uin '%s' already exists", ex.getUin()));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
