package Engine.rendering;

import Engine.models.Model;
import Engine.models.WorldObject;
import Engine.shaders.ShaderProgram;
import Engine.shaders.StaticShader;
import Engine.util.Maths;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Renderer {
    private StaticShader shader;
    public Renderer(StaticShader shader) {
        this.shader = shader;
    }

    public void init(){
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1f, 0f, 0f, 1f);
    }

    public void render(WorldObject object){
        shader.start();
        GL30.glBindVertexArray(object.getModel().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        Matrix4f transform = Maths.createTransformationMatrix(object.getPosition(), object.getScale());
        shader.loadTransformationMatrix(transform);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, object.getModel().getVertexCount());
        shader.stop();
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);

    }
}
