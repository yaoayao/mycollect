package com.cc.util.thread.factory;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.cc.util.thread.ThreadPoolExecutor;

/**
 * 各个业务获取响应线程池
 * @author yfguopeng
 */
public class ThreadGroupFactory {
	private static Map<String, ThreadPoolExecutor> threadworkers;

	static {
		threadworkers = new ConcurrentHashMap<String, ThreadPoolExecutor>();
	}
	
	public static void addThreadWorker(String bizName,ThreadPoolExecutor executor){
		threadworkers.put(bizName, executor);
	}
	
	public static ThreadPoolExecutor getThreadWorker(String bizName) {
		return threadworkers.get(bizName);
	}
	
	public static Map<String, ThreadPoolExecutor> getThreadWorkers(){
		return threadworkers;
	}

}
