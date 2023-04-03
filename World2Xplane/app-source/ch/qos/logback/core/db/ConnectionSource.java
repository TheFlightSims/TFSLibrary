package ch.qos.logback.core.db;

import ch.qos.logback.core.db.dialect.SQLDialectCode;
import ch.qos.logback.core.spi.LifeCycle;
import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionSource extends LifeCycle {
  Connection getConnection() throws SQLException;
  
  SQLDialectCode getSQLDialectCode();
  
  boolean supportsGetGeneratedKeys();
  
  boolean supportsBatchUpdates();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\db\ConnectionSource.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */