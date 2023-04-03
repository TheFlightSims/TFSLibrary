package javax.media.jai;

import java.io.Serializable;

public interface PropertyGenerator extends Serializable {
  String[] getPropertyNames();
  
  Class getClass(String paramString);
  
  boolean canGenerateProperties(Object paramObject);
  
  Object getProperty(String paramString, Object paramObject);
  
  Object getProperty(String paramString, RenderedOp paramRenderedOp);
  
  Object getProperty(String paramString, RenderableOp paramRenderableOp);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\PropertyGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */