package Engine;

import Engine.input.KeyListener;
import Engine.input.Keyboard;
import Engine.input.Mouse;
import Engine.rendering.Window;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;

public class MainClass {
        public static Window window = new Window(800, 400, "test");

        public static boolean paused = false;

        public static void main(String[] args){
            MainClass main = new MainClass();
            main.init();
            window.create();
            GL.createCapabilities();
            glClearColor(0.0f,0.0f,1.0f, 0.0f);
            Keyboard.addKeyListener((key, action, mod) -> {
                if(key == GLFW_KEY_ESCAPE && action == KeyListener.KEY_RELEASED){
                    paused = !paused;
                }
            });
            while(!glfwWindowShouldClose(window.getHandle())){
                if(paused){
                    glfwSetInputMode(window.getHandle(), GLFW_CURSOR, GLFW_CURSOR_NORMAL);
                }else {
                    glfwSetInputMode(window.getHandle(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);
                }
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