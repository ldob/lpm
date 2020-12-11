package eu.ldob.lpm.be.api;

import eu.ldob.lpm.be.exception.LpmNoResultException;
import eu.ldob.lpm.be.exception.LpmNotAllowedException;
import eu.ldob.lpm.be.model.type.EProjectRole;
import eu.ldob.lpm.be.request.ProjectRequest;
import eu.ldob.lpm.be.security.UserDetailsImpl;
import eu.ldob.lpm.be.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/api/project")
public class ProjectController extends AController{

    @Autowired
    private ProjectService service;

    @PostMapping(
            path = "/add",
            consumes = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> add(@RequestBody ProjectRequest request, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return ResponseEntity.ok(service.save(request, getUserModel(user)));
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@RequestBody ProjectRequest request, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return ResponseEntity.ok(service.save(request, getUserModel(user)));
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
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        }
        catch (LpmNoResultException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PostMapping(
            path = "/{projectId}/addMember/{memberId}",
            produces = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addMember(@PathVariable Long projectId, @PathVariable Long memberId, @RequestBody EProjectRole role) {
        try {
            service.addMember(projectId, memberId, role);

            return ResponseEntity.ok(service.findById(projectId));
        }
        catch (LpmNoResultException e) {
            return ResponseEntity
                    .badRequest()
                    .body(role + " of user " + memberId + " could not be added to project " + projectId);
        }
    }

    @DeleteMapping(
            path = "/{projectId}/deleteMember/{memberId}",
            produces = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> removeMember(@PathVariable Long projectId, @PathVariable Long memberId, @RequestBody EProjectRole role) {
        try {
            service.removeMember(projectId, memberId, role);

            return ResponseEntity.ok(service.findById(projectId));
        }
        catch (LpmNoResultException e) {
            return ResponseEntity
                    .badRequest()
                    .body(role + " of user " + memberId + " could not be removed from project " + projectId);
        }
    }
}
