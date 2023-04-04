#version 400 core

in vec3 position;
in vec2 textureCoord;

out vec2 outTextureCoord;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

void main() {

    gl_Position =  projectionMatrix * transformationMatrix * viewMatrix  * vec4(position, 1.0);
    outTextureCoord = textureCoord;
}