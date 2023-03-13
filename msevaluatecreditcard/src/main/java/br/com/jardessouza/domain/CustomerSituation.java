package br.com.jardessouza.domain;

import lombok.Data;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Data
public class CustomerSituation {
    private CustomerData customer;
    private List<CustomerCard> cards;
}
