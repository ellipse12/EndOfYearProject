package Engine.shaders;

import Engine.shaders.ShaderProgram;

public abstract class Uniform<T> implements UniformI<T>{

    private int location;
    private String name;


    public Uniform(String name, ShaderProgram program){
        this.name = name;
        this.location = program.getUniformLocation(name);
    }



    public int getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }
}
