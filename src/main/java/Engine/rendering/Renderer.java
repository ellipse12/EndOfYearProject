package Engine.rendering;

import Engine.Scene;

public interface Renderer {
    /**
     * used for custom renderers, allowing them to render without having to update the driver class
     * @param scene the current scene
     */
    void render(Scene scene);

    /**
     * updates the renderer
     * @param scene the current scene
     */
    void update(Scene scene);

    /**
     * called after the game is closed so that the renderer can clean anything it needs to
     */
    void cleanUp();
}
