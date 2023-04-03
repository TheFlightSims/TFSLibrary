package org.slf4j.spi;

import org.slf4j.IMarkerFactory;

public interface MarkerFactoryBinder {
  IMarkerFactory getMarkerFactory();
  
  String getMarkerFactoryClassStr();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\slf4j\spi\MarkerFactoryBinder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */