package Engine.rendering;
import Engine.input.KeyListener;
import Engine.input.Keyboard;
import Engine.input.Mouse;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {
    private int width, height;
    private long window;
    private String title;
    private boolean resized;

    private GLFWWindowSizeCallback sizeCallback;

    /**
     * @param width the initial width of the window
     * @param height the initial height of the window
     * @param title the title of the window
     */
    public Window(int width, int height, String title) {
            this.width = width;
            this.height = height;
            this.title = title;
    }

    /**
     * creates the window with some default settings
     */
    public void create(){
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_SCALE_TO_MONITOR, GLFW_TRUE);
        glfwWindowHint(GLFW_SAMPLES, 16);

        long window = glfwCreateWindow(width, height, title, NULL, NULL);
        if(window == NULL){
            throw new RuntimeException("Unable to create window");
        }



        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*
            glfwGetWindowSize(window, pWidth, pHeight);

            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(window);

        glfwSwapInterval(1);
        glfwShowWindow(window);
        this.window = window;

        createCallbacks();


    }

    /**
     * creates various callbacks that are called on different events
     */
    public void createCallbacks(){
            sizeCallback = new GLFWWindowSizeCallback() {
                @Override
                public void invoke(long window, int pWidth, int pHeight) {
                    width = pWidth;
                    height = pHeight;
                    resized = true;
                }
            };
            GLFW.glfwSetWindowSizeCallback(window, sizeCallback);
        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

        Mouse.create(this);
        Keyboard.create(window);

        Keyboard.addKeyListener((key, action, mod) -> {
            if(key == GLFW_KEY_E && action == KeyListener.KEY_RELEASED){
                glfwSetWindowShouldClose(window, true);
            }
        });


    }

    public long getHandle() {
        return window;
    }

    /**
     * updates the window if it was resized
     */
    public void update(){
        if(resized){
            GL11.glViewport(0,0,width, height);
        }
    }


    public boolean isResized() {
        return resized;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
