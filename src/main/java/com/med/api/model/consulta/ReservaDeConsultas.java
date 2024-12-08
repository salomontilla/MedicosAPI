package com.med.api.model.consulta;

import com.med.api.model.medico.Medico;
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

        if(!pacienteRepository.existsById(datos.idPaciente())){
            throw new RuntimeException("Paciente no encontrado");
        }
        if(!medicoRepository.existsById(datos.idMedico())){
            throw new RuntimeException("Medico no encontrado");
        }
        
        var medico = elegirMedico(datos);
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        Consulta consulta = new Consulta(null, medico, paciente, datos.fecha());
        consultaRepository.save(consulta);
    }

    private Medico elegirMedico(DatosReservaConsultaDTO datos) {

        if(datos.idMedico() != null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if(datos.especialidad() == null){
            throw new RuntimeException("Es necesario especificar una especialidad");
        }

        return medicoRepository.elegirMedicoAleatorio(datos.especialidad(), datos.fecha());
    }
}
