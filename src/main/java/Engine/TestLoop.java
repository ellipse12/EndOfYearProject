package Engine;

import Engine.input.Keyboard;
import Engine.input.Mouse;
import Engine.models.Attenuation;
import Engine.models.Light;
import Engine.models.Model;
import Engine.models.WorldObject;
import Engine.rendering.Camera;
import Engine.rendering.Renderer;
import Engine.rendering.Window;
import Engine.resourceLoading.Loader;
import Engine.resourceLoading.Texture;
import Engine.resourceLoading.objectLoading.OBJFileLoader;
import Engine.shaders.StaticShader;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;
/*
This is a testing class.
 */
public class TestLoop {

    static Loader loader = new Loader();

    static StaticShader shader = new StaticShader();
    static Renderer renderer = new Renderer(shader);
    static Model model = loader.getModelFromResource("test", new Texture("test.png"));



    static WorldObject object = new WorldObject(model, new Vector3f(0,0,-5), new Vector3f(), new Vector3f(1,1, 1));
    static WorldObject object1 = new WorldObject(model, new Vector3f(-9,1,-10), new Vector3f(), new Vector3f(1,1, 1));

    static Camera camera = new Camera();
    public static void loop(Window window){


        renderer.init();
        //object.increaseRotation(new Vector3f(0,1,0));

        camera.update();

        renderer.render(object, camera);

        Mouse.poll();
        glfwSwapBuffers(window.getHandle());

        glfwPollEvents();
        window.update();
    }

    public static void cleanUp() {
        loader.cleanUp();
        shader.cleanUp();
    }
}