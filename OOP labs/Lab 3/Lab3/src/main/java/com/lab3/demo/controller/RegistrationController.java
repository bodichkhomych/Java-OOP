package com.lab3.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin
@AllArgsConstructor
public class RegistrationController {

    //TODO:
//    @PostMapping(value = "/registration")
//    public ResponseEntity registration(@Valid @RequestBody UseDTO userDTO) {
//        //TODO:
//    }
}
