package org.hsqldb.persist;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.HsqlNameManager;
import org.hsqldb.Session;
import org.hsqldb.Statement;
import org.hsqldb.Table;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlByteArrayInputStream;
import org.hsqldb.lib.LineGroupReader;
import org.hsqldb.map.ValuePool;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultLob;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BlobData;
import org.hsqldb.types.BlobDataID;
import org.hsqldb.types.ClobData;
import org.hsqldb.types.ClobDataID;
import org.hsqldb.types.Collation;

public class LobManager {
  static final String resourceFileName = "/org/hsqldb/resources/lob-schema.sql";
  
  static final String[] starters = new String[] { "/*" };
  
  Database database;
  
  LobStore lobStore;
  
  Session sysLobSession;
  
  volatile boolean storeModified;
  
  byte[] byteBuffer;
  
  Inflater inflater;
  
  Deflater deflater;
  
  byte[] dataBuffer;
  
  boolean cryptLobs;
  
  boolean compressLobs;
  
  int lobBlockSize;
  
  int largeLobBlockSize = 524288;
  
  int totalBlockLimitCount = Integer.MAX_VALUE;
  
  Statement getLob;
  
  Statement getSpanningBlocks;
  
  Statement deleteLobCall;
  
  Statement deleteLobPartCall;
  
  Statement divideLobPartCall;
  
  Statement createLob;
  
  Statement createLobPartCall;
  
  Statement createSingleLobPartCall;
  
  Statement updateLobLength;
  
  Statement updateLobUsage;
  
  Statement getNextLobId;
  
  Statement deleteUnusedLobs;
  
  Statement mergeUnusedSpace;
  
  Statement getLobUseLimit;
  
  Statement getLobCount;
  
  Statement getSpanningParts;
  
  Statement getLastPart;
  
  Statement createPart;
  
  boolean usageChanged;
  
  ReadWriteLock lock = new ReentrantReadWriteLock();
  
  Lock writeLock = this.lock.writeLock();
  
  private static final String existsBlocksSQL = "SELECT * FROM SYSTEM_LOBS.BLOCKS LIMIT 1";
  
  private static final String initialiseBlocksSQL = "INSERT INTO SYSTEM_LOBS.BLOCKS VALUES(?,?,?)";
  
  private static final String getLobSQL = "SELECT * FROM SYSTEM_LOBS.LOB_IDS WHERE LOB_IDS.LOB_ID = ?";
  
  private static final String getLobPartSQL = "SELECT * FROM SYSTEM_LOBS.LOBS WHERE LOBS.LOB_ID = ? AND BLOCK_OFFSET + BLOCK_COUNT > ? AND BLOCK_OFFSET < ? ORDER BY BLOCK_OFFSET";
  
  private static final String deleteLobPartCallSQL = "CALL SYSTEM_LOBS.DELETE_BLOCKS(?,?,?,?)";
  
  private static final String createLobSQL = "INSERT INTO SYSTEM_LOBS.LOB_IDS VALUES(?, ?, ?, ?)";
  
  private static final String updateLobLengthSQL = "UPDATE SYSTEM_LOBS.LOB_IDS SET LOB_LENGTH = ? WHERE LOB_IDS.LOB_ID = ?";
  
  private static final String createLobPartCallSQL = "CALL SYSTEM_LOBS.ALLOC_BLOCKS(?, ?, ?)";
  
  private static final String createSingleLobPartCallSQL = "CALL SYSTEM_LOBS.ALLOC_SINGLE_BLOCK(?, ?, ?)";
  
  private static final String divideLobPartCallSQL = "CALL SYSTEM_LOBS.DIVIDE_BLOCK(?, ?)";
  
  private static final String updateLobUsageSQL = "UPDATE SYSTEM_LOBS.LOB_IDS SET LOB_USAGE_COUNT = LOB_USAGE_COUNT + ? WHERE LOB_IDS.LOB_ID = ?";
  
  private static final String getNextLobIdSQL = "VALUES NEXT VALUE FOR SYSTEM_LOBS.LOB_ID";
  
  private static final String deleteLobCallSQL = "CALL SYSTEM_LOBS.DELETE_LOB(?, ?)";
  
  private static final String deleteUnusedCallSQL = "CALL SYSTEM_LOBS.DELETE_UNUSED_LOBS(?)";
  
  private static final String mergeUnusedSpaceSQL = "CALL SYSTEM_LOBS.MERGE_EMPTY_BLOCKS()";
  
  private static final String getLobUseLimitSQL = "SELECT * FROM SYSTEM_LOBS.LOBS WHERE BLOCK_ADDR = (SELECT MAX(BLOCK_ADDR) FROM SYSTEM_LOBS.LOBS)";
  
  private static final String getLobCountSQL = "SELECT COUNT(*) FROM SYSTEM_LOBS.LOB_IDS";
  
  private static final String getPartsSQL = "SELECT BLOCK_COUNT, BLOCK_OFFSET, PART_OFFSET, PART_LENGTH, PART_BYTES, LOB_ID FROM SYSTEM_LOBS.PARTS WHERE LOB_ID = ?  AND PART_OFFSET + PART_LENGTH > ? AND PART_OFFSET < ? ORDER BY BLOCK_OFFSET";
  
  private static final String getLastPartSQL = "SELECT BLOCK_COUNT, BLOCK_OFFSET, PART_OFFSET, PART_LENGTH, PART_BYTES, LOB_ID FROM SYSTEM_LOBS.PARTS WHERE LOB_ID = ? ORDER BY LOB_ID DESC, BLOCK_OFFSET DESC LIMIT 1";
  
  private static final String createPartSQL = "INSERT INTO SYSTEM_LOBS.PARTS VALUES ?,?,?,?,?,?";
  
  public LobManager(Database paramDatabase) {
    this.database = paramDatabase;
  }
  
  public void lock() {
    this.writeLock.lock();
  }
  
  public void unlock() {
    this.writeLock.unlock();
  }
  
  public void createSchema() {
    this.sysLobSession = this.database.sessionManager.getSysLobSession();
    InputStream inputStream = AccessController.<InputStream>doPrivileged(new PrivilegedAction<InputStream>() {
          public InputStream run() {
            return getClass().getResourceAsStream("/org/hsqldb/resources/lob-schema.sql");
          }
        });
    InputStreamReader inputStreamReader = null;
    try {
      inputStreamReader = new InputStreamReader(inputStream, "ISO-8859-1");
    } catch (Exception exception) {}
    LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
    LineGroupReader lineGroupReader = new LineGroupReader(lineNumberReader, starters);
    HashMappedList hashMappedList = lineGroupReader.getAsMap();
    lineGroupReader.close();
    String str = (String)hashMappedList.get("/*lob_schema_definition*/");
    Statement statement = this.sysLobSession.compileStatement(str);
    Result result = statement.execute(this.sysLobSession);
    if (result.isError())
      throw result.getException(); 
    HsqlNameManager.HsqlName hsqlName = this.database.schemaManager.getSchemaHsqlName("SYSTEM_LOBS");
    Table table = this.database.schemaManager.getTable(this.sysLobSession, "BLOCKS", "SYSTEM_LOBS");
    compileStatements();
  }
  
  public void compileStatements() {
    this.writeLock.lock();
    try {
      this.getLob = this.sysLobSession.compileStatement("SELECT * FROM SYSTEM_LOBS.LOB_IDS WHERE LOB_IDS.LOB_ID = ?");
      this.getSpanningBlocks = this.sysLobSession.compileStatement("SELECT * FROM SYSTEM_LOBS.LOBS WHERE LOBS.LOB_ID = ? AND BLOCK_OFFSET + BLOCK_COUNT > ? AND BLOCK_OFFSET < ? ORDER BY BLOCK_OFFSET");
      this.createLob = this.sysLobSession.compileStatement("INSERT INTO SYSTEM_LOBS.LOB_IDS VALUES(?, ?, ?, ?)");
      this.createLobPartCall = this.sysLobSession.compileStatement("CALL SYSTEM_LOBS.ALLOC_BLOCKS(?, ?, ?)");
      this.createSingleLobPartCall = this.sysLobSession.compileStatement("CALL SYSTEM_LOBS.ALLOC_SINGLE_BLOCK(?, ?, ?)");
      this.divideLobPartCall = this.sysLobSession.compileStatement("CALL SYSTEM_LOBS.DIVIDE_BLOCK(?, ?)");
      this.deleteLobCall = this.sysLobSession.compileStatement("CALL SYSTEM_LOBS.DELETE_LOB(?, ?)");
      this.deleteLobPartCall = this.sysLobSession.compileStatement("CALL SYSTEM_LOBS.DELETE_BLOCKS(?,?,?,?)");
      this.updateLobLength = this.sysLobSession.compileStatement("UPDATE SYSTEM_LOBS.LOB_IDS SET LOB_LENGTH = ? WHERE LOB_IDS.LOB_ID = ?");
      this.updateLobUsage = this.sysLobSession.compileStatement("UPDATE SYSTEM_LOBS.LOB_IDS SET LOB_USAGE_COUNT = LOB_USAGE_COUNT + ? WHERE LOB_IDS.LOB_ID = ?");
      this.getNextLobId = this.sysLobSession.compileStatement("VALUES NEXT VALUE FOR SYSTEM_LOBS.LOB_ID");
      this.deleteUnusedLobs = this.sysLobSession.compileStatement("CALL SYSTEM_LOBS.DELETE_UNUSED_LOBS(?)");
      this.mergeUnusedSpace = this.sysLobSession.compileStatement("CALL SYSTEM_LOBS.MERGE_EMPTY_BLOCKS()");
      this.getLobUseLimit = this.sysLobSession.compileStatement("SELECT * FROM SYSTEM_LOBS.LOBS WHERE BLOCK_ADDR = (SELECT MAX(BLOCK_ADDR) FROM SYSTEM_LOBS.LOBS)");
      this.getLobCount = this.sysLobSession.compileStatement("SELECT COUNT(*) FROM SYSTEM_LOBS.LOB_IDS");
      this.getSpanningParts = this.sysLobSession.compileStatement("SELECT BLOCK_COUNT, BLOCK_OFFSET, PART_OFFSET, PART_LENGTH, PART_BYTES, LOB_ID FROM SYSTEM_LOBS.PARTS WHERE LOB_ID = ?  AND PART_OFFSET + PART_LENGTH > ? AND PART_OFFSET < ? ORDER BY BLOCK_OFFSET");
      this.getLastPart = this.sysLobSession.compileStatement("SELECT BLOCK_COUNT, BLOCK_OFFSET, PART_OFFSET, PART_LENGTH, PART_BYTES, LOB_ID FROM SYSTEM_LOBS.PARTS WHERE LOB_ID = ? ORDER BY LOB_ID DESC, BLOCK_OFFSET DESC LIMIT 1");
      this.createPart = this.sysLobSession.compileStatement("INSERT INTO SYSTEM_LOBS.PARTS VALUES ?,?,?,?,?,?");
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  private void initialiseLobSpace() {
    Statement statement = this.sysLobSession.compileStatement("SELECT * FROM SYSTEM_LOBS.BLOCKS LIMIT 1");
    Result result = statement.execute(this.sysLobSession);
    if (result.isError())
      throw result.getException(); 
    RowSetNavigator rowSetNavigator = result.getNavigator();
    int i = rowSetNavigator.getSize();
    if (i > 0)
      return; 
    statement = this.sysLobSession.compileStatement("INSERT INTO SYSTEM_LOBS.BLOCKS VALUES(?,?,?)");
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = ValuePool.INTEGER_0;
    arrayOfObject[1] = ValuePool.getInt(this.totalBlockLimitCount);
    arrayOfObject[2] = ValuePool.getLong(0L);
    this.sysLobSession.executeCompiledStatement(statement, arrayOfObject, 0);
  }
  
  public void open() {
    this.lobBlockSize = this.database.logger.getLobBlockSize();
    this.cryptLobs = this.database.logger.cryptLobs;
    this.compressLobs = this.database.logger.propCompressLobs;
    if (this.compressLobs || this.cryptLobs) {
      int i = this.largeLobBlockSize + 4096;
      this.inflater = new Inflater();
      this.deflater = new Deflater(1);
      this.dataBuffer = new byte[i];
    } 
    if (this.database.getType() == "res:") {
      this.lobStore = new LobStoreInJar(this.database, this.lobBlockSize);
    } else if (this.database.getType() == "file:") {
      this.lobStore = new LobStoreRAFile(this.database, this.lobBlockSize);
      if (!this.database.isFilesReadOnly()) {
        this.byteBuffer = new byte[this.lobBlockSize];
        initialiseLobSpace();
      } 
    } else {
      this.lobStore = new LobStoreMem(this.lobBlockSize);
      this.byteBuffer = new byte[this.lobBlockSize];
      initialiseLobSpace();
    } 
  }
  
  public void close() {
    if (this.lobStore != null)
      this.lobStore.close(); 
    this.lobStore = null;
  }
  
  public LobStore getLobStore() {
    if (this.lobStore == null)
      open(); 
    return this.lobStore;
  }
  
  private Long getNewLobID() {
    Result result = this.getNextLobId.execute(this.sysLobSession);
    if (result.isError())
      return Long.valueOf(0L); 
    RowSetNavigator rowSetNavigator = result.getNavigator();
    boolean bool = rowSetNavigator.next();
    if (!bool) {
      rowSetNavigator.release();
      return Long.valueOf(0L);
    } 
    Object[] arrayOfObject = rowSetNavigator.getCurrent();
    rowSetNavigator.release();
    return (Long)arrayOfObject[0];
  }
  
  private Object[] getLobHeader(long paramLong) {
    ResultMetaData resultMetaData = this.getLob.getParametersMetaData();
    Object[] arrayOfObject1 = new Object[resultMetaData.getColumnCount()];
    arrayOfObject1[0] = ValuePool.getLong(paramLong);
    this.sysLobSession.sessionContext.pushDynamicArguments(arrayOfObject1);
    Result result = this.getLob.execute(this.sysLobSession);
    this.sysLobSession.sessionContext.pop();
    if (result.isError())
      throw result.getException(); 
    RowSetNavigator rowSetNavigator = result.getNavigator();
    boolean bool = rowSetNavigator.next();
    Object[] arrayOfObject2 = null;
    if (bool)
      arrayOfObject2 = rowSetNavigator.getCurrent(); 
    rowSetNavigator.release();
    return arrayOfObject2;
  }
  
  public BlobData getBlob(long paramLong) {
    this.writeLock.lock();
    try {
      Object[] arrayOfObject = getLobHeader(paramLong);
      if (arrayOfObject == null)
        return null; 
      BlobDataID blobDataID = new BlobDataID(paramLong);
      return (BlobData)blobDataID;
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public ClobData getClob(long paramLong) {
    this.writeLock.lock();
    try {
      Object[] arrayOfObject = getLobHeader(paramLong);
      if (arrayOfObject == null)
        return null; 
      ClobDataID clobDataID = new ClobDataID(paramLong);
      return (ClobData)clobDataID;
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public long createBlob(Session paramSession, long paramLong) {
    this.writeLock.lock();
    try {
      Long long_ = getNewLobID();
      ResultMetaData resultMetaData = this.createLob.getParametersMetaData();
      Object[] arrayOfObject = new Object[resultMetaData.getColumnCount()];
      arrayOfObject[0] = long_;
      arrayOfObject[1] = ValuePool.getLong(paramLong);
      arrayOfObject[2] = ValuePool.INTEGER_0;
      arrayOfObject[3] = ValuePool.getInt(30);
      Result result = this.sysLobSession.executeCompiledStatement(this.createLob, arrayOfObject, 0);
      this.usageChanged = true;
      return long_.longValue();
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public long createClob(Session paramSession, long paramLong) {
    this.writeLock.lock();
    try {
      Long long_ = getNewLobID();
      ResultMetaData resultMetaData = this.createLob.getParametersMetaData();
      Object[] arrayOfObject = new Object[resultMetaData.getColumnCount()];
      arrayOfObject[0] = long_;
      arrayOfObject[1] = ValuePool.getLong(paramLong);
      arrayOfObject[2] = ValuePool.INTEGER_0;
      arrayOfObject[3] = ValuePool.getInt(40);
      Result result = this.sysLobSession.executeCompiledStatement(this.createLob, arrayOfObject, 0);
      this.usageChanged = true;
      return long_.longValue();
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public Result deleteLob(long paramLong) {
    this.writeLock.lock();
    try {
      ResultMetaData resultMetaData = this.deleteLobCall.getParametersMetaData();
      Object[] arrayOfObject = new Object[resultMetaData.getColumnCount()];
      arrayOfObject[0] = ValuePool.getLong(paramLong);
      arrayOfObject[1] = ValuePool.getLong(0L);
      Result result = this.sysLobSession.executeCompiledStatement(this.deleteLobCall, arrayOfObject, 0);
      this.usageChanged = true;
      return result;
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public Result deleteUnusedLobs() {
    this.writeLock.lock();
    try {
      if (this.lobStore == null || this.byteBuffer == null || !this.usageChanged)
        return Result.updateZeroResult; 
      Session[] arrayOfSession = this.database.sessionManager.getAllSessions();
      long l1 = Long.MAX_VALUE;
      for (byte b = 0; b < arrayOfSession.length; b++) {
        if (!arrayOfSession[b].isClosed()) {
          long l = (arrayOfSession[b]).sessionData.getFirstLobID();
          if (l != 0L && l < l1)
            l1 = l; 
        } 
      } 
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = new Long(l1);
      Result result = this.sysLobSession.executeCompiledStatement(this.deleteUnusedLobs, arrayOfObject1, 0);
      if (result.isError())
        return result; 
      result = this.sysLobSession.executeCompiledStatement(this.mergeUnusedSpace, ValuePool.emptyObjectArray, 0);
      if (result.isError())
        return result; 
      result = this.sysLobSession.executeCompiledStatement(this.getLobUseLimit, ValuePool.emptyObjectArray, 0);
      if (result.isError())
        return result; 
      this.usageChanged = false;
      RowSetNavigator rowSetNavigator = result.getNavigator();
      boolean bool = rowSetNavigator.next();
      if (!bool) {
        rowSetNavigator.release();
        return Result.updateOneResult;
      } 
      Object[] arrayOfObject2 = rowSetNavigator.getCurrent();
      if (arrayOfObject2[0] == null || arrayOfObject2[1] == null)
        return Result.updateOneResult; 
      long l2 = (((Integer)arrayOfObject2[0]).intValue() + ((Integer)arrayOfObject2[1]).intValue());
      l2 *= this.lobBlockSize;
      long l3 = this.lobStore.getLength();
      if (l3 > l2) {
        this.database.logger.logInfoEvent("lob file truncated to usage");
        this.lobStore.setLength(l2);
        try {
          this.lobStore.synch();
        } catch (Throwable throwable) {}
      } else if (l3 < l2) {
        this.database.logger.logInfoEvent("lob file reported smaller than usage");
      } 
      return Result.updateOneResult;
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public Result getLength(long paramLong) {
    this.writeLock.lock();
    try {
      Object[] arrayOfObject = getLobHeader(paramLong);
      if (arrayOfObject == null)
        throw Error.error(3474); 
      long l = ((Long)arrayOfObject[1]).longValue();
      int i = ((Integer)arrayOfObject[3]).intValue();
      return (Result)ResultLob.newLobSetResponse(paramLong, l);
    } catch (HsqlException hsqlException) {
      return Result.newErrorResult((Throwable)hsqlException);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public int compare(BlobData paramBlobData, byte[] paramArrayOfbyte) {
    this.writeLock.lock();
    try {
      Object[] arrayOfObject = getLobHeader(paramBlobData.getId());
      long l = ((Long)arrayOfObject[1]).longValue();
      int[][] arrayOfInt = getBlockAddresses(paramBlobData.getId(), 0, 2147483647);
      byte b1 = 0;
      int i = 0;
      byte b2 = 0;
      if (l == 0L)
        return (paramArrayOfbyte.length == 0) ? 0 : -1; 
      if (paramArrayOfbyte.length == 0)
        return 1; 
      do {
        int j = arrayOfInt[b1][0] + b2;
        byte[] arrayOfByte = getLobStore().getBlockBytes(j, 1);
        byte b = 0;
        while (b < arrayOfByte.length) {
          if (i + b >= paramArrayOfbyte.length) {
            if (l == paramArrayOfbyte.length)
              return 0; 
            return 1;
          } 
          if (arrayOfByte[b] == paramArrayOfbyte[i + b]) {
            b++;
            continue;
          } 
          return ((arrayOfByte[b] & 0xFF) > (paramArrayOfbyte[i + b] & 0xFF)) ? 1 : -1;
        } 
        b2++;
        i += this.lobBlockSize;
        if (b2 != arrayOfInt[b1][1])
          continue; 
        b2 = 0;
        b1++;
      } while (b1 != arrayOfInt.length && i < paramArrayOfbyte.length);
      if (l == paramArrayOfbyte.length)
        return 0; 
      return (l > paramArrayOfbyte.length) ? 1 : -1;
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public int compare(BlobData paramBlobData1, BlobData paramBlobData2) {
    if (paramBlobData1.getId() == paramBlobData2.getId())
      return 0; 
    this.writeLock.lock();
    try {
      if (this.compressLobs || this.cryptLobs)
        return compareBytesCompressed(paramBlobData1.getId(), paramBlobData2.getId()); 
      return compareBytesNormal(paramBlobData1.getId(), paramBlobData2.getId());
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public int compare(Collation paramCollation, ClobData paramClobData, String paramString) {
    this.writeLock.lock();
    try {
      Object[] arrayOfObject = getLobHeader(paramClobData.getId());
      long l = ((Long)arrayOfObject[1]).longValue();
      int[][] arrayOfInt = getBlockAddresses(paramClobData.getId(), 0, 2147483647);
      byte b1 = 0;
      int i = 0;
      byte b2 = 0;
      if (l == 0L)
        return (paramString.length() == 0) ? 0 : -1; 
      if (paramString.length() == 0)
        return 1; 
      do {
        int j = arrayOfInt[b1][0] + b2;
        byte[] arrayOfByte = getLobStore().getBlockBytes(j, 1);
        long l1 = l - (arrayOfInt[b1][2] + b2) * this.lobBlockSize / 2L;
        if (l1 > (this.lobBlockSize / 2))
          l1 = (this.lobBlockSize / 2); 
        String str1 = new String(ArrayUtil.byteArrayToChars(arrayOfByte), 0, (int)l1);
        int k = paramString.length() - i;
        if (k > this.lobBlockSize / 2)
          k = this.lobBlockSize / 2; 
        String str2 = paramString.substring(i, i + k);
        int m = paramCollation.compare(str1, str2);
        if (m != 0)
          return m; 
        b2++;
        i += this.lobBlockSize / 2;
        if (b2 != arrayOfInt[b1][1])
          continue; 
        b2 = 0;
        b1++;
      } while (b1 != arrayOfInt.length && i < paramString.length());
      if (l == paramString.length())
        return 0; 
      return (l > paramString.length()) ? 1 : -1;
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public int compare(Collation paramCollation, ClobData paramClobData1, ClobData paramClobData2) {
    if (paramClobData1.getId() == paramClobData2.getId())
      return 0; 
    this.writeLock.lock();
    try {
      if (this.compressLobs || this.cryptLobs)
        return compareTextCompressed(paramCollation, paramClobData1.getId(), paramClobData2.getId()); 
      return compareTextNormal(paramCollation, paramClobData1.getId(), paramClobData2.getId());
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  private int compareBytesNormal(long paramLong1, long paramLong2) {
    Object[] arrayOfObject = getLobHeader(paramLong1);
    long l1 = ((Long)arrayOfObject[1]).longValue();
    arrayOfObject = getLobHeader(paramLong2);
    long l2 = ((Long)arrayOfObject[1]).longValue();
    int[][] arrayOfInt1 = getBlockAddresses(paramLong1, 0, 2147483647);
    int[][] arrayOfInt2 = getBlockAddresses(paramLong2, 0, 2147483647);
    byte b1 = 0;
    byte b2 = 0;
    byte b3 = 0;
    byte b4 = 0;
    if (l1 == 0L)
      return (l2 == 0L) ? 0 : -1; 
    if (l2 == 0L)
      return 1; 
    do {
      int i = arrayOfInt1[b1][0] + b3;
      int j = arrayOfInt2[b2][0] + b4;
      byte[] arrayOfByte1 = getLobStore().getBlockBytes(i, 1);
      byte[] arrayOfByte2 = getLobStore().getBlockBytes(j, 1);
      int k = ArrayUtil.compare(arrayOfByte1, arrayOfByte2);
      if (k != 0)
        return k; 
      b3++;
      b4++;
      if (b3 == arrayOfInt1[b1][1]) {
        b3 = 0;
        b1++;
      } 
      if (b4 != arrayOfInt2[b2][1])
        continue; 
      b4 = 0;
      b2++;
    } while (b1 != arrayOfInt1.length && b2 != arrayOfInt2.length);
    return (l1 == l2) ? 0 : ((l1 > l2) ? 1 : -1);
  }
  
  private int compareTextNormal(Collation paramCollation, long paramLong1, long paramLong2) {
    Object[] arrayOfObject = getLobHeader(paramLong1);
    long l1 = ((Long)arrayOfObject[1]).longValue();
    arrayOfObject = getLobHeader(paramLong2);
    long l2 = ((Long)arrayOfObject[1]).longValue();
    int[][] arrayOfInt1 = getBlockAddresses(paramLong1, 0, 2147483647);
    int[][] arrayOfInt2 = getBlockAddresses(paramLong2, 0, 2147483647);
    byte b1 = 0;
    byte b2 = 0;
    byte b3 = 0;
    byte b4 = 0;
    if (l1 == 0L)
      return (l2 == 0L) ? 0 : -1; 
    if (l2 == 0L)
      return 1; 
    do {
      int i = arrayOfInt1[b1][0] + b3;
      int j = arrayOfInt2[b2][0] + b4;
      byte[] arrayOfByte1 = getLobStore().getBlockBytes(i, 1);
      byte[] arrayOfByte2 = getLobStore().getBlockBytes(j, 1);
      long l3 = l1 - (arrayOfInt1[b1][2] + b3) * this.lobBlockSize / 2L;
      if (l3 > (this.lobBlockSize / 2))
        l3 = (this.lobBlockSize / 2); 
      long l4 = l2 - (arrayOfInt2[b2][2] + b4) * this.lobBlockSize / 2L;
      if (l4 > (this.lobBlockSize / 2))
        l4 = (this.lobBlockSize / 2); 
      String str1 = new String(ArrayUtil.byteArrayToChars(arrayOfByte1), 0, (int)l3);
      String str2 = new String(ArrayUtil.byteArrayToChars(arrayOfByte2), 0, (int)l4);
      int k = paramCollation.compare(str1, str2);
      if (k != 0)
        return k; 
      b3++;
      b4++;
      if (b3 == arrayOfInt1[b1][1]) {
        b3 = 0;
        b1++;
      } 
      if (b4 != arrayOfInt2[b2][1])
        continue; 
      b4 = 0;
      b2++;
    } while (b1 != arrayOfInt1.length && b2 != arrayOfInt2.length);
    return (l1 == l2) ? 0 : ((l1 > l2) ? 1 : -1);
  }
  
  public Result getLob(long paramLong1, long paramLong2, long paramLong3) {
    if (paramLong2 == 0L)
      return createDuplicateLob(paramLong1, paramLong3); 
    throw Error.runtimeError(201, "LobManager");
  }
  
  public Result createDuplicateLob(long paramLong) {
    Result result = getLength(paramLong);
    return result.isError() ? result : createDuplicateLob(paramLong, ((ResultLob)result).getBlockLength());
  }
  
  public Result createDuplicateLob(long paramLong1, long paramLong2) {
    if (this.byteBuffer == null)
      throw Error.error(456); 
    this.writeLock.lock();
    try {
      Object[] arrayOfObject1 = getLobHeader(paramLong1);
      if (arrayOfObject1 == null)
        return Result.newErrorResult((Throwable)Error.error(3474)); 
      Long long_ = getNewLobID();
      Object[] arrayOfObject2 = new Object[arrayOfObject1.length];
      arrayOfObject2[0] = long_;
      arrayOfObject2[1] = Long.valueOf(paramLong2);
      arrayOfObject2[2] = ValuePool.INTEGER_0;
      arrayOfObject2[3] = arrayOfObject1[3];
      Result result = this.sysLobSession.executeCompiledStatement(this.createLob, arrayOfObject2, 0);
      if (result.isError())
        return result; 
      this.usageChanged = true;
      if (paramLong2 == 0L)
        return (Result)ResultLob.newLobSetResponse(long_.longValue(), paramLong2); 
      long l = paramLong2;
      int i = ((Integer)arrayOfObject1[3]).intValue();
      if (i == 40)
        l *= 2L; 
      int j = (int)(l / this.lobBlockSize);
      if (l % this.lobBlockSize != 0L)
        j++; 
      createBlockAddresses(long_.longValue(), 0, j);
      int[][] arrayOfInt1 = getBlockAddresses(paramLong1, 0, 2147483647);
      int[][] arrayOfInt2 = getBlockAddresses(long_.longValue(), 0, 2147483647);
      try {
        copyBlockSet(arrayOfInt1, arrayOfInt2);
      } catch (HsqlException hsqlException) {
        return Result.newErrorResult((Throwable)hsqlException);
      } 
      int k = (int)(l % this.lobBlockSize);
      if (k != 0) {
        int[] arrayOfInt = arrayOfInt2[arrayOfInt2.length - 1];
        int m = arrayOfInt[0] + arrayOfInt[1] - 1;
        byte[] arrayOfByte = getLobStore().getBlockBytes(m, 1);
        ArrayUtil.fillArray(arrayOfByte, k, (byte)0);
        getLobStore().setBlockBytes(arrayOfByte, m, 1);
      } 
      return (Result)ResultLob.newLobSetResponse(long_.longValue(), paramLong2);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public Result getTruncateLength(long paramLong) {
    this.writeLock.lock();
    try {
      Object[] arrayOfObject = getLobHeader(paramLong);
      if (arrayOfObject == null)
        throw Error.error(3474); 
      long l = ((Long)arrayOfObject[1]).longValue();
      int i = ((Integer)arrayOfObject[3]).intValue();
      return (Result)ResultLob.newLobSetResponse(paramLong, l);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  private void copyBlockSet(int[][] paramArrayOfint1, int[][] paramArrayOfint2) {
    byte b1 = 0;
    byte b2 = 0;
    byte b3 = 0;
    byte b4 = 0;
    do {
      byte[] arrayOfByte = getLobStore().getBlockBytes(paramArrayOfint1[b1][0] + b3, 1);
      getLobStore().setBlockBytes(arrayOfByte, paramArrayOfint2[b2][0] + b4, 1);
      b3++;
      b4++;
      if (b3 == paramArrayOfint1[b1][1]) {
        b3 = 0;
        b1++;
      } 
      if (b4 != paramArrayOfint2[b2][1])
        continue; 
      b4 = 0;
      b2++;
    } while (b1 != paramArrayOfint1.length && b2 != paramArrayOfint2.length);
    this.storeModified = true;
  }
  
  public Result getChars(long paramLong1, long paramLong2, int paramInt) {
    Result result;
    this.writeLock.lock();
    try {
      if (this.compressLobs || this.cryptLobs) {
        result = getBytesCompressed(paramLong1, paramLong2 * 2L, paramInt * 2, true);
      } else {
        result = getBytesNormal(paramLong1, paramLong2 * 2L, paramInt * 2);
      } 
    } finally {
      this.writeLock.unlock();
    } 
    if (result.isError())
      return result; 
    byte[] arrayOfByte = ((ResultLob)result).getByteArray();
    char[] arrayOfChar = ArrayUtil.byteArrayToChars(arrayOfByte);
    return (Result)ResultLob.newLobGetCharsResponse(paramLong1, paramLong2, arrayOfChar);
  }
  
  public Result getBytes(long paramLong1, long paramLong2, int paramInt) {
    this.writeLock.lock();
    try {
      if (this.compressLobs || this.cryptLobs)
        return getBytesCompressed(paramLong1, paramLong2, paramInt, false); 
      return getBytesNormal(paramLong1, paramLong2, paramInt);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  private Result getBytesNormal(long paramLong1, long paramLong2, int paramInt) {
    byte[] arrayOfByte2;
    int i = (int)(paramLong2 / this.lobBlockSize);
    int j = (int)(paramLong2 % this.lobBlockSize);
    int k = (int)((paramLong2 + paramInt) / this.lobBlockSize);
    int m = (int)((paramLong2 + paramInt) % this.lobBlockSize);
    if (m == 0) {
      m = this.lobBlockSize;
    } else {
      k++;
    } 
    if (paramInt == 0)
      return (Result)ResultLob.newLobGetBytesResponse(paramLong1, paramLong2, BinaryData.zeroLengthBytes); 
    int n = 0;
    byte[] arrayOfByte1 = new byte[paramInt];
    int[][] arrayOfInt = getBlockAddresses(paramLong1, i, k);
    if (arrayOfInt.length == 0)
      return Result.newErrorResult((Throwable)Error.error(3474)); 
    byte b = 0;
    int i1 = arrayOfInt[b][1] + arrayOfInt[b][2] - i;
    if (arrayOfInt[b][1] + arrayOfInt[b][2] > k)
      i1 -= arrayOfInt[b][1] + arrayOfInt[b][2] - k; 
    try {
      arrayOfByte2 = getLobStore().getBlockBytes(arrayOfInt[b][0] - arrayOfInt[b][2] + i, i1);
    } catch (HsqlException hsqlException) {
      return Result.newErrorResult((Throwable)hsqlException);
    } 
    int i2 = this.lobBlockSize * i1 - j;
    if (i2 > paramInt)
      i2 = paramInt; 
    System.arraycopy(arrayOfByte2, j, arrayOfByte1, n, i2);
    n += i2;
    while (++b < arrayOfInt.length && n < paramInt) {
      i1 = arrayOfInt[b][1];
      if (arrayOfInt[b][1] + arrayOfInt[b][2] > k)
        i1 -= arrayOfInt[b][1] + arrayOfInt[b][2] - k; 
      try {
        arrayOfByte2 = getLobStore().getBlockBytes(arrayOfInt[b][0], i1);
      } catch (HsqlException hsqlException) {
        return Result.newErrorResult((Throwable)hsqlException);
      } 
      i2 = this.lobBlockSize * i1;
      if (i2 > paramInt - n)
        i2 = paramInt - n; 
      System.arraycopy(arrayOfByte2, 0, arrayOfByte1, n, i2);
      n += i2;
      b++;
    } 
    return (Result)ResultLob.newLobGetBytesResponse(paramLong1, paramLong2, arrayOfByte1);
  }
  
  private Result setBytesBA(long paramLong1, long paramLong2, byte[] paramArrayOfbyte, int paramInt, boolean paramBoolean) {
    if (paramInt == 0)
      return (Result)ResultLob.newLobSetResponse(paramLong1, 0L); 
    this.writeLock.lock();
    try {
      if (this.compressLobs || this.cryptLobs)
        return setBytesBACompressed(paramLong1, paramLong2, paramArrayOfbyte, paramInt, paramBoolean); 
      return setBytesBANormal(paramLong1, paramLong2, paramArrayOfbyte, paramInt);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  private Result setBytesBANormal(long paramLong1, long paramLong2, byte[] paramArrayOfbyte, int paramInt) {
    boolean bool = false;
    int i = (int)(paramLong2 / this.lobBlockSize);
    int j = (int)(paramLong2 % this.lobBlockSize);
    int k = (int)((paramLong2 + paramInt) / this.lobBlockSize);
    int m = (int)((paramLong2 + paramInt) % this.lobBlockSize);
    if (m == 0) {
      m = this.lobBlockSize;
    } else {
      k++;
    } 
    int[][] arrayOfInt = getBlockAddresses(paramLong1, i, k);
    int n = i;
    if (arrayOfInt.length > 0)
      n = arrayOfInt[arrayOfInt.length - 1][2] + arrayOfInt[arrayOfInt.length - 1][1]; 
    if (n < k) {
      createBlockAddresses(paramLong1, n, k - n);
      arrayOfInt = getBlockAddresses(paramLong1, i, k);
      bool = true;
    } 
    int i1 = 0;
    int i2 = paramInt;
    try {
      for (byte b = 0; b < arrayOfInt.length; b++) {
        long l1 = arrayOfInt[b][2] * this.lobBlockSize;
        long l2 = arrayOfInt[b][1] * this.lobBlockSize;
        long l3 = arrayOfInt[b][0] * this.lobBlockSize;
        int i3 = 0;
        if (paramLong2 > l1) {
          l2 -= paramLong2 - l1;
          l3 += paramLong2 - l1;
        } 
        if (i2 < l2) {
          if (bool)
            i3 = (int)((l2 - i2) % this.lobBlockSize); 
          l2 = i2;
        } 
        getLobStore().setBlockBytes(paramArrayOfbyte, l3, i1, (int)l2);
        if (i3 != 0) {
          ArrayUtil.fillArray(this.byteBuffer, 0, (byte)0);
          getLobStore().setBlockBytes(this.byteBuffer, l3 + l2, 0, i3);
        } 
        i1 = (int)(i1 + l2);
        i2 = (int)(i2 - l2);
      } 
    } catch (HsqlException hsqlException) {
      return Result.newErrorResult((Throwable)hsqlException);
    } 
    this.storeModified = true;
    return (Result)ResultLob.newLobSetResponse(paramLong1, paramInt);
  }
  
  private Result setBytesIS(long paramLong1, InputStream paramInputStream, long paramLong2, boolean paramBoolean) {
    return (Result)((paramLong2 == 0L) ? ResultLob.newLobSetResponse(paramLong1, 0L) : ((this.compressLobs || this.cryptLobs) ? setBytesISCompressed(paramLong1, paramInputStream, paramLong2, paramBoolean) : setBytesISNormal(paramLong1, paramInputStream, paramLong2)));
  }
  
  private Result setBytesISNormal(long paramLong1, InputStream paramInputStream, long paramLong2) {
    long l = 0L;
    int i = (int)(paramLong2 / this.lobBlockSize);
    int j = (int)(paramLong2 % this.lobBlockSize);
    if (j == 0) {
      j = this.lobBlockSize;
    } else {
      i++;
    } 
    createBlockAddresses(paramLong1, 0, i);
    int[][] arrayOfInt = getBlockAddresses(paramLong1, 0, i);
    for (byte b = 0; b < arrayOfInt.length; b++) {
      for (byte b1 = 0; b1 < arrayOfInt[b][1]; b1++) {
        int k = this.lobBlockSize;
        ArrayUtil.fillArray(this.byteBuffer, 0, (byte)0);
        if (b == arrayOfInt.length - 1 && b1 == arrayOfInt[b][1] - 1)
          k = j; 
        try {
          int m;
          for (m = 0; k > 0; m += n) {
            int n = paramInputStream.read(this.byteBuffer, m, k);
            if (n == -1)
              return Result.newErrorResult(new EOFException()); 
            k -= n;
          } 
          l += m;
        } catch (IOException iOException) {
          return Result.newErrorResult(iOException);
        } 
        try {
          getLobStore().setBlockBytes(this.byteBuffer, arrayOfInt[b][0] + b1, 1);
        } catch (HsqlException hsqlException) {
          return Result.newErrorResult((Throwable)hsqlException);
        } 
      } 
    } 
    this.storeModified = true;
    return (Result)ResultLob.newLobSetResponse(paramLong1, l);
  }
  
  public Result setBytes(long paramLong1, long paramLong2, byte[] paramArrayOfbyte, int paramInt) {
    if (this.byteBuffer == null)
      throw Error.error(456); 
    this.writeLock.lock();
    try {
      Object[] arrayOfObject = getLobHeader(paramLong1);
      if (arrayOfObject == null)
        return Result.newErrorResult((Throwable)Error.error(3474)); 
      long l = ((Long)arrayOfObject[1]).longValue();
      if (paramInt == 0)
        return (Result)ResultLob.newLobSetResponse(paramLong1, l); 
      Result result = setBytesBA(paramLong1, paramLong2, paramArrayOfbyte, paramInt, false);
      if (result.isError())
        return result; 
      if (paramLong2 + paramInt > l) {
        l = paramLong2 + paramInt;
        result = setLength(paramLong1, l);
        if (result.isError())
          return result; 
      } 
      return (Result)ResultLob.newLobSetResponse(paramLong1, l);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public Result setBytesForNewBlob(long paramLong1, InputStream paramInputStream, long paramLong2) {
    if (paramLong2 == 0L)
      return (Result)ResultLob.newLobSetResponse(paramLong1, 0L); 
    if (this.byteBuffer == null)
      throw Error.error(456); 
    this.writeLock.lock();
    try {
      Result result = setBytesIS(paramLong1, paramInputStream, paramLong2, false);
      return result;
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public Result setChars(long paramLong1, long paramLong2, char[] paramArrayOfchar, int paramInt) {
    if (this.byteBuffer == null)
      throw Error.error(456); 
    this.writeLock.lock();
    try {
      Object[] arrayOfObject = getLobHeader(paramLong1);
      if (arrayOfObject == null)
        return Result.newErrorResult((Throwable)Error.error(3474)); 
      long l = ((Long)arrayOfObject[1]).longValue();
      if (paramInt == 0)
        return (Result)ResultLob.newLobSetResponse(paramLong1, l); 
      byte[] arrayOfByte = ArrayUtil.charArrayToBytes(paramArrayOfchar, paramInt);
      Result result = setBytesBA(paramLong1, paramLong2 * 2L, arrayOfByte, paramInt * 2, true);
      if (result.isError())
        return result; 
      if (paramLong2 + paramInt > l) {
        l = paramLong2 + paramInt;
        result = setLength(paramLong1, l);
        if (result.isError())
          return result; 
      } 
      return (Result)ResultLob.newLobSetResponse(paramLong1, l);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public Result setCharsForNewClob(long paramLong1, InputStream paramInputStream, long paramLong2) {
    if (paramLong2 == 0L)
      return (Result)ResultLob.newLobSetResponse(paramLong1, 0L); 
    if (this.byteBuffer == null)
      throw Error.error(456); 
    this.writeLock.lock();
    try {
      Result result = setBytesIS(paramLong1, paramInputStream, paramLong2 * 2L, false);
      if (result.isError())
        return result; 
      long l = ((ResultLob)result).getBlockLength();
      if (l < paramLong2)
        Result result1 = truncate(paramLong1, l); 
      return result;
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public Result truncate(long paramLong1, long paramLong2) {
    if (this.byteBuffer == null)
      throw Error.error(456); 
    this.writeLock.lock();
    try {
      Object[] arrayOfObject1 = getLobHeader(paramLong1);
      if (arrayOfObject1 == null)
        return Result.newErrorResult((Throwable)Error.error(3474)); 
      long l1 = ((Long)arrayOfObject1[1]).longValue();
      long l2 = paramLong2;
      if (((Integer)arrayOfObject1[3]).intValue() == 40)
        l2 *= 2L; 
      int i = (int)((l2 + this.lobBlockSize - 1L) / this.lobBlockSize);
      ResultMetaData resultMetaData = this.deleteLobPartCall.getParametersMetaData();
      Object[] arrayOfObject2 = new Object[resultMetaData.getColumnCount()];
      arrayOfObject2[0] = ValuePool.getLong(paramLong1);
      arrayOfObject2[1] = Integer.valueOf(i);
      arrayOfObject2[2] = ValuePool.INTEGER_MAX;
      arrayOfObject2[3] = ValuePool.getLong(this.sysLobSession.getTransactionTimestamp());
      Result result = this.sysLobSession.executeCompiledStatement(this.deleteLobPartCall, arrayOfObject2, 0);
      setLength(paramLong1, paramLong2);
      return (Result)ResultLob.newLobTruncateResponse(paramLong1, paramLong2);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  private Result setLength(long paramLong1, long paramLong2) {
    ResultMetaData resultMetaData = this.updateLobLength.getParametersMetaData();
    Object[] arrayOfObject = new Object[resultMetaData.getColumnCount()];
    arrayOfObject[0] = ValuePool.getLong(paramLong2);
    arrayOfObject[1] = ValuePool.getLong(paramLong1);
    return this.sysLobSession.executeCompiledStatement(this.updateLobLength, arrayOfObject, 0);
  }
  
  public Result adjustUsageCount(Session paramSession, long paramLong, int paramInt) {
    ResultMetaData resultMetaData = this.updateLobUsage.getParametersMetaData();
    Object[] arrayOfObject = new Object[resultMetaData.getColumnCount()];
    arrayOfObject[0] = ValuePool.getInt(paramInt);
    arrayOfObject[1] = ValuePool.getLong(paramLong);
    paramSession.sessionContext.pushDynamicArguments(arrayOfObject);
    Result result = this.updateLobUsage.execute(paramSession);
    paramSession.sessionContext.pop();
    return result;
  }
  
  private int[][] getBlockAddresses(long paramLong, int paramInt1, int paramInt2) {
    ResultMetaData resultMetaData = this.getSpanningBlocks.getParametersMetaData();
    Object[] arrayOfObject = new Object[resultMetaData.getColumnCount()];
    arrayOfObject[0] = ValuePool.getLong(paramLong);
    arrayOfObject[1] = ValuePool.getInt(paramInt1);
    arrayOfObject[2] = ValuePool.getInt(paramInt2);
    this.sysLobSession.sessionContext.pushDynamicArguments(arrayOfObject);
    Result result = this.getSpanningBlocks.execute(this.sysLobSession);
    this.sysLobSession.sessionContext.pop();
    RowSetNavigator rowSetNavigator = result.getNavigator();
    int i = rowSetNavigator.getSize();
    int[][] arrayOfInt = new int[i][3];
    for (byte b = 0; b < i; b++) {
      rowSetNavigator.absolute(b);
      Object[] arrayOfObject1 = rowSetNavigator.getCurrent();
      arrayOfInt[b][0] = ((Integer)arrayOfObject1[0]).intValue();
      arrayOfInt[b][1] = ((Integer)arrayOfObject1[1]).intValue();
      arrayOfInt[b][2] = ((Integer)arrayOfObject1[2]).intValue();
    } 
    rowSetNavigator.release();
    return arrayOfInt;
  }
  
  private void deleteBlockAddresses(long paramLong, int paramInt1, int paramInt2) {
    ResultMetaData resultMetaData = this.deleteLobPartCall.getParametersMetaData();
    Object[] arrayOfObject = new Object[resultMetaData.getColumnCount()];
    arrayOfObject[0] = ValuePool.getLong(paramLong);
    arrayOfObject[1] = ValuePool.getInt(paramInt1);
    arrayOfObject[2] = ValuePool.getInt(paramInt2);
    arrayOfObject[3] = ValuePool.getLong(this.sysLobSession.getTransactionTimestamp());
    Result result = this.sysLobSession.executeCompiledStatement(this.deleteLobPartCall, arrayOfObject, 0);
  }
  
  private void divideBlockAddresses(long paramLong, int paramInt) {
    ResultMetaData resultMetaData = this.divideLobPartCall.getParametersMetaData();
    Object[] arrayOfObject = new Object[resultMetaData.getColumnCount()];
    arrayOfObject[0] = ValuePool.getInt(paramInt);
    arrayOfObject[1] = ValuePool.getLong(paramLong);
    Result result = this.sysLobSession.executeCompiledStatement(this.divideLobPartCall, arrayOfObject, 0);
  }
  
  private Result createBlockAddresses(long paramLong, int paramInt1, int paramInt2) {
    ResultMetaData resultMetaData = this.createLobPartCall.getParametersMetaData();
    Object[] arrayOfObject = new Object[resultMetaData.getColumnCount()];
    arrayOfObject[0] = ValuePool.getInt(paramInt2);
    arrayOfObject[1] = ValuePool.getInt(paramInt1);
    arrayOfObject[2] = ValuePool.getLong(paramLong);
    return this.sysLobSession.executeCompiledStatement(this.createLobPartCall, arrayOfObject, 0);
  }
  
  private Result createFullBlockAddresses(long paramLong, int paramInt1, int paramInt2) {
    ResultMetaData resultMetaData = this.createSingleLobPartCall.getParametersMetaData();
    Object[] arrayOfObject = new Object[resultMetaData.getColumnCount()];
    arrayOfObject[0] = ValuePool.getInt(paramInt2);
    arrayOfObject[1] = ValuePool.getInt(paramInt1);
    arrayOfObject[2] = ValuePool.getLong(paramLong);
    return this.sysLobSession.executeCompiledStatement(this.createSingleLobPartCall, arrayOfObject, 0);
  }
  
  private Result createPart(long paramLong1, long paramLong2, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    ResultMetaData resultMetaData = this.createPart.getParametersMetaData();
    Object[] arrayOfObject = new Object[resultMetaData.getColumnCount()];
    arrayOfObject[0] = ValuePool.getInt(paramInt4);
    arrayOfObject[1] = ValuePool.getInt(paramInt3);
    arrayOfObject[2] = ValuePool.getLong(paramLong2);
    arrayOfObject[3] = ValuePool.getLong(paramInt1);
    arrayOfObject[4] = ValuePool.getLong(paramInt2);
    arrayOfObject[5] = ValuePool.getLong(paramLong1);
    return this.sysLobSession.executeCompiledStatement(this.createPart, arrayOfObject, 0);
  }
  
  private int getBlockAddress(int[][] paramArrayOfint, int paramInt) {
    for (byte b = 0; b < paramArrayOfint.length; b++) {
      if (paramArrayOfint[b][2] + paramArrayOfint[b][1] > paramInt)
        return paramArrayOfint[b][0] - paramArrayOfint[b][2] + paramInt; 
    } 
    return -1;
  }
  
  public int getLobCount() {
    this.writeLock.lock();
    try {
      this.sysLobSession.sessionContext.pushDynamicArguments(new Object[0]);
      Result result = this.getLobCount.execute(this.sysLobSession);
      this.sysLobSession.sessionContext.pop();
      RowSetNavigator rowSetNavigator = result.getNavigator();
      boolean bool = rowSetNavigator.next();
      if (!bool) {
        rowSetNavigator.release();
        return 0;
      } 
      Object[] arrayOfObject = rowSetNavigator.getCurrent();
      return ((Number)arrayOfObject[0]).intValue();
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public void synch() {
    if (this.storeModified && this.lobStore != null) {
      this.writeLock.lock();
      try {
        try {
          this.lobStore.synch();
        } catch (Throwable throwable) {}
        this.storeModified = false;
      } finally {
        this.writeLock.unlock();
      } 
    } 
  }
  
  private long[][] getParts(long paramLong1, long paramLong2, long paramLong3) {
    ResultMetaData resultMetaData = this.getSpanningParts.getParametersMetaData();
    Object[] arrayOfObject = new Object[resultMetaData.getColumnCount()];
    arrayOfObject[0] = ValuePool.getLong(paramLong1);
    arrayOfObject[1] = ValuePool.getLong(paramLong2);
    arrayOfObject[2] = ValuePool.getLong(paramLong3);
    this.sysLobSession.sessionContext.pushDynamicArguments(arrayOfObject);
    Result result = this.getSpanningParts.execute(this.sysLobSession);
    this.sysLobSession.sessionContext.pop();
    RowSetNavigator rowSetNavigator = result.getNavigator();
    int i = rowSetNavigator.getSize();
    long[][] arrayOfLong = new long[i][6];
    for (byte b = 0; b < i; b++) {
      rowSetNavigator.absolute(b);
      Object[] arrayOfObject1 = rowSetNavigator.getCurrent();
      for (byte b1 = 0; b1 < (arrayOfLong[b]).length; b1++)
        arrayOfLong[b][b1] = ((Number)arrayOfObject1[b1]).longValue(); 
    } 
    rowSetNavigator.release();
    return arrayOfLong;
  }
  
  void inflate(byte[] paramArrayOfbyte, int paramInt, boolean paramBoolean) {
    if (this.cryptLobs)
      paramInt = this.database.logger.getCrypto().decode(paramArrayOfbyte, 0, paramInt, paramArrayOfbyte, 0); 
    try {
      this.inflater.setInput(paramArrayOfbyte, 0, paramInt);
      paramInt = this.inflater.inflate(this.dataBuffer);
      this.inflater.reset();
    } catch (DataFormatException dataFormatException) {
    
    } catch (Throwable throwable) {}
    int i = (int)ArrayUtil.getBinaryMultipleCeiling(paramInt, this.lobBlockSize);
    for (int j = paramInt; j < i; j++)
      this.dataBuffer[j] = 0; 
  }
  
  int deflate(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean) {
    this.deflater.setInput(paramArrayOfbyte, paramInt1, paramInt2);
    this.deflater.finish();
    paramInt2 = this.deflater.deflate(this.dataBuffer);
    this.deflater.reset();
    if (this.cryptLobs)
      paramInt2 = this.database.logger.getCrypto().encode(this.dataBuffer, 0, paramInt2, this.dataBuffer, 0); 
    int i = (int)ArrayUtil.getBinaryMultipleCeiling(paramInt2, this.lobBlockSize);
    for (int j = paramInt2; j < i; j++)
      this.dataBuffer[j] = 0; 
    return paramInt2;
  }
  
  private int compareBytesCompressed(long paramLong1, long paramLong2) {
    long[][] arrayOfLong1 = getParts(paramLong1, 0L, Long.MAX_VALUE);
    long[][] arrayOfLong2 = getParts(paramLong2, 0L, Long.MAX_VALUE);
    for (byte b = 0; b < arrayOfLong1.length && b < arrayOfLong2.length; b++) {
      int i = (int)arrayOfLong1[b][3];
      getPartBytesCompressedInBuffer(paramLong1, arrayOfLong1[b], false);
      byte[] arrayOfByte = new byte[i];
      System.arraycopy(this.dataBuffer, 0, arrayOfByte, 0, i);
      int j = (int)arrayOfLong2[b][3];
      getPartBytesCompressedInBuffer(paramLong1, arrayOfLong2[b], false);
      int k = ArrayUtil.compare(arrayOfByte, i, this.byteBuffer, j);
      if (k != 0)
        return k; 
    } 
    return (arrayOfLong1.length == arrayOfLong2.length) ? 0 : ((arrayOfLong1.length > arrayOfLong2.length) ? 1 : -1);
  }
  
  private int compareTextCompressed(Collation paramCollation, long paramLong1, long paramLong2) {
    long[][] arrayOfLong1 = getParts(paramLong1, 0L, Long.MAX_VALUE);
    long[][] arrayOfLong2 = getParts(paramLong2, 0L, Long.MAX_VALUE);
    for (byte b = 0; b < arrayOfLong1.length && b < arrayOfLong2.length; b++) {
      int i = (int)arrayOfLong1[b][3];
      getPartBytesCompressedInBuffer(paramLong1, arrayOfLong1[b], true);
      String str1 = new String(ArrayUtil.byteArrayToChars(this.dataBuffer, i));
      int j = (int)arrayOfLong2[b][3];
      getPartBytesCompressedInBuffer(paramLong2, arrayOfLong2[b], true);
      String str2 = new String(ArrayUtil.byteArrayToChars(this.dataBuffer, j));
      int k = paramCollation.compare(str1, str2);
      if (k != 0)
        return k; 
    } 
    return (arrayOfLong1.length == arrayOfLong2.length) ? 0 : ((arrayOfLong1.length > arrayOfLong2.length) ? 1 : -1);
  }
  
  private Result setBytesISCompressed(long paramLong1, InputStream paramInputStream, long paramLong2, boolean paramBoolean) {
    byte[] arrayOfByte;
    long l = 0L;
    if (paramLong2 < this.largeLobBlockSize) {
      arrayOfByte = new byte[(int)paramLong2];
    } else {
      arrayOfByte = new byte[this.largeLobBlockSize];
    } 
    while (true) {
      int i = arrayOfByte.length;
      if (i > paramLong2 - l)
        i = (int)(paramLong2 - l); 
      int j = 0;
      try {
        while (i > 0) {
          int k = paramInputStream.read(arrayOfByte, j, i);
          if (k == -1)
            return Result.newErrorResult(new EOFException()); 
          i -= k;
          j += k;
        } 
      } catch (IOException iOException) {
        return Result.newErrorResult(iOException);
      } 
      Result result = setBytesBACompressedPart(paramLong1, l, arrayOfByte, j, paramBoolean);
      if (result.isError())
        return result; 
      l += j;
      if (l == paramLong2) {
        this.storeModified = true;
        return (Result)ResultLob.newLobSetResponse(paramLong1, paramLong2);
      } 
    } 
  }
  
  private Result setBytesBACompressed(long paramLong1, long paramLong2, byte[] paramArrayOfbyte, int paramInt, boolean paramBoolean) {
    if (paramInt == 0)
      return (Result)ResultLob.newLobSetResponse(paramLong1, 0L); 
    if (paramInt <= this.largeLobBlockSize)
      return setBytesBACompressedPart(paramLong1, paramLong2, paramArrayOfbyte, paramInt, paramBoolean); 
    HsqlByteArrayInputStream hsqlByteArrayInputStream = new HsqlByteArrayInputStream(paramArrayOfbyte, 0, paramInt);
    return setBytesISCompressed(paramLong1, (InputStream)hsqlByteArrayInputStream, paramInt, paramBoolean);
  }
  
  private Result setBytesBACompressedPart(long paramLong1, long paramLong2, byte[] paramArrayOfbyte, int paramInt, boolean paramBoolean) {
    int i = deflate(paramArrayOfbyte, 0, paramInt, paramBoolean);
    long[] arrayOfLong = getLastPart(paramLong1);
    int j = (int)arrayOfLong[1] + (int)arrayOfLong[0];
    int k = (i + this.lobBlockSize - 1) / this.lobBlockSize;
    long l1 = arrayOfLong[2] + arrayOfLong[3];
    if (l1 != paramLong2 || l1 % this.largeLobBlockSize != 0L)
      return Result.newErrorResult((Throwable)Error.error(3474)); 
    Result result = createFullBlockAddresses(paramLong1, j, k);
    if (result.isError())
      return result; 
    result = createPart(paramLong1, paramLong2, paramInt, i, j, k);
    if (result.isError())
      return result; 
    long l2 = (j * this.lobBlockSize);
    int m = (int)ArrayUtil.getBinaryMultipleCeiling(i, this.lobBlockSize);
    setBytesBANormal(paramLong1, l2, this.dataBuffer, m);
    this.storeModified = true;
    return (Result)ResultLob.newLobSetResponse(paramLong1, paramInt);
  }
  
  private Result getBytesCompressed(long paramLong1, long paramLong2, int paramInt, boolean paramBoolean) {
    byte[] arrayOfByte = new byte[paramInt];
    long[][] arrayOfLong = getParts(paramLong1, paramLong2, paramLong2 + paramInt);
    for (byte b = 0; b < arrayOfLong.length; b++) {
      long[] arrayOfLong1 = arrayOfLong[b];
      long l = arrayOfLong1[2];
      int i = (int)arrayOfLong1[3];
      Result result = getPartBytesCompressedInBuffer(paramLong1, arrayOfLong1, paramBoolean);
      if (result.isError())
        return result; 
      ArrayUtil.copyBytes(l, this.dataBuffer, 0, i, paramLong2, arrayOfByte, paramInt);
    } 
    return (Result)ResultLob.newLobGetBytesResponse(paramLong1, paramLong2, arrayOfByte);
  }
  
  private Result getPartBytesCompressedInBuffer(long paramLong, long[] paramArrayOflong, boolean paramBoolean) {
    int i = (int)paramArrayOflong[1];
    long l1 = paramArrayOflong[2];
    int j = (int)paramArrayOflong[3];
    int k = (int)paramArrayOflong[4];
    long l2 = (i * this.lobBlockSize);
    Result result = getBytesNormal(paramLong, l2, k);
    if (result.isError())
      return result; 
    byte[] arrayOfByte = ((ResultLob)result).getByteArray();
    inflate(arrayOfByte, k, paramBoolean);
    return (Result)ResultLob.newLobSetResponse(paramLong, j);
  }
  
  private long[] getLastPart(long paramLong) {
    ResultMetaData resultMetaData = this.getLastPart.getParametersMetaData();
    Object[] arrayOfObject = new Object[resultMetaData.getColumnCount()];
    arrayOfObject[0] = ValuePool.getLong(paramLong);
    this.sysLobSession.sessionContext.pushDynamicArguments(arrayOfObject);
    Result result = this.getLastPart.execute(this.sysLobSession);
    this.sysLobSession.sessionContext.pop();
    RowSetNavigator rowSetNavigator = result.getNavigator();
    int i = rowSetNavigator.getSize();
    long[] arrayOfLong = new long[6];
    if (i == 0) {
      arrayOfLong[5] = paramLong;
    } else {
      rowSetNavigator.absolute(0);
      Object[] arrayOfObject1 = rowSetNavigator.getCurrent();
      for (byte b = 0; b < arrayOfLong.length; b++)
        arrayOfLong[b] = ((Number)arrayOfObject1[b]).longValue(); 
    } 
    rowSetNavigator.release();
    return arrayOfLong;
  }
  
  private static interface ALLOC_PART {
    public static final int BLOCK_COUNT = 0;
    
    public static final int BLOCK_OFFSET = 1;
    
    public static final int PART_OFFSET = 2;
    
    public static final int PART_LENGTH = 3;
    
    public static final int PART_BYTES = 4;
    
    public static final int LOB_ID = 5;
  }
  
  private static interface UPDATE_LENGTH {
    public static final int LOB_LENGTH = 0;
    
    public static final int LOB_ID = 1;
  }
  
  private static interface UPDATE_USAGE {
    public static final int BLOCK_COUNT = 0;
    
    public static final int LOB_ID = 1;
  }
  
  private static interface ALLOC_BLOCKS {
    public static final int BLOCK_COUNT = 0;
    
    public static final int BLOCK_OFFSET = 1;
    
    public static final int LOB_ID = 2;
  }
  
  private static interface DELETE_BLOCKS {
    public static final int LOB_ID = 0;
    
    public static final int BLOCK_OFFSET = 1;
    
    public static final int BLOCK_LIMIT = 2;
    
    public static final int TX_ID = 3;
  }
  
  private static interface DIVIDE_BLOCK {
    public static final int BLOCK_OFFSET = 0;
    
    public static final int LOB_ID = 1;
  }
  
  private static interface GET_LOB_PART {
    public static final int LOB_ID = 0;
    
    public static final int BLOCK_OFFSET = 1;
    
    public static final int BLOCK_LIMIT = 2;
  }
  
  private static interface LOB_IDS {
    public static final int LOB_ID = 0;
    
    public static final int LOB_LENGTH = 1;
    
    public static final int LOB_USAGE_COUNT = 2;
    
    public static final int LOB_TYPE = 3;
  }
  
  private static interface LOBS {
    public static final int BLOCK_ADDR = 0;
    
    public static final int BLOCK_COUNT = 1;
    
    public static final int BLOCK_OFFSET = 2;
    
    public static final int LOB_ID = 3;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\LobManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */