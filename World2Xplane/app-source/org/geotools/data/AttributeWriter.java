package org.geotools.data;

import java.io.IOException;
import org.opengis.feature.type.AttributeDescriptor;

public interface AttributeWriter {
  int getAttributeCount();
  
  AttributeDescriptor getAttributeType(int paramInt) throws ArrayIndexOutOfBoundsException;
  
  void next() throws IOException;
  
  void write(int paramInt, Object paramObject) throws IOException;
  
  void close() throws IOException;
  
  boolean hasNext() throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\AttributeWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */