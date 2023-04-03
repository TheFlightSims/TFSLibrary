package org.geotools.data.shapefile.index;

public interface Lock {
  public static final short SHARED = 1;
  
  public static final short EXCLUSIVE = 2;
  
  short getType();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\index\Lock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */