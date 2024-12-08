package com.med.api.model.paciente;

import com.med.api.model.direccion.DatosDireccionDTO;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarPacienteDTO(
        @NotNull
        Long id,
        String nombre,
        String telefono,
        DatosDireccionDTO direccion) {
}
