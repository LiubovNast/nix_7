package util;

import annotations.PropertyKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class InitClass {

    private static final Logger LOG = LoggerFactory.getLogger(InitClass.class);
    private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public <T> T initialisation(Class<T> objectClass, Properties properties) {
        T result = null;
        try {
            result = getObject(objectClass, properties);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOG.error(e.getMessage());
        }
        return result;
    }

    private static <T> T getObject(Class<T> objectClass, Properties properties) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Field[] fields = objectClass.getDeclaredFields();
        T result = objectClass.getConstructor().newInstance();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldValue;
            if (field.isAnnotationPresent(PropertyKey.class)) {
                String key = field.getAnnotation(PropertyKey.class).value();
                fieldValue = properties.getProperty(key);
            } else continue;

            if (field.getType() == int.class && fieldValue != null) {
                field.setInt(result, Integer.parseInt(fieldValue));
            } else if (field.getType() == double.class && fieldValue != null) {
                field.setDouble(result, Double.parseDouble(fieldValue));
            } else if (field.getType() == long.class && fieldValue != null) {
                field.setLong(result, Long.parseLong(fieldValue));
            } else if (field.getType() == boolean.class && fieldValue != null) {
                field.setBoolean(result, Boolean.parseBoolean(fieldValue));
            } else if (field.getType() == byte.class && fieldValue != null) {
                field.setByte(result, Byte.parseByte(fieldValue));
            } else if (field.getType() == String.class) {
                field.set(result, fieldValue);
            } else if (field.getType() == Date.class) {
                try {
                    field.set(result, format.parse(fieldValue));
                } catch (ParseException e) {
                    LOG.error("Wrong date format");
                }
            } else if (field.getType().isEnum()) {
                int index = Integer.parseInt(fieldValue);
                field.set(result, field.getType().getEnumConstants()[index]);
            }
        }
        return result;
    }
}
