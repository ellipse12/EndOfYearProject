package Engine.shaders.uniforms.collections;

import Engine.models.Light;
import Engine.shaders.ShaderProgram;
import Engine.shaders.uniforms.compound.LightU;

public class LightArrayU extends ArrayUniform<LightU, Light>{

    public LightArrayU(String name, int length, ShaderProgram program) {
        super(name, length, program);
    }

    @Override
    public LightU create(String name, ShaderProgram program) {
        return new LightU(name, program);
    }


}
