package Engine;

import Engine.guiRendering.GUI;
import Engine.models.Light;
import Engine.models.WorldObject;
import Engine.rendering.Camera;
import Engine.rendering.Renderer;
import Engine.rendering.Updateable;
import GameTest.Player;

import java.util.ArrayList;

public class Scene {
    private ArrayList<WorldObject> objects = new ArrayList<>();
    private ArrayList<Light> lights  = new ArrayList<>();

    private ArrayList<Renderer> renderers = new ArrayList<Renderer>();

    private ArrayList<Updateable> updateables = new ArrayList<>();

    private ArrayList<GUI> guis = new ArrayList<>();

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


    /**
     * @param gui the gui to add
     */
    public void addGUI(GUI gui){
        guis.add(gui);
        if(gui instanceof Updateable){
            updateables.add((Updateable) gui);
        }
    }

    /**
     * @param object the object to add
     */
    public void addObject(WorldObject object){
        objects.add(object);
        if(object instanceof Updateable){
            updateables.add((Updateable) object);
        }
    }

    /**
     * @param light the light to add
     */
    public void addLight(Light light){
        lights.add(light);
    }

    public void updateAll(){
        updateables.forEach(r->r.update(this));
        renderers.forEach(l->l.update(this));
        player.update(this);
    }

    /**
     * @param renderer the renderer to add
     */
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
