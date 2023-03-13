package kz.auto_life.authservice.services.impls;

import kz.auto_life.authservice.exceptions.*;
import kz.auto_life.authservice.models.User;
import kz.auto_life.authservice.payload.UserRegisterRequest;
import kz.auto_life.authservice.repositories.UserRepository;
import kz.auto_life.authservice.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private boolean phoneExists(String phone) {
        return userRepository.findByPhone(phone) != null;
    }
    private boolean uinExists(String uin) {
        return userRepository.findByUin(uin) != null;
    }

    @Override
    @Transactional
    public User register(UserRegisterRequest request) {
        if (phoneExists(request.getPhone())) {
            throw new ExistsException(String.format("The phone '%s' already exists", request.getPhone()));
        } else if (uinExists(request.getUin())) {
            throw new ExistsException(String.format("The uin '%s' already exists", request.getUin()));
        } else {
            User user = new User();
            user.setUin(request.getUin());
            user.setFirstName(request.getFirstName());
            user.setMidName(request.getMidName());
            user.setLastName(request.getLastName());
            user.setPhone(request.getPhone());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            log.info("Saving new user with uin '{}' to the database", request.getUin());
            return userRepository.save(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userRepository.findByPhone(phone);
        if (user == null) {
            log.info("Phone '{}' does not exist, please try again", phone);
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("Phone '{}' found in the database!", phone);
        }
        return new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPassword(), new ArrayList<>());
    }
}
