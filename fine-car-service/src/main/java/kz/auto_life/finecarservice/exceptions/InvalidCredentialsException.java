package kz.auto_life.finecarservice.exceptions;

import kz.auto_life.finecarservice.payload.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Actor Not Found")
@Getter
@AllArgsConstructor
public class InvalidCredentialsException extends RuntimeException {
    private ResponseMessage response;
}
