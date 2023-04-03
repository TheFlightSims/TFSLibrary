package org.geotools.data;

import java.io.IOException;

public interface FIDReader {
  void close() throws IOException;
  
  boolean hasNext() throws IOException;
  
  String next() throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\FIDReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */