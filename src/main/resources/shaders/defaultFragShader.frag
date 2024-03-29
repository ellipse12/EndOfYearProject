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

struct Light{
     vec3 color;
     vec3 position;
     float intensity;
};



uniform Material material;
uniform int numLights;
uniform Light lights[30];
uniform vec3 camera_position;
uniform vec3 ambientLight;
uniform float specularPower;



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


     vec3 light_direction = light.position - position;
     vec3 to_light_source  = normalize(light_direction);
     float diffuseFactor = max(dot(normal, to_light_source ), 0.0);
     diffuseColour = diffuseC * vec4(light.color, 1.0) * light.intensity * diffuseFactor;


     vec3 camera_direction = normalize(-position);
     vec3 from_light_source = -to_light_source;
     vec3 reflected_light = normalize(reflect(from_light_source, normal));
     float specularFactor = max( dot(camera_direction, reflected_light), 0.0);
     specularFactor = pow(specularFactor, specularPower);
     specColour = specularC * specularFactor * material.reflectance * vec4(light.color, 1.0);

     float distance = length(light_direction);
     float attenuationInv = 1 + 0.05 * distance +
     0.04 * distance * distance;

     return (diffuseColour + specColour) / attenuationInv;
}


void main() {

     setupColors(material, outTextureCoord);

     vec4 iut = vec4(0,0,0,0);
     for(int i =0; i < numLights; i++){
          iut += calcPointLight(lights[i], mvVertexPos, mvVertexNormal);
     }


    color = ambientC * vec4(ambientLight, 1) +iut;


}
