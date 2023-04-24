package Engine.shaders.uniforms;

import Engine.shaders.ShaderProgram;
import Engine.shaders.Uniform;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL20;

public class Vector3fU extends Uniform<Vector3f> {
    public Vector3fU(String name, ShaderProgram program) {
        super(name, program);
    }

    @Override
    public void load(Vector3f value) {
        GL20.glUniform3f(this.getLocation(), value.x, value.y, value.z);
    }
}
