package javax.media.jai.remote;

import java.awt.RenderingHints;
import java.awt.image.renderable.ParameterBlock;
import java.net.URL;
import javax.media.jai.OperationDescriptor;
import javax.media.jai.OperationNode;
import javax.media.jai.RegistryElementDescriptor;

public interface RemoteDescriptor extends RegistryElementDescriptor {
  OperationDescriptor[] getServerSupportedOperationList(String paramString) throws RemoteImagingException;
  
  NegotiableCapabilitySet getServerCapabilities(String paramString) throws RemoteImagingException;
  
  URL getServerNameDocs();
  
  Object getInvalidRegion(String paramString1, String paramString2, ParameterBlock paramParameterBlock1, RenderingHints paramRenderingHints1, String paramString3, ParameterBlock paramParameterBlock2, RenderingHints paramRenderingHints2, OperationNode paramOperationNode) throws RemoteImagingException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\RemoteDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */