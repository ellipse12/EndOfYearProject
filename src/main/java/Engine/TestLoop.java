package Engine;

import Engine.guiRendering.GUI;
import Engine.guiRendering.GUIRenderer;
import Engine.models.Light;
import Engine.registration.Registry;
import Engine.rendering.Camera;
import Engine.rendering.DefaultRenderer;
import Engine.rendering.Renderer;
import Engine.rendering.Window;
import Engine.saving.JsonParser;
import Engine.resourceLoading.Texture;
import Engine.shaders.StaticShader;
import GameTest.Player;
import GameTest.worldObjects.Plane;
import GameTest.worldObjects.TestObject;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.json.JSONException;

import java.io.IOException;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
/*
This is a testing class.
 */
public class TestLoop {



    static Camera camera = new Camera(new Vector3f(0, -3, 0), new Vector3f());
    static Player player = new Player(camera);
    public static Scene scene = new Scene(player);


    /**
     * run on startup
     */
    public static void init(){
        registerObjects();
        scene.addRenderer(new DefaultRenderer(new StaticShader())); //has to be added otherwise nothing will render
        scene.addRenderer(new GUIRenderer());
        scene.addGUI(new GUI(new Vector2f(), new Vector2f(1), new Texture("cursor.png")));

        JsonParser.loadSave(scene, "save.json");

    }

    /**
     * registers all the objects
     */
    private static void registerObjects(){
        Registry.registerObject("test", ()->new TestObject(new Vector3f(), new Vector3f(), new Vector3f(1)));
        Registry.registerObject("plane", ()->new Plane(new Vector3f(0,-3,0), new Vector3f(), new Vector3f(10)));

    }


    /**
     * main loop
     * @param window the current window
     */
    public static void loop(Window window){
        for(Renderer renderer : scene.getRenderers()){//updates and renders everything
            renderer.update(scene);
            renderer.render(scene);
        }

        glfwSwapBuffers(window.getHandle());
        glfwPollEvents(); //needed for keyboard events
        window.update();
    }

    /**
     * cleans up everything/ run after the window is closed
     */
    public static void cleanUp() {
        MainClass.loader.cleanUp();
        //try {
            //JsonParser.save(scene, "save"); //saves the scene
        //} catch (IOException e) {
            //throw new RuntimeException(e);
        //}
    }
}
