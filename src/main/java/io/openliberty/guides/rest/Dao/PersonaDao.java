package io.openliberty.guides.rest.Dao;

import io.openliberty.guides.rest.entidades.Persona;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/*
 *Creado por: Bryan Saltos
 *    El: 27 - ene - 2019
 */
@RequestScoped
public class PersonaDao {

    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;


    public void crearPersona(Persona persona) {
        em.persist(persona);
    }

    public Persona buscarPersona(long idPersona) {

        return em.find(Persona.class, idPersona);
    }

    public void actualizarPersona(Persona persona) {
        em.merge(persona);
    }

    public void borrarPersona(Persona persona) {

        em.remove(persona);
    }

    public List<Persona> listarTodasPersonas() {
        return em.createNamedQuery("Persona.findAll", Persona.class).getResultList();
    }


    public List<Persona> findPersona(String identificacion, String nombre, String fechaNacimiento) {
        return em.createNamedQuery("Persona.findPersona", Persona.class)
                .setParameter("identificacion", identificacion)
                .setParameter("nombre", nombre)
                .setParameter("fechaNacimiento", fechaNacimiento).getResultList();
    }

}
