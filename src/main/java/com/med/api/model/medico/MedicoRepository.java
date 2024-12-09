package com.med.api.model.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findByActivoTrue(Pageable pageable);

    @Query(value = """
        SELECT * FROM medico m
        WHERE m.especialidad = :especialidad
        AND m.activo = TRUE
        AND m.id NOT IN (
            SELECT c.medico_id FROM consulta c
            WHERE c.fecha = :fecha
        )
        ORDER BY RAND()
        LIMIT 1
    """, nativeQuery = true)
    Medico elegirMedicoAleatorio(@Param("especialidad") String especialidad, @Param("fecha") LocalDateTime fecha);


    @Query("""
                select m.activo from Medico m
                where m.id = :id
            """)
    boolean findActivoById(Long id);
}
