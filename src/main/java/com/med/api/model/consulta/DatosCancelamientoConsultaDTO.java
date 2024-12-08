package com.med.api.model.consulta;

import jakarta.validation.constraints.NotNull;

public record DatosCancelamientoConsultaDTO(
        @NotNull
        Long id,
        @NotNull
        MotivoCancelamiento motivo
) {
}
