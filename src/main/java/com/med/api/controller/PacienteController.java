package com.med.api.controller;

import com.med.api.ModeloDTO.DatosPacienteDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @PostMapping
    public void registrarPaciente(@RequestBody DatosPacienteDTO paciente){

    }

}
