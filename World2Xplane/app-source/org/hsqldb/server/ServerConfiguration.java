package org.hsqldb.server;

import java.net.InetAddress;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.StringUtil;
import org.hsqldb.persist.HsqlProperties;

public final class ServerConfiguration implements ServerConstants {
  public static int getDefaultPort(int paramInt, boolean paramBoolean) {
    switch (paramInt) {
      case 1:
        return paramBoolean ? 554 : 9001;
      case 0:
        return paramBoolean ? 443 : 80;
      case 2:
        return paramBoolean ? -1 : 9101;
    } 
    return -1;
  }
  
  public static ServerProperties getPropertiesFromFile(int paramInt, String paramString1, String paramString2) {
    boolean bool;
    if (StringUtil.isEmpty(paramString1))
      return null; 
    ServerProperties serverProperties = new ServerProperties(paramInt, paramString1, paramString2);
    try {
      bool = serverProperties.load();
    } catch (Exception exception) {
      return null;
    } 
    return bool ? serverProperties : null;
  }
  
  public static String[] listLocalInetAddressNames() {
    HashSet hashSet = new HashSet();
    try {
      InetAddress inetAddress = InetAddress.getLocalHost();
      InetAddress[] arrayOfInetAddress = InetAddress.getAllByName(inetAddress.getHostAddress());
      byte b;
      for (b = 0; b < arrayOfInetAddress.length; b++) {
        hashSet.add(arrayOfInetAddress[b].getHostAddress());
        hashSet.add(arrayOfInetAddress[b].getHostName());
      } 
      arrayOfInetAddress = InetAddress.getAllByName(inetAddress.getHostName());
      for (b = 0; b < arrayOfInetAddress.length; b++) {
        hashSet.add(arrayOfInetAddress[b].getHostAddress());
        hashSet.add(arrayOfInetAddress[b].getHostName());
      } 
    } catch (Exception exception) {}
    try {
      InetAddress inetAddress = InetAddress.getByName(null);
      InetAddress[] arrayOfInetAddress = InetAddress.getAllByName(inetAddress.getHostAddress());
      byte b;
      for (b = 0; b < arrayOfInetAddress.length; b++) {
        hashSet.add(arrayOfInetAddress[b].getHostAddress());
        hashSet.add(arrayOfInetAddress[b].getHostName());
      } 
      arrayOfInetAddress = InetAddress.getAllByName(inetAddress.getHostName());
      for (b = 0; b < arrayOfInetAddress.length; b++) {
        hashSet.add(arrayOfInetAddress[b].getHostAddress());
        hashSet.add(arrayOfInetAddress[b].getHostName());
      } 
    } catch (Exception exception) {}
    try {
      hashSet.add(InetAddress.getByName("loopback").getHostAddress());
      hashSet.add(InetAddress.getByName("loopback").getHostName());
    } catch (Exception exception) {}
    String[] arrayOfString = new String[hashSet.size()];
    hashSet.toArray((Object[])arrayOfString);
    return arrayOfString;
  }
  
  public static ServerProperties newDefaultProperties(int paramInt) {
    ServerProperties serverProperties = new ServerProperties(paramInt);
    serverProperties.setProperty("server.restart_on_shutdown", false);
    serverProperties.setProperty("server.address", "0.0.0.0");
    serverProperties.setProperty("server.no_system_exit", true);
    serverProperties.setProperty("server.maxdatabases", 10);
    serverProperties.setProperty("server.silent", true);
    serverProperties.setProperty("server.tls", false);
    serverProperties.setProperty("server.trace", false);
    serverProperties.setProperty("server.default_page", "index.html");
    serverProperties.setProperty("server.root", ".");
    return serverProperties;
  }
  
  public static void translateAddressProperty(HsqlProperties paramHsqlProperties) {
    if (paramHsqlProperties == null)
      return; 
    String str = paramHsqlProperties.getProperty("server.address");
    if (StringUtil.isEmpty(str))
      paramHsqlProperties.setProperty("server.address", "0.0.0.0"); 
  }
  
  public static void translateDefaultDatabaseProperty(HsqlProperties paramHsqlProperties) {
    if (paramHsqlProperties == null)
      return; 
    if (!paramHsqlProperties.isPropertyTrue("server.remote_open")) {
      if (paramHsqlProperties.getProperty("server.database.0") == null) {
        String str = paramHsqlProperties.getProperty("server.database");
        if (str == null) {
          str = "test";
        } else {
          paramHsqlProperties.removeProperty("server.database");
        } 
        paramHsqlProperties.setProperty("server.database.0", str);
        paramHsqlProperties.setProperty("server.dbname.0", "");
      } 
      if (paramHsqlProperties.getProperty("server.dbname.0") == null)
        paramHsqlProperties.setProperty("server.dbname.0", ""); 
    } 
  }
  
  public static void translateDefaultNoSystemExitProperty(HsqlProperties paramHsqlProperties) {
    if (paramHsqlProperties == null)
      return; 
    paramHsqlProperties.setPropertyIfNotExists("server.no_system_exit", "false");
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\server\ServerConfiguration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */