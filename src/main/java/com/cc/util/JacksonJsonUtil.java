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
		mapper.setSerializationInclusion(Include.NON_NULL);//�������л����ã�Ϊnull�����Բ����뵽json��
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);//���ݵ����� �������Ų�����json��׼ ������ʹ��
	}
	/**
	 * ������ת����json�ַ���,���ת��ʧ���򷵻�null
	 * @author zhaoyunxiao
	 * @param o ��Ҫת��Ϊjson�Ķ���
	 * @return String ת�����json�ַ���
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
     * ��jsonת��Ϊ���� �������ģ��Ϊ�ڲ����������⣬���Բ�Ҫʹ���ڲ���
     * @author zhaoyunxiao
     * @param json Ҫת����json
//     * @param Ҫӳ�������
     * @return ת���ɵ�Ŀ��������ת��ʧ�ܷ���null
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
     * for example��
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
