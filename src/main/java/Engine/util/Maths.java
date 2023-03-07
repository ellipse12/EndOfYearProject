package Engine.util;

import Engine.rendering.Window;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public class Maths {

    public static Matrix4f createTransformationMatrix(Vector3f translation, Vector3f scale) {
        Matrix4f matrix = new Matrix4f();
        matrix.identity().translate(translation).scale(1);

        return matrix;
    }
    public static Matrix4f createTransformationMatrix(Vector3f translation, Vector3f rotation, Vector3f scale) {
        Matrix4f matrix = new Matrix4f();
        matrix.identity().translate(translation)
        .rotate((float) Math.toRadians(rotation.x), new Vector3f(1,0,0))
        .rotate((float) Math.toRadians(rotation.y), new Vector3f(0,1,0))
        .rotate((float) Math.toRadians(rotation.z), new Vector3f(0,0,1))
        .scale(new Vector3f(scale.x, scale.y, 1f));
        return matrix;
    }
    private static final float FOV = (float) Math.toRadians(60.0f);

    private static final float Z_NEAR = 0.01f;

    private static final float Z_FAR = 1000.f;
    public static Matrix4f createProjectionMatrix(Window window){

        float aspectRatio = (float) window.getWidth() / (float) window.getHeight();
        Matrix4f projectionMatrix = new Matrix4f().identity().perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
        return projectionMatrix;

    }


}