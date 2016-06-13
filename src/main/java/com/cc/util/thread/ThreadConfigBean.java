package com.cc.util.thread;

import java.io.Serializable;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@SuppressWarnings("serial")
public class ThreadConfigBean implements Serializable{
	/**
	 * 业务ID
	 */
	private String businessId;
	/**
	 * 任务队列最大线程数
	 * 默认：80
	 */
	private Integer max = 160;
	/**
	 * 任务队列最小线程数
	 * 默认：40
	 */
	private Integer min = 80;
	/**
	 * 等待队列请求数
	 * 默认：300
	 */
	private Integer queueCapacity = 300;
	/**
	 * 空闲线程存活时间
	 * 默认：3分钟
	 */
	private Long keepAliveTime = 3 * 60l;
	/**
	 * 线程拒绝策略
	 */
	private RejectedExecutionHandler rejectHandler = new ThreadPoolExecutor.AbortPolicy();
	
	public ThreadConfigBean() {
		super();
	}
	
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}
	public Integer getMin() {
		return min;
	}
	public void setMin(Integer min) {
		this.min = min;
	}
	public Integer getQueueCapacity() {
		return queueCapacity;
	}
	public void setQueueCapacity(Integer queueCapacity) {
		this.queueCapacity = queueCapacity;
	}
	public Long getKeepAliveTime() {
		return keepAliveTime;
	}
	public void setKeepAliveTime(Long keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}
	public RejectedExecutionHandler getRejectHandler() {
		return rejectHandler;
	}
	public void setRejectHandler(RejectedExecutionHandler rejectHandler) {
		this.rejectHandler = rejectHandler;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
}
