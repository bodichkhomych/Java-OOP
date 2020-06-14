package com.lab3.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Payload<T> {
    private String event;
    private T objectToSend;
}
