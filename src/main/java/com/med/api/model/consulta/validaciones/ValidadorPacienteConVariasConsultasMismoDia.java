package com.med.api.model.consulta.validaciones;

import com.med.api.model.consulta.ConsultaRepository;
import com.med.api.model.consulta.DatosReservaConsultaDTO;

public class ValidadorPacienteConVariasConsultasMismoDia {

    ConsultaRepository consultaRepository;

    public void validar(DatosReservaConsultaDTO datos) {
        var fechaConsulta = datos.fecha();
        var primerHorario = fechaConsulta.withHour(7);
        var ultimoHorario = fechaConsulta.withHour(18);


        var pacienteTieneOtraConsulta = consultaRepository.existsByPacienteAndFechaBetween(datos.idPaciente(), primerHorario, ultimoHorario);
        if (pacienteTieneOtraConsulta) {
            throw new RuntimeException("El paciente seleccionado ya tiene una consulta agendada para el mismo día");
        }
    }
}
