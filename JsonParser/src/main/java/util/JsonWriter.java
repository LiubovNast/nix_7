package util;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class JsonWriter {

    public static <T> void write(List<T> list, String fileName) {
        String string = "{\n";
        for (T object : list) {
            if (list.indexOf(object) != 0) {
                string += ",\n";
            }

            try {
                string += getStringForJson(object);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                System.out.println(e.getMessage());
            }
        }
        string += "}";
        createAndWriteInFile(fileName, string);
    }

    public static <T> void write(T[] array, String fileName) {
        String string = "{\n";
        for (T object : array) {
            if (array[0] != object) {
                string += ",\n";
            }
            try {
                string += getStringForJson(object);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                System.out.println(e.getMessage());
            }
        }
        string += "\n}";
        createAndWriteInFile(fileName, string);
    }

    public static <T> void write(T object, String fileName) {
        String string = "";
        try {
            string += getStringForJson(object);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            System.out.println(e.getMessage());
        }
        createAndWriteInFile(fileName, string);
    }

    private static void createAndWriteInFile(String fileName, String string) {
        try {
            if (Files.notExists(Path.of("files_Json"))) {
                Files.createDirectories(Path.of("files_Json"));
            }
            String path = "files_Json\\" + fileName;
            if (Files.notExists(Path.of(path))) {
                Files.createFile(Path.of(path));
            }
            Files.writeString(Paths.get(path), string);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getStringForJson(Object o) throws IllegalAccessException, NoSuchFieldException {
        String string = "";
        if (o.getClass().isPrimitive()) {
            string += getStringFromPrimitive(o);
        } else if (o.getClass().isArray()) {
            string += getStringFromArray(o);
        } else {
            string += "\"" + o.getClass().getSimpleName() + "\":";
            string += "{\n";
            Field[] field = o.getClass().getDeclaredFields();
            for (int i = 0; i < field.length; i++) {
                if (i != 0) string += ",\n";
                string += "\"" + field[i].getName() + "\":";
                String fieldName = field[i].getName();
                if (!field[i].isAccessible()) {
                    field[i].setAccessible(true);
                }
                string += getValue(field[i], o);
            }
            string += "}\n";
        }
        return string;
    }

    private static String getValue(Field field, Object o) throws IllegalAccessException, NoSuchFieldException {
        String value;
        Class fieldType = field.getType();

        if (fieldType.getSimpleName().equals("boolean")) {
            if (field.getBoolean(o)) {
                value = "true";
            } else value = "false";
        } else if (fieldType.getSimpleName().equals("String")) {
            value = "\"" + field.get(o) + "\"";
        } else if (fieldType.isArray()) {
            value = Arrays.deepToString(new Object[]{field.get(o)})
                    .replaceAll("\\[{2}", "[").replaceAll("]{2}", "]");
        } else {
            long aLong = field.getLong(o);
            value = String.valueOf(aLong);
        }
        return value;
    }

    private static String getStringFromPrimitive(Object o) throws NoSuchFieldException, IllegalAccessException {
        Field field = o.getClass().getField(o.getClass().getSimpleName());
        String fieldName = field.getName();
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        String str = "\"" + o.getClass().getSimpleName() + "\":\"" + getValue(field, o) + "\"";
        return str;
    }

    private static String getStringFromArray(Object object) throws NoSuchFieldException, IllegalAccessException {
        String string = "\"" + object.getClass().getSimpleName() + "\":";
        string += "[\n";
        int length = Array.getLength(object);
        for (int i = 0; i < length; i++) {
            Object objectFromArray = Array.get(object, i);
            if (objectFromArray != null) {
                if (i != 0) string += ",\n";
                string += getStringForJson(objectFromArray);
            }
        }
        string += "]\n";
        return string;
    }
}
