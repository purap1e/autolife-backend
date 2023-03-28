package kz.auto_life.authservice.payload;

import kz.auto_life.authservice.modules.User;
import lombok.Getter;

@Getter
public class UserRegisterRequest {

    private String uin;
    private String password;
    private String firstName;
    private String midName;
    private String lastName;
    private String phone;

    public static UserRegisterResponse from(User user) {
        return UserRegisterResponse.builder()
                .uin(user.getUin())
                .firstName(user.getFirstName())
                .midName(user.getMidName())
                .lastName(user.getLastName())
                .phone(user.getPhone()).build();
    }
}
