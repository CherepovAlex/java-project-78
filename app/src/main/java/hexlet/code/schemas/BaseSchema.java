package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    private Map<String, Predicate<T>> validations = new HashMap<>();

    protected void addValidation(String key, Predicate<T> validation) {
        this.validations.put(key, validation);
    }

    public boolean isValid(T value) {
        if (validations.containsKey("required") && (value == null
                || (value instanceof String && ((String) value).isEmpty()))) {
            return false;
        }
        if (value == null || (value instanceof String && ((String) value).isEmpty())) {
            return true;
        }
        return validations.values().stream().allMatch(predicate -> predicate.test(value));
    };
}
