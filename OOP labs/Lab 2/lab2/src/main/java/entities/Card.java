package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Card {
    private String name;
    private boolean blocked;
    private Account account;

    public int getId() {
        return account.getId();
    }

    public int getBalance() {
        return account.getBalance();
    }

    public List<Payment> getPayments() {
        return account.getPayments();
    }

}
