package com.lab3.demo.controller;

import com.lab3.demo.dto.UserDTO;
import com.lab3.demo.service.UserControllerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class UserController {
    private UserControllerService userService;

    @GetMapping(value = "/users")
    public List<UserDTO> findUsers() {
        return userService.findAll();
    }
}
