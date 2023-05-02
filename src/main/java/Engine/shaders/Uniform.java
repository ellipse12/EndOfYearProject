package Engine.shaders;

public abstract class Uniform<T>{

    private int location;
    private String name;




    public Uniform(String name, ShaderProgram program){
        this.name = name;
        this.location = program.getUniformLocation(name);

    }

    public Uniform(String name, int location){
        this.name = name;
        this.location = location;
    }



    public int getLocation() {
        return location;
    }


    public String getName() {
        return name;
    }

    public abstract void load(T value);



}
