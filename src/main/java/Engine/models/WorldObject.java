package Engine.models;

import Engine.rendering.Camera;
import Engine.rendering.Renderable;
import org.joml.Vector3f;

public abstract class WorldObject implements Renderable {
    private Model model;

    private Vector3f position;

    private Vector3f rotation;

    private Vector3f scale;

    /**
     * a generic object class
     * @param model the model of the object
     * @param position the position of the object
     * @param rotation the rotation of the object
     * @param scale the scale of the object
     */
    public WorldObject(Model model, Vector3f position, Vector3f rotation, Vector3f scale) {
        this.model = model;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    public void increasePosition(Vector3f position){
        this.position.add(position);
    }

    public void increaseRotation(Vector3f rotation){
        this.rotation.add(rotation);
        if(this.rotation.x > 360 || this.rotation.y > 360 || this.rotation.z > 360){
            this.rotation.set(0);
        }
    }

    public void increaseScale(Vector3f scale){
        this.scale.add(scale);
    }
    public void increaseScale(float scale){
        this.scale.add(new Vector3f(scale,scale,scale));
    }




}
