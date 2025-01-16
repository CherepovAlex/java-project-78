package hexlet.code;

import hexlet.code.schemas.BaseSchema;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
//        System.out.println("Strings: ");
//        var v1 = new Validator();
//        var schema1 = v1.string().required().minLength(5).contains("hex");
//
//        // Пока не вызван метод required(), null и пустая строка считаются валидным
//        var v2 = new Validator();
//        var schema2 = v2.string();
//        System.out.println(schema2.isValid("")); // true
//        System.out.println(schema2.isValid(null)); // true
//        System.out.println();
//
//        schema2.required();
//
//        System.out.println(schema2.isValid(null)); // false
//        System.out.println(schema2.isValid("")); // false
//        System.out.println(schema2.isValid("what does the fox say")); // true
//        System.out.println(schema2.isValid("hexlet")); // true
//        System.out.println();
//
//        System.out.println(schema2.contains("wh").isValid("what does the fox say")); // true
//        System.out.println(schema2.contains("what").isValid("what does the fox say")); // true
//        System.out.println(schema2.contains("whatthe").isValid("what does the fox say")); // false
//        System.out.println();
//
//        System.out.println(schema2.isValid("what does the fox say")); // false
//        // Здесь уже false, так как добавлена еще одна проверка contains("whatthe")
//        System.out.println();
//
//        // Если один валидатор вызывался несколько раз
//        // то последний имеет приоритет (перетирает предыдущий)
//        var v3 = new Validator();
//        var schema3 = v3.string();
//        System.out.println(schema3.minLength(10).minLength(4).isValid("Hexlet")); // true
//
//        System.out.println("Numbers: ");
//        var v4 = new Validator();
//        var schema4 = v4.number();
//
//        System.out.println(schema4.isValid(5)); // true
//        System.out.println();
//        // Пока не вызван метод required(), null считается валидным
//        System.out.println(schema4.isValid(null)); // true
//        System.out.println(schema4.positive().isValid(null)); // true
//        System.out.println();
//
//        schema4.required();
//
//        System.out.println(schema4.isValid(null)); // false
//        System.out.println(schema4.isValid(10)); // true
//        System.out.println();
//        // Потому что ранее мы вызвали метод positive()
//        System.out.println(schema4.isValid(-10)); // false
//        //  Ноль — не положительное число
//        System.out.println(schema4.isValid(0)); // false
//        System.out.println();
//        schema4.range(5, 10);
//
//        System.out.println(schema4.isValid(5)); // true
//        System.out.println(schema4.isValid(10)); // true
//        System.out.println(schema4.isValid(4)); // false
//        System.out.println(schema4.isValid(11)); // false
//
        System.out.println("Maps: ");
        var v5 = new Validator();
        var schema5 = v5.map();

        System.out.println(schema5.isValid(null)); // true
        System.out.println();
        schema5.required();

        System.out.println(schema5.isValid(null)); // false
        System.out.println(schema5.isValid(new HashMap<>())); // true
        var data = new HashMap<String, Object>();
        data.put("key1", "value1");
        System.out.println(schema5.isValid(data)); // true
        System.out.println();
        schema5.sizeof(2);

        System.out.println(schema5.isValid(data));  // false
        data.put("key2", "value2");
        System.out.println(schema5.isValid(data)); // true

        var v6 = new Validator();

        var schema6 = v6.map();

        // shape позволяет описывать валидацию для значений каждого ключа объекта Map
        // Создаем набор схем для проверки каждого ключа проверяемого объекта
        // Для значения каждого ключа - своя схема
        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        // Определяем схемы валидации для значений свойств "firstName" и "lastName"
        // Имя должно быть строкой, обязательно для заполнения
        schemas.put("firstName", v6.string().required());
        // Фамилия обязательна для заполнения и должна содержать не менее 2 символов
        schemas.put("lastName", v6.string().required().minLength(2));

        // Настраиваем схему `MapSchema`
        // Передаем созданный набор схем в метод shape()
        schema6.shape(schemas);

        // Проверяем объекты
        Map<String, Object> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        System.out.println(schema6.isValid(human1)); // true
        System.out.println();

        Map<String, Object> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        System.out.println(schema6.isValid(human2)); // false
        System.out.println();

        Map<String, Object> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        System.out.println(schema6.isValid(human3)); // false
    }
}
