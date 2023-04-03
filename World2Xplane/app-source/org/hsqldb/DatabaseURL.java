package org.hsqldb;

import java.util.Locale;
import org.hsqldb.persist.HsqlProperties;

public class DatabaseURL {
  static final String S_DOT = ".";
  
  public static final String S_MEM = "mem:";
  
  public static final String S_FILE = "file:";
  
  public static final String S_RES = "res:";
  
  public static final String S_ALIAS = "alias:";
  
  public static final String S_HSQL = "hsql://";
  
  public static final String S_HSQLS = "hsqls://";
  
  public static final String S_HTTP = "http://";
  
  public static final String S_HTTPS = "https://";
  
  public static final String S_URL_PREFIX = "jdbc:hsqldb:";
  
  public static final String S_URL_INTERNAL = "jdbc:default:connection";
  
  public static final String url_connection_type = "connection_type";
  
  public static final String url_database = "database";
  
  public static boolean isFileBasedDatabaseType(String paramString) {
    return (paramString == "file:" || paramString == "res:");
  }
  
  public static boolean isInProcessDatabaseType(String paramString) {
    return (paramString == "file:" || paramString == "res:" || paramString == "mem:");
  }
  
  public static HsqlProperties parseURL(String paramString, boolean paramBoolean1, boolean paramBoolean2) {
    String str4;
    String str1 = paramString.toLowerCase(Locale.ENGLISH);
    HsqlProperties hsqlProperties1 = new HsqlProperties();
    HsqlProperties hsqlProperties2 = null;
    String str2 = null;
    int i = 0;
    String str3 = null;
    int j = 0;
    boolean bool = false;
    if (paramBoolean1)
      if (str1.startsWith("jdbc:hsqldb:")) {
        i = "jdbc:hsqldb:".length();
      } else {
        return hsqlProperties1;
      }  
    while (true) {
      int n = paramString.indexOf("${");
      if (n == -1)
        break; 
      int i1 = paramString.indexOf("}", n);
      if (i1 == -1)
        break; 
      String str5 = paramString.substring(n + 2, i1);
      String str6 = null;
      try {
        str6 = System.getProperty(str5);
      } catch (SecurityException securityException) {}
      if (str6 == null)
        break; 
      paramString = paramString.substring(0, n) + str6 + paramString.substring(i1 + 1);
      str1 = paramString.toLowerCase(Locale.ENGLISH);
    } 
    hsqlProperties1.setProperty("url", paramString);
    int k = paramString.length();
    int m = paramString.indexOf(';', i);
    if (m > -1) {
      str2 = paramString.substring(m + 1, str1.length());
      k = m;
      hsqlProperties2 = HsqlProperties.delimitedArgPairsToProps(str2, "=", ";", null);
      hsqlProperties1.addProperties(hsqlProperties2);
    } 
    if (k == i + 1 && str1.startsWith(".", i)) {
      str3 = ".";
    } else if (str1.startsWith("mem:", i)) {
      str3 = "mem:";
    } else if (str1.startsWith("file:", i)) {
      str3 = "file:";
    } else if (str1.startsWith("res:", i)) {
      str3 = "res:";
    } else if (str1.startsWith("alias:", i)) {
      str3 = "alias:";
    } else if (str1.startsWith("hsql://", i)) {
      str3 = "hsql://";
      j = 9001;
      bool = true;
    } else if (str1.startsWith("hsqls://", i)) {
      str3 = "hsqls://";
      j = 554;
      bool = true;
    } else if (str1.startsWith("http://", i)) {
      str3 = "http://";
      j = 80;
      bool = true;
    } else if (str1.startsWith("https://", i)) {
      str3 = "https://";
      j = 443;
      bool = true;
    } 
    if (str3 == null) {
      str3 = "file:";
    } else if (str3 == ".") {
      str3 = "mem:";
    } else {
      i += str3.length();
    } 
    hsqlProperties1.setProperty("connection_type", str3);
    if (bool) {
      String str5;
      String str6 = null;
      String str7 = null;
      String str8 = null;
      int n = paramString.indexOf('/', i);
      if (n > 0 && n < k) {
        str6 = paramString.substring(n, k);
      } else {
        n = k;
      } 
      if (paramString.charAt(i) == '[') {
        int i1 = paramString.indexOf(']', i + 2);
        if (i1 < 0 || i1 >= n)
          return null; 
        str7 = str1.substring(i + 1, i1);
        if (n > i1 + 1)
          str8 = paramString.substring(i1 + 1, n); 
      } else {
        int i1 = paramString.indexOf(':', i + 1);
        if (i1 > -1 && i1 < n) {
          str8 = paramString.substring(i1, n);
        } else {
          i1 = -1;
        } 
        str7 = str1.substring(i, (i1 > 0) ? i1 : n);
      } 
      if (str8 != null) {
        if (str8.length() < 2 || str8.charAt(0) != ':')
          return null; 
        try {
          j = Integer.parseInt(str8.substring(1));
        } catch (NumberFormatException numberFormatException) {
          return null;
        } 
      } 
      if (paramBoolean2) {
        str5 = "";
        str4 = str6;
      } else if (str6 == null) {
        str5 = "/";
        str4 = "";
      } else {
        int i1 = str6.lastIndexOf('/');
        if (i1 < 1) {
          str5 = "/";
          str4 = str6.substring(1).toLowerCase(Locale.ENGLISH);
        } else {
          str5 = str6.substring(0, i1);
          str4 = str6.substring(i1 + 1);
        } 
      } 
      hsqlProperties1.setProperty("port", j);
      hsqlProperties1.setProperty("host", str7);
      hsqlProperties1.setProperty("path", str5);
      if (!paramBoolean2 && hsqlProperties2 != null) {
        String str = hsqlProperties2.getProperty("filepath");
        if (str != null && str4.length() != 0) {
          str4 = str4 + ";" + str;
        } else if (paramString.indexOf("mem:") == k + 1 || paramString.indexOf("file:") == k + 1) {
          str4 = str4 + paramString.substring(k);
        } 
      } 
    } else {
      if (str3 == "mem:") {
        str4 = str1.substring(i, k);
      } else if (str3 == "res:") {
        str4 = paramString.substring(i, k);
        if (str4.indexOf('/') != 0)
          str4 = '/' + str4; 
      } else {
        str4 = paramString.substring(i, k);
        if (str4.startsWith("~")) {
          String str = "~";
          try {
            str = System.getProperty("user.home");
          } catch (SecurityException securityException) {}
          str4 = str + str4.substring(1);
        } 
      } 
      if (str4.length() == 0)
        return null; 
    } 
    i = str4.indexOf("&password=");
    if (i != -1) {
      String str = str4.substring(i + "&password=".length());
      hsqlProperties1.setProperty("password", str);
      str4 = str4.substring(0, i);
    } 
    i = str4.indexOf("?user=");
    if (i != -1) {
      String str = str4.substring(i + "?user=".length());
      hsqlProperties1.setProperty("user", str);
      str4 = str4.substring(0, i);
    } 
    hsqlProperties1.setProperty("database", str4);
    return hsqlProperties1;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\DatabaseURL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */