package com.med.api.model.medico;

public record DatosMedicoListaDTO(
        long id,
        String nombre,
        String especialidad,
        String telefono,
        String email
) {
    public DatosMedicoListaDTO(Medico medico){
        this(medico.getId(), medico.getNombre(), medico.getEspecialidad().toString(), medico.getTelefono(), medico.getEmail());
    }
}
