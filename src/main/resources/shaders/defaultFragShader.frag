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
uniform float specularPower;
uniform float test[3];


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
vec4 calcPointLight(Light light, vec3 position, vec3 normal)
{
     vec4 diffuseColour = vec4(0, 0, 0, 0);
     vec4 specColour = vec4(0, 0, 0, 0);

     // Diffuse Light
     vec3 light_direction = light.position - position;
     vec3 to_light_source  = normalize(light_direction);
     float diffuseFactor = max(dot(normal, to_light_source ), 0.0);
     diffuseColour = diffuseC * vec4(light.color, 1.0) * light.intensity * diffuseFactor;

     // Specular Light
     vec3 camera_direction = normalize(-position);
     vec3 from_light_source = -to_light_source;
     vec3 reflected_light = normalize(reflect(from_light_source, normal));
     float specularFactor = max( dot(camera_direction, reflected_light), 0.0);
     specularFactor = pow(specularFactor, specularPower);
     specColour = specularC * specularFactor * material.reflectance * vec4(light.color, 1.0);

     // Attenuation
     float distance = length(light_direction);
     float attenuationInv = light.att.constant + light.att.linear * distance +
     light.att.exponent * distance * distance;
     return (diffuseColour + specColour);
}


void main() {

     setupColors(material, outTextureCoord);

     vec4 iut = calcPointLight(light, mvVertexPos, mvVertexNormal);


    color = ambientC * vec4(ambientLight, 1) +iut;


}
