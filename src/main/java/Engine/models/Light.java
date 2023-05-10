package Engine.models;

import Engine.MainClass;
import Engine.rendering.Camera;
import Engine.shaders.StaticShader;
import Engine.util.Maths;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import Engine.saving.JsonSerializable;

public class Light implements JsonSerializable<Light>{

    private Vector3f color;
    private Vector3f position;
    private float intensity;
    private final String id;

    private final StaticShader shader = new StaticShader();

    /**
     * represents a light object
     * @param color the color of the light
     * @param position the position of the light
     * @param intensity the intensity of the light
     */
    public Light(Vector3f color, Vector3f position, float intensity, String id) {
        this.color = color;
        this.position = position;
        this.intensity = intensity;
        this.id = id;

    }

    public Vector3f getColor() {
        return color;
    }
    public String getID(){
        return this.id;
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


    /**
     * renders this light
     * @param camera the camera to render from
     */
    public void render(Camera camera) {

        shader.setUniform("specularPower", 0.1f);
        shader.setUniform("ambientLight", new Vector3f(0.5f,0.5f,0.5f));
        shader.setUniform("light", this);
        shader.setUniform("camera_position", camera.getPosition());

    }


    /**
     * updates this light
     * @param camera the camera to render from
     */
    public void update(Camera camera) {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        shader.start();
        Matrix4f projectionMatrix = Maths.createProjectionMatrix(MainClass.window);
        shader.setUniform("projectionMatrix", projectionMatrix);


        shader.stop();


    }

    /**
     * cleans the shader
     */
    public void cleanUp() {
        shader.cleanUp();
    }
    
    @Override
    public Light deserialize(JSONObject object){
        
    }
    
    @Override
    public JSONObject serialize(){
        try{
            JSONObject out = new JSONObject();
            out.put("position",createVector3fEntry(this.position));
            out.put("color", createVector3fEntry(this.color));
            out.put("id", this.getID());
            out.put("intensity", this.intensity);
            return out;
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
