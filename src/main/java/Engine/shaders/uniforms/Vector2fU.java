package Engine.shaders.uniforms;

import Engine.shaders.Uniform;
import org.joml.Vector2f;
import org.lwjgl.opengl.GL20;

public class Vector2fU extends Uniform<Vector2f> {
    public Vector2fU(String name, int location) {
        super(name, location);
    }

    @Override
    public void load(Vector2f value) {
        GL20.glUniform2f(this.getLocation(), value.x, value.y);
    }
}
