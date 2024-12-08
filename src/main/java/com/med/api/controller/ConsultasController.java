package com.med.api.controller;

import com.med.api.model.consulta.DatosDetalleConsultaDTO;
import com.med.api.model.consulta.DatosReservaConsultaDTO;
import com.med.api.model.consulta.ReservaDeConsultas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultasController {
    @Autowired
    ReservaDeConsultas reserva;

    @PostMapping @Transactional
    public ResponseEntity reservar (@RequestBody @Valid DatosReservaConsultaDTO datos) {
        reserva.reservar(datos);

        return ResponseEntity.ok(new DatosDetalleConsultaDTO(null, null, null, null));
    }
}
