package com.lab3.demo.converter;

import com.lab3.demo.dto.UserDTO;
import com.lab3.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {
    public UserDTO convertToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }

    public User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        return user;
    }

    public List<UserDTO> convertToListDto(List<User> users) {
        return users.stream().
                map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
