package org.geotools.data.ows;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public interface HTTPClient {
  HTTPResponse post(URL paramURL, InputStream paramInputStream, String paramString) throws IOException;
  
  HTTPResponse get(URL paramURL) throws IOException;
  
  String getUser();
  
  void setUser(String paramString);
  
  String getPassword();
  
  void setPassword(String paramString);
  
  int getConnectTimeout();
  
  void setConnectTimeout(int paramInt);
  
  int getReadTimeout();
  
  void setReadTimeout(int paramInt);
  
  void setTryGzip(boolean paramBoolean);
  
  boolean isTryGzip();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ows\HTTPClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */