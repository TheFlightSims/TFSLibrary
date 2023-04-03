package com.sun.media.jai.rmi;

import java.awt.Rectangle;
import java.awt.image.RenderedImage;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;
import javax.media.jai.RenderableOp;
import javax.media.jai.RenderedOp;

public interface RMIImage extends Remote {
  public static final String RMI_IMAGE_SERVER_NAME = "RemoteImageServer";
  
  Long getRemoteID() throws RemoteException;
  
  void setSource(Long paramLong, RenderedImage paramRenderedImage) throws RemoteException;
  
  void setSource(Long paramLong, RenderedOp paramRenderedOp) throws RemoteException;
  
  void setSource(Long paramLong, RenderableOp paramRenderableOp, RenderContextProxy paramRenderContextProxy) throws RemoteException;
  
  void dispose(Long paramLong) throws RemoteException;
  
  Vector getSources(Long paramLong) throws RemoteException;
  
  Object getProperty(Long paramLong, String paramString) throws RemoteException;
  
  String[] getPropertyNames(Long paramLong) throws RemoteException;
  
  ColorModelProxy getColorModel(Long paramLong) throws RemoteException;
  
  SampleModelProxy getSampleModel(Long paramLong) throws RemoteException;
  
  int getWidth(Long paramLong) throws RemoteException;
  
  int getHeight(Long paramLong) throws RemoteException;
  
  int getMinX(Long paramLong) throws RemoteException;
  
  int getMinY(Long paramLong) throws RemoteException;
  
  int getNumXTiles(Long paramLong) throws RemoteException;
  
  int getNumYTiles(Long paramLong) throws RemoteException;
  
  int getMinTileX(Long paramLong) throws RemoteException;
  
  int getMinTileY(Long paramLong) throws RemoteException;
  
  int getTileWidth(Long paramLong) throws RemoteException;
  
  int getTileHeight(Long paramLong) throws RemoteException;
  
  int getTileGridXOffset(Long paramLong) throws RemoteException;
  
  int getTileGridYOffset(Long paramLong) throws RemoteException;
  
  RasterProxy getTile(Long paramLong, int paramInt1, int paramInt2) throws RemoteException;
  
  RasterProxy getData(Long paramLong) throws RemoteException;
  
  RasterProxy getData(Long paramLong, Rectangle paramRectangle) throws RemoteException;
  
  RasterProxy copyData(Long paramLong, Rectangle paramRectangle) throws RemoteException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\RMIImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */