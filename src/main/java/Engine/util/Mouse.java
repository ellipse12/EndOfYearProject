package Engine.util;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class Mouse {
    private static double x, y;
    private static double dx, dy;


    private static GLFWCursorPosCallback posCallback;


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
