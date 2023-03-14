package br.com.jardessouza.application;

import br.com.jardessouza.application.ex.CustomerDataNotFoundException;
import br.com.jardessouza.application.ex.ErrorCommunicationMicroservicesException;
import br.com.jardessouza.domain.*;
import br.com.jardessouza.infra.client.CardsResourceClient;
import br.com.jardessouza.infra.client.CustomerResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EvaluateCreditCardService {

    private final CustomerResourceClient customerResourceClient;
    private final CardsResourceClient cardsResourceClient;

    public CustomerSituation getCustomerSituation(String cpf) throws CustomerDataNotFoundException, ErrorCommunicationMicroservicesException {
        try {
            ResponseEntity<CustomerData> customerDataResponse = this.customerResourceClient.customerData(cpf);
            ResponseEntity<List<CustomerCard>> cardsResponse = this.cardsResourceClient.getCardsByCustomer(cpf);

            return CustomerSituation.builder()
                    .customer(customerDataResponse.getBody())
                    .cards(cardsResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new CustomerDataNotFoundException();
            }
            throw new ErrorCommunicationMicroservicesException(e.getMessage(), status);
        }
    }

    public ReturnCustomerEvaluation carryOutEvaluation(String cpf, Long income)
            throws CustomerDataNotFoundException, ErrorCommunicationMicroservicesException {

        try {
            var customerDataResponse = this.customerResourceClient.customerData(cpf);
            var cardsResponse = this.cardsResourceClient.getCardIncomeUntil(income);

            List<Card> cards = cardsResponse.getBody();

            var listOfApprovedCards = cards.stream().map(card -> {

                CustomerData customerData = customerDataResponse.getBody();

                BigDecimal basicLimit = card.getBasicLimit();
                BigDecimal ageBD = BigDecimal.valueOf(customerData.getAge());
                var factor = ageBD.divide(BigDecimal.valueOf(10));
                BigDecimal limitApproved = factor.multiply(basicLimit);

                CardApproved approved = new CardApproved();
                approved.setCard(card.getName());
                approved.setFlag(card.getFlag());
                approved.setApprovedLimit(limitApproved);

                return approved;
            }).collect(Collectors.toList());

            return new ReturnCustomerEvaluation(listOfApprovedCards);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new CustomerDataNotFoundException();
            }
            throw new ErrorCommunicationMicroservicesException(e.getMessage(), status);
        }
    }
}
