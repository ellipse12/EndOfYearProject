package Engine.models;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public class WorldObject {
    private Model model;

    private Vector3f position;

    private Vector3f rotation;

    private Vector3f scale;

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
}
