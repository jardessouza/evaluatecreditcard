package br.com.jardessouza.infra.client;

import br.com.jardessouza.domain.CustomerData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mscustomers", path = "/customer")
public interface CustomerResourceClient {
    @GetMapping(params = "cpf")
    ResponseEntity<CustomerData> customerData(@RequestParam("cpf") String cpf);

}
