package Engine.rendering;

import Engine.MainClass;
import Engine.models.Model;
import Engine.models.WorldObject;
import Engine.resourceLoading.Texture;
import Engine.shaders.ShaderProgram;
import Engine.shaders.StaticShader;
import Engine.util.Maths;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Renderer {
    private StaticShader shader;

    private Matrix4f viewMatrix;
    public Renderer(StaticShader shader) {
        this.shader = shader;
    }

    /**
     * @param camera called once every game cycle
     */
    public void init(Camera camera){
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1f, 1f, 1f, 1f);
        shader.start();
        Matrix4f projectionMatrix = Maths.createProjectionMatrix(MainClass.window);
        shader.loadProjectionMatrix(projectionMatrix);


        shader.stop();

    }

    /**
     * renders Individual objects to the screen
     * @param object the object to render
     * @param camera the camera to render from
     */
    public void render(WorldObject object, Camera camera){

        shader.start();
        GL30.glBindVertexArray(object.getModel().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);


        Texture texture = object.getModel().getTexture();

        Matrix4f transform = Maths.createTransformationMatrix(object.getPosition(), object.getRotation(), object.getScale());
        shader.loadTransformationMatrix(transform);
        shader.loadViewMatrix(object, camera);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, object.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        shader.stop();
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);

    }

}
