package br.com.jardessouza.eurekaserver.application.representation;

import br.com.jardessouza.eurekaserver.domain.Customer;
import lombok.Data;

@Data
public class CustomerSaveRequest {
    private String cpf;
    private String name;
    private Integer age;

    public Customer toModel(){
        return new Customer(cpf, name, age);
    }

}
