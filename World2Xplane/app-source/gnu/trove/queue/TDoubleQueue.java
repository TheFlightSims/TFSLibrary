package gnu.trove.queue;

import gnu.trove.TDoubleCollection;

public interface TDoubleQueue extends TDoubleCollection {
  double element();
  
  boolean offer(double paramDouble);
  
  double peek();
  
  double poll();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\queue\TDoubleQueue.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */