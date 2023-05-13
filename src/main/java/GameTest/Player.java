package GameTest;

import Engine.MainClass;
import Engine.Scene;
import Engine.TestLoop;
import Engine.input.Mouse;
import Engine.rendering.Camera;
import Engine.rendering.Updateable;
import Engine.saving.JsonSerializable;
import org.joml.Vector3f;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.GregorianCalendar;

import static Engine.input.Keyboard.isKeyDown;
import static org.lwjgl.glfw.GLFW.*;

public class Player implements JsonSerializable<Player>, Updateable {

    private float speed = 0.4f;

    private static final float GRAVITY = -0.005f;

    private static final float JUMP_POWER = 0.2f;

    private Camera camera;

    public Player(Camera camera) {
        this.camera = camera;

    }

    private float upwardsSpeed = 0;
    private boolean isInAir = false;
    @Override
    public void update(Scene scene){

        if(!MainClass.paused) {


            if (isKeyDown(GLFW_KEY_W)) {
                camera.move(0, 0, -speed);
            }
            if (isKeyDown(GLFW_KEY_S)) {
                camera.move(0, 0, speed);
            }
            if (isKeyDown(GLFW_KEY_A)) {
                camera.move(-speed, 0, 0);
            }
            if (isKeyDown(GLFW_KEY_D)) {
                camera.move(speed, 0, 0);
            }
            if (isKeyDown(GLFW_KEY_SPACE)) {
                jump();
            }

            double max = 100f;
            float offset = 0.0001f;
            Vector3f po = new Vector3f((float) (Mouse.getDY() * 0.08f), (float) (Mouse.getDX() * 0.08f), 0);
            camera.rotate(po);


            upwardsSpeed += GRAVITY;
            camera.move(0, upwardsSpeed, 0);
            if (camera.getPosition().y < 0) {
                upwardsSpeed = 0;
                isInAir = false;
                camera.getPosition().y = 0;
            }
        }

    }

    /**
     * jumps
     */
    private void jump(){
        if(!isInAir){
            upwardsSpeed = JUMP_POWER;
            isInAir = true;
        }
    }

    public Camera getCamera() {
        return camera;
    }

    @Override
    public JSONObject serialize() {
        JSONObject out = new JSONObject();
        try {
            out.put("position", createVector3fEntry(this.camera.getPosition()));

        out.put("rotation", createVector3fEntry(this.camera.getRotation()));
        out.put("inAir", isInAir);
        out.put("type", "player");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return out;
    }

    @Override
    public Player deserialize(JSONObject object) {
        try {
            this.camera.setPosition(parseVector3fEntry(object.getJSONArray("position")));

        this.camera.setRotation(parseVector3fEntry(object.getJSONArray("rotation")));
        this.isInAir = object.getBoolean("inAir");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return this;

    }
}
