package api.factories;

import api.models.Step;
import api.models.Steps;

import java.util.List;

public class StepsFactory {
    private final StepFactory stepFactory = new StepFactory();

    public Steps create() {
        List<Step> steps = List.of(stepFactory.create());
        return Steps.builder()
                .count(steps.size())
                .step(steps)
                .build();
    }

    public Steps create(List<Step> steps) {
        int count = steps == null ? 0 : steps.size();
        return Steps.builder()
                .count(count)
                .step(steps)
                .build();
    }
}
