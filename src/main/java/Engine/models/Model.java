package Engine.models;

import Engine.resourceLoading.Loader;
import Engine.resourceLoading.Texture;
import Engine.resourceLoading.objectLoading.RawModelData;
import org.joml.Vector4f;

public class Model {
    private int vaoID;
    private int vertexCount;

    private Texture texture;

    private Material material;


    /**
     * a generic model class with a texture
     * @param vaoID the ID of the model data's VAO
     * @param vertexCount the number of vertices in this model
     */
    public Model(int vaoID, int vertexCount) {
        this(vaoID, vertexCount, null, new Material(new Vector4f(), new Vector4f(), new Vector4f(), false, 0f));
    }
    public Model(int vaoID, int vertexCount, Texture texture) {
        this(vaoID, vertexCount, texture, new Material(new Vector4f(), new Vector4f(), new Vector4f(), true, 0f));

    }
    public Model(int vaoID, int vertexCount, Material material){
        this(vaoID, vertexCount, null, material);
    }
    public Model(int vaoID, int vertexCount, Texture texture,  Material material){
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
        this.material = material;
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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
