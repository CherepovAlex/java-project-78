package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String, StringSchema> {

    public StringSchema required() {
        addValidation("required", o -> !(o.isEmpty()));
        return this;
    }

    public StringSchema minLength(int min) {
        addValidation("minLength", o -> o.length() >= min);
        return this;
    }

    public StringSchema contains(String str) {
        addValidation("contains", o -> o.contains(str));
        return this;
    }
}
