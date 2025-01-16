package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    private Map<String, Predicate<T>> validations = new HashMap<>();

    protected void addValidation(String key, Predicate<T> validation) {
        this.validations.put(key, validation);
    }

    public boolean isValid(T o) {
        if (validations.containsKey("required") && (o == null || o.equals(""))) {
            return false;
        }
        if (o == null || o.equals("")) {
            return true;
        }
        return validations.values().stream().allMatch(value -> value.test(o));
    };

}
