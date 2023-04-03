package com.sun.media.jai.util;

import javax.media.jai.PlanarImage;

interface Job {
  void compute();
  
  boolean notDone();
  
  PlanarImage getOwner();
  
  boolean isBlocking();
  
  Exception getException();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\Job.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */