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
 * 发送http请求的工具类
 * User: gaotianlin
 * Date: 2010-12-29
 * Time: 11:42:27
 */
public class HttpClientUtil {
    private final static Log log = LogFactory.getLog(HttpClientUtil.class);
    private static String key;  //订单key
    private static String timeout;

    public static final int MIN_ERROR_REQUEST_SPEND_TIME = 200;

    public static String sendHttpRequest(String url) {
        return sendHttpRequest(url, null);
    }

    /**
     * 发送多参数的post请求
     *
     * @param url
     * @param data
     * @return
     */
    public static String sendHttpRequestByParams(String url, NameValuePair[] data) {
        return sendHttpRequestByParams(url, data, 30000);
    }

    /**
     * 发送多参数的post请求
     *
     * @param url
     * @param data
     * @return
     */
    public static String sendHttpRequestByParams(String url, NameValuePair[] data, int timeoutTime) {
        //构造HttpClient的实例
        HttpClient httpClient = new HttpClient();
        log.info(url);
        PostMethod getMethod = new PostMethod(url);
        getMethod.setRequestHeader("Connection", "close");
        getMethod.addParameters(data);
        //使用系统提供的默认的恢复策略
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        //定义五秒钟的超时
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeoutTime);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeoutTime);
        // getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        //定义一个输入流
        InputStream ins = null;
        //定义文件流
        BufferedReader br = null;
        try {

            //执行getMethod
            long timeBeforeExecute = System.currentTimeMillis();
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                log.info("method failure: " + getMethod.getStatusLine()+",url:-----------"+url+",params:"+toJson(data));
                log.error("method failure: " + getMethod.getStatusLine()+"-----------"+url+"params:"+toJson(data));
            }
            long timeAfterExecute = System.currentTimeMillis();
            long executeSpendTime = timeAfterExecute - timeBeforeExecute;
            if (executeSpendTime > MIN_ERROR_REQUEST_SPEND_TIME) {
               // log.error("请求url：" + url+",params:"+toJson(data) + " 花费了太长时间：" + executeSpendTime + "ms!");
            }
            //使用getResponseBodyAsStream读取页面内容，这个方法对于目标地址中有大量数据需要传输是最佳的。
            //   Thread.sleep(2000l);//测试需要，线程暂停10s
            ins = getMethod.getResponseBodyAsStream();
            String charset = getMethod.getResponseCharSet();
            if (charset.toUpperCase().equals("ISO-8859-1")) {
                charset = "utf-8";
            }
            //按服务器编码字符集构建文件流，这里的CHARSET要根据实际情况设置
            br = new BufferedReader(new InputStreamReader(ins, getMethod.getResponseCharSet()));
            StringBuffer sbf = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sbf.append(line);
            }
            String result = new String(sbf.toString().getBytes(getMethod.getResponseCharSet()), charset);
            return result;
        } catch (HttpException e) {
            //发生致命的异常，可能是协议不对或者返回的内容有问题  \
             log.error("ulr------"+url+toJson(data));
            log.error("please check your http url address！", e);
        } catch (IOException e) {
             log.error("ulr------"+url+toJson(data));
            log.error("network exception is happening", e);
        } catch (Exception e) {
             log.error("ulr------"+url+toJson(data));
            log.error("Exception  is happening", e);
        } finally {
            //关闭流，释放连接
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
     * 订单统一请求方法
     * 勿动！！！！！
     */
    public static String sendHttpRequest(String url, String param) {
        return sendHttpRequest(url, param, Integer.parseInt(timeout));
    }


    public static String sendHttpRequest(String url, String param, int timeoutTime) {
        //构造HttpClient的实例
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
        //使用系统提供的默认的恢复策略
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        //定义五秒钟的超时
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeoutTime*5);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeoutTime*5);
        // getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        //定义一个输入流
        InputStream ins = null;
        //定义文件流
        BufferedReader br = null;
        try {

            long timeBeforeExecute = System.currentTimeMillis();
            //执行getMethod
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                log.error("method failure: " + getMethod.getStatusLine()+",url:-----------"+url+",params:"+param);
                log.error("method failure: " + getMethod.getStatusLine()+"-----------"+url+",params:"+param);
            }
            long timeAfterExecute = System.currentTimeMillis();
            long executeSpendTime = timeAfterExecute - timeBeforeExecute;
            if (executeSpendTime > MIN_ERROR_REQUEST_SPEND_TIME) {
               // log.error("请求url： " + url+",params:" + param + " 花费了太长时间：" + executeSpendTime + "ms");
            }
            //使用getResponseBodyAsStream读取页面内容，这个方法对于目标地址中有大量数据需要传输是最佳的。
            //   Thread.sleep(2000l);//测试需要，线程暂停10s
            ins = getMethod.getResponseBodyAsStream();
            String charset = getMethod.getResponseCharSet();
            if (charset.toUpperCase().equals("ISO-8859-1")) {
                charset = "utf-8";
            }
            //按服务器编码字符集构建文件流，这里的CHARSET要根据实际情况设置
            br = new BufferedReader(new InputStreamReader(ins, getMethod.getResponseCharSet()));
            StringBuffer sbf = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sbf.append(line);
            }
            String result = new String(sbf.toString().getBytes(getMethod.getResponseCharSet()), charset);
            return result;
        } catch (HttpException e) {
            //发生致命的异常，可能是协议不对或者返回的内容有问题  \
            log.error("ulr------"+url+param);
            log.error("please check your http url address！", e);
        } catch (IOException e) {
             log.error("ulr------"+url+param);
            log.error("network exception is happening", e);
        } catch (Exception e) {
             log.error("ulr------"+url+param);
            log.error("Exception  is happening", e);
        } finally {
            //关闭流，释放连接
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
     * 将NameValuePair数组转换为json格式
     *
     * @param data NameValuePair 数组
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
    /*************************************   快捷支付  ************************************************//*
    *//**
     * （copy自m-common 2012-05-21 郭鹏）
     * 订单统一请求方法
     * 勿动！！！！！
     *//*
    public static String sendHttpRequest(HttpEntity entity) {
    	String url = entity.getUrl();
        //构造HttpClient的实例
        HttpClient httpClient = new HttpClient();
        log.info(url);
        PostMethod getMethod = new PostMethod(entity.getUrl());

        if(entity.getPost()!=null && entity.getPost().length!=0){
            getMethod.addParameters(entity.getPost());
        }

        //使用系统提供的默认的恢复策略
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        if(entity.getCookies()!=null){
        	getMethod.addRequestHeader("Cookie", entity.getCookies());
        }

        getMethod.setRequestHeader("Connection", "close");

        if(StringUtils.isBlank(entity.getEncoding())) {
        	entity.setEncoding("UTF-8");
        }
        getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, entity.getEncoding());
        //定义五秒钟的超时
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(1000*30);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(1000*30);
        //定义一个输入流
        InputStream ins = null;
        //定义文件流
        BufferedReader br = null;
        try {

            //执行getMethod
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                log.error("method failure: " + getMethod.getStatusLine());
            }
            String result =getMethod.getResponseBodyAsString();

            //设置返回值以及cookie
            entity.setResponseText(result);
            String cookies = updateCookie(getMethod);
            entity.setCookies(cookies);
            return result;
        }
        catch (HttpException e) {
            //发生致命的异常，可能是协议不对或者返回的内容有问题  \
            log.error("please check your http url address！" , e);
        }
        catch (IOException e) {
            log.error("network exception is happening" , e);
        }
        catch (Exception e) {
            log.error("Exception  is happening" , e);
        }
        finally {
            //关闭流，释放连接
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

