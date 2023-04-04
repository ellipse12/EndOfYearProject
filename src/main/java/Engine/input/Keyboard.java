package Engine.input;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWKeyCallback;

import java.util.*;

public final class Keyboard {

    private static Map<Integer, Integer> keyMap = new HashMap<>();

    private static Set<KeyListener> listeners = new HashSet<>();

    private static GLFWKeyCallback keyCallback;



    public static void create(long window){
        keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if((keyMap.getOrDefault(key, -1) == GLFW_PRESS || keyMap.getOrDefault(key,-1) == GLFW_REPEAT) && action == GLFW_RELEASE){
                    listeners.forEach(listener -> listener.onKeyEvent(key, KeyListener.KEY_RELEASED, mods));
                }else{
                    listeners.forEach(listener -> listener.onKeyEvent(key, action, mods));
                }
                keyMap.put(key, action);


            }
        };
        glfwSetKeyCallback(window, keyCallback);
    }

    public static boolean isKeyDown(int key){
        int result = keyMap.getOrDefault(key, -1);
        return result == GLFW_PRESS || result == GLFW_REPEAT;
    }

    public static void addKeyListener(KeyListener listener){
        listeners.add(listener);
    }




}
