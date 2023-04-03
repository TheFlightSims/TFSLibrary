package org.hsqldb.scriptio;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.Session;
import org.hsqldb.Statement;
import org.hsqldb.error.Error;
import org.hsqldb.lib.LineReader;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.map.ValuePool;
import org.hsqldb.result.Result;
import org.hsqldb.rowio.RowInputTextLog;
import org.hsqldb.types.Type;

public class ScriptReaderText extends ScriptReaderBase {
  LineReader dataStreamIn;
  
  RowInputTextLog rowIn;
  
  boolean isInsert;
  
  public ScriptReaderText(Database paramDatabase) {
    super(paramDatabase);
  }
  
  public ScriptReaderText(Database paramDatabase, String paramString, boolean paramBoolean) throws IOException {
    super(paramDatabase);
    InputStream inputStream = this.database.logger.getFileAccess().openInputStreamElement(paramString);
    inputStream = new BufferedInputStream(inputStream);
    if (paramBoolean)
      inputStream = new GZIPInputStream(inputStream); 
    this.dataStreamIn = new LineReader(inputStream, "ISO-8859-1");
    this.rowIn = new RowInputTextLog(paramDatabase.databaseProperties.isVersion18());
  }
  
  public ScriptReaderText(Database paramDatabase, InputStream paramInputStream) {
    super(paramDatabase);
    this.dataStreamIn = new LineReader(paramInputStream, "ISO-8859-1");
    this.rowIn = new RowInputTextLog(paramDatabase.databaseProperties.isVersion18());
  }
  
  protected void readDDL(Session paramSession) {
    while (readLoggedStatement(paramSession)) {
      Statement statement = null;
      Result result = null;
      if (this.rowIn.getStatementType() == 3) {
        this.isInsert = true;
        break;
      } 
      try {
        statement = paramSession.compileStatement(this.statement);
        result = paramSession.executeCompiledStatement(statement, ValuePool.emptyObjectArray, 0);
      } catch (HsqlException hsqlException) {
        result = Result.newErrorResult((Throwable)hsqlException);
      } 
      if ((!result.isError() || statement == null || (statement.getType() != 48 && (statement.getType() != 14 || result.getMainString().indexOf("org.hsqldb.Library") <= -1))) && result.isError()) {
        this.database.logger.logWarningEvent(result.getMainString(), (Throwable)result.getException());
        if (statement != null && statement.getType() == 14)
          continue; 
        HsqlException hsqlException = Error.error((Throwable)result.getException(), 461, 25, new Object[] { this.database.getCanonicalPath(), new Integer(this.lineCount), result.getMainString() });
        handleException(hsqlException);
      } 
    } 
  }
  
  protected void readExistingData(Session paramSession) {
    try {
      String str = null;
      this.database.setReferentialIntegrity(false);
      while (true) {
        if (this.isInsert || readLoggedStatement(paramSession)) {
          if (this.statementType == 6) {
            paramSession.setSchema(this.currentSchema);
          } else if (this.statementType == 3) {
            if (!this.rowIn.getTableName().equals(str)) {
              str = this.rowIn.getTableName();
              String str1 = paramSession.getSchemaName(this.currentSchema);
              this.currentTable = this.database.schemaManager.getUserTable(paramSession, str, str1);
              this.currentStore = this.database.persistentStoreCollection.getStore(this.currentTable);
            } 
            try {
              this.currentTable.insertFromScript(paramSession, this.currentStore, this.rowData);
            } catch (HsqlException hsqlException) {
              handleException(hsqlException);
            } 
          } else {
            throw Error.error(461, this.statement);
          } 
          this.isInsert = false;
          continue;
        } 
        this.database.setReferentialIntegrity(true);
        return;
      } 
    } catch (Throwable throwable) {
      this.database.logger.logSevereEvent("readExistingData failed", throwable);
      throw Error.error(throwable, 461, 25, new Object[] { new Integer(this.lineCount), throwable.toString() });
    } 
  }
  
  public boolean readLoggedStatement(Session paramSession) {
    if (!this.sessionChanged) {
      try {
        this.rawStatement = this.dataStreamIn.readLine();
      } catch (EOFException eOFException) {
        return false;
      } catch (IOException iOException) {
        throw Error.error(iOException, 452, null);
      } 
      this.lineCount++;
      this.statement = StringConverter.unicodeStringToString(this.rawStatement);
      if (this.statement == null)
        return false; 
    } 
    processStatement(paramSession);
    return true;
  }
  
  void processStatement(Session paramSession) {
    Type[] arrayOfType;
    if (this.statement.startsWith("/*C")) {
      int i = this.statement.indexOf('*', 4);
      this.sessionNumber = Integer.parseInt(this.statement.substring(3, i));
      this.statement = this.statement.substring(i + 2);
      this.sessionChanged = true;
      this.statementType = 5;
      return;
    } 
    this.sessionChanged = false;
    this.rowIn.setSource(this.statement);
    this.statementType = this.rowIn.getStatementType();
    if (this.statementType == 1) {
      this.rowData = null;
      this.currentTable = null;
      return;
    } 
    if (this.statementType == 4) {
      this.rowData = null;
      this.currentTable = null;
      return;
    } 
    if (this.statementType == 6) {
      this.rowData = null;
      this.currentTable = null;
      this.currentSchema = this.rowIn.getSchemaName();
      return;
    } 
    String str1 = this.rowIn.getTableName();
    String str2 = (paramSession.getCurrentSchemaHsqlName()).name;
    this.currentTable = this.database.schemaManager.getUserTable(paramSession, str1, str2);
    this.currentStore = this.database.persistentStoreCollection.getStore(this.currentTable);
    if (this.statementType == 3) {
      arrayOfType = this.currentTable.getColumnTypes();
    } else if (this.currentTable.hasPrimaryKey()) {
      arrayOfType = this.currentTable.getPrimaryKeyTypes();
    } else {
      arrayOfType = this.currentTable.getColumnTypes();
    } 
    try {
      this.rowData = this.rowIn.readData(arrayOfType);
    } catch (IOException iOException) {
      throw Error.error(iOException, 452, null);
    } 
  }
  
  public void close() {
    try {
      this.dataStreamIn.close();
      if (this.scrwriter != null)
        this.scrwriter.close(); 
      this.database.recoveryMode = 0;
    } catch (Exception exception) {}
  }
  
  private void handleException(HsqlException paramHsqlException) {
    if (this.database.recoveryMode == 0)
      throw paramHsqlException; 
    if (this.scrwriter == null) {
      String str = this.database.getPath() + ".reject";
      this.scrwriter = new ScriptWriterText(this.database, str, true, true, true);
    } 
    try {
      this.scrwriter.writeLogStatement(null, this.rawStatement);
    } catch (Throwable throwable) {}
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\scriptio\ScriptReaderText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */