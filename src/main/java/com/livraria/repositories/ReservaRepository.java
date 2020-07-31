package com.livraria.repositories;

import com.livraria.entities.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Reserva p")
    Page<Reserva> findByReserva(Pageable pageRequest);

}
