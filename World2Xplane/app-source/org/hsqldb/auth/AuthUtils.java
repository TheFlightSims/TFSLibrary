package org.hsqldb.auth;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import org.hsqldb.lib.FrameworkLogger;

public class AuthUtils {
  private static FrameworkLogger logger = FrameworkLogger.getLog(AuthUtils.class);
  
  static String getInitialSchema(Connection paramConnection) throws SQLException {
    ResultSet resultSet = paramConnection.createStatement().executeQuery("SELECT initial_schema FROM information_schema.system_users\nWHERE user_name = current_user");
    try {
      if (!resultSet.next())
        throw new IllegalStateException("Failed to retrieve initial_schema for current user"); 
      return resultSet.getString(1);
    } finally {
      if (resultSet != null)
        try {
          resultSet.close();
        } catch (SQLException sQLException) {
          logger.error("Failed to close ResultSet for retrieving initial schema");
        }  
      resultSet = null;
    } 
  }
  
  static Set getEnabledRoles(Connection paramConnection) throws SQLException {
    HashSet<String> hashSet = new HashSet();
    ResultSet resultSet = paramConnection.createStatement().executeQuery("SELECT * FROM information_schema.enabled_roles");
    try {
      while (resultSet.next())
        hashSet.add(resultSet.getString(1)); 
    } finally {
      if (resultSet != null)
        try {
          resultSet.close();
        } catch (SQLException sQLException) {
          logger.error("Failed to close ResultSet for retrieving db name");
        }  
      resultSet = null;
    } 
    return hashSet;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\auth\AuthUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */