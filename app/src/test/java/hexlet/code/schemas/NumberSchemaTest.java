package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberSchemaTest {

    @Test
    void range() {
        var schema = new NumberSchema().range(5, 10);
        assertEquals(schema.isValid(6), true);
        assertEquals(schema.isValid(11), false);
        assertEquals(schema.isValid(5), true);
        assertEquals(schema.isValid(10), true);
        assertEquals(schema.isValid(4), false);
    }

    @Test
    void positive() {
        var schema = new NumberSchema().positive();
        assertEquals(schema.isValid(5), true);
        assertEquals(schema.isValid(0), false);
        assertEquals(schema.isValid(null), true);
        assertEquals(schema.isValid(-1), false);
    }

    @Test
    void required() {
        var schema = new NumberSchema().required();
        assertEquals(schema.isValid(1), true);
        assertEquals(schema.isValid(null), false);
        assertEquals(schema.isValid(0), true);
        assertEquals(schema.isValid(-1), true);
    }

    @Test
    void combination() {
        var v = new Validator();
        var schema = v.number().required().range(5, 10).positive().range(7, 11);
        assertEquals(schema.isValid(8), true);
        assertEquals(schema.isValid(null), false);
        assertEquals(schema.isValid(6), false);
    }

    @Test
    void beforeAndAfter() {
        var schema = new NumberSchema();
        assertEquals(schema.isValid(null), true);
        schema.required();
        assertEquals(schema.isValid(null), false);
        assertEquals(schema.isValid(10), true);
    }
}
