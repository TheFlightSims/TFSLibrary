package org.hsqldb.server;

import java.sql.SQLException;
import org.hsqldb.HsqlException;
import org.hsqldb.error.Error;
import org.hsqldb.jdbc.JDBCUtil;

public class HsqlServerFactory {
  public static HsqlSocketRequestHandler createHsqlServer(String paramString, boolean paramBoolean1, boolean paramBoolean2) throws SQLException {
    ServerProperties serverProperties = new ServerProperties(1);
    serverProperties.setProperty("server.dbname.0", "");
    serverProperties.setProperty("server.database.0", paramString);
    serverProperties.setProperty("server.trace", paramBoolean1);
    serverProperties.setProperty("server.silent", paramBoolean2);
    Server server = new Server();
    try {
      server.setProperties(serverProperties);
    } catch (Exception exception) {
      throw new SQLException("Failed to set server properties: " + exception);
    } 
    if (!server.openDatabases()) {
      Throwable throwable = server.getServerError();
      if (throwable instanceof HsqlException)
        throw JDBCUtil.sqlException((HsqlException)throwable); 
      throw JDBCUtil.sqlException(Error.error(458));
    } 
    server.setState(1);
    return server;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\server\HsqlServerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */