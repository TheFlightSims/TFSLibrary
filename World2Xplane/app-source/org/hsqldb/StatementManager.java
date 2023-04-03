package org.hsqldb;

import org.hsqldb.lib.IntKeyHashMap;
import org.hsqldb.lib.LongKeyHashMap;
import org.hsqldb.lib.LongKeyIntValueHashMap;
import org.hsqldb.lib.LongValueHashMap;
import org.hsqldb.result.Result;

public final class StatementManager {
  private Database database;
  
  private IntKeyHashMap schemaMap;
  
  private LongKeyHashMap csidMap;
  
  private LongKeyIntValueHashMap useMap;
  
  private long next_cs_id;
  
  StatementManager(Database paramDatabase) {
    this.database = paramDatabase;
    this.schemaMap = new IntKeyHashMap();
    this.csidMap = new LongKeyHashMap();
    this.useMap = new LongKeyIntValueHashMap();
    this.next_cs_id = 0L;
  }
  
  synchronized void reset() {
    this.schemaMap.clear();
    this.csidMap.clear();
    this.useMap.clear();
    this.next_cs_id = 0L;
  }
  
  private long nextID() {
    this.next_cs_id++;
    return this.next_cs_id;
  }
  
  private long getStatementID(HsqlNameManager.HsqlName paramHsqlName, String paramString) {
    LongValueHashMap longValueHashMap = (LongValueHashMap)this.schemaMap.get(paramHsqlName.hashCode());
    return (longValueHashMap == null) ? -1L : longValueHashMap.get(paramString, -1);
  }
  
  public synchronized Statement getStatement(Session paramSession, long paramLong) {
    Statement statement = (Statement)this.csidMap.get(paramLong);
    if (statement == null)
      return null; 
    if (statement.getCompileTimestamp() < this.database.schemaManager.getSchemaChangeTimestamp()) {
      statement = recompileStatement(paramSession, statement);
      if (statement == null) {
        freeStatement(paramLong);
        return null;
      } 
      this.csidMap.put(paramLong, statement);
    } 
    return statement;
  }
  
  public synchronized Statement getStatement(Session paramSession, Statement paramStatement) {
    long l = paramStatement.getID();
    Statement statement = (Statement)this.csidMap.get(l);
    if (statement != null)
      return getStatement(paramSession, l); 
    if (paramStatement.getCompileTimestamp() < this.database.schemaManager.getSchemaChangeTimestamp()) {
      statement = recompileStatement(paramSession, paramStatement);
      if (statement == null) {
        freeStatement(l);
        return null;
      } 
    } 
    return statement;
  }
  
  private Statement recompileStatement(Session paramSession, Statement paramStatement) {
    Statement statement;
    HsqlNameManager.HsqlName hsqlName = paramSession.getCurrentSchemaHsqlName();
    try {
      HsqlNameManager.HsqlName hsqlName1 = paramStatement.getSchemaName();
      int i = paramStatement.getCursorPropertiesRequest();
      if (hsqlName1 != null)
        paramSession.setSchema(hsqlName1.name); 
      boolean bool = (paramStatement.generatedResultMetaData() != null) ? true : false;
      statement = paramSession.compileStatement(paramStatement.getSQL(), i);
      statement.setCursorPropertiesRequest(i);
      if (!paramStatement.getResultMetaData().areTypesCompatible(statement.getResultMetaData()))
        return null; 
      if (!paramStatement.getParametersMetaData().areTypesCompatible(statement.getParametersMetaData()))
        return null; 
      statement.setCompileTimestamp(this.database.txManager.getGlobalChangeTimestamp());
      if (bool) {
        StatementDML statementDML = (StatementDML)paramStatement;
        statement.setGeneratedColumnInfo(statementDML.generatedType, statementDML.generatedInputMetaData);
      } 
    } catch (Throwable throwable) {
      return null;
    } finally {
      paramSession.setCurrentSchemaHsqlName(hsqlName);
    } 
    return statement;
  }
  
  private long registerStatement(long paramLong, Statement paramStatement) {
    if (paramLong < 0L) {
      paramLong = nextID();
      int i = paramStatement.getSchemaName().hashCode();
      LongValueHashMap longValueHashMap = (LongValueHashMap)this.schemaMap.get(i);
      if (longValueHashMap == null) {
        longValueHashMap = new LongValueHashMap();
        this.schemaMap.put(i, longValueHashMap);
      } 
      longValueHashMap.put(paramStatement.getSQL(), paramLong);
    } 
    paramStatement.setID(paramLong);
    paramStatement.setCompileTimestamp(this.database.txManager.getGlobalChangeTimestamp());
    this.csidMap.put(paramLong, paramStatement);
    return paramLong;
  }
  
  synchronized void freeStatement(long paramLong) {
    if (paramLong == -1L)
      return; 
    int i = this.useMap.get(paramLong, 1);
    if (i > 1) {
      this.useMap.put(paramLong, i - 1);
      return;
    } 
    Statement statement = (Statement)this.csidMap.remove(paramLong);
    if (statement != null) {
      int j = statement.getSchemaName().hashCode();
      LongValueHashMap longValueHashMap = (LongValueHashMap)this.schemaMap.get(j);
      String str = statement.getSQL();
      longValueHashMap.remove(str);
    } 
    this.useMap.remove(paramLong);
  }
  
  synchronized Statement compile(Session paramSession, Result paramResult) throws Throwable {
    int i = paramResult.getExecuteProperties();
    Statement statement = null;
    String str = paramResult.getMainString();
    long l = getStatementID(paramSession.currentSchema, str);
    if (l >= 0L) {
      statement = (Statement)this.csidMap.get(l);
      if (statement != null && statement.getCursorPropertiesRequest() != i) {
        statement = null;
        l = -1L;
      } 
    } 
    if (statement == null || !statement.isValid() || statement.getCompileTimestamp() < this.database.schemaManager.getSchemaChangeTimestamp()) {
      statement = paramSession.compileStatement(str, i);
      statement.setCursorPropertiesRequest(i);
      l = registerStatement(l, statement);
    } 
    int j = this.useMap.get(l, 0) + 1;
    this.useMap.put(l, j);
    statement.setGeneratedColumnInfo(paramResult.getGeneratedResultType(), paramResult.getGeneratedResultMetaData());
    return statement;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\StatementManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */