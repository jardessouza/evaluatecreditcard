package br.com.jardessouza.application;

import br.com.jardessouza.application.ex.CustomerDataNotFoundException;
import br.com.jardessouza.application.ex.ErrorCommunicationMicroservicesException;
import br.com.jardessouza.application.ex.ErrorRequestCardIssueException;
import br.com.jardessouza.domain.CardIssueRequestData;
import br.com.jardessouza.domain.CardRequestProtocol;
import br.com.jardessouza.domain.CustomerSituation;
import br.com.jardessouza.domain.EvaluationData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluatecreditcard")
@RequiredArgsConstructor
public class EvaluateCreditCardController {

    private final EvaluateCreditCardService evaluateCreditCardService;

    @GetMapping(value = "customer-situation", params = "cpf")
    public ResponseEntity customerSituationConsultation(@RequestParam("cpf") String cpf) {

        try {
            var customerSituation = this.evaluateCreditCardService.getCustomerSituation(cpf);
            return ResponseEntity.ok(customerSituation);
        } catch (CustomerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorCommunicationMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity carryOutEvaluation(@RequestBody EvaluationData data){
        try {
            var returncustomerevaluation = this.evaluateCreditCardService
                    .carryOutEvaluation(data.getCpf(), data.getIncome());

            return ResponseEntity.ok(returncustomerevaluation);
        } catch (CustomerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorCommunicationMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
    @PostMapping("request-card")
    public ResponseEntity requestCard(@RequestBody CardIssueRequestData data){
        try {
            CardRequestProtocol cardRequestProtocol = this.evaluateCreditCardService.requestCardIssue(data);
            return ResponseEntity.ok(cardRequestProtocol);
        } catch (ErrorRequestCardIssueException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
