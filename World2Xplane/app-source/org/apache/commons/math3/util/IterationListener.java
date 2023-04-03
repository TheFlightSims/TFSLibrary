package org.apache.commons.math3.util;

import java.util.EventListener;

public interface IterationListener extends EventListener {
  void initializationPerformed(IterationEvent paramIterationEvent);
  
  void iterationPerformed(IterationEvent paramIterationEvent);
  
  void iterationStarted(IterationEvent paramIterationEvent);
  
  void terminationPerformed(IterationEvent paramIterationEvent);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\IterationListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */