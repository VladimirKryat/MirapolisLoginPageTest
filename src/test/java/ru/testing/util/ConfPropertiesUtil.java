package ru.testing.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/*
* ConfProperties позволяет загрузить параметры из файла conf.properties
* getProperty возвращает параметр по ключу(имени), в случае если параметр не найден логируется предупреждение и возвращается null;
* */
public class ConfPropertiesUtil {
    private static Logger logger = LoggerFactory.getLogger(ConfPropertiesUtil.class);
    private static Properties PROPERTIES;
    static{
        try(FileInputStream inputStream = new FileInputStream("src/test/resources/conf.properties")){
            PROPERTIES = new Properties();
            PROPERTIES.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getProperty(String key){
        String property = PROPERTIES.getProperty(key);
        if (Objects.isNull(property)) logger.warn("Not found property by key {}",key);
        return property;
    }
}
