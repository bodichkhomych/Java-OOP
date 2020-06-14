package com.lab3.demo.service.data;

import com.lab3.demo.entity.User;
import com.lab3.demo.exception.UserAlreadyExistsException;
import com.lab3.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RegistrationService {
    private UserRepository userRepository;

    @Transactional
    public User save(User currentUser) {
        Optional<User> oldUser = userRepository.finByEmail(currentUser.getEmail());
        oldUser.ifPresent(entity -> {
            throw new UserAlreadyExistsException("User already exist.");
        });
        return userRepository.save(currentUser);
    }
}
