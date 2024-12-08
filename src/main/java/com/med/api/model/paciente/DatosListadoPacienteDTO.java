package com.med.api.model.paciente;

public record DatosListadoPacienteDTO(Long id, String nombre, String email, String documento) {

    public DatosListadoPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumento());
    }
}
