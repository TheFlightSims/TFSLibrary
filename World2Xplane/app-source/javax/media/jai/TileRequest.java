package javax.media.jai;

import java.awt.Point;

public interface TileRequest {
  public static final int TILE_STATUS_PENDING = 0;
  
  public static final int TILE_STATUS_PROCESSING = 1;
  
  public static final int TILE_STATUS_COMPUTED = 2;
  
  public static final int TILE_STATUS_CANCELLED = 3;
  
  public static final int TILE_STATUS_FAILED = 4;
  
  PlanarImage getImage();
  
  Point[] getTileIndices();
  
  TileComputationListener[] getTileListeners();
  
  boolean isStatusAvailable();
  
  int getTileStatus(int paramInt1, int paramInt2);
  
  void cancelTiles(Point[] paramArrayOfPoint);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\TileRequest.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */