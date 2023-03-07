package Engine;

import Engine.models.Model;
import Engine.models.WorldObject;
import Engine.rendering.Renderer;
import Engine.rendering.Window;
import Engine.resourceLoading.Loader;
import Engine.shaders.StaticShader;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;

public class TestLoop {

    static Loader loader = new Loader();

    static StaticShader shader = new StaticShader();
    static Renderer renderer = new Renderer(shader);
    static float[] vertices = {
            -0.5f,0.5f,0,
            -0.5f,-0.5f,0,
            0.5f,-0.5f,0,
            0.5f,0.5f,0,

            -0.5f,0.5f,1,
            -0.5f,-0.5f,1,
            0.5f,-0.5f,1,
            0.5f,0.5f,1,

            0.5f,0.5f,0,
            0.5f,-0.5f,0,
            0.5f,-0.5f,1,
            0.5f,0.5f,1,

            -0.5f,0.5f,0,
            -0.5f,-0.5f,0,
            -0.5f,-0.5f,1,
            -0.5f,0.5f,1,

            -0.5f,0.5f,1,
            -0.5f,0.5f,0,
            0.5f,0.5f,0,
            0.5f,0.5f,1,

            -0.5f,-0.5f,1,
            -0.5f,-0.5f,0,
            0.5f,-0.5f,0,
            0.5f,-0.5f,1

    };

    static int[] indices = {
            0,1,3,
            3,1,2,
            4,5,7,
            7,5,6,
            8,9,11,
            11,9,10,
            12,13,15,
            15,13,14,
            16,17,19,
            19,17,18,
            20,21,23,
            23,21,22

    };
    static Model model = loader.loadToVAO(vertices, indices);
    static WorldObject object = new WorldObject(model, new Vector3f(0,0,-5), new Vector3f(0,0,0), new Vector3f(1,1, 1));
    public static void loop(Window window){

        renderer.init();
        object.increaseRotation(new Vector3f(0,1,0));
        //object.increaseScale(.01f);
        renderer.render(object);


        glfwSwapBuffers(window.getHandle());

        glfwPollEvents();
        window.update();
    }

    public static void cleanUp() {
        loader.cleanUp();
        shader.cleanUp();
    }
}