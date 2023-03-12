package br.com.jardessouza.domain;

import br.com.jardessouza.enums.FlagCard;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private FlagCard flag;
    private BigDecimal fixedIncome;
    private BigDecimal basicLimit;

    public Card(String name, FlagCard flag, BigDecimal fixedIncome, BigDecimal basicLimit) {
        this.name = name;
        this.flag = flag;
        this.fixedIncome = fixedIncome;
        this.basicLimit = basicLimit;
    }
}
