package com.med.api.ModeloDTO;

public record DatosPacienteDTO(
        String nombre,
        String email,
        String telefono,
        String documentoIdentidad,
        DatosDireccionDTO direccion ) {
}
