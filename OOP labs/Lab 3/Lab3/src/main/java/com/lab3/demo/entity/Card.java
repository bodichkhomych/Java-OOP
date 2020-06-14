package com.lab3.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Data
@Table(name = "cards")
public class Card {
    @Id
    @Column(name = "card_number")
    private String id;

    @ManyToOne
    private User user;

    @OneToOne
    private Account account;
}
