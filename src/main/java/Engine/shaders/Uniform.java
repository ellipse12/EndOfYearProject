package Engine.shaders;

public abstract class Uniform<T> implements UniformI<T>{

    private final int location;
    private final String name;




    public Uniform(String name, ShaderProgram program){
        this.name = name;
        this.location = program.getUniformLocation(name);

    }

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

    public abstract void load(T value);



}
