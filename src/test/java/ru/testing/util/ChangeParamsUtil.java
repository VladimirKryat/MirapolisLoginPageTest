package ru.testing.util;

public final class ChangeParamsUtil {
    //CsvSource настроен на удаление всех пробелов ( потому, что они служат только для организации приличного формата файла с ресурсами)
    // поэтому для передачи пустого параметра введём ключивое слово empty
    public static String changeParams(String param){
        if (param.equals("empty")) return new String("");
        return param;
    }
}
