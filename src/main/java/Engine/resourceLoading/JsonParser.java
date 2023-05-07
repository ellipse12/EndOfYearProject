package Engine.resourceLoading;

import Engine.models.Model;
import Engine.models.WorldObject;
import Engine.util.ResourceLocation;
import org.joml.Vector3f;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

public class JsonParser {

    public static WorldObject parseFile(String file, Loader loader) throws JSONException, IOException {
            JSONObject object;
            String contents = readFile(file);
            object = new JSONObject(contents);
            JSONObject model = object.getJSONObject("model");
            Model model1 = loader.getNormalModelFromResource(model.getString("modelFile"), new Texture(model.getString("texture")));
            JSONArray pos = object.getJSONArray("position");
            Vector3f position = pos == null ? new Vector3f() : new Vector3f((float) pos.getDouble(0), (float) pos.getDouble(1), (float) pos.getDouble(2));
            JSONArray rot = object.getJSONArray("rotation");
            Vector3f rotation = rot == null ? new Vector3f(): new Vector3f((float) rot.getDouble(0), (float) rot.getDouble(1), (float) rot.getDouble(2));
            JSONArray sc = object.optJSONArray("scale");
            Vector3f scale = sc == null ? new Vector3f(1) : new Vector3f((float) sc.getDouble(0), (float) sc.getDouble(1), (float) sc.getDouble(2));
            return new WorldObject(model1, position, rotation, scale);



    }

    public static String[] getFilesInDirectory(String dir){
        String[] files;
        try {
            files = new File(ResourceLocation.getURI(dir)).list((dir1, name) -> {
                if(name.endsWith(".json")){
                    return true;
                }
                return false;
            });
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

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

    public static void createSaveFile(WorldObject object, String name, String modelFile){

        try{
            JSONObject model = new JSONObject();
            model.put("model", new JSONObject().put("texture", object.getModel().getTexture().getFilename()).put("modelFile", modelFile));
            model.put("position", new JSONArray().put(object.getPosition().x).put(object.getPosition().y).put(object.getPosition().z));
            model.put("rotation", new JSONArray().put(object.getRotation().x).put(object.getRotation().y).put(object.getRotation().z));
            model.put("scale", new JSONArray().put(object.getScale().x).put(object.getScale().y).put(object.getScale().z));
            File file = new File("src/main/resources/saves/" + name + ".json");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(model.toString());
            writer.close();

        } catch (IOException | JSONException e) {
            System.err.println("could not find saves/" + name + ".json");
            throw new RuntimeException(e);
        }
    }

}
