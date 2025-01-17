package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<String, ?>> {

    public MapSchema required() {
        addValidation("required", o -> o != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        addValidation("sizeof", o -> o.size() == size);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addValidation("shape", map -> {
            return schemas.entrySet().stream().allMatch(entry -> {
                String key = entry.getKey();
                BaseSchema<T> schema = entry.getValue();
                if (!map.containsKey(key)) {
                    return false;
                }
                T value = (T) map.get(key);
                if (value == null) {
                    return false;
                }
                return schema.isValid(value);
            });
            }
        );
        return this;
    }
}
