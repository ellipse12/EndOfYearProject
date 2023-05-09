package Engine.resourceLoading;

import Engine.MainClass;
import Engine.Scene;
import Engine.models.Light;
import Engine.models.Model;
import Engine.models.WorldObject;
import Engine.rendering.Camera;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Objects;

public class JsonParser {

    public static Scene parseFile(String file, Loader loader) throws IOException, JSONException {

            JSONObject object;
        String contents = null;

            contents = readFile(file);

        //System.out.println(contents);
           object = new JSONObject(contents);
           Player player = parsePlayerEntry(object.getJSONObject("player"));
           Scene scene = new Scene(player);
           JSONArray lights = object.getJSONArray("lights");
           JSONArray objects = object.getJSONArray("objects");
           for(int i = 0; i < lights.length(); i++){
               scene.addLight(parseLightEntry(lights.getJSONObject(i)));

           }
           for(int k = 0; k < objects.length(); k++){
               scene.addObject(parseWorldObjectEntry(objects.getJSONObject(k), loader));
           }
           return scene;



    }

    private static Player parsePlayerEntry(JSONObject player){
        try {
            Vector3f position = parseVector3f(player.getJSONArray("position"));
            Vector3f rotation = parseVector3f(player.getJSONArray("rotation"));
            return new Player(new Camera(position, rotation));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static WorldObject parseWorldObjectEntry(JSONObject object, Loader loader){
        try{
            Vector3f position = parseVector3f(object.getJSONArray("position"));
            Vector3f rotation = parseVector3f(object.getJSONArray("rotation"));
            Vector3f scale = parseVector3f(object.getJSONArray("scale"));
            JSONObject m = object.getJSONObject("model");
            Model model = loader.getNormalModelFromResource(m.getString("modelFile"), new Texture(m.getString("texture")));
            return new WorldObject(model, position, rotation, scale);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static Light parseLightEntry(JSONObject light){
        try{
            Vector3f position = parseVector3f(light.getJSONArray("position"));
            Vector3f color = parseVector3f(light.getJSONArray("color"));
            float intensity = (float) light.getDouble("intensity");
            return new Light(color, position, intensity);
        }catch (JSONException e){
            throw new RuntimeException(e);
        }
    }

    private static Vector3f parseVector3f(JSONArray vector){
        if(vector.length() != 3){
            throw new RuntimeException("Vector must be of length 3");
        }
        try {
            return new Vector3f((float) vector.getDouble(0), (float) vector.getDouble(1), (float) vector.getDouble(2));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] getFilesInDirectory(String dir){
        String[] files;

            files = new File("/"+dir).list((dir1, name) -> {
                if(name.endsWith(".json")){
                    return true;
                }
                return false;
            });


        return files;
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
    private static String readFile(URL file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.openStream()));

        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        return stringBuilder.toString();
    }

    public static void createSaveFile(Scene scene, String name){
        JSONObject out = new JSONObject();
        JSONArray lights = new JSONArray();
        JSONArray objects = new JSONArray();
        for(WorldObject object: scene.getObjects()){
            objects.put(createWorldObjectEntry(object));
        }for(Light light: scene.getLights()){
           lights.put(createLightEntry(light));
        }
        try {
            out.put("player", createPlayerEntry(scene.getPlayer()));
            out.put("lights", lights);
            out.put("objects", objects);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try{
            File file = new File("src/main/resources/saves/" + name + ".json");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(out.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
