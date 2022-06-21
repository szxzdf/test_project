package com.rose.git;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zjh
 * @date 2022/5/26 21:55
 * @description
 */
public class GitTest {

    static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        // System.out.println("hello pcy!");
        // System.out.println("hello pcy1!");
        // System.out.println("hello pcy2!");
        // System.out.println("hello pcy3!");
        // System.out.println("master test");
        // System.out.println("hot-fix test");
        // System.out.println("push test");
        // System.out.println("test-back-check");

        //
        //   corePoolSize:核心线程池大小
        //     maximumPoolSize:最大核心线程池大小
        //     keepAliveTime:空闲线程存活时间
        //     unit:时间单位
        //     workQueue:阻塞队列
        //     threadFactory:线程工厂：创建线程的，一般不用动
        //     handler:拒绝策略
        //  拒绝策略
        //  new ThreadPoolExecutor.AbortPolicy() // 不执行新任务，直接抛出异常，提示线程池已满
        //  new ThreadPoolExecutor.CallerRunsPolicy() // 哪来的去哪里！由调用线程处理该任务
        //  new ThreadPoolExecutor.DiscardPolicy() //不执行新任务，也不抛出异常
        //  new ThreadPoolExecutor.DiscardOldestPolicy() //丢弃队列最前面的任务，然后重新提交被拒绝的任务。



        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        Runnable runnable = () -> {
            for (int i = 1; i <= 100; i++) {
                atomicInteger.incrementAndGet();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(atomicInteger.get());
        };
        threadPoolExecutor.execute(runnable);
        // System.out.println(count[0]);
        // System.out.println("=============");
        threadPoolExecutor.execute(runnable);

    }

}
