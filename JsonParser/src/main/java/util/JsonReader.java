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

    public static <T> List<T> read(String fileName, Object object) {
        List<T> result = new ArrayList<>();
        try {
            if (Files.notExists(Path.of("files_Json"))) {
                return null;
            }
            String path = "files_Json\\" + fileName;
            if (Files.notExists(Path.of(path))) {
                return null;
            }
            String json = Files.readString(Paths.get(path)).replaceAll("\n", " ").trim();
            result = getListObjectsFromString(json, object);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private static <T> List<T> getListObjectsFromString(String json, Object object) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
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
                        arrayList.addAll(getListObjectsFromString(stringsObject[i], object));
                }
                return arrayList;
            } else {
                List<T> objectList = new ArrayList<>();
                Class classObject = Class.forName(object.getClass().getPackageName() + "." + nameObject);
                String[] valueArray = value.split(nameObject);
                Map<String, String> mapKeyValue;
                String[] arrays;
                for (String valAr : valueArray) {
                    String[] jsonObjects = getArrayFromString(valAr, '{', '}');
                    for (String jsonOb : jsonObjects) {
                        if (jsonOb != null) {
                            T result = (T) classObject.getConstructor().newInstance();
                            arrays = getArrayFromString(jsonOb, '[', ']');
                            mapKeyValue = getMap(jsonOb);
                            result = setObjectFromMap(mapKeyValue, result, arrays);
                            objectList.add(result);
                        }
                    }
                }
                return objectList;
            }
        }
        return null;
    }

    private static <T> T setObjectFromMap(Map<String, String> mapKeyValue, T result, String[] arrays) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
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
                        if (stringValue.equals("null")) method.invoke(result, false);
                        else method.invoke(result, Boolean.parseBoolean(stringValue));
                    } else if (fields[i].getType().getSimpleName().endsWith("[]")) {
                        String array = arrays[0].trim();
                        String[] temp = array.split(", ");
                        int[] indexArray = new int[temp.length];
                        for (int a = 0; a < temp.length; a++) {
                            if (temp[a].equals("null")) indexArray[a] = 0;
                            else indexArray[a] = Integer.parseInt(temp[a]);
                        }
                        method.invoke(result, indexArray);
                    } else {
                        if (stringValue.equals("null")) method.invoke(result, 0);
                        else method.invoke(result, Integer.parseInt(stringValue));
                    }
                }
            }
        }
        return result;
    }

    private static Map<String, String> getMap(String value) {
        Map<String, String> map = new HashMap<>();
        String[] keyValue = value.split(", ");
        for (String keyVal : keyValue) {
            if (keyVal.contains(":")) {
                int indexSeparator = keyVal.indexOf(":");
                String key = keyVal.substring(0, indexSeparator);
                if (indexSeparator < keyVal.length()) {
                    String mapValue = keyVal.substring(indexSeparator + 1);
                    map.put(key, mapValue);
                } else map.put(key, "null");
            }
        }
        return map;
    }

    private static boolean isObject(String json) {
        return json.startsWith("{");
    }

    private static String[] getArrayFromString(String string, char open, char close) {
        String[] objects = new String[countBrackets(string, open, close)];
        for (int i = 0; i < objects.length; i++) {
            String first = getStringWithOneTypeOfBrackets(string, open, close);
            int index;
            if (first != null) {
                String second = string.replaceFirst(first, "");
                objects[i] = first.trim();
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
