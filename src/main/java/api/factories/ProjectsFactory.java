package api.factories;

import api.models.Project;
import api.models.Projects;

import java.util.List;

public class ProjectsFactory {
    private final ProjectFactory projectFactory = new ProjectFactory();

    public Projects create() {
        List<Project> projects = List.of(projectFactory.create());
        return Projects.builder()
                .count(projects.size())
                .project(projects)
                .build();
    }

    public Projects create(List<Project> projects) {
        int count = projects == null ? 0 : projects.size();
        return Projects.builder()
                .count(count)
                .project(projects)
                .build();
    }
}
