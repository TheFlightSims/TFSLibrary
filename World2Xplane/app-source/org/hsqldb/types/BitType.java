package org.hsqldb.types;

import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.map.BitMap;

public final class BitType extends BinaryType {
  static final long maxBitPrecision = 1024L;
  
  public BitType(int paramInt, long paramLong) {
    super(paramInt, paramLong);
  }
  
  public int displaySize() {
    return (int)this.precision;
  }
  
  public int getJDBCTypeCode() {
    return -7;
  }
  
  public Class getJDBCClass() {
    return byte[].class;
  }
  
  public String getJDBCClassName() {
    return "[B";
  }
  
  public int getSQLGenericTypeCode() {
    return this.typeCode;
  }
  
  public String getNameString() {
    return (this.typeCode == 14) ? "BIT" : "BIT VARYING";
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
  
  public boolean isBitType() {
    return true;
  }
  
  public long getMaxPrecision() {
    return 1024L;
  }
  
  public boolean requiresPrecision() {
    return (this.typeCode == 15);
  }
  
  public Type getAggregateType(Type paramType) {
    if (paramType == null)
      return this; 
    if (paramType == SQL_ALL_TYPES)
      return this; 
    if (this.typeCode == paramType.typeCode)
      return (this.precision >= paramType.precision) ? this : paramType; 
    switch (paramType.typeCode) {
      case 14:
        return (this.precision >= paramType.precision) ? this : getBitType(this.typeCode, paramType.precision);
      case 15:
        return (paramType.precision >= this.precision) ? paramType : getBitType(paramType.typeCode, this.precision);
      case 30:
      case 60:
      case 61:
        return paramType;
    } 
    throw Error.error(5562);
  }
  
  public Type getCombinedType(Session paramSession, Type paramType, int paramInt) {
    BitType bitType;
    Type type;
    if (paramInt != 36)
      return getAggregateType(paramType); 
    long l = this.precision + paramType.precision;
    switch (paramType.typeCode) {
      case 0:
        return this;
      case 14:
        bitType = this;
        break;
      case 15:
        type = paramType;
        break;
      case 30:
      case 60:
      case 61:
        return paramType.getCombinedType(paramSession, this, paramInt);
      default:
        throw Error.error(5562);
    } 
    if (l > 1024L) {
      if (this.typeCode == 14)
        throw Error.error(5570); 
      l = 1024L;
    } 
    return getBitType(type.typeCode, l);
  }
  
  public int compare(Session paramSession, Object paramObject1, Object paramObject2) {
    int i = super.compare(paramSession, paramObject1, paramObject2);
    return (i == 0 && paramObject1 != null) ? ((((BinaryData)paramObject1).bitLength(null) == ((BinaryData)paramObject2).bitLength(null)) ? 0 : ((((BinaryData)paramObject1).bitLength(null) > ((BinaryData)paramObject2).bitLength(null)) ? 1 : -1)) : i;
  }
  
  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject) {
    return castOrConvertToType((SessionInterface)null, paramObject, this, false);
  }
  
  public Object castToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType) {
    return castOrConvertToType(paramSessionInterface, paramObject, paramType, true);
  }
  
  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType) {
    return castOrConvertToType(paramSessionInterface, paramObject, paramType, false);
  }
  
  Object castOrConvertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType, boolean paramBoolean) {
    BinaryData binaryData;
    BlobData blobData;
    if (paramObject == null)
      return null; 
    switch (paramType.typeCode) {
      case 1:
      case 12:
        binaryData = paramSessionInterface.getScanner().convertToBit((String)paramObject);
        paramType = getBitType(15, binaryData.length(paramSessionInterface));
        break;
      case 14:
      case 15:
      case 30:
      case 60:
      case 61:
        blobData = (BlobData)paramObject;
        break;
      case 16:
        if (this.precision != 1L)
          throw Error.error(3471); 
        return ((Boolean)paramObject).booleanValue() ? BinaryData.singleBitOne : BinaryData.singleBitZero;
      case -6:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 25:
        if (this.precision != 1L)
          throw Error.error(3471); 
        return (((NumberType)paramType).compareToZero(paramObject) == 0) ? BinaryData.singleBitZero : BinaryData.singleBitOne;
      default:
        throw Error.error(3471);
    } 
    if (blobData.bitLength(paramSessionInterface) > this.precision) {
      if (!paramBoolean)
        throw Error.error(3401); 
      paramSessionInterface.addWarning(Error.error(1004));
    } 
    int i = (int)((this.precision + 7L) / 8L);
    if (paramType.typeCode == 30) {
      byte[] arrayOfByte1 = blobData.getBytes(paramSessionInterface, 0L, i);
      blobData = new BinaryData(arrayOfByte1, this.precision);
    } 
    switch (this.typeCode) {
      case 14:
        if (blobData.bitLength(paramSessionInterface) == this.precision)
          return blobData; 
        if (blobData.length(paramSessionInterface) > i) {
          byte[] arrayOfByte1 = blobData.getBytes(paramSessionInterface, 0L, i);
          blobData = new BinaryData(arrayOfByte1, this.precision);
          break;
        } 
        if (blobData.length(paramSessionInterface) <= i) {
          byte[] arrayOfByte1 = (byte[])ArrayUtil.resizeArray(blobData.getBytes(), i);
          blobData = new BinaryData(arrayOfByte1, this.precision);
        } 
        break;
      case 15:
        if (blobData.bitLength(paramSessionInterface) <= this.precision)
          return blobData; 
        if (blobData.length(paramSessionInterface) > i) {
          byte[] arrayOfByte1 = blobData.getBytes(paramSessionInterface, 0L, i);
          blobData = new BinaryData(arrayOfByte1, this.precision);
        } 
        break;
      default:
        throw Error.error(3471);
    } 
    byte[] arrayOfByte = blobData.getBytes();
    for (int j = (int)this.precision; j < blobData.length(paramSessionInterface) * 8L; j++)
      BitMap.unset(arrayOfByte, j); 
    return blobData;
  }
  
  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject) {
    if (paramObject == null)
      return paramObject; 
    if (paramObject instanceof byte[]) {
      BinaryData binaryData = new BinaryData((byte[])paramObject, ((byte[])paramObject).length);
      return convertToTypeLimits(paramSessionInterface, binaryData);
    } 
    if (paramObject instanceof BinaryData)
      return convertToTypeLimits(paramSessionInterface, paramObject); 
    if (paramObject instanceof String)
      return convertToType(paramSessionInterface, paramObject, Type.SQL_VARCHAR); 
    if (paramObject instanceof Boolean)
      return convertToType(paramSessionInterface, paramObject, Type.SQL_BOOLEAN); 
    if (paramObject instanceof Integer)
      return convertToType(paramSessionInterface, paramObject, Type.SQL_INTEGER); 
    if (paramObject instanceof Long)
      return convertToType(paramSessionInterface, paramObject, Type.SQL_BIGINT); 
    throw Error.error(3471);
  }
  
  public Object convertJavaToSQL(SessionInterface paramSessionInterface, Object paramObject) {
    return convertToDefaultType(paramSessionInterface, paramObject);
  }
  
  public String convertToString(Object paramObject) {
    return (paramObject == null) ? null : StringConverter.byteArrayToBitString(((BinaryData)paramObject).getBytes(), (int)((BinaryData)paramObject).bitLength(null));
  }
  
  public String convertToSQLString(Object paramObject) {
    return (paramObject == null) ? "NULL" : StringConverter.byteArrayToSQLBitString(((BinaryData)paramObject).getBytes(), (int)((BinaryData)paramObject).bitLength(null));
  }
  
  public boolean canConvertFrom(Type paramType) {
    return (paramType.typeCode == 0 || paramType.isBinaryType() || (this.precision == 1L && (paramType.isIntegralType() || paramType.isBooleanType())) || paramType.isCharacterType());
  }
  
  public long position(SessionInterface paramSessionInterface, BlobData paramBlobData1, BlobData paramBlobData2, Type paramType, long paramLong) {
    if (paramBlobData1 == null || paramBlobData2 == null)
      return -1L; 
    long l = paramBlobData1.bitLength(paramSessionInterface);
    if (paramLong + l > paramBlobData1.bitLength(paramSessionInterface))
      return -1L; 
    throw Error.runtimeError(201, "BitType");
  }
  
  public BlobData substring(SessionInterface paramSessionInterface, BlobData paramBlobData, long paramLong1, long paramLong2, boolean paramBoolean) {
    long l1;
    long l2 = paramBlobData.bitLength(paramSessionInterface);
    if (paramBoolean) {
      l1 = paramLong1 + paramLong2;
    } else {
      l1 = (l2 > paramLong1) ? l2 : paramLong1;
    } 
    if (l1 < paramLong1)
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
    byte[] arrayOfByte1 = paramBlobData.getBytes();
    byte[] arrayOfByte2 = new byte[(int)(paramLong2 + 7L) / 8];
    for (int i = (int)paramLong1; i < l1; i++) {
      if (BitMap.isSet(arrayOfByte1, i))
        BitMap.set(arrayOfByte2, i - (int)paramLong1); 
    } 
    return new BinaryData(arrayOfByte2, paramLong2);
  }
  
  int getRightTrimSize(BinaryData paramBinaryData) {
    int i = (int)paramBinaryData.bitLength(null) - 1;
    byte[] arrayOfByte = paramBinaryData.getBytes();
    while (i >= 0 && !BitMap.isSet(arrayOfByte, i))
      i--; 
    return i + 1;
  }
  
  public BlobData overlay(Session paramSession, BlobData paramBlobData1, BlobData paramBlobData2, long paramLong1, long paramLong2, boolean paramBoolean) {
    byte[] arrayOfByte1;
    byte[] arrayOfByte2;
    byte b;
    int i;
    if (paramBlobData1 == null || paramBlobData2 == null)
      return null; 
    if (!paramBoolean)
      paramLong2 = paramBlobData2.bitLength((SessionInterface)paramSession); 
    switch (this.typeCode) {
      case 14:
      case 15:
        arrayOfByte1 = (byte[])ArrayUtil.duplicateArray(paramBlobData1.getBytes());
        arrayOfByte2 = paramBlobData2.getBytes();
        b = 0;
        i = (int)paramLong1;
        while (b < paramLong2) {
          int j = 8;
          if (paramLong2 - i < 8L)
            j = (int)paramLong2 - i; 
          BitMap.overlay(arrayOfByte1, i, arrayOfByte2[b], j);
          i += 8;
          b++;
        } 
        return new BinaryData(arrayOfByte1, paramBlobData1.bitLength((SessionInterface)paramSession));
    } 
    throw Error.runtimeError(201, "BitType");
  }
  
  public Object concat(Session paramSession, Object paramObject1, Object paramObject2) {
    if (paramObject1 == null || paramObject2 == null)
      return null; 
    long l = ((BlobData)paramObject1).bitLength((SessionInterface)paramSession) + ((BlobData)paramObject2).bitLength((SessionInterface)paramSession);
    if (l > 2147483647L)
      throw Error.error(1000); 
    byte[] arrayOfByte1 = ((BlobData)paramObject1).getBytes();
    byte[] arrayOfByte2 = ((BlobData)paramObject2).getBytes();
    int i = (int)((BlobData)paramObject1).bitLength((SessionInterface)paramSession);
    int j = (int)((BlobData)paramObject2).bitLength((SessionInterface)paramSession);
    byte[] arrayOfByte3 = new byte[(int)(l + 7L) / 8];
    System.arraycopy(arrayOfByte1, 0, arrayOfByte3, 0, arrayOfByte1.length);
    for (byte b = 0; b < j; b++) {
      if (BitMap.isSet(arrayOfByte2, b))
        BitMap.set(arrayOfByte3, i + b); 
    } 
    return new BinaryData(arrayOfByte3, l);
  }
  
  public static BinaryType getBitType(int paramInt, long paramLong) {
    switch (paramInt) {
      case 14:
      case 15:
        return new BitType(paramInt, paramLong);
    } 
    throw Error.runtimeError(201, "BitType");
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\BitType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */