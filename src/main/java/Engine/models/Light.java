package Engine.models;

import Engine.MainClass;
import Engine.rendering.Camera;
import Engine.shaders.StaticShader;
import Engine.util.Maths;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

public class Light {

    private Vector3f color;
    private Vector3f position;
    private float intensity;
    private Attenuation attenuation;

    private final StaticShader shader = new StaticShader();

    public Light(Vector3f color, Vector3f position, float intensity, Attenuation attenuation) {
        this.color = color;
        this.position = position;
        this.intensity = intensity;
        this.attenuation = attenuation;
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

    public Attenuation getAttenuation() {
        return attenuation;
    }

    public void setAttenuation(Attenuation attenuation) {
        this.attenuation = attenuation;
    }


    public void render(Camera camera) {

        shader.setUniform("specularPower", 0.1f);
        shader.setUniform("ambientLight", new Vector3f(0.5f,0.5f,0.5f));
        shader.setUniform("light", this);
        shader.setUniform("camera_position", camera.getPosition());

    }
    int add = 1;


    public void update(Camera camera) {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1f, 1f, 1f, 1f);
        shader.start();
        Matrix4f projectionMatrix = Maths.createProjectionMatrix(MainClass.window);
        shader.setUniform("projectionMatrix", projectionMatrix);


        shader.stop();


    }


    public void cleanUp() {
        shader.cleanUp();
    }
}
