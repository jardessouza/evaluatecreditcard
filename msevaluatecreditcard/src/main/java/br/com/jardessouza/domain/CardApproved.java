package br.com.jardessouza.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardApproved {
    private String card;
    private String flag;
    private BigDecimal approvedLimit;
}
