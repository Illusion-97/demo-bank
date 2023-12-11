package fr.dawan.demobank.mappers;

import fr.dawan.demobank.dtos.AccountDto;
import fr.dawan.demobank.models.Account;
import org.mapstruct.Mapper;

@Mapper
public interface AccountMapper {
    AccountDto toDto(Account model);
    Account toModel(AccountDto dto);
}
