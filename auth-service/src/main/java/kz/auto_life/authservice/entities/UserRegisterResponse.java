package kz.auto_life.authservice.entities;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserRegisterResponse {
    private String uin;
    private String firstName;
    private String lastName;
    private String phone;
}
