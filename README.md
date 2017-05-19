# Everything I need to know about Threads. 

### Question 1. What is Thread in java?
* Threads consumes CPU in best possible manner, hence enables multi processing. Multi threading reduces idle time of CPU which improves    performance of application.
* Thread are light weight process.
* A thread class belongs to java.lang package.
* We can create multiple threads in java, even if we don’t create any Thread, one Thread at least  do exist i.e. main thread.
* Multiple threads run parallely in java.  
* Threads have their own stack.

Advantage of Thread : Suppose one thread needs 10 minutes to get certain task, 10 threads used at a time could complete that task in 1 minute, because threads can run parallely.

### Question 2. What is difference between Process and Thread in java?

Answer.  One process can have multiple Threads,
* Thread are subdivision of Process. One or more Threads runs in the context of process. Threads can execute any part of process. And same part of process can be executed by multiple Threads.
* Processes have their own copy of the data segment of the parent process while Threads have direct access to the data segment of its process.
* Processes have their own address while Threads share the address space of the process that created it.
* Process creation needs whole lot of stuff to be done, we might need to copy whole parent process, but Thread can be easily created.
* Processes can easily communicate with child processes but interprocess communication is difficult. While, Threads can easily communicate with other threads of the same process using wait() and notify() methods.
* In process all threads share system resource like heap Memory etc. while Thread has its own stack.
* Any change made to process does not affect child processes, but any change made to thread can affect the behavior of the other threads of the process.

### Question 3. How to implement Threads in java?

This is very basic threading question. Threads can be created in two ways i.e. by implementing java.lang.Runnable interface or extending java.lang.Thread class and then extending run method.

Thread has its own variables and methods, it lives and dies on the heap. But a thread of execution is an individual process that has its own call stack. Thread are lightweight process in java.

Thread creation by  implementingjava.lang.Runnableinterface.

* We will create object of class which implements Runnable interface :

    MyRunnable runnable=new MyRunnable();
    Thread thread=new Thread(runnable);
    
* And then create Thread object by calling constructor and passing reference of Runnable interface i.e.  runnable object :

    Thread thread=new Thread(runnable);

### Question 4 . Does Thread implements their own Stack, if yes how? (Important)

Answer.  Yes, Threads have their own stack. This is very interesting question, where interviewer tends to check your basic knowledge about how threads internally maintains their own stacks. I’ll be explaining you the concept by diagram.

### Question 5. We should implement Runnable interface or extend Thread class. What are differences between implementing Runnable and extending Thread?

Answer. Well the answer is you must extend Thread only when you are looking to modify run() and other methods as well. If you are simply looking to modify only the run() method implementing Runnable is the best option (Runnable interface has only one abstract method i.e. run() ).  

Differences between implementing Runnable interface and extending Thread class -

1. Multiple inheritance in not allowed in java : When we implement Runnable interface we can extend another class as well, but if we extend Thread class we cannot extend any other class because java does not allow multiple inheritance. So, same work is done by implementing Runnable and extending Thread but in case of implementing Runnable we are still left with option of extending some other class. So, it’s better to implement Runnable.
2. Thread safety : When we implement Runnable interface, same object is shared amongst multiple threads, but when we extend Thread class each and every thread gets associated with new object. 
3. Inheritance (Implementing Runnable is lightweight operation) : When we extend Thread unnecessary all Thread class features are inherited, but when we implement Runnable interface no extra feature are inherited, as Runnable only consists only of one abstract method i.e. run() method. So, implementing Runnable is lightweight operation.
4. Coding to interface : Even java recommends coding to interface. So, we must implement Runnable rather than extending thread. Also, Thread class implements Runnable interface.
5. Don’t extend unless you wanna modify fundamental behaviour of class, Runnable interface has only one abstract method i.e. run()  : We must extend Thread only when you are looking to modify run() and other methods as well. If you are simply looking to modify only the run() method implementing Runnable is the best option (Runnable interface has only one abstract method i.e. run() ). We must not extend Thread class unless we're looking to modify fundamental behaviour of Thread class.
6. Flexibility in code when we implement Runnable : When we extend Thread first a fall all thread features are inherited and our class becomes direct subclass of Thread , so whatever action we are doing is in Thread class. But, when we implement Runnable we create a new thread and pass runnable object as parameter,we could pass runnable object to executorService & much more. So, we have more options when we implement Runnable and our code becomes more flexible.
7. ExecutorService : If we implement Runnable, we can start multiple thread created on runnable object  with ExecutorService (because we can start Runnable object with new threads), but not in the case when we extend Thread (because thread can be started only once).

### Question 6. How can you say Thread behaviour is unpredictable? (Important)

Answer. The solution to question is quite simple, Thread behaviour is unpredictable because execution of Threads depends on Thread scheduler, thread scheduler may have different implementation on different platforms like windows, unix etc. Same threading program may produce different output in subsequent executions even on same platform.

To achieve we are going to create 2 threads on same Runnable Object, create for loop in run() method and start  both threads. There is no surety that which threads will complete first,  both threads will enter anonymously in for loop.

### Question 7 . When threads are not lightweight process in java?

Answer. Threads are lightweight process only if threads of same process are executing concurrently. But if threads of different processes are executing concurrently then threads are heavy weight process.

### Question 8. How can you ensure all threads that started from main must end in order in which they started and also main should end in last? (Important)

Answer.  Interviewers tend to know interviewees knowledge about Thread methods. So this is time to prove your point by answering correctly. We can use join() methodto ensure all threads that started from main must end in order in which they started and also main should end in last.In other words waits for this thread to die. Calling join() method internally calls join(0);

### Question 9. What is difference between starting thread with run() and start() method? (Important)

Answer. This is quite interesting question, it might confuse you a bit and at time may make you think is there really any difference between starting thread with run() and start() method.
* When you call start() method, main thread internally calls run() method to start newly created Thread, so run() method is ultimately called by newly created thread.
* When you call run() method main thread rather than starting run() method with newly thread it start run() method by itself.

### Question 10. What is significance of using Volatile keyword? (Important)

Answer. Java allows threads to access shared variables. As a rule, to ensure that shared variables are consistently updated, a thread should ensure that it has exclusive use of such variables by obtaining a lock that enforces mutual exclusion for those shared variables.

If a field is declared volatile, in that case the Java memory model ensures that all threads see a consistent value for the variable.

Few small questions>

Q. Can we have volatile methods in java?

* No, volatile is only a keyword, can be used only with variables.

Q. Can we have synchronized variable in java?

* No, synchronized can be used only with methods, i.e. in method declaration.

### Question 11. Differences between synchronized and volatile keyword in Java? (Important)

Answer.Its very important question from interview perspective.

1. Volatile can be used as a keyword against the variable, we cannot use volatile against method declaration.

volatile void method1(){} //it’s illegal, compilation error.

While synchronization can be used in method declaration or we can create synchronization blocks (In both cases thread acquires lock on object’s monitor). Variables cannot be synchronized.

Volatile does not acquire any lock on variable or object, but Synchronization acquires lock on method or block in which it is used.

Volatile variables are not cached, but variables used inside synchronized method or block are cached.

When volatile is used will never create deadlock in program, as volatile never obtains any kind of lock . But in case if synchronization is not done properly, we might end up creating dedlock in program.

Synchronization may cost us performance issues, as one thread might be waiting for another thread to release lock on object. But volatile is never expensive in terms of performance.

### Question 12. Can you again start Thread?

Answer. No, we cannot start Thread again, doing so will throw runtimeException java.lang.IllegalThreadStateException. The reason is once run() method is executed by Thread, it goes into dead state.

Let’s take an example-

Thinking of starting thread again and calling start() method on it (which internally is going to call run() method) for us is some what like asking dead man to wake up and run. As, after completing his life person goes to dead state.

### Question 13. What is race condition in multithreading and how can we solve it? (Important)

Answer. This is very important question, this forms the core of multi threading, you should be able to explain about race condition in detail. When more than one thread try to access same resource without synchronization causes race condition.

So we can solve race condition by using either synchronized block or synchronized method. When no two threads can access same resource at a time phenomenon is also called as mutual exclusion.

##### Few sub questions
1. What if two threads try to read same resource without synchronization?

2. When two threads try to read on same resource without synchronization, it’s never going to create any problem.

3. What if two threads try to write to same resource without synchronization?

4. When two threads try to write to same resource without synchronization, it’s going to create synchronization problems.

### Question 14. How threads communicate between each other?

Answer. This is very must know question for all the interviewees, you will most probably face this question in almost every time you go for interview.
* Threads can communicate with each other by using wait(), notify() and notifyAll() methods.

### Question 15. Why wait(), notify()  and notifyAll() are in Object class and not in Thread class? (Important)

Answer. Every Object has a monitor, acquiring that monitors allow thread to hold lock on object. But Thread class does not have any monitors.

wait(), notify() and notifyAll()are called on objects only >When wait() method is called on object by thread it waits for another thread on that object to release object monitor by calling notify() or notifyAll() method on that object.

When notify() method is called on object by thread it notifies all the threads, which are waiting for that object monitor that object monitor is available now.

So, this shows that wait(), notify() and notifyAll() are called on objects only.

Now, Straight forward question that comes to mind is how thread acquires object lock by acquiring object monitor? Let’s try to understand this basic concept in detail?

Wait(), notify() and notifyAll() method being in Object class allows all the threads created on that object to communicate with other.  .
As multiple threads exists on same object. Only one thread can hold object monitor at a time. As a result thread can notify other threads of same object that lock is available now. But, thread having these methods does not make any sense because multiple threads exists on object its not other way around (i.e. multiple objects exists on thread).
Now let’s discuss one hypothetical scenario, what will happen if Thread class contains wait(), notify() and notifyAll() methods?
Having wait(), notify() and notifyAll() methods means Thread class also must have their monitor.

###### Every thread having their monitor will create few problems:
* Thread communication problem.
* Synchronization on object won’t be possible- Because object has monitor, one object can have multiple threads and thread hold lock on object by holding object monitor. But if each thread will have monitor, we won’t have any way of achieving synchronization.
* Inconsistency in state of object (because synchronization won't be possible).

### Question 16. Is it important to acquire object lock before calling wait(), notify() and notifyAll()?

Answer. Yes, it’s mandatory to acquire object lock before calling these methods on object. As discussed above wait(), notify()  and notifyAll() methods are always called from Synchronized block only, and as soon as thread enters synchronized block it acquires object lock (by holding object monitor). If we call these methods without acquiring object lock i.e. from outside synchronize block then java.lang. IllegalMonitorStateException is thrown at runtime.

Wait() method needs to enclosed in try-catch block, because it throws compile time exception i.e. InterruptedException.

### Question 17. How can you solve consumer producer problem by using wait() and notify() method? (Important)

Answer.  Here come the time to answer very very important question from interview perspective. Interviewers tends to check how sound you are in threads inter communication. Because for solving this problem we got to use synchronization blocks, wait() and notify() method very cautiously. If you misplace synchronization block or any of the method, that may cause your program to go horribly wrong. So, before going into this question first i’ll recommend you to understand how to use synchronized blocks, wait() and notify() methods.

* Key points we need to ensure before programming :

> Producer will produce total of 10 products and cannot produce more than 2 products at a time until products are being consumed by consumer.

Example > when sharedQueue’s size is 2, wait for consumer to consume (consumer will consume by calling remove(0) method on sharedQueue and reduce sharedQueue’s size). As soon as size is less than 2, producer will start producing.

> Consumer can consume only when there are some products to consume.

Example > when sharedQueue’s size is 0, wait for producer to produce (producer will produce by calling add() method on sharedQueue and increase sharedQueue’s size). As soon as size is greater than 0, consumer will start consuming.

Explanation of Logic >

We will create sharedQueue that will be shared amongst Producer and Consumer. We will now start consumer and producer thread.

Note: it does not matter order in which threads are started (because rest of code has taken care of synchronization and key points mentioned above)

First we will start consumerThread >

consumerThread.start();
consumerThread will enter run method and call consume() method. There it will check for sharedQueue’s size.

-if size is equal to 0 that means producer hasn’t produced any product, wait for producer to produce by using below piece of code-
```
synchronized (sharedQueue) {
    while (sharedQueue.size() == 0) { 
       sharedQueue.wait();
    }
 }
```
-if size is greater than 0, consumer will start consuming by using below piece of code.
```
synchronized (sharedQueue) {
    Thread.sleep((long)(Math.random() * 2000));
    System.out.println("consumed : "+ sharedQueue.remove(0));
    sharedQueue.notify();
 }
```
Than we will start producerThread > 
producerThread.start();

producerThread will enter run method and call produce() method. There it will check for sharedQueue’s size.

-if size is equal to 2 (i.e. maximum number of products which sharedQueue can hold at a time), wait for consumer to consume by using below piece of code-
```
 synchronized (sharedQueue) {
     while (sharedQueue.size() == maxSize) { //maxsize is 2
         sharedQueue.wait();
     }
 }
 ```
 
-if size is less than 2, producer will start producing by using below piece of code.
```
synchronized (sharedQueue) {
    System.out.println("Produced : " + i);
    sharedQueue.add(i);
    Thread.sleep((long)(Math.random() * 1000));
    sharedQueue.notify();
}
```

DETAILED DESCRIPTION with program : Solve Consumer Producer problem by using wait() and notify() methods in multithreading.

### Question 18. How to solve Consumer Producer problem without using wait() and notify() methods, where consumer can consume only when production is over.?

Answer. In this problem, producer will allow consumer to consume only when 10 products have been produced (i.e. when production is over).

We will approach by keeping one boolean variable productionInProcess and initially setting it to true, and later when production will be over we will set it to false.

### Question 19. How can you solve consumer producer pattern by using BlockingQueue? (Important)

Answer. Now it’s time to gear up to face question which is most probably going to be followed up by previous question i.e. after how to solve consumer producer problem using wait() and notify() method. Generally you might wonder why interviewer's are so much interested in asking about solving consumer producer problem using BlockingQueue, answer is they want to know how strong knowledge you have about java concurrent Api’s, this Api use consumer producer pattern in very optimized manner, BlockingQueue is designed is such a manner that it offer us the best performance.

BlockingQueue is a interface and we will use its implementation class LinkedBlockingQueue.

###### Key methods for solving consumer producer pattern are:

put(i);  //used by producer to put/produce in sharedQueue.

take();//used by consumer to take/consume from sharedQueue.

### Question 20. What is deadlock in multithreading? Write a program to form DeadLock in multi threading and also how to solve DeadLock situation. What measures you should take to avoid deadlock? (Important)

Answer.  This is very important question from interview perspective. But, what makes this question important is it checks interviewees capability of creating and detecting deadlock. If you can write a code to form deadlock, than I am sure you must be well capable in solving that deadlock as well. If not, later on this post we will learn how to solve deadlock as well.

First question comes to mind is, what is deadlock in multi threading program?

Deadlock is a situation where two threads are waiting for each other to release lock holded by them on resources.

But how deadlock could be formed :

Thread-1 acquires lock on String.class and then calls sleep() method which gives Thread-2 the chance to execute immediately after Thread-1 has acquired lock on String.class and Thread-2 acquires lock on Object.class then calls sleep() method and now it waits for Thread-1 to release lock on String.class.

Conclusion:

Now, Thread-1 is waiting for Thread-2 to release lock on Object.class and Thread-2 is waiting for Thread-1 to release lock on String.class and deadlock is formed.


Here comes the important part, how above formed deadlock could be solved :

Thread-1 acquires lock on String.class and then calls sleep() method which gives Thread-2 the chance to execute immediately after Thread-1 has acquired lock on String.class and Thread-2 tries to acquire lock on String.class but lock is holded by Thread-1. Meanwhile, Thread-1 completes successfully. As Thread-1 has completed successfully it releases lock on String.class, Thread-2 can now acquire lock on String.class and complete successfully without any deadlock formation.

Conclusion: No deadlock is formed.

Few important measures to avoid Deadlock

* Lock specific member variables of class rather than locking whole class: We must try to lock specific member variables of class rather than locking whole class.
* Use join() method: If possible try touse join() method, although it may refrain us from taking full advantage of multithreading environment because threads will start and end sequentially, but it can be handy in avoiding deadlocks.
* If possible try avoid using nested synchronization blocks.

### Question 21. Have you ever generated thread dumps or analyzed Thread Dumps? (Important)

Answer. Answering this questions will show your in depth knowledge of Threads. Every experienced must know how to generate Thread Dumps.
* VisualVM  is most popular way to generate Thread Dump and is most widely used by developers. It’s important to understand usage of VisualVM for in depth knowledge of VisualVM. I’ll recommend every developer must understand this topic to become master in multi threading.
* It helps us in analyzing threads performance, thread states, CPU consumed by threads, garbage collection and much more.  For detailed information see Generating and analyzing Thread Dumps using VisualVM - step by step detail to setup VisualVM with screenshots
* jstack is very easy way to generate Thread dump and is widely used by developers. I’ll recommend every developer must understand this topic to become master in multi threading. For creating Thread dumps we need not to download any jar or any extra software. 

### Question 22. What is life cycle of Thread, explain thread states? (Important)

Answer. Thread states/ Thread life cycle is very basic question, before going deep into concepts we must understand Thread life cycle.

###### Thread have following states:
* New
* Runnable
* Running
* Waiting/blocked/sleeping
* Terminated (Dead)

###### Thread states in detail:
* New : When instance of thread is created using new operator it is in new state, but the start() method has not been invoked on the thread yet, thread is not eligible to run yet.
* Runnable : When start() method is called on thread it enters runnable state.
* Running : Thread scheduler selects thread to go fromrunnable to running state. In running state Thread starts executing by entering run() method.
* Waiting/blocked/sleeping : In this state a thread is not eligible to run. Thread is still alive, but currently it’s not eligible to run. In other words.

###### How can Thread go from running to waiting state?
By calling wait()method thread go from running to waiting state. In waiting state it will wait for other threads to release object monitor/lock.

###### How can Thread go from running to sleeping state?
By calling sleep() methodthread go from running to sleeping state. In sleeping state it will wait for sleep time to get over.

Terminated (Dead) : A thread is considered dead when its run() method completes.

### Question 23. Are you aware of preemptive scheduling and time slicing?

Answer. In preemptive scheduling, the highest priority thread executes until it enters into the waiting or dead state.

In time slicing, a thread executes for a certain predefined time and then enters runnable pool. Than thread can enter running state when selected by thread scheduler.

### Question 24. What are daemon threads?

Answer.Daemon threads are low priority threads which runs intermittently in background for doing garbage collection.

##### 12 Few salient features of daemon() threads>

1. Thread scheduler schedules these threads only when CPU is idle.
2. Daemon threads are service oriented threads, they serves all other threads.
3. These threads are created before user threads are created and die after all other user threads dies.
4. Priority of daemon threads is always 1 (i.e. MIN_PRIORITY).
5. User created threads are non daemon threads.
6. JVM can exit when only daemon threads exist in system.
7. We can use isDaemon() method to check whether thread is daemon thread or not.
8. We can use setDaemon(boolean on) method to make any user method a daemon thread.
9. If setDaemon(boolean on) is called on thread after calling start() method than IllegalThreadStateException is thrown.

### Question 27. As stop() method is deprecated,  How can we terminate or stop infinitely running thread in java? (Important)

Answer. This is very interesting question where interviewees thread basics basic will be tested. Interviewers tend to know user’s knowledge about main thread’s and thread invoked by main thread.
* We will try to address the problem by creating new thread which will run infinitely until certain condition is satisfied and will be called by main Thread.
* Infinitely running thread can be stopped using boolean variable.
* Infinitely running thread can be stopped using interrupt() method.

Let’s understand Why stop() method is deprecated :
> Stopping a thread with Thread.stop() causes it to release all of the monitors that it has locked. If any of the objects previously protected by these monitors were in an inconsistent state, the damaged objects become visible to other threads, which might lead to unpredictable behavior.

### Question 30. Difference between wait() and sleep() ? (Important)

Answer. Should be called from synchronized block :wait() method is always called from synchronized block i.e. wait() method needs to lock object monitor before object on which it is called.  But sleep() method can be called from outside synchronized block i.e. sleep() method doesn’t need any object monitor.

* **IllegalMonitorStateException:** if wait() method is called without acquiring object lock than IllegalMonitorStateException is thrown at runtime, but sleep() methodnever throws such exception.

* **Belongs to which class:** wait() method belongs to java.lang.Object class but sleep() method belongs to java.lang.Thread class.

* **Called on object or thread:**  wait() method is called on objects but sleep() method is called on Threads not objects.

* **Thread state:** when wait() method is called on object, thread that holded object’s monitor goes from running to waiting state and can return to runnable state only when notify() or notifyAll()method is called on that object. And later thread scheduler schedules that thread to go from from runnable to running state.

* when sleep() is called on thread it goes from running to waiting state and can return to runnable state when sleep time is up.

* **When called from synchronized block:** when wait() method is called thread leaves the object lock.  But sleep()method when called from synchronized block or method thread doesn’t leaves object lock.

### Question 37. What will happen if we don’t override run method?

Answer.  This question will test your basic knowledge how start and run methods work internally in Thread Api.
* When we call start() method on thread, it internally calls run() method with newly created thread. So, if we don’t override run() method newly created thread won’t be called and nothing will happen.

### Question 38. What will happen if we override start method?

Answer. This question will again test your basic core java knowledge how overriding works at runtime, what what will be called at runtime and how start and run methods work internally in Thread Api.
* When we call start() method on thread, it internally calls run() method with newly created thread. So, if we override start() method, run() method will not be called until we write code for calling run() method.

### Question 51.  How can you implement your own Thread Pool in java?

Answer.

What is ThreadPool?
> ThreadPool is a pool of threads which reuses a fixed number of threads  to execute tasks.

* At any point, at most nThreads threads will be active processing tasks. If additional tasks are submitted when all threads are active, they will wait in the queue until a thread is available.
* ThreadPool implementation internally uses LinkedBlockingQueue for adding and removing tasks.

##### Need/Advantage of ThreadPool?
* Instead of creating new thread every time for executing tasks, we can create ThreadPool which reuses a fixed number of threads for executing tasks.
* As threads are reused, performance of our application improves drastically.

##### How ThreadPool works?
* We will instantiate ThreadPool, in ThreadPool’s constructor nThreads number of threads are created and started.

    ThreadPool threadPool=new ThreadPool(2);

Here 2 threads will be created and started in ThreadPool.

Then, threads will enter run() method of ThreadPoolsThread class and will call take() method on taskQueue.

If tasks are available thread will execute task by entering run() method of task (As tasks executed always implements Runnable).

##### When tasks are added?

When execute() method of ThreadPool is called, it internally calls put() method on taskQueue to add tasks.

    taskQueue.put(task);

Once tasks are available all waiting threads are notified that task is available.

### Question 56. What do you mean by thread starvation?

Answer.  When thread does not enough CPU for its execution Thread starvation happens.

##### Thread starvation may happen in following scenarios:
* Low priority threads gets less CPU (time for execution) as compared to high priority threads. Lower priority thread may starve away waiting to get enough CPU to perform calculations.
* In deadlock two threads waits for each other to release lock holded by them on resources. There both Threads starves away to get CPU.
* Thread might be waiting indefinitely for lock on object’s monitor (by calling wait() method), because no other thread is calling notify()/notifAll() method on object. In that case, Thread starves away to get CPU.
* Thread might be waiting indefinitely for lock on object’s monitor (by calling wait() method), but notify() may be repeatedly awakening some other threads. In that case also Thread starves away to get CPU.

### Question 57. Define each method (methods are invoked on a particular Thread object) :

1. public void start()

Starts the thread in a separate path of execution, then invokes the run() method on this Thread object.

2. public void run()

If this Thread object was instantiated using a separate Runnable target, the run() method is invoked on that Runnable object.

3. public final void setName(String name)

Changes the name of the Thread object. There is also a getName() method for retrieving the name.

4. public final void setPriority(int priority)

Sets the priority of this Thread object. The possible values are between 1 and 10.

5. public final void setDaemon(boolean on)

A parameter of true denotes this Thread as a daemon thread.

6. public final void join(long millisec)

The current thread invokes this method on a second thread, causing the current thread to block until the second thread terminates or the specified number of milliseconds passes.

7. public void interrupt()

Interrupts this thread, causing it to continue execution if it was blocked for any reason.

8. public final boolean isAlive()

Returns true if the thread is alive, which is any time after the thread has been started but before it runs to completion.

### Question 58. Define each method (methods in the Thread class are static. Invoking one of the static methods performs the operation on the currently running thread.) :

1. public static void yield()

Causes the currently running thread to yield to any other threads of the same priority that are waiting to be scheduled.

2. public static void sleep(long millisec)

Causes the currently running thread to block for at least the specified number of milliseconds.

3. public static boolean holdsLock(Object x)

Returns true if the current thread holds the lock on the given Object.

4. public static Thread currentThread()

Returns a reference to the currently running thread, which is the thread that invokes this method.

5. public static void dumpStack()

Prints the stack trace for the currently running thread, which is useful when debugging a multithreaded application.
