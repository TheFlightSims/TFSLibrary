package javax.media.jai;

import java.awt.image.Raster;
import java.util.EventListener;

public interface TileComputationListener extends EventListener {
  void tileComputed(Object paramObject, TileRequest[] paramArrayOfTileRequest, PlanarImage paramPlanarImage, int paramInt1, int paramInt2, Raster paramRaster);
  
  void tileCancelled(Object paramObject, TileRequest[] paramArrayOfTileRequest, PlanarImage paramPlanarImage, int paramInt1, int paramInt2);
  
  void tileComputationFailure(Object paramObject, TileRequest[] paramArrayOfTileRequest, PlanarImage paramPlanarImage, int paramInt1, int paramInt2, Throwable paramThrowable);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\TileComputationListener.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */