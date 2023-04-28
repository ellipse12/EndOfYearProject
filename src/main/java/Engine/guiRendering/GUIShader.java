package Engine.guiRendering;

import Engine.shaders.ShaderProgram;
import Engine.shaders.Uniform;

import java.util.Set;

public class GUIShader extends ShaderProgram {

    private static final String vertLoc = "src/main/resources/shaders/guiVertexShader";
    private static final String fragLoc = "src/main/resources/shaders/guiFragmentShader";

    public GUIShader() {
        super(vertLoc, fragLoc);
        setupUniforms();
    }

    private void setupUniforms(){

    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoord");
    }
}
