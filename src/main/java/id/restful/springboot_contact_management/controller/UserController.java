package id.restful.springboot_contact_management.controller;

import id.restful.springboot_contact_management.dto.request.UserRegisterRequest;
import id.restful.springboot_contact_management.dto.response.UserResponse;
import id.restful.springboot_contact_management.dto.response.WebResponse;
import id.restful.springboot_contact_management.entity.User;
import id.restful.springboot_contact_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;

@RestController
@RequestMapping(path = "/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(
            path = "/users",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Object> register(@RequestBody UserRegisterRequest request) {
        User register = userService.register(request);
        return WebResponse.builder().data(register.getName() + " success registered ").build();
    }

    @GetMapping(
            path = "/users/current",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Object> get(@RequestHeader(name = "X-TOKEN-API") String token) {
        UserResponse userResponse = userService.getUser(token);
        return WebResponse.builder().data(userResponse).build();
    }

    @PatchMapping(
            path = "/users/current",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Object> update(@RequestHeader(name = "X-TOKEN-API") String token, @RequestBody User request) {
        UserResponse userResponse = userService.updateUser(token, request);
        return WebResponse.builder()
                .data(userResponse)
                .build();
    }

}
