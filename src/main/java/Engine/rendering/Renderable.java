package Engine.rendering;

public interface Renderable {
    void render(Camera camera);

    void update(Camera camera);

    void cleanUp();
}
