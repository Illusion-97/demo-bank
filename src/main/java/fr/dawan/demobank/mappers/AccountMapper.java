package fr.dawan.demobank.mappers;

import fr.dawan.demobank.dtos.AccountDto;
import fr.dawan.demobank.models.Account;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface AccountMapper {
    @Mapping(target = "maskedCode", source = "code")
    @Mapping(target = "historyCount", source = "history", qualifiedByName = "count")
    AccountDto toDto(Account entity);
    @InheritInverseConfiguration
    @Mapping(source = "historyCount", target ="history", ignore = true)
    Account toEntity(AccountDto dto);

    @Named("count")
    static int getHistoryCount(List<String> history) {
        return history.size();
    }
}
