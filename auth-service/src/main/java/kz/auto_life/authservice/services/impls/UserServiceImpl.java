package kz.auto_life.authservice.services.impls;

import kz.auto_life.authservice.models.User;
import kz.auto_life.authservice.exceptions.UinExistsException;
import kz.auto_life.authservice.payload.UserRegisterRequest;
import kz.auto_life.authservice.repositories.UserRepository;
import kz.auto_life.authservice.services.UserService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private boolean uinExist(String uin) {
        return userRepository.findByUin(uin) != null;
    }

    @Override
    @Transactional
    public User register(UserRegisterRequest request) {
        if (uinExist(request.getUin())) {
            throw new UinExistsException(request.getUin());
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
    public UserDetails loadUserByUsername(String uin) throws UsernameNotFoundException {
        User user = userRepository.findByUin(uin);
        if (user == null) {
            log.info("This uin: {} does not exist", uin);
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("uin {} found in the database", uin);
        }
        return new org.springframework.security.core.userdetails.User(user.getUin(), user.getPassword(), new ArrayList<>());
    }
}
