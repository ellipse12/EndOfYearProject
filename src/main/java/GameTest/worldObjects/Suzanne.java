package GameTest.worldObjects;

import Engine.MainClass;
import Engine.Scene;
import Engine.models.Model;
import Engine.models.WorldObject;
import Engine.resourceLoading.Texture;
import org.joml.Vector3f;

public class Suzanne extends WorldObject {


    public Suzanne(Vector3f position, Vector3f rotation, Vector3f scale) {
        super(MainClass.loader.getNormalModelFromResource("suzanne", new Texture("suzanne.png")), position, rotation, scale, "suzanne");
    }

    @Override
    public void update(Scene scene) {
        //this.increaseRotation(new Vector3f(1,1,1));
    }
}
