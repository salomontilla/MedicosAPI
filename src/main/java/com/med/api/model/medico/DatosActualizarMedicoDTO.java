package com.med.api.model.medico;

import com.med.api.model.direccion.DatosDireccionDTO;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarMedicoDTO(@NotNull Long id, String nombre, String telefono, DatosDireccionDTO direccion) {
}
