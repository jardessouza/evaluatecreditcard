package br.com.jardessouza.infra.client;

import br.com.jardessouza.domain.Card;
import br.com.jardessouza.domain.CustomerCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscards", path = "/cards")
public interface CardsResourceClient {
    @GetMapping(params = "cpf")
    ResponseEntity<List<CustomerCard>> getCardsByCustomer(@RequestParam("cpf") String cpf);

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardIncomeUntil(@RequestParam("income") Long income);
}
