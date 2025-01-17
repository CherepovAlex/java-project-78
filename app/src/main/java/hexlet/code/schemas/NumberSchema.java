package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addValidation("required", Objects::nonNull);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addValidation("maxValue", o -> min <= o && o <= max);
        return this;
    }

    public NumberSchema positive() {
        addValidation("positive", o -> o == null || o > 0);
        return this;
    }
}
