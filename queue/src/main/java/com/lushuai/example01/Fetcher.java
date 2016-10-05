package com.lushuai.example01;

import java.util.concurrent.BlockingQueue;

/**
 * Created by lushuai on 16-7-17.
 */
public class Fetcher  implements Runnable
{
    @SuppressWarnings("unused")
    private BlockingQueue<String> queue = null ;

    public Fetcher(BlockingQueue<String> queue)
    {
        this.queue = queue;
    }

    public void run()
    {
        int i=0;
        try {
            while (true) {
                queue.put("segment-name-"+i);
                System.out.println("ThreadName : "+ Thread.currentThread().getName() +" 抓取完成");
                i++;
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}