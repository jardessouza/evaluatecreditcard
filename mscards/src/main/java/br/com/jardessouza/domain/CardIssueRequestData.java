package br.com.jardessouza.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardIssueRequestData {
    private  Long idCard;
    private String cpf;
    private String address;
    private BigDecimal limitReleased;

}
