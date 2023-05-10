package Engine.resourceLoading;

import Engine.util.ResourceLocation;
import de.matthiasmann.twl.utils.PNGDecoder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class Texture {
    private final String filename;
    private final ByteBuffer textureBuffer;

    private int textureID;

    private PNGDecoder decoder;

    /**
     * used to represent a Texture instance
     * @param filename the location of the texture
     */
    public Texture(String filename){
        this.filename = filename;
        this.textureBuffer = decodeTexture(filename);

    }


    /**
     * decodes the given texture
     * @param filename the name/ location of the texture
     * @return the decoded texture data (all of the pixels)
     */
    private ByteBuffer decodeTexture(String filename){

        try{
            InputStream stream = ResourceLocation.getFileStream("textures/" + filename); //uses a file stream to allow for resources to be bundled in the jar file
            decoder = new PNGDecoder(stream);
            ByteBuffer buf = ByteBuffer.allocateDirect(
                    4 * decoder.getWidth() * decoder.getHeight());
            decoder.decode(buf, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
            buf.flip();
            return buf;
        }catch (IOException e){
            System.err.println("could not file");
            e.printStackTrace();
        }
        return ByteBuffer.allocateDirect(1);
    }

    public String getFilename() {
        return filename;
    }


    public ByteBuffer getTextureData() {
        return textureBuffer;
    }

    public int getWidth(){
        return decoder.getWidth();
    }
    public int getHeight(){
        return decoder.getHeight();
    }

    public int getTextureID() {
        return textureID;
    }

    /**
     * MUST be set to be used as an active texture
     * @param textureID the ID of the texture in OpenGL's memory
     */
    public void setTextureID(int textureID) {
        this.textureID = textureID;
    }
}
