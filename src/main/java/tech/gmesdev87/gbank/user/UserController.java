package tech.gmesdev87.gbank.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tech.gmesdev87.gbank.user.dto.UserRegisterDTO;

@RestController 
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public User create(
        @Valid @RequestBody() UserRegisterDTO request
    ){
        return userService.create(request);
    }
    
}
