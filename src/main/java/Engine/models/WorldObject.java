package Engine.models;

import Engine.saving.JsonSerializable;
import org.joml.Vector3f;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class WorldObject implements JsonSerializable<WorldObject> {
    private Model model;

    private Vector3f position;

    private Vector3f rotation;

    private Vector3f scale;

    private final String id;

    /**
     * a generic object class
     * @param model the model of the object
     * @param position the position of the object
     * @param rotation the rotation of the object
     * @param scale the scale of the object
     */
    public WorldObject(Model model, Vector3f position, Vector3f rotation, Vector3f scale, String id) {
        this.model = model;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.id = id;
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

    /**
     * increases the position
     * @param offset the offset to increase the position by
     */
    public void increasePosition(Vector3f offset){
        this.position.add(offset);
    }

    /**
     * increases the rotation and clamps it between 0 and 360 degrees
     * @param rotation how much to increase the rotation by
     */
    public void increaseRotation(Vector3f rotation){
        this.rotation.add(rotation);
        if(this.rotation.x > 360 || this.rotation.y > 360 || this.rotation.z > 360){
            this.rotation.set(0);
        }
    }

    /**
     * scales the object up
     * @param scale the amount to scale it by
     */
    public void increaseScale(Vector3f scale){
        this.scale.add(scale);
    }
    public void increaseScale(float scale){
        this.scale.add(new Vector3f(scale,scale,scale));
    }


    public String getId() {
        return id;
    }

    @Override
    public JSONObject serialize() {
        JSONObject out = new JSONObject();
        try {
            out.put("position", createVector3fEntry(this.getPosition()));

        out.put("rotation", createVector3fEntry(this.getRotation()));
        out.put("scale", createVector3fEntry(this.getScale()));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return out;
    }

    @Override
    public WorldObject deserialize(JSONObject object) {
        String id = object.getString("id");

    }
}
