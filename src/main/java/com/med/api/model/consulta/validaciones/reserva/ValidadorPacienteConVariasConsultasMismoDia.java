package com.med.api.model.consulta.validaciones.reserva;

import com.med.api.model.consulta.ConsultaRepository;
import com.med.api.model.consulta.DatosReservaConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteConVariasConsultasMismoDia implements ValidadorDeConsultas {
    @Autowired
    ConsultaRepository consultaRepository;

    public void validar(DatosReservaConsultaDTO datos) {
        var fechaConsulta = datos.fecha();
        var primerHorario = fechaConsulta.withHour(7);
        var ultimoHorario = fechaConsulta.withHour(18);


        var pacienteTieneOtraConsulta = consultaRepository.existsByPacienteIdAndFechaBetween(datos.idPaciente(), primerHorario, ultimoHorario);
        if (pacienteTieneOtraConsulta) {
            throw new RuntimeException("El paciente seleccionado ya tiene una consulta agendada para el mismo día");
        }
    }
}
