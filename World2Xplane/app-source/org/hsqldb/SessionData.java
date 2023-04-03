package org.hsqldb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import org.hsqldb.error.Error;
import org.hsqldb.lib.CharArrayWriter;
import org.hsqldb.lib.CountdownInputStream;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.LongKeyHashMap;
import org.hsqldb.lib.LongKeyLongValueHashMap;
import org.hsqldb.lib.ReaderInputStream;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.navigator.RowSetNavigatorClient;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.persist.PersistentStoreCollection;
import org.hsqldb.persist.PersistentStoreCollectionSession;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultLob;
import org.hsqldb.result.ResultProperties;
import org.hsqldb.types.BlobData;
import org.hsqldb.types.BlobDataID;
import org.hsqldb.types.ClobData;
import org.hsqldb.types.ClobDataID;
import org.hsqldb.types.LobData;

public class SessionData {
  private final Database database;
  
  private final Session session;
  
  public PersistentStoreCollectionSession persistentStoreCollection;
  
  LongKeyHashMap resultMap;
  
  Object currentValue;
  
  HashMap sequenceMap;
  
  HashMap sequenceUpdateMap;
  
  boolean hasLobOps;
  
  long firstNewLobID;
  
  LongKeyLongValueHashMap resultLobs = new LongKeyLongValueHashMap();
  
  public SessionData(Database paramDatabase, Session paramSession) {
    this.database = paramDatabase;
    this.session = paramSession;
    this.persistentStoreCollection = new PersistentStoreCollectionSession(paramSession);
  }
  
  public PersistentStore getSubqueryRowStore(TableBase paramTableBase) {
    PersistentStore persistentStore = this.persistentStoreCollection.getStore(paramTableBase);
    persistentStore.removeAll();
    return persistentStore;
  }
  
  public PersistentStore getNewResultRowStore(TableBase paramTableBase, boolean paramBoolean) {
    try {
      PersistentStore persistentStore = this.session.database.logger.newStore(this.session, (PersistentStoreCollection)this.persistentStoreCollection, paramTableBase);
      if (!paramBoolean)
        persistentStore.setMemory(true); 
      return persistentStore;
    } catch (HsqlException hsqlException) {
      throw Error.runtimeError(201, "SessionData");
    } 
  }
  
  void setResultSetProperties(Result paramResult1, Result paramResult2) {
    int i = paramResult1.rsProperties;
    int j = paramResult2.rsProperties;
    if (i != j) {
      if (ResultProperties.isReadOnly(i)) {
        j = ResultProperties.addHoldable(j, ResultProperties.isHoldable(i));
      } else if (ResultProperties.isUpdatable(j)) {
        if (ResultProperties.isHoldable(i))
          this.session.addWarning(Error.error(4713)); 
      } else {
        j = ResultProperties.addHoldable(j, ResultProperties.isHoldable(i));
        this.session.addWarning(Error.error(4712));
      } 
      if (ResultProperties.isSensitive(i))
        this.session.addWarning(Error.error(4711)); 
      j = ResultProperties.addScrollable(j, ResultProperties.isScrollable(i));
      paramResult2.rsProperties = j;
    } 
  }
  
  Result getDataResultHead(Result paramResult1, Result paramResult2, boolean paramBoolean) {
    int i = paramResult1.getFetchSize();
    paramResult2.setResultId(this.session.actionTimestamp);
    int j = paramResult1.rsProperties;
    int k = paramResult2.rsProperties;
    if (j != k) {
      if (ResultProperties.isReadOnly(j)) {
        k = ResultProperties.addHoldable(k, ResultProperties.isHoldable(j));
      } else if (ResultProperties.isReadOnly(k)) {
        k = ResultProperties.addHoldable(k, ResultProperties.isHoldable(j));
      } else if (this.session.isAutoCommit()) {
        k = ResultProperties.addHoldable(k, ResultProperties.isHoldable(j));
      } else {
        k = ResultProperties.addHoldable(k, false);
      } 
      k = ResultProperties.addScrollable(k, ResultProperties.isScrollable(j));
      paramResult2.rsProperties = k;
    } 
    boolean bool1 = false;
    boolean bool2 = false;
    if (ResultProperties.isUpdatable(paramResult2.rsProperties))
      bool1 = true; 
    if (paramBoolean) {
      if (i != 0 && paramResult2.getNavigator().getSize() > i) {
        bool2 = true;
        bool1 = true;
      } 
    } else if (!paramResult2.getNavigator().isMemory()) {
      bool1 = true;
    } 
    if (bool1) {
      if (this.resultMap == null)
        this.resultMap = new LongKeyHashMap(); 
      this.resultMap.put(paramResult2.getResultId(), paramResult2);
      paramResult2.rsProperties = ResultProperties.addIsHeld(paramResult2.rsProperties, true);
    } 
    if (bool2)
      paramResult2 = Result.newDataHeadResult(this.session, paramResult2, 0, i); 
    return paramResult2;
  }
  
  Result getDataResultSlice(long paramLong, int paramInt1, int paramInt2) {
    Result result = (Result)this.resultMap.get(paramLong);
    RowSetNavigator rowSetNavigator = result.getNavigator();
    if (paramInt1 + paramInt2 > rowSetNavigator.getSize())
      paramInt2 = rowSetNavigator.getSize() - paramInt1; 
    return Result.newDataRowsResult(result, paramInt1, paramInt2);
  }
  
  Result getDataResult(long paramLong) {
    return (Result)this.resultMap.get(paramLong);
  }
  
  RowSetNavigatorClient getRowSetSlice(long paramLong, int paramInt1, int paramInt2) {
    Result result = (Result)this.resultMap.get(paramLong);
    RowSetNavigator rowSetNavigator = result.getNavigator();
    if (paramInt1 + paramInt2 > rowSetNavigator.getSize())
      paramInt2 = rowSetNavigator.getSize() - paramInt1; 
    return new RowSetNavigatorClient(rowSetNavigator, paramInt1, paramInt2);
  }
  
  public void closeNavigator(long paramLong) {
    Result result = (Result)this.resultMap.remove(paramLong);
    if (result != null)
      result.getNavigator().release(); 
  }
  
  public void closeAllNavigators() {
    if (this.resultMap == null)
      return; 
    Iterator iterator = this.resultMap.values().iterator();
    while (iterator.hasNext()) {
      Result result = (Result)iterator.next();
      result.getNavigator().release();
    } 
    this.resultMap.clear();
  }
  
  public void closeAllTransactionNavigators() {
    if (this.resultMap == null)
      return; 
    Iterator iterator = this.resultMap.values().iterator();
    while (iterator.hasNext()) {
      Result result = (Result)iterator.next();
      if (!ResultProperties.isHoldable(result.rsProperties)) {
        result.getNavigator().release();
        iterator.remove();
      } 
    } 
  }
  
  public void registerNewLob(long paramLong) {
    if (this.firstNewLobID == 0L)
      this.firstNewLobID = paramLong; 
    this.hasLobOps = true;
  }
  
  public void clearLobOps() {
    this.firstNewLobID = 0L;
    this.hasLobOps = false;
  }
  
  public long getFirstLobID() {
    return this.firstNewLobID;
  }
  
  public void adjustLobUsageCount(Object paramObject, int paramInt) {
    if (this.session.isProcessingLog() || this.session.isProcessingScript())
      return; 
    if (paramObject == null)
      return; 
    this.database.lobManager.adjustUsageCount(this.session, ((LobData)paramObject).getId(), paramInt);
    this.hasLobOps = true;
  }
  
  public void adjustLobUsageCount(TableBase paramTableBase, Object[] paramArrayOfObject, int paramInt) {
    if (!paramTableBase.hasLobColumn)
      return; 
    if (paramTableBase.isTemp)
      return; 
    if (this.session.isProcessingLog() || this.session.isProcessingScript())
      return; 
    for (byte b = 0; b < paramTableBase.columnCount; b++) {
      if (paramTableBase.colTypes[b].isLobType()) {
        Object object = paramArrayOfObject[b];
        if (object != null) {
          this.database.lobManager.adjustUsageCount(this.session, ((LobData)object).getId(), paramInt);
          this.hasLobOps = true;
        } 
      } 
    } 
  }
  
  public void allocateLobForResult(ResultLob paramResultLob, InputStream paramInputStream) {
    try {
      CountdownInputStream countdownInputStream;
      long l1;
      long l2;
      byte[] arrayOfByte;
      char[] arrayOfChar;
      Result result;
      switch (paramResultLob.getSubType()) {
        case 7:
          l2 = paramResultLob.getBlockLength();
          if (l2 < 0L) {
            allocateBlobSegments(paramResultLob, paramResultLob.getInputStream());
            break;
          } 
          if (paramInputStream == null) {
            l1 = paramResultLob.getLobID();
            paramInputStream = paramResultLob.getInputStream();
          } else {
            BlobDataID blobDataID = this.session.createBlob(l2);
            l1 = blobDataID.getId();
            this.resultLobs.put(paramResultLob.getLobID(), l1);
          } 
          countdownInputStream = new CountdownInputStream(paramInputStream);
          countdownInputStream.setCount(l2);
          this.database.lobManager.setBytesForNewBlob(l1, (InputStream)countdownInputStream, paramResultLob.getBlockLength());
          break;
        case 8:
          l2 = paramResultLob.getBlockLength();
          if (l2 < 0L) {
            allocateClobSegments(paramResultLob, paramResultLob.getReader());
            break;
          } 
          if (paramInputStream == null) {
            l1 = paramResultLob.getLobID();
            if (paramResultLob.getReader() != null) {
              ReaderInputStream readerInputStream = new ReaderInputStream(paramResultLob.getReader());
            } else {
              paramInputStream = paramResultLob.getInputStream();
            } 
          } else {
            ClobDataID clobDataID = this.session.createClob(l2);
            l1 = clobDataID.getId();
            this.resultLobs.put(paramResultLob.getLobID(), l1);
          } 
          countdownInputStream = new CountdownInputStream(paramInputStream);
          countdownInputStream.setCount(l2 * 2L);
          this.database.lobManager.setCharsForNewClob(l1, (InputStream)countdownInputStream, paramResultLob.getBlockLength());
          break;
        case 2:
          l1 = this.resultLobs.get(paramResultLob.getLobID());
          l2 = paramResultLob.getBlockLength();
          arrayOfByte = paramResultLob.getByteArray();
          result = this.database.lobManager.setBytes(l1, paramResultLob.getOffset(), arrayOfByte, (int)l2);
          break;
        case 4:
          l1 = this.resultLobs.get(paramResultLob.getLobID());
          l2 = paramResultLob.getBlockLength();
          arrayOfChar = paramResultLob.getCharArray();
          result = this.database.lobManager.setChars(l1, paramResultLob.getOffset(), arrayOfChar, (int)l2);
          break;
      } 
    } catch (Throwable throwable) {
      this.resultLobs.clear();
      throw Error.error(458, throwable);
    } 
  }
  
  private void allocateBlobSegments(ResultLob paramResultLob, InputStream paramInputStream) throws IOException {
    long l = paramResultLob.getOffset();
    int i = this.session.getStreamBlockSize();
    HsqlByteArrayOutputStream hsqlByteArrayOutputStream = new HsqlByteArrayOutputStream(i);
    do {
      hsqlByteArrayOutputStream.reset();
      hsqlByteArrayOutputStream.write(paramInputStream, i);
      if (hsqlByteArrayOutputStream.size() == 0)
        return; 
      byte[] arrayOfByte = hsqlByteArrayOutputStream.getBuffer();
      Result result = this.database.lobManager.setBytes(paramResultLob.getLobID(), l, arrayOfByte, hsqlByteArrayOutputStream.size());
      l += hsqlByteArrayOutputStream.size();
    } while (hsqlByteArrayOutputStream.size() >= i);
  }
  
  private void allocateClobSegments(ResultLob paramResultLob, Reader paramReader) throws IOException {
    allocateClobSegments(paramResultLob.getLobID(), paramResultLob.getOffset(), paramReader);
  }
  
  private void allocateClobSegments(long paramLong1, long paramLong2, Reader paramReader) throws IOException {
    int i = this.session.getStreamBlockSize();
    CharArrayWriter charArrayWriter = new CharArrayWriter(i);
    long l = paramLong2;
    do {
      charArrayWriter.reset();
      charArrayWriter.write(paramReader, i);
      char[] arrayOfChar = charArrayWriter.getBuffer();
      if (charArrayWriter.size() == 0)
        return; 
      Result result = this.database.lobManager.setChars(paramLong1, l, arrayOfChar, charArrayWriter.size());
      l += charArrayWriter.size();
    } while (charArrayWriter.size() >= i);
  }
  
  public void registerLobForResult(Result paramResult) {
    RowSetNavigator rowSetNavigator = paramResult.getNavigator();
    if (rowSetNavigator == null) {
      registerLobsForRow((Object[])paramResult.valueData);
    } else {
      while (rowSetNavigator.next()) {
        Object[] arrayOfObject = rowSetNavigator.getCurrent();
        registerLobsForRow(arrayOfObject);
      } 
      rowSetNavigator.reset();
    } 
    this.resultLobs.clear();
  }
  
  private void registerLobsForRow(Object[] paramArrayOfObject) {
    for (byte b = 0; b < paramArrayOfObject.length; b++) {
      if (paramArrayOfObject[b] instanceof BlobDataID) {
        BlobDataID blobDataID = (BlobDataID)paramArrayOfObject[b];
        long l = blobDataID.getId();
        if (l < 0L)
          l = this.resultLobs.get(l); 
        paramArrayOfObject[b] = this.database.lobManager.getBlob(l);
      } else if (paramArrayOfObject[b] instanceof ClobDataID) {
        ClobDataID clobDataID = (ClobDataID)paramArrayOfObject[b];
        long l = clobDataID.getId();
        if (l < 0L)
          l = this.resultLobs.get(l); 
        paramArrayOfObject[b] = this.database.lobManager.getClob(l);
      } 
    } 
  }
  
  ClobData createClobFromFile(String paramString1, String paramString2) {
    File file = getFile(paramString1);
    long l = file.length();
    FileInputStream fileInputStream = null;
    try {
      ClobDataID clobDataID = this.session.createClob(l);
      fileInputStream = new FileInputStream(file);
      InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, paramString2);
      allocateClobSegments(clobDataID.getId(), 0L, inputStreamReader);
      return (ClobData)clobDataID;
    } catch (IOException iOException) {
      throw Error.error(452, iOException.toString());
    } finally {
      try {
        fileInputStream.close();
      } catch (Exception exception) {}
    } 
  }
  
  BlobData createBlobFromFile(String paramString) {
    File file = getFile(paramString);
    long l = file.length();
    FileInputStream fileInputStream = null;
    try {
      BlobDataID blobDataID = this.session.createBlob(l);
      fileInputStream = new FileInputStream(file);
      this.database.lobManager.setBytesForNewBlob(blobDataID.getId(), fileInputStream, l);
      return (BlobData)blobDataID;
    } catch (IOException iOException) {
      throw Error.error(452);
    } finally {
      try {
        fileInputStream.close();
      } catch (Exception exception) {}
    } 
  }
  
  private File getFile(String paramString) {
    this.session.checkAdmin();
    paramString = this.database.logger.getSecurePath(paramString, false, false);
    if (paramString == null)
      throw Error.error(457, paramString); 
    File file = new File(paramString);
    boolean bool = file.exists();
    if (!bool)
      throw Error.error(452); 
    return file;
  }
  
  public void startRowProcessing() {
    if (this.sequenceMap != null)
      this.sequenceMap.clear(); 
  }
  
  public Object getSequenceValue(NumberSequence paramNumberSequence) {
    if (this.sequenceMap == null) {
      this.sequenceMap = new HashMap();
      this.sequenceUpdateMap = new HashMap();
    } 
    HsqlNameManager.HsqlName hsqlName = paramNumberSequence.getName();
    Object object = this.sequenceMap.get(hsqlName);
    if (object == null) {
      object = paramNumberSequence.getValueObject();
      this.sequenceMap.put(hsqlName, object);
      this.sequenceUpdateMap.put(paramNumberSequence, object);
    } 
    return object;
  }
  
  public Object getSequenceCurrent(NumberSequence paramNumberSequence) {
    return (this.sequenceUpdateMap == null) ? null : this.sequenceUpdateMap.get(paramNumberSequence);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\SessionData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */