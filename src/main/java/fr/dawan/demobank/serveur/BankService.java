package fr.dawan.demobank.serveur;

import fr.dawan.demobank.dtos.AccountDto;
import fr.dawan.demobank.mappers.AccountMapper;
import fr.dawan.demobank.repository.AccountRepository;

import java.util.List;

public class BankService {
    private AccountRepository repository;
    private AccountMapper mapper;

    public List<AccountDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
