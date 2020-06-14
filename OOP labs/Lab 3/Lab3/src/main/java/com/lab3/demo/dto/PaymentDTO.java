package com.lab3.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDTO {
    @NotNull(message = "Amount is required")
    private int amount;

    @NotNull(message = "Sender card number is required")
    private String cardFrom;

    @NotNull(message = "Receiver card number is required")
    private String cardTo;
}