package Engine.guiRendering;

import Engine.shaders.ShaderProgram;
import Engine.shaders.Uniform;
import Engine.shaders.uniforms.primitives.IntegerU;
import Engine.shaders.uniforms.primitives.Matrix4fU;


import java.util.Set;

public class GUIShader extends ShaderProgram {

    private static final String vertLoc = "src/main/resources/shaders/guiVertexShader.vert";
    private static final String fragLoc = "src/main/resources/shaders/guiFragmentShader.frag";

    public GUIShader() {
        super(vertLoc, fragLoc);
        setupUniforms();
    }

    private void setupUniforms(){
        addUniform(new Matrix4fU("transformation", this));
        addUniform(new IntegerU("offset", this));
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoord");
    }
}
