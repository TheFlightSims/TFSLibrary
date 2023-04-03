package org.geotools.data;

import java.io.Closeable;
import java.io.IOException;
import java.util.NoSuchElementException;

public interface FeatureReader<T extends org.opengis.feature.type.FeatureType, F extends org.opengis.feature.Feature> extends Closeable {
  T getFeatureType();
  
  F next() throws IOException, IllegalArgumentException, NoSuchElementException;
  
  boolean hasNext() throws IOException;
  
  void close() throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\FeatureReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */