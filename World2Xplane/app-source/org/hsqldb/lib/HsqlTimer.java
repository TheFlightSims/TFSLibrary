package org.hsqldb.lib;

import java.util.Comparator;
import java.util.Date;

public final class HsqlTimer implements Comparator, ThreadFactory {
  protected final TaskQueue taskQueue = new TaskQueue(16, this);
  
  protected final TaskRunner taskRunner = new TaskRunner();
  
  protected Thread taskRunnerThread;
  
  protected final ThreadFactory threadFactory;
  
  protected volatile boolean isShutdown;
  
  static int nowCount = 0;
  
  public HsqlTimer() {
    this(null);
  }
  
  public HsqlTimer(ThreadFactory paramThreadFactory) {
    this.threadFactory = (paramThreadFactory == null) ? this : paramThreadFactory;
  }
  
  public int compare(Object paramObject1, Object paramObject2) {
    long l1 = ((Task)paramObject1).getNextScheduled();
    long l2 = ((Task)paramObject2).getNextScheduled();
    return (l1 < l2) ? -1 : ((l1 == l2) ? 0 : 1);
  }
  
  public Thread newThread(Runnable paramRunnable) {
    Thread thread = new Thread(paramRunnable);
    thread.setName("HSQLDB Timer @" + Integer.toHexString(hashCode()));
    thread.setDaemon(true);
    return thread;
  }
  
  public synchronized Thread getThread() {
    return this.taskRunnerThread;
  }
  
  public synchronized void restart() throws IllegalStateException {
    if (this.isShutdown)
      throw new IllegalStateException("isShutdown==true"); 
    if (this.taskRunnerThread == null) {
      this.taskRunnerThread = this.threadFactory.newThread(this.taskRunner);
      this.taskRunnerThread.start();
    } else {
      this.taskQueue.unpark();
    } 
  }
  
  public Object scheduleAfter(long paramLong, Runnable paramRunnable) throws IllegalArgumentException {
    if (paramRunnable == null)
      throw new IllegalArgumentException("runnable == null"); 
    return addTask(now() + paramLong, paramRunnable, 0L, false);
  }
  
  public Object scheduleAt(Date paramDate, Runnable paramRunnable) throws IllegalArgumentException {
    if (paramDate == null)
      throw new IllegalArgumentException("date == null"); 
    if (paramRunnable == null)
      throw new IllegalArgumentException("runnable == null"); 
    return addTask(paramDate.getTime(), paramRunnable, 0L, false);
  }
  
  public Object schedulePeriodicallyAt(Date paramDate, long paramLong, Runnable paramRunnable, boolean paramBoolean) throws IllegalArgumentException {
    if (paramDate == null)
      throw new IllegalArgumentException("date == null"); 
    if (paramLong <= 0L)
      throw new IllegalArgumentException("period <= 0"); 
    if (paramRunnable == null)
      throw new IllegalArgumentException("runnable == null"); 
    return addTask(paramDate.getTime(), paramRunnable, paramLong, paramBoolean);
  }
  
  public Object schedulePeriodicallyAfter(long paramLong1, long paramLong2, Runnable paramRunnable, boolean paramBoolean) throws IllegalArgumentException {
    if (paramLong2 <= 0L)
      throw new IllegalArgumentException("period <= 0"); 
    if (paramRunnable == null)
      throw new IllegalArgumentException("runnable == null"); 
    return addTask(now() + paramLong1, paramRunnable, paramLong2, paramBoolean);
  }
  
  public synchronized void shutdown() {
    if (!this.isShutdown) {
      this.isShutdown = true;
      this.taskQueue.cancelAllTasks();
    } 
  }
  
  public synchronized void shutDown() {
    shutdown();
  }
  
  public synchronized void shutdownImmediately() {
    if (!this.isShutdown) {
      Thread thread = this.taskRunnerThread;
      this.isShutdown = true;
      if (thread != null && thread.isAlive())
        thread.interrupt(); 
      this.taskQueue.cancelAllTasks();
    } 
  }
  
  public static void cancel(Object paramObject) {
    if (paramObject instanceof Task)
      ((Task)paramObject).cancel(); 
  }
  
  public static boolean isCancelled(Object paramObject) {
    return (paramObject instanceof Task) ? ((Task)paramObject).isCancelled() : true;
  }
  
  public static boolean isFixedRate(Object paramObject) {
    if (paramObject instanceof Task) {
      Task task = (Task)paramObject;
      return (task.relative && task.period > 0L);
    } 
    return false;
  }
  
  public static boolean isFixedDelay(Object paramObject) {
    if (paramObject instanceof Task) {
      Task task = (Task)paramObject;
      return (!task.relative && task.period > 0L);
    } 
    return false;
  }
  
  public static boolean isPeriodic(Object paramObject) {
    return (paramObject instanceof Task) ? ((((Task)paramObject).period > 0L)) : false;
  }
  
  public static Date getLastScheduled(Object paramObject) {
    if (paramObject instanceof Task) {
      Task task = (Task)paramObject;
      long l = task.getLastScheduled();
      return (l == 0L) ? null : new Date(l);
    } 
    return null;
  }
  
  public static Object setPeriod(Object paramObject, long paramLong) {
    return (paramObject instanceof Task) ? ((Task)paramObject).setPeriod(paramLong) : paramObject;
  }
  
  public static Date getNextScheduled(Object paramObject) {
    if (paramObject instanceof Task) {
      Task task = (Task)paramObject;
      long l = task.isCancelled() ? 0L : task.getNextScheduled();
      return (l == 0L) ? null : new Date(l);
    } 
    return null;
  }
  
  protected Task addTask(long paramLong1, Runnable paramRunnable, long paramLong2, boolean paramBoolean) {
    if (this.isShutdown)
      throw new IllegalStateException("shutdown"); 
    Task task = new Task(paramLong1, paramRunnable, paramLong2, paramBoolean);
    this.taskQueue.addTask(task);
    restart();
    return task;
  }
  
  protected synchronized void clearThread() {
    try {
      this.taskRunnerThread.setContextClassLoader(null);
    } catch (Throwable throwable) {}
    this.taskRunnerThread = null;
  }
  
  protected Task nextTask() {
    try {
      while (!this.isShutdown || Thread.interrupted()) {
        long l1;
        long l2;
        Task task;
        synchronized (this.taskQueue) {
          task = this.taskQueue.peekTask();
          if (task == null)
            break; 
          l1 = System.currentTimeMillis();
          l2 = task.next;
          long l = l2 - l1;
          if (l > 0L) {
            this.taskQueue.park(l);
            continue;
          } 
          this.taskQueue.removeTask();
        } 
        long l3 = task.period;
        if (l3 > 0L) {
          if (task.relative) {
            long l = l1 - l2;
            if (l > l3) {
              l3 = 0L;
            } else if (l > 0L) {
              l3 -= l;
            } 
          } 
          task.updateSchedule(l1, l1 + l3);
          this.taskQueue.addTask(task);
        } 
        return task;
      } 
    } catch (InterruptedException interruptedException) {}
    return null;
  }
  
  static long now() {
    nowCount++;
    return System.currentTimeMillis();
  }
  
  protected static class TaskQueue extends HsqlArrayHeap {
    TaskQueue(int param1Int, Comparator param1Comparator) {
      super(param1Int, param1Comparator);
    }
    
    void addTask(HsqlTimer.Task param1Task) {
      add(param1Task);
    }
    
    void cancelAllTasks() {
      Object[] arrayOfObject;
      int i;
      synchronized (this) {
        arrayOfObject = this.heap;
        i = this.count;
        this.heap = new Object[1];
        this.count = 0;
      } 
      for (byte b = 0; b < i; b++)
        ((HsqlTimer.Task)arrayOfObject[b]).cancelled = true; 
    }
    
    synchronized void park(long param1Long) throws InterruptedException {
      wait(param1Long);
    }
    
    synchronized HsqlTimer.Task peekTask() {
      while (this.heap[0] != null && ((HsqlTimer.Task)this.heap[0]).isCancelled())
        remove(); 
      return (HsqlTimer.Task)this.heap[0];
    }
    
    synchronized void signalTaskCancelled(HsqlTimer.Task param1Task) {
      if (param1Task == this.heap[0]) {
        remove();
        notify();
      } 
    }
    
    HsqlTimer.Task removeTask() {
      return (HsqlTimer.Task)remove();
    }
    
    synchronized void unpark() {
      notify();
    }
  }
  
  protected class Task {
    Runnable runnable;
    
    long period;
    
    long last;
    
    long next;
    
    boolean cancelled = false;
    
    private Object cancel_mutex = new Object();
    
    final boolean relative;
    
    Task(long param1Long1, Runnable param1Runnable, long param1Long2, boolean param1Boolean) {
      this.next = param1Long1;
      this.runnable = param1Runnable;
      this.period = param1Long2;
      this.relative = param1Boolean;
    }
    
    void cancel() {
      boolean bool = false;
      synchronized (this.cancel_mutex) {
        if (!this.cancelled)
          this.cancelled = bool = true; 
      } 
      if (bool)
        HsqlTimer.this.taskQueue.signalTaskCancelled(this); 
    }
    
    boolean isCancelled() {
      synchronized (this.cancel_mutex) {
        return this.cancelled;
      } 
    }
    
    synchronized long getLastScheduled() {
      return this.last;
    }
    
    synchronized long getNextScheduled() {
      return this.next;
    }
    
    synchronized void updateSchedule(long param1Long1, long param1Long2) {
      this.last = param1Long1;
      this.next = param1Long2;
    }
    
    synchronized Object setPeriod(long param1Long) {
      if (this.period == param1Long || isCancelled())
        return this; 
      if (param1Long > this.period) {
        this.period = param1Long;
        return this;
      } 
      cancel();
      return HsqlTimer.this.addTask(HsqlTimer.now(), this.runnable, param1Long, this.relative);
    }
  }
  
  protected class TaskRunner implements Runnable {
    public void run() {
      try {
        while (true) {
          HsqlTimer.Task task = HsqlTimer.this.nextTask();
          if (task == null)
            return; 
          task.runnable.run();
        } 
      } finally {
        HsqlTimer.this.clearThread();
      } 
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\HsqlTimer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */