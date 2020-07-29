package com.livraria.controllers;

import com.livraria.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericController <T extends GenericService> {

    @Autowired
    private T service;

    public GenericController() {
    }

    public T getService() {
        return service;
    }


}
