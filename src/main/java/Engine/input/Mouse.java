package Engine.input;


import Engine.rendering.Window;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorEnterCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public final class Mouse {
    private static double x, y;

    private static double lastX, lastY;
    private static double dx, dy;

    private static boolean inWindow = false;

    private static long window;

    private static boolean isCaptured = false;

    private static Map<Integer, Integer> keyMap = new HashMap<>();

    private static GLFWCursorPosCallback posCallback;
    private static GLFWMouseButtonCallback buttonCallback;

    private static GLFWCursorEnterCallback enterCallback;
    private static boolean shown = false;

    /**
     * sets the mouse to the current context and sets some callbacks to get things like position, what button is down, etc.
     * @param windoww the current window instance
     */
    public static void create(Window windoww) {

        long window = windoww.getHandle();
        posCallback = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                x = xpos;
                y = ypos;

            }
        };

        buttonCallback = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                if(action == 1) {
                    keyMap.put(button, action);
                }else{
                    keyMap.put(button, 2);
                }



            }
        };

        enterCallback = new GLFWCursorEnterCallback() {
            @Override
            public void invoke(long window, boolean entered) {
                inWindow = entered;
            }
        };



        GLFW.glfwSetMouseButtonCallback(window, buttonCallback);
        GLFW.glfwSetCursorPosCallback(window, posCallback);
        GLFW.glfwSetCursorEnterCallback(window, enterCallback);
        Mouse.window = window;
    }


    /**
     * called every frame to allow for storing the change of the position of the mouse
     */
    public static void poll(){
        dx = 0;
        dy = 0;
        if(lastX > 0 && lastY > 0){
           dx = x-lastX;
           dy = y-lastY;
        }
        lastX = x;
        lastY= y;
        if(shown){
            glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
        }else{
            glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

        }
    }


    /**
     * @param button the button to check
     * @return if the given button is pressed on the mouse
     */
    public static boolean isButtonDown(int button){
        return keyMap.getOrDefault(button, 0) == 1;
    }


    /**
     * @param button the button to check
     * @return if the given button is released on the mouse
     */
    public static boolean isButtonReleased(int button){
        boolean released = keyMap.getOrDefault(button, 0) == 2;
        if(released){
            keyMap.put(button, 0);
        }
        return released;
    }


    public static double getX() {
        return x;
    }

    public static double getY() {
        return y;
    }

    public static double getDX() {
        double out = dx;
        dx = 0;
        return out;
    }

    public static double getDY() {
        double out = dy;
        dy = 0;
        return out;
    }

    public static boolean isInWindow(){
        return inWindow;
    }

    public static void setShown(boolean shown){
        Mouse.shown = shown;
    }

    public static boolean isShown(){
        return shown;
    }
}
