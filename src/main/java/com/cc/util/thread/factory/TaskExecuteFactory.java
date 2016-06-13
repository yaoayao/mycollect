package com.cc.util.thread.factory;

import com.cc.util.thread.inf.TaskExecute;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * �߳�ִ�й�����
 * @author yfguopeng
 *
 * @param <T>
 */
public class TaskExecuteFactory<T> {
	private static Log log = LogFactory.getLog(TaskExecuteFactory.class);
	private Map<String, TaskExecute<T>> tasks = new HashMap<String, TaskExecute<T>>();
	private List<String> keys = new LinkedList<String>();
	private boolean ignoreException;
	private boolean isAsyn;
	private long timeout = 5;//Ĭ�ϵ�λ��
	
	/**
	 * �Ƿ�����쳣
	 * Ĭ��ͬ��ִ��
	 */
	@SuppressWarnings("rawtypes")
	public static TaskExecuteFactory create(boolean ignore, boolean asyn, long timeout) {
		TaskExecuteFactory  factory = new TaskExecuteFactory();
		factory.setIgnoreException(ignore);
		factory.setAsyn(asyn);
		factory.setTimeout(timeout);
		return factory;
	}
	/**
	 * Ĭ�� �����쳣
	 * Ĭ�� ͬ��ִ��
	 * Ĭ�� ��ʱʱ��5��
	 */
	@SuppressWarnings("rawtypes")
	public static TaskExecuteFactory create() {
		return TaskExecuteFactory.create(true,false,5);
	}
	
	/**
	 * key ��Ҫ�첽ִ��������ı�ʾ��������Ҫ�ô˱�ʾ��ȡ�����
	 */
	public TaskExecuteFactory<T> registerTask(String key, TaskExecute<T> te){
		tasks.put(key, te);
		keys.add(key);
		return this;
	}
	
	/**
	 * ע�⣬��Ҫҵ���Լ���֤��ʱʱ������
	 * @param bizName
	 * @return
	 * @throws Exception
	 */
	public Map<String, T> getResult(String bizName) throws Exception {
		ThreadPoolExecutor executor = ThreadGroupFactory.getThreadWorker(bizName);
		Map<String, T> resultMap = new HashMap<String, T>();
		//����̳߳�û�йرգ������첽���������ͬ���ӿ�ʵ�֣���֤���񲻳�������
		if (isAsyn() && executor != null && !executor.isShutdown()) {
			log.info("�첽ִ�� >>>>>> "+bizName);
			asynExecute(bizName,executor,resultMap);
		} else {
			log.info("ͬ��ִ�� >>>>>> "+bizName);
			synExecute(bizName,resultMap);
		}
		return resultMap;
	}
	
	/**
	 * ͬ��ִ��
	 * @param resultMap
	 * @throws Exception
	 */
	private void synExecute(String bizName, Map<String, T> resultMap) throws Exception {
		for (String key : keys) {
			TaskExecute<T> te = tasks.get(key);
			try{
				T res = te.execute();
				resultMap.put(key, res);
			} catch(Exception e) {
				log.error("ͬ��ִ���쳣 >>>>>> task = "+key, e);
				if ( ! ignoreException ) throw e;
			}
		}
	}
	
	/**
	 * �첽ִ��
	 * @param executor
	 * @param resultMap
	 * @throws Exception
	 */
	private void asynExecute(String bizName, ThreadPoolExecutor executor, Map<String, T> resultMap) throws Exception {
		Map<String, Future<T>> tempResult = new HashMap<String, Future<T>>();
		CountDownLatch latch = new CountDownLatch(keys.size());
		for (String key : keys) {
			TaskExecute<T> te = tasks.get(key);
			te.setLatch(latch);
			try{
				Future<T> f = executor.submit(te);
				tempResult.put(key, f);
			} catch(Exception e) {
				log.error("�첽ִ���쳣 >>>>>> task = "+key, e);
				if ( ! ignoreException ) throw e;
			}
		}
		try {
			long time = getTimeout();
			if (time == 0) time = 5;
			log.info("�첽ִ�еȴ���ʱʱ�� >>>>>> timeout = "+time+" seconds");
			latch.await(getTimeout(), TimeUnit.SECONDS);
		} catch (Exception e) {
			log.error("�첽ִ�г�ʱ >>>>>> bizName = "+bizName, e);
			return;
		}
		for (String key : keys) {
			try{
				resultMap.put(key, tempResult.get(key).get(tasks.get(key).getTimeout(),TimeUnit.MILLISECONDS));
			} catch(Exception e) {
				log.error("�첽ִ���쳣 >>>>>> task = "+key, e);
				if ( ! ignoreException ) throw e;
			}
		}
	}
	public boolean isIgnoreException() {
		return ignoreException;
	}
	public void setIgnoreException(boolean ignoreException) {
		this.ignoreException = ignoreException;
	}
	public boolean isAsyn() {
		return isAsyn;
	}
	public void setAsyn(boolean isAsyn) {
		this.isAsyn = isAsyn;
	}
	public long getTimeout() {
		return timeout;
	}
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
}
