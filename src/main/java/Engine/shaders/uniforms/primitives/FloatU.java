package Engine.shaders.uniforms.primitives;

import Engine.shaders.ShaderProgram;
import Engine.shaders.Uniform;
import org.lwjgl.opengl.GL20;

public class FloatU extends Uniform<Float> {
    public FloatU(String name, ShaderProgram program) {
        super(name, program);
    }

    @Override
    public void load(Float value) {
        GL20.glUniform1f(this.getLocation(), value);
    }
}
