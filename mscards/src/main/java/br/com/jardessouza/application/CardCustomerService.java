package br.com.jardessouza.application;

import br.com.jardessouza.domain.CardCustomer;
import br.com.jardessouza.repository.CardCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardCustomerService {
    private final CardCustomerRepository cardCustomerRepository;

    public List<CardCustomer> listCardsByCpf(String cpf){
        return this.cardCustomerRepository.findByCpf(cpf);
    }
}
