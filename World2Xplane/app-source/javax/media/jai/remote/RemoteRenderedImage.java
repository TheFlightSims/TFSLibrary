package javax.media.jai.remote;

import java.awt.image.RenderedImage;

public interface RemoteRenderedImage extends RenderedImage {
  String getServerName();
  
  String getProtocolName();
  
  int getRetryInterval();
  
  void setRetryInterval(int paramInt);
  
  int getNumRetries();
  
  void setNumRetries(int paramInt);
  
  NegotiableCapabilitySet getNegotiationPreferences();
  
  void setNegotiationPreferences(NegotiableCapabilitySet paramNegotiableCapabilitySet);
  
  NegotiableCapabilitySet getNegotiatedValues() throws RemoteImagingException;
  
  NegotiableCapability getNegotiatedValue(String paramString) throws RemoteImagingException;
  
  void setServerNegotiatedValues(NegotiableCapabilitySet paramNegotiableCapabilitySet) throws RemoteImagingException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\RemoteRenderedImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */