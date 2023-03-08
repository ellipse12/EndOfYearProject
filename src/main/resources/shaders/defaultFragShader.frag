#version 400 core

in vec2 outTextureCoord;
out vec4 color;

uniform sampler2D texture_sampler;

void main() {
    color = texture(texture_sampler, outTextureCoord);
}