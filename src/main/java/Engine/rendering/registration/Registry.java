package Engine.rendering.registration;

import java.util.HashSet;
import java.util.Set;

public class Registry<T> {
    private Set<RegistryObject<T>> registryObjects;

    public Registry() {
        registryObjects = new HashSet<>();
    }

    public Set<RegistryObject<T>> getAllObjects(){
        return registryObjects;
    }

    public void register(RegistryObject<T> object) throws ElementExistsException{
        if(registryObjects.contains(object)){
            throw new ElementExistsException("Element {" + object.getId() + "} already exists and cannot be re-registered.");
        }
        registryObjects.add(object);
    }
    public RegistryObject<T> getRegistryElement(String id){
        for(RegistryObject<T> obj: registryObjects){
            if(obj.getId().equals(id)){
                return obj;
            }
        }
        return null;
    }

}
