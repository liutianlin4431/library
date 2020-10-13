#笔记：
###内存可见性
**内存可见性问题是，当多个线程同时操作共享数据时，彼此时不可见的！**
#####一、解决内存可见性问题：
~~~
1. 增加synchronized【同步锁】【多个线程时效率过低】  
2. 使用volatile关键字【当多个线程操作共享数据时，可以保证内存中数据可见】	
~~~
#####volatile与synchronized的区别：
~~~
1. volatile不存在互斥性  
2. volatile不能保证变量的原子性
~~~
#####二、原子变量：jdk1.5后java.util.concurrent.atomic包下提供了常用的源自变量：
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