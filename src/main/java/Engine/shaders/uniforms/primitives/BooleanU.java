package Engine.shaders.uniforms.primitives;

import Engine.shaders.ShaderProgram;
import Engine.shaders.Uniform;
import org.lwjgl.opengl.GL20;

public class BooleanU extends Uniform<Boolean> {
    public BooleanU(String name, ShaderProgram program) {
        super(name, program);
    }

    @Override
    public void load(Boolean value) {
        GL20.glUniform1i(this.getLocation(), value?1:0);
    }
}
