package id.restful.springboot_contact_management.controller;

import id.restful.springboot_contact_management.dto.request.UserLoginReqeust;
import id.restful.springboot_contact_management.dto.response.LoginResponse;
import id.restful.springboot_contact_management.dto.response.WebResponse;
import id.restful.springboot_contact_management.entity.User;
import id.restful.springboot_contact_management.repository.UserRepository;
import id.restful.springboot_contact_management.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(
            path = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Object> login(@RequestBody UserLoginReqeust reqeust) {
        LoginResponse loginResponse = authService.login(reqeust);
        return WebResponse.builder().data(loginResponse).build();
    }
}
