package bg.softuni.lection2.demo.web;

import bg.softuni.lection2.demo.model.service.UserLoginServiceModel;
import bg.softuni.lection2.demo.security.CurrentUser;
import bg.softuni.lection2.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {


    private UserService userService;

    public LoginController( UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String showLogin() {
        return "auth-login";
    }

    @PostMapping("/users/login")
    public String login(UserLoginServiceModel model) {

        if (userService.authenticate(model.getUsername(), model.getPassword())) {
            userService.loginUser(model.getUsername());
            return "redirect:/";
        } else {
            return "redirect:/users/login";
        }
    }
}
