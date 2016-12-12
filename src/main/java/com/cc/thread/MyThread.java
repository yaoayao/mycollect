package com.cc.thread;


import java.util.concurrent.CountDownLatch;

/**
 * Created by chengwanchao on 2016/9/26.
 */
public class MyThread {

//    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        Callable<Object> task = new Callable<Object>() {
//            @Override
//            public Object call() throws Exception {
//                System.out.println("call------");
//                String s = "result";
//                Thread.sleep(5000);
//                return s;
//            }
//        };
//        Future<Object> submit = executor.submit(task);
//        System.out.println(submit.get());
//    }


    //    public static void main(String[] args) {
//        final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
//        Thread consumer = new Thread("consumer"){
//            @Override
//            public void run() {
//                for (; ; ) {
//                    try {
//                        String s = queue.take();
//                        System.out.println("take ---" + s);
//                    } catch (InterruptedException e) {
//                        break;
//                    }
//                }
//
//            }
//        };
//        Thread product = new Thread("product"){
//            @Override
//            public void run() {
//                while (true){
//                    Random random = new Random();
//
//                    try {
//                        String s = String.valueOf(random.nextInt(10));
//                        queue.put(s);
//                        System.out.println("pru---" + s);
//                    } catch (InterruptedException e) {
//                        break;
//                    }
//                }
//            }
//        };
//        product.start();
//        consumer.start();
//
//    }
    static CountDownLatch countDownLatch = new CountDownLatch(1);
    static int i = 0;
    public static void main(String[] args) {
//        final Counter counter = new Counter();
//        for (int i = 0; i < 1000; i++) {
//            Thread thread = new Thread(){
//                @Override
//                public void run() {
//                    try {
//                        countDownLatch.await();
//                        System.out.println("----run-----");
//
//                        System.out.println(counter.getCount());;
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            };
//            thread.start();
//        }
        countDownLatch.countDown();
        System.out.println("---start---");
    }
}
