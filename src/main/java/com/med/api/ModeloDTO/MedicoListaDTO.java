package com.med.api.ModeloDTO;

import com.med.api.model.Medico;

public record MedicoListaDTO (
        long id,
        String nombre,
        String especialidad,
        String telefono,
        String email
) {
    public MedicoListaDTO(Medico medico){
        this(medico.getId(), medico.getNombre(), medico.getEspecialidad().toString(), medico.getTelefono(), medico.getEmail());
    }
}
