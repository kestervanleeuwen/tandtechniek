package hu.tandtechniek.security.presentation;

import hu.tandtechniek.security.application.UserService;
import hu.tandtechniek.security.presentation.DTO.AuthDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void register(@Validated @RequestBody AuthDTO registration, HttpServletResponse response) {
        try{
            this.userService.register(
                    registration.username,
                    registration.password
            );
            response.setStatus(200);
        }catch (Exception ignored){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
