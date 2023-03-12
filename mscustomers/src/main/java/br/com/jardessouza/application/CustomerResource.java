package br.com.jardessouza.application;

import br.com.jardessouza.application.representation.CustomerSaveRequest;
import br.com.jardessouza.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerResource {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity save(@RequestBody CustomerSaveRequest request){
        var customer = request.toModel();
        this.customerService.save(customer);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(customer.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();

    }

    @GetMapping(params = "cpf")
    public ResponseEntity customerData(@RequestParam("cpf") String cpf){
        var customer = this.customerService.getByCPF(cpf);
        if (customer.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }


}
