package org.hsqldb.lib;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class ReadWriteLockDummy implements ReadWriteLock {
  public Lock readLock() {
    return new LockDummy();
  }
  
  public Lock writeLock() {
    return new LockDummy();
  }
  
  public static class LockDummy implements Lock {
    public void lock() {}
    
    public void lockInterruptibly() throws InterruptedException {}
    
    public boolean tryLock() {
      return false;
    }
    
    public boolean tryLock(long param1Long, TimeUnit param1TimeUnit) throws InterruptedException {
      return false;
    }
    
    public void unlock() {}
    
    public Condition newCondition() {
      return null;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\ReadWriteLockDummy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */