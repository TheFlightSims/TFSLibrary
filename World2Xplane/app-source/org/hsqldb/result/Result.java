package org.hsqldb.result;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import org.hsqldb.ColumnBase;
import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.SqlInvariants;
import org.hsqldb.Statement;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.DataOutputStream;
import org.hsqldb.map.ValuePool;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.navigator.RowSetNavigatorClient;
import org.hsqldb.rowio.RowInputBinary;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;
import org.hsqldb.types.Charset;
import org.hsqldb.types.Collation;
import org.hsqldb.types.Type;

public class Result {
  public static final ResultMetaData sessionAttributesMetaData = ResultMetaData.newResultMetaData(4);
  
  private static final ResultMetaData emptyMeta = ResultMetaData.newResultMetaData(0);
  
  public static final Result emptyGeneratedResult = newDataResult(emptyMeta);
  
  public static final Result updateZeroResult = newUpdateCountResult(0);
  
  public static final Result updateOneResult = newUpdateCountResult(1);
  
  public byte mode;
  
  int databaseID;
  
  long sessionID;
  
  private long id;
  
  private String databaseName;
  
  private String mainString;
  
  private String subString;
  
  private String zoneString;
  
  int errorCode;
  
  private HsqlException exception;
  
  long statementID;
  
  int statementReturnType;
  
  public int updateCount;
  
  private int fetchSize;
  
  private Result chainedResult;
  
  private int lobCount;
  
  ResultLob lobResults;
  
  public ResultMetaData metaData;
  
  public ResultMetaData parameterMetaData;
  
  public ResultMetaData generatedMetaData;
  
  public int rsProperties;
  
  public int queryTimeout;
  
  int generateKeys;
  
  public Object valueData;
  
  public Statement statement;
  
  public RowSetNavigator navigator;
  
  Result(int paramInt) {
    this.mode = (byte)paramInt;
  }
  
  public Result(int paramInt1, int paramInt2) {
    this.mode = (byte)paramInt1;
    this.updateCount = paramInt2;
  }
  
  public static Result newResult(RowSetNavigator paramRowSetNavigator) {
    Result result = new Result(3);
    result.navigator = paramRowSetNavigator;
    return result;
  }
  
  public static Result newResult(int paramInt) {
    RowSetNavigatorClient rowSetNavigatorClient = null;
    Result result = null;
    switch (paramInt) {
      case 8:
      case 9:
        rowSetNavigatorClient = new RowSetNavigatorClient(4);
        break;
      case 6:
      case 17:
        rowSetNavigatorClient = new RowSetNavigatorClient(1);
        break;
      case 16:
        rowSetNavigatorClient = new RowSetNavigatorClient(4);
        break;
      case 18:
        throw Error.runtimeError(201, "Result");
    } 
    result = new Result(paramInt);
    result.navigator = (RowSetNavigator)rowSetNavigatorClient;
    return result;
  }
  
  public static Result newResult(DataInput paramDataInput, RowInputBinary paramRowInputBinary) throws IOException, HsqlException {
    return newResult((Session)null, paramDataInput.readByte(), paramDataInput, paramRowInputBinary);
  }
  
  public static Result newResult(Session paramSession, int paramInt, DataInput paramDataInput, RowInputBinary paramRowInputBinary) throws IOException, HsqlException {
    try {
      return (paramInt == 18) ? ResultLob.newLob(paramDataInput, false) : newResult(paramSession, paramDataInput, paramRowInputBinary, paramInt);
    } catch (IOException iOException) {
      throw Error.error(1300);
    } 
  }
  
  public void readAdditionalResults(SessionInterface paramSessionInterface, DataInputStream paramDataInputStream, RowInputBinary paramRowInputBinary) throws IOException, HsqlException {
    Result result = this;
    setSession(paramSessionInterface);
    while (true) {
      byte b = paramDataInputStream.readByte();
      if (b == 0)
        return; 
      result = newResult((Session)null, paramDataInputStream, paramRowInputBinary, b);
      addChainedResult(result);
    } 
  }
  
  public void readLobResults(SessionInterface paramSessionInterface, DataInputStream paramDataInputStream, RowInputBinary paramRowInputBinary) throws IOException, HsqlException {
    Result result = this;
    boolean bool = false;
    setSession(paramSessionInterface);
    while (true) {
      byte b = paramDataInputStream.readByte();
      if (b == 18) {
        ResultLob resultLob = ResultLob.newLob(paramDataInputStream, false);
        if (paramSessionInterface instanceof Session) {
          ((Session)paramSessionInterface).allocateResultLob(resultLob, paramDataInputStream);
        } else {
          result.addLobResult(resultLob);
        } 
        bool = true;
        continue;
      } 
      if (b == 0) {
        if (bool)
          ((Session)paramSessionInterface).registerResultLobs(result); 
        return;
      } 
      throw Error.runtimeError(201, "Result");
    } 
  }
  
  private static Result newResult(Session paramSession, DataInput paramDataInput, RowInputBinary paramRowInputBinary, int paramInt) throws IOException, HsqlException {
    int j;
    Statement statement;
    int k;
    Result result = newResult(paramInt);
    int i = paramDataInput.readInt();
    paramRowInputBinary.resetRow(0L, i);
    byte[] arrayOfByte = paramRowInputBinary.getBuffer();
    paramDataInput.readFully(arrayOfByte, 4, i - 4);
    switch (paramInt) {
      case 7:
        result.statementReturnType = paramRowInputBinary.readByte();
      case 10:
      case 32:
      case 39:
      
      case 37:
        result.setStatementType(paramRowInputBinary.readByte());
        result.mainString = paramRowInputBinary.readString();
        result.rsProperties = paramRowInputBinary.readByte();
        result.generateKeys = paramRowInputBinary.readByte();
        if (result.generateKeys == 11 || result.generateKeys == 21)
          result.generatedMetaData = new ResultMetaData(paramRowInputBinary); 
      case 40:
        result.id = paramRowInputBinary.readLong();
      case 36:
        result.statementID = paramRowInputBinary.readLong();
      case 34:
        result.updateCount = paramRowInputBinary.readInt();
        result.fetchSize = paramRowInputBinary.readInt();
        result.statementReturnType = paramRowInputBinary.readByte();
        result.mainString = paramRowInputBinary.readString();
        result.rsProperties = paramRowInputBinary.readByte();
        result.queryTimeout = paramRowInputBinary.readShort();
        result.generateKeys = paramRowInputBinary.readByte();
        if (result.generateKeys == 11 || result.generateKeys == 21)
          result.generatedMetaData = new ResultMetaData(paramRowInputBinary); 
      case 31:
        result.databaseName = paramRowInputBinary.readString();
        result.mainString = paramRowInputBinary.readString();
        result.subString = paramRowInputBinary.readString();
        result.zoneString = paramRowInputBinary.readString();
        result.updateCount = paramRowInputBinary.readInt();
      case 2:
      case 19:
        result.mainString = paramRowInputBinary.readString();
        result.subString = paramRowInputBinary.readString();
        result.errorCode = paramRowInputBinary.readInt();
      case 11:
        result.databaseID = paramRowInputBinary.readInt();
        result.sessionID = paramRowInputBinary.readLong();
        result.databaseName = paramRowInputBinary.readString();
        result.mainString = paramRowInputBinary.readString();
      case 1:
        result.updateCount = paramRowInputBinary.readInt();
      case 33:
        j = paramRowInputBinary.readInt();
        result.setActionType(j);
        switch (j) {
          case 2:
          case 4:
            result.mainString = paramRowInputBinary.readString();
          case 0:
          case 1:
          case 6:
          case 7:
            return result;
        } 
        throw Error.runtimeError(201, "Result");
      case 38:
        j = paramRowInputBinary.readInt();
        result.setConnectionAttrType(j);
        switch (j) {
          case 10027:
            result.mainString = paramRowInputBinary.readString();
        } 
        throw Error.runtimeError(201, "Result");
      case 4:
        result.statementReturnType = paramRowInputBinary.readByte();
        result.statementID = paramRowInputBinary.readLong();
        result.rsProperties = paramRowInputBinary.readByte();
        result.metaData = new ResultMetaData(paramRowInputBinary);
        result.parameterMetaData = new ResultMetaData(paramRowInputBinary);
      case 43:
        result.updateCount = paramRowInputBinary.readInt();
        result.fetchSize = paramRowInputBinary.readInt();
        result.statementID = paramRowInputBinary.readLong();
        result.statementReturnType = paramRowInputBinary.readByte();
        result.rsProperties = paramRowInputBinary.readByte();
        result.metaData = new ResultMetaData(paramRowInputBinary);
        result.valueData = readSimple(paramRowInputBinary, result.metaData);
      case 35:
        result.updateCount = paramRowInputBinary.readInt();
        result.fetchSize = paramRowInputBinary.readInt();
        result.statementID = paramRowInputBinary.readLong();
        result.rsProperties = paramRowInputBinary.readByte();
        result.queryTimeout = paramRowInputBinary.readShort();
        statement = paramSession.statementManager.getStatement(paramSession, result.statementID);
        if (statement == null) {
          result.mode = 21;
          result.valueData = ValuePool.emptyObjectArray;
        } 
        result.statement = statement;
        result.metaData = result.statement.getParametersMetaData();
        result.valueData = readSimple(paramRowInputBinary, result.metaData);
      case 41:
        result.id = paramRowInputBinary.readLong();
        k = paramRowInputBinary.readInt();
        result.setActionType(k);
        result.metaData = new ResultMetaData(paramRowInputBinary);
        result.valueData = readSimple(paramRowInputBinary, result.metaData);
      case 6:
      case 8:
      case 9:
      case 16:
        result.updateCount = paramRowInputBinary.readInt();
        result.fetchSize = paramRowInputBinary.readInt();
        result.statementID = paramRowInputBinary.readLong();
        result.queryTimeout = paramRowInputBinary.readShort();
        result.metaData = new ResultMetaData(paramRowInputBinary);
        result.navigator.readSimple((RowInputInterface)paramRowInputBinary, result.metaData);
      case 17:
        result.metaData = new ResultMetaData(paramRowInputBinary);
        result.navigator.read((RowInputInterface)paramRowInputBinary, result.metaData);
      case 13:
        result.id = paramRowInputBinary.readLong();
        result.updateCount = paramRowInputBinary.readInt();
        result.fetchSize = paramRowInputBinary.readInt();
      case 3:
      case 15:
      case 20:
        result.id = paramRowInputBinary.readLong();
        result.updateCount = paramRowInputBinary.readInt();
        result.fetchSize = paramRowInputBinary.readInt();
        result.rsProperties = paramRowInputBinary.readByte();
        result.metaData = new ResultMetaData(paramRowInputBinary);
        result.navigator = (RowSetNavigator)new RowSetNavigatorClient();
        result.navigator.read((RowInputInterface)paramRowInputBinary, result.metaData);
      case 14:
        result.metaData = new ResultMetaData(paramRowInputBinary);
        result.navigator = (RowSetNavigator)new RowSetNavigatorClient();
        result.navigator.read((RowInputInterface)paramRowInputBinary, result.metaData);
    } 
    throw Error.runtimeError(201, "Result");
  }
  
  public static Result newPSMResult(int paramInt, String paramString, Object paramObject) {
    Result result = newResult(42);
    result.errorCode = paramInt;
    result.mainString = paramString;
    result.valueData = paramObject;
    return result;
  }
  
  public static Result newPSMResult(Object paramObject) {
    Result result = newResult(42);
    result.valueData = paramObject;
    return result;
  }
  
  public static Result newPrepareStatementRequest() {
    return newResult(37);
  }
  
  public static Result newPreparedExecuteRequest(Type[] paramArrayOfType, long paramLong) {
    Result result = newResult(35);
    result.metaData = ResultMetaData.newSimpleResultMetaData(paramArrayOfType);
    result.statementID = paramLong;
    result.valueData = ValuePool.emptyObjectArray;
    return result;
  }
  
  public static Result newCallResponse(Type[] paramArrayOfType, long paramLong, Object[] paramArrayOfObject) {
    Result result = newResult(43);
    result.metaData = ResultMetaData.newSimpleResultMetaData(paramArrayOfType);
    result.statementID = paramLong;
    result.valueData = paramArrayOfObject;
    return result;
  }
  
  public static Result newUpdateResultRequest(Type[] paramArrayOfType, long paramLong) {
    Result result = newResult(41);
    result.metaData = ResultMetaData.newUpdateResultMetaData(paramArrayOfType);
    result.id = paramLong;
    result.valueData = new Object[0];
    return result;
  }
  
  public void setPreparedResultUpdateProperties(Object[] paramArrayOfObject) {
    this.valueData = paramArrayOfObject;
  }
  
  public void setPreparedExecuteProperties(Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.mode = 35;
    this.valueData = paramArrayOfObject;
    this.updateCount = paramInt1;
    this.fetchSize = paramInt2;
    this.rsProperties = paramInt3;
    this.queryTimeout = paramInt4;
  }
  
  public void setBatchedPreparedExecuteRequest() {
    this.mode = 9;
    if (this.navigator == null) {
      this.navigator = (RowSetNavigator)new RowSetNavigatorClient(4);
    } else {
      this.navigator.clear();
    } 
    this.updateCount = 0;
    this.fetchSize = 0;
  }
  
  public void addBatchedPreparedExecuteRequest(Object[] paramArrayOfObject) {
    ((RowSetNavigatorClient)this.navigator).add(paramArrayOfObject);
  }
  
  public static Result newBatchedExecuteRequest() {
    Type[] arrayOfType = { (Type)Type.SQL_VARCHAR };
    Result result = newResult(8);
    result.metaData = ResultMetaData.newSimpleResultMetaData(arrayOfType);
    return result;
  }
  
  public static Result newBatchedExecuteResponse(int[] paramArrayOfint, Result paramResult1, Result paramResult2) {
    Result result = newResult(16);
    result.addChainedResult(paramResult1);
    result.addChainedResult(paramResult2);
    Type[] arrayOfType = { (Type)Type.SQL_INTEGER };
    result.metaData = ResultMetaData.newSimpleResultMetaData(arrayOfType);
    Object[][] arrayOfObject = new Object[paramArrayOfint.length][];
    for (byte b = 0; b < paramArrayOfint.length; b++) {
      (new Object[1])[0] = ValuePool.getInt(paramArrayOfint[b]);
      arrayOfObject[b] = new Object[1];
    } 
    ((RowSetNavigatorClient)result.navigator).setData(arrayOfObject);
    return result;
  }
  
  public static Result newResetSessionRequest() {
    return newResult(10);
  }
  
  public static Result newConnectionAttemptRequest(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt) {
    Result result = newResult(31);
    result.mainString = paramString1;
    result.subString = paramString2;
    result.zoneString = paramString4;
    result.databaseName = paramString3;
    result.updateCount = paramInt;
    return result;
  }
  
  public static Result newConnectionAcknowledgeResponse(Database paramDatabase, long paramLong, int paramInt) {
    Result result = newResult(11);
    result.sessionID = paramLong;
    result.databaseID = paramInt;
    result.databaseName = paramDatabase.getUniqueName();
    result.mainString = paramDatabase.getProperties().getClientPropertiesAsString();
    return result;
  }
  
  public static Result newUpdateZeroResult() {
    return new Result(1, 0);
  }
  
  public static Result newUpdateCountResult(int paramInt) {
    return new Result(1, paramInt);
  }
  
  public static Result newUpdateCountResult(ResultMetaData paramResultMetaData, int paramInt) {
    Result result1 = newResult(1);
    Result result2 = newGeneratedDataResult(paramResultMetaData);
    result1.updateCount = paramInt;
    result1.addChainedResult(result2);
    return result1;
  }
  
  public static Result newSingleColumnResult(ResultMetaData paramResultMetaData) {
    Result result = newResult(3);
    result.metaData = paramResultMetaData;
    result.navigator = (RowSetNavigator)new RowSetNavigatorClient();
    return result;
  }
  
  public static Result newSingleColumnResult(String paramString) {
    Result result = newResult(3);
    result.metaData = ResultMetaData.newSingleColumnMetaData(paramString);
    result.navigator = (RowSetNavigator)new RowSetNavigatorClient(8);
    return result;
  }
  
  public static Result newSingleColumnStringResult(String paramString1, String paramString2) {
    Result result = newSingleColumnResult(paramString1);
    LineNumberReader lineNumberReader = new LineNumberReader(new StringReader(paramString2));
    while (true) {
      String str = null;
      try {
        str = lineNumberReader.readLine();
      } catch (Exception exception) {}
      if (str == null)
        return result; 
      result.getNavigator().add(new Object[] { str });
    } 
  }
  
  public static Result newPrepareResponse(Statement paramStatement) {
    Result result = newResult(4);
    result.statement = paramStatement;
    result.statementID = paramStatement.getID();
    int i = paramStatement.getType();
    result.statementReturnType = paramStatement.getStatementReturnType();
    result.metaData = paramStatement.getResultMetaData();
    result.parameterMetaData = paramStatement.getParametersMetaData();
    return result;
  }
  
  public static Result newFreeStmtRequest(long paramLong) {
    Result result = newResult(36);
    result.statementID = paramLong;
    return result;
  }
  
  public static Result newExecuteDirectRequest() {
    return newResult(34);
  }
  
  public void setPrepareOrExecuteProperties(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfint, String[] paramArrayOfString) {
    this.mainString = paramString;
    this.updateCount = paramInt1;
    this.fetchSize = paramInt2;
    this.statementReturnType = paramInt3;
    this.queryTimeout = paramInt4;
    this.rsProperties = paramInt5;
    this.generateKeys = paramInt6;
    this.generatedMetaData = ResultMetaData.newGeneratedColumnsMetaData(paramArrayOfint, paramArrayOfString);
  }
  
  public static Result newSetSavepointRequest(String paramString) {
    Result result = newResult(38);
    result.setConnectionAttrType(10027);
    result.setMainString(paramString);
    return result;
  }
  
  public static Result newRequestDataResult(long paramLong, int paramInt1, int paramInt2) {
    Result result = newResult(13);
    result.id = paramLong;
    result.updateCount = paramInt1;
    result.fetchSize = paramInt2;
    return result;
  }
  
  public static Result newDataResult(ResultMetaData paramResultMetaData) {
    Result result = newResult(3);
    result.navigator = (RowSetNavigator)new RowSetNavigatorClient();
    result.metaData = paramResultMetaData;
    return result;
  }
  
  public static Result newGeneratedDataResult(ResultMetaData paramResultMetaData) {
    Result result = newResult(20);
    result.navigator = (RowSetNavigator)new RowSetNavigatorClient();
    result.metaData = paramResultMetaData;
    return result;
  }
  
  public int getExecuteProperties() {
    return this.rsProperties;
  }
  
  public static Result newDataHeadResult(SessionInterface paramSessionInterface, Result paramResult, int paramInt1, int paramInt2) {
    if (paramInt1 + paramInt2 > paramResult.navigator.getSize())
      paramInt2 = paramResult.navigator.getSize() - paramInt1; 
    Result result = newResult(15);
    result.metaData = paramResult.metaData;
    result.navigator = (RowSetNavigator)new RowSetNavigatorClient(paramResult.navigator, paramInt1, paramInt2);
    result.navigator.setId(paramResult.navigator.getId());
    result.setSession(paramSessionInterface);
    result.rsProperties = paramResult.rsProperties;
    result.fetchSize = paramResult.fetchSize;
    return result;
  }
  
  public static Result newDataRowsResult(Result paramResult, int paramInt1, int paramInt2) {
    if (paramInt1 + paramInt2 > paramResult.navigator.getSize())
      paramInt2 = paramResult.navigator.getSize() - paramInt1; 
    Result result = newResult(14);
    result.id = paramResult.id;
    result.metaData = paramResult.metaData;
    result.navigator = (RowSetNavigator)new RowSetNavigatorClient(paramResult.navigator, paramInt1, paramInt2);
    return result;
  }
  
  public static Result newDataRowsResult(RowSetNavigator paramRowSetNavigator) {
    Result result = newResult(14);
    result.navigator = paramRowSetNavigator;
    return result;
  }
  
  public static Result newSessionAttributesResult() {
    Result result = newResult(3);
    result.navigator = (RowSetNavigator)new RowSetNavigatorClient(1);
    result.metaData = sessionAttributesMetaData;
    result.navigator.add(new Object[4]);
    return result;
  }
  
  public static Result newWarningResult(HsqlException paramHsqlException) {
    Result result = newResult(19);
    result.mainString = paramHsqlException.getMessage();
    result.subString = paramHsqlException.getSQLState();
    result.errorCode = paramHsqlException.getErrorCode();
    return result;
  }
  
  public static Result newErrorResult(Throwable paramThrowable) {
    return newErrorResult(paramThrowable, null);
  }
  
  public static Result newErrorResult(Throwable paramThrowable, String paramString) {
    Result result = newResult(2);
    if (paramThrowable instanceof HsqlException) {
      result.exception = (HsqlException)paramThrowable;
      result.mainString = result.exception.getMessage();
      result.subString = result.exception.getSQLState();
      if (paramString != null)
        result.mainString += " in statement [" + paramString + "]"; 
      result.errorCode = result.exception.getErrorCode();
    } else if (paramThrowable instanceof OutOfMemoryError) {
      System.gc();
      result.exception = Error.error(460, paramThrowable);
      result.mainString = result.exception.getMessage();
      result.subString = result.exception.getSQLState();
      result.errorCode = result.exception.getErrorCode();
    } else {
      result.exception = Error.error(458, paramThrowable);
      result.mainString = result.exception.getMessage() + " " + paramThrowable.toString();
      result.subString = result.exception.getSQLState();
      result.errorCode = result.exception.getErrorCode();
      if (paramString != null)
        result.mainString += " in statement [" + paramString + "]"; 
    } 
    return result;
  }
  
  public void write(SessionInterface paramSessionInterface, DataOutputStream paramDataOutputStream, RowOutputInterface paramRowOutputInterface) throws IOException, HsqlException {
    paramRowOutputInterface.reset();
    paramRowOutputInterface.writeByte(this.mode);
    int i = paramRowOutputInterface.size();
    paramRowOutputInterface.writeSize(0);
    switch (this.mode) {
      case 7:
        paramRowOutputInterface.writeByte(this.statementReturnType);
        break;
      case 10:
      case 32:
      case 39:
        break;
      case 37:
        paramRowOutputInterface.writeByte(this.statementReturnType);
        paramRowOutputInterface.writeString(this.mainString);
        paramRowOutputInterface.writeByte(this.rsProperties);
        paramRowOutputInterface.writeByte(this.generateKeys);
        if (this.generateKeys == 11 || this.generateKeys == 21)
          this.generatedMetaData.write(paramRowOutputInterface); 
        break;
      case 36:
        paramRowOutputInterface.writeLong(this.statementID);
        break;
      case 40:
        paramRowOutputInterface.writeLong(this.id);
        break;
      case 34:
        paramRowOutputInterface.writeInt(this.updateCount);
        paramRowOutputInterface.writeInt(this.fetchSize);
        paramRowOutputInterface.writeByte(this.statementReturnType);
        paramRowOutputInterface.writeString(this.mainString);
        paramRowOutputInterface.writeByte(this.rsProperties);
        paramRowOutputInterface.writeShort(this.queryTimeout);
        paramRowOutputInterface.writeByte(this.generateKeys);
        if (this.generateKeys == 11 || this.generateKeys == 21)
          this.generatedMetaData.write(paramRowOutputInterface); 
        break;
      case 31:
        paramRowOutputInterface.writeString(this.databaseName);
        paramRowOutputInterface.writeString(this.mainString);
        paramRowOutputInterface.writeString(this.subString);
        paramRowOutputInterface.writeString(this.zoneString);
        paramRowOutputInterface.writeInt(this.updateCount);
        break;
      case 2:
      case 19:
        paramRowOutputInterface.writeString(this.mainString);
        paramRowOutputInterface.writeString(this.subString);
        paramRowOutputInterface.writeInt(this.errorCode);
        break;
      case 11:
        paramRowOutputInterface.writeInt(this.databaseID);
        paramRowOutputInterface.writeLong(this.sessionID);
        paramRowOutputInterface.writeString(this.databaseName);
        paramRowOutputInterface.writeString(this.mainString);
        break;
      case 1:
        paramRowOutputInterface.writeInt(this.updateCount);
        break;
      case 33:
        j = getActionType();
        paramRowOutputInterface.writeInt(j);
        switch (j) {
          case 2:
          case 4:
            paramRowOutputInterface.writeString(this.mainString);
            break;
          case 0:
          case 1:
          case 6:
          case 7:
            break;
        } 
        throw Error.runtimeError(201, "Result");
      case 4:
        paramRowOutputInterface.writeByte(this.statementReturnType);
        paramRowOutputInterface.writeLong(this.statementID);
        paramRowOutputInterface.writeByte(this.rsProperties);
        this.metaData.write(paramRowOutputInterface);
        this.parameterMetaData.write(paramRowOutputInterface);
        break;
      case 43:
        paramRowOutputInterface.writeInt(this.updateCount);
        paramRowOutputInterface.writeInt(this.fetchSize);
        paramRowOutputInterface.writeLong(this.statementID);
        paramRowOutputInterface.writeByte(this.statementReturnType);
        paramRowOutputInterface.writeByte(this.rsProperties);
        this.metaData.write(paramRowOutputInterface);
        writeSimple(paramRowOutputInterface, this.metaData, (Object[])this.valueData);
        break;
      case 35:
        paramRowOutputInterface.writeInt(this.updateCount);
        paramRowOutputInterface.writeInt(this.fetchSize);
        paramRowOutputInterface.writeLong(this.statementID);
        paramRowOutputInterface.writeByte(this.rsProperties);
        paramRowOutputInterface.writeShort(this.queryTimeout);
        writeSimple(paramRowOutputInterface, this.metaData, (Object[])this.valueData);
        break;
      case 41:
        paramRowOutputInterface.writeLong(this.id);
        paramRowOutputInterface.writeInt(getActionType());
        this.metaData.write(paramRowOutputInterface);
        writeSimple(paramRowOutputInterface, this.metaData, (Object[])this.valueData);
        break;
      case 6:
      case 8:
      case 9:
      case 16:
        paramRowOutputInterface.writeInt(this.updateCount);
        paramRowOutputInterface.writeInt(this.fetchSize);
        paramRowOutputInterface.writeLong(this.statementID);
        paramRowOutputInterface.writeShort(this.queryTimeout);
        this.metaData.write(paramRowOutputInterface);
        this.navigator.writeSimple(paramRowOutputInterface, this.metaData);
        break;
      case 17:
        this.metaData.write(paramRowOutputInterface);
        this.navigator.write(paramRowOutputInterface, this.metaData);
        break;
      case 38:
        j = getConnectionAttrType();
        paramRowOutputInterface.writeInt(j);
        switch (j) {
          case 10027:
            paramRowOutputInterface.writeString(this.mainString);
            break;
        } 
        throw Error.runtimeError(201, "Result");
      case 13:
        paramRowOutputInterface.writeLong(this.id);
        paramRowOutputInterface.writeInt(this.updateCount);
        paramRowOutputInterface.writeInt(this.fetchSize);
        break;
      case 14:
        this.metaData.write(paramRowOutputInterface);
        this.navigator.write(paramRowOutputInterface, this.metaData);
        break;
      case 3:
      case 15:
      case 20:
        paramRowOutputInterface.writeLong(this.id);
        paramRowOutputInterface.writeInt(this.updateCount);
        paramRowOutputInterface.writeInt(this.fetchSize);
        paramRowOutputInterface.writeByte(this.rsProperties);
        this.metaData.write(paramRowOutputInterface);
        this.navigator.write(paramRowOutputInterface, this.metaData);
        break;
      default:
        throw Error.runtimeError(201, "Result");
    } 
    paramRowOutputInterface.writeIntData(paramRowOutputInterface.size() - i, i);
    paramDataOutputStream.write(paramRowOutputInterface.getOutputStream().getBuffer(), 0, paramRowOutputInterface.size());
    int j = getLobCount();
    Result result = this;
    for (byte b = 0; b < j; b++) {
      ResultLob resultLob = result.lobResults;
      resultLob.writeBody(paramSessionInterface, paramDataOutputStream);
      result = result.lobResults;
    } 
    if (this.chainedResult == null) {
      paramDataOutputStream.writeByte(0);
    } else {
      this.chainedResult.write(paramSessionInterface, paramDataOutputStream, paramRowOutputInterface);
    } 
    paramDataOutputStream.flush();
  }
  
  public int getType() {
    return this.mode;
  }
  
  public boolean isData() {
    return (this.mode == 3 || this.mode == 15);
  }
  
  public boolean isError() {
    return (this.mode == 2);
  }
  
  public boolean isWarning() {
    return (this.mode == 19);
  }
  
  public boolean isUpdateCount() {
    return (this.mode == 1);
  }
  
  public boolean isSimpleValue() {
    return (this.mode == 42);
  }
  
  public boolean hasGeneratedKeys() {
    return (this.mode == 1 && this.chainedResult != null);
  }
  
  public HsqlException getException() {
    return this.exception;
  }
  
  public long getStatementID() {
    return this.statementID;
  }
  
  public void setStatementID(long paramLong) {
    this.statementID = paramLong;
  }
  
  public String getMainString() {
    return this.mainString;
  }
  
  public void setMainString(String paramString) {
    this.mainString = paramString;
  }
  
  public String getSubString() {
    return this.subString;
  }
  
  public String getZoneString() {
    return this.zoneString;
  }
  
  public int getErrorCode() {
    return this.errorCode;
  }
  
  public Object getValueObject() {
    return this.valueData;
  }
  
  public void setValueObject(Object paramObject) {
    this.valueData = paramObject;
  }
  
  public Statement getStatement() {
    return this.statement;
  }
  
  public void setStatement(Statement paramStatement) {
    this.statement = paramStatement;
  }
  
  public String getDatabaseName() {
    return this.databaseName;
  }
  
  public void setMaxRows(int paramInt) {
    this.updateCount = paramInt;
  }
  
  public int getFetchSize() {
    return this.fetchSize;
  }
  
  public void setFetchSize(int paramInt) {
    this.fetchSize = paramInt;
  }
  
  public int getUpdateCount() {
    return this.updateCount;
  }
  
  public int getConnectionAttrType() {
    return this.updateCount;
  }
  
  public void setConnectionAttrType(int paramInt) {
    this.updateCount = paramInt;
  }
  
  public int getActionType() {
    return this.updateCount;
  }
  
  public void setActionType(int paramInt) {
    this.updateCount = paramInt;
  }
  
  public long getSessionId() {
    return this.sessionID;
  }
  
  public void setSessionId(long paramLong) {
    this.sessionID = paramLong;
  }
  
  public void setSession(SessionInterface paramSessionInterface) {
    if (this.navigator != null)
      this.navigator.setSession(paramSessionInterface); 
  }
  
  public int getDatabaseId() {
    return this.databaseID;
  }
  
  public void setDatabaseId(int paramInt) {
    this.databaseID = paramInt;
  }
  
  public long getResultId() {
    return this.id;
  }
  
  public void setResultId(long paramLong) {
    this.id = paramLong;
    if (this.navigator != null)
      this.navigator.setId(paramLong); 
  }
  
  public void setUpdateCount(int paramInt) {
    this.updateCount = paramInt;
  }
  
  public void setAsTransactionEndRequest(int paramInt, String paramString) {
    this.mode = 33;
    this.updateCount = paramInt;
    this.mainString = (paramString == null) ? "" : paramString;
  }
  
  public Object[] getSingleRowData() {
    null = initialiseNavigator().getNext();
    return (Object[])ArrayUtil.resizeArrayIfDifferent(null, this.metaData.getColumnCount());
  }
  
  public Object[] getParameterData() {
    return (Object[])this.valueData;
  }
  
  public Object[] getSessionAttributes() {
    return initialiseNavigator().getNext();
  }
  
  public void setResultType(int paramInt) {
    this.mode = (byte)paramInt;
  }
  
  public void setStatementType(int paramInt) {
    this.statementReturnType = paramInt;
  }
  
  public int getStatementType() {
    return this.statementReturnType;
  }
  
  public int getGeneratedResultType() {
    return this.generateKeys;
  }
  
  public ResultMetaData getGeneratedResultMetaData() {
    return this.generatedMetaData;
  }
  
  public Result getChainedResult() {
    return this.chainedResult;
  }
  
  public Result getUnlinkChainedResult() {
    Result result = this.chainedResult;
    this.chainedResult = null;
    return result;
  }
  
  public void addChainedResult(Result paramResult) {
    Result result;
    for (result = this; result.chainedResult != null; result = result.chainedResult);
    result.chainedResult = paramResult;
  }
  
  public void addWarnings(HsqlException[] paramArrayOfHsqlException) {
    for (byte b = 0; b < paramArrayOfHsqlException.length; b++) {
      Result result = newWarningResult(paramArrayOfHsqlException[b]);
      addChainedResult(result);
    } 
  }
  
  public int getLobCount() {
    return this.lobCount;
  }
  
  public ResultLob getLOBResult() {
    return this.lobResults;
  }
  
  public void addLobResult(ResultLob paramResultLob) {
    Result result;
    for (result = this; result.lobResults != null; result = result.lobResults);
    result.lobResults = paramResultLob;
    this.lobCount++;
  }
  
  public void clearLobResults() {
    this.lobResults = null;
    this.lobCount = 0;
  }
  
  private static Object[] readSimple(RowInputBinary paramRowInputBinary, ResultMetaData paramResultMetaData) throws IOException {
    int i = paramRowInputBinary.readInt();
    return paramRowInputBinary.readData(paramResultMetaData.columnTypes);
  }
  
  private static void writeSimple(RowOutputInterface paramRowOutputInterface, ResultMetaData paramResultMetaData, Object[] paramArrayOfObject) throws IOException {
    paramRowOutputInterface.writeInt(1);
    paramRowOutputInterface.writeData(paramResultMetaData.getColumnCount(), paramResultMetaData.columnTypes, paramArrayOfObject, null, null);
  }
  
  public RowSetNavigator getNavigator() {
    return this.navigator;
  }
  
  public void setNavigator(RowSetNavigator paramRowSetNavigator) {
    this.navigator = paramRowSetNavigator;
  }
  
  public RowSetNavigator initialiseNavigator() {
    switch (this.mode) {
      case 6:
      case 8:
      case 9:
      case 16:
      case 17:
        this.navigator.beforeFirst();
        return this.navigator;
      case 3:
      case 15:
      case 20:
        this.navigator.reset();
        return this.navigator;
    } 
    throw Error.runtimeError(201, "Result");
  }
  
  static {
    SqlInvariants.isSystemSchemaName("SYSTEM_SCHEMA");
    Charset.getDefaultInstance();
    Collation.getDefaultInstance();
  }
  
  static {
    for (byte b = 0; b < 4; b++)
      sessionAttributesMetaData.columns[b] = new ColumnBase(null, null, null, null); 
    sessionAttributesMetaData.columns[0].setType((Type)Type.SQL_INTEGER);
    sessionAttributesMetaData.columns[1].setType((Type)Type.SQL_INTEGER);
    sessionAttributesMetaData.columns[2].setType((Type)Type.SQL_BOOLEAN);
    sessionAttributesMetaData.columns[3].setType((Type)Type.SQL_VARCHAR);
    sessionAttributesMetaData.prepareData();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\result\Result.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */