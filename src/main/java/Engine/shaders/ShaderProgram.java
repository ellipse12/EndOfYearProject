package Engine.shaders;

import Engine.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public abstract class ShaderProgram {

    private final int programID;
    private final int vertexShaderID;
    private final int fragmentShaderID;

    private final Set<UniformI> uniforms;


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
        uniforms = new HashSet<>();

    }


    /**
     * adds a uniform to the set
     * @param uniform the uniform to add
     */
    public <T> void addUniform(UniformI<T> uniform) {
        uniforms.add(uniform);
    }


    /**
     * sets a uniform to a value
     * @param name the uniform to set
     * @param value the value to set
     */
    public <T> void setUniform(String name, T value) {
        for(UniformI uniform:uniforms){
            if(uniform.getName().equals(name)){
                uniform.load(value);
            }
        }
    }

    /**
     * should be used in getAllUniformLocations()
     * @param uniformName the name of the uniform variable
     * @return the integer representation of the location of the uniform given
     */
    public int getUniformLocation(String uniformName){
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
     * loads and compiles an external shader source
     * @param file the file location of the shader
     * @param type the type of shader (vertex, fragment, etc.)
     * @return the location in OpenGL's memory of the shader in question
     */
    private static  int loadShader(String file, int type){
        StringBuilder shaderSource = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceLocation.getFileStream(file))); //load the file as an InputStream
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
        GL20.glCompileShader(shaderID); //compiles the shader
        if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS)== GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader.");
            System.exit(-1);
        }
        return shaderID;

    }

}