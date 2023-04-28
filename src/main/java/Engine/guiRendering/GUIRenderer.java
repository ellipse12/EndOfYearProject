package Engine.guiRendering;

import Engine.rendering.Camera;
import Engine.rendering.Renderer;

public class GUIRenderer implements Renderer {


    private GUIShader shader = new GUIShader();
    @Override
    public void render(Camera camera) {
        shader.start();
    }

    @Override
    public void update(Camera camera) {

    }

    @Override
    public void cleanUp() {

    }
}
