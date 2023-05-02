package Engine;

import Engine.models.Light;
import Engine.models.WorldObject;
import Engine.rendering.Camera;
import Engine.rendering.Renderer;

import java.util.ArrayList;

public class Scene {
    private ArrayList<WorldObject> objects = new ArrayList<>();
    private ArrayList<Light> lights  = new ArrayList<>();

    private ArrayList<Renderer> renderers = new ArrayList<Renderer>();

    public void addObject(WorldObject object){
        objects.add(object);
    }

    public void addLight(Light light){
        lights.add(light);
    }

    public void addRenderer(Renderer renderer){
        renderers.add(renderer);
    }

    public void removeObject(WorldObject object){
        objects.remove(object);
    }

    public void removeLight(Light light){
        lights.remove(light);
    }

    public ArrayList<WorldObject> getObjects() {
        return objects;
    }

    public ArrayList<Light> getLights() {
        return lights;
    }

    public void renderAll(Camera camera){
        renderWorldObjects(camera);
        renderLights(camera);

    }
    public void updateAll(Camera camera){
        lights.forEach(l->l.update(camera));

    }

    public void renderWorldObjects(Camera camera){
       renderers.get(0).render(camera);
    }

    public void renderLights(Camera camera){
        lights.forEach(l->l.render(camera));
    }

    public void cleanAll(){

    }
}
