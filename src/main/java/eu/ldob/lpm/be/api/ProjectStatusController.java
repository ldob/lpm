package eu.ldob.lpm.be.api;

import eu.ldob.lpm.be.exception.LpmNotAllowedException;
import eu.ldob.lpm.be.request.ProjectStatusRequest;
import eu.ldob.lpm.be.security.UserDetailsImpl;
import eu.ldob.lpm.be.service.ProjectStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/api/project/{projectId}/status")
public class ProjectStatusController extends AController{

    @Autowired
    private ProjectStatusService service;

    @GetMapping(
            path = "/all",
            produces = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getAll(@PathVariable Long projectId, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return ResponseEntity.ok(service.findAll(projectId, getUserModel(user)));
        }
        catch (LpmNotAllowedException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping(
            path = "/latest",
            produces = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getLatest(@PathVariable Long projectId, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return ResponseEntity.ok(service.findLatest(projectId, getUserModel(user)));
        }
        catch (LpmNotAllowedException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PostMapping(
            path = "/add",
            consumes = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> add(@PathVariable Long projectId, @RequestBody ProjectStatusRequest request, @AuthenticationPrincipal UserDetailsImpl user) {
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
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> update(@PathVariable Long projectId, @RequestBody ProjectStatusRequest request, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return ResponseEntity.ok(service.save(projectId, request, getUserModel(user)));
        }
        catch (LpmNotAllowedException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    /*
    @PostMapping(
            path = "/update",
            consumes = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('USER')")
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
    */

    /*
    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('USER')")
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
    */
}
