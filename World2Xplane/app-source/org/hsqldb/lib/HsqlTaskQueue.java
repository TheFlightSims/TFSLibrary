package org.hsqldb.lib;

public class HsqlTaskQueue {
  protected Thread taskRunnerThread;
  
  protected static final Runnable SHUTDOWNTASK = new Runnable() {
      public void run() {}
    };
  
  protected volatile boolean isShutdown;
  
  protected final HsqlDeque queue = new HsqlDeque();
  
  protected final TaskRunner taskRunner = new TaskRunner();
  
  public synchronized Thread getTaskRunnerThread() {
    return this.taskRunnerThread;
  }
  
  protected synchronized void clearThread() {
    try {
      this.taskRunnerThread.setContextClassLoader(null);
    } catch (Throwable throwable) {}
    this.taskRunnerThread = null;
  }
  
  public boolean isShutdown() {
    return this.isShutdown;
  }
  
  public synchronized void restart() {
    if (this.taskRunnerThread == null && !this.isShutdown) {
      this.taskRunnerThread = new Thread(this.taskRunner);
      this.taskRunnerThread.start();
    } 
  }
  
  public void execute(Runnable paramRunnable) throws RuntimeException {
    if (!this.isShutdown) {
      synchronized (this.queue) {
        this.queue.addLast(paramRunnable);
      } 
      restart();
    } 
  }
  
  public synchronized void shutdownAfterQueued() {
    if (!this.isShutdown)
      synchronized (this.queue) {
        this.queue.addLast(SHUTDOWNTASK);
      }  
  }
  
  public synchronized void shutdownAfterCurrent() {
    this.isShutdown = true;
    synchronized (this.queue) {
      this.queue.clear();
      this.queue.addLast(SHUTDOWNTASK);
    } 
  }
  
  public synchronized void shutdownImmediately() {
    this.isShutdown = true;
    if (this.taskRunnerThread != null)
      this.taskRunnerThread.interrupt(); 
    synchronized (this.queue) {
      this.queue.clear();
      this.queue.addLast(SHUTDOWNTASK);
    } 
  }
  
  protected class TaskRunner implements Runnable {
    public void run() {
      try {
        while (!HsqlTaskQueue.this.isShutdown) {
          Runnable runnable;
          synchronized (HsqlTaskQueue.this.queue) {
            runnable = (Runnable)HsqlTaskQueue.this.queue.getFirst();
          } 
          if (runnable == HsqlTaskQueue.SHUTDOWNTASK) {
            HsqlTaskQueue.this.isShutdown = true;
            synchronized (HsqlTaskQueue.this.queue) {
              HsqlTaskQueue.this.queue.clear();
            } 
            break;
          } 
          if (runnable != null) {
            runnable.run();
            runnable = null;
          } 
        } 
      } finally {
        HsqlTaskQueue.this.clearThread();
      } 
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\HsqlTaskQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */