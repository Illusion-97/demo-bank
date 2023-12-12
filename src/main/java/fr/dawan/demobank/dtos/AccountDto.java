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
    private String maskedCode;
    private int historyCount;

    public void setMaskedCode(String maskedCode) {
        this.maskedCode = maskedCode == null ? "Code non généré" : "******";
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "balance=" + balance +
                ", maskedCode='" + maskedCode + '\'' +
                ", historyCount=" + historyCount +
                '}';
    }
}
