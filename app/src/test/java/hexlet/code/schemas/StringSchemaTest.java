package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringSchemaTest {

    @Test
    void contains() {
        var schema = new StringSchema().contains("wh");
        assertEquals(true, schema.isValid("what does the fox say"));
        assertEquals(false, schema.isValid("hexlet"));
        assertEquals(true, schema.isValid(null));
        assertEquals(true, schema.isValid(""));
    }

    @Test
    void minLength() {
        var schema = new StringSchema().minLength(5);
        assertEquals(true, schema.isValid("hexlet"));
        assertEquals(false, schema.isValid("who"));
        assertEquals(true, schema.isValid(null));
        assertEquals(true, schema.isValid(""));
    }

    @Test
    void required() {
        var schema = new StringSchema().required();
        assertEquals(true, schema.isValid("hexlet"));
        assertEquals(false, schema.isValid(null));
        assertEquals(false, schema.isValid(""));
    }

    @Test
    void combination() {
        var v = new Validator();
        var schema = v.string().required().minLength(10).minLength(4).contains("xl");
        assertEquals(true, schema.isValid("hexlet"));
        assertEquals(false, schema.isValid(null));
        assertEquals(false, schema.isValid(""));
    }

    @Test
    void beforeAndAfter() {
        var schema = new StringSchema();
        assertEquals(true, schema.isValid(null));
        schema.required();
        assertEquals(false, schema.isValid(null));
        assertEquals(true, schema.isValid("hex"));
    }
}
