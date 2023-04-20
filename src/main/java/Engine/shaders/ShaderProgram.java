package Engine.shaders;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;

public abstract class ShaderProgram {

    private int programID;
    private int vertexShaderID;
    private int fragmentShaderID;


    /**
     * a class that is extended by all other shader classes
     * @param vertexFile the location of the vertex shader file
     * @param fragmentFile the location of the fragment shader file
     */
    public ShaderProgram(String vertexFile, String fragmentFile){
        vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
        programID = GL20.glCreateProgram();
        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID,fragmentShaderID);
        bindAttributes();
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
        getAllUniformLocations();

    }

    /**
     * called to get all of the locations of the uniforms in the vertex shader
     */
    protected abstract void getAllUniformLocations();

    public abstract void createUniform(String name);

    public abstract <T> void setUniform(String name, T value);

    /**
     * should be used in getAllUniformLocations()
     * @param uniformName the name of the uniform variable
     * @return the integer representation of the location of the uniform given
     */
    protected int getUniformLocation(String uniformName){
        return GL20.glGetUniformLocation(programID, uniformName);
    }

    /**
     * tells OpenGL that this shader is in use
     */
    public void start(){
        GL20.glUseProgram(programID);
    }

    /**
     * tells OpenGl that this shader is no longer in use
     */
    public void stop(){
        GL20.glUseProgram(0);
    }

    /**
     * run after the window is destroyed and game loop exits.
     */
    public void cleanUp(){
        stop();
        GL20.glDetachShader(programID, vertexShaderID);
        GL20.glDetachShader(programID, fragmentShaderID);
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        GL20.glDeleteProgram(programID);
    }

    /**
     * binds the locations of the vertex attributes in the shader
     */
    protected abstract void bindAttributes();

    /**
     * @param attribute the vertex attribute location
     * @param variableName the variable name to be used
     */
    protected void bindAttribute(int attribute, String variableName){
        GL20.glBindAttribLocation(programID, attribute, variableName);
    }


    /**
     * used to load a generic matrix uniform
     * @param location the location of the uniform
     * @param matrix the matrix to load
     */
    protected void loadMatrix(int location, Matrix4f matrix){
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer fb = stack.mallocFloat(16);
            matrix.get(fb);

            glUniformMatrix4fv(location, false, fb);
        }

    }

    /**
     * loads and compiles an external shader source
     * @param file the file location of the shader
     * @param type the type of shader (vertex, fragment, etc.)
     * @return the location in OpenGL's memory of the shader in question
     */
    private static  int loadShader(String file, int type){
        StringBuilder shaderSource = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine())!=null){
                shaderSource.append(line).append("\n");
            }
            reader.close();
        }catch(IOException e){
            System.err.println("Could not read file!");
            e.printStackTrace();
            System.exit(-1);
        }
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS)== GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader.");
            System.exit(-1);
        }
        return shaderID;

    }

}