package Engine;

import Engine.guiRendering.GUI;
import Engine.models.Light;
import Engine.models.WorldObject;
import Engine.rendering.Camera;
import Engine.rendering.Renderer;
import GameTest.Player;

import java.util.ArrayList;

public class Scene {
    private final ArrayList<WorldObject> objects = new ArrayList<>();
    private final ArrayList<Light> lights  = new ArrayList<>();

    private final ArrayList<Renderer> renderers = new ArrayList<Renderer>();

    private final ArrayList<GUI> guis = new ArrayList<>();

    private Camera camera;

    private final Player player;

    public Scene(Camera camera, Player player){
        this.camera = camera;
        this.player = player;
    }

    public Scene(Player player){
        this.camera = player.getCamera();
        this.player = player;
    }


    public void addGUI(GUI gui){
        guis.add(gui);
    }

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

    public void removeGUI(GUI gui){
        guis.remove(gui);
    }

    public ArrayList<WorldObject> getObjects() {
        return objects;
    }

    public ArrayList<Light> getLights() {
        return lights;
    }

    public ArrayList<GUI> getGuis(){
        return guis;
    }

    public ArrayList<Renderer> getRenderers() {
        return renderers;
    }

    public Camera getCamera() {
        return camera;
    }

    public Player getPlayer(){return player;}

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}
