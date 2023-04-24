#version 400 core

in vec2 outTextureCoord;
in float visibility;
in vec3 mvVertexPos;
in vec3 mvVertexNormal;

out vec4 color;

uniform sampler2D texture_sampler;
struct Material{
     vec4 ambient;
     vec4 diffuse;
     vec4 specular;
     int hasTexture;
     float reflectance;
};
struct Attenuation{
     float constant;
     float linear;
     float exponent;
};

struct Light{
     vec3 color;
     vec3 position;
     float intensity;
     Attenuation att;
};



uniform Material material;
uniform Light light;
uniform vec3 camera_position;
uniform vec3 ambientLight;


const vec3 skyColor = vec3(1.0, 1.0, 1.0);

vec4 ambientC;
vec4 diffuseC;
vec4 specularC;

void setupColors(Material material, vec2 textCoord){
     if(material.hasTexture == 1){
          ambientC = texture(texture_sampler, textCoord);
          diffuseC = ambientC;
          specularC = ambientC;
     }else{
          ambientC = material.ambient;
          diffuseC = material.diffuse;
          specularC = material.specular;
     }
}


void main() {

     setupColors(material, outTextureCoord);
     vec3 light_direction = light.position - mvVertexPos;
     vec3 to_light_source  = normalize(light_direction);
     float diffuseFactor = max(dot(mvVertexNormal, to_light_source ), 0.0);
     vec4 diffuseColour = diffuseC * vec4(light.color, 1.0) * light.intensity * diffuseFactor;

     vec3 camera_direction = normalize(-camera_position);
     vec3 fromLight = -to_light_source;
     vec3 reflected = normalize(reflect(fromLight, mvVertexNormal));
     float specular_factor = max(dot(camera_direction, reflected), 0.0);
     specular_factor = pow(specular_factor, 0.5f);

     vec4 spec_color = specularC * specular_factor * material.reflectance * vec4(light.color, 1.0);


     float dist = length(light_direction);
     float attenInv = light.att.constant + light.att.linear * dist + light.att.exponent * dist * dist;
     vec4 iut = (diffuseColour + spec_color)/attenInv;



     color = texture(texture_sampler, outTextureCoord) + diffuseC;


}
