package Engine.registration;

import Engine.guiRendering.GUI;
import Engine.models.Light;
import Engine.models.WorldObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Registry {
    private static Map<String, Supplier<WorldObject>> objectRegistry = new HashMap<>();
    private static Map<String, Supplier<Light>> lightRegistry = new HashMap<>();
    private static Map<String, Supplier<GUI>> guiRegistry = new HashMap<>();

    public void registerObject(String id, Supplier<WorldObject> object){
        objectRegistry.put(id,object);
    }
    public void registerGUI(String id, Supplier<GUI> object){
        guiRegistry.put(id,object);
    }
}
