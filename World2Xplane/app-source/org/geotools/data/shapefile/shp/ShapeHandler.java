package org.geotools.data.shapefile.shp;

import java.nio.ByteBuffer;

public interface ShapeHandler {
  ShapeType getShapeType();
  
  Object read(ByteBuffer paramByteBuffer, ShapeType paramShapeType, boolean paramBoolean);
  
  void write(ByteBuffer paramByteBuffer, Object paramObject);
  
  int getLength(Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\shp\ShapeHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */