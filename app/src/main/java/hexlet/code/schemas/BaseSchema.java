package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * BaseSchema is an abstract class that provides a foundation for creating validation schemas.
 * It allows adding validation rules and checking if a value satisfies all the rules.
 *
 * @param <T> the type of value to be validated
 *            // * @param <S> the self-type parameter allowing methods to return an instance of the current class
 */
public abstract class BaseSchema<T> {

    private Map<String, Predicate<T>> validations = new HashMap<>();

    /**
     * Adds a validation rule to the schema.
     * <p>
     * This method is protected to allow subclasses to add custom validation rules.
     * If you override this method, ensure that the validation logic is consistent
     * with the base implementation.
     * </p>
     *
     * @param key        the unique identifier for the validation rule
     * @param validation the predicate that defines the validation rule
     */
    protected void addValidation(String key, Predicate<T> validation) {
        this.validations.put(key, validation);
    }

    /**
     * Validates the given value against all the validation rules in the schema.
     * <p>
     * If the schema has a "required" rule, null or empty values are considered invalid.
     * Otherwise, null or empty values are considered valid.
     * </p>
     *
     * @param value the value to be validated
     * @return true if the value satisfies all the validation rules, false otherwise
     */
    public boolean isValid(T value) {
        if (validations.containsKey("required") && (value == null)) {
            return false;
        }
        if (value == null) {
            return true;
        }
        return validations.values().stream().allMatch(predicate -> predicate.test(value));
    }

    /**
     * Marks the schema as requiring a non-null value.
     * <p>
     * When this method is called, the schema will consider null values as invalid.
     * This method adds a validation rule that checks if the value is non-null.
     * </p>
     *
     * @return the current schema instance to allow method chaining
     */
    protected BaseSchema<T> required() {
        addValidation("required", Objects::nonNull);
        return this;
    }
}
