package fr.dawan.demobank.models;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(fluent = true)
public class Account implements Serializable {
    private double balance;
    private String code;
    private List<String> history;
}
