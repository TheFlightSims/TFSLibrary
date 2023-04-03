package org.hsqldb.persist;

import java.io.InputStream;
import org.hsqldb.ColumnSchema;
import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.HsqlNameManager;
import org.hsqldb.Row;
import org.hsqldb.Session;
import org.hsqldb.Statement;
import org.hsqldb.StatementDML;
import org.hsqldb.StatementSchema;
import org.hsqldb.Table;
import org.hsqldb.error.Error;
import org.hsqldb.lib.IntKeyHashMap;
import org.hsqldb.lib.StopWatch;
import org.hsqldb.map.ValuePool;
import org.hsqldb.result.Result;
import org.hsqldb.scriptio.ScriptReaderBase;
import org.hsqldb.scriptio.ScriptReaderDecode;
import org.hsqldb.scriptio.ScriptReaderText;
import org.hsqldb.types.Type;

public class ScriptRunner {
  public static void runScript(Database paramDatabase, InputStream paramInputStream) {
    ScriptReaderDecode scriptReaderDecode;
    Crypto crypto = paramDatabase.logger.getCrypto();
    if (crypto == null) {
      ScriptReaderText scriptReaderText = new ScriptReaderText(paramDatabase, paramInputStream);
    } else {
      try {
        scriptReaderDecode = new ScriptReaderDecode(paramDatabase, paramInputStream, crypto, true);
      } catch (Throwable throwable) {
        paramDatabase.logger.logSevereEvent("opening log file", throwable);
        return;
      } 
    } 
    runScript(paramDatabase, (ScriptReaderBase)scriptReaderDecode);
  }
  
  public static void runScript(Database paramDatabase, String paramString) {
    ScriptReaderDecode scriptReaderDecode;
    Crypto crypto = paramDatabase.logger.getCrypto();
    try {
      if (crypto == null) {
        ScriptReaderText scriptReaderText = new ScriptReaderText(paramDatabase, paramString, false);
      } else {
        scriptReaderDecode = new ScriptReaderDecode(paramDatabase, paramString, crypto, true);
      } 
    } catch (Throwable throwable) {
      if (!(throwable instanceof java.io.EOFException))
        paramDatabase.logger.logSevereEvent("opening log file", throwable); 
      return;
    } 
    runScript(paramDatabase, (ScriptReaderBase)scriptReaderDecode);
  }
  
  private static void runScript(Database paramDatabase, ScriptReaderBase paramScriptReaderBase) {
    IntKeyHashMap intKeyHashMap = new IntKeyHashMap();
    Session session = null;
    int i = 0;
    StatementDML statementDML = new StatementDML(81, 2004, null);
    String str = paramDatabase.getPath();
    boolean bool = paramDatabase.getURLProperties().isPropertyTrue("hsqldb.full_log_replay");
    statementDML.setCompileTimestamp(Long.MAX_VALUE);
    paramDatabase.setReferentialIntegrity(false);
    try {
      StopWatch stopWatch = new StopWatch();
      while (paramScriptReaderBase.readLoggedStatement(session)) {
        String str1;
        Object[] arrayOfObject1;
        Table table;
        HsqlNameManager.HsqlName hsqlName;
        PersistentStore persistentStore;
        Object[] arrayOfObject2;
        Row row;
        int k = paramScriptReaderBase.getSessionNumber();
        if (session == null || i != k) {
          i = k;
          session = (Session)intKeyHashMap.get(i);
          if (session == null) {
            session = paramDatabase.getSessionManager().newSessionForLog(paramDatabase);
            intKeyHashMap.put(i, session);
          } 
        } 
        if (session.isClosed()) {
          intKeyHashMap.remove(i);
          continue;
        } 
        Result result = null;
        int j = paramScriptReaderBase.getStatementType();
        switch (j) {
          case 1:
            str1 = paramScriptReaderBase.getLoggedStatement();
            try {
              Statement statement = session.compileStatement(str1);
              if (paramDatabase.getProperties().isVersion18() && statement.getType() == 77) {
                Table table1 = (Table)((StatementSchema)statement).getArguments()[0];
                for (byte b = 0; b < table1.getColumnCount(); b++) {
                  ColumnSchema columnSchema = table1.getColumn(b);
                  if (columnSchema.getDataType().isBitType())
                    columnSchema.setType((Type)Type.SQL_BOOLEAN); 
                } 
              } 
              result = session.executeCompiledStatement(statement, ValuePool.emptyObjectArray, 0);
            } catch (Throwable throwable) {
              result = Result.newErrorResult(throwable);
            } 
            if (result != null && result.isError()) {
              if (result.getException() != null)
                throw result.getException(); 
              throw Error.error(result);
            } 
            break;
          case 4:
            session.commit(false);
            break;
          case 3:
            session.sessionContext.currentStatement = (Statement)statementDML;
            session.beginAction((Statement)statementDML);
            arrayOfObject1 = paramScriptReaderBase.getData();
            paramScriptReaderBase.getCurrentTable().insertNoCheckFromLog(session, arrayOfObject1);
            session.endAction(Result.updateOneResult);
            break;
          case 2:
            session.sessionContext.currentStatement = (Statement)statementDML;
            session.beginAction((Statement)statementDML);
            table = paramScriptReaderBase.getCurrentTable();
            persistentStore = table.getRowStore(session);
            arrayOfObject2 = paramScriptReaderBase.getData();
            row = table.getDeleteRowFromLog(session, arrayOfObject2);
            if (row != null)
              session.addDeleteAction(table, persistentStore, row, null); 
            session.endAction(Result.updateOneResult);
            break;
          case 6:
            hsqlName = paramDatabase.schemaManager.findSchemaHsqlName(paramScriptReaderBase.getCurrentSchema());
            session.setCurrentSchemaHsqlName(hsqlName);
            break;
        } 
        if (session.isClosed())
          intKeyHashMap.remove(i); 
      } 
    } catch (HsqlException hsqlException) {
      String str1 = "statement error processing log " + str + "line: " + paramScriptReaderBase.getLineNumber();
      paramDatabase.logger.logSevereEvent(str1, (Throwable)hsqlException);
      if (bool)
        throw Error.error(hsqlException, 461, str1); 
    } catch (OutOfMemoryError outOfMemoryError) {
      String str1 = "out of memory processing log" + str + " line: " + paramScriptReaderBase.getLineNumber();
      paramDatabase.logger.logSevereEvent(str1, outOfMemoryError);
      throw Error.error(460);
    } catch (Throwable throwable) {
      String str1 = "statement error processing log " + str + "line: " + paramScriptReaderBase.getLineNumber();
      paramDatabase.logger.logSevereEvent(str1, throwable);
      if (bool)
        throw Error.error(throwable, 461, str1); 
    } finally {
      if (paramScriptReaderBase != null)
        paramScriptReaderBase.close(); 
      paramDatabase.getSessionManager().closeAllSessions();
      paramDatabase.setReferentialIntegrity(true);
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\ScriptRunner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */