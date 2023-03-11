package Engine.shaders;


import Engine.rendering.Camera;
import Engine.util.Maths;
import org.joml.Matrix4f;

public class StaticShader extends ShaderProgram {
    private static final String vertexFile = "src/main/resources/shaders/defaultVertShader.vert";
    private static final String fragmentFile = "src/main/resources/shaders/defaultFragShader.frag";


    private int loc_transformationMatrix;
    private int loc_projectionMatrix;

    private int loc_viewMatrix;

    public StaticShader() {
        super(vertexFile, fragmentFile);
    }

    @Override
    protected void getAllUniformLocations() {
        loc_transformationMatrix = super.getUniformLocation("transformationMatrix");
        loc_projectionMatrix = super.getUniformLocation("projectionMatrix");
        loc_viewMatrix = super.getUniformLocation("viewMatrix");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoord");
    }
    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadMatrix(loc_transformationMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix){
        super.loadMatrix(loc_projectionMatrix, matrix);
    }

    public void loadViewMatrix(Camera camera){
        Matrix4f matrix = Maths.createViewMatrix(camera);
        super.loadMatrix(loc_viewMatrix, matrix);
    }
}
