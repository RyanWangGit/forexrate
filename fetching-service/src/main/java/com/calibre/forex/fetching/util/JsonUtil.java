package com.calibre.forex.fetching.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility for Json and Object conversion
 */
public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static ObjectMapper INSTANCE = new ObjectMapper();
    private JsonUtil() {}

    /**
     * Oject to Json
     * @param obj
     * @return
     */
    public static String toJsonStr(Object obj) {
        String result = null;
        try {
            result =  INSTANCE.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("Exception from JsonUtil",e);
        }
        return result;
    }

    /**
     * Json to Object
     * @param json
     * @param type type of object
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T parseJson(String json, String type) {
        T result = null;
        try {
            result = (T) parseJson(json, Class.forName(type));
        } catch (ClassNotFoundException e) {
            logger.error("Exception from JsonUtil",e);
    }
        return result;
    }


    /**
     * Json to Object
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseJson(String json, Class<T> clazz) {
        T result = null;
        try {
            result = (T) INSTANCE.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            logger.error("Exception from JsonUtil",e);
        }
        return result;
    }

    /**
     * Json List to object List
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> parseJsonList(String json, Class<T> clazz) {
        List<T> results = null;
        try {
            JavaType javaType = getCollectionType(ArrayList.class, clazz);
            results = (List<T>) INSTANCE.readValue(json, javaType);
        } catch (Exception e) {
            logger.error("Exception from JsonUtil",e);
        }
        return results;
    }

    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return INSTANCE.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

}
