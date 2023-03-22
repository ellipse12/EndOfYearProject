package Engine.resourceLoading;

import de.matthiasmann.twl.utils.PNGDecoder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Texture {
    private String filename;
    private ByteBuffer textureBuffer;

    private int textureID;

    private PNGDecoder decoder;

    public Texture(String filename){
        this.filename = filename;
        this.textureBuffer = decodeTexture(filename);

    }


    private ByteBuffer decodeTexture(String filename){

        try{
            InputStream stream = Files.newInputStream(Paths.get("src/main/resources/textures/" + filename));
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

    public void setTextureID(int textureID) {
        this.textureID = textureID;
    }
}