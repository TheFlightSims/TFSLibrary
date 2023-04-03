package org.hsqldb.auth;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hsqldb.jdbc.JDBCArrayBasic;
import org.hsqldb.lib.FrameworkLogger;
import org.hsqldb.types.Type;

public class AuthBeanMultiplexer {
  private static FrameworkLogger logger = FrameworkLogger.getLog(AuthBeanMultiplexer.class);
  
  private static AuthBeanMultiplexer singleton = new AuthBeanMultiplexer();
  
  private static Map<String, List<AuthFunctionBean>> beans = new HashMap<String, List<AuthFunctionBean>>();
  
  public static AuthBeanMultiplexer getSingleton() {
    return singleton;
  }
  
  public void clear() {
    beans.clear();
  }
  
  public void setAuthFunctionBeans(Map<String, List<AuthFunctionBean>> paramMap) {
    if (beans.size() > 0)
      throw new IllegalStateException("Use setAuthFunctionBeans(Map) only when the set is empty"); 
    beans.putAll(paramMap);
  }
  
  protected static String getUniqueNameFor(Connection paramConnection) throws SQLException {
    ResultSet resultSet = paramConnection.createStatement().executeQuery("CALL database_name()");
    try {
      if (!resultSet.next())
        throw new SQLException("Engine did not reveal unique database name"); 
      return resultSet.getString(1);
    } finally {
      if (resultSet != null)
        try {
          resultSet.close();
        } catch (SQLException sQLException) {
          logger.error("Failed to close ResultSet for retrieving db name");
        }  
      resultSet = null;
    } 
  }
  
  public void setAuthFunctionBeans(Connection paramConnection, List<AuthFunctionBean> paramList) throws SQLException {
    setAuthFunctionBeans(getUniqueNameFor(paramConnection), paramList);
  }
  
  public void setAuthFunctionBeans(String paramString, List<AuthFunctionBean> paramList) {
    if (paramString == null || paramString.length() != 16)
      throw new IllegalArgumentException("Database name not exactly 16 characters long: " + paramString); 
    List<AuthFunctionBean> list = beans.get(paramString);
    if (list == null) {
      list = new ArrayList();
      beans.put(paramString, list);
    } else if (list.size() > 0) {
      throw new IllegalStateException("Use setAuthFunctionBeans(String, List) only when the db's AuthFunctionBean list is empty");
    } 
    list.addAll(paramList);
  }
  
  public void setAuthFunctionBean(Connection paramConnection, AuthFunctionBean paramAuthFunctionBean) throws SQLException {
    setAuthFunctionBeans(getUniqueNameFor(paramConnection), Collections.singletonList(paramAuthFunctionBean));
  }
  
  public void setAuthFunctionBean(String paramString, AuthFunctionBean paramAuthFunctionBean) {
    setAuthFunctionBeans(paramString, Collections.singletonList(paramAuthFunctionBean));
  }
  
  public static Array authenticate(String paramString1, String paramString2, String paramString3) throws Exception {
    if (paramString1 == null || paramString1.length() != 16)
      throw new IllegalStateException("Internal problem.  Database name not exactly 16 characters long: " + paramString1); 
    List list = beans.get(paramString1);
    if (list == null) {
      logger.error("Database '" + paramString1 + "' has not been set up with " + AuthBeanMultiplexer.class.getName());
      throw new IllegalArgumentException("Database '" + paramString1 + "' has not been set up with " + AuthBeanMultiplexer.class.getName());
    } 
    RuntimeException runtimeException = null;
    for (AuthFunctionBean authFunctionBean : list) {
      try {
        String[] arrayOfString = authFunctionBean.authenticate(paramString2, paramString3);
        return (arrayOfString == null) ? null : (Array)new JDBCArrayBasic((Object[])arrayOfString, (Type)Type.SQL_VARCHAR);
      } catch (RuntimeException runtimeException1) {
        if (runtimeException == null)
          runtimeException = runtimeException1; 
        logger.error("System failure of an AuthFunctionBean: " + ((runtimeException1.getMessage() == null) ? runtimeException1.toString() : runtimeException1.getMessage()));
      } catch (Exception exception) {
        throw exception;
      } 
    } 
    throw runtimeException;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\auth\AuthBeanMultiplexer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */