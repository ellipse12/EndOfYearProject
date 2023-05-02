package Engine.shaders.uniforms.primitives;

import Engine.shaders.ShaderProgram;
import Engine.shaders.Uniform;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL20;

public class Vector4fU extends Uniform<Vector4f> {
    public Vector4fU(String name, ShaderProgram program) {
        super(name, program);
    }

    @Override
    public void load(Vector4f value) {
        GL20.glUniform4f(this.getLocation(), value.x, value.y, value.z, value.w);
    }
}
