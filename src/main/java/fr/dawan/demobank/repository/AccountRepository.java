package fr.dawan.demobank.repository;

import fr.dawan.demobank.models.Account;

import java.util.List;

public interface AccountRepository {
    List<Account> findAll();
    Account getById(long id);
}
