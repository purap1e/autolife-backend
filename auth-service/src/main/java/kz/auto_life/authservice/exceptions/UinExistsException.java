package kz.auto_life.authservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Actor Not Found")
@Data
@AllArgsConstructor
public class UinExistsException extends RuntimeException {
    private String uin;
}
