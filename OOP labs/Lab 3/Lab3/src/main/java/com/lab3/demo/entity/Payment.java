package com.lab3.demo.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @ManyToOne
    private Card from;

    @ManyToOne
    private Card to;

    @Column(name = "amount")
    private int amount;
}
