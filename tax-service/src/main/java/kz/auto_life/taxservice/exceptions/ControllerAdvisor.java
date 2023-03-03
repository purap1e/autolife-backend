package kz.auto_life.taxservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(GrnzExistsException.class)
    public ResponseEntity<Object> handleUsernameExistsException(GrnzExistsException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("errorCode", "grnz_exists");
        body.put("errorDescription", String.format("The grnz '%s' already exists", ex.getGrnz()));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
