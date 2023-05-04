package Engine.rendering;

import Engine.MainClass;
import Engine.Scene;
import Engine.models.Attenuation;
import Engine.models.Light;
import Engine.models.Model;
import Engine.models.WorldObject;
import Engine.resourceLoading.Texture;
import Engine.shaders.ShaderProgram;
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
    private StaticShader shader;



    public DefaultRenderer(StaticShader shader) {
        this.shader = shader;

    }

    /**
     * called once every game cycle
     */
    public void init(){
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1f, 1f, 1f, 1f);
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
        //TODO: allow for multiple objects/ add Render interface.
        shader.start();
        GL30.glBindVertexArray(object.getModel().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(2);
        GL20.glEnableVertexAttribArray(1);



        Texture texture = object.getModel().getTexture();

        Matrix4f transform = Maths.createTransformationMatrix(object.getPosition(), object.getRotation(), object.getScale());
        Matrix4f view = Maths.getModelViewMatrix(object, Maths.createViewMatrix(camera));


        shader.setUniform("transformationMatrix", transform);
        shader.setUniform("viewMatrix", view);
        shader.setUniform("material", object.getModel().getMaterial());
        shader.setUniform("specularPower", 0.1f);
        shader.setUniform("ambientLight", new Vector3f(0.5f,0.5f,0.5f));
        //shader.setUniform("light", new Light(new Vector3f(1,1,1), new Vector3f(-500f, 1000f, 0), 1f, new Attenuation(0.05f, 0.05f, 0.005f)));
        shader.setUniform("camera_position", camera.getPosition());
        shader.setUniform("numLights", 3);
        shader.setUniform("lights", lights);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, object.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        shader.stop();
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);

    }

    @Override
    public void render(Scene scene) {
        for(WorldObject object: scene.getObjects()){
            render(object, scene.getCamera(), scene.getLights());
        }
    }

    @Override
    public void update(Scene scene) {
        scene.getCamera().update();
        init();
    }

    @Override
    public void cleanUp() {

    }
}
