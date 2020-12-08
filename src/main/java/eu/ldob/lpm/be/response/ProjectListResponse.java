package eu.ldob.lpm.be.response;

import eu.ldob.lpm.be.model.type.EProjectRole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectListResponse extends HashMap<EProjectRole, List<ProjectResponse>> {

    public ProjectListResponse() {
    }

    public Map<EProjectRole, List<ProjectResponse>> getProjects() {
        return this;
    }

    public void addProject(EProjectRole role, ProjectResponse project) {
        if(this.containsKey(role)) {
            this.get(role).add(project);
        }
        else {
            List<ProjectResponse> list = new ArrayList<>();
            list.add(project);
            this.put(role, list);
        }
    }
}
