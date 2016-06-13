package com.cc.util.thread;

import java.util.concurrent.RejectedExecutionHandler;

public interface RejectedPolicyHandlerInteface extends RejectedExecutionHandler{
	public long getRejectedSize() ;
}
