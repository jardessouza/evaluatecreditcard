package br.com.jardessouza.application;

import br.com.jardessouza.application.representation.CardSaveRequestDTO;
import br.com.jardessouza.application.representation.CardsCustomerResponseDTO;
import br.com.jardessouza.domain.Card;
import br.com.jardessouza.domain.CardCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardsResource {
    private final CardService cardService;
    private final CardCustomerService cardCustomerService;
    @PostMapping
    public ResponseEntity register(@RequestBody CardSaveRequestDTO request){
        var card = request.toModel();
        this.cardService.save(card);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardIncomeUntil(@RequestParam("income") Long income){
       var listCards = this.cardService.getCardsEqualLesserIncome(income);
       return ResponseEntity.ok(listCards);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardsCustomerResponseDTO>> getCardsByCustomer(@RequestParam("cpf") String cpf){
        List<CardCustomer> listCardCustomers = this.cardCustomerService.listCardsByCpf(cpf);

        List<CardsCustomerResponseDTO> resultList = listCardCustomers.stream()
                .map(CardsCustomerResponseDTO::fromModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resultList);
    }


}
