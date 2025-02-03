package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapSchemaTest {

    @Test
    void testSizeof() {
        Validator validator = new Validator();
        var schema = validator.map().sizeof(2);

        var map1 = Map.of("key1", "value1");
        var map2 = Map.of("key1", "value1", "key2", "value2");

        assertEquals(true, schema.isValid(map2));
        assertEquals(true, schema.isValid(null));
        assertEquals(false, schema.isValid(map1));
        assertEquals(false, schema.isValid(Map.of()));

        Map<String, BaseSchema<String, ?>> schemas = new HashMap<>();
        schemas.put("key1", validator.string().required());
        schemas.put("key2", validator.string().required());
        var nestedSchema = validator.map().shape(schemas);

        Map<String, Object> nestedMap1 = new HashMap<>();
        nestedMap1.put("key1", "value1");
        Map<String, Object> nestedMap2 = new HashMap<>();
        nestedMap2.put("key1", "value1");
        nestedMap2.put("key2", "value2");

        assertEquals(true, nestedSchema.isValid(nestedMap2));
        assertEquals(false, nestedSchema.isValid(nestedMap1));

    }

    @Test
    void testRequired() {
        Validator validator = new Validator();
        var schema = validator.map();

        var map = Map.of("key1", "value1");
        assertEquals(true, schema.isValid(null));
        assertEquals(true, schema.isValid(map));
        schema.required();
        assertEquals(true, schema.isValid(map));
        assertEquals(false, schema.isValid(null));

        Map<String, BaseSchema<String, ?>> schemas = new HashMap<>();
        schemas.put("key1", validator.string().required());
        var nestedSchema = validator.map().shape(schemas);

        Map<String, Object> nestedMap = new HashMap<>();
        nestedMap.put("key1", "value1");

        assertEquals(true, nestedSchema.isValid(nestedMap));
        assertEquals(true, schema.isValid(Map.of("nested", nestedMap)));
    }

    @Test
    void testShapeValidNInvalid() {
        Validator validator = new Validator();
        var schema = validator.map();

        Map<String, BaseSchema<String, ?>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required().contains("hn"));
        schemas.put("lastName", validator.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, Object> validMap = new HashMap<>();
        validMap.put("firstName", "John");
        validMap.put("lastName", "Smith");

        Map<String, Object> invalidMap1 = new HashMap<>();
        invalidMap1.put("firstName", "John");
        invalidMap1.put("lastName", null);

        Map<String, Object> invalidMap2 = new HashMap<>();
        invalidMap2.put("lastName", "Smith");

        Map<String, Object> invalidMap3 = new HashMap<>();
        invalidMap3.put("firstName", "");
        invalidMap3.put("lastName", "Smith");

        assertEquals(true, schema.isValid(validMap));
        assertEquals(false, schema.isValid(invalidMap1));
        assertEquals(false, schema.isValid(invalidMap2));
        assertEquals(false, schema.isValid(invalidMap3));

        Map<String, BaseSchema<String, ?>> nestedSchemas = new HashMap<>();
        nestedSchemas.put("firstName", validator.string().required().contains("hn"));
        nestedSchemas.put("lastName", validator.string().required().minLength(2));

        var nestedSchema = validator.map().shape(nestedSchemas);

        Map<String, Object> nestedValidMap = new HashMap<>();
        nestedValidMap.put("firstName", "John");
        nestedValidMap.put("lastName", "Smith");

        Map<String, Object> nestedInvalidMap = new HashMap<>();
        nestedInvalidMap.put("firstName", "John");
        nestedInvalidMap.put("lastName", null);

        assertEquals(true, nestedSchema.isValid(nestedValidMap));
        assertEquals(false, nestedSchema.isValid(nestedInvalidMap));
    }

    @Test
    void testCombination() {
        Validator validator = new Validator();
        var schema = validator.map().required().sizeof(2);

        var map1 = Map.of("key1", "value1");
        var map2 = Map.of("key1", "value1", "key2", "value2");

        assertEquals(false, schema.isValid(map1));
        assertEquals(false, schema.isValid(null));
        assertEquals(true, schema.isValid(map2));

        Map<String, BaseSchema<String, ?>> schemas = new HashMap<>();
        schemas.put("key1", validator.string().required());
        schemas.put("key2", validator.string().required());

        var nestedSchema = validator.map().shape(schemas);

        Map<String, Object> nestedMap1 = new HashMap<>();
        nestedMap1.put("key1", "value1");

        Map<String, Object> nestedMap2 = new HashMap<>();
        nestedMap2.put("key1", "value1");
        nestedMap2.put("key2", "value2");

        assertEquals(false, nestedSchema.isValid(nestedMap1));
        assertEquals(true, nestedSchema.isValid(nestedMap2));
    }
}
