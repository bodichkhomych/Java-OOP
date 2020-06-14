package com.lab3.demo.service;

import com.lab3.demo.converter.CardConverter;
import com.lab3.demo.dto.CardDTO;
import com.lab3.demo.entity.Card;
import com.lab3.demo.exception.CardNumberNotNullException;
import com.lab3.demo.service.data.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CardControllerService {
    private CardService cardService;

    private CardConverter cardConverter;

    public CardDTO addCard(CardDTO cardDTO) {
        Card card = this.cardService.addCard(cardConverter.convertToEntity(cardDTO));
        return cardConverter.convertToDTO(cardService.addCard(card));
    }

    public List<CardDTO> findByUser(String email) {
        return cardConverter.convertToListDTO(cardService.findByUser(email));
    }

    public CardDTO blockCard(String cardNumber) {
        if (cardNumber == null) {
            throw new CardNumberNotNullException();
        }

        return cardConverter.convertToDTO(cardService.blockCard(cardNumber));
    }

    public CardDTO unblockCard(String cardNumber) {
        if (cardNumber == null) {
            throw new CardNumberNotNullException();
        }

        return cardConverter.convertToDTO(cardService.unblockCard(cardNumber));
    }
}
