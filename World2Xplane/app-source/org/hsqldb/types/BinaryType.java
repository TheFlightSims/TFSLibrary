package org.hsqldb.types;

import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.StringConverter;

public class BinaryType extends Type {
  public static final long maxBinaryPrecision = 2147483647L;
  
  protected BinaryType(int paramInt, long paramLong) {
    super(61, paramInt, paramLong, 0);
  }
  
  public int displaySize() {
    return (this.precision > 2147483647L) ? Integer.MAX_VALUE : (int)this.precision;
  }
  
  public int getJDBCTypeCode() {
    return (this.typeCode == 60) ? -2 : -3;
  }
  
  public Class getJDBCClass() {
    return byte[].class;
  }
  
  public String getJDBCClassName() {
    return "[B";
  }
  
  public String getNameString() {
    return (this.typeCode == 60) ? "BINARY" : "VARBINARY";
  }
  
  public String getNameFullString() {
    return (this.typeCode == 60) ? "BINARY" : "BINARY VARYING";
  }
  
  public String getDefinition() {
    if (this.precision == 0L)
      return getNameString(); 
    StringBuffer stringBuffer = new StringBuffer(16);
    stringBuffer.append(getNameString());
    stringBuffer.append('(');
    stringBuffer.append(this.precision);
    stringBuffer.append(')');
    return stringBuffer.toString();
  }
  
  public boolean isBinaryType() {
    return true;
  }
  
  public boolean acceptsPrecision() {
    return true;
  }
  
  public long getMaxPrecision() {
    return 2147483647L;
  }
  
  public boolean requiresPrecision() {
    return (this.typeCode == 61);
  }
  
  public int precedenceDegree(Type paramType) {
    if (paramType.typeCode == this.typeCode)
      return 0; 
    if (!paramType.isBinaryType())
      return Integer.MIN_VALUE; 
    switch (this.typeCode) {
      case 14:
      case 15:
        return Integer.MIN_VALUE;
      case 60:
        return (paramType.typeCode == 30) ? 4 : 2;
      case 61:
        return (paramType.typeCode == 30) ? 4 : 2;
      case 30:
        return (paramType.typeCode == 60) ? -4 : -2;
    } 
    throw Error.runtimeError(201, "BinaryType");
  }
  
  public Type getAggregateType(Type paramType) {
    long l;
    if (paramType == null)
      return this; 
    if (paramType == SQL_ALL_TYPES)
      return this; 
    if (this.typeCode == paramType.typeCode)
      return (this.precision >= paramType.precision) ? this : paramType; 
    if (paramType.isCharacterType())
      return paramType.getAggregateType(this); 
    switch (paramType.typeCode) {
      case 14:
      case 15:
        l = (paramType.precision + 7L) / 8L;
        return (this.precision >= l) ? this : getBinaryType(this.typeCode, l);
      case 60:
        return (this.precision >= paramType.precision) ? this : getBinaryType(this.typeCode, paramType.precision);
      case 61:
        return (this.typeCode == 30) ? ((this.precision >= paramType.precision) ? this : getBinaryType(this.typeCode, paramType.precision)) : ((paramType.precision >= this.precision) ? paramType : getBinaryType(paramType.typeCode, this.precision));
      case 30:
        return (paramType.precision >= this.precision) ? paramType : getBinaryType(paramType.typeCode, this.precision);
    } 
    throw Error.error(5562);
  }
  
  public Type getCombinedType(Session paramSession, Type paramType, int paramInt) {
    BinaryType binaryType;
    Type type;
    if (paramInt != 36)
      return getAggregateType(paramType); 
    long l = this.precision + paramType.precision;
    switch (paramType.typeCode) {
      case 0:
        return this;
      case 14:
      case 15:
        l = this.precision + (paramType.precision + 7L) / 8L;
        binaryType = this;
        break;
      case 60:
        binaryType = this;
        break;
      case 61:
        type = (this.typeCode == 30) ? this : paramType;
        break;
      case 30:
        type = paramType;
        break;
      default:
        throw Error.error(5561);
    } 
    if (l > 2147483647L) {
      if (this.typeCode == 60)
        throw Error.error(5570); 
      if (this.typeCode == 61)
        l = 2147483647L; 
    } 
    return getBinaryType(type.typeCode, l);
  }
  
  public int compare(Session paramSession, Object paramObject1, Object paramObject2) {
    if (paramObject1 == paramObject2)
      return 0; 
    if (paramObject1 == null)
      return -1; 
    if (paramObject2 == null)
      return 1; 
    if (paramObject1 instanceof BinaryData && paramObject2 instanceof BinaryData) {
      byte[] arrayOfByte1 = ((BinaryData)paramObject1).getBytes();
      byte[] arrayOfByte2 = ((BinaryData)paramObject2).getBytes();
      int i = (arrayOfByte1.length > arrayOfByte2.length) ? arrayOfByte2.length : arrayOfByte1.length;
      int j = 0;
      while (j < i) {
        if (arrayOfByte1[j] == arrayOfByte2[j]) {
          j++;
          continue;
        } 
        return ((arrayOfByte1[j] & 0xFF) > (arrayOfByte2[j] & 0xFF)) ? 1 : -1;
      } 
      if (arrayOfByte1.length == arrayOfByte2.length)
        return 0; 
      if (this.typeCode == 60) {
        if (arrayOfByte1.length > arrayOfByte2.length) {
          for (j = arrayOfByte2.length; j < arrayOfByte1.length; j++) {
            if (arrayOfByte1[j] != 0)
              return 1; 
          } 
          return 0;
        } 
        for (j = arrayOfByte1.length; j < arrayOfByte2.length; j++) {
          if (arrayOfByte2[j] != 0)
            return -1; 
        } 
        return 0;
      } 
      return (arrayOfByte1.length > arrayOfByte2.length) ? 1 : -1;
    } 
    throw Error.runtimeError(201, "BinaryType");
  }
  
  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject) {
    return castOrConvertToType(paramSessionInterface, paramObject, this, false);
  }
  
  public Object castToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType) {
    return castOrConvertToType(paramSessionInterface, paramObject, paramType, true);
  }
  
  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType) {
    return castOrConvertToType(paramSessionInterface, paramObject, paramType, false);
  }
  
  public Object convertJavaToSQL(SessionInterface paramSessionInterface, Object paramObject) {
    if (paramObject == null)
      return null; 
    if (paramObject instanceof byte[])
      return new BinaryData((byte[])paramObject, true); 
    throw Error.error(5561);
  }
  
  public Object convertSQLToJava(SessionInterface paramSessionInterface, Object paramObject) {
    return (paramObject == null) ? null : ((BlobData)paramObject).getBytes();
  }
  
  Object castOrConvertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType, boolean paramBoolean) {
    BinaryData binaryData;
    BlobData blobData;
    if (paramObject == null)
      return null; 
    switch (paramType.typeCode) {
      case 40:
        paramObject = Type.SQL_VARCHAR.convertToType(paramSessionInterface, paramObject, paramType);
      case 1:
      case 12:
        binaryData = paramSessionInterface.getScanner().convertToBinary((String)paramObject);
        paramType = getBinaryType(61, binaryData.length(paramSessionInterface));
        break;
      case 14:
        blobData = (BlobData)paramObject;
        paramType = getBinaryType(61, blobData.length(paramSessionInterface));
        break;
      case 30:
      case 60:
      case 61:
        blobData = (BlobData)paramObject;
        break;
      default:
        throw Error.error(3471);
    } 
    if (this.precision == 0L)
      return blobData; 
    if (paramType.typeCode == 30) {
      long l = blobData.length(paramSessionInterface);
      if (l > this.precision)
        throw Error.error(3401); 
      byte[] arrayOfByte = blobData.getBytes(paramSessionInterface, 0L, (int)l);
      blobData = new BinaryData(arrayOfByte, false);
    } 
    if (blobData.length(paramSessionInterface) > this.precision && blobData.nonZeroLength(paramSessionInterface) > this.precision) {
      if (!paramBoolean)
        throw Error.error(3401); 
      paramSessionInterface.addWarning(Error.error(1004));
    } 
    switch (this.typeCode) {
      case 60:
        if (blobData.length(paramSessionInterface) > this.precision) {
          byte[] arrayOfByte = blobData.getBytes(paramSessionInterface, 0L, (int)this.precision);
          blobData = new BinaryData(arrayOfByte, false);
        } else if (blobData.length(paramSessionInterface) < this.precision) {
          byte[] arrayOfByte = (byte[])ArrayUtil.resizeArray(blobData.getBytes(), (int)this.precision);
          blobData = new BinaryData(arrayOfByte, false);
        } 
        return blobData;
      case 61:
        if (blobData.length(paramSessionInterface) > this.precision) {
          byte[] arrayOfByte = blobData.getBytes(paramSessionInterface, 0L, (int)this.precision);
          blobData = new BinaryData(arrayOfByte, false);
        } 
        return blobData;
    } 
    throw Error.error(3471);
  }
  
  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject) {
    if (paramObject == null)
      return paramObject; 
    if (paramObject instanceof byte[])
      return new BinaryData((byte[])paramObject, false); 
    if (paramObject instanceof BinaryData)
      return paramObject; 
    if (paramObject instanceof String)
      return castOrConvertToType(paramSessionInterface, paramObject, Type.SQL_VARCHAR, false); 
    throw Error.error(3471);
  }
  
  public String convertToString(Object paramObject) {
    return (paramObject == null) ? null : StringConverter.byteArrayToHexString(((BlobData)paramObject).getBytes());
  }
  
  public String convertToSQLString(Object paramObject) {
    return (paramObject == null) ? "NULL" : StringConverter.byteArrayToSQLHexString(((BinaryData)paramObject).getBytes());
  }
  
  public boolean canConvertFrom(Type paramType) {
    return (paramType.typeCode == 0 || paramType.isBinaryType() || paramType.isCharacterType());
  }
  
  public int canMoveFrom(Type paramType) {
    if (paramType == this)
      return 0; 
    if (!paramType.isBinaryType())
      return -1; 
    switch (this.typeCode) {
      case 61:
        return (paramType.typeCode == this.typeCode) ? ((this.precision >= paramType.precision) ? 0 : 1) : ((paramType.typeCode == 60) ? ((this.precision >= paramType.precision) ? 0 : -1) : -1);
      case 30:
        return (paramType.typeCode == this.typeCode) ? ((this.precision >= paramType.precision) ? 0 : 1) : -1;
      case 14:
      case 60:
        return (paramType.typeCode == this.typeCode && this.precision == paramType.precision) ? 0 : -1;
      case 15:
        return (paramType.typeCode == this.typeCode && this.precision >= paramType.precision) ? 0 : -1;
    } 
    return -1;
  }
  
  public long position(SessionInterface paramSessionInterface, BlobData paramBlobData1, BlobData paramBlobData2, Type paramType, long paramLong) {
    if (paramBlobData1 == null || paramBlobData2 == null)
      return -1L; 
    long l = paramBlobData1.length(paramSessionInterface);
    return (paramLong + l > paramBlobData1.length(paramSessionInterface)) ? -1L : paramBlobData1.position(paramSessionInterface, paramBlobData2, paramLong);
  }
  
  public BlobData substring(SessionInterface paramSessionInterface, BlobData paramBlobData, long paramLong1, long paramLong2, boolean paramBoolean) {
    long l1;
    long l2 = paramBlobData.length(paramSessionInterface);
    if (paramBoolean) {
      l1 = paramLong1 + paramLong2;
    } else {
      l1 = (l2 > paramLong1) ? l2 : paramLong1;
    } 
    if (paramLong1 > l1)
      throw Error.error(3431); 
    if (paramLong1 > l1 || l1 < 0L) {
      paramLong1 = 0L;
      l1 = 0L;
    } 
    if (paramLong1 < 0L)
      paramLong1 = 0L; 
    if (l1 > l2)
      l1 = l2; 
    paramLong2 = l1 - paramLong1;
    byte[] arrayOfByte = paramBlobData.getBytes(paramSessionInterface, paramLong1, (int)paramLong2);
    return new BinaryData(arrayOfByte, false);
  }
  
  int getRightTrimSize(BlobData paramBlobData) {
    byte[] arrayOfByte = paramBlobData.getBytes();
    int i;
    for (i = arrayOfByte.length; --i >= 0 && arrayOfByte[i] == 0; i--);
    return ++i;
  }
  
  public BlobData trim(Session paramSession, BlobData paramBlobData, int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
    if (paramBlobData == null)
      return null; 
    long l = paramBlobData.length((SessionInterface)paramSession);
    if (l > 2147483647L)
      throw Error.error(3460); 
    byte[] arrayOfByte1 = paramBlobData.getBytes((SessionInterface)paramSession, 0L, (int)l);
    int i = arrayOfByte1.length;
    if (paramBoolean2) {
      while (--i >= 0 && arrayOfByte1[i] == paramInt)
        i--; 
      i++;
    } 
    byte b = 0;
    if (paramBoolean1)
      while (b < i && arrayOfByte1[b] == paramInt)
        b++;  
    byte[] arrayOfByte2 = arrayOfByte1;
    if (b != 0 || i != arrayOfByte1.length) {
      arrayOfByte2 = new byte[i - b];
      System.arraycopy(arrayOfByte1, b, arrayOfByte2, 0, i - b);
    } 
    if (this.typeCode == 30) {
      BlobDataID blobDataID = paramSession.createBlob(arrayOfByte2.length);
      blobDataID.setBytes((SessionInterface)paramSession, 0L, arrayOfByte2);
      return blobDataID;
    } 
    return new BinaryData(arrayOfByte2, (arrayOfByte2 == arrayOfByte1));
  }
  
  public BlobData overlay(Session paramSession, BlobData paramBlobData1, BlobData paramBlobData2, long paramLong1, long paramLong2, boolean paramBoolean) {
    byte[] arrayOfByte;
    long l;
    BlobDataID blobDataID;
    if (paramBlobData1 == null || paramBlobData2 == null)
      return null; 
    if (!paramBoolean)
      paramLong2 = paramBlobData2.length((SessionInterface)paramSession); 
    switch (this.typeCode) {
      case 60:
      case 61:
        null = new BinaryData((SessionInterface)paramSession, substring((SessionInterface)paramSession, paramBlobData1, 0L, paramLong1, true), paramBlobData2);
        return new BinaryData((SessionInterface)paramSession, null, substring((SessionInterface)paramSession, paramBlobData1, paramLong1 + paramLong2, 0L, false));
      case 30:
        arrayOfByte = substring((SessionInterface)paramSession, paramBlobData1, 0L, paramLong1, false).getBytes();
        l = paramBlobData1.length((SessionInterface)paramSession) + paramBlobData2.length((SessionInterface)paramSession) - paramLong2;
        blobDataID = paramSession.createBlob(l);
        blobDataID.setBytes((SessionInterface)paramSession, 0L, arrayOfByte);
        blobDataID.setBytes((SessionInterface)paramSession, blobDataID.length((SessionInterface)paramSession), paramBlobData2.getBytes());
        arrayOfByte = substring((SessionInterface)paramSession, paramBlobData1, paramLong1 + paramLong2, 0L, false).getBytes();
        blobDataID.setBytes((SessionInterface)paramSession, blobDataID.length((SessionInterface)paramSession), arrayOfByte);
        return blobDataID;
    } 
    throw Error.runtimeError(201, "BinaryType");
  }
  
  public Object concat(Session paramSession, Object paramObject1, Object paramObject2) {
    if (paramObject1 == null || paramObject2 == null)
      return null; 
    long l = ((BlobData)paramObject1).length((SessionInterface)paramSession) + ((BlobData)paramObject2).length((SessionInterface)paramSession);
    if (l > this.precision)
      throw Error.error(3401); 
    if (this.typeCode == 30) {
      BlobDataID blobDataID = paramSession.createBlob(l);
      blobDataID.setBytes((SessionInterface)paramSession, 0L, (BlobData)paramObject1, 0L, ((BlobData)paramObject1).length((SessionInterface)paramSession));
      blobDataID.setBytes((SessionInterface)paramSession, ((BlobData)paramObject1).length((SessionInterface)paramSession), (BlobData)paramObject2, 0L, ((BlobData)paramObject2).length((SessionInterface)paramSession));
      return blobDataID;
    } 
    return new BinaryData((SessionInterface)paramSession, (BlobData)paramObject1, (BlobData)paramObject2);
  }
  
  public static BinaryType getBinaryType(int paramInt, long paramLong) {
    switch (paramInt) {
      case 60:
      case 61:
        return new BinaryType(paramInt, paramLong);
      case 30:
        return new BlobType(paramLong);
    } 
    throw Error.runtimeError(201, "BinaryType");
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\BinaryType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */