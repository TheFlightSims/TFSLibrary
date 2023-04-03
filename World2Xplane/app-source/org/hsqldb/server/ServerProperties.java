package org.hsqldb.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.IntKeyHashMap;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.persist.HsqlProperties;

public class ServerProperties extends HsqlProperties {
  static final int SERVER_PROPERTY = 0;
  
  static final int SERVER_MULTI_PROPERTY = 1;
  
  static final int SYSTEM_PROPERTY = 2;
  
  static final String sc_key_prefix = "server";
  
  static final String sc_key_address = "server.address";
  
  static final String sc_key_autorestart_server = "server.restart_on_shutdown";
  
  static final String sc_key_database = "server.database";
  
  static final String sc_key_dbname = "server.dbname";
  
  static final String sc_key_no_system_exit = "server.no_system_exit";
  
  static final String sc_key_port = "server.port";
  
  static final String sc_key_http_port = "server.port";
  
  static final String sc_key_silent = "server.silent";
  
  static final String sc_key_tls = "server.tls";
  
  static final String sc_key_trace = "server.trace";
  
  static final String sc_key_web_default_page = "server.default_page";
  
  static final String sc_key_web_root = "server.root";
  
  static final String sc_key_max_connections = "server.maxconnections";
  
  static final String sc_key_remote_open_db = "server.remote_open";
  
  static final String sc_key_max_databases = "server.maxdatabases";
  
  static final String sc_key_acl = "server.acl";
  
  static final String sc_key_daemon = "server.daemon";
  
  static final String sc_key_props = "server.props";
  
  static final String sc_key_system = "system";
  
  static final String sc_default_web_mime = "text/html";
  
  static final String sc_default_web_page = "index.html";
  
  static final String sc_default_web_root = ".";
  
  static final HashMap meta = new HashMap();
  
  static final OrderedHashSet prefixes = new OrderedHashSet();
  
  final int protocol;
  
  protected boolean initialised = false;
  
  IntKeyHashMap idToAliasMap = new IntKeyHashMap();
  
  IntKeyHashMap idToPathMap = new IntKeyHashMap();
  
  public ServerProperties(int paramInt, File paramFile) throws IOException {
    FileInputStream fileInputStream = null;
    try {
      fileInputStream = new FileInputStream(paramFile);
      this.stringProps.load(fileInputStream);
    } finally {
      if (fileInputStream != null)
        fileInputStream.close(); 
    } 
    this.protocol = paramInt;
  }
  
  ServerProperties(int paramInt) {
    this.protocol = paramInt;
  }
  
  ServerProperties(int paramInt, String paramString1, String paramString2) {
    super(paramString1, paramString2);
    this.protocol = paramInt;
  }
  
  public void validate() {
    Enumeration<?> enumeration = this.stringProps.propertyNames();
    while (enumeration.hasMoreElements()) {
      String str1 = (String)enumeration.nextElement();
      Object[] arrayOfObject = (Object[])meta.get(str1);
      if (arrayOfObject == null)
        arrayOfObject = getPrefixedMetadata(str1); 
      if (arrayOfObject == null) {
        String str = "unsupported property: " + str1;
        addError(0, str);
        continue;
      } 
      String str2 = null;
      if (((Integer)arrayOfObject[1]).intValue() == 2) {
        str2 = validateSystemProperty(str1, arrayOfObject);
      } else if (((Integer)arrayOfObject[1]).intValue() == 1) {
        str2 = validateMultiProperty(str1, arrayOfObject);
      } else {
        String str = getProperty(str1);
        if (str == null) {
          if (arrayOfObject[4] == null) {
            str2 = "missing value for property: " + str1;
          } else {
            setProperty(str1, arrayOfObject[4].toString());
          } 
        } else {
          str2 = HsqlProperties.validateProperty(str1, str, arrayOfObject);
        } 
      } 
      if (str2 != null)
        addError(0, str2); 
    } 
    Iterator iterator = this.idToAliasMap.keySet().iterator();
    while (iterator.hasNext()) {
      int i = iterator.nextInt();
      if (!this.idToPathMap.containsKey(i))
        addError(0, "no path for database id: " + i); 
    } 
    iterator = this.idToPathMap.keySet().iterator();
    while (iterator.hasNext()) {
      int i = iterator.nextInt();
      if (!this.idToAliasMap.containsKey(i))
        addError(0, "no alias for database id: " + i); 
    } 
    this.initialised = true;
  }
  
  Object[] getPrefixedMetadata(String paramString) {
    for (byte b = 0; b < prefixes.size(); b++) {
      String str = (String)prefixes.get(b);
      if (paramString.startsWith(str))
        return (Object[])meta.get(str); 
    } 
    return null;
  }
  
  String validateMultiProperty(String paramString, Object[] paramArrayOfObject) {
    int i;
    String str = (String)paramArrayOfObject[0];
    if (paramArrayOfObject[0].equals("server.database") && "server.database".equals(paramString))
      paramString = paramString + ".0"; 
    try {
      i = Integer.parseInt(paramString.substring(str.length() + 1));
    } catch (NumberFormatException numberFormatException) {
      return "maformed database enumerator: " + paramString;
    } 
    if (paramArrayOfObject[0].equals("server.dbname")) {
      String str1 = this.stringProps.getProperty(paramString).toLowerCase();
      Object object = this.idToAliasMap.put(i, str1);
      if (object != null)
        return "duplicate database enumerator: " + paramString; 
    } else if (paramArrayOfObject[0].equals("server.database")) {
      String str1 = this.stringProps.getProperty(paramString);
      Object object = this.idToPathMap.put(i, str1);
      if (object != null)
        return "duplicate database enumerator: " + paramString; 
    } 
    return null;
  }
  
  String validateSystemProperty(String paramString, Object[] paramArrayOfObject) {
    String str1 = (String)paramArrayOfObject[0];
    String str2 = paramString.substring(str1.length() + 1);
    String str3 = this.stringProps.getProperty(paramString);
    if (str3 == null)
      return "value required for property: " + paramString; 
    System.setProperty(str2, str3);
    return null;
  }
  
  static {
    meta.put("server.database", getMeta("server.database", 1, null));
    meta.put("server.dbname", getMeta("server.dbname", 1, null));
    meta.put("system", getMeta("system", 2, null));
    meta.put("server.silent", getMeta("server.silent", 0, false));
    meta.put("server.trace", getMeta("server.trace", 0, false));
    meta.put("server.tls", getMeta("server.tls", 0, false));
    meta.put("server.acl", getMeta("server.acl", 0, null));
    meta.put("server.restart_on_shutdown", getMeta("server.restart_on_shutdown", 0, false));
    meta.put("server.remote_open", getMeta("server.remote_open", 0, false));
    meta.put("server.no_system_exit", getMeta("server.no_system_exit", 0, false));
    meta.put("server.daemon", getMeta("server.daemon", 0, false));
    meta.put("server.address", getMeta("server.address", 0, null));
    meta.put("server.port", getMeta("server.port", 0, 9001, 0, 65535));
    meta.put("server.port", getMeta("server.port", 0, 80, 0, 65535));
    meta.put("server.maxconnections", getMeta("server.maxconnections", 0, 100, 1, 10000));
    meta.put("server.maxdatabases", getMeta("server.maxdatabases", 0, 10, 1, 1000));
    prefixes.add("server.database");
    prefixes.add("server.dbname");
    prefixes.add("system");
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\server\ServerProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */