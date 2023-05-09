package Engine;

import Engine.input.KeyListener;
import Engine.input.Keyboard;
import Engine.rendering.Window;
import Engine.resourceLoading.Loader;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;

public class MainClass {
        public static Window window = new Window(800, 400, "test");

        public static Loader loader = new Loader();

        public static boolean paused = false;



        public static void main(String[] args){
            MainClass main = new MainClass();
            main.init();
            window.create();

            GL.createCapabilities();

            Keyboard.addKeyListener((key, action, mod) -> {
                if(key == GLFW_KEY_E && action == KeyListener.KEY_RELEASED){
                    paused = !paused;
                }
            });
            TestLoop.init();
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