package javax.media.jai;

import java.awt.RenderingHints;
import java.awt.image.renderable.ParameterBlock;

public interface OperationNode extends PropertySource, PropertyChangeEmitter {
  String getRegistryModeName();
  
  String getOperationName();
  
  void setOperationName(String paramString);
  
  OperationRegistry getRegistry();
  
  void setRegistry(OperationRegistry paramOperationRegistry);
  
  ParameterBlock getParameterBlock();
  
  void setParameterBlock(ParameterBlock paramParameterBlock);
  
  RenderingHints getRenderingHints();
  
  void setRenderingHints(RenderingHints paramRenderingHints);
  
  Object getDynamicProperty(String paramString);
  
  void addPropertyGenerator(PropertyGenerator paramPropertyGenerator);
  
  void copyPropertyFromSource(String paramString, int paramInt);
  
  void suppressProperty(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\OperationNode.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */