package com.med.api.model.consulta.validaciones;

import com.med.api.model.consulta.DatosReservaConsultaDTO;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorConsultaConAnticipacion {
    public void validar(DatosReservaConsultaDTO datos) {
        var fechaConsulta = datos.fecha();
        var fechaActual = LocalDateTime.now();
        var diferenciaHoras = Duration.between(fechaActual, fechaConsulta).toMinutes();

        if(diferenciaHoras < 30) {
            throw new RuntimeException("No se puede reservar una consulta con menos de 30 minutos de anticipación");
        }
    }
}
