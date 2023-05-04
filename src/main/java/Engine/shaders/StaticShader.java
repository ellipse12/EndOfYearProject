package Engine.shaders;


import Engine.shaders.uniforms.collections.LightArrayU;
import Engine.shaders.uniforms.compound.LightU;
import Engine.shaders.uniforms.compound.MaterialU;
import Engine.shaders.uniforms.primitives.FloatU;
import Engine.shaders.uniforms.primitives.IntegerU;
import Engine.shaders.uniforms.primitives.Matrix4fU;
import Engine.shaders.uniforms.primitives.Vector3fU;

public class StaticShader extends ShaderProgram {
    private static final String vertexFile = "src/main/resources/shaders/defaultVertShader.vert";
    private static final String fragmentFile = "src/main/resources/shaders/defaultFragShader.frag";






    /**
     * A generic shader that is used for rendering
     */
    //TODO finish lighting
    public StaticShader() {
        super(vertexFile, fragmentFile);

        setupUniforms();
    }

    /**
     * a method to get the integer id's/ locations of all the uniforms in the vertex shader
     */

    protected void setupUniforms() {
        addUniform(new Matrix4fU("transformationMatrix", this));
        addUniform(new Matrix4fU("projectionMatrix", this));
        addUniform(new Matrix4fU("viewMatrix", this));
        addUniform(new MaterialU("material", this));
        addUniform(new IntegerU("texture_sampler", this));
        addUniform(new LightArrayU("lights", 3, this));
        addUniform(new Vector3fU("camera_position", this));
        addUniform(new Vector3fU("ambientLight", this));
        addUniform(new FloatU("specularPower", this));
        addUniform(new IntegerU("numLights", this));

    }



    /**
     * a method to bind all of the "in" variables in the vertex shader, defines the data that represents each vertex in a model
     */
    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoord");
        super.bindAttribute(2, "vertexNormal");
    }


}
