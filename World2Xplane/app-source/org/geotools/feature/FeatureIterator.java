package org.geotools.feature;

import java.io.Closeable;
import java.util.NoSuchElementException;

public interface FeatureIterator<F extends org.opengis.feature.Feature> extends Closeable {
  boolean hasNext();
  
  F next() throws NoSuchElementException;
  
  void close();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\FeatureIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */