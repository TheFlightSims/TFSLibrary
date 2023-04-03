package javax.media.jai;

import java.awt.Point;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.util.Comparator;

public interface TileCache {
  void add(RenderedImage paramRenderedImage, int paramInt1, int paramInt2, Raster paramRaster);
  
  void add(RenderedImage paramRenderedImage, int paramInt1, int paramInt2, Raster paramRaster, Object paramObject);
  
  void remove(RenderedImage paramRenderedImage, int paramInt1, int paramInt2);
  
  Raster getTile(RenderedImage paramRenderedImage, int paramInt1, int paramInt2);
  
  Raster[] getTiles(RenderedImage paramRenderedImage);
  
  void removeTiles(RenderedImage paramRenderedImage);
  
  void addTiles(RenderedImage paramRenderedImage, Point[] paramArrayOfPoint, Raster[] paramArrayOfRaster, Object paramObject);
  
  Raster[] getTiles(RenderedImage paramRenderedImage, Point[] paramArrayOfPoint);
  
  void flush();
  
  void memoryControl();
  
  void setTileCapacity(int paramInt);
  
  int getTileCapacity();
  
  void setMemoryCapacity(long paramLong);
  
  long getMemoryCapacity();
  
  void setMemoryThreshold(float paramFloat);
  
  float getMemoryThreshold();
  
  void setTileComparator(Comparator paramComparator);
  
  Comparator getTileComparator();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\TileCache.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */