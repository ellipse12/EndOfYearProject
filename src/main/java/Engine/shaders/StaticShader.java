package Engine.shaders;


import Engine.models.WorldObject;
import Engine.rendering.Camera;
import Engine.util.Maths;
import org.joml.Matrix4f;

public class StaticShader extends ShaderProgram {
    private static final String vertexFile = "src/main/resources/shaders/defaultVertShader.vert";
    private static final String fragmentFile = "src/main/resources/shaders/defaultFragShader.frag";


    private int loc_transformationMatrix;
    private int loc_projectionMatrix;

    private int loc_viewMatrix;

    /**
     * A generic shader that is used for rendering
     */
    public StaticShader() {
        super(vertexFile, fragmentFile);
    }

    /**
     * a method to get the integer id's/ locations of all the uniforms in the vertex shader
     */
    @Override
    protected void getAllUniformLocations() {
        loc_transformationMatrix = super.getUniformLocation("transformationMatrix");
        loc_projectionMatrix = super.getUniformLocation("projectionMatrix");
        loc_viewMatrix = super.getUniformLocation("viewMatrix");
    }

    /**
     * a method to bind all of the "in" variables in the vertex shader, defines the data that represents each vertex in a model
     */
    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoord");
        super.bindAttribute(2, "vertexNormal");
    }

    /**
     * loads a given matrix into the vertex shader to be used for transformations
     * @param matrix the matrix to load
     */
    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadMatrix(loc_transformationMatrix, matrix);
    }

    /**
     * loads a given projection matrix into the vertex shader
     * @param matrix the matrix to load
     */
    public void loadProjectionMatrix(Matrix4f matrix){
        super.loadMatrix(loc_projectionMatrix, matrix);
    }

    /**
     * creates and loads a model specific view matrix
     * @param object the object in question
     * @param camera the camera that is being used for the viewpoint
     */
    public void loadViewMatrix(WorldObject object, Camera camera){
        Matrix4f matrix = Maths.getModelViewMatrix(object, Maths.createViewMatrix(camera));

        super.loadMatrix(loc_viewMatrix, matrix);
    }
}
