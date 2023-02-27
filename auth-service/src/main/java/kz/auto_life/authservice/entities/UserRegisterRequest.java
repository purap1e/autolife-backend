package kz.auto_life.authservice.entities;

import lombok.Getter;

@Getter
public class UserRegisterRequest {

    private String uin;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
}
