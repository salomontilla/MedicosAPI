package com.med.api.model.consulta.validaciones;

import com.med.api.model.consulta.DatosReservaConsultaDTO;
import com.med.api.model.paciente.PacienteRepository;

public class ValidadorPacienteActivo {
    private PacienteRepository pacienteRepository;

    public void validar(DatosReservaConsultaDTO datos) {

        var pacienteActivo = pacienteRepository.findActivoById(datos.idPaciente());
        if (!pacienteActivo) {
            throw new RuntimeException("El paciente seleccionado no está activo");
        }
    }
}
