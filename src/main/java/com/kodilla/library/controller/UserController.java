package com.kodilla.library.controller;

import com.kodilla.library.domain.UserDto;
import com.kodilla.library.entity.User;
import com.kodilla.library.mapper.UserMapper;
import com.kodilla.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v2/user")
public class UserController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST,value = "addUser",consumes = APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UserDto userDto) {
        userService.save(userMapper.mapToUser(userDto));
        System.out.println("User is saved in DB");
    }

    @RequestMapping(method = RequestMethod.GET,value = "getUser")
    public UserDto getUser(@RequestParam Long userId) {
        System.out.println("Retrieving user from DB");
        return userService.getUser(userId);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "deleteUser")
    public void deleteUser(@RequestParam Long userId) {
        userService.deleteUser(userId);
        System.out.println("User is deleted in DB");
    }
}
