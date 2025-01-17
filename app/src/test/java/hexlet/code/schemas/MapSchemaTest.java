package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapSchemaTest {

    private Map<String, Object> map1;
    private Map<String, Object> map2;
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
        assertEquals(false, schema.isValid(Map.of()));
    }

    @Test
    void required() {
        schema.required();
        assertEquals(true, schema.isValid(map2));
        assertEquals(false, schema.isValid(null));
    }

    @Test
    void combination() {
        var schema1 = validator.map().required().sizeof(2);
        assertEquals(false, schema1.isValid(map1));
        assertEquals(false, schema1.isValid(null));
        assertEquals(true, schema1.isValid(map2));
    }

    @Test
    void beforeAndAfter() {
        assertEquals(true, schema.isValid(null));
        schema.required();
        assertEquals(false, schema.isValid(null));
        assertEquals(true, schema.isValid(map2));
    }

    @Test
    void shapeValid() {
        schema = validator.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required().contains("hn"));
        schemas.put("lastName", validator.string().required().minLength(2));
        schema.shape(schemas);

        Map<String, Object> validMap = Map.of(
                "firstName", "John",
                "lastName", "Smith"
        );
        assertEquals(true, schema.isValid(validMap));
    }

    @Test
    void shapeInvalid() {
        schema = validator.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));
        schema.shape(schemas);

        Map<String, Object> invalidMap1 = new HashMap<>();
        invalidMap1.put("firstName", "John");
        invalidMap1.put("lastName", null);
        Map<String, Object> invalidMap2 = new HashMap<>();
        invalidMap1.put("lastName", "Smith");
        Map<String, Object> invalidMap3 = new HashMap<>();
        invalidMap1.put("firstName", "");
        invalidMap1.put("age", "Smith");

        assertEquals(false, schema.isValid(invalidMap1));
        assertEquals(false, schema.isValid(invalidMap2));
        assertEquals(false, schema.isValid(invalidMap3));
    }

    @Test
    void shapeWithNestedMaps() {
        schema = validator.map();
        Map<String, BaseSchema<String>> schemas1 = new HashMap<>();
        schemas1.put("firstName", validator.string().required());
        schemas1.put("lastName", validator.string().required().minLength(2));

        schema.shape(Map.of("job", validator.map().shape(schemas1)));
        Map<String, Object> validMap = new HashMap<>();
        Map<String, String> nestedValidMap = new HashMap<>();
        nestedValidMap.put("firstName", "John");
        nestedValidMap.put("lastName", "Smith");
        validMap.put("job", nestedValidMap);

        Map<String, Object> invalidMap = new HashMap<>();
        Map<String, String> nestedInvalidMap = new HashMap<>();
        nestedInvalidMap.put("firstName", "John");
        nestedInvalidMap.put("lastName", null); // null значение
        invalidMap.put("job", nestedInvalidMap);

        assertEquals(true, schema.isValid(validMap));
        assertEquals(false, schema.isValid(invalidMap));
    }
}
