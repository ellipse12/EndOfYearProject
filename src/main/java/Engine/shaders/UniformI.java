package Engine.shaders;

public interface UniformI<T> {

    /**
     * loads a value to this uniform
     * @param value the value to load
     */
    void load(T value);

    /**
     * @return the name of this uniform
     */
    String getName();

    /**
     * @return the location of this uniform in the shader
     */
    int getLocation();

}
