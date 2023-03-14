package br.com.jardessouza.application.representation;

import br.com.jardessouza.domain.CardCustomer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsCustomerResponseDTO {
    private String name;
    private String flag;
    private BigDecimal limitReleased;

    public static CardsCustomerResponseDTO fromModel(CardCustomer model){
        return new CardsCustomerResponseDTO(
                model.getCard().getName(),
                model.getCard().getFlag().toString(),
                model.getLimitReleased()
        );
    }
}
