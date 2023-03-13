package br.com.jardessouza.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerCard {
    private String name;
    private String flag;
    private BigDecimal limitReleased;
}
