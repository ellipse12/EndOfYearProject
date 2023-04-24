package Engine.shaders.uniforms;

import Engine.shaders.ShaderProgram;
import Engine.shaders.Uniform;
import org.lwjgl.opengl.GL20;

public class IntegerU extends Uniform<Integer> {
    public IntegerU(String name, ShaderProgram program) {
        super(name, program);
    }

    @Override
    public void load(Integer value) {
        GL20.glUniform1i(this.getLocation(), value);
    }
}
