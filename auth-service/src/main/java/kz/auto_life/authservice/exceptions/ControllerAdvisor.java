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
    @ExceptionHandler(UinExistsException.class)
    public ResponseEntity<Object> handleUsernameExistsException(UinExistsException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("errorCode", "uin_exists");
        body.put("errorDescription", String.format("The uin '%s' already exists", ex.getUin()));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
