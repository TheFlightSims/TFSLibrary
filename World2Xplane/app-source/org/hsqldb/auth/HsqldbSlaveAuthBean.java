package org.hsqldb.auth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;
import org.hsqldb.lib.FrameworkLogger;

public class HsqldbSlaveAuthBean implements AuthFunctionBean {
  private static FrameworkLogger logger = FrameworkLogger.getLog(HsqldbSlaveAuthBean.class);
  
  private String masterJdbcUrl;
  
  private String validationUser;
  
  private String validationPassword;
  
  private boolean delegateRolesSchema = true;
  
  protected boolean initialized;
  
  public void setValidationUser(String paramString) {
    this.validationUser = paramString;
  }
  
  public void setValidationPassword(String paramString) {
    this.validationPassword = paramString;
  }
  
  public void setMasterJdbcUrl(String paramString) {
    this.masterJdbcUrl = paramString;
  }
  
  public void setDelegateRolesSchema(boolean paramBoolean) {
    this.delegateRolesSchema = paramBoolean;
  }
  
  public void init() throws SQLException {
    if (this.masterJdbcUrl == null)
      throw new IllegalStateException("Required property 'masterJdbcUrl' not set"); 
    if (this.validationUser != null || this.validationPassword != null) {
      if (this.validationUser == null || this.validationPassword == null)
        throw new IllegalStateException("If you set one property of 'validationUser' or 'validationPassword', then you must set both."); 
      Connection connection = null;
      SQLException sQLException = null;
      try {
        connection = DriverManager.getConnection(this.masterJdbcUrl, this.validationUser, this.validationPassword);
      } catch (SQLException sQLException1) {
        logger.error("Master/slave Connection validation failure", sQLException1);
        sQLException = sQLException1;
      } finally {
        if (connection != null)
          try {
            connection.close();
            connection = null;
          } catch (SQLException sQLException1) {
            logger.error("Failed to close test master/slave Connection", sQLException1);
            if (sQLException == null)
              throw sQLException1; 
          }  
      } 
    } 
    this.initialized = true;
  }
  
  public String[] authenticate(String paramString1, String paramString2) throws DenyException {
    if (!this.initialized)
      throw new IllegalStateException("You must invoke the 'init' method to initialize the " + HsqldbSlaveAuthBean.class.getName() + " instance."); 
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(this.masterJdbcUrl, paramString1, paramString2);
      if (this.delegateRolesSchema) {
        Set<String> set = AuthUtils.getEnabledRoles(connection);
        String str = AuthUtils.getInitialSchema(connection);
        if (str != null)
          set.add(str); 
        logger.finer("Slave delegating schema+roles: " + set);
        return set.<String>toArray(new String[0]);
      } 
      return null;
    } catch (SQLException sQLException) {
      throw new DenyException();
    } finally {
      if (connection != null)
        try {
          connection.close();
          connection = null;
        } catch (SQLException sQLException) {
          logger.severe("Failed to close master/slave Connection", sQLException);
        }  
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\auth\HsqldbSlaveAuthBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */