package Engine.saving;

import org.json.JSONObject;

public interface JsonSerializable<T> {
    JSONObject serialize();

    T deserialize(JSONObject object);
}
