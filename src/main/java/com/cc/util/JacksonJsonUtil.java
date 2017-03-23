package com.cc.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.security.acl.Group;


public class JacksonJsonUtil {
	private static final Log log = LogFactory.getLog(JacksonJsonUtil.class);


	private static ObjectMapper mapper = new ObjectMapper();
	static{
		mapper.setSerializationInclusion(Include.NON_NULL);//设置序列化配置，为null的属性不加入到json中
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);//兼容单引号 但单引号不属于json标准 不建议使用
	}
	/**
	 * 将对象转换成json字符串,如果转换失败则返回null
	 * @author zhaoyunxiao
	 * @param o 需要转换为json的对象
	 * @return String 转换后的json字符串
	 *
	 *
	 * */
	public static String object2Json(Object o){
		String jsonStr = "";
		try {
			jsonStr = mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			log.error("write2JsonStr() exception: " + e.getMessage());
		}
		return jsonStr;
	}

	/**
	 * 将json转换为对象 如果对象模版为内部类会出现问题，所以不要使用内部类
	 * @author zhaoyunxiao
	 * @param json 要转换的json
	//     * @param 要映射的类型
	 * @return 转换成的目标对象，如果转换失败返回null
	 * */
	public static Object json2Object(String json,Class<?> clazz){
		try {
			return mapper.readValue(json,clazz);
		} catch (JsonParseException e) {
			log.error("json2Object() parseException: " + e.getMessage());
		} catch (JsonMappingException e) {
			log.error("json2Object() mappingException: " + e.getMessage());
		} catch (IOException e) {
			log.error("json2Object() IOException: " + e.getMessage());
		}
		return null;
	}

	/**
	 * for example：
	 * TypeReference type = new TypeReference<Map<String, Integer>>() {}
	 *
	 * @see com.fasterxml.jackson.core.type.TypeReference
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String jsonString, TypeReference<T> typeReference) {
		if (org.apache.commons.lang3.StringUtils.isBlank(jsonString)) {
			return null;
		}
		try {
			return (T) mapper.readValue(jsonString, typeReference);
		} catch (IOException e) {
			log.error("parse json string error:" + jsonString, e);
		}
		return null;
	}



}
