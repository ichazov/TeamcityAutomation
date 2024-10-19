package api.generators;

import api.annotations.NonMandatory;
import api.annotations.Parameterizable;
import api.annotations.Random;
import api.models.BaseModel;
import api.models.TestData;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public class TestDataGenerator {
    private TestDataGenerator() {
    }

    private static Optional<BaseModel> findGeneratedClass(List<BaseModel> generatedModels, Field field) {
        return generatedModels.stream()
                .filter(m -> m.getClass().equals(field.getType())).findFirst();
    }

    private static <T extends BaseModel> void setRandomField(T instance, Field field) {
        if (String.class.equals(field.getType())) {
            try {
                field.set(instance, RandomData.getString());
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Cannot set random field");
            }
        }
    }

    private static <T extends BaseModel> Object[] setParameterizableField(T instance, Field field,
                                                                          Object[] parameters) {
        try {
            field.set(instance, parameters[0]);
            return Arrays.copyOfRange(parameters, 1, parameters.length);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Cannot set parameterized field");
        }
    }

    private static <T extends BaseModel> T createInstance(Class<T> generatorClass) {
        try {
            return generatorClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                 | NoSuchMethodException e) {
            throw new IllegalStateException("Cannot create instance", e);
        }
    }

    private static boolean isListType(Field field) {
        return List.class.isAssignableFrom(field.getType()) && field.getGenericType() instanceof ParameterizedType;
    }

    public static <T extends BaseModel> T generate(List<BaseModel> generatedModels, Class<T> generatorClass,
                                                   Object... parameters) {
        try {
            var instance = createInstance(generatorClass);

            for (var field : generatorClass.getDeclaredFields()) {
                field.setAccessible(true);

                if (!field.isAnnotationPresent(NonMandatory.class)) {
                    var generatedClass = findGeneratedClass(generatedModels, field);

                    if (field.isAnnotationPresent(Parameterizable.class) && parameters.length > 0) {
                        parameters = setParameterizableField(instance, field, parameters);
                    } else if (field.isAnnotationPresent(Random.class)) {
                        setRandomField(instance, field);
                    } else if (BaseModel.class.isAssignableFrom(field.getType())) {
                        var finalParameters = parameters;

                        field.set(instance, generatedClass.orElseGet(() -> generate(
                                generatedModels, field.getType().asSubclass(BaseModel.class), finalParameters)));
                    } else if (isListType(field)) {
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
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Cannot generate test data", e);
        }
    }

    public static TestData generate() {
        try {
            var instance = createInstance(TestData.class);
            var models = new ArrayList<BaseModel>();
            for (var field : TestData.class.getDeclaredFields()) {
                field.setAccessible(true);
                if (BaseModel.class.isAssignableFrom(field.getType())) {
                    var model = generate(models, field.getType().asSubclass(BaseModel.class));
                    field.set(instance, model);
                    models.add(model);
                }
                field.setAccessible(false);
            }
            return instance;
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Cannot generate test data", e);
        }
    }

    public static <T extends BaseModel> T generate(Class<T> generatorClass, Object... parameters) {
        return generate(Collections.emptyList(), generatorClass, parameters);
    }
}
