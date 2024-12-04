package com.med.api.ModeloDTO;

import com.med.api.model.Direccion;
import jakarta.validation.constraints.NotBlank;

public record DatosDireccionDTO(
        @NotBlank
        String calle,
        @NotBlank
        String barrio,
        @NotBlank
        String ciudad) {
}
