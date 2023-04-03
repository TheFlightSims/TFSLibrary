package org.hsqldb.lib;

import java.util.concurrent.CountDownLatch;

public class CountUpDownLatch {
  volatile CountDownLatch latch = new CountDownLatch(1);
  
  volatile int count;
  
  public void await() throws InterruptedException {
    if (this.count == 0)
      return; 
    this.latch.await();
  }
  
  public void countDown() {
    this.count--;
    if (this.count == 0)
      this.latch.countDown(); 
  }
  
  public long getCount() {
    return this.count;
  }
  
  public void countUp() {
    if (this.latch.getCount() == 0L)
      this.latch = new CountDownLatch(1); 
    this.count++;
  }
  
  public void setCount(int paramInt) {
    if (paramInt == 0) {
      if (this.latch.getCount() != 0L)
        this.latch.countDown(); 
    } else if (this.latch.getCount() == 0L) {
      this.latch = new CountDownLatch(1);
    } 
    this.count = paramInt;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\CountUpDownLatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */