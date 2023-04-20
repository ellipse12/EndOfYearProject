package Engine.models;

import Engine.resourceLoading.Loader;
import Engine.resourceLoading.Texture;
import Engine.resourceLoading.objectLoading.RawModelData;

public class Model {
    private int vaoID;
    private int vertexCount;

    private Texture texture;


    /**
     * a generic model class with a texture
     * @param vaoID the ID of the model data's VAO
     * @param vertexCount the number of vertices in this model
     */
    public Model(int vaoID, int vertexCount) {
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
        this.texture = null;
    }
    public Model(int vaoID, int vertexCount, Texture texture) {
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
        this.texture = texture;
    }




    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
