package Engine;

import Engine.models.Model;
import Engine.models.WorldObject;
import Engine.rendering.Renderer;
import Engine.resourceLoading.Loader;
import Engine.shaders.StaticShader;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;

public class TestLoop {

    static Loader loader = new Loader();

    static StaticShader shader = new StaticShader();
    static Renderer renderer = new Renderer(shader);
    static float[] vertices = {
            // Left bottom triangle
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            // Right top triangle
            0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f,
            -0.5f, 0.5f, 0f
    };
    static Model model = loader.loadToVAO(vertices);
    static WorldObject object = new WorldObject(model, new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(1,1, 1));
    public static void loop(long window){
        renderer.init();
        renderer.render(object);


        glfwSwapBuffers(window);

        glfwPollEvents();
    }

    public static void cleanUp() {
        loader.cleanUp();
        shader.cleanUp();
    }
}