package com.med.api.model.medico;

import com.med.api.model.direccion.DatosDireccionDTO;

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
