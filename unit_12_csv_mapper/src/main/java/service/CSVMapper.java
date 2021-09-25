package service;

import exception.IllegalDateException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CSVMapper {

    public static <T> List<T> getObjects(String[] csvStrings, Class<T> objectClass) {
        String[] firstString = csvStrings[0].replace("\r", "").split(", ");
        List<T> result = new ArrayList<>();
        Map<String, Integer> fieldsName = getMapFieldsName(firstString);

        for (int i = 1; i < csvStrings.length; i++) {
            try {
                result.add(getObjectFromString(fieldsName, csvStrings[i], objectClass));
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | IllegalDateException e) {
                throw new RuntimeException();
            }
        }
        return result;
    }

    private static Map<String, Integer> getMapFieldsName(String[] fieldsName) {
        Map<String, Integer> mapNames = new HashMap<>();
        for (int i = 0; i < fieldsName.length; i++) {
            mapNames.put(fieldsName[i], i);
        }
        return mapNames;
    }

    private static <T> T getObjectFromString(Map<String, Integer> fieldsName, String csvString, Class<T> objectClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IllegalDateException {
        Field[] fields = objectClass.getDeclaredFields();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        T result = objectClass.getConstructor().newInstance();
        String[] values = csvString.split(", ");
        for (Field field : fields) {
            field.setAccessible(true);
            int index = fieldsName.get(field.getName());
            String fieldValue = values[index].trim();
            if (field.getType() == int.class) {
                field.setInt(result, Integer.parseInt(fieldValue));
            } else if (field.getType() == double.class) {
                field.setDouble(result, Double.parseDouble(fieldValue));
            } else if (field.getType() == long.class) {
                field.setLong(result, Long.parseLong(fieldValue));
            } else if (field.getType() == boolean.class) {
                field.setBoolean(result, Boolean.parseBoolean(fieldValue));
            } else if (field.getType() == byte.class) {
                field.setByte(result, Byte.parseByte(fieldValue));
            } else if (field.getType() == String.class) {
                field.set(result, fieldValue);
            } else if (field.getType() == Date.class) {
                try {
                    field.set(result, format.parse(fieldValue));
                } catch (ParseException e) {
                    throw new IllegalDateException("Wrong date format");
                }
            }
        }
        return result;
    }
}
