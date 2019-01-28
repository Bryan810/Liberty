/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.openliberty.guides.rest.entidades;

import javax.persistence.*;

/**
 * @author Bryan
 */
@Entity
@Table(name = "Direccion")
@NamedQuery(name = "Direccion.findAll", query = "SELECT e FROM Direccion e")
@NamedQuery(name = "Direccion.findDireccion", query = "SELECT e FROM Direccion e WHERE "
        + "e.calle1 = :calle1 AND e.calle2 = :calle2 AND e.numero = :numero")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "calle1")
    private String calle1;
    @Column(name = "calle2")
    private String calle2;
    @Column(name = "numero")
    private String numero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle1() {
        return calle1;
    }

    public void setCalle1(String calle1) {
        this.calle1 = calle1;
    }

    public String getCalle2() {
        return calle2;
    }

    public void setCalle2(String calle2) {
        this.calle2 = calle2;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Direccion() {
    }

    public Direccion(String calle1, String calle2, String numero) {
        this.calle1 = calle1;
        this.calle2 = calle2;
        this.numero = numero;
    }
}
