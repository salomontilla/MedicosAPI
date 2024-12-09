package com.med.api.model.consulta.validaciones.cancelamiento;

import com.med.api.model.consulta.ConsultaRepository;
import com.med.api.model.consulta.DatosCancelamientoConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorPorHoraAnticipacion implements ValidadorDeCancelamiento {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void cancelar(DatosCancelamientoConsultaDTO datos) {
        var consulta = consultaRepository.getReferenceById(datos.id());
        var ahora = LocalDateTime.now();
        var diferenciaEnHoras = Duration.between(ahora, consulta.getFecha()).toHours();

        if (diferenciaEnHoras < 24) {
            throw new IllegalArgumentException("No se puede cancelar la consulta con menos de 24 horas de anticipación");
        }
    }
}
