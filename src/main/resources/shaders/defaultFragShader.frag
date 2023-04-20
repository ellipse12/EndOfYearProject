#version 400 core

in vec2 outTextureCoord;
in float visibility;

out vec4 color;

uniform sampler2D texture_sampler;

const vec3 skyColor = vec3(1.0, 1.0, 1.0);

void main() {

     vec4 textCol =  texture(texture_sampler, outTextureCoord);
     color = mix(vec4(skyColor, 1.0), textCol, visibility);


}