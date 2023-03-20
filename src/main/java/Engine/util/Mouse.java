package Engine.util;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorEnterCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import java.util.HashMap;
import java.util.Map;

public class Mouse {
    private static double x, y;
    private static double dx, dy;

    private static Map<Integer, Integer> keyMap = new HashMap<>();

    private static GLFWCursorPosCallback posCallback;
    private static GLFWMouseButtonCallback buttonCallback;


    public static void create(long window) {
        posCallback = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                dx = Math.abs(x-xpos);
                dy = Math.abs(y - ypos);
                x = xpos;
                y = ypos;

            }
        };

        buttonCallback = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                keyMap.put(button, action);

            }
        };

        GLFW.glfwSetMouseButtonCallback(window, buttonCallback);
        GLFW.glfwSetCursorPosCallback(window, posCallback);
    }



    public static double getX() {
        return x;
    }

    public static double getY() {
        return y;
    }

    public static double getDx() {
        return dx;
    }

    public static double getDy() {
        return dy;
    }
}
