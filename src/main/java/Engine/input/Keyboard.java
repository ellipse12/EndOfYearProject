package Engine.input;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWKeyCallback;

import java.util.*;

public final class Keyboard {

    private static Map<Integer, Integer> keyMap = new HashMap<>();

    private static Set<KeyListener> listeners = new HashSet<>();

    private static GLFWKeyCallback keyCallback;

    public static final int KEY_RELEASED = -3;

    public static void create(long window){
        keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if((keyMap.getOrDefault(key, -1) == GLFW_PRESS || keyMap.getOrDefault(key,-1) == GLFW_REPEAT) && action == GLFW_RELEASE){

                    keyMap.put(key, KEY_RELEASED);

                }else {
                    keyMap.put(key, action);
                }
                if(key == GLFW_KEY_ESCAPE){
                    glfwSetWindowShouldClose(window, true);
                }
                listeners.forEach(listener -> listener.onKeyEvent(key, action, mods));

            }
        };
        glfwSetKeyCallback(window, keyCallback);
    }

    public static boolean isKeyDown(int key){
        return keyMap.getOrDefault(key, -1) == GLFW_PRESS;
    }

    public static void addKeyListener(KeyListener listener){
        listeners.add(listener);
    }

    public static boolean isKeyReleased(int key){
        boolean released = keyMap.getOrDefault(key, -1) == KEY_RELEASED;
        if(released){
            keyMap.put(key, 0);
        }
        return released;
    }


}
