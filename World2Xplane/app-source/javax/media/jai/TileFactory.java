package javax.media.jai;

import java.awt.Point;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;

public interface TileFactory {
  boolean canReclaimMemory();
  
  boolean isMemoryCache();
  
  long getMemoryUsed();
  
  void flush();
  
  WritableRaster createTile(SampleModel paramSampleModel, Point paramPoint);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\TileFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */