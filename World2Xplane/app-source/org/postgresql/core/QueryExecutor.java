package org.postgresql.core;

import java.sql.SQLException;
import org.postgresql.copy.CopyOperation;

public interface QueryExecutor {
  public static final int QUERY_ONESHOT = 1;
  
  public static final int QUERY_NO_METADATA = 2;
  
  public static final int QUERY_NO_RESULTS = 4;
  
  public static final int QUERY_FORWARD_CURSOR = 8;
  
  public static final int QUERY_SUPPRESS_BEGIN = 16;
  
  public static final int QUERY_DESCRIBE_ONLY = 32;
  
  public static final int QUERY_BOTH_ROWS_AND_STATUS = 64;
  
  public static final int QUERY_DISALLOW_BATCHING = 128;
  
  void execute(Query paramQuery, ParameterList paramParameterList, ResultHandler paramResultHandler, int paramInt1, int paramInt2, int paramInt3) throws SQLException;
  
  void execute(Query[] paramArrayOfQuery, ParameterList[] paramArrayOfParameterList, ResultHandler paramResultHandler, int paramInt1, int paramInt2, int paramInt3) throws SQLException;
  
  void fetch(ResultCursor paramResultCursor, ResultHandler paramResultHandler, int paramInt) throws SQLException;
  
  Query createSimpleQuery(String paramString);
  
  Query createParameterizedQuery(String paramString);
  
  void processNotifies() throws SQLException;
  
  ParameterList createFastpathParameters(int paramInt);
  
  byte[] fastpathCall(int paramInt, ParameterList paramParameterList, boolean paramBoolean) throws SQLException;
  
  CopyOperation startCopy(String paramString, boolean paramBoolean) throws SQLException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\QueryExecutor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */