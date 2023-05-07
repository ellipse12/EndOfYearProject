package Engine.shaders.uniforms.compound;

import Engine.models.Light;
import Engine.shaders.ShaderProgram;
import Engine.shaders.Uniform;
import Engine.shaders.uniforms.primitives.FloatU;
import Engine.shaders.uniforms.primitives.Vector3fU;

public class LightU extends Uniform<Light> {
    private final MultiUniform uniforms;
    public LightU(String name, ShaderProgram program) {
        super(name, program);
        uniforms = new MultiUniform();
        uniforms.addUniform(new Vector3fU(this.getName()+".color", program));
        uniforms.addUniform(new Vector3fU(this.getName()+".position", program));
        uniforms.addUniform(new FloatU(this.getName()+".intensity", program));


    }

    @Override
    public void load(Light value) {
        uniforms.load(this.getName() + ".color", value.getColor());
        uniforms.load(this.getName() + ".position", value.getPosition());
        uniforms.load(this.getName() + ".intensity", value.getIntensity());

    }
}
