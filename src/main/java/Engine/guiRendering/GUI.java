package Engine.guiRendering;

import Engine.MainClass;
import Engine.models.Model;
import Engine.resourceLoading.Texture;
import org.joml.Vector2f;

public class GUI {


    private Vector2f position;

    private Vector2f scale;

    private final Texture texture;

    private final Model model;

    private final float[] positions = {-1, 1, -1, -1,1, 1, 1, -1}; //typical "quad" model

    /**
     * represents a 2D GUI object
     * @param position the position of the GUI on the screen
     * @param scale the scale of the GUI
     * @param texture the texture of the GUI
     */
    public GUI(Vector2f position, Vector2f scale, Texture texture) {
        this.position = position;
        this.scale = scale;
        this.texture = texture;
        this.model = MainClass.loader.loadToVAO(positions, texture);
        //TODO: add a pause menu
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public Vector2f getScale() {
        return scale;
    }

    public void setScale(Vector2f scale) {
        this.scale = scale;
    }

    public Texture getTexture() {
        return texture;
    }

    public Model getModel() {
        return model;
    }
}
