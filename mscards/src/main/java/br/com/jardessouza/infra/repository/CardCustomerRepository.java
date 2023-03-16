package br.com.jardessouza.infra.repository;

import br.com.jardessouza.domain.CardCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardCustomerRepository extends JpaRepository<CardCustomer, Long> {
    List<CardCustomer> findByCpf(String cpf);
}
