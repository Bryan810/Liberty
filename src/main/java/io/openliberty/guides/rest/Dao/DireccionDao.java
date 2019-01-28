package io.openliberty.guides.rest.Dao;

/*
 *Creado por: Bryan Saltos
 *    El: 27 - ene - 2019
 */


import io.openliberty.guides.rest.entidades.Direccion;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RequestScoped
public class DireccionDao {

    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;


    public void crearDireccion(Direccion direccion) {
        em.persist(direccion);
    }

    public Direccion leerDireccion(long direccionId) {
        return em.find(Direccion.class, direccionId);
    }

    public void actualizarDireccion(Direccion direccion) {
        em.merge(direccion);
    }

    public void borrarDireccion(Direccion direccion) {
        em.remove(direccion);
    }

    public List<Direccion> todasDirecciones() {
        return em.createNamedQuery("Direccion.findAll", Direccion.class).getResultList();
    }

    public List<Direccion> findEvent(String calle1, String calle2, String numero) {
        return em.createNamedQuery("Direccion.findDireccion", Direccion.class)
                .setParameter("calle1", calle1)
                .setParameter("calle2", calle2)
                .setParameter("numero", numero).getResultList();
    }

}
