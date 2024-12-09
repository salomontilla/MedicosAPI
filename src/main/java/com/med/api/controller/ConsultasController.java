package com.med.api.controller;

import com.med.api.model.consulta.DatosCancelamientoConsultaDTO;
import com.med.api.model.consulta.DatosDetalleConsultaDTO;
import com.med.api.model.consulta.DatosReservaConsultaDTO;
import com.med.api.model.consulta.ReservaDeConsultas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultasController {
    @Autowired
    ReservaDeConsultas reserva;

    @PostMapping @Transactional
    public ResponseEntity reservar (@RequestBody @Valid DatosReservaConsultaDTO datos) {
        var consulta = reserva.reservar(datos);
        return ResponseEntity.ok(new DatosDetalleConsultaDTO(consulta.id(), consulta.idMedico(), consulta.idPaciente(), consulta.fecha()));
    }

    @DeleteMapping() @Transactional
    public ResponseEntity cancelar (@RequestBody @Valid DatosCancelamientoConsultaDTO datos) {
        reserva.cancelar(datos);
        return ResponseEntity.noContent().build();
    }
}
