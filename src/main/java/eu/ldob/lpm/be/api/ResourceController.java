package eu.ldob.lpm.be.api;

import eu.ldob.lpm.be.exception.LpmNotAllowedException;
import eu.ldob.lpm.be.security.UserDetailsImpl;
import eu.ldob.lpm.be.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/api/resource")
public class ResourceController extends AController{

    @Autowired
    private ResourceService service;

    @GetMapping(
            path = "/planned/all/{year}",
            produces = MediaType.APPLICATION_JSON
    )
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllPlanned(@PathVariable Integer year, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return ResponseEntity.ok(service.findAllPlanned(year, getUserModel(user)));
        }
        catch (LpmNotAllowedException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
