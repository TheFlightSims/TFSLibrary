package gnu.trove.queue;

import gnu.trove.TCharCollection;

public interface TCharQueue extends TCharCollection {
  char element();
  
  boolean offer(char paramChar);
  
  char peek();
  
  char poll();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\queue\TCharQueue.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */