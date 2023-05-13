package Engine.rendering;

import Engine.Scene;

public interface Updateable {

    /**
     * used to update an object every scene
     * @param scene the current scene
     */
    void update(Scene scene);
}
