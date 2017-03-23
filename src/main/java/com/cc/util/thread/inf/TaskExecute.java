package com.cc.util.thread.inf;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;


public abstract class TaskExecute<T> implements Callable<T>{

	private CountDownLatch latch;
	
	/**
	 */
	private long timeout = 3000;
	
	public T call() throws Exception {
		T o = null;
		try {
			o = execute();
		} catch (Exception e) {
			throw e;
		} finally {
			latch.countDown();
		}
		return o;
	}
	
	public abstract T execute() throws Exception;

	public CountDownLatch getLatch() {
		return latch;
	}

	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	
}
