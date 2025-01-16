package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<String, String>> {

    public MapSchema required() {
        addValidation("required", o -> o != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        addValidation("minLength", o -> o.size() == size);
        return this;
    }
}
