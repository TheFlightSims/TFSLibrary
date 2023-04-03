package org.hsqldb.util;

import java.io.Serializable;

public class ConnectionSetting implements Serializable {
  private String name;
  
  private String driver;
  
  private String url;
  
  private String user;
  
  private String pw;
  
  String getName() {
    return this.name;
  }
  
  String getDriver() {
    return this.driver;
  }
  
  String getUrl() {
    return this.url;
  }
  
  String getUser() {
    return this.user;
  }
  
  String getPassword() {
    return this.pw;
  }
  
  private ConnectionSetting() {}
  
  ConnectionSetting(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
    this.name = paramString1;
    this.driver = paramString2;
    this.url = paramString3;
    this.user = paramString4;
    this.pw = paramString5;
  }
  
  public boolean equals(Object paramObject) {
    if (!(paramObject instanceof ConnectionSetting))
      return false; 
    ConnectionSetting connectionSetting = (ConnectionSetting)paramObject;
    return (getName() == connectionSetting.getName()) ? true : ((getName() == null) ? false : getName().trim().equals(connectionSetting.getName().trim()));
  }
  
  public int hashCode() {
    return (getName() == null) ? 0 : getName().trim().hashCode();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqld\\util\ConnectionSetting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */