package com.med.api.model.consulta.validaciones;

import com.med.api.model.consulta.ConsultaRepository;
import com.med.api.model.consulta.DatosReservaConsultaDTO;

public class ValidadorMedicoConConsulta {
    private ConsultaRepository consultaRepository;
    public void validar(DatosReservaConsultaDTO datos) {

        var medicoTieneOtraConsulta = consultaRepository.existsByMedicoAndFecha(datos.idMedico(), datos.fecha());
        if(medicoTieneOtraConsulta) {
            throw new RuntimeException("El médico seleccionado ya tiene una consulta agendada para la fecha y hora seleccionada");
        }
    }
}
