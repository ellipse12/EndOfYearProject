package Engine.registration;

import Engine.guiRendering.GUI;
import Engine.models.Light;
import Engine.models.WorldObject;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Registry {
    private static Map<String, Supplier<WorldObject>> objectRegistry = new HashMap<>();
    private static Map<String, Supplier<GUI>> guiRegistry = new HashMap<>();


    /**
     * registers an object to the registry
     * @param id the id of the object to register
     * @param object the object supplier to register
     */
    public static void registerObject(String id, Supplier<WorldObject> object){
        objectRegistry.put(id,object);
    }

    /**
     * registers an GUI to the registry
     * @param id the id of the GUI to register
     * @param object the GUI supplier to register
     */
    public static void registerGUI(String id, Supplier<GUI> object){
        guiRegistry.put(id,object);
    }

    /**
     * @return a supplier of a typical light
     */
    public static Supplier<Light> getLight(){
        return ()->new Light(new Vector3f(), new Vector3f(), 0f);
    }

    public static Map<String, Supplier<WorldObject>> getObjectRegistry(){
        return objectRegistry;
    }



    public static Supplier<WorldObject> getObject(String id){
        return objectRegistry.get(id);
    }


}
