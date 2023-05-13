package GameTest.worldObjects;

import Engine.MainClass;
import Engine.Scene;
import Engine.models.WorldObject;
import Engine.resourceLoading.Texture;
import org.joml.Vector3f;
import org.json.JSONObject;

public class TestObject extends WorldObject {



    public TestObject(Vector3f position, Vector3f rotation, Vector3f scale) {
        super(MainClass.loader.getNormalModelFromResource("test2", new Texture("black.png")), position, rotation, scale, "test");

    }

    private float in = 0.04f;
    @Override
    public void update(Scene scene) {

        this.increasePosition(new Vector3f(0,in,0));
        if(this.getPosition().y > 3){
            in = -Math.abs(in);

        }if(this.getPosition().y < -3){
            in = Math.abs(in);
        }
        this.increaseRotation(new Vector3f(0,1,0));

    }
}
