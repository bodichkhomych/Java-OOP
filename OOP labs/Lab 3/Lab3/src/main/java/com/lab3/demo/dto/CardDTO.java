package com.lab3.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDTO {
    @NotNull(message = "Card number is required")
    private String cardNumber;

    @NotNull(message = "Balance is required")
    private int balance;

    @NotNull(message = "Block state is required")
    private boolean blocked;
}
