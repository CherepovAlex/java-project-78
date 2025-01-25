package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map<String, ?>> {

    public MapSchema required() {
        addValidation("required", Objects::nonNull);
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
                T value = (T) map.get(key);
                return schema.isValid(value);
            });
            }
        );
        return this;
    }
}
