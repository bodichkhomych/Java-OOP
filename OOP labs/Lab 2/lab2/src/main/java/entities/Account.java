package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Account {

    private int id;
    private int balance;
    private List<Payment> payments;
}
