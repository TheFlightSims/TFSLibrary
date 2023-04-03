package com.sun.media.jai.rmi;

import java.awt.Rectangle;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import javax.media.jai.RenderableOp;
import javax.media.jai.RenderedOp;
import javax.media.jai.remote.NegotiableCapabilitySet;
import javax.media.jai.remote.SerializableState;

public interface ImageServer extends Remote {
  Long getRemoteID() throws RemoteException;
  
  void dispose(Long paramLong) throws RemoteException;
  
  void incrementRefCount(Long paramLong) throws RemoteException;
  
  Object getProperty(Long paramLong, String paramString) throws RemoteException;
  
  String[] getPropertyNames(Long paramLong) throws RemoteException;
  
  String[] getPropertyNames(String paramString) throws RemoteException;
  
  SerializableState getColorModel(Long paramLong) throws RemoteException;
  
  SerializableState getSampleModel(Long paramLong) throws RemoteException;
  
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
  
  SerializableState getTile(Long paramLong, int paramInt1, int paramInt2) throws RemoteException;
  
  byte[] getCompressedTile(Long paramLong, int paramInt1, int paramInt2) throws RemoteException;
  
  SerializableState getData(Long paramLong) throws RemoteException;
  
  SerializableState getData(Long paramLong, Rectangle paramRectangle) throws RemoteException;
  
  SerializableState copyData(Long paramLong, Rectangle paramRectangle) throws RemoteException;
  
  void createRenderedOp(Long paramLong, String paramString, ParameterBlock paramParameterBlock, SerializableState paramSerializableState) throws RemoteException;
  
  boolean getRendering(Long paramLong) throws RemoteException;
  
  RenderedOp getNode(Long paramLong) throws RemoteException;
  
  void setRenderedSource(Long paramLong, RenderedImage paramRenderedImage, int paramInt) throws RemoteException;
  
  void setRenderedSource(Long paramLong, RenderedOp paramRenderedOp, int paramInt) throws RemoteException;
  
  void setRenderedSource(Long paramLong1, Long paramLong2, int paramInt) throws RemoteException;
  
  void setRenderedSource(Long paramLong1, Long paramLong2, String paramString1, String paramString2, int paramInt) throws RemoteException;
  
  float getRenderableMinX(Long paramLong) throws RemoteException;
  
  float getRenderableMinY(Long paramLong) throws RemoteException;
  
  float getRenderableWidth(Long paramLong) throws RemoteException;
  
  float getRenderableHeight(Long paramLong) throws RemoteException;
  
  RenderedImage createScaledRendering(Long paramLong, int paramInt1, int paramInt2, SerializableState paramSerializableState) throws RemoteException;
  
  RenderedImage createDefaultRendering(Long paramLong) throws RemoteException;
  
  RenderedImage createRendering(Long paramLong, SerializableState paramSerializableState) throws RemoteException;
  
  void createRenderableOp(Long paramLong, String paramString, ParameterBlock paramParameterBlock) throws RemoteException;
  
  Long getRendering(Long paramLong, SerializableState paramSerializableState) throws RemoteException;
  
  void setRenderableSource(Long paramLong1, Long paramLong2, int paramInt) throws RemoteException;
  
  void setRenderableSource(Long paramLong1, Long paramLong2, String paramString1, String paramString2, int paramInt) throws RemoteException;
  
  void setRenderableRMIServerProxyAsSource(Long paramLong1, Long paramLong2, String paramString1, String paramString2, int paramInt) throws RemoteException;
  
  void setRenderableSource(Long paramLong, RenderableOp paramRenderableOp, int paramInt) throws RemoteException;
  
  void setRenderableSource(Long paramLong, SerializableRenderableImage paramSerializableRenderableImage, int paramInt) throws RemoteException;
  
  void setRenderableSource(Long paramLong, RenderedImage paramRenderedImage, int paramInt) throws RemoteException;
  
  SerializableState mapRenderContext(int paramInt, Long paramLong, String paramString, SerializableState paramSerializableState) throws RemoteException;
  
  SerializableState getBounds2D(Long paramLong, String paramString) throws RemoteException;
  
  boolean isDynamic(String paramString) throws RemoteException;
  
  boolean isDynamic(Long paramLong) throws RemoteException;
  
  String[] getServerSupportedOperationNames() throws RemoteException;
  
  List getOperationDescriptors() throws RemoteException;
  
  SerializableState getInvalidRegion(Long paramLong, ParameterBlock paramParameterBlock1, SerializableState paramSerializableState1, ParameterBlock paramParameterBlock2, SerializableState paramSerializableState2) throws RemoteException;
  
  Rectangle mapSourceRect(Long paramLong, Rectangle paramRectangle, int paramInt) throws RemoteException;
  
  Rectangle mapDestRect(Long paramLong, Rectangle paramRectangle, int paramInt) throws RemoteException;
  
  Long handleEvent(Long paramLong, String paramString, Object paramObject1, Object paramObject2) throws RemoteException;
  
  Long handleEvent(Long paramLong, int paramInt, SerializableState paramSerializableState, Object paramObject) throws RemoteException;
  
  NegotiableCapabilitySet getServerCapabilities() throws RemoteException;
  
  void setServerNegotiatedValues(Long paramLong, NegotiableCapabilitySet paramNegotiableCapabilitySet) throws RemoteException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\ImageServer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */