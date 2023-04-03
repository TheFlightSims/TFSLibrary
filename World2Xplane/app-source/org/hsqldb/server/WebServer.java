package org.hsqldb.server;

import org.hsqldb.lib.FileUtil;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.resources.ResourceBundleHandler;

public class WebServer extends Server {
  static int webBundleHandle = ResourceBundleHandler.getBundleHandle("webserver-pages", null);
  
  public WebServer() {
    super(0);
  }
  
  public static void main(String[] paramArrayOfString) {
    HsqlProperties hsqlProperties = null;
    hsqlProperties = HsqlProperties.argArrayToProps(paramArrayOfString, "server");
    String[] arrayOfString = hsqlProperties.getErrorKeys();
    if (arrayOfString.length != 0) {
      System.out.println("no value for argument:" + arrayOfString[0]);
      printHelp("webserver.help");
      return;
    } 
    String str1 = hsqlProperties.getProperty("server.props");
    String str2 = "";
    if (str1 == null) {
      str1 = "webserver";
      str2 = ".properties";
    } 
    str1 = FileUtil.getFileUtil().canonicalOrAbsolutePath(str1);
    ServerProperties serverProperties1 = ServerConfiguration.getPropertiesFromFile(0, str1, str2);
    ServerProperties serverProperties2 = (serverProperties1 == null) ? new ServerProperties(0) : serverProperties1;
    serverProperties2.addProperties(hsqlProperties);
    ServerConfiguration.translateDefaultDatabaseProperty(serverProperties2);
    ServerConfiguration.translateDefaultNoSystemExitProperty(serverProperties2);
    ServerConfiguration.translateAddressProperty(serverProperties2);
    WebServer webServer = new WebServer();
    try {
      webServer.setProperties(serverProperties2);
    } catch (Exception exception) {
      webServer.printError("Failed to set properties");
      webServer.printStackTrace(exception);
      return;
    } 
    webServer.print("Startup sequence initiated from main() method");
    if (serverProperties1 != null) {
      webServer.print("Loaded properties from [" + str1 + ".properties]");
    } else {
      webServer.print("Could not load properties from file");
      webServer.print("Using cli/default properties only");
    } 
    webServer.start();
  }
  
  public String getDefaultWebPage() {
    return this.serverProperties.getProperty("server.default_page");
  }
  
  public String getHelpString() {
    return ResourceBundleHandler.getString(serverBundleHandle, "webserver.help");
  }
  
  public String getProductName() {
    return "HSQLDB web server";
  }
  
  public String getProtocol() {
    return isTls() ? "HTTPS" : "HTTP";
  }
  
  public String getWebRoot() {
    return this.serverProperties.getProperty("server.root");
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\server\WebServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */