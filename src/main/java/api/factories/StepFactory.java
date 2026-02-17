package api.factories;

import api.models.Step;

public class StepFactory {
    public Step create() {
        return Step.builder().build();
    }

    public Step create(String id, String name, String type) {
        return Step.builder()
                .id(id)
                .name(name)
                .type(type)
                .build();
    }
}
