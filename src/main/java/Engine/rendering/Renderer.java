package Engine.rendering;

public interface Renderer {
    void render(Camera camera);

    void update(Camera camera);

    void cleanUp();
}
