package Engine.shaders;

import org.lwjgl.BufferUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

public abstract class ShaderProgram {
    private int programID;
    private int vertShaderID;
    private int fragShaderID;

    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

    public ShaderProgram(String vertFile, String fragFile) {

    }

    public static int loadShader(String file, int type){
        StringBuilder source = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine()) != null){
                source.append(line).append("\n");
            }
        }catch(IOException e){
            System.err.println("Could not find file \"" + file + "\" in source.");
            e.printStackTrace();
            System.exit(-1);
        }

    }
}
