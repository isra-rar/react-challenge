package com.livraria.services.excepctions;

public class DataIntegrityException extends RuntimeException {

    public DataIntegrityException(String s) {
        super(s);
    }

    public DataIntegrityException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
