package org.geotools.data.ows;

import java.net.URL;

public abstract class Specification {
  public abstract String getVersion();
  
  public abstract GetCapabilitiesRequest createGetCapabilitiesRequest(URL paramURL);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ows\Specification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */