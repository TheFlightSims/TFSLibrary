package org.hsqldb.lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.StringTokenizer;

public class RCData {
  public static final String DEFAULT_JDBC_DRIVER = "org.hsqldb.jdbc.JDBCDriver";
  
  private String defaultJdbcDriverName = "org.hsqldb.jdbc.JDBCDriver";
  
  public String id;
  
  public String url;
  
  public String username;
  
  public String password;
  
  public String ti;
  
  public String driver;
  
  public String charset;
  
  public String truststore;
  
  public String libpath;
  
  public void setDefaultJdbcDriver(String paramString) {
    this.defaultJdbcDriverName = paramString;
  }
  
  public String getDefaultJdbcDriverName() {
    return this.defaultJdbcDriverName;
  }
  
  public RCData(File paramFile, String paramString) throws Exception {
    if (paramFile == null)
      throw new IllegalArgumentException("RC file name not specified"); 
    if (!paramFile.canRead())
      throw new IOException("Please set up authentication file '" + paramFile + "'"); 
    StringTokenizer stringTokenizer = null;
    boolean bool = false;
    byte b = 0;
    BufferedReader bufferedReader = new BufferedReader(new FileReader(paramFile));
    try {
      String str;
      while ((str = bufferedReader.readLine()) != null) {
        String str1;
        String str2;
        b++;
        str = str.trim();
        if (str.length() == 0 || str.charAt(0) == '#')
          continue; 
        stringTokenizer = new StringTokenizer(str);
        if (stringTokenizer.countTokens() == 1) {
          str1 = stringTokenizer.nextToken();
          str2 = "";
        } else if (stringTokenizer.countTokens() > 1) {
          str1 = stringTokenizer.nextToken();
          str2 = stringTokenizer.nextToken("").trim();
        } else {
          try {
            bufferedReader.close();
          } catch (IOException iOException) {}
          throw new Exception("Corrupt line " + b + " in '" + paramFile + "':  " + str);
        } 
        if (paramString == null) {
          if (str1.equals("urlid"))
            System.out.println(str2); 
          continue;
        } 
        if (str1.equals("urlid")) {
          if (str2.equals(paramString)) {
            if (this.id == null) {
              this.id = paramString;
              bool = true;
              continue;
            } 
            try {
              bufferedReader.close();
            } catch (IOException iOException) {}
            throw new Exception("Key '" + paramString + " redefined at" + " line " + b + " in '" + paramFile);
          } 
          bool = false;
          continue;
        } 
        if (bool) {
          if (str1.equals("url")) {
            this.url = str2;
            continue;
          } 
          if (str1.equals("username")) {
            this.username = str2;
            continue;
          } 
          if (str1.equals("driver")) {
            this.driver = str2;
            continue;
          } 
          if (str1.equals("charset")) {
            this.charset = str2;
            continue;
          } 
          if (str1.equals("truststore")) {
            this.truststore = str2;
            continue;
          } 
          if (str1.equals("password")) {
            this.password = str2;
            continue;
          } 
          if (str1.equals("transiso")) {
            this.ti = str2;
            continue;
          } 
          if (str1.equals("libpath")) {
            this.libpath = str2;
            continue;
          } 
          try {
            bufferedReader.close();
          } catch (IOException iOException) {}
          throw new Exception("Bad line " + b + " in '" + paramFile + "':  " + str);
        } 
      } 
    } finally {
      try {
        bufferedReader.close();
      } catch (IOException iOException) {}
      bufferedReader = null;
    } 
    if (paramString == null)
      return; 
    if (this.url == null)
      throw new Exception("url not set for '" + paramString + "' in file '" + paramFile + "'"); 
    if (this.libpath != null)
      throw new IllegalArgumentException("Sorry, 'libpath' not supported yet"); 
  }
  
  public RCData(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7) throws Exception {
    this(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, null);
  }
  
  public RCData(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8) throws Exception {
    this(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, null);
  }
  
  public RCData(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9) throws Exception {
    this.id = paramString1;
    this.url = paramString2;
    this.username = paramString3;
    this.password = paramString4;
    this.ti = paramString9;
    this.driver = paramString5;
    this.charset = paramString6;
    this.truststore = paramString7;
    this.libpath = paramString8;
    if (paramString8 != null)
      throw new IllegalArgumentException("Sorry, 'libpath' not supported yet"); 
    if (paramString1 == null || paramString2 == null)
      throw new Exception("id or url was not set"); 
  }
  
  public Connection getConnection() throws ClassNotFoundException, SQLException, MalformedURLException {
    return getConnection(null, null);
  }
  
  public Connection getConnection(String paramString1, String paramString2) throws ClassNotFoundException, MalformedURLException, SQLException {
    String str1 = null;
    String str2 = null;
    Properties properties = System.getProperties();
    if (paramString1 == null) {
      str1 = (this.driver == null) ? "org.hsqldb.jdbc.JDBCDriver" : this.driver;
    } else {
      str1 = expandSysPropVars(paramString1);
    } 
    if (paramString2 == null) {
      if (this.truststore != null)
        str2 = expandSysPropVars(this.truststore); 
    } else {
      str2 = expandSysPropVars(paramString2);
    } 
    if (str2 == null) {
      properties.remove("javax.net.ssl.trustStore");
    } else {
      properties.put("javax.net.ssl.trustStore", str2);
    } 
    String str3 = null;
    try {
      str3 = expandSysPropVars(this.url);
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new MalformedURLException(illegalArgumentException.toString() + " for URL '" + this.url + "'");
    } 
    String str4 = null;
    if (this.username != null)
      try {
        str4 = expandSysPropVars(this.username);
      } catch (IllegalArgumentException illegalArgumentException) {
        throw new MalformedURLException(illegalArgumentException.toString() + " for user name '" + this.username + "'");
      }  
    String str5 = null;
    if (this.password != null)
      try {
        str5 = expandSysPropVars(this.password);
      } catch (IllegalArgumentException illegalArgumentException) {
        throw new MalformedURLException(illegalArgumentException.toString() + " for password");
      }  
    Class.forName(str1);
    Connection connection = (str4 == null) ? DriverManager.getConnection(str3) : DriverManager.getConnection(str3, str4, str5);
    if (this.ti != null)
      setTI(connection, this.ti); 
    return connection;
  }
  
  public static String expandSysPropVars(String paramString) {
    String str;
    for (str = new String(paramString);; str = str.substring(0, i) + str1 + str.substring(j + 1)) {
      int i = str.indexOf("${");
      if (i < 0)
        break; 
      int j = str.indexOf('}', i + 2);
      if (j < 0)
        break; 
      String str2 = str.substring(i + 2, j);
      if (str2.length() < 1)
        throw new IllegalArgumentException("Bad variable setting"); 
      String str1 = System.getProperty(str2);
      if (str1 == null)
        throw new IllegalArgumentException("No Java system property with name '" + str2 + "'"); 
    } 
    return str;
  }
  
  public static void setTI(Connection paramConnection, String paramString) throws SQLException {
    byte b = -1;
    if (paramString.equals("TRANSACTION_READ_UNCOMMITTED"))
      b = 1; 
    if (paramString.equals("TRANSACTION_READ_COMMITTED"))
      b = 2; 
    if (paramString.equals("TRANSACTION_REPEATABLE_READ"))
      b = 4; 
    if (paramString.equals("TRANSACTION_SERIALIZABLE"))
      b = 8; 
    if (paramString.equals("TRANSACTION_NONE"))
      b = 0; 
    if (b < 0)
      throw new SQLException("Trans. isol. value not supported by " + RCData.class.getName() + ": " + paramString); 
    paramConnection.setTransactionIsolation(b);
  }
  
  public static String tiToString(int paramInt) {
    switch (paramInt) {
      case 1:
        return "TRANSACTION_READ_UNCOMMITTED";
      case 2:
        return "TRANSACTION_READ_COMMITTED";
      case 4:
        return "TRANSACTION_REPEATABLE_READ";
      case 8:
        return "TRANSACTION_SERIALIZABLE";
      case 0:
        return "TRANSACTION_NONE";
    } 
    return "Custom Transaction Isolation numerical value: " + paramInt;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\RCData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */