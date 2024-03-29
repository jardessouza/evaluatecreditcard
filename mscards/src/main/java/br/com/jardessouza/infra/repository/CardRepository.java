package br.com.jardessouza.infra.repository;

import br.com.jardessouza.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByfixedIncomeLessThanEqual(BigDecimal fixedIncomeBigDecimal);
}
