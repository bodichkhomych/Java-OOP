package com.lab3.demo.service;

import com.lab3.demo.converter.CardConverter;
import com.lab3.demo.converter.PaymentConverter;
import com.lab3.demo.dto.CardDTO;
import com.lab3.demo.dto.PaymentDTO;
import com.lab3.demo.service.data.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentControllerService {
    private PaymentService paymentService;
    private CardConverter cardConverter;

    public CardDTO addPayment(PaymentDTO paymentDTO) {
        return cardConverter.convertToDTO(paymentService.addPayment(paymentDTO));
    }
}
