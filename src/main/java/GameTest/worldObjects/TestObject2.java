package GameTest.worldObjects;

import Engine.MainClass;
import Engine.models.WorldObject;
import Engine.resourceLoading.Texture;
import org.joml.Vector3f;
import org.json.JSONObject;

public class TestObject2 extends WorldObject {



    public TestObject2(Vector3f position, Vector3f rotation, Vector3f scale) {
        super(MainClass.loader.getNormalModelFromResource("worldModel", new Texture("test.png")),position, rotation, scale);
    }


    @Override
    public JSONObject serialize() {
        return null;
    }

    @Override
    public WorldObject deserialize(JSONObject object) {
        return null;
    }
}
