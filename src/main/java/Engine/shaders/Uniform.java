package Engine.shaders;

public abstract class Uniform<T> implements UniformI<T>{

    private final int location;
    private final String name;


    /**
     * @param name the name of this uniform
     * @param program the shader to load too
     */
    public Uniform(String name, ShaderProgram program){
        this.name = name;
        this.location = program.getUniformLocation(name);

    }

    /**
     * @param name the name of the uniform
     * @param location the location of the uniform in the shader
     */
    public Uniform(String name, int location){
        this.name = name;
        this.location = location;
    }

    @Override
    public int getLocation() {
        return location;
    }


    @Override
    public String getName() {
        return name;
    }

    /**
     * loads a value to the uniform
     * @param value the value to load
     */
    public abstract void load(T value);



}
