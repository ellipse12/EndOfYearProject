#version 100

in vec3 position;

out vec3 color;

uniform mat4 transformationMatrix;
void main() {

    gl_Position = transformationMatrix * vec4(position, 1.0);
    color = vec3(1.0,1.0,1.0);
}