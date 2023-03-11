package br.com.jardessouza.eurekaserver.infra.repository;

import br.com.jardessouza.eurekaserver.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCpf(String cpf);
}
