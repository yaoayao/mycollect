package com.cc.thread;


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

    public static void main(String[] args) {

    }
}
