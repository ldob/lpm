package eu.ldob.lpm.be.api;

import eu.ldob.lpm.be.exception.LpmNoResultException;
import eu.ldob.lpm.be.exception.LpmNotAllowedException;
import eu.ldob.lpm.be.request.ProjectTodoRequest;
import eu.ldob.lpm.be.security.UserDetailsImpl;
import eu.ldob.lpm.be.service.ProjectTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/api/todo")
public class ProjectTodoController extends AController{

    @Autowired
    private ProjectTodoService service;

    @PostMapping(
            path = "/add/{projectId}",
            consumes = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> add(@PathVariable Long projectId, @RequestBody ProjectTodoRequest request, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return ResponseEntity.ok(service.save(projectId, request, getUserModel(user)));
        }
        catch (LpmNotAllowedException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PostMapping(
            path = "/update",
            consumes = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> update(@RequestBody ProjectTodoRequest request, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return ResponseEntity.ok(service.save(null, request, getUserModel(user)));
        }
        catch (LpmNotAllowedException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping(
            path = "/all",
            produces = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAll(@AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return ResponseEntity.ok(service.findAll(getUserModel(user)));
        }
        catch (LpmNotAllowedException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping(
            path = "/all/{projectId}",
            produces = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllByProject(@PathVariable Long projectId, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return ResponseEntity.ok(service.findByProject(projectId, getUserModel(user)));
        }
        catch (LpmNotAllowedException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping(
            path = "/own",
            produces = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getOwn(@AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return ResponseEntity.ok(service.findByUser(getUserModel(user)));
        }
        catch (LpmNotAllowedException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getById(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return ResponseEntity.ok(service.findById(id, getUserModel(user)));
        }
        catch (LpmNotAllowedException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
        catch (LpmNoResultException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
