#version 400 core

in vec2 outTextureCoord;
in float visibility;

out vec4 color;

uniform sampler2D texture_sampler;
struct Material{
     vec4 ambient;
     vec4 diffuse;
     vec4 specular;
     int hasTexture;
     float reflectance;
};
uniform Material material;

const vec3 skyColor = vec3(1.0, 1.0, 1.0);

void main() {

     vec4 textCol =  texture(texture_sampler, outTextureCoord);
     color = mix(vec4(skyColor, 1.0), textCol, visibility);


}
