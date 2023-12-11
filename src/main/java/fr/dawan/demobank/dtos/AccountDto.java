package fr.dawan.demobank.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data // @Getter / @Setter / @ToString / @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private double balance;
    private String code;
    private List<String> history;
}
