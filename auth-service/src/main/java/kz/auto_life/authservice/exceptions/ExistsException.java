package kz.auto_life.authservice.exceptions;

import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Actor Not Found")
@Getter
@AllArgsConstructor
public class ExistsException extends RuntimeException {
    private String message;
}
