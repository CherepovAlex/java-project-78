package hexlet.code;

import hexlet.code.schemas.IntegerSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {

    public StringSchema string() {
        return new StringSchema();
    }

    public IntegerSchema integer() {
        return new IntegerSchema();
    }
}
