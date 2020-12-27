package eu.ldob.lpm.be.api;

import eu.ldob.lpm.be.exception.LpmNoResultException;
import eu.ldob.lpm.be.model.type.ERole;
import eu.ldob.lpm.be.request.UserRequest;
import eu.ldob.lpm.be.security.UserDetailsImpl;
import eu.ldob.lpm.be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/api/user")
public class UserController extends AController {

    @Autowired
    private UserService service;

    /*
    @PostMapping(
            path = "/add",
            consumes = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> add(@RequestBody UserRequest request, @AuthenticationPrincipal UserDetailsImpl user) {
        return ResponseEntity.ok(service.save(request));
    }
    */

    @PostMapping(
            path = "/update",
            consumes = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@RequestBody UserRequest request, @AuthenticationPrincipal UserDetailsImpl user) {
        return ResponseEntity.ok(service.update(request));
    }

    @GetMapping(
            path = "/all",
            produces = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll(@AuthenticationPrincipal UserDetailsImpl user) {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getById(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return ResponseEntity.ok(service.findById(id));
        }
        catch (LpmNoResultException e) {
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
    public ResponseEntity<?> getOwn(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return ResponseEntity.ok(service.findById(user.getId()));
        }
        catch (LpmNoResultException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PostMapping(
            path = "/{userId}/addRole",
            produces = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addRole(@PathVariable Long userId, @RequestBody ERole role) {
        try {
            service.addRole(userId, role);

            return ResponseEntity.ok(service.findById(userId));
        }
        catch (LpmNoResultException e) {
            return ResponseEntity
                    .badRequest()
                    .body(role + " could not be added to user " + userId);
        }
    }

    @DeleteMapping(
            path = "/{userId}/deleteRole",
            produces = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> removeRole(@PathVariable Long userId, @RequestBody ERole role) {
        try {
            service.removeRole(userId, role);

            return ResponseEntity.ok(service.findById(userId));
        }
        catch (LpmNoResultException e) {
            return ResponseEntity
                    .badRequest()
                    .body(role + " could not be removed from user " + userId);
        }
    }
}
