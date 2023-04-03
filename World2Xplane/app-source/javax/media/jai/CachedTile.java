package javax.media.jai;

import java.awt.image.Raster;
import java.awt.image.RenderedImage;

public interface CachedTile {
  RenderedImage getOwner();
  
  Raster getTile();
  
  Object getTileCacheMetric();
  
  long getTileTimeStamp();
  
  long getTileSize();
  
  int getAction();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\CachedTile.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */