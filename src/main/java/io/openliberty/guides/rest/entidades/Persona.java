/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.openliberty.guides.rest.entidades;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Bryan
 */

@Entity
@Table(name = "Persona")
@NamedQuery(name = "Persona.findAll", query = "SELECT e FROM Persona e")
@NamedQuery(name = "Persona.findPersona", query = "SELECT e FROM Persona e WHERE "
        + "e.identificacion = :identificacion AND e.nombre = :nombre AND e.fechaNacimiento= :fechaNacimiento")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long idPersona;
    @Column(name = "identificacion")
    private String identificacion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "Fecha_Nacimiento")
    private String fechaNacimiento;
    @ManyToOne
    @Column(name = "Direccion")
    private Direccion direccion;

    public Persona() {
    }

    public Persona(String identificacion, String nombre, String fechaNacimiento, Direccion direccion) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
