package com.med.api.model.consulta.validaciones.cancelamiento;

import com.med.api.model.consulta.DatosCancelamientoConsultaDTO;

public interface ValidadorDeCancelamiento {
    void cancelar(DatosCancelamientoConsultaDTO datos);
}
