package GameTest.worldObjects;

import Engine.MainClass;
import Engine.TestLoop;
import Engine.models.Model;
import Engine.models.WorldObject;
import Engine.rendering.Camera;
import Engine.resourceLoading.Texture;
import Engine.shaders.StaticShader;
import Engine.util.Maths;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class TestObject2 extends WorldObject {

    private final StaticShader shader = new StaticShader();

    public TestObject2(Vector3f position, Vector3f rotation, Vector3f scale) {
        super(MainClass.loader.getNormalModelFromResource("worldModel", new Texture("test.png")),position, rotation, scale);
    }

    @Override
    public void render(Camera camera) {
        shader.start();
        GL30.glBindVertexArray(this.getModel().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(2);
        GL20.glEnableVertexAttribArray(1);
        Texture texture = this.getModel().getTexture();
        Matrix4f transform = Maths.createTransformationMatrix(this.getPosition(), this.getRotation(), this.getScale());
        Matrix4f view = Maths.getModelViewMatrix(this, Maths.createViewMatrix(camera));
        shader.setUniform("transformationMatrix", transform);
        shader.setUniform("viewMatrix", view);
        shader.setUniform("material", this.getModel().getMaterial());
//        shader.setUniform("specularPower", 0.1f);
//        shader.setUniform("ambientLight", new Vector3f(0.5f,0.5f,0.5f));
//        shader.setUniform("light", new Light(new Vector3f(0,0,1), new Vector3f(-500f, 1000f, 0), 1f, new Attenuation(0.05f, 0.05f, 0.005f)));
//        shader.setUniform("camera_position", camera.getPosition());
        TestLoop.scene.getLights().forEach(l->l.render(camera));
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, this.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        shader.stop();
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    @Override
    public void update(Camera camera) {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1f, 1f, 1f, 1f);
        shader.start();
        Matrix4f projectionMatrix = Maths.createProjectionMatrix(MainClass.window);
        shader.setUniform("projectionMatrix", projectionMatrix);


        shader.stop();
    }

    @Override
    public void cleanUp() {
        shader.cleanUp();
    }
}
