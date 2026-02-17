package api.factories;

import api.generators.RandomData;
import api.models.Project;

public class ProjectFactory {
    public Project create() {
        return create(RandomData.getString(), RandomData.getString());
    }

    public Project create(String id, String name) {
        return Project.builder()
                .id(id)
                .name(name)
                .build();
    }

    public Project create(String id, String name, String locator) {
        return Project.builder()
                .id(id)
                .name(name)
                .locator(locator)
                .build();
    }
}
