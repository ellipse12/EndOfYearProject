import Engine.DisplayManager;
import Engine.TestLoop;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {

        public static void main(String[] args){
            Main main = new Main();
            main.init();
            long window = new DisplayManager().create();
            GL.createCapabilities();
            glClearColor(0.0f,0.0f,1.0f, 0.0f);
            while(!glfwWindowShouldClose(window)){
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