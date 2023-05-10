package Engine.rendering;

import Engine.MainClass;
import Engine.Scene;
import Engine.models.Light;
import Engine.models.WorldObject;
import Engine.resourceLoading.Texture;
import Engine.shaders.StaticShader;
import Engine.util.Maths;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;

public class DefaultRenderer implements Renderer{
    private final StaticShader shader;



    public DefaultRenderer(StaticShader shader) {
        this.shader = shader;

    }

    /**
     * called once every game cycle
     */
    public void init(){
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.8f, 0.8f, 0.8f, 1f); //the background color
        shader.start();
        Matrix4f projectionMatrix = Maths.createProjectionMatrix(MainClass.window);
        shader.setUniform("projectionMatrix", projectionMatrix);


        shader.stop();

    }

    /**
     * renders Individual objects to the screen
     * @param object the object to render
     * @param camera the camera to render from
     */
    public void render(WorldObject object, Camera camera, ArrayList<Light> lights){
        
        shader.start();
        GL30.glBindVertexArray(object.getModel().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(2);
        GL20.glEnableVertexAttribArray(1);



        Texture texture = object.getModel().getTexture();

        Matrix4f transform = Maths.createTransformationMatrix(object.getPosition(), object.getRotation(), object.getScale());


        shader.setUniform("transformationMatrix", transform);

        shader.setUniform("material", object.getModel().getMaterial());
        shader.setUniform("specularPower", 1f);
        shader.setUniform("ambientLight", new Vector3f(.7f,.7f,.7f));

        shader.setUniform("camera_position", camera.getPosition());
        shader.setUniform("numLights", 3);
        shader.setUniform("lights", lights.toArray(new Light[0]));
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, object.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        shader.stop();
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);

    }

    private ArrayList<Light> transformLights(ArrayList<Light> lights, Matrix4f viewMatrix){
        ArrayList<Light> out = new ArrayList<>();
        for(Light lightt : lights){
            out.add(new Light(lightt.getColor(), lightt.getPosition(), lightt.getIntensity()));
        }
        for(Light light : out){
            Vector3f position = light.getPosition();
            Vector4f uax = new Vector4f(light.getPosition(), 1);
            uax.mul(viewMatrix);
            position.x = uax.x;
            position.y = uax.y;
            position.z = uax.z;
        }
        return out;
    }

    @Override
    public void render(Scene scene) {
        shader.start();
        Matrix4f view = Maths.createViewMatrix(scene.getCamera());
        shader.setUniform("viewMatrix", view);
        shader.stop();

        for(WorldObject object: scene.getObjects()){ //iterate over all of the objects in the scene
            render(object, scene.getCamera(), scene.getLights()); //render each individual object.
        }
    }

    @Override
    public void update(Scene scene) {
        if(!MainClass.paused) {
            scene.getPlayer().update(); //updates the player, which checks inputs and moves around
        }

        init();
    }

    @Override
    public void cleanUp() {

    }
}
