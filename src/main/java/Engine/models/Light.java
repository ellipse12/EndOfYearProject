package Engine.models;

import Engine.MainClass;
import Engine.rendering.Camera;
import Engine.shaders.StaticShader;
import Engine.util.Maths;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.json.JSONException;
import org.json.JSONObject;
import org.lwjgl.opengl.GL11;
import Engine.saving.JsonSerializable;

public class Light implements JsonSerializable<Light>{

    private Vector3f color;
    private Vector3f position;
    private float intensity;


    private final StaticShader shader = new StaticShader();

    /**
     * represents a light object
     * @param color the color of the light
     * @param position the position of the light
     * @param intensity the intensity of the light
     */
    public Light(Vector3f color, Vector3f position, float intensity) {
        this.color = color;
        this.position = position;
        this.intensity = intensity;


    }

    public Vector3f getColor() {
        return color;
    }

    
    public void setColor(Vector3f color) {
        this.color = color;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

    @Override
    public Light deserialize(JSONObject object){
       try {
            Vector3f position = parseVector3fEntry(object.getJSONArray("position"));
            Vector3f color = parseVector3fEntry(object.getJSONArray("color"));
            float intensity = (float)object.getDouble("intensity");
            this.setColor(color);
            this.setPosition(position);
            this.setIntensity(intensity);
            return this;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public JSONObject serialize(){
        try{
            JSONObject out = new JSONObject();
            out.put("position",createVector3fEntry(this.position));
            out.put("color", createVector3fEntry(this.color));
            out.put("intensity", this.intensity);
            out.put("type", "light");
            return out;
        }catch(JSONException e){
            throw new RuntimeException(e);
        }

    }
}
