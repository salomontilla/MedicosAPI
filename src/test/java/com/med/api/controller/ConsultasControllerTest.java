package com.med.api.controller;

import com.med.api.model.consulta.DatosDetalleConsultaDTO;
import com.med.api.model.consulta.DatosReservaConsultaDTO;
import com.med.api.model.consulta.ReservaDeConsultas;
import com.med.api.model.medico.Especialidad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@TestPropertySource(locations = "classpath:application-test.properties")
class ConsultasControllerTest {

    @Autowired
    private MockMvc mvc; // Simula las peticiones HTTP

    @Autowired
    private JacksonTester<DatosReservaConsultaDTO> datosReservaConsultaDTOJson; // Serializa y deserializa objetos para ser testeados

    @Autowired
    private JacksonTester<DatosDetalleConsultaDTO> datosDetalleConsultaDTOJson;

    @MockitoBean //esta anotacion es necesaria para que mockito pueda mockear la clase //mockear es simular el comportamiento de una clase
    private ReservaDeConsultas reservaDeConsultas; // Simula la clase ReservaDeConsultas

    @Test
    @DisplayName("Retorna http status 400 bad request")
    @WithMockUser//simula un usuario autenticado
    void reservar_escenario1() throws Exception {
        var response = mvc.perform(post("/consultas"))
            .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Retorna http status 200")
    @WithMockUser
    void reservar_escenario2() throws Exception {

        var fecha = LocalDateTime.now().plusHours(1);
        var especialidad = Especialidad.CARDIOLOGIA;
        var datosDetalle = new DatosDetalleConsultaDTO(1L, 1L, 1L, fecha);
        when(reservaDeConsultas.reservar(any())).thenReturn(datosDetalle);//cuando se llame al metodo reservar de la clase ReservaDeConsultas, retornara datosDetalle
        var response = mvc.perform(post("/consultas")//perform realiza una peticion HTTP
                .contentType(MediaType.APPLICATION_JSON)
                .content(datosReservaConsultaDTOJson.write(new DatosReservaConsultaDTO(
                        1L, 1L, fecha, especialidad
                )).getJson())
        ).andReturn().getResponse(); // Se realiza una petición POST a la ruta /consultas con los datos de la consulta

        var jsonEsperado = datosDetalleConsultaDTOJson.write(
               datosDetalle
        ).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

}