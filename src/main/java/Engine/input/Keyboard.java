package Engine.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Keyboard {

    private static Map<Integer, Integer> keyMap = new HashMap<>();

    private static GLFWKeyCallback keyCallback;

    public static void create(long window){
        keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if(keyMap.getOrDefault(key, -1) == 1 && action == 0){
                    keyMap.put(key, 2);
                }else {
                    keyMap.put(key, action);
                }

            }
        };
        GLFW.glfwSetKeyCallback(window, keyCallback);
    }

    public static boolean isKeyDown(int key){
        return keyMap.getOrDefault(key, -1) == 1;
    }
    public static boolean isKeyReleased(int key){
        return keyMap.getOrDefault(key, -1) == 2;
    }


}
