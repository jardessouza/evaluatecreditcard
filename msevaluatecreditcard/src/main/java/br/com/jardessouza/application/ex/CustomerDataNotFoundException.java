package br.com.jardessouza.application.ex;

public class CustomerDataNotFoundException extends Exception {
    public CustomerDataNotFoundException() {
        super("Customer data not found for the informed cpf.");
    }
}
