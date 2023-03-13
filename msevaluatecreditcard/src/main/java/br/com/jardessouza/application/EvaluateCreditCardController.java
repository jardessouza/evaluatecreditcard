package br.com.jardessouza.application;

import br.com.jardessouza.domain.CustomerSituation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evaluatecreditcard")
@RequiredArgsConstructor
public class EvaluateCreditCardController {

    private final EvaluateCreditCardService evaluateCreditCardService;
    @GetMapping(value = "customer-situation" ,params = "cpf")
    public ResponseEntity<CustomerSituation> customerSituationConsultation(@RequestParam("cpf") String cpf){
        CustomerSituation customerSituation = this.evaluateCreditCardService.getCustomerSituation(cpf);
        return null;
    }
}
