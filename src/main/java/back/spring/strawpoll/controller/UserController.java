package back.spring.strawpoll.controller;

import back.spring.strawpoll.entity.UserEntity;
import back.spring.strawpoll.service.UserService;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FieldDefaults(makeFinal = true)
@RestController
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping("/users/id={id}")
    public Optional<UserEntity> getUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public void addUser(@RequestBody UserEntity user) {
        userService.createUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/id={id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUserById(id);
    }
}
