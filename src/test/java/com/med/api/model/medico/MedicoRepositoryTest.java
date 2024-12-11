package com.med.api.model.medico;

import com.med.api.model.consulta.Consulta;
import com.med.api.model.direccion.DatosDireccionDTO;
import com.med.api.model.paciente.DatosPacienteDTO;
import com.med.api.model.paciente.DatosRegistroPacienteDTO;
import com.med.api.model.paciente.Paciente;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {
    @Autowired
    private EntityManager em;

    @Autowired
    MedicoRepository medicoRepository;

    @Test @DisplayName("Debe retornar null si no hay medicos disponibles en la fecha especificada")
    void elegirMedicoAleatorioDisponibleEscenario1() {
        //given or arrange
        var proximoLunes = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0); // Se asume que el lunes a las 10:00 no hay medicos disponibles

        var medico= registrarMedico("medico1", "medico1@gmail.com", "111222333", Especialidad.CARDIOLOGIA);
        var paciente = registrarPaciente("paciente1", "paciente1@gmail.com", "111222333");
        registarConsulta(medico, paciente, proximoLunes);

        //when or act
        var medicoLibre = medicoRepository
                .elegirMedicoAleatorio(String.valueOf(Especialidad.CARDIOLOGIA), proximoLunes);// Se realiza la consulta
        //then or assert
        assertThat(medicoLibre).isNull(); // Se espera que no haya medicos disponibles
    }

    @Test @DisplayName("Debe retornar un medico si hay disponibles en la fecha especificada")
    void elegirMedicoAleatorioDisponibleEscenario2() {
        //given or arrange
        var proximoLunes = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0); // Se asume que el lunes a las 10:00 no hay medicos disponibles

        var medico= registrarMedico("medico1", "medico1@gmail.com", "111222333", Especialidad.CARDIOLOGIA);
        //when or act
        var medicoLibre = medicoRepository
                .elegirMedicoAleatorio(String.valueOf(Especialidad.CARDIOLOGIA), proximoLunes);// Se realiza la consulta
        //then or assert
        assertThat(medicoLibre).isEqualTo(medico); // Se espera que retorne medicos disponibles
    }

    private void registarConsulta(Medico medico, Paciente paciente, LocalDateTime proximoLunes) {
        em.persist(new Consulta(null,medico, paciente, proximoLunes, null));
    }

    private Medico registrarMedico(String nombre, String email, String documento, Especialidad especialidad) {
        var medico = new Medico(datosMedico(nombre, email, documento, especialidad));
        em.persist(medico);
        return medico;
    }

    private Paciente registrarPaciente(String nombre, String email, String documento) {
        var paciente = new Paciente(datosPaciente(nombre, email, documento));
        em.persist(paciente);
        return paciente;
    }
    private DatosMedicoDTO datosMedico(String nombre, String email, String documento, Especialidad especialidad) {
        return new DatosMedicoDTO(nombre, "apellido", email, "111000222", especialidad, datosDireccion());
    }

    private DatosRegistroPacienteDTO datosPaciente(String nombre, String email, String documento) {
        return new DatosRegistroPacienteDTO(nombre, email, "111222333", documento, datosDireccion());
    }

    private DatosDireccionDTO datosDireccion() {
        return new DatosDireccionDTO(
                "Calle x",
                "Barrio x",
                "Ciudad x"
        );
    }

}