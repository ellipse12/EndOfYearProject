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

    /**
     * moves the camera by a specified value
     * @param offX the offset in the X direction
     * @param offY the offset in the Y direction
     * @param offZ the offset in the Z direction
     */
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

    /**
     * rotates the camera by a specified amount
     * @param rotation the rotation
     */
    public void rotate(Vector3f rotation){
        this.rotation.x = this.rotation.x + rotation.x;
        this.rotation.y = this.rotation.y + rotation.y;
        this.rotation.z = this.rotation.z + rotation.z;

    }
    private float speed= 0.1f;

    /**
     * updates the camera every frame
     */
    public void update(){
        //TODO move to separate player class
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
        if(true) {
            double max = 100f;

            Vector3f po = new Vector3f((float) (Mouse.getDY() * 0.08f), (float) (Mouse.getDX() * 0.08f), 0);

            rotate(po);
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
