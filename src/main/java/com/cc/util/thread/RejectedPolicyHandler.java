package com.cc.util.thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

/**
 * �ܾ߳̾�ִ�п�����
 * @author yfguopeng
 * @Date 2013-02-28
 */
public class RejectedPolicyHandler extends ThreadPoolExecutor.AbortPolicy implements RejectedPolicyHandlerInteface{
	private final static Log log = LogFactory.getLog(RejectedPolicyHandler.class);
	private static AtomicLong totals = new AtomicLong(0l);
	
	private String bizName;
	
	public RejectedPolicyHandler(){}
	
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		String tip = "["+bizName+"] �߳�æ�����󱻾ܾ�.max: "+executor.getMaximumPoolSize()+", queue: "+executor.getQueue().size();
		log.info(tip);
//		Profiler.businessAlarm(bizName+"_thread_busy",(new Date()).getTime(),tip);
		totals.addAndGet(1);
		super.rejectedExecution(r, executor);
	}

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}
	
	public long getRejectedSize() {
		return totals.get();
	}
}
