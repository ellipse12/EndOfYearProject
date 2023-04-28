package Engine.rendering.registration;

import java.util.function.Supplier;

public class RegistryObject<T> {
    private Supplier<T> object;
    private String id;

    public RegistryObject(String id, Supplier<T> object) {
        this.object = object;
        this.id = id;
    }

    public Supplier<T> getObject() {
        return object;
    }

    public String getId() {
        return id;
    }
}
