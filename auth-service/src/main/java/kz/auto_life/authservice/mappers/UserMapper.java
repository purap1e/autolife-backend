package kz.auto_life.authservice.mappers;

import kz.auto_life.authservice.models.User;
import kz.auto_life.authservice.payload.UserRegisterResponse;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserMapper implements Function<User, UserRegisterResponse> {
    @Override
    public UserRegisterResponse apply(User user) {
        return UserRegisterResponse.builder()
                .uin(user.getUin())
                .firstName(user.getFirstName())
                .midName(user.getMidName())
                .lastName(user.getLastName())
                .phone(user.getPhone()).build();
    }
}
