package Engine.shaders.uniforms.compound;

import Engine.models.Material;
import Engine.shaders.ShaderProgram;
import Engine.shaders.Uniform;
import Engine.shaders.uniforms.primitives.BooleanU;
import Engine.shaders.uniforms.primitives.FloatU;
import Engine.shaders.uniforms.primitives.Vector4fU;

public class MaterialU extends Uniform<Material> {

   private final MultiUniform uniforms;

    public MaterialU(String name, ShaderProgram program) {
        super(name, program);
        uniforms= new MultiUniform();
        uniforms.addUniform(new Vector4fU(name + ".ambient", program));
        uniforms.addUniform( new Vector4fU(name + ".diffuse", program));
        uniforms.addUniform(new Vector4fU(name + ".specular", program));
        uniforms.addUniform(new BooleanU(name + ".hasTexture", program));
        uniforms.addUniform(new FloatU(name+".reflectance", program));
    }

    @Override
    public void load(Material value) {
        uniforms.load(this.getName() + ".ambient", value.getAmbient());
        uniforms.load(this.getName() + ".diffuse", value.getDiffuse());
        uniforms.load(this.getName() + ".specular", value.getSpecular());
        uniforms.load(this.getName() + ".hasTexture", value.isHasTexture());
        uniforms.load(this.getName() + ".reflectance", value.getReflectance());

    }




}
