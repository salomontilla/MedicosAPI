package com.med.api.model;

import com.med.api.ModeloDTO.DatosMedicoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medico")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DatosMedicoDTO medico) {
        this.nombre = medico.nombre();
        this.apellido = medico.apellido();
        this.email = medico.email();
        this.telefono = medico.telefono();
        this.activo = true;
        this.especialidad = medico.especialidad();
        this.direccion = new Direccion(medico.direccion());
    }

    public void actualizarDatos(ActualizarMedicoDTO medico) {
        if(medico.nombre() != null){
            this.nombre = medico.nombre();
        }
        if(medico.telefono() != null){
            this.telefono = medico.telefono();
        }
        if(medico.direccion() != null){
            this.direccion = this.direccion.actualizarDireccion(medico.direccion());
        }
    }

    public void desactivarMedico() {
        this.activo = false;
    }
}
