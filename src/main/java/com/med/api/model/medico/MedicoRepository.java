package com.med.api.model.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findByActivoTrue(Pageable pageable);

    @Query("""
            SELECT m FROM Medico m
            WHERE m.especialidad = :especialidad
            AND m.activo = true
            AND m.id NOT IN (
                SELECT c.medico.id FROM consulta c
                WHERE c.fecha = :fecha
            )
            ORDER BY RAND()
            LIMIT 1
            """)
    Medico elegirMedicoAleatorio(Especialidad especialidad, LocalDateTime fecha);
}
