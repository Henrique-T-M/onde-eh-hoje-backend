package com.maybenot.oeh.controllers;

import com.maybenot.oeh.entities.User;
import com.maybenot.oeh.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Get user by ID")
    @GetMapping(path = "user/{userId}")
    public User getUser(@PathVariable("userId") String userId){
        return userService.getUser(userId);
    }

    @ApiOperation(value = "Get all users")
    @GetMapping(path = "users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @ApiOperation(value = "Register new user")
    @PostMapping(path = "registerUser")
    public void registerUser(@RequestBody User user){
        userService.registerUser(user);
    }

    @ApiOperation(value = "Update user")
    @PutMapping(path = "updateUser/{userId}")
    public void updateUser(@PathVariable("userId") String userId, @RequestParam String name, @RequestParam String email){
        userService.updateUser(userId, name, email);
    }

    @ApiOperation(value = "Delete user by ID")
    @DeleteMapping(path = "deleteUser/{userId}")
    public void deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
    }

}
