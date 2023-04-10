package kz.auto_life.authservice.services.impls;

import kz.auto_life.authservice.exceptions.ExistsException;
import kz.auto_life.authservice.exceptions.InvalidCredentialsException;
import kz.auto_life.authservice.mappers.UserMapper;
import kz.auto_life.authservice.models.User;
import kz.auto_life.authservice.payload.ResponseMessage;
import kz.auto_life.authservice.payload.UserRegisterRequest;
import kz.auto_life.authservice.payload.UserRegisterResponse;
import kz.auto_life.authservice.repositories.UserRepository;
import kz.auto_life.authservice.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final ServletContext servletContext;
    private final static int LENGTH_IIN = 12;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, ServletContext servletContext) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.servletContext = servletContext;
    }

    private boolean phoneExists(String phone) {
        return userRepository.findByPhone(phone) != null;
    }
    private boolean uinExists(String uin) {
        return userRepository.findByUin(uin) != null;
    }
    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userRepository.findByPhone(phone);
        if (user == null) {
            log.info("Phone '{}' does not exist, please try again", phone);
            throw new InvalidCredentialsException(new ResponseMessage("Invalid credentials"));
        } else {
            log.info("Phone '{}' found in the database!", phone);
        }
        return new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public User register(UserRegisterRequest request) {
        if (uinExists(request.getUin())) {
            throw new ExistsException(new ResponseMessage(String.format("Iin '%s' already exists", request.getUin())));
        } else if (phoneExists(request.getPhone())) {
            throw new ExistsException(new ResponseMessage(String.format("Phone '%s' already exists", request.getPhone())));
        } else {
            User user = new User();
            if(request.getUin().length() != LENGTH_IIN) {
                throw new InvalidCredentialsException(new ResponseMessage("iin is not correct, please try again!"));
            } else {
                user.setUin(request.getUin());
            }
            user.setFirstName(request.getFirstName());
            user.setMidName(request.getMidName());
            user.setLastName(request.getLastName());
            user.setPhone(request.getPhone());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            log.info("Saving new user with uin '{}' to the database", request.getUin());
            return userRepository.save(user);
        }
    }

    public UUID getUserId() {
        try {
            return UUID.fromString(String.valueOf(servletContext.getAttribute("userId")));
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String updatePassword(String newPassword) {
        User user = userRepository.findById(getUserId()).orElseThrow(() -> new RuntimeException("user not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return "Password has been successfully updated";
    }

    @Override
    public UserRegisterResponse get(UUID id) {
        return userRepository.findById(id).map(userMapper).orElseThrow(() -> new RuntimeException("user not found"));
    }
}
