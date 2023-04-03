package gnu.trove.queue;

import gnu.trove.TShortCollection;

public interface TShortQueue extends TShortCollection {
  short element();
  
  boolean offer(short paramShort);
  
  short peek();
  
  short poll();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\queue\TShortQueue.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */