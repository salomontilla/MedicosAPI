package com.med.api.model.consulta.validaciones;

import com.med.api.model.consulta.DatosReservaConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
@Component
public class ValidadorFueraHorarioConsulta implements ValidadorDeConsultas {
    public void validar(DatosReservaConsultaDTO datos) {
        var fechaConsulta = datos.fecha();
        var isDomingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var isDentroHorarioApertura = fechaConsulta.getHour() < 7;
        var isDentroHorarioCierre = fechaConsulta.getHour() > 18;

        if(isDomingo || isDentroHorarioApertura || isDentroHorarioCierre) {
            throw new RuntimeException("No se puede reservar una consulta fuera del horario de atención");
        }

    }
}
