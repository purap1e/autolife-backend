package kz.auto_life.authservice.payload;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserRegisterResponse {
    private String uin;
    private String firstName;
    private String midName;
    private String lastName;
    private String phone;
}
