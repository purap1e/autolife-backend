package kz.auto_life.shopservice.exceptions;

import kz.auto_life.shopservice.payload.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Actor Not Found")
@Getter
@AllArgsConstructor
public class UnauthorizedException extends RuntimeException {
    private ResponseMessage response;
}
