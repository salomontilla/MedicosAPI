package com.med.api.controller;

import com.med.api.model.direccion.DatosDireccionDTO;
import com.med.api.model.direccion.Direccion;
import com.med.api.model.medico.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")

class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DatosMedicoDTO> datosRegistroMedicoJson;

    @Autowired
    private JacksonTester<DatosMedicoRespuestaDTO> datosDetalleMedicoJson;

    @MockitoBean
    private MedicoRepository repository;

    @Test
    @DisplayName("Debería devolver código http 400 cuando las informaciones son inválidas")
    @WithMockUser
    void registrar_escenario1() throws Exception {
        var response = mvc
                .perform(post("/medicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Debería devolver código http 200 cuando las informaciones son validas")
    @WithMockUser
    void registrar_escenario2() throws Exception {

        var datosRegistro = new DatosMedicoDTO(
                "Salomon",
                "Montilla",
                "salomontilla@outlook.com",
                "3216342133",
                Especialidad.CARDIOLOGIA,
                datosDireccion());
        when(repository.save(any())).thenReturn(new Medico(datosRegistro));

        var response = mvc
                .perform(post("/medicos")
                        .contentType("application/json")
                        .content(datosRegistroMedicoJson.write(datosRegistro).getJson()))
                .andReturn().getResponse();

        var datosDetalle = new DatosMedicoRespuestaDTO(
                null,
                datosRegistro.nombre(),
                datosRegistro.apellido(),
                datosRegistro.email(),
                datosRegistro.telefono(),
                datosRegistro.especialidad().toString(),
                new DatosDireccionDTO(
                        datosRegistro.direccion().calle(),
                        datosRegistro.direccion().barrio(),
                        datosRegistro.direccion().ciudad()
                )
        );
        var jsonEsperado = datosDetalleMedicoJson.write(datosDetalle).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
    

    private DatosDireccionDTO datosDireccion() {
        return new DatosDireccionDTO(
                "Calle x",
                "Barrio x",
                "Ciudad x"
        );
    }

}