package com.med.api.model.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Direccion {
    private String calle;
    private String barrio;
    private String ciudad;

    public Direccion(DatosDireccionDTO direccion) {
        this.calle = direccion.calle();
        this.barrio = direccion.barrio();
        this.ciudad = direccion.ciudad();
    }

    public Direccion actualizarDireccion(DatosDireccionDTO direccion) {
        this.calle = direccion.calle();
        this.barrio = direccion.barrio();
        this.ciudad = direccion.ciudad();
        return this;
    }
}
