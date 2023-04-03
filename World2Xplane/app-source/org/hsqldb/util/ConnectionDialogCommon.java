package org.hsqldb.util;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Hashtable;

class ConnectionDialogCommon {
  private static String[][] connTypes;
  
  private static final String[][] sJDBCTypes = new String[][] { 
      { "HSQL Database Engine In-Memory", "org.hsqldb.jdbcDriver", "jdbc:hsqldb:mem:." }, { "HSQL Database Engine Standalone", "org.hsqldb.jdbcDriver", "jdbc:hsqldb:file:«database/path?»" }, { "HSQL Database Engine Server", "org.hsqldb.jdbcDriver", "jdbc:hsqldb:hsql://localhost/" }, { "HSQL Database Engine WebServer", "org.hsqldb.jdbcDriver", "jdbc:hsqldb:http://«hostname/?»" }, { "JDBC-ODBC Bridge from Sun", "sun.jdbc.odbc.JdbcOdbcDriver", "jdbc:odbc:«database?»" }, { "Cloudscape RMI", "RmiJdbc.RJDriver", "jdbc:rmi://«host?»:1099/jdbc:cloudscape:«database?»;create=true" }, { "IBM DB2", "COM.ibm.db2.jdbc.app.DB2Driver", "jdbc:db2:«database?»" }, { "IBM DB2 (thin)", "COM.ibm.db2.jdbc.net.DB2Driver", "jdbc:db2://«host?»:6789/«database?»" }, { "Informix", "com.informix.jdbc.IfxDriver", "jdbc:informix-sqli://«host?»:1533/«database?»:INFORMIXSERVER=«server?»" }, { "InstantDb", "jdbc.idbDriver", "jdbc:idb:«database?».prp" }, 
      { "MySQL Connector/J", "com.mysql.jdbc.Driver", "jdbc:mysql://«host?»/«database?»" }, { "MM.MySQL", "org.gjt.mm.mysql.Driver", "jdbc:mysql://«host?»/«database?»" }, { "Oracle", "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:oci8:@«database?»" }, { "Oracle (thin)", "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@«host?»:1521:«database?»" }, { "PointBase", "com.pointbase.jdbc.jdbcUniversalDriver", "jdbc:pointbase://«host?»/«database?»" }, { "PostgreSQL", "org.postgresql.Driver", "jdbc:postgresql://«host?»/«database?»" }, { "PostgreSQL v6.5", "postgresql.Driver", "jdbc:postgresql://«host?»/«database?»" } };
  
  private static final String fileName = "hsqlprefs.dat";
  
  private static File recentSettings = null;
  
  static String emptySettingName = "Recent settings...";
  
  private static String homedir = null;
  
  static String[][] getTypes() {
    return sJDBCTypes;
  }
  
  static synchronized Hashtable loadRecentConnectionSettings() throws IOException {
    Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
    try {
      if (recentSettings == null) {
        setHomeDir();
        if (homedir == null)
          return hashtable; 
        recentSettings = new File(homedir, "hsqlprefs.dat");
        if (!recentSettings.exists()) {
          recentSettings.createNewFile();
          return hashtable;
        } 
      } 
    } catch (Throwable throwable) {
      return hashtable;
    } 
    FileInputStream fileInputStream = null;
    ObjectInputStream objectInputStream = null;
    try {
      fileInputStream = new FileInputStream(recentSettings);
      objectInputStream = new ObjectInputStream(fileInputStream);
      hashtable.clear();
      while (true) {
        ConnectionSetting connectionSetting = (ConnectionSetting)objectInputStream.readObject();
        if (!emptySettingName.equals(connectionSetting.getName()))
          hashtable.put(connectionSetting.getName(), connectionSetting); 
      } 
    } catch (EOFException eOFException) {
    
    } catch (ClassNotFoundException classNotFoundException) {
      throw new IOException("Unrecognized class type " + classNotFoundException.getMessage());
    } catch (ClassCastException classCastException) {
      throw new IOException("Unrecognized class type " + classCastException.getMessage());
    } catch (Throwable throwable) {
    
    } finally {
      if (objectInputStream != null)
        objectInputStream.close(); 
      if (fileInputStream != null)
        fileInputStream.close(); 
    } 
    return hashtable;
  }
  
  static void addToRecentConnectionSettings(Hashtable<String, ConnectionSetting> paramHashtable, ConnectionSetting paramConnectionSetting) throws IOException {
    paramHashtable.put(paramConnectionSetting.getName(), paramConnectionSetting);
    storeRecentConnectionSettings(paramHashtable);
  }
  
  private static void storeRecentConnectionSettings(Hashtable paramHashtable) {
    try {
      if (recentSettings == null) {
        setHomeDir();
        if (homedir == null)
          return; 
        recentSettings = new File(homedir, "hsqlprefs.dat");
        if (!recentSettings.exists());
      } 
      if (paramHashtable == null || paramHashtable.size() == 0)
        return; 
      FileOutputStream fileOutputStream = new FileOutputStream(recentSettings);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      Enumeration enumeration = paramHashtable.elements();
      while (enumeration.hasMoreElements())
        objectOutputStream.writeObject(enumeration.nextElement()); 
      objectOutputStream.flush();
      objectOutputStream.close();
      fileOutputStream.close();
    } catch (Throwable throwable) {}
  }
  
  static void deleteRecentConnectionSettings() {
    try {
      if (recentSettings == null) {
        setHomeDir();
        if (homedir == null)
          return; 
        recentSettings = new File(homedir, "hsqlprefs.dat");
      } 
      if (!recentSettings.exists()) {
        recentSettings = null;
        return;
      } 
      recentSettings.delete();
      recentSettings = null;
    } catch (Throwable throwable) {}
  }
  
  public static void setHomeDir() {
    if (homedir == null)
      try {
        Class<?> clazz = Class.forName("sun.security.action.GetPropertyAction");
        Constructor<?> constructor = clazz.getConstructor(new Class[] { String.class });
        PrivilegedAction<String> privilegedAction = (PrivilegedAction)constructor.newInstance(new Object[] { "user.home" });
        homedir = AccessController.<String>doPrivileged(privilegedAction);
      } catch (Exception exception) {
        System.err.println("No access to home directory.  Continuing without...");
      }  
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqld\\util\ConnectionDialogCommon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */