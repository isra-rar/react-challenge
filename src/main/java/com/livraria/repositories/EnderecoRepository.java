package com.livraria.repositories;

import com.livraria.entities.Cliente;
import com.livraria.entities.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Endereco p")
    Page<Endereco> findByEndereco(Pageable pageRequest);

}
