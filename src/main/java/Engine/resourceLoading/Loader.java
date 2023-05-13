package Engine.resourceLoading;

import Engine.MainClass;
import Engine.models.Material;
import Engine.models.Model;
import Engine.resourceLoading.objectLoading.ModelDataNM;
import Engine.resourceLoading.objectLoading.NormalMappedObjLoader;
import Engine.resourceLoading.objectLoading.OBJFileLoader;
import Engine.resourceLoading.objectLoading.RawModelData;
import de.matthiasmann.twl.utils.PNGDecoder;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import Engine.resourceLoading.Texture;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.*;

import static org.lwjgl.opengl.GL11.*;

public class Loader {
    private final List<Integer> vaos = new ArrayList<>();
    private final List<Integer> vbos = new ArrayList<>();

    private final List<Integer> textures = new ArrayList<>();

    private Map<Integer, String> resources = new HashMap<>();


    /**
     * loads the model data to the VAO to allow it to be rendered
     * @param positions the vertex positions
     * @return a Model representation of the data
     */
    public Model loadToVAO(float[] positions){
        int vaoID =createVAO();
        storeDataInAttributeList(0, 3, positions);
        GL30.glBindVertexArray(0);
        return new Model(vaoID, positions.length/3);

    }
    public Model loadToVAO(float[] positions, Texture texture){
        int vaoID =createVAO();
        storeDataInAttributeList(0, 2, positions);
        GL30.glBindVertexArray(0);
        loadTexture(texture);
        return new Model(vaoID, positions.length/2);

    }


    public Model loadToVAO(float[] positions, int[] indices, Material material){
        int vaoID =createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0,3, positions);
        GL30.glBindVertexArray(0);
        return new Model(vaoID, indices.length, material);

    }
    public Model loadToVAO(float[] positions, int[] indices, float[] textureCoords, Texture texture){
        int vaoID =createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0,3, positions);
        storeDataInAttributeList(1,2, textureCoords);
        GL30.glBindVertexArray(0);
        loadTexture(texture);
        return new Model(vaoID, indices.length, texture);

    }
    public Model loadToVAO(float[] positions, float[] textureCoords,float[] normals ,int[] indices, Texture texture){
        int vaoID = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0,3, positions);
        storeDataInAttributeList(1,2, textureCoords);
        storeDataInAttributeList(2,3, normals);

        GL30.glBindVertexArray(0);
        loadTexture(texture);
        return new Model((vaoID), indices.length, texture);

    }



    /**
     * used to load a model without a texture
     * @param resource the location of the resource (usually an .obj file)
     * @return the Model representation of the data
     */
    public Model getModelFromResource(String resource, Material material){
        RawModelData data =  OBJFileLoader.loadOBJ(resource);

        return loadToVAO(data.getVertices(), data.getIndices(), material);
    }

    /**
     * used to load a model with normal lighting
     * @param resource the location of the resource
     * @param texture the texture of the resource
     * @return the Model from the resource
     */
    public Model getNormalModelFromResource(String resource, Texture texture){
        RawModelData data = OBJFileLoader.loadOBJ(resource);

        return loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices(), texture);
    }

    /**
     * @param resource the location of the resource (usually an .obj file)
     * @param texture the texture to be associated with the object
     * @return the Model representation of the data
     */
    public Model getModelFromResource(String resource, Texture texture){
        RawModelData data =  OBJFileLoader.loadOBJ(resource);
        Model model = loadToVAO(data.getVertices(), data.getIndices(),data.getTextureCoords(), texture);
        resources.put(model.getVaoID(), resource);
        return model;
    }

    /**
     * loads a texture to be used
     * @param texture the texture to load
     * @return the texture location
     */
    public int loadTexture(Texture texture){

        int textID = glGenTextures();
        GL11.glBindTexture(GL_TEXTURE_2D, textID);
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, texture.getWidth(), texture.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, texture.getTextureData());
        GL30.glGenerateMipmap(GL_TEXTURE_2D);
        texture.setTextureID(textID);
        textures.add(textID);
        storeDataInAttributeList(1, 2, texture.getTextureData().asFloatBuffer());
        return textID;
    }

    /**
     * called after termination
     */
    public void cleanUp() {
        for (int vao : vaos) {
            GL30.glDeleteVertexArrays(vao);
        }
        for (int vbo : vbos) {
            GL15.glDeleteBuffers(vbo);
        }
    }


    /**
     * creates a VAO
     * @return the VAO ID
     */
    private int createVAO(){
        int vaoID = GL30.glGenVertexArrays();
        vaos.add(vaoID);
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }

    /**
     * creates a VBO with the provided data
     * @param attributeNumber the ID of the attribute
     * @param coordinateSize the size of each unit of data (vertices would be 3 because they have 3 coordinates)
     * @param data the data to store
     */
    private void storeDataInAttributeList(int attributeNumber, int coordinateSize,float[] data){
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber,coordinateSize,GL11.GL_FLOAT,false,0,0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    /**
     * creates a VBO with the provided data
     * @param attributeNumber the attribute id
     * @param coordinateSize the size of each unit of data
     * @param data the data to store
     */
    private void storeDataInAttributeList(int attributeNumber, int coordinateSize,FloatBuffer data){
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, data, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber,coordinateSize,GL11.GL_FLOAT,false,0,0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    /**
     * creates a VBO for specifically indices data
     * @param indices the indices data
     */
    private void bindIndicesBuffer(int[] indices){
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer = storeDataInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    /**
     * @param data the data to store
     * @return the data in an IntBuffer form
     */
    private IntBuffer storeDataInIntBuffer(int[] data){
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    /**
     * @param data the data to store
     * @return the data in a FloatBuffer form
     */
    private FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
}
