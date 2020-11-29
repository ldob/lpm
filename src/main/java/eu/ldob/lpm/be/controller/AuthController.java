package eu.ldob.lpm.be.controller;

import eu.ldob.lpm.be.model.ERole;
import eu.ldob.lpm.be.model.RoleModel;
import eu.ldob.lpm.be.model.UserModel;
import eu.ldob.lpm.be.repository.RoleRepository;
import eu.ldob.lpm.be.repository.UserRepository;
import eu.ldob.lpm.be.request.JwtResponse;
import eu.ldob.lpm.be.request.LoginRequest;
import eu.ldob.lpm.be.request.SignupRequest;
import eu.ldob.lpm.be.response.AuthenticationResponse;
import eu.ldob.lpm.be.security.JwtUtils;
import eu.ldob.lpm.be.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new AuthenticationResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new AuthenticationResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        UserModel user = new UserModel(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<RoleModel> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_LOGIN).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new AuthenticationResponse("User registered successfully!"));
    }
}
