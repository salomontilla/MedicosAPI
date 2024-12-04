package com.med.api.ModeloDTO;

public record DatosMedicoRespuestaDTO(
        Long id,
        String nombre,
        String apellido,
        String email,
        String telefono,
        String especialidad,
        DatosDireccionDTO direccion
) {
}
