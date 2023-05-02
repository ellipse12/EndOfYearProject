package Engine.shaders.uniforms.collections;

import Engine.shaders.ShaderProgram;
import Engine.shaders.Uniform;

public abstract class ArrayUniform<T extends Uniform<V>, V> extends Uniform<V[]> {

    private T uniform;

    private int[] locations;

    private int length;
    private String name;

    public ArrayUniform(String name, int length, ShaderProgram program) {
        super(name, program);
        locations = new int[length];
        for(int i =0; i < length; i++){
            locations[i] = program.getUniformLocation(name + "[" + i + "]");
        }
    }



    public T getUniform() {
        return uniform;
    }


    public void load(V[] value) {
        load(locations, value);
    }

    public abstract void load(int[] locations, V[] values);

    public int[] getLocations() {
        return locations;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String getName() {
        return name;
    }
}
