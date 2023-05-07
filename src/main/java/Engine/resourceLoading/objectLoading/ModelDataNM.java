package Engine.resourceLoading.objectLoading;
/**
None of this code is mine, and I claim no ownership. All rights go to @author ThinMatrix on YouTube,
and this should be considered a helper class and not graded within the scope of the rest of the project
 */
public class ModelDataNM {

    private final float[] vertices;
    private final float[] textureCoords;
    private final float[] normals;
    private final float[] tangents;
    private final int[] indices;
    private final float furthestPoint;

    public ModelDataNM(float[] vertices, float[] textureCoords, float[] normals, float[] tangents, int[] indices,
                       float furthestPoint) {
        this.vertices = vertices;
        this.textureCoords = textureCoords;
        this.normals = normals;
        this.indices = indices;
        this.furthestPoint = furthestPoint;
        this.tangents = tangents;
    }

    public float[] getVertices() {
        return vertices;
    }

    public float[] getTextureCoords() {
        return textureCoords;
    }

    public float[] getTangents(){
        return tangents;
    }

    public float[] getNormals() {
        return normals;
    }

    public int[] getIndices() {
        return indices;
    }

    public float getFurthestPoint() {
        return furthestPoint;
    }

}
