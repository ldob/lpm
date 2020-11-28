package eu.ldob.lpm.be.resource;

import eu.ldob.lpm.be.entity.Project;
import eu.ldob.lpm.be.exception.LpmNoResultException;
import eu.ldob.lpm.be.request.ProjectRequest;
import eu.ldob.lpm.be.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/project")
@Component
public class ProjectResource {
    @Autowired
    private ProjectService service;

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Project add(final ProjectRequest request) {
        return service.add(request);
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getAll() {
        return service.findAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Project getById(@PathParam("id") Long id) {
        try {
            return service.findById(id);
        }
        catch (LpmNoResultException e) {
            return null;
        }
    }
}
