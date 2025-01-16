package hexlet.code.schemas;

public class IntegerSchema extends BaseSchema<Integer> {

    public IntegerSchema required() {
        addValidation("required", o -> o != null);
        return this;
    }

    public IntegerSchema maxValue(int max) {
        addValidation("maxValue", o -> o < max);
        return this;
    }

    public IntegerSchema isPositive() {
        addValidation("isPositive", o -> o > 0);
        return this;
    }
}
