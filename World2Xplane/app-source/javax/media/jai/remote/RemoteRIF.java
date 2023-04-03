package javax.media.jai.remote;

import java.awt.RenderingHints;
import java.awt.image.renderable.ParameterBlock;
import javax.media.jai.OperationNode;
import javax.media.jai.PropertyChangeEventJAI;

public interface RemoteRIF {
  RemoteRenderedImage create(String paramString1, String paramString2, ParameterBlock paramParameterBlock, RenderingHints paramRenderingHints) throws RemoteImagingException;
  
  RemoteRenderedImage create(PlanarImageServerProxy paramPlanarImageServerProxy, OperationNode paramOperationNode, PropertyChangeEventJAI paramPropertyChangeEventJAI) throws RemoteImagingException;
  
  NegotiableCapabilitySet getClientCapabilities();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\RemoteRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */