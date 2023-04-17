package Engine.input;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWKeyCallback;

import java.util.*;

/**
 * Contains a map of the active keys, and provides a way to register key listeners.
 */
public final class Keyboard {

    private static Map<Integer, Integer> keyMap = new HashMap<>();

    private static Set<KeyListener> listeners = new HashSet<>();

    private static GLFWKeyCallback keyCallback;


    /**
     * exposes this class as a GLFWKeyCallback for GLFW and adds the keys to the keymap, and updates the listeners.
     * @param window the window handle for the current context
     */
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

    /**
     * @param key the integer representation of the keypress.
     * @return
     */
    public static boolean isKeyDown(int key){
        int result = keyMap.getOrDefault(key, -1);
        return result == GLFW_PRESS || result == GLFW_REPEAT;
    }

    /**
     * @param listener the listener to add.
     */
    public static void addKeyListener(KeyListener listener){
        listeners.add(listener);
    }




}
