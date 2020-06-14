package com.lab3.demo.service;

import com.lab3.demo.dto.UserDTO;
import com.lab3.demo.service.data.UserService;
import com.lab3.demo.converter.UserConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserControllerService {
    private UserService userService;
    private UserConverter userConverter;

    public List<UserDTO> findAll() {
        return userConverter.convertToListDto(userService.findAll());
    }
}
