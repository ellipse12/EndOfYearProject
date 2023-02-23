package Engine;

import Engine.models.Model;
import Engine.rendering.Renderer;
import Engine.resourceLoading.Loader;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.*;

public class TestLoop {
    static Renderer renderer = new Renderer();
    static Loader loader = new Loader();
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
    public static void loop(long window){
        renderer.init();
        renderer.render(model);


        glfwSwapBuffers(window);

        glfwPollEvents();
    }

    public static void cleanUp() {
        loader.cleanUp();

    }
}
