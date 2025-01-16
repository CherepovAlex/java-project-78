package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addValidation("required", o -> o != null);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addValidation("maxValue", o -> min <= o && o <= max);
        return this;
    }

    public NumberSchema positive() {
        addValidation("positive", o -> o > 0);
        return this;
    }
}
