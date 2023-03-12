package br.com.jardessouza.service;

import br.com.jardessouza.domain.Customer;
import br.com.jardessouza.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Transactional
    public Customer save(Customer customer){
        return this.customerRepository.save(customer);
    }

    public Optional<Customer> getByCPF(String cpf){
        return this.customerRepository.findByCpf(cpf);
    }
}
