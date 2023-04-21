package Engine.shaders.uniforms;

import Engine.shaders.Uniform;
import org.lwjgl.opengl.GL20;

public class IntegerU extends Uniform<Integer> {
    public IntegerU(String name, int location) {
        super(name, location);
    }

    @Override
    public void load(Integer value) {
        GL20.glUniform1i(this.getLocation(), value);
    }
}
