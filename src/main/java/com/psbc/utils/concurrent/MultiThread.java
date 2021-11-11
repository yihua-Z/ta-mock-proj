package com.psbc.utils.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThread {

    private MultiCompletable wholeTask;
    private ExecutorService threadPool;

    public MultiThread(int threadSize, MultiCompletable task){
        this.threadPool = Executors.newFixedThreadPool(threadSize);
        this.wholeTask = task;
    }

    /**
     * 执行(无需返回值)
     */
    public void execute(){
        int size = wholeTask.getTasksSize();
        for(int i = 0; i < size; ++i){
            int finalI = i;
            threadPool.execute(()->{
                try {
                    wholeTask.singleProcess(finalI);
                } catch (Exception e){
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }
}