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


    /**
     * loads a save instance from the disk
     * @param scene the current scene
     * @param fileName the location of the save file
     */
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

                   }else if(type.equals("player")){
                       scene.getPlayer().deserialize(obj);
                   }
               }
           }catch(JSONException | IOException e){
             throw new RuntimeException(e);
           }
    }


    /**
     * @param file the file to read
     * @return the content of the file
     *
     */
    private static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(ResourceLocation.getFileStream("saves/" + file))));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
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

    /**
     * @param scene the scene to save
     * @param name the name of the save
     *
     */
    public static void save(Scene scene, String name) throws IOException {
        JSONArray out = new JSONArray();
        for(WorldObject object : scene.getObjects()){
                out.put(object.serialize()); //serialize each object
        }
        for(Light light : scene.getLights()){
            out.put(light.serialize());
        }
        out.put(scene.getPlayer().serialize());
        File file = null;
        try {
            file = new File(JsonParser.class.getClassLoader().getResource("saves/" + name + ".json").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        FileWriter writer = new FileWriter("src/main/resources/saves/" + name + ".json"); //create a new file writer
        writer.write(out.toString());
        writer.close();


    }
}
