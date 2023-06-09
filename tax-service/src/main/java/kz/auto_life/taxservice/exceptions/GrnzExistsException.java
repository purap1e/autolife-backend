package kz.auto_life.taxservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Actor Not Found")
@Getter
@AllArgsConstructor
public class GrnzExistsException extends RuntimeException {
    private String message;
}
