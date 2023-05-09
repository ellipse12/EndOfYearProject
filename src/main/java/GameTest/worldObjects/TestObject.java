package GameTest.worldObjects;

import Engine.MainClass;
import Engine.models.WorldObject;
import Engine.resourceLoading.Texture;
import org.joml.Vector3f;
import org.json.JSONObject;

public class TestObject extends WorldObject {



    public TestObject(Vector3f position, Vector3f rotation, Vector3f scale) {
        super(MainClass.loader.getNormalModelFromResource("test", new Texture("test.png")), position, rotation, scale, "test1");
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
