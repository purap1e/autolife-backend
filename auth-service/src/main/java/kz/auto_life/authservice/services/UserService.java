package kz.auto_life.authservice.services;


import kz.auto_life.authservice.models.User;
import kz.auto_life.authservice.payload.UserRegisterRequest;

public interface UserService {
    User register(UserRegisterRequest request);
    String updatePassword(String phone, String newPassword);
}
