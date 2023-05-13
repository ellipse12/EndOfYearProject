package Engine.util;

import Engine.models.Light;
import Engine.models.WorldObject;
import Engine.rendering.Camera;
import Engine.rendering.Window;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public class Maths {


    /**
     * @param translation the translation to be applied
     * @param rotation the rotation to be applied
     * @param scale the scale to be applied
     * @return a matrix representing the transformations given
     */
    public static Matrix4f createTransformationMatrix(Vector3f translation, Vector3f rotation, Vector3f scale) {
        Matrix4f matrix = new Matrix4f();
        matrix.identity().translate(translation)
        .rotateX((float) Math.toRadians(rotation.x))
        .rotateY((float) Math.toRadians(rotation.y))
        .rotateZ((float) Math.toRadians(rotation.z))

        .scale(new Vector3f(scale.x, scale.y, scale.z));
        return matrix;
    }


    public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale){
        Matrix4f out = new Matrix4f();
        out.identity().translate(translation.x, translation.y, 1f).scale(scale.x, scale.y, 1f);
        return out;
    }

    private static final float FOV = (float) Math.toRadians(60.0f);

    private static final float Z_NEAR = 0.01f;

    private static final float Z_FAR = 1000.f;

    /**
     * @param window the current window
     * @return a matrix allowing for the projection of 3D models
     */
    public static Matrix4f createProjectionMatrix(Window window){

        float aspectRatio = (float) window.getWidth() / (float) window.getHeight();
        Matrix4f projectionMatrix = new Matrix4f();
        projectionMatrix.identity();
        projectionMatrix.perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
        return projectionMatrix;

    }

    /**
     * @param camera the camera to use
     * @return a matrix that represents how the scene should be viewed
     */
    public static Matrix4f createViewMatrix(Camera camera){
         Vector3f position = camera.getPosition();
         Vector3f rotation = camera.getRotation();
         Matrix4f viewMatrix = new Matrix4f();
         viewMatrix.identity();

         viewMatrix.rotate((float)Math.toRadians(rotation.x), new Vector3f(1,0,0))
                 .rotate((float)Math.toRadians(rotation.y), new Vector3f(0,1,0))
                 .rotate((float)Math.toRadians(rotation.z), new Vector3f(0, 0, 1));
         viewMatrix.translate(-position.x, -position.y, -position.z);
         return viewMatrix;

    }

    /**
     * used to simulate movement with the camera, instead of moving the camera around we move the whole scene around opposite of the movement of the camera
     * @param object the object in question
     * @param viewMatrix the matrix that is based off the camera, created by the method above
     * @return a model specific view matrix
     */
    public static Matrix4f getModelViewMatrix(Light object, Matrix4f viewMatrix){
        //Vector3f rotation = object.getRotation();
        Matrix4f modelMat = new Matrix4f();
        modelMat.identity().translate(object.getPosition());
        Matrix4f view = new Matrix4f(viewMatrix);
        return view.mul(modelMat);
    }


}
