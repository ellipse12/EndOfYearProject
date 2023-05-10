package Engine.saving;

import org.joml.Vector3f;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface JsonSerializable<T> {
    JSONObject serialize();

    T deserialize(JSONObject object);

    default JSONArray createVector3fEntry(Vector3f vector3f){
        JSONArray out = new JSONArray();
        try {
            out.put(vector3f.x).put(vector3f.y).put(vector3f.z);
        }catch (JSONException e){
            throw new RuntimeException(e);
        }
        return out;
    }

    default Vector3f parseVector3fEntry(JSONArray array){
        try{
            Vector3f out = new Vector3f((float) array.getDouble(0), (float) array.getDouble(1), (float) array.getDouble(2));
            return out;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
