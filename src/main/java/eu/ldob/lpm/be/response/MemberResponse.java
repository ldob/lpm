package eu.ldob.lpm.be.response;

public class MemberResponse {

    private Long id;
    private String username;

    public MemberResponse() {
    }

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
}
