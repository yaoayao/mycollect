package com.cc.util;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * ����http����Ĺ�����
 * User: gaotianlin
 * Date: 2010-12-29
 * Time: 11:42:27
 */
public class HttpClientUtil {
    private final static Log log = LogFactory.getLog(HttpClientUtil.class);
    private static String key;  //����key
    private static String timeout;

    public static final int MIN_ERROR_REQUEST_SPEND_TIME = 200;

    public static String sendHttpRequest(String url) {
        return sendHttpRequest(url, null);
    }

    /**
     * ���Ͷ������post����
     *
     * @param url
     * @param data
     * @return
     */
    public static String sendHttpRequestByParams(String url, NameValuePair[] data) {
        return sendHttpRequestByParams(url, data, 30000);
    }

    /**
     * ���Ͷ������post����
     *
     * @param url
     * @param data
     * @return
     */
    public static String sendHttpRequestByParams(String url, NameValuePair[] data, int timeoutTime) {
        //����HttpClient��ʵ��
        HttpClient httpClient = new HttpClient();
        log.info(url);
        PostMethod getMethod = new PostMethod(url);
        getMethod.setRequestHeader("Connection", "close");
        getMethod.addParameters(data);
        //ʹ��ϵͳ�ṩ��Ĭ�ϵĻָ�����
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        //���������ӵĳ�ʱ
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeoutTime);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeoutTime);
        // getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        //����һ��������
        InputStream ins = null;
        //�����ļ���
        BufferedReader br = null;
        try {

            //ִ��getMethod
            long timeBeforeExecute = System.currentTimeMillis();
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                log.info("method failure: " + getMethod.getStatusLine()+",url:-----------"+url+",params:"+toJson(data));
                log.error("method failure: " + getMethod.getStatusLine()+"-----------"+url+"params:"+toJson(data));
            }
            long timeAfterExecute = System.currentTimeMillis();
            long executeSpendTime = timeAfterExecute - timeBeforeExecute;
            if (executeSpendTime > MIN_ERROR_REQUEST_SPEND_TIME) {
               // log.error("����url��" + url+",params:"+toJson(data) + " ������̫��ʱ�䣺" + executeSpendTime + "ms!");
            }
            //ʹ��getResponseBodyAsStream��ȡҳ�����ݣ������������Ŀ���ַ���д���������Ҫ��������ѵġ�
            //   Thread.sleep(2000l);//������Ҫ���߳���ͣ10s
            ins = getMethod.getResponseBodyAsStream();
            String charset = getMethod.getResponseCharSet();
            if (charset.toUpperCase().equals("ISO-8859-1")) {
                charset = "utf-8";
            }
            //�������������ַ��������ļ����������CHARSETҪ����ʵ���������
            br = new BufferedReader(new InputStreamReader(ins, getMethod.getResponseCharSet()));
            StringBuffer sbf = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sbf.append(line);
            }
            String result = new String(sbf.toString().getBytes(getMethod.getResponseCharSet()), charset);
            return result;
        } catch (HttpException e) {
            //�����������쳣��������Э�鲻�Ի��߷��ص�����������  \
             log.error("ulr------"+url+toJson(data));
            log.error("please check your http url address��", e);
        } catch (IOException e) {
             log.error("ulr------"+url+toJson(data));
            log.error("network exception is happening", e);
        } catch (Exception e) {
             log.error("ulr------"+url+toJson(data));
            log.error("Exception  is happening", e);
        } finally {
            //�ر������ͷ�����
            try {
                if (br != null) {
                    br.close();
                }
                if (ins != null) {
                    ins.close();
                }
                if (getMethod != null) {
                    try {
                        getMethod.releaseConnection();
                        httpClient.getHttpConnectionManager().closeIdleConnections(0);
                    } catch (Exception e) {
                         log.error("ulr------"+url+toJson(data));
                        log.error("close http connetion failure", e);
                    }
                }
            } catch (IOException e) {
                 log.error("ulr------"+url+toJson(data));
                log.error("stream connection close failure", e);
            } catch (Exception e) {
                 log.error("ulr------"+url+toJson(data));
                log.error("Exception", e);
            }
        }
        return null;
    }

    /**
     * ����ͳһ���󷽷�
     * �𶯣���������
     */
    public static String sendHttpRequest(String url, String param) {
        return sendHttpRequest(url, param, Integer.parseInt(timeout));
    }


    public static String sendHttpRequest(String url, String param, int timeoutTime) {
        //����HttpClient��ʵ��
        HttpClient httpClient = new HttpClient();
        log.info(url);
        PostMethod getMethod = new PostMethod(url);
        getMethod.setRequestHeader("Connection", "close");
        if (StringUtils.isNotEmpty(param)) {
            getMethod.addParameter("param", param);
        }

        if (StringUtils.isNotEmpty(param)) {
            String keyValue = "";
            if (StringUtils.isNotEmpty(key)) {
                String[] keys = key.split(";");
                String content = keys[1];
                String seed = keys[0];
                byte[] bb = MD5Util.MD5(content + "-" + seed).getBytes();
                keyValue = new String(Base64.encodeBase64(bb));
            } else {
                keyValue = "";
            }
            getMethod.addParameter("key", keyValue);
        }
        //ʹ��ϵͳ�ṩ��Ĭ�ϵĻָ�����
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        //���������ӵĳ�ʱ
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeoutTime*5);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeoutTime*5);
        // getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        //����һ��������
        InputStream ins = null;
        //�����ļ���
        BufferedReader br = null;
        try {

            long timeBeforeExecute = System.currentTimeMillis();
            //ִ��getMethod
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                log.error("method failure: " + getMethod.getStatusLine()+",url:-----------"+url+",params:"+param);
                log.error("method failure: " + getMethod.getStatusLine()+"-----------"+url+",params:"+param);
            }
            long timeAfterExecute = System.currentTimeMillis();
            long executeSpendTime = timeAfterExecute - timeBeforeExecute;
            if (executeSpendTime > MIN_ERROR_REQUEST_SPEND_TIME) {
               // log.error("����url�� " + url+",params:" + param + " ������̫��ʱ�䣺" + executeSpendTime + "ms");
            }
            //ʹ��getResponseBodyAsStream��ȡҳ�����ݣ������������Ŀ���ַ���д���������Ҫ��������ѵġ�
            //   Thread.sleep(2000l);//������Ҫ���߳���ͣ10s
            ins = getMethod.getResponseBodyAsStream();
            String charset = getMethod.getResponseCharSet();
            if (charset.toUpperCase().equals("ISO-8859-1")) {
                charset = "utf-8";
            }
            //�������������ַ��������ļ����������CHARSETҪ����ʵ���������
            br = new BufferedReader(new InputStreamReader(ins, getMethod.getResponseCharSet()));
            StringBuffer sbf = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sbf.append(line);
            }
            String result = new String(sbf.toString().getBytes(getMethod.getResponseCharSet()), charset);
            return result;
        } catch (HttpException e) {
            //�����������쳣��������Э�鲻�Ի��߷��ص�����������  \
            log.error("ulr------"+url+param);
            log.error("please check your http url address��", e);
        } catch (IOException e) {
             log.error("ulr------"+url+param);
            log.error("network exception is happening", e);
        } catch (Exception e) {
             log.error("ulr------"+url+param);
            log.error("Exception  is happening", e);
        } finally {
            //�ر������ͷ�����
            try {
                if (br != null) {
                    br.close();
                }
                if (ins != null) {
                    ins.close();
                }
                if (getMethod != null) {
                    try {
                        getMethod.releaseConnection();
                        httpClient.getHttpConnectionManager().closeIdleConnections(0);
                    } catch (Exception e) {
                        log.error("ulr------"+url+param);
                        log.error("close http connetion failure", e);
                    }
                }
            } catch (IOException e) {
                log.error("ulr------"+url+param);
                log.error("stream connection close failure", e);
            } catch (Exception e) {
                log.error("ulr------"+url+param);
                log.error("Exception", e);
            }
        }
        return null;
    }

     /**
     * ��NameValuePair����ת��Ϊjson��ʽ
     *
     * @param data NameValuePair ����
     * @return
     */
    private static String toJson(NameValuePair[] data) {

        StringBuffer buffer = new StringBuffer("{");
        for (NameValuePair nvp : data) {
            buffer.append(nvp.getName());
            buffer.append(":");
            buffer.append(nvp.getValue());
            buffer.append(",");
        }
        buffer.append("}");

        return buffer.toString();
    }

    public void setKey(String key) {
        this.key = key;
    }

    public  void setTimeout(String timeout) {
        HttpClientUtil.timeout = timeout;
    }
    /*************************************   ���֧��  ************************************************//*
    *//**
     * ��copy��m-common 2012-05-21 ������
     * ����ͳһ���󷽷�
     * �𶯣���������
     *//*
    public static String sendHttpRequest(HttpEntity entity) {
    	String url = entity.getUrl();
        //����HttpClient��ʵ��
        HttpClient httpClient = new HttpClient();
        log.info(url);
        PostMethod getMethod = new PostMethod(entity.getUrl());

        if(entity.getPost()!=null && entity.getPost().length!=0){
            getMethod.addParameters(entity.getPost());
        }

        //ʹ��ϵͳ�ṩ��Ĭ�ϵĻָ�����
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        if(entity.getCookies()!=null){
        	getMethod.addRequestHeader("Cookie", entity.getCookies());
        }

        getMethod.setRequestHeader("Connection", "close");

        if(StringUtils.isBlank(entity.getEncoding())) {
        	entity.setEncoding("UTF-8");
        }
        getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, entity.getEncoding());
        //���������ӵĳ�ʱ
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(1000*30);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(1000*30);
        //����һ��������
        InputStream ins = null;
        //�����ļ���
        BufferedReader br = null;
        try {

            //ִ��getMethod
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                log.error("method failure: " + getMethod.getStatusLine());
            }
            String result =getMethod.getResponseBodyAsString();

            //���÷���ֵ�Լ�cookie
            entity.setResponseText(result);
            String cookies = updateCookie(getMethod);
            entity.setCookies(cookies);
            return result;
        }
        catch (HttpException e) {
            //�����������쳣��������Э�鲻�Ի��߷��ص�����������  \
            log.error("please check your http url address��" , e);
        }
        catch (IOException e) {
            log.error("network exception is happening" , e);
        }
        catch (Exception e) {
            log.error("Exception  is happening" , e);
        }
        finally {
            //�ر������ͷ�����
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                log.error("br stream connection close failure", e);
            }

            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    log.error("ins stream connection close failure" , e);
                }
            }

            if(getMethod!=null){
                try{
                    getMethod.releaseConnection();
                    httpClient.getHttpConnectionManager().closeIdleConnections(0);
                } catch (Exception e) {
                    log.error("close http connetion failure" , e);
                }
            }

        }
        return null;
    }*/

	private static String updateCookie(PostMethod getMethod) {
		String cookies=null;
		NameValuePair cookieHeader = getMethod.getResponseHeader("Set-Cookie");
        if(cookieHeader!=null){
            cookies = cookieHeader.getValue();
            if(cookies!=null){
                cookies = cookies.substring(0, cookies.indexOf(";"));
            }
        }
		return cookies;
	}
}

