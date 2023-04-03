package org.geotools.data.ows;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;
import org.geotools.ows.ServiceException;

public interface Request {
  public static final String REQUEST = "REQUEST";
  
  public static final String VERSION = "VERSION";
  
  public static final String WMTVER = "WMTVER";
  
  public static final String SERVICE = "SERVICE";
  
  URL getFinalURL();
  
  void setProperty(String paramString1, String paramString2);
  
  Properties getProperties();
  
  Response createResponse(HTTPResponse paramHTTPResponse) throws ServiceException, IOException;
  
  boolean requiresPost();
  
  String getPostContentType();
  
  void performPostOutput(OutputStream paramOutputStream) throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ows\Request.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */