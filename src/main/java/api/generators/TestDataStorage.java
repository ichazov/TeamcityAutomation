package api.generators;

import api.enums.Endpoint;
import api.models.BaseModel;
import api.requests.unchecked.UncheckedBase;
import api.spec.Specifications;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TestDataStorage {
    private final EnumMap<Endpoint, Set<String>> storage;

    private TestDataStorage() {
        storage = new EnumMap<>(Endpoint.class);
    }

    private static class SingletonHelper {
        private static final TestDataStorage TEST_DATA_STORAGE_INSTANCE = new TestDataStorage();
    }

    public static TestDataStorage getInstance() {
        return SingletonHelper.TEST_DATA_STORAGE_INSTANCE;
    }

    private void addToStorage(Endpoint endpoint, String id) {
        if (id != null) {
            storage.computeIfAbsent(endpoint, key -> new HashSet<>()).add(id);
        }
    }

    private String getEntityIdOrLocator(BaseModel model) {
        try {
            var id = model.getClass().getDeclaredField("id");
            id.setAccessible(true);
            var idValue = Objects.toString(id.get(model), null);
            id.setAccessible(false);
            return idValue;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            try {
                var locator = model.getClass().getDeclaredField("locator");
                locator.setAccessible(true);
                var locatorValue = Objects.toString(locator.get(model), null);
                locator.setAccessible(false);
                return locatorValue;
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                throw new IllegalStateException("Cannot get id or locator of entity", e);
            }
        }
    }

    public void addToStorage(Endpoint endpoint, BaseModel model) {
        addToStorage(endpoint, getEntityIdOrLocator(model));
    }

    public void clearStorage() {
        storage.forEach(((endpoint, ids) ->
                        ids.forEach(id ->
                                new UncheckedBase(Specifications.superUserSpec(), endpoint).delete(id)
                        )
                )
        );
        storage.clear();
    }
}
