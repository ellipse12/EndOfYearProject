package Engine.shaders.uniforms.collections;

import Engine.shaders.ShaderProgram;
import Engine.shaders.UniformI;

import java.util.ArrayList;
import java.util.List;

public abstract class ArrayUniform<T extends UniformI<V>, V> implements UniformI<V[]> {
    private String name;
    private int length;
    private int location;

    private List<T> uniforms;
    public ArrayUniform(String name, int length, ShaderProgram program){
        this.name = name;
        this.length = length;
        this.location = program.getUniformLocation(name);
        this.uniforms = new ArrayList<>();
        for(int i =0; i < length; i++){
            uniforms.add(create(this.getName() + "[" +i +"]", program));
        }
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLocation() {
        return location;
    }

    @Override
    public void load(V[] value) {
        if(value.length >= 1) {
            for (int i = 0; i < this.getLength(); i++) {
                uniforms.get(i).load(value[i]);
            }
        }
    }

    public int getLength() {
        return length;
    }

    public abstract T create(String name, ShaderProgram program);
}
