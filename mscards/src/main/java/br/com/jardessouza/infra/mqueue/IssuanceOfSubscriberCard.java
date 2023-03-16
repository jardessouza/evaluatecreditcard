package br.com.jardessouza.infra.mqueue;

import br.com.jardessouza.domain.CardCustomer;
import br.com.jardessouza.domain.CardIssueRequestData;
import br.com.jardessouza.infra.repository.CardCustomerRepository;
import br.com.jardessouza.infra.repository.CardRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IssuanceOfSubscriberCard {
    private final CardRepository cardRepository;
    private final CardCustomerRepository cardCustomerRepository;

    @RabbitListener(queues = "${mq.queues.issuance-cards}")
    public void receiveIssueRequest(@Payload String payload){
        try{
            var mapper = new ObjectMapper();
            var data = mapper.readValue(payload, CardIssueRequestData.class);
            var card = this.cardRepository.findById(data.getIdCard()).orElseThrow();

            CardCustomer cardCustomer = new CardCustomer();
            cardCustomer.setCard(card);
            cardCustomer.setCpf(data.getCpf());
            cardCustomer.setLimitReleased(data.getLimitReleased());

            this.cardCustomerRepository.save(cardCustomer);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
