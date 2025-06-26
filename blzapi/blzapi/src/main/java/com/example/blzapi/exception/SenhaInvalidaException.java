package com.example.blzapi.exception;

public class SenhaInvalidaException extends RuntimeException {

    public SenhaInvalidaException(){

        super("senha Invalida");
    }
}
