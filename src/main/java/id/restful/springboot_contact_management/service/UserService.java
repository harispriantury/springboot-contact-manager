package id.restful.springboot_contact_management.service;

import id.restful.springboot_contact_management.bcrypt.Bcrypt;
import id.restful.springboot_contact_management.dto.request.UserLoginReqeust;
import id.restful.springboot_contact_management.dto.request.UserRegisterRequest;
import id.restful.springboot_contact_management.dto.response.UserResponse;
import id.restful.springboot_contact_management.entity.User;
import id.restful.springboot_contact_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public User register(UserRegisterRequest request) {
        System.out.println("sebelum validate");
        validationService.validate(request);

        if (userRepository.existsById(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "username already register");
        }

        User user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(Bcrypt.hashpw(request.getPassword(), Bcrypt.gensalt()));

        User save = userRepository.save(user);
        return save;
    }

    @Transactional
    public UserResponse getUser(String token) {
        User user = userRepository.findFirstByToken(token).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "unauthorized"));
        if (user.getTokenExpiredAt() < System.currentTimeMillis()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED , "token expired please relogin");
        }
        return UserResponse.builder()
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }

    @Transactional
    public UserResponse updateUser(String token, User request) {
        User user = userRepository.findFirstByToken(token).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "gagal update unauthorized"));

        if (Objects.nonNull(request.getName())) {
            user.setName(request.getName());
        }
        if (Objects.nonNull(request.getPassword())) {
            user.setPassword(Bcrypt.hashpw(request.getPassword(), Bcrypt.gensalt()));
        }

        userRepository.save(user);

        return UserResponse.builder()
                .name(user.getName())
                .build();

    }


}
