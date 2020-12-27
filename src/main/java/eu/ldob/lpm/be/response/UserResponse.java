package eu.ldob.lpm.be.response;

import eu.ldob.lpm.be.model.type.ERole;

import java.util.ArrayList;
import java.util.List;

public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private List<ERole> roles = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ERole> getRoles() {
        return roles;
    }

    public void setRoles(List<ERole> roles) {
        this.roles = roles;
    }
}
