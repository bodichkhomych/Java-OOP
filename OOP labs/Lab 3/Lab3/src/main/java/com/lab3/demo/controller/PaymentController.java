package com.lab3.demo.controller;

import com.lab3.demo.dto.CardDTO;
import com.lab3.demo.dto.PaymentDTO;
import com.lab3.demo.service.PaymentControllerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
public class PaymentController {
    private PaymentControllerService paymentService;

    @PostMapping(value = "/payment")
    public CardDTO addCard(@RequestBody PaymentDTO paymentDTO) {
        return paymentService.addPayment(paymentDTO);
    }
}
