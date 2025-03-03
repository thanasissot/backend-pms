package sotiroglou.athanasios;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/api/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {
    @GET
    @Transactional
    public List<Person> getAllPersons() {
        return Person.listAll();
    }

    @POST
    @Transactional
    public Response addPerson(Person person) {
        person.persist();
        return Response.status(Response.Status.CREATED).entity(person).build();
    }

    @POST
    @Transactional
    @Path("/multi")
    public Response addMany(ManyPersonDto manyPersonDto) {
        // Persist all the persons received in the request body
        List<Person> persons = manyPersonDto.getPersons();
        Person.persist(persons); // Persist all persons at once

        // Fetch the persons from the database with their generated IDs
        List<Person> persistedPersons = Person.list("id in ?1", persons.stream()
                .map(Person::getId)
                .collect(Collectors.toList())); // Find persisted persons by IDs

        // Return only the persisted persons with their IDs
        return Response.status(Response.Status.CREATED).entity(persistedPersons).build();
    }

    @PUT
    @Transactional
    public Response updatePerson(Person person) {
        Person entity = Person.findById(person.getId());
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        entity.firstName = person.firstName;
        entity.lastName = person.lastName;
        return Response.ok(entity).build();
    }

    @PUT
    @Path("/multi")
    @Transactional
    public Response updatePersons(List<Person> persons) {
        for (Person person : persons) {
            Person entity = Person.findById(person.getId());
            if (entity != null) {
                entity.firstName = person.firstName;
                entity.lastName = person.lastName;
            }
        }
        return Response.ok(persons).build();
    }

    @GET
    @Path("/delete/{id}")
    @Transactional
    public Response deletePerson(@PathParam("id") Long id) {
        boolean deleted = Person.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/delete/multi")
    @Transactional
    public Response deletePersons(List<Long> ids) {
        for (Long id : ids) {
            Person.deleteById(id);
        }
        return Response.noContent().build();
    }
}
