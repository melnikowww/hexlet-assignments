package exercise;

import javax.lang.model.element.NestingKind;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        List<String> result = new ArrayList<>();
        try {
            Field[] fields = address.getClass().getDeclaredFields();
            for (Field field: fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(NotNull.class) && field.get(address) == null) {
                    result.add(field.getName());
                }
            }
        } catch (Exception  exception) {
            exception.printStackTrace();
        }

        return result;
    }
    public static Map<String, List<String>> advancedValidate(Address address) {
        final String LENGTH_ERROR = "length less than ";
        final String NULL_ERROR = "can not be null";
        Map<String, List<String>> result = new HashMap<>();
        try {
            Field[] fields = address.getClass().getDeclaredFields();
            for (Field field: fields) {
                field.setAccessible(true);
                List<String> fieldErrorList = new ArrayList<>();
                if (field.isAnnotationPresent(NotNull.class) && field.get(address) == null) {
                    fieldErrorList.add(NULL_ERROR);
                    result.put(field.getName(), fieldErrorList);
                }
                if (field.isAnnotationPresent(MinLength.class)
                    && field.get(address).toString().length() < field.getAnnotation(MinLength.class).minLength()) {
                    int minLength = field.getAnnotation(MinLength.class).minLength();
                    fieldErrorList.add(LENGTH_ERROR + minLength);
                    result.put(field.getName(), fieldErrorList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
// END
