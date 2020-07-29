package com.livraria.repositories;

import com.livraria.entities.Autor;
import com.livraria.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Autor p")
    Page<Autor> findByAutor(Pageable pageRequest);

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Autor p WHERE p.nome = ?1")
    Autor findAutorByNome(String nome);

}

