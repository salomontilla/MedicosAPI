package com.med.api.model.consulta.validaciones.reserva;

import com.med.api.model.consulta.DatosReservaConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorConsultaConAnticipacion implements ValidadorDeConsultas {
    public void validar(DatosReservaConsultaDTO datos) {
        var fechaConsulta = datos.fecha();
        var fechaActual = LocalDateTime.now();
        var diferenciaHoras = Duration.between(fechaActual, fechaConsulta).toMinutes();

        if(diferenciaHoras < 30) {
            throw new RuntimeException("No se puede reservar una consulta con menos de 30 minutos de anticipación");
        }
    }
}
