package id.restful.springboot_contact_management.service;

import id.restful.springboot_contact_management.bcrypt.Bcrypt;
import id.restful.springboot_contact_management.dto.request.UserLoginReqeust;
import id.restful.springboot_contact_management.dto.response.LoginResponse;
import id.restful.springboot_contact_management.entity.User;
import id.restful.springboot_contact_management.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public LoginResponse login(UserLoginReqeust reqeust) {
        User user = userRepository.findById(reqeust.getUsername()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED)
        );
        if (!Bcrypt.checkpw(reqeust.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        user.setToken(UUID.randomUUID().toString());
        user.setTokenExpiredAt(next30Days());
        userRepository.save(user);
        return LoginResponse.builder()
                .username(user.getUsername())
                .token(user.getToken())
                .tokenExpiredAt(user.getTokenExpiredAt())
                .build();
    }

    private Long next30Days() {
        return System.currentTimeMillis() + (1000 * 60 * 24 * 30);
    }
}
