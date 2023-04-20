#version 400 core

in vec3 position;
in vec2 textureCoord;
in vec3 vertexNormal;

out vec2 outTextureCoord;
out float visibility;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

const float density = 0.0;
const float gradient = 1.0;

void main() {


    vec4 worldPos = transformationMatrix * vec4(position, 1.0);
    vec4 relPos = viewMatrix * worldPos;
    gl_Position = projectionMatrix * relPos;
    outTextureCoord = textureCoord;

    float distance = length(relPos.xyz);
    visibility = exp(-pow((distance*density), gradient));
    visibility = clamp(visibility, 0.0, 1.0);

}