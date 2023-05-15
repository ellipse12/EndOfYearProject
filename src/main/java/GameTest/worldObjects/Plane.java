package GameTest.worldObjects;

import Engine.MainClass;
import Engine.Scene;
import Engine.models.Model;
import Engine.models.WorldObject;
import Engine.resourceLoading.Texture;
import org.joml.Vector3f;

public class Plane extends WorldObject {


    public Plane(Vector3f position, Vector3f rotation, Vector3f scale) {
        super(MainClass.loader.getNormalModelFromResource("worldModel", new Texture("plane.png")), position, rotation, scale, "plane");
    }

}
