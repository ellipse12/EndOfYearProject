package Engine.saving;

import Engine.MainClass;
import Engine.Scene;
import Engine.models.Light;
import Engine.models.Model;
import Engine.models.WorldObject;
import Engine.registration.Registry;
import Engine.rendering.Camera;
import Engine.resourceLoading.Loader;
import Engine.resourceLoading.Texture;
import Engine.util.ResourceLocation;
import GameTest.Player;
import org.joml.Vector3f;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.function.Supplier;

public class JsonParser {


    public static void loadSave(Scene scene, String fileName){
           try{
               String content = readFile(fileName);
               JSONArray array = new JSONArray(content);
               for(int i =0; i < array.length(); i++){
                   JSONObject obj = array.getJSONObject(i);
                   String type = obj.getString("type");

                   if(type.equals("worldObject")){
                       String id = obj.getString("id");
                    if(Registry.getObjectRegistry().keySet().contains(id)){
                        WorldObject object = Registry.getObject(id).get().deserialize(obj);
                        scene.addObject(object);
                    }
                   }else if(type.equals("light")){

                       Light light = Registry.getLight().get().deserialize(obj);
                       scene.addLight(light);

                   }
               }
           }catch(JSONException | IOException e){
             throw new RuntimeException(e);
           }
    }





    private static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceLocation.getFileStream("saves/" + file)));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();
        String content = stringBuilder.toString();

        return content;
    }

    public static void save(Scene scene, String name) throws IOException {
        JSONArray out = new JSONArray();
        for(WorldObject object : scene.getObjects()){
                out.put(object.serialize());
        }
        for(Light light : scene.getLights()){
            out.put(light.serialize());
        }
        File file = null;
        try {
            file = new File(JsonParser.class.getClassLoader().getResource("saves/" + name + ".json").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        FileWriter writer = new FileWriter("src/main/resources/saves/" + name + ".json");
        writer.write(out.toString());
        writer.close();


    }


    private static JSONObject createPlayerEntry(Player player){
        try{

            JSONObject temp = new JSONObject();
            temp.put("position", createVector3fEntry(player.getCamera().getPosition()));
            temp.put("rotation", createVector3fEntry(player.getCamera().getRotation()));
            return temp;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static JSONArray createVector3fEntry(Vector3f vector3f){
        JSONArray out = new JSONArray();
        try {
            out.put(vector3f.x).put(vector3f.y).put(vector3f.z);
        }catch (JSONException e){
            throw new RuntimeException(e);
        }
        return out;
    }

    private static JSONObject createWorldObjectEntry(WorldObject object){

        try{

            JSONObject temp = new JSONObject();
            temp.put("model", new JSONObject().put("texture", object.getModel().getTexture().getFilename()).put("modelFile", MainClass.loader.getResourceFromID(object.getModel().getVaoID())));
            temp.put("position", createVector3fEntry(object.getPosition()));
            temp.put("rotation", createVector3fEntry(object.getRotation()));
            temp.put("scale", createVector3fEntry(object.getScale()));

            return temp;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static JSONObject createLightEntry(Light light){
        try{

            JSONObject temp = new JSONObject();
            temp.put("position", createVector3fEntry(light.getPosition()));
            temp.put("color",createVector3fEntry(light.getColor()));
            temp.put("intensity", light.getIntensity());

            return temp;
        }catch(JSONException e){
            throw new RuntimeException(e);
        }
    }

}
