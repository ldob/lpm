package eu.ldob.lpm.be.resource;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/public")
@Component
public class IndexResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        //has to be without blank spaces
        return "forward:index.html";
    }

}
