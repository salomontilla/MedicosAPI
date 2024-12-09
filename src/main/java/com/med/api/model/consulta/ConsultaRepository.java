package com.med.api.model.consulta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedicoIdAndFecha(Long MedicoId, LocalDateTime fecha);

    boolean existsByPacienteIdAndFechaBetween(Long id, LocalDateTime primerHorario, LocalDateTime ultimoHorario);
}
