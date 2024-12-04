package com.med.api.ModeloDTO;

import com.med.api.model.Especialidad;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

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
