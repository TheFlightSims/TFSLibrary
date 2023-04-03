package scala.concurrent.forkjoin;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public interface TransferQueue<E> extends BlockingQueue<E> {
  boolean tryTransfer(E paramE);
  
  void transfer(E paramE) throws InterruptedException;
  
  boolean tryTransfer(E paramE, long paramLong, TimeUnit paramTimeUnit) throws InterruptedException;
  
  boolean hasWaitingConsumer();
  
  int getWaitingConsumerCount();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\forkjoin\TransferQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */