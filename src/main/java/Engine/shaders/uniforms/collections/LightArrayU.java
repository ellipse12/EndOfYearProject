package Engine.shaders.uniforms.collections;

import Engine.models.Light;
import Engine.shaders.ShaderProgram;
import Engine.shaders.uniforms.compound.LightU;

import java.util.List;

public class LightArrayU extends ArrayUniform<LightU, Light>{

    public LightArrayU(String name, int length, ShaderProgram program) {
        super(name, length, program);
    }

    @Override
    public LightU create(String name, ShaderProgram program) {
        return new LightU(name, program);
    }

    public void load(List<Light> lights){
        Light[] vals = new Light[lights.size()];
        for(int i =0; i < lights.size(); i++){
            vals[i] = lights.get(i);
        }
        super.load(vals);
    }
}
