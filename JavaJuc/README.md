笔记：
=
内存可见性
---
**内存可见性问题是，当多个线程同时操作共享数据时，彼此时不可见的！**  
一、解决内存可见性问题：
-----
~~~
1. 增加synchronized【同步锁】【多个线程时效率过低】  
2. 使用volatile关键字【当多个线程操作共享数据时，可以保证内存中数据可见】	
~~~
二、volatile与synchronized的区别：
-----
~~~
1. volatile不存在互斥性  
2. volatile不能保证变量的原子性
~~~
三、原子变量：jdk1.5后java.util.concurrent.atomic包下提供了常用的源自变量：
-----
~~~
1. volatile保证内存可见性  
2. CAS(Compare And Swap)算法保证数据的原子性  
	CAS算法是硬件对于并发操作共享数据的执行  
	CAS包含三个操作
	内存值V
	预估值A
	更新值B
	当且仅当V==A时，V=B，否则不做任何操作
~~~
线程
---
一、线程八锁关键（线程锁）
-----
~~~
1. 非静态方法得锁默认为this，静态方法得锁为对应得Class实例
2. 某一个时刻内，只能有一个线程持有锁，无论几个方法
~~~
二、线程池的体系结构
-----
~~~
java.util.concurrent.Executor : 负责线程的使用与调度的根接口
 		|--*ExecutorService 子接口: 线程池的主要接口
 			|--ThreadPoolExecutor 线程池的实现类
 			|--ScheduledExecutorService 子接口：负责线程的调度
 				|--ScheduledThreadPoolExecutor ：继承 ThreadPoolExecutor， 实现 ScheduledExecutorService
~~~
 三、工具类 : Executors 
-----
~~~
	ExecutorService newFixedThreadPool() : 创建固定大小的线程池
	ExecutorService newCachedThreadPool() : 缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量。
	ExecutorService newSingleThreadExecutor() : 创建单个线程池。线程池中只有一个线程
	ScheduledExecutorService newScheduledThreadPool() : 创建固定大小的线程，可以延迟或定时的执行任务。
 ~~~