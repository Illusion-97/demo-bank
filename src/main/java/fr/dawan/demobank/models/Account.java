package fr.dawan.demobank.models;

import fr.dawan.demobank.exceptions.CodeException;
import fr.dawan.demobank.exceptions.InsufficientFundsException;
import fr.dawan.demobank.tools.RandomTool;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static fr.dawan.demobank.tools.DateTimeHelper.getNowString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account implements Serializable {
    private double balance;
    private String code;
    private List<String> history = new ArrayList<>();

    public double deposit(double amount) {
        balance += amount;
        addHistory("Deposit",amount);
        return balance;
    }
    public double withdraw(double amount) throws InsufficientFundsException {
        if (balance < amount) throw new InsufficientFundsException(balance,amount);
        addHistory("Withdraw",amount);
        return balance;
    }
    public void transfer(double amount, Account target) throws InsufficientFundsException {
        withdraw(amount);
        target.deposit(amount);
    }

    public String generateCode() {
        code = RandomTool.generateCode(4);
        return code;
    }

    public Account checkCode(String code) throws CodeException {
        if (this.code == null) throw new CodeException(CodeException.Type.NULL);
        if(!this.code.equals(code)) throw new CodeException(CodeException.Type.DIFFERENT);
        return this;
    }
    private void addHistory(String action, double amount) {
        history.add("%s - %s : %.2f".formatted(getNowString(),action,amount));
    }
}
