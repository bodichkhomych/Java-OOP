package com.lab3.demo.converter;

import com.lab3.demo.dto.CardDTO;
import com.lab3.demo.entity.Account;
import com.lab3.demo.entity.Card;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CardConverter {
    public CardDTO convertToDTO(Card card) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setCardNumber(card.getId());
        cardDTO.setBalance(card.getAccount().getBalance());
        cardDTO.setBlocked(card.getAccount().isBlocked());

        return cardDTO;
    }

    public Card convertToEntity(CardDTO cardDTO) {
        Card card = new Card();
        card.setId(cardDTO.getCardNumber());

        return card;
    }

    public List<CardDTO> convertToListDTO(List<Card> cards) {
        return cards.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
