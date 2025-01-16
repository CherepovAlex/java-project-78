package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringSchemaTest {

    @Test
    void contains() {
        var schema = new StringSchema().contains("wh");
        assertEquals(schema.isValid("what does the fox say"), true);
        assertEquals(schema.isValid("hexlet"), false);
        assertEquals(schema.isValid(null), true);
        assertEquals(schema.isValid(""), true);
    }

    @Test
    void minLength() {
        var schema = new StringSchema().minLength(5);
        assertEquals(schema.isValid("hexlet"), true);
        assertEquals(schema.isValid("who"), false);
        assertEquals(schema.isValid(null), true);
        assertEquals(schema.isValid(""), true);
    }

    @Test
    void required() {
        var schema = new StringSchema().required();
        assertEquals(schema.isValid("hexlet"), true);
        assertEquals(schema.isValid(null), false);
        assertEquals(schema.isValid(""), false);
    }

    @Test
    void combination() {
        var v = new Validator();
        var schema = v.string().required().minLength(10).minLength(4).contains("xl");
        assertEquals(schema.isValid("hexlet"), true);
        assertEquals(schema.isValid(null), false);
        assertEquals(schema.isValid(""), false);
    }

    @Test
    void beforeAndAfter() {
        var schema = new StringSchema();
        assertEquals(schema.isValid(null), true);
        schema.required();
        assertEquals(schema.isValid(null), false);
        assertEquals(schema.isValid("hex"), true);
    }
}
