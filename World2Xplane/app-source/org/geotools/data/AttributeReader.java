package org.geotools.data;

import java.io.IOException;
import org.opengis.feature.type.AttributeDescriptor;

public interface AttributeReader {
  int getAttributeCount();
  
  AttributeDescriptor getAttributeType(int paramInt) throws ArrayIndexOutOfBoundsException;
  
  void close() throws IOException;
  
  boolean hasNext() throws IOException;
  
  void next() throws IOException;
  
  Object read(int paramInt) throws IOException, ArrayIndexOutOfBoundsException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\AttributeReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */