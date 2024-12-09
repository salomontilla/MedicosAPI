package com.med.api.model.consulta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedicoAndFecha(Long id, LocalDateTime fecha);

    boolean existsByPacienteAndFechaBetween(Long id, LocalDateTime primerHorario, LocalDateTime ultimoHorario);
}
