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
import GameTest.worldObjects.TestObject;
import GameTest.worldObjects.TestObject2;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;
/*
This is a testing class.
 */
public class TestLoop {

    public static Scene scene = new Scene();
    static Camera camera = new Camera();
    public static void init(){
        scene.addObject(new TestObject(new Vector3f(0, 0, -5), new Vector3f(), new Vector3f(1,1,1)));
        scene.addObject(new TestObject2(new Vector3f(0, -5, 0), new Vector3f(), new Vector3f(1,1,1)));
        scene.addLight(new Light(new Vector3f(52/255f,33/255f,255/255f), new Vector3f(-500f, 100f, 0), 3f, new Attenuation(0.05f, 0.05f, 0.005f)));
    }
    public static void loop(Window window){

        camera.update();

        scene.updateAll(camera);
        scene.renderAll(camera);
        glfwSwapBuffers(window.getHandle());
        glfwPollEvents();
        window.update();
    }

    public static void cleanUp() {
        MainClass.loader.cleanUp();
        scene.cleanAll();
    }
}