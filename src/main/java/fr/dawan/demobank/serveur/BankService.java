package fr.dawan.demobank.serveur;

import fr.dawan.demobank.dtos.AccountDto;
import fr.dawan.demobank.exceptions.AccountNotFoundException;
import fr.dawan.demobank.exceptions.CodeException;
import fr.dawan.demobank.exceptions.InsufficientFundsException;
import fr.dawan.demobank.mappers.AccountMapper;
import fr.dawan.demobank.models.Account;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BankService {

    private static final Map<String, Account> accounts = new HashMap<>();
    private static final AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

    public static void createAccount(String name) {
        accounts.put(name, new Account());
    }

    private static Account getAccount(String name) throws AccountNotFoundException {
        if (!accounts.containsKey(name)) throw new AccountNotFoundException(name);
        return accounts.get(name);
    }

    private static Account getAccount(String name, String code) throws AccountNotFoundException, CodeException {
        return getAccount(name).checkCode(code);
    }

    public static double deposit(String name, String code, double amount) throws AccountNotFoundException, CodeException {
        return getAccount(name,code).deposit(amount);
    }

    public static double withdraw(String name, String code, double amount) throws AccountNotFoundException, InsufficientFundsException, CodeException {
        return getAccount(name,code).withdraw(amount);
    }
    public static void transfer(String name, String code, String targetName, double amount) throws AccountNotFoundException, InsufficientFundsException, CodeException {
        getAccount(name,code).transfer(amount,getAccount(targetName));
    }

    public static String generateCode(String name) throws AccountNotFoundException {
        return getAccount(name).generateCode();
    }

    public static String getAccountData(String name) throws AccountNotFoundException {
        return Objects.toString(accountMapper.toDto(getAccount(name)),"Conversion Error");
    }
}
