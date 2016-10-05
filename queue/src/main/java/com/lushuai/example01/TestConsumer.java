package com.lushuai.example01;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BlockingQueue 生产者消费者　　
 * 参考：　http://blog.csdn.net/hopezhangbo/article/details/7994585
 *
 * 本例介绍一个特殊的队列：BlockingQueue，如果BlockingQueue是空的，从BlockingQueue取东西的操作将会被阻断进入等待状态，直到BlockingQueue进了东西才会被唤醒，同样，如果BlockingQueue是满的，任何试图往里存东西的操作也会被阻断进入等待状态，直到BlockingQueue里有空间时才会被唤醒继续操作。
 本例再次实现前面介绍的篮子程序，不过这个篮子中最多能放得苹果数不是1，可以随意指定。当篮子满时，生产者进入等待状态，当篮子空时，消费者等待。

 BlockingQueue定义的常用方法如下：
 add(anObject)：把anObject加到BlockingQueue里，如果BlockingQueue可以容纳，则返回true，否则抛出异常。
 offer(anObject)：表示如果可能的话，将anObject加到BlockingQueue里，即如果BlockingQueue可以容纳，则返回true，否则返回false。
 put(anObject)：把anObject加到BlockingQueue里，如果BlockingQueue没有空间，则调用此方法的线程被阻断直到BlockingQueue里有空间再继续。
 poll(time)：取走BlockingQueue里排在首位的对象，若不能立即取出，则可以等time参数规定的时间，取不到时返回null。
 take()：取走BlockingQueue里排在首位的对象，若BlockingQueue为空，阻断进入等待状态直到BlockingQueue有新的对象被加入为止。

 BlockingQueue有四个具体的实现类，根据不同需求，选择不同的实现类：
 ArrayBlockingQueue：规定大小的BlockingQueue，其构造函数必须带一个int参数来指明其大小。其所含的对象是以FIFO（先入先出）顺序排序的。
 LinkedBlockingQueue：大小不定的BlockingQueue，若其构造函数带一个规定大小的参数，生成的BlockingQueue有大小限制，若不带大小参数，所生成的BlockingQueue的大小由Integer.MAX_VALUE来决定。其所含的对象是以FIFO顺序排序的。
 PriorityBlockingQueue：类似于LinkedBlockingQueue,但其所含对象的排序不是FIFO，而是依据对象的自然排序顺序或者是构造函数所带的Comparator决定的顺序。
 SynchronousQueue：特殊的BlockingQueue，对其的操作必须是放和取交替完成的。

 LinkedBlockingQueue和ArrayBlockingQueue比较起来，它们背后所用的数据结构不一样，导致LinkedBlockingQueue的数据吞吐量要大于ArrayBlockingQueue，但在线程数量很大时其性能的可预见性低于ArrayBlockingQueue。
 * Created by lushuai on 16-7-17.
 */
public class TestConsumer {
    private static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);


    public static void main(String [] args)
    {
        ExecutorService service = Executors.newCachedThreadPool();
        Fetcher producer = new Fetcher(queue);
        Indexer consumer = new Indexer(queue);
        Indexer consumer1 = new Indexer(queue);
        service.submit(producer);
        service.submit(consumer);
        service.submit(consumer1);
        // 程序运行5s后，所有任务停止
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdownNow();
    }
}