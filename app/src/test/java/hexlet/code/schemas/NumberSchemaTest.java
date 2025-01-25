package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberSchemaTest {

    @Test
    void range() {
        var schema = new NumberSchema().range(5, 10);
        assertEquals(true, schema.isValid(6));
        assertEquals(false, schema.isValid(11));
        assertEquals(true, schema.isValid(5));
        assertEquals(true, schema.isValid(10));
        assertEquals(false, schema.isValid(4));
    }

    @Test
    void positive() {
        var schema = new NumberSchema().positive();
        assertEquals(true, schema.isValid(5));
        assertEquals(false, schema.isValid(0));
        assertEquals(true, schema.isValid(null));
        assertEquals(false, schema.isValid(-1));
    }

    @Test
    void required() {
        var schema = new NumberSchema();
        assertEquals(true, schema.isValid(null));
        schema.required();
        assertEquals(true, schema.isValid(1));
        assertEquals(false, schema.isValid(null));
        assertEquals(true, schema.isValid(0));
        assertEquals(true, schema.isValid(-1));
    }

    @Test
    void combination() {
        var v = new Validator();
        var schema = v.number().required().range(5, 10).positive().range(7, 11);
        assertEquals(true, schema.isValid(8));
        assertEquals(false, schema.isValid(null));
        assertEquals(false, schema.isValid(6));
    }

}
