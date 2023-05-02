package Engine.shaders.uniforms.collections;

import Engine.shaders.ShaderProgram;
import Engine.shaders.uniforms.primitives.FloatU;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;

public class FloatArrayU extends ArrayUniform<FloatU, Float>{
    public FloatArrayU(String name, int length, ShaderProgram program) {
        super(name, length, program);
    }

    @Override
    public void load(int[] locations, Float[] values) {
        for(int i = 0; i < locations.length; i++){
            GL20.glUniform1f(i, values[i]);
        }
    }


}
