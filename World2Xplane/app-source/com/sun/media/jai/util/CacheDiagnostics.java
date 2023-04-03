package com.sun.media.jai.util;

public interface CacheDiagnostics {
  void enableDiagnostics();
  
  void disableDiagnostics();
  
  long getCacheTileCount();
  
  long getCacheMemoryUsed();
  
  long getCacheHitCount();
  
  long getCacheMissCount();
  
  void resetCounts();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\CacheDiagnostics.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */