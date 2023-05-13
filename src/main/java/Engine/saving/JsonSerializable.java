package Engine.saving;

import org.joml.Vector3f;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface JsonSerializable<T> {
    /**
     * @return serialize the object into a JSONObject
     */
    JSONObject serialize();

    /**
     * @param object the input object
     * @return an object created from the JSONObject
     */
    T deserialize(JSONObject object);

    /**
     * @param vector3f the vector3f to serialize into json
     * @return a json array representing a vector3f
     */
    default JSONArray createVector3fEntry(Vector3f vector3f){
        JSONArray out = new JSONArray();
        try {
            out.put(vector3f.x).put(vector3f.y).put(vector3f.z);
        }catch (JSONException e){
            throw new RuntimeException(e);
        }
        return out;
    }

    /**
     * @param array the json array representing a vector3f
     * @return a vector3f from the json array
     */
    default Vector3f parseVector3fEntry(JSONArray array){
        try{
            Vector3f out = new Vector3f((float) array.getDouble(0), (float) array.getDouble(1), (float) array.getDouble(2));
            return out;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
