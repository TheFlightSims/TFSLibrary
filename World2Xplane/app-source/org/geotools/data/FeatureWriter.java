package org.geotools.data;

import java.io.Closeable;
import java.io.IOException;

public interface FeatureWriter<T extends org.opengis.feature.type.FeatureType, F extends org.opengis.feature.Feature> extends Closeable {
  T getFeatureType();
  
  F next() throws IOException;
  
  void remove() throws IOException;
  
  void write() throws IOException;
  
  boolean hasNext() throws IOException;
  
  void close() throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\FeatureWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */