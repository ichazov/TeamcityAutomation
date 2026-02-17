package api.factories;

import api.generators.RandomData;
import api.models.BuildType;
import api.models.Project;
import api.models.Steps;


public class BuildTypeFactory {
    private final StepsFactory stepsFactory = new StepsFactory();

    public BuildType create() {
        Project project = new ProjectFactory().create();
        return create(project);
    }

    public BuildType create(Project project) {
        return create(RandomData.getString(), RandomData.getString(), project, stepsFactory.create());
    }

    public BuildType create(String id, String name, Project project, Steps steps) {
        return BuildType.builder()
                .id(id)
                .name(name)
                .project(project)
                .steps(steps)
                .build();
    }
}
