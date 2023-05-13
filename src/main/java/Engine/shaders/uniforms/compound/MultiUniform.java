package Engine.shaders.uniforms.compound;

import Engine.shaders.Uniform;

import java.util.HashSet;
import java.util.Set;

public class MultiUniform  {
    private final Set<Uniform> uniforms;
    public MultiUniform() {

        this.uniforms = new HashSet<>();
    }

    public MultiUniform(Set<Uniform> uniforms){

        this.uniforms = new HashSet<>(uniforms);
    }


    /**
     * loads a value to all the associated uniforms
     * @param value the value to load
     */
    public <T> void load(T value) {
        for(Uniform uniform : uniforms){
            uniform.load(value);
        }
    }

    /**
     * @param name the name of the uniform
     * @param value the value to load to a specific uniform
     */
    public <T> void load(String name, T value){
        for(Uniform uniform : uniforms){
            if(uniform.getName().equals(name)){
                uniform.load(value);
            }
        }
    }

    /**
     * adds a uniform to the list
     * @param uniform the uniform to add
     */
    public void addUniform(Uniform uniform){
        uniforms.add(uniform);
    }
    public void removeUniform(Uniform uniform){
        uniforms.remove(uniform);
    }

}
