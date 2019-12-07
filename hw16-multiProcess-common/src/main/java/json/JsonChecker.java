package json;

import com.google.gson.JsonObject;

import java.util.Set;

public interface JsonChecker {
    String getType();
    void setJsonObject(JsonObject jsonObject, Set<String> validTypes);
    JsonObject getJsonObject();
}
