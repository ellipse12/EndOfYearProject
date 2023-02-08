import Engine.DisplayManager;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {

        public static void main(String[] args){
            Main main = new Main();
            main.init();
            long window = new DisplayManager().create();
            glClearColor(0.0f,0.0f,1.0f, 0.0f);
            while(glfwWindowShouldClose(window)){
                main.loop(window);
            }

        }
        public  void init(){
            GLFWErrorCallback.createPrint(System.err).set();
            if ( !glfwInit() ) {
                throw new IllegalStateException("Unable to initialize GLFW");
            }
            GL.createCapabilities();
        }
        public  void loop(long window){
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

                glfwSwapBuffers(window);

                glfwPollEvents();
        }

}