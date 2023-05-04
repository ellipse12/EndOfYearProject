package Engine.shaders;

public interface UniformI<T> {

    void load(T value);

    String getName();

    int getLocation();

}
