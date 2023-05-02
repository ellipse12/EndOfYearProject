package Engine.guiRendering;

import Engine.MainClass;
import Engine.Scene;
import Engine.rendering.Camera;
import Engine.rendering.Renderer;
import Engine.resourceLoading.Texture;
import Engine.util.Maths;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class GUIRenderer implements Renderer {


    private GUIShader shader = new GUIShader();

    private static final GUI gui = new GUI(new Vector2f(), new Vector2f(1f), new Texture("test.png"));

    @Override
    public void render(Scene scene) {


        shader.start();
        GL30.glBindVertexArray(gui.getModel().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, gui.getTexture().getTextureID());
        float scaleX =  gui.getScale().x * (((float) gui.getTexture().getWidth() / MainClass.window.getWidth()));
        float scaleY = gui.getScale().y * (((float) gui.getTexture().getHeight() / MainClass.window.getHeight()));
        Matrix4f transform = Maths.createTransformationMatrix(gui.getPosition(), new Vector2f(scaleX, scaleY));

        shader.setUniform("transformation", transform);

        GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0 , gui.getModel().getVertexCount());
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
        shader.stop();

    }

    @Override
    public void update(Scene scene) {

    }

    @Override
    public void cleanUp() {

    }
}
