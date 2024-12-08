package com.med.api.model.paciente;

import com.med.api.model.direccion.DatosDireccionDTO;

public record DatosPacienteDTO(
        String nombre,
        String email,
        String telefono,
        String documentoIdentidad,
        DatosDireccionDTO direccion ) {
}
