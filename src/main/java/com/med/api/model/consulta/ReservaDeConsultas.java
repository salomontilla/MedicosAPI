package com.med.api.model.consulta;

import com.med.api.model.consulta.validaciones.reserva.ValidadorDeConsultas;
import com.med.api.model.consulta.validaciones.cancelamiento.ValidadorDeCancelamiento;
import com.med.api.model.medico.Medico;
import com.med.api.model.medico.MedicoRepository;
import com.med.api.model.paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorDeConsultas> validadores;

    @Autowired
    private List<ValidadorDeCancelamiento> validadoresDeCancelamientos;

    //este metodo se encarga de reservar una consulta
    public DatosDetalleConsultaDTO reservar(@Valid DatosReservaConsultaDTO datos){

        if(!pacienteRepository.existsById(datos.idPaciente())){//validamos que el paciente exista
            throw new RuntimeException("Paciente no encontrado");
        }


        validadores.forEach(validador -> validador.validar(datos));//validamos los dato de la consulta usando los validadores
        
        var medico = elegirMedico(datos);//elegimos un medico para la consulta

        if (medico == null) {
            throw new RuntimeException("No hay médicos disponibles");
        }

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();//obtenemos el paciente

        Consulta consulta = new Consulta(null, medico, paciente, datos.fecha(), null);
        consultaRepository.save(consulta);
        return new DatosDetalleConsultaDTO(consulta);
    }

    public Medico elegirMedico(DatosReservaConsultaDTO datos) {

        if(datos.idMedico() != null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if(datos.especialidad() == null){
            throw new RuntimeException("Es necesario especificar una especialidad");
        }

        return medicoRepository.elegirMedicoAleatorio(datos.especialidad().toString(), datos.fecha());
    }

    public void cancelar(DatosCancelamientoConsultaDTO datos){
        if(!consultaRepository.existsById(datos.id())){
            throw new RuntimeException("Consulta no encontrada");
        }
        var consulta = consultaRepository.getReferenceById(datos.id());
        consulta.cancelar(datos.motivo());
    }

}
