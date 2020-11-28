package eu.ldob.lpm.be;

import eu.ldob.lpm.be.resource.IndexResource;
import eu.ldob.lpm.be.resource.ProjectResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        registerEndpoints();
    }

    private void registerEndpoints() {
        //register(IndexResource.class);
        register(ProjectResource.class);
    }
}
