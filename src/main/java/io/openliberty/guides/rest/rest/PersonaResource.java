package io.openliberty.guides.rest.rest;

import io.openliberty.guides.rest.Dao.PersonaDao;
import io.openliberty.guides.rest.entidades.Persona;
import io.openliberty.guides.rest.entidades.Direccion;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/*
 *Creado por: Bryan Saltos
 *    El: 27 - ene - 2019
 */
@RequestScoped
@Path("persona")
public class PersonaResource {

    @Inject
    private PersonaDao personaDao;


    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public Response addNuevaPersona(@FormParam("identificacion") String identificacion,
                                    @FormParam("nombre") String nombre,
                                    @FormParam("fechaNacimiento") String fechaNacimiento,
                                    @FormParam("Direccion") String Direccion) {
        Direccion direccion = new Direccion();
        Persona nuevaPersona = new Persona(identificacion, nombre, fechaNacimiento, direccion);
        personaDao.crearPersona(nuevaPersona);
        return Response.status(Response.Status.NO_CONTENT).build();
    }


    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public Response updatePersona(@FormParam("identificacion") String identificacion,
                                  @FormParam("nombre") String nombre,
                                  @FormParam("fechaNacimiento") String fechaNacimiento,
                                  @FormParam("Direccion") String Direccion,
                                  @PathParam("id") long id) {
        Persona persPrevia = personaDao.buscarPersona(id);
        if (persPrevia == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No existe esa persona").build();
        }
        if (!personaDao.findPersona(identificacion, nombre, fechaNacimiento).isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Ya existe esa persona").build();
        }

        persPrevia.setIdentificacion(identificacion);
        persPrevia.setNombre(nombre);
        persPrevia.setFechaNacimiento(fechaNacimiento);

        personaDao.actualizarPersona(persPrevia);
        return Response.status(Response.Status.NO_CONTENT).build();
    }


    @DELETE
    @Path("{id}")
    @Transactional
    public Response deletePersona(@PathParam("id") long id) {
        Persona persona = personaDao.buscarPersona(id);
        if (persona == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Esa Persona no existe").build();
        }
        personaDao.borrarPersona(persona);
        return Response.status(Response.Status.NO_CONTENT).build();
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public JsonObject getPersona(@PathParam("id") long id) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        Persona persona = personaDao.buscarPersona(id);

        if (persona != null) {
            builder.add("identificacion", persona.getIdentificacion()).add("nombre", persona.getNombre())
                    .add("Fecha Nacimiento", persona.getFechaNacimiento()).add("Direccion", persona.getDireccion().getCalle1())
                    .add("Direccion", persona.getDireccion().getCalle2()).add("Numero", persona.getDireccion().getNumero());
        }
        return builder.build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public JsonArray getPersonas() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder finalArray = Json.createArrayBuilder();
        for (Persona persona : personaDao.listarTodasPersonas()) {
            builder.add("identificacion", persona.getIdentificacion()).add("nombre", persona.getNombre())
                    .add("Fecha Nacimiento", persona.getFechaNacimiento()).add("Direccion", persona.getDireccion().getCalle1())
                    .add("Direccion", persona.getDireccion().getCalle2()).add("Numero", persona.getDireccion().getNumero());
            finalArray.add(builder.build());
        }
        return finalArray.build();
    }

}
