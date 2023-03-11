package br.com.jardessouza.eurekaserver.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String name;
    private Integer age;

    public Customer(String cpf, String name, Integer age) {
        this.cpf = cpf;
        this.name = name;
        this.age = age;
    }
}
