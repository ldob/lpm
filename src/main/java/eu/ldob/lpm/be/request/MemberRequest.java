package eu.ldob.lpm.be.request;

public class MemberRequest {

    private Long id;
    private String name;

    public MemberRequest() {
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
