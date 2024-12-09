package com.med.api.model.consulta.validaciones.reserva;

import com.med.api.model.consulta.DatosReservaConsultaDTO;
import com.med.api.model.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoActivo implements ValidadorDeConsultas {
    @Autowired
    private MedicoRepository medicoRepository;
    public void validar(DatosReservaConsultaDTO datos) {
        if (datos.idMedico() == null) {
            return;
        }
        var medicoActivo = medicoRepository.findActivoById(datos.idMedico());
        if (!medicoActivo) {
            throw new RuntimeException("El médico seleccionado no está activo");
        }
    }
}
