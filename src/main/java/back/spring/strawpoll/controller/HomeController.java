package back.spring.strawpoll.controller;

import back.spring.strawpoll.dto.UserRegisterDto;
import back.spring.strawpoll.service.UserService;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@FieldDefaults(makeFinal = true)
@RestController
public class HomeController {
    UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/")
    public String home() {
        return "/";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String showRegisterForm() {
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String registerUser(@ModelAttribute("UserEntity") UserRegisterDto userRegisterDto) {
        userService.createUser(userRegisterDto);
        return "success";
    }

}
