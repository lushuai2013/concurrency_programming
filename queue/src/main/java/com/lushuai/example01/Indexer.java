package com.lushuai.example01;

import java.util.concurrent.BlockingQueue;

/**
 * Created by lushuai on 16-7-17.
 */
public class Indexer implements Runnable
{

    private BlockingQueue<String> queue ;
    public Indexer(BlockingQueue<String> queue)
    {
        this.queue = queue;
    }

    public void run()
    {
        try {
            while(true) {
                Thread.sleep(1000);
                String name = queue.take();
                System.out.println("ThreadName : " +Thread.currentThread().getName()+ " 索引创建完成 " +name);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}