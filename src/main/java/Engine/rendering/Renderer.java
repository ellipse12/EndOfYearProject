package Engine.rendering;

import Engine.Scene;

public interface Renderer {
    void render(Scene scene);

    void update(Scene scene);

    void cleanUp();
}
