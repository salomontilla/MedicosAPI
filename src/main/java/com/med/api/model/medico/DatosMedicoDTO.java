package com.med.api.model.medico;

import com.med.api.model.direccion.DatosDireccionDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosMedicoDTO (
        @NotBlank
        String nombre,
        @NotBlank
        String apellido,
        @NotBlank @Email
        String email,
        @NotBlank
        String telefono,
        @NotNull
        Especialidad especialidad,
        @NotNull @Valid
        DatosDireccionDTO direccion) {
}
