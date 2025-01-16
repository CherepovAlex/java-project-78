package hexlet.code;

public class App {
    public static void main(String[] args) {

        var v1 = new Validator();
        var schema1 = v1.string().required().minLength(5).contains("hex");

        // Пока не вызван метод required(), null и пустая строка считаются валидным
        var v2 = new Validator();
        var schema2 = v2.string();
        System.out.println(schema2.isValid("")); // true
        System.out.println(schema2.isValid(null)); // true
        System.out.println();

        schema2.required();

        System.out.println(schema2.isValid(null)); // false
        System.out.println(schema2.isValid("")); // false
        System.out.println(schema2.isValid("what does the fox say")); // true
        System.out.println(schema2.isValid("hexlet")); // true
        System.out.println();

        System.out.println(schema2.contains("wh").isValid("what does the fox say")); // true
        System.out.println(schema2.contains("what").isValid("what does the fox say")); // true
        System.out.println(schema2.contains("whatthe").isValid("what does the fox say")); // false
        System.out.println();

        System.out.println(schema2.isValid("what does the fox say")); // false
        // Здесь уже false, так как добавлена еще одна проверка contains("whatthe")
        System.out.println();

        // Если один валидатор вызывался несколько раз
        // то последний имеет приоритет (перетирает предыдущий)
        var v3 = new Validator();
        var schema3 = v3.string();
        System.out.println(schema3.minLength(10).minLength(4).isValid("Hexlet")); // true
    }
}
