package br.com.jardessouza.application.representation;

import br.com.jardessouza.domain.Card;
import br.com.jardessouza.enums.FlagCard;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardSaveRequestDTO {
    private String name;
    private FlagCard flag;
    private BigDecimal fixedIncome;
    private BigDecimal basicLimit;

    public Card toModel(){
        return new Card(name, flag, fixedIncome, basicLimit);
    }
}
