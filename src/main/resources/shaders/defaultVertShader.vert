#version 400 core

in vec3 position;
in vec2 textureCoord;
in vec3 vertexNormal;

out vec2 outTextureCoord;
out vec3 mvVertexPos;
out vec3 mvVertexNormal;
out vec3 toCamera;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;



void main() {


    vec4 worldPos = transformationMatrix * vec4(position, 1.0);
    vec4 relPos = viewMatrix * worldPos;
    gl_Position =projectionMatrix * relPos;
    outTextureCoord = textureCoord;


    mvVertexNormal = normalize(transformationMatrix * vec4(vertexNormal,0.0)).xyz;
    mvVertexPos = worldPos.xyz;
}

