package com.med.api.model;

import com.med.api.ModeloDTO.DatosDireccionDTO;
import jakarta.validation.constraints.NotNull;

public record ActualizarMedicoDTO(@NotNull Long id, String nombre, String telefono, DatosDireccionDTO direccion) {
}
