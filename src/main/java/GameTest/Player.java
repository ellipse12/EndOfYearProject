package GameTest;

import Engine.input.Mouse;
import Engine.rendering.Camera;
import org.joml.Vector3f;

import java.util.GregorianCalendar;

import static Engine.input.Keyboard.isKeyDown;
import static org.lwjgl.glfw.GLFW.*;

public class Player{

    private float speed = 0.1f;

    private static final float GRAVITY = -0.005f;

    private static final float JUMP_POWER = 0.14f;

    private Camera camera;

    public Player(Camera camera) {
        this.camera = camera;

    }

    private float upwardsSpeed = 0;
    private boolean isInAir = false;

    public void update(){
        //TODO move to separate player class


        if(isKeyDown(GLFW_KEY_W)){
            camera.move(0, 0, -speed);
        }
        if(isKeyDown(GLFW_KEY_S)){
            camera.move(0,0,speed);
        }
        if(isKeyDown(GLFW_KEY_A)){
            camera.move(-speed, 0, 0);
        }
        if(isKeyDown(GLFW_KEY_D)){
            camera.move(speed,0,0);
        }
        if(isKeyDown(GLFW_KEY_SPACE)){
            jump();
        }

        double max = 100f;
        float offset = 0.0001f;
        Vector3f po = new Vector3f((float) (Mouse.getDY() * 0.08f), (float) (Mouse.getDX() * 0.08f), 0);
        camera.rotate(po);



        upwardsSpeed += GRAVITY;
        camera.move(0, upwardsSpeed, 0);
        if(camera.getPosition().y < -3){
            upwardsSpeed = 0;
            isInAir = false;
            camera.getPosition().y = -3;
        }


    }

    private void jump(){
        if(!isInAir){
            upwardsSpeed = JUMP_POWER;
            isInAir = true;
        }
    }

    public Camera getCamera() {
        return camera;
    }
}
