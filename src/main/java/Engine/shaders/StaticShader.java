package Engine.shaders;


import org.joml.Matrix4f;

public class StaticShader extends ShaderProgram {
    private static final String vertexFile = "src/main/resources/shaders/defaultVertShader.vert";
    private static final String fragmentFile = "src/main/resources/shaders/defaultFragShader.frag";


    private int loc_transformationMatrix;

    public StaticShader() {
        super(vertexFile, fragmentFile);
    }

    @Override
    protected void getAllUniformLocations() {
        loc_transformationMatrix = super.getUniformLocation("transformationMatrix");

    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }
    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadMatrix(loc_transformationMatrix, matrix);
    }
}
