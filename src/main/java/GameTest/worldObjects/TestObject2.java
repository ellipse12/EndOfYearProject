package GameTest.worldObjects;

import Engine.MainClass;
import Engine.Scene;
import Engine.models.WorldObject;
import Engine.rendering.Updateable;
import Engine.resourceLoading.Texture;
import org.joml.Vector3f;
import org.json.JSONObject;

public class TestObject2 extends WorldObject implements Updateable {



    public TestObject2(Vector3f position, Vector3f rotation, Vector3f scale) {
        super(MainClass.loader.getNormalModelFromResource("worldModel", new Texture("test.png")),position, rotation, scale, "test2");
    }


    @Override
    public void update(Scene scene) {

    }
}
