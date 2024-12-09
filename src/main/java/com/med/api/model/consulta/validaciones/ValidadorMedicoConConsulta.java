package com.med.api.model.consulta.validaciones;

import com.med.api.model.consulta.ConsultaRepository;
import com.med.api.model.consulta.DatosReservaConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConConsulta implements ValidadorDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DatosReservaConsultaDTO datos) {

        var medicoTieneOtraConsulta = consultaRepository.existsByMedicoIdAndFecha(datos.idMedico(), datos.fecha());
        if(medicoTieneOtraConsulta) {
            throw new RuntimeException("El médico seleccionado ya tiene una consulta agendada para la fecha y hora seleccionada");
        }
    }
}
