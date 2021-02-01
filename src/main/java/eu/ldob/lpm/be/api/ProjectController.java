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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @GetMapping(
            path = "/{projectId}/member",
            produces = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllAssignedMembers(@PathVariable Long projectId, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return ResponseEntity.ok(service.findAllAssignedMembers(projectId, Arrays.asList(EProjectRole.values()), getUserModel(user)));
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

    @GetMapping(
            path = "/{projectId}/member/{memberRole}",
            produces = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAssignedMembersByRole(@PathVariable Long projectId, @PathVariable String memberRole, @AuthenticationPrincipal UserDetailsImpl user) {
        List<EProjectRole> roles = new ArrayList<>();
        try {
            roles.add(EProjectRole.valueOf(memberRole));

            return ResponseEntity.ok(service.findAllAssignedMembers(projectId, roles, getUserModel(user)));
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

    @PostMapping(
            path = "/{projectId}/addMember/{memberUsername}",
            produces = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addMember(@PathVariable Long projectId, @PathVariable String memberUsername, @RequestBody String role, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            service.addMember(projectId, memberUsername, EProjectRole.valueOf(role), getUserModel(user));
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body("role " + role + " could not be found");
        }
        catch (LpmNotAllowedException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }

        try {
            List<EProjectRole> roles = new ArrayList<>();
            roles.add(EProjectRole.valueOf(role));
            return ResponseEntity.ok(service.findAllAssignedMembers(projectId, roles, getUserModel(user)));
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body("role " + role + " could not be found");
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

    @PostMapping(
            path = "/{projectId}/removeMember/{memberUsername}",
            produces = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> removeMember(@PathVariable Long projectId, @PathVariable String memberUsername, @RequestBody String role, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            service.removeMember(projectId, memberUsername, EProjectRole.valueOf(role), getUserModel(user));
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body("role " + role + " could not be found");
        }
        catch (LpmNotAllowedException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }

        try {
            List<EProjectRole> roles = new ArrayList<>();
            roles.add(EProjectRole.valueOf(role));
            return ResponseEntity.ok(service.findAllAssignedMembers(projectId, roles, getUserModel(user)));
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body("role " + role + " could not be found");
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
