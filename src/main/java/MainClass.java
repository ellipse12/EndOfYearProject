import Engine.rendering.Window;
import Engine.TestLoop;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class MainClass {
        public static Window window = new Window(800, 400, "test");

        public static void main(String[] args){
            MainClass main = new MainClass();
            main.init();
            window.create();
            GL.createCapabilities();
            window.createCallbacks();
            glClearColor(0.0f,0.0f,1.0f, 0.0f);
            while(!glfwWindowShouldClose(window.getHandle())){
                TestLoop.loop(window);
            }
            TestLoop.cleanUp();

        }



    public  void init(){
            GLFWErrorCallback.createPrint(System.err).set();
            if ( !glfwInit() ) {
                throw new IllegalStateException("Unable to initialize GLFW");
            }

        }


}