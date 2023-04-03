package org.geotools.data.ows;

import java.io.IOException;
import java.io.InputStream;

public interface HTTPResponse {
  void dispose();
  
  String getContentType();
  
  String getResponseHeader(String paramString);
  
  InputStream getResponseStream() throws IOException;
  
  String getResponseCharset();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ows\HTTPResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */