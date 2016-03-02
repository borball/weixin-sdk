package com.riversoft.weixin.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by exizhai on 4/14/2015.
 */
public class JsonMapper {

    private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);

    private static JsonMapper defaultJsonMapper = null;
    private static JsonMapper nonEmptyJsonMapper = null;
    private static JsonMapper nonDefaultJsonMapper = null;
    private static JsonMapper defaultUnwrapRootJsonMapper = null;

    private ObjectMapper mapper;

    public JsonMapper() {
        this(null, false);
    }

    public JsonMapper(boolean unwrapRoot) {
        this(null, unwrapRoot);
    }

    public JsonMapper(JsonInclude.Include include, boolean unwrapRoot) {
        mapper = new ObjectMapper();
        // 设置输出时包含属性的风格
        if (include != null) {
            mapper.setSerializationInclusion(include);
        }
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        if (unwrapRoot) {
            mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        }
    }

    /**
     * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper,建议在外部接口中使用.
     */
    public synchronized static JsonMapper nonEmptyMapper() {
        if (nonEmptyJsonMapper == null) {
            nonEmptyJsonMapper = new JsonMapper(JsonInclude.Include.NON_EMPTY, false);
        }
        return nonEmptyJsonMapper;
    }

    /**
     * 创建只输出初始值被改变的属性到Json字符串的Mapper, 最节约的存储方式，建议在内部接口中使用。
     */
    public synchronized static JsonMapper nonDefaultMapper() {
        if (nonDefaultJsonMapper == null) {
            nonDefaultJsonMapper = new JsonMapper(JsonInclude.Include.NON_DEFAULT, false);
        }
        return nonDefaultJsonMapper;
    }

    public synchronized static JsonMapper defaultUnwrapRootMapper() {
        if (defaultUnwrapRootJsonMapper == null) {
            defaultUnwrapRootJsonMapper = new JsonMapper(true);
        }
        return defaultUnwrapRootJsonMapper;
    }

    /**
     * 创建默认Mapper
     */
    public synchronized static JsonMapper defaultMapper() {
        if (defaultJsonMapper == null) {
            defaultJsonMapper = new JsonMapper();
        }
        return defaultJsonMapper;
    }

    /**
     * 对象转换成JSON字符串
     *
     * @param object
     * @return
     */
    public String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            logger.warn("toJson出错:" + object, e);
            return null;
        }
    }

    /**
     * JSON转换成Java对象
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T fromJson(String json, Class<T> clazz) {
        if (json == null || json.trim().length() == 0) {
            return null;
        }

        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.warn("fromJson出错:" + json, e);
            return null;
        }
    }

    /**
     * JSON转换成Java对象
     *
     * @param json
     * @return
     */
    public HashMap<String, Object> json2Map(String json) {
        return fromJson(json, HashMap.class);
    }

    /**
     * 把object转出clazz对象， 比如POJO和Map互换，字符串转换成Date
     *
     * @param object 原对象
     * @param clazz  目标类型
     * @param <T>
     * @return
     */
    public <T> T convert(Object object, Class<T> clazz) {
        if (object == null) {
            return null;
        }

        return mapper.convertValue(object, clazz);
    }

    /**
     * 如果jsons 是数组格式，则挨个转换成clazz对象返回list，否则直接尝试转换成clazz对象返回list
     *
     * @param jsons
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> List<T> fromJsons(String jsons, Class<T> clazz) throws IOException {
        if (jsons == null || jsons.trim().length() == 0) {
            return Collections.EMPTY_LIST;
        }

        List<T> list = new ArrayList<>();

        JsonNode jsonNode = mapper.readTree(jsons);
        if (jsonNode.isArray()) {//是数组
            for (JsonNode child : jsonNode) {
                list.add(mapper.treeToValue(child, clazz));
            }
        } else {//不是数组
            list.add(fromJson(jsons, clazz));
        }

        return list;

    }

    public ObjectMapper getMapper() {
        return mapper;
    }

}
