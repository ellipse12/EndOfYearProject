package Engine.shaders.uniforms;

import Engine.shaders.ShaderProgram;
import Engine.shaders.Uniform;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;

public class Matrix4fU extends Uniform<Matrix4f> {
    public Matrix4fU(String name, ShaderProgram program) {
        super(name, program);
    }

    @Override
    public void load(Matrix4f matrix) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer fb = stack.mallocFloat(16);
            matrix.get(fb);

            glUniformMatrix4fv(this.getLocation(), false, fb);
        }
    }
}
