package Engine.rendering;

import Engine.MainClass;
import Engine.input.Mouse;
import org.joml.Vector3f;

import static Engine.input.Keyboard.isKeyDown;
import static org.lwjgl.glfw.GLFW.*;

public class Camera {
    private static final float TURN_SPEED = 5;
    private Vector3f position;
    private Vector3f rotation;

    public Camera() {
        position = new Vector3f(0,0,0);
        rotation = new Vector3f(0,0,0);
    }

    public Camera(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public void move(float offX, float offY, float offZ){
        if ( offZ != 0 ) {
            position.x += (float)Math.sin(Math.toRadians(rotation.y)) * -1.0f * offZ;
            position.z += (float)Math.cos(Math.toRadians(rotation.y)) * offZ;
        }
        if ( offX != 0) {
            position.x += (float)Math.sin(Math.toRadians(rotation.y - 90)) * -1.0f * offX;
            position.z += (float)Math.cos(Math.toRadians(rotation.y - 90)) * offX;
        }
        position.y += offY;
    }

    public void rotate(Vector3f rotation){
        this.rotation.add(rotation);
    }
    private float speed= 0.1f;

    public void update(Window window){

        if(isKeyDown(GLFW_KEY_W)){
            move(0, 0, -speed);
        }
        if(isKeyDown(GLFW_KEY_S)){
            move(0,0,speed);
        }
        if(isKeyDown(GLFW_KEY_A)){
            move(-speed, 0, 0);
        }
        if(isKeyDown(GLFW_KEY_D)){
            move(speed,0,0);
        }
        if(Mouse.isInWindow(window)) {
            rotate(new Vector3f((float) (Mouse.getDY() * 0.1f), (float) (Mouse.getDX() * 0.1f), 0));
        }




    }





    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }
}
