package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer, NumberSchema> {

    public NumberSchema range(int min, int max) {
        addValidation("range", o -> min <= o && o <= max);
        return this;
    }

    public NumberSchema positive() {
        addValidation("positive", o -> o > 0);
        return this;
    }
}
