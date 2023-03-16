package br.com.jardessouza.application;

import br.com.jardessouza.domain.Card;
import br.com.jardessouza.infra.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private  final CardRepository cardRepository;

    @Transactional
    public Card save(Card card){
        return this.cardRepository.save(card);
    }

    public List<Card> getCardsEqualLesserIncome(Long income){
        var fixedIncomeBigDecimal = BigDecimal.valueOf(income);
        return this.cardRepository.findByfixedIncomeLessThanEqual(fixedIncomeBigDecimal);
    }
}
