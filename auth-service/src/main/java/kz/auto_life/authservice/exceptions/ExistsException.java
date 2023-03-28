package kz.auto_life.authservice.exceptions;

import kz.auto_life.authservice.payload.ResponseMessage;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Actor Not Found")
@Getter
@Setter
@AllArgsConstructor
public class ExistsException extends RuntimeException {
    private ResponseMessage response;
}
