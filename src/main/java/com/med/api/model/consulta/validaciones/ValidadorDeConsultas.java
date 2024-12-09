package com.med.api.model.consulta.validaciones;

import com.med.api.model.consulta.DatosReservaConsultaDTO;

public interface ValidadorDeConsultas {
    void validar(DatosReservaConsultaDTO datos);
}
