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
 * 线程执行工厂类
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
	private long timeout = 5;//默认单位秒

	/**
	 * 是否忽略异常
	 * 默认同步执行
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
	 * 默认 忽略异常
	 * 默认 同步执行
	 * 默认 超时时间5秒
	 */
	@SuppressWarnings("rawtypes")
	public static TaskExecuteFactory create() {
		return TaskExecuteFactory.create(true,false,5);
	}

	/**
	 * key 需要异步执行任务类的标示，后续需要用此标示获取结果集
	 */
	public TaskExecuteFactory<T> registerTask(String key, TaskExecute<T> te){
		tasks.put(key, te);
		keys.add(key);
		return this;
	}

	/**
	 * 注意，需要业务自己保证超时时间设置
	 * @param bizName
	 * @return
	 * @throws Exception
	 */
	public Map<String, T> getResult(String bizName) throws Exception {
		ThreadPoolExecutor executor = ThreadGroupFactory.getThreadWorker(bizName);
		Map<String, T> resultMap = new HashMap<String, T>();
		//如果线程池没有关闭，调用异步，否则调用同步接口实现，保证服务不出现问题
		if (isAsyn() && executor != null && !executor.isShutdown()) {
			log.info("异步执行 >>>>>> "+bizName);
			asynExecute(bizName,executor,resultMap);
		} else {
			log.info("同步执行 >>>>>> "+bizName);
			synExecute(bizName,resultMap);
		}
		return resultMap;
	}

	/**
	 * 同步执行
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
				log.error("同步执行异常 >>>>>> task = "+key, e);
				if ( ! ignoreException ) throw e;
			}
		}
	}

	/**
	 * 异步执行
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
				log.error("异步执行异常 >>>>>> task = "+key, e);
				if ( ! ignoreException ) throw e;
			}
		}
		try {
			long time = getTimeout();
			if (time == 0) time = 5;
			log.info("异步执行等待超时时间 >>>>>> timeout = "+time+" seconds");
			latch.await(getTimeout(), TimeUnit.SECONDS);
		} catch (Exception e) {
			log.error("异步执行超时 >>>>>> bizName = "+bizName, e);
			return;
		}
		for (String key : keys) {
			try{
				resultMap.put(key, tempResult.get(key).get(tasks.get(key).getTimeout(),TimeUnit.MILLISECONDS));
			} catch(Exception e) {
				log.error("异步执行异常 >>>>>> task = "+key, e);
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
