package Engine.shaders.uniforms;

import Engine.models.Material;
import Engine.shaders.ShaderProgram;
import Engine.shaders.Uniform;

public class MaterialU extends Uniform<Material> {



    public MaterialU(String name, ShaderProgram program) {
        super(name, program);
    }

    @Override
    public void load(Material value) {

    }




}
