package com.livraria.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class GenericServiceImpl<T extends JpaRepository, M> {

    @Autowired
    private T repository;

    @Autowired
    private M mapper;

    public T getRepository() {
        return repository;
    }

    public M getModelMapper() {
        return mapper;
    }

}
