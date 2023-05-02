package Engine.shaders.uniforms.compound;

import Engine.models.Attenuation;
import Engine.shaders.ShaderProgram;
import Engine.shaders.Uniform;
import Engine.shaders.uniforms.primitives.FloatU;

public class AttenuationU extends Uniform<Attenuation> {
    private MultiUniform uniforms;
    public AttenuationU(String name, ShaderProgram program) {
        super(name, program);
        uniforms= new MultiUniform();
        uniforms.addUniform(new FloatU(this.getName() + ".constant", program));
        uniforms.addUniform(new FloatU(this.getName() + ".linear", program));
        uniforms.addUniform(new FloatU(this.getName() + ".exponent", program));

    }

    @Override
    public void load(Attenuation value) {
        uniforms.load(this.getName() + ".constant", value.getConstant());
        uniforms.load(this.getName() + ".linear", value.getLinear());
        uniforms.load(this.getName() + ".exponent", value.getExponent());
    }
}
