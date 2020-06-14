package com.lab3.demo.controller;

import com.lab3.demo.dto.CardDTO;
import com.lab3.demo.service.CardControllerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class CardController {
    private CardControllerService cardService;

    @GetMapping(value = "/cards/{email}")
    public List<CardDTO> findByUser(@PathVariable("email") String email) {
        return cardService.findByUser(email);
    }

    @PostMapping(value = "/card")
    public CardDTO addCard(@RequestBody CardDTO cardDTO) {
        return cardService.addCard(cardDTO);
    }

    @PatchMapping(value = "/block/{cardNumber}")
    public CardDTO blockCard(@PathVariable(value = "cardNumber") String cardNumber) {
        return cardService.blockCard(cardNumber);
    }

    @DeleteMapping(value = "/block/{cardNumber}")
    public CardDTO unblockCard(@PathVariable(value = "cardNumber") String cardNumber) {
        return cardService.unblockCard(cardNumber);
    }
}
