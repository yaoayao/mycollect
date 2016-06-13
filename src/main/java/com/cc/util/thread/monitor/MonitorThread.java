package com.cc.util.thread.monitor;

import com.cc.util.thread.RejectedPolicyHandlerInteface;
import com.cc.util.thread.factory.ThreadGroupFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.cc.util.thread.ThreadPoolExecutor;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.RejectedExecutionHandler;

/**
 * 监控业务线程池运行情况
 * @author yfguopeng
 * @Date 2013-02-28
 */
public class MonitorThread implements Runnable {
	private final static Log log = LogFactory.getLog(MonitorThread.class);
	private final ThreadGroup group;
	
	public MonitorThread(ThreadGroup group) {
		this.group = group;
	}
	
	public void run() {
		Map<String, ThreadPoolExecutor> workers =  ThreadGroupFactory.getThreadWorkers();
		Iterator<String> iterator = workers.keySet().iterator();
		
		log.info("total threadpools:[ "+workers.size()+" ],total threads:[ "+group.activeCount()+" ]");
		while(iterator.hasNext()) {
			ThreadPoolExecutor worker = ThreadGroupFactory.getThreadWorker(iterator.next());
			
			RejectedExecutionHandler handler = worker.getRejectedExecutionHandler();
			String rejectedSize = "";
			if (RejectedPolicyHandlerInteface.class.isAssignableFrom(handler.getClass())) {
				rejectedSize = " ],rejected threads:[ "+((RejectedPolicyHandlerInteface) handler).getRejectedSize();
			}
			
			log.info("business name:[ "+worker.getBizName()+" ],core threads:[ "+worker.getCorePoolSize()+" ],max threads:[ "+worker.getMaximumPoolSize()+" ],queue capacitys:[ "+worker.getQueue().size()+" ],running threads:[ "+worker.getActiveCount()+rejectedSize+" ],  largest threads:[ "+worker.getLargestPoolSize()+" ],complete threads:[ "+worker.getCompletedTaskCount()+" ]");
			
			double per = (double)worker.getLargestPoolSize()/worker.getMaximumPoolSize();
			if (Double.compare(per, 0.80) > 0) {//当市盈率超过80%，报警
				DecimalFormat nf = new DecimalFormat("0.00");
//				Profiler.businessAlarm(worker.getBizName()+"_thread_largest",(new Date()).getTime(),"线程池最大容量已达到80%, 百分比 = "+nf.format(per));
			}
		}
	}

}
