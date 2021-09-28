package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import entity.Table;
import exception.IllegalDateException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class CSVMapper {

    private static final Logger log = LoggerFactory.getLogger(CSVMapper.class);
    private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public <T> List<T> getObjects(Table table, Class<T> objectClass) {
        List<String> header = table.getHeader();
        List<String> rows = table.getRows();
        List<T> result = new ArrayList<>();
        Map<String, Integer> fieldsName = getMapFieldsName(header);

        for (String row : rows) {
            try {
                result.add(getObjectFromString(fieldsName, row, objectClass));
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | IllegalDateException e) {
                log.error("Exception ", e);
            }
        }
        return result;
    }

    private static Map<String, Integer> getMapFieldsName(List<String> fieldsName) {
        Map<String, Integer> mapNames = new HashMap<>();
        for (int i = 0; i < fieldsName.size(); i++) {
            mapNames.put(fieldsName.get(i), i);
        }
        return mapNames;
    }

    private static <T> T getObjectFromString(Map<String, Integer> fieldsName, String csvString, Class<T> objectClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IllegalDateException {
        Field[] fields = objectClass.getDeclaredFields();
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
                    log.error("Wrong date format");
                    throw new IllegalDateException(e);
                }
            }
        }
        return result;
    }
}
