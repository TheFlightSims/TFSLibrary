package javax.media.jai;

import java.awt.Point;
import java.awt.image.Raster;

public interface TileScheduler {
  Raster scheduleTile(OpImage paramOpImage, int paramInt1, int paramInt2);
  
  Raster[] scheduleTiles(OpImage paramOpImage, Point[] paramArrayOfPoint);
  
  TileRequest scheduleTiles(PlanarImage paramPlanarImage, Point[] paramArrayOfPoint, TileComputationListener[] paramArrayOfTileComputationListener);
  
  void cancelTiles(TileRequest paramTileRequest, Point[] paramArrayOfPoint);
  
  void prefetchTiles(PlanarImage paramPlanarImage, Point[] paramArrayOfPoint);
  
  void setParallelism(int paramInt);
  
  int getParallelism();
  
  void setPrefetchParallelism(int paramInt);
  
  int getPrefetchParallelism();
  
  void setPriority(int paramInt);
  
  int getPriority();
  
  void setPrefetchPriority(int paramInt);
  
  int getPrefetchPriority();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\TileScheduler.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */