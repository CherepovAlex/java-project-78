package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addValidation("required", o -> o != null && !(o.isEmpty()));
        return this;
    }

    public StringSchema minLength(int min) {
        addValidation("minLength", o -> o!= null && o.length() >= min);
        return this;
    }

    public StringSchema contains(String str) {
        addValidation("contains", o -> o == null || o.contains(str) );
        return this;
    }
}
