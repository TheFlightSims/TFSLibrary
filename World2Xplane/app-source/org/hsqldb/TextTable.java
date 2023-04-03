package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.persist.DataFileCache;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.persist.TextCache;
import org.hsqldb.persist.TextFileReader;
import org.hsqldb.rowio.RowInputInterface;

public class TextTable extends Table {
  String dataSource = "";
  
  boolean isReversed = false;
  
  boolean isConnected = false;
  
  TextTable(Database paramDatabase, HsqlNameManager.HsqlName paramHsqlName, int paramInt) {
    super(paramDatabase, paramHsqlName, paramInt);
  }
  
  public boolean isConnected() {
    return this.isConnected;
  }
  
  public void connect(Session paramSession) {
    connect(paramSession, this.isReadOnly);
  }
  
  private void connect(Session paramSession, boolean paramBoolean) {
    if (this.dataSource.length() == 0 || this.isConnected)
      return; 
    PersistentStore persistentStore = this.database.persistentStoreCollection.getStore(this);
    this.store = persistentStore;
    TextCache textCache = null;
    TextFileReader textFileReader = null;
    try {
      textCache = (TextCache)this.database.logger.openTextFilePersistence(this, this.dataSource, paramBoolean, this.isReversed);
      persistentStore.setCache((DataFileCache)textCache);
      textFileReader = textCache.getTextFileReader();
      Row row = null;
      long l = 0L;
      if (textCache.isIgnoreFirstLine()) {
        l += textFileReader.readHeaderLine();
        textCache.setHeaderInitialise(textFileReader.getHeaderLine());
      } 
      while (true) {
        RowInputInterface rowInputInterface = textFileReader.readObject(l);
        if (rowInputInterface == null)
          break; 
        row = (Row)persistentStore.get(rowInputInterface);
        if (row == null)
          break; 
        Object[] arrayOfObject = row.getData();
        l = ((int)row.getPos() + row.getStorageSize());
        systemUpdateIdentityValue(arrayOfObject);
        enforceRowConstraints(paramSession, arrayOfObject);
        persistentStore.indexRow(paramSession, row);
      } 
    } catch (Throwable throwable) {
      boolean bool = (textFileReader == null) ? false : textFileReader.getLineNumber();
      clearAllData(paramSession);
      if (textCache != null) {
        this.database.logger.closeTextCache(this);
        persistentStore.release();
      } 
      throw Error.error(throwable, 483, 0, new Object[] { new Integer(bool), throwable.toString() });
    } 
    this.isConnected = true;
    this.isReadOnly = paramBoolean;
  }
  
  public void disconnect() {
    this.store = null;
    PersistentStore persistentStore = this.database.persistentStoreCollection.getStore(this);
    persistentStore.release();
    this.isConnected = false;
  }
  
  private void openCache(Session paramSession, String paramString, boolean paramBoolean1, boolean paramBoolean2) {
    String str = this.dataSource;
    boolean bool1 = this.isReversed;
    boolean bool2 = this.isReadOnly;
    if (paramString == null)
      paramString = ""; 
    disconnect();
    this.dataSource = paramString;
    this.isReversed = (paramBoolean1 && this.dataSource.length() > 0);
    try {
      connect(paramSession, paramBoolean2);
    } catch (HsqlException hsqlException) {
      this.dataSource = str;
      this.isReversed = bool1;
      connect(paramSession, bool2);
      throw hsqlException;
    } 
  }
  
  void setDataSource(Session paramSession, String paramString, boolean paramBoolean1, boolean paramBoolean2) {
    if (getTableType() != 6)
      paramSession.getGrantee().checkSchemaUpdateOrGrantRights((getSchemaName()).name); 
    paramString = paramString.trim();
    if (paramBoolean1 || paramBoolean1 != this.isReversed || !this.dataSource.equals(paramString) || !this.isConnected)
      openCache(paramSession, paramString, paramBoolean1, this.isReadOnly); 
    if (this.isReversed)
      this.isReadOnly = true; 
  }
  
  public String getDataSource() {
    return this.dataSource;
  }
  
  public boolean isDescDataSource() {
    return this.isReversed;
  }
  
  public void setHeader(String paramString) {
    PersistentStore persistentStore = this.database.persistentStoreCollection.getStore(this);
    TextCache textCache = (TextCache)persistentStore.getCache();
    if (textCache != null && textCache.isIgnoreFirstLine()) {
      textCache.setHeader(paramString);
      return;
    } 
    throw Error.error(486);
  }
  
  private String getHeader() {
    PersistentStore persistentStore = this.database.persistentStoreCollection.getStore(this);
    TextCache textCache = (TextCache)persistentStore.getCache();
    String str = (textCache == null) ? null : textCache.getHeader();
    return (str == null) ? null : StringConverter.toQuotedString(str, '\'', true);
  }
  
  public void checkDataReadOnly() {
    if (this.dataSource.length() == 0)
      throw Error.error(481); 
    if (isDataReadOnly())
      throw Error.error(456); 
  }
  
  public boolean isDataReadOnly() {
    return (!isConnected() || super.isDataReadOnly() || this.store.getCache().isDataReadOnly());
  }
  
  public void setDataReadOnly(boolean paramBoolean) {
    if (!paramBoolean) {
      if (this.isReversed)
        throw Error.error(456); 
      if (this.database.isFilesReadOnly())
        throw Error.error(455); 
      if (isConnected()) {
        this.store.getCache().close();
        this.store.getCache().open(paramBoolean);
      } 
    } 
    this.isReadOnly = paramBoolean;
  }
  
  public void insertData(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject) {
    Row row = (Row)paramPersistentStore.getNewCachedObject(paramSession, paramArrayOfObject, false);
    paramPersistentStore.indexRow(paramSession, row);
    paramPersistentStore.commitPersistence(row);
  }
  
  String getDataSourceDDL() {
    String str = getDataSource();
    if (str == null)
      return null; 
    StringBuffer stringBuffer = new StringBuffer(128);
    stringBuffer.append("SET").append(' ').append("TABLE").append(' ');
    stringBuffer.append(getName().getSchemaQualifiedStatementName());
    stringBuffer.append(' ').append("SOURCE").append(' ').append('\'');
    stringBuffer.append(str);
    stringBuffer.append('\'');
    return stringBuffer.toString();
  }
  
  String getDataSourceHeader() {
    String str = getHeader();
    if (str == null)
      return null; 
    StringBuffer stringBuffer = new StringBuffer(128);
    stringBuffer.append("SET").append(' ').append("TABLE").append(' ');
    stringBuffer.append(getName().getSchemaQualifiedStatementName());
    stringBuffer.append(' ').append("SOURCE").append(' ');
    stringBuffer.append("HEADER").append(' ');
    stringBuffer.append(str);
    return stringBuffer.toString();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\TextTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */