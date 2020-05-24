package entities;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String firstName;
    private String secondName;
    private String username;
    private String password;
    private List<Card> cards;

    public User(String username, List<Card> cards) {
        this.username = username;
        this.cards = cards;
    }

    public User(String firstName, String secondName, String username, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.username = username;
        this.password = password;
    }

}
