package com.med.api.model.consulta.validaciones;

import com.med.api.model.consulta.DatosReservaConsultaDTO;
import com.med.api.model.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorMedicoActivo {

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
