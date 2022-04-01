package ru.testing.util;

public final class ChangeParamsUtil {
    public final static String KEY_EMPTY = "empty";

    //CsvSource настроен на удаление всех пробелов ( потому, что они служат только для организации приличного формата файла с ресурсами)
    // поэтому для передачи пустого параметра введём ключивое слово empty
    public static String changeParams(String param) {
        if (param.equals(KEY_EMPTY)) return new String("");
        return param;
    }
}
