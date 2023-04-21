package Engine.shaders;

import Engine.shaders.ShaderProgram;

public abstract class Uniform<T> {

    private int location;
    private String name;

    public Uniform(String name, int location) {
        this.location = location;
        this.name = name;
    }
    public Uniform(String name, ShaderProgram program){
        this.name = name;
        this.location = program.getUniformLocation(name);
    }

    public abstract void load(T value);

    public int getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }
}
