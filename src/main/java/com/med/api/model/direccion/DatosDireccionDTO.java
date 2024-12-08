package com.med.api.model.direccion;

import jakarta.validation.constraints.NotBlank;

public record DatosDireccionDTO(
        @NotBlank
        String calle,
        @NotBlank
        String barrio,
        @NotBlank
        String ciudad) {
}
