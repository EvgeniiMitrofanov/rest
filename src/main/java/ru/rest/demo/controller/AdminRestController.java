package ru.rest.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.rest.demo.model.User;
import ru.rest.demo.service.UserService;

import java.util.List;

@RestController
public class AdminRestController {

    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/main")
    public ModelAndView getMainPage() {
        return new ModelAndView("main");
    }

    @GetMapping("api/admin")
    public ResponseEntity<List<User>> getInfoUsersList() {
        List<User> userList = userService.getAllUsers();
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("api/admin/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("api/admin")
    public ResponseEntity<User>  saveUserForm(@RequestBody User user) {
        if (userService.addUser(user)) {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(user, HttpStatus.CONFLICT);
    }

    @PutMapping("api/admin")
    public ResponseEntity<User> updateUserForm(@RequestBody User user) {
        if (userService.updateUser(user)) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @DeleteMapping("api/admin/{id}")
    public ResponseEntity<Long>  removeUserForm(@PathVariable long id) {
        if (userService.deleteUserById(id)) {
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
    }
}
