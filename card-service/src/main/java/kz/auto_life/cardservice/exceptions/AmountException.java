package kz.auto_life.cardservice.exceptions;

import kz.auto_life.cardservice.payload.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Actor Not Found")
@Getter
@Setter
@AllArgsConstructor
public class AmountException extends RuntimeException {
    private ResponseMessage response;
}
