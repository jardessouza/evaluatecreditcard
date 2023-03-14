package br.com.jardessouza.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReturnCustomerEvaluation {
    private List<CardApproved> cards;
}
