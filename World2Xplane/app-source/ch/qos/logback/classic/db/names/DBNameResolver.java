package ch.qos.logback.classic.db.names;

public interface DBNameResolver {
  <N extends Enum<?>> String getTableName(N paramN);
  
  <N extends Enum<?>> String getColumnName(N paramN);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\db\names\DBNameResolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */