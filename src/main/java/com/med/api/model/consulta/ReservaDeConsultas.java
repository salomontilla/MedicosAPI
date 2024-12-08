package com.med.api.model.consulta;

import com.med.api.model.medico.MedicoRepository;
import com.med.api.model.paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaDeConsultas {

    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    public void reservar(@Valid DatosReservaConsultaDTO datos){
        var medico = medicoRepository.findById(datos.idMedico()).get();
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        Consulta consulta = new Consulta(null, medico, paciente, datos.fecha());
        consultaRepository.save(consulta);
    }
}
