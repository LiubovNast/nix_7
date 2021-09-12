package util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JsonReader {

    public static <T> List<T> read(String fileName, Object object) throws IOException {
        if (Files.notExists(Path.of("files_Json"))) {
            Files.createDirectories(Path.of("files_Json"));
        }
        String path = "files_Json\\" + fileName;
        if (Files.notExists(Path.of(path))) {
            Files.createFile(Path.of(path));
        }
        String json = Files.readString(Paths.get(path)).replaceAll("\n", " ").trim();
        if (json.isBlank()) return null;
        List<T> result = new ArrayList<>();
        try {
            result = getObjectFromString(json, object);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private static <T> List<T> getObjectFromString(String json, Object object) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        int indexSeparator = json.indexOf(":");
        String nameObject;
        String value;
        if (isObject(json)) {
            nameObject = json.substring(1, indexSeparator).replaceAll("\"", "").trim();
            value = json.substring(indexSeparator + 1, json.length() - 1).trim();
            if (nameObject.endsWith("[]")) {
                List<T> arrayList = new ArrayList<>();
                String[] stringsObject = getArrayFromString(value, '[', ']');
                for (int i = 0; i < stringsObject.length; i++) {
                    if (stringsObject[i] != null)
                        arrayList.addAll(getObjectFromString(stringsObject[i], object));
                }
                return arrayList;
            } else {
                List<T> objectList = new ArrayList<>();
                Class classObject = Class.forName(object.getClass().getPackageName() + "." + nameObject);
                T result = (T) classObject.getConstructor().newInstance();
                String[] valueArray = value.split(nameObject);
                Map<String, String> mapKeyValue;
                for (int i = 0; i < valueArray.length; i++) {
                    String[] jsonObjects = getArrayFromString(valueArray[i], '{', '}');
                    for (int j = 0; j < jsonObjects.length; j++) {
                        if (jsonObjects[j] != null) {
                            mapKeyValue = getMap(jsonObjects[j]);
                            result = setObjectFromMap(mapKeyValue, result);
                            objectList.add(result);
                        }
                    }
                }
                return objectList;
            }
        }
        return null;
    }

    private static <T> T setObjectFromMap(Map<String, String> mapKeyValue, T result) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Field[] fields = result.getClass().getDeclaredFields();
        for (Map.Entry<String, String> element : mapKeyValue.entrySet()) {
            String key = element.getKey().replaceAll("\"", "");
            String stringValue = element.getValue().replaceAll("\"", "");
            for (int i = 0; i < fields.length; i++) {
                String fieldName = getStringWithFirstLatterToUpperCase(fields[i].getName());
                if (fields[i].getName().equals(key)) {
                    Method method = result.getClass().getMethod("set" + fieldName, fields[i].getType());
                    if (fields[i].getType().getSimpleName().equals("String")) {
                        method.invoke(result, stringValue);
                    } else if (fields[i].getType().getSimpleName().equals("boolean")) {
                        method.invoke(result, Boolean.parseBoolean(stringValue));
                    } else if (fields[i].getType().getSimpleName().endsWith("[]")) {
                        String array = stringValue.substring(1, element.getValue().length() - 1);
                        String[] temp = array.split(", ");
                        int[] indexArray = new int[temp.length];
                        for (int a = 0; a < temp.length; a++) {
                            indexArray[a] = Integer.parseInt(temp[a]);
                        }
                        method.invoke(result, indexArray);
                    } else {
                        method.invoke(result, Integer.parseInt(stringValue));
                    }
                }
            }
        }
        return result;
    }

    private static Map<String, String> getMap(String value) {
        Map<String, String> map = new HashMap<>();
        String[] keyValue = value.split(", ");
        for (int i = 0; i < keyValue.length; i++) {
            int indexSeparator = keyValue[i].indexOf(":");
            String key = keyValue[i].substring(0, indexSeparator);
            String mapValue = keyValue[i].substring(indexSeparator + 1);
            map.put(key, mapValue);
        }
        return map;
    }

    private static boolean isObject(String json) {
        return json.startsWith("{");
    }

    private static boolean isArray(String json) {
        return json.startsWith("[");
    }

    private static String[] getArrayFromString(String string, char open, char close) {
        String[] objects = new String[countBrackets(string, open, close)];
        for (int i = 0; i < objects.length; i++) {
            String first = getStringWithOneTypeOfBrackets(string, open, close);
            int index;
            if (first != null) {
                index = first.length();
                objects[i] = first.trim();
                String second = string.substring(index).trim();
                string = second;
            } else break;
        }
        return objects;
    }

    private static String getStringWithOneTypeOfBrackets(String str, char openChar, char closeChar) {
        char[] arrayChar = str.toCharArray();
        for (int i = 0; i < arrayChar.length; i++) {
            if (arrayChar[i] == openChar) {
                for (int j = i; j < arrayChar.length; j++) {
                    if (arrayChar[j] == closeChar) {
                        arrayChar[i] = ' ';
                        arrayChar[j] = ' ';
                        String subStr = "";
                        for (int k = i; k < j; k++) {
                            subStr += arrayChar[k];
                        }
                        if (findAllBrackets(subStr))
                            return subStr;
                        else {
                            arrayChar[i] = openChar;
                            arrayChar[j] = closeChar;
                        }
                    }
                }
                return null;
            }
        }
        return null;
    }

    private static String getStringWithFirstLatterToUpperCase(String name) {
        String firstLatter = name.substring(0, 1).toUpperCase();
        String endWord = name.substring(1);
        return firstLatter + endWord;
    }

    private static boolean findAllBrackets(String str) {
        return findOneTypeOfBrackets(str, '{', '}')
                && findOneTypeOfBrackets(str, '[', ']');
    }

    private static int countBrackets(String str, char openChar, char closeChar) {
        char[] arrayChar = str.toCharArray();
        int open = 0, close = 0;
        for (char c : arrayChar) {
            if (c == openChar) open++;
            if (c == closeChar) close++;
        }
        if (open != close) return 0;
        else return open;
    }

    private static boolean findOneTypeOfBrackets(String str, char openChar, char closeChar) {
        char[] arrayChar = str.toCharArray();
        int open = 0, close = 0;
        for (char c : arrayChar) {
            if (c == openChar) open++;
            if (c == closeChar) close++;
        }
        if (open != close) return false;
        else {
            for (int i = 0; i < arrayChar.length; i++) {
                if (arrayChar[i] == openChar) {
                    break;
                }
                if (arrayChar[i] == closeChar) {
                    return false;
                }
            }
            for (int i = 0; i < arrayChar.length; i++) {
                if (arrayChar[i] == openChar) {
                    for (int j = i; j < arrayChar.length; j++) {
                        if (arrayChar[j] == closeChar) {
                            arrayChar[i] = 0;
                            arrayChar[j] = 0;
                            String subStr = "";
                            for (int k = i; k < j; k++) {
                                subStr += arrayChar[k];
                            }
                            if (!findAllBrackets(subStr)) {
                                arrayChar[i] = openChar;
                                arrayChar[j] = closeChar;
                            } else return true;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
