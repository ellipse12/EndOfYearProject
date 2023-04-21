package Engine.shaders;


import Engine.models.Material;
import Engine.models.WorldObject;
import Engine.rendering.Camera;
import Engine.shaders.uniforms.MaterialU;
import Engine.shaders.uniforms.Matrix4fU;
import Engine.util.Maths;
import org.joml.Matrix4f;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StaticShader extends ShaderProgram {
    private static final String vertexFile = "src/main/resources/shaders/defaultVertShader.vert";
    private static final String fragmentFile = "src/main/resources/shaders/defaultFragShader.frag";




    private Set<Uniform> uniforms;

    /**
     * A generic shader that is used for rendering
     */
    public StaticShader() {
        super(vertexFile, fragmentFile);
        uniforms = new HashSet<>();
        setupUniforms();
    }

    /**
     * a method to get the integer id's/ locations of all the uniforms in the vertex shader
     */

    protected void setupUniforms() {
        addUniform(new Matrix4fU("transformationMatrix", this));
        addUniform(new Matrix4fU("projectionMatrix", this));
        addUniform(new Matrix4fU("viewMatrix", this));
    }

    @Override
    public void addUniform(Uniform uniform) {

            uniforms.add(uniform);

    }


    @Override
    public <T> void setUniform(String name, T value) {
        for(Uniform uniform:uniforms){
            if(uniform.getName().equals(name)){
                uniform.load(value);
            }
        }
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
