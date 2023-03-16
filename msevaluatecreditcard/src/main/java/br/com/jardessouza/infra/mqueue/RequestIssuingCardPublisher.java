package br.com.jardessouza.infra.mqueue;

import br.com.jardessouza.domain.CardIssueRequestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestIssuingCardPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final Queue cardIssueQueue;

    public void requestCard(CardIssueRequestData data) throws JsonProcessingException {
        var json = convertIntoJason(data);
        rabbitTemplate.convertAndSend(cardIssueQueue.getName(), json);

    }
    private String convertIntoJason(CardIssueRequestData data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        var json = mapper.writeValueAsString(data);

        return json;
    }
}
