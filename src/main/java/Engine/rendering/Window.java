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

    public Window(int width, int height, String title) {
            this.width = width;
            this.height = height;
            this.title = title;
    }

    public void create(){
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_SCALE_TO_MONITOR, GLFW_TRUE);

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

        Mouse.create(window);
        Keyboard.create(window);

        Keyboard.addKeyListener((key, action, mod) -> {
            if(key == GLFW_KEY_ESCAPE && action == KeyListener.KEY_RELEASED){
                glfwSetWindowShouldClose(window, true);
            }
        });

    }

    public long getHandle() {
        return window;
    }
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
