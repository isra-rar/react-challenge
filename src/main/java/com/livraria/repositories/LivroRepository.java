package com.livraria.repositories;

import com.livraria.entities.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Livro p")
    Page<Livro> findByLivro(Pageable pageRequest);

    @Transactional(readOnly = true)
    @Query("SELECT l FROM Livro l WHERE l.autor.id = ?1")
    List<Livro> findLivrosByAutor(Long id);

    @Transactional(readOnly = true)
    @Query("SELECT l FROM Livro l WHERE l.titulo = ?1")
    Livro existsLivroByTitulo(String titulo);



}
