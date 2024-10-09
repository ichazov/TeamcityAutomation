package api.generators;

import api.annotations.Optional;
import api.annotations.Parameterizable;
import api.annotations.Random;
import api.models.BaseModel;
import api.models.TestData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestDataGenerator {
    private TestDataGenerator() {
    }

    public static <T extends BaseModel> T generate(List<BaseModel> generatedModels, Class<T> generatorClass,
                                                   Object... parameters) {
        try {
            var instance = generatorClass.getDeclaredConstructor().newInstance();

            for (var field : generatorClass.getDeclaredFields()) {
                field.setAccessible(true);

                if (!field.isAnnotationPresent(Optional.class)) {
                    var generatedClass = generatedModels.stream()
                            .filter(m -> m.getClass().equals(field.getType())).findFirst();

                    if (field.isAnnotationPresent(Parameterizable.class) && parameters.length > 0) {
                        field.set(instance, parameters[0]);
                        parameters = Arrays.copyOfRange(parameters, 1, parameters.length);
                    } else if (field.isAnnotationPresent(Random.class)) {

                        if (String.class.equals(field.getType())) {
                            field.set(instance, RandomData.getString());
                        }
                    } else if (BaseModel.class.isAssignableFrom(field.getType())) {
                        var finalParameters = parameters;
                        field.set(instance, generatedClass.orElseGet(() -> generate(
                                generatedModels, field.getType().asSubclass(BaseModel.class), finalParameters)));
                    } else if (List.class.isAssignableFrom(field.getType()) && field.getGenericType() instanceof ParameterizedType) {
                            ParameterizedType pt = (ParameterizedType) field.getGenericType();
                            var typeClass = (Class<?>) pt.getActualTypeArguments()[0];

                            if (BaseModel.class.isAssignableFrom(typeClass)) {
                                var finalParameters = parameters;
                                field.set(instance, generatedClass.map(List::of).orElseGet(() -> List.of(generate(
                                        generatedModels, typeClass.asSubclass(BaseModel.class), finalParameters))));
                            }
                        }
                }
                field.setAccessible(false);
            }
            return instance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                 | NoSuchMethodException e) {
            throw new IllegalStateException("Cannot generate test data", e);
        }
    }

    public static TestData generate() {
        try {
            var instance = TestData.class.getDeclaredConstructor().newInstance();
            var models = new ArrayList<BaseModel>();
            for (var field: TestData.class.getDeclaredFields()) {
                field.setAccessible(true);
                if (BaseModel.class.isAssignableFrom(field.getType())) {
                    var model = generate(models, field.getType().asSubclass(BaseModel.class));
                    field.set(instance, model);
                    models.add(model);
                }
                field.setAccessible(false);
            }
            return instance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException("Cannot generate test data", e);
        }
    }

    public static <T extends BaseModel> T generate(Class<T> generatorClass, Object... parameters) {
        return generate(Collections.emptyList(), generatorClass, parameters);
    }
}
