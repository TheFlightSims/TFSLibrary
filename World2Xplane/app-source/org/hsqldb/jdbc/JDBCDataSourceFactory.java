package org.hsqldb.jdbc;

import java.util.Hashtable;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;
import javax.sql.DataSource;

public class JDBCDataSourceFactory implements ObjectFactory {
  private static final String urlName = "url";
  
  private static final String databaseName = "database";
  
  private static final String userName = "user";
  
  private static final String userNameName = "username";
  
  private static final String passwordName = "password";
  
  private static final String loginTimeoutName = "loginTimeout";
  
  private static final String bdsClassName = "org.hsqldb.jdbc.JDBCDataSource";
  
  private static final String poolClassName = "org.hsqldb.jdbc.JDBCPool";
  
  private static final String pdsClassName = "org.hsqldb.jdbc.pool.JDBCPooledDataSource";
  
  private static final String xdsClassName = "org.hsqldb.jdbc.pool.JDBCXADataSource";
  
  public static DataSource createDataSource(Properties paramProperties) throws Exception {
    JDBCDataSource jDBCDataSource = (JDBCDataSource)Class.forName("org.hsqldb.jdbc.JDBCDataSource").newInstance();
    String str = paramProperties.getProperty("database");
    if (str == null)
      str = paramProperties.getProperty("url"); 
    jDBCDataSource.setDatabase(str);
    str = paramProperties.getProperty("user");
    if (str == null)
      str = paramProperties.getProperty("username"); 
    jDBCDataSource.setUser(str);
    str = paramProperties.getProperty("password");
    jDBCDataSource.setPassword(str);
    str = paramProperties.getProperty("loginTimeout");
    if (str != null) {
      str = str.trim();
      if (str.length() > 0)
        try {
          jDBCDataSource.setLoginTimeout(Integer.parseInt(str));
        } catch (NumberFormatException numberFormatException) {} 
    } 
    return jDBCDataSource;
  }
  
  public Object getObjectInstance(Object paramObject, Name paramName, Context paramContext, Hashtable paramHashtable) throws Exception {
    if (!(paramObject instanceof Reference))
      return null; 
    Reference reference = (Reference)paramObject;
    String str = reference.getClassName();
    if ("org.hsqldb.jdbc.JDBCDataSource".equals(str) || "org.hsqldb.jdbc.JDBCPool".equals(str) || "org.hsqldb.jdbc.pool.JDBCPooledDataSource".equals(str) || "org.hsqldb.jdbc.pool.JDBCXADataSource".equals(str)) {
      JDBCCommonDataSource jDBCCommonDataSource = (JDBCCommonDataSource)Class.forName(str).newInstance();
      RefAddr refAddr = reference.get("database");
      if (refAddr == null)
        throw new Exception(str + ": RefAddr not set: database"); 
      Object object = refAddr.getContent();
      if (!(object instanceof String))
        throw new Exception(str + ": invalid RefAddr: database"); 
      jDBCCommonDataSource.setDatabase((String)object);
      refAddr = reference.get("user");
      if (refAddr == null)
        throw new Exception(str + ": RefAddr not set: user"); 
      object = reference.get("user").getContent();
      if (!(object instanceof String))
        throw new Exception(str + ": invalid RefAddr: user"); 
      jDBCCommonDataSource.setUser((String)object);
      refAddr = reference.get("password");
      if (refAddr == null) {
        object = "";
      } else {
        object = reference.get("password").getContent();
        if (!(object instanceof String))
          throw new Exception(str + ": invalid RefAddr: password"); 
      } 
      jDBCCommonDataSource.setPassword((String)object);
      refAddr = reference.get("loginTimeout");
      if (refAddr != null) {
        object = refAddr.getContent();
        if (object instanceof String) {
          String str1 = ((String)object).trim();
          if (str1.length() > 0)
            try {
              jDBCCommonDataSource.setLoginTimeout(Integer.parseInt(str1));
            } catch (NumberFormatException numberFormatException) {} 
        } 
      } 
      return jDBCCommonDataSource;
    } 
    return null;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\JDBCDataSourceFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */