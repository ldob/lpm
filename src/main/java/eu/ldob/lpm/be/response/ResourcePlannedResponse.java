package eu.ldob.lpm.be.response;

import java.util.ArrayList;
import java.util.List;

public class ResourcePlannedResponse {

    private List<ProjectResponse> projects;
    private List<WeekResponse> weeks;
    private List<ResourcePlannedValueResponse> values = new ArrayList<>();

    public ResourcePlannedResponse() {
    }

    public List<ProjectResponse> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectResponse> projects) {
        this.projects = projects;
    }

    public List<WeekResponse> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<WeekResponse> weeks) {
        this.weeks = weeks;
    }

    public void addValues(Long projectId, Integer weekId, Float resources) {
        this.values.add(new ResourcePlannedValueResponse(projectId, weekId, resources));
    }

    private class ResourcePlannedValueResponse {
        private Long projectId;
        private Integer weekId;
        private Float resources;

        public ResourcePlannedValueResponse(Long projectId, Integer weekId, Float resources) {
            this.projectId = projectId;
            this.weekId = weekId;
            this.resources = resources;
        }
    }
}
