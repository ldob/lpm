package eu.ldob.lpm.be.controller;

import eu.ldob.lpm.be.exception.LpmNoResultException;
import eu.ldob.lpm.be.request.ProjectRequest;
import eu.ldob.lpm.be.response.ProjectResponse;
import eu.ldob.lpm.be.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService service;

    @PostMapping(
            path = "/add",
            consumes = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ProjectResponse add(@RequestBody ProjectRequest request) {
        return service.add(request);
    }

    @GetMapping(
            path = "/all",
            produces = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<ProjectResponse> getAll() {
        return service.findAll();
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public @ResponseBody ProjectResponse getById(@PathVariable Long id) {
        try {
            return service.findById(id);
        }
        catch (LpmNoResultException e) {
            return null;
        }
    }
}
