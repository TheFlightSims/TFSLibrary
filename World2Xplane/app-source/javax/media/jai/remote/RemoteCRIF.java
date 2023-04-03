package javax.media.jai.remote;

import java.awt.geom.Rectangle2D;
import java.awt.image.renderable.ParameterBlock;
import java.awt.image.renderable.RenderContext;
import java.awt.image.renderable.RenderableImage;

public interface RemoteCRIF extends RemoteRIF {
  RenderContext mapRenderContext(String paramString1, String paramString2, int paramInt, RenderContext paramRenderContext, ParameterBlock paramParameterBlock, RenderableImage paramRenderableImage) throws RemoteImagingException;
  
  RemoteRenderedImage create(String paramString1, String paramString2, RenderContext paramRenderContext, ParameterBlock paramParameterBlock) throws RemoteImagingException;
  
  Rectangle2D getBounds2D(String paramString1, String paramString2, ParameterBlock paramParameterBlock) throws RemoteImagingException;
  
  Object getProperty(String paramString1, String paramString2, ParameterBlock paramParameterBlock, String paramString3) throws RemoteImagingException;
  
  String[] getPropertyNames(String paramString1, String paramString2) throws RemoteImagingException;
  
  boolean isDynamic(String paramString1, String paramString2) throws RemoteImagingException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\RemoteCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */