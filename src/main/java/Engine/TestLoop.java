package Engine;

import Engine.guiRendering.GUI;
import Engine.guiRendering.GUIRenderer;
import Engine.models.Light;
import Engine.models.WorldObject;
import Engine.rendering.Camera;
import Engine.rendering.DefaultRenderer;
import Engine.rendering.Renderer;
import Engine.rendering.Window;
import Engine.resourceLoading.JsonParser;
import Engine.resourceLoading.Texture;
import Engine.shaders.StaticShader;
import GameTest.Player;
import GameTest.worldObjects.TestObject;
import GameTest.worldObjects.TestObject2;
import jdk.nashorn.internal.parser.JSONParser;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
/*
This is a testing class.
 */
public class TestLoop {



    static Camera camera = new Camera(new Vector3f(0, -3, 0), new Vector3f());
    static Player player = new Player(camera);
    public static Scene scene = new Scene(player);



    public static void init(){
        addObjects();
        scene.addRenderer(new DefaultRenderer(new StaticShader()));
        scene.addRenderer(new GUIRenderer());
        scene.addGUI(new GUI(new Vector2f(), new Vector2f(1), new Texture("cursor.png")));

    }

    private static void addObjects(){
            try {
                scene = JsonParser.parseFile("save.json", MainClass.loader);
            } catch (IOException | JSONException e) {
                throw new RuntimeException(e);
            }
    }
    public static void loop(Window window){
        for(Renderer renderer : scene.getRenderers()){
            renderer.update(scene);
            renderer.render(scene);
        }

        glfwSwapBuffers(window.getHandle());
        glfwPollEvents();
        window.update();
    }

    public static void cleanUp() {
        MainClass.loader.cleanUp();
        JsonParser.createSaveFile(scene, "save");
    }
}