package kz.auto_life.authservice.services;


import kz.auto_life.authservice.payload.UserRegisterRequest;
import kz.auto_life.models.entities.User;

public interface UserService {
    User register(UserRegisterRequest request);
}
