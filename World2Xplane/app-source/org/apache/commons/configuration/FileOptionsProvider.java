package org.apache.commons.configuration;

import java.util.Map;

public interface FileOptionsProvider {
  public static final String CURRENT_USER = "currentUser";
  
  public static final String VERSIONING = "versioning";
  
  public static final String PROXY_HOST = "proxyHost";
  
  public static final String PROXY_PORT = "proxyPort";
  
  public static final String MAX_HOST_CONNECTIONS = "maxHostConnections";
  
  public static final String MAX_TOTAL_CONNECTIONS = "maxTotalConnections";
  
  Map getOptions();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\FileOptionsProvider.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */