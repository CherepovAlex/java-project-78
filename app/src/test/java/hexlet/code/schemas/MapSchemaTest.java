package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapSchemaTest {

    private Map<String, String> map1;
    private Map<String, String> map2;
    private MapSchema schema;
    private Validator validator;

    @BeforeEach
    void setUp() {
        map1 = Map.of("key1", "value1");
        map2 = Map.of("key1", "value1", "key2", "value2");
        schema = new MapSchema();
        validator = new Validator();
    }

    @Test
    void sizeof() {
        schema.sizeof(2);
        assertEquals(true, schema.isValid(map2));
        assertEquals(true, schema.isValid(null));
        assertEquals(false, schema.isValid(map1));
    }

    @Test
    void required() {
        schema.required();
        assertEquals(true, schema.isValid(map2));
        assertEquals(false, schema.isValid(null));
    }

    @Test
    void combination() {
        var schema = validator.map().required().sizeof(2);
        assertEquals(false, schema.isValid(map1));
        assertEquals(false, schema.isValid(null));
        assertEquals(true, schema.isValid(map2));
    }

    @Test
    void beforeAndAfter() {
        assertEquals(true, schema.isValid(null));
        schema.required();
        assertEquals(false, schema.isValid(null));
        assertEquals(true, schema.isValid(map2));
    }
}
