package org.hsqldb.jdbc;

import java.sql.SQLException;
import java.sql.Savepoint;

public class JDBCSavepoint implements Savepoint {
  int id;
  
  String name;
  
  JDBCConnection connection;
  
  JDBCSavepoint(String paramString, JDBCConnection paramJDBCConnection) throws SQLException {
    if (paramString == null)
      throw JDBCUtil.nullArgument("name"); 
    if (paramJDBCConnection == null)
      throw JDBCUtil.nullArgument("conn"); 
    this.name = paramString;
    this.id = -1;
    this.connection = paramJDBCConnection;
  }
  
  JDBCSavepoint(JDBCConnection paramJDBCConnection) throws SQLException {
    if (paramJDBCConnection == null)
      throw JDBCUtil.nullArgument("conn"); 
    this.id = paramJDBCConnection.getSavepointID();
    this.name = "SYSTEM_SAVEPOINT_" + this.id;
    this.connection = paramJDBCConnection;
  }
  
  public int getSavepointId() throws SQLException {
    if (this.id != -1)
      return this.id; 
    throw JDBCUtil.notSupported();
  }
  
  public String getSavepointName() throws SQLException {
    if (this.id == -1)
      return this.name; 
    throw JDBCUtil.notSupported();
  }
  
  public String toString() {
    return super.toString() + "[name=" + this.name + "]";
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\JDBCSavepoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */