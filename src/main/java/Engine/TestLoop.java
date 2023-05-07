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
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

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
        scene.addRenderer(new DefaultRenderer(new StaticShader()));
        scene.addRenderer(new GUIRenderer());
        scene.addLight(new Light(new Vector3f(1f,0f,1f), new Vector3f(-5f, 10f, 0), 3f));
        scene.addLight(new Light(new Vector3f(1,1,1), new Vector3f(500, 100, 0), 1f));
        scene.addGUI(new GUI(new Vector2f(), new Vector2f(1), new Texture("test.png")));
        JsonParser.createSaveFile(new WorldObject(MainClass.loader.getNormalModelFromResource("worldModel", new Texture("test.png")), new Vector3f(-6, -5, -1), new Vector3f(), new Vector3f(1)), "worldModel", "worldModel");
        addObjects();
    }

    private static void addObjects(){


        for(String string: JsonParser.getFilesInDirectory("saves")){
            try {
                scene.addObject(JsonParser.parseFile(string, MainClass.loader));
            }catch (JSONException | IOException e) {
                throw new RuntimeException(e);
            }
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
    }
}