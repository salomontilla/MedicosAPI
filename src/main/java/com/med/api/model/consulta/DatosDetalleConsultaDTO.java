package com.med.api.model.consulta;

import java.time.LocalDateTime;

public record DatosDetalleConsultaDTO(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime fecha) {
    public DatosDetalleConsultaDTO(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getFecha());
    }
}
