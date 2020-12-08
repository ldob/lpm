package eu.ldob.lpm.be.response;

public class MemberResponse {

    private Long id;
    private String name;

    public MemberResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
