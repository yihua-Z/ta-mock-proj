package com.psbc.utils.concurrent;

public interface MultiCompletable {
    // 方便不同的线程同时执行不同的task
    int getTasksSize();
    // 一个线程可单独执行的内容
    void singleProcess(int index);
}