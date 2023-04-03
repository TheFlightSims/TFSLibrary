package org.hsqldb.rowio;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.hsqldb.Row;
import org.hsqldb.error.Error;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BlobData;
import org.hsqldb.types.ClobData;
import org.hsqldb.types.IntervalMonthData;
import org.hsqldb.types.IntervalSecondData;
import org.hsqldb.types.JavaObjectData;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

public class RowOutputBinary extends RowOutputBase {
  public static final int INT_STORE_SIZE = 4;
  
  int storageSize;
  
  final int scale;
  
  final int mask;
  
  public RowOutputBinary(int paramInt1, int paramInt2) {
    super(paramInt1);
    this.scale = paramInt2;
    this.mask = paramInt2 - 1 ^ 0xFFFFFFFF;
  }
  
  public RowOutputBinary(byte[] paramArrayOfbyte) {
    super(paramArrayOfbyte);
    this.scale = 1;
    this.mask = this.scale - 1 ^ 0xFFFFFFFF;
  }
  
  public void writeIntData(int paramInt1, int paramInt2) {
    int i = this.count;
    this.count = paramInt2;
    writeInt(paramInt1);
    if (this.count < i)
      this.count = i; 
  }
  
  public void writeData(Row paramRow, Type[] paramArrayOfType) {
    super.writeData(paramRow, paramArrayOfType);
  }
  
  public void writeEnd() {
    if (this.count > this.storageSize)
      Error.runtimeError(201, "RowOutputBinary"); 
    while (this.count < this.storageSize)
      write(0); 
  }
  
  public void writeSize(int paramInt) {
    this.storageSize = paramInt;
    writeInt(paramInt);
  }
  
  public void writeType(int paramInt) {
    writeShort(paramInt);
  }
  
  public void writeString(String paramString) {
    int i = this.count;
    writeInt(0);
    if (paramString != null && paramString.length() != 0) {
      StringConverter.stringToUTFBytes(paramString, this);
      writeIntData(this.count - i - 4, i);
    } 
  }
  
  public int getSize(Row paramRow) {
    Object[] arrayOfObject = paramRow.getData();
    Type[] arrayOfType = paramRow.getTable().getColumnTypes();
    int i = paramRow.getTable().getDataColumnCount();
    return 4 + getSize(arrayOfObject, i, arrayOfType);
  }
  
  public int getStorageSize(int paramInt) {
    return paramInt + this.scale - 1 & this.mask;
  }
  
  public void writeFieldType(Type paramType) {
    write(1);
  }
  
  public void writeNull(Type paramType) {
    write(0);
  }
  
  protected void writeChar(String paramString, Type paramType) {
    writeString(paramString);
  }
  
  protected void writeSmallint(Number paramNumber) {
    writeShort(paramNumber.intValue());
  }
  
  protected void writeInteger(Number paramNumber) {
    writeInt(paramNumber.intValue());
  }
  
  protected void writeBigint(Number paramNumber) {
    writeLong(paramNumber.longValue());
  }
  
  protected void writeReal(Double paramDouble) {
    writeLong(Double.doubleToLongBits(paramDouble.doubleValue()));
  }
  
  protected void writeDecimal(BigDecimal paramBigDecimal, Type paramType) {
    int i = paramBigDecimal.scale();
    BigInteger bigInteger = paramBigDecimal.unscaledValue();
    byte[] arrayOfByte = bigInteger.toByteArray();
    writeByteArray(arrayOfByte);
    writeInt(i);
  }
  
  protected void writeBoolean(Boolean paramBoolean) {
    write(paramBoolean.booleanValue() ? 1 : 0);
  }
  
  protected void writeDate(TimestampData paramTimestampData, Type paramType) {
    writeLong(paramTimestampData.getSeconds());
  }
  
  protected void writeTime(TimeData paramTimeData, Type paramType) {
    writeInt(paramTimeData.getSeconds());
    writeInt(paramTimeData.getNanos());
    if (paramType.typeCode == 94)
      writeInt(paramTimeData.getZone()); 
  }
  
  protected void writeTimestamp(TimestampData paramTimestampData, Type paramType) {
    writeLong(paramTimestampData.getSeconds());
    writeInt(paramTimestampData.getNanos());
    if (paramType.typeCode == 95)
      writeInt(paramTimestampData.getZone()); 
  }
  
  protected void writeYearMonthInterval(IntervalMonthData paramIntervalMonthData, Type paramType) {
    writeLong(paramIntervalMonthData.units);
  }
  
  protected void writeDaySecondInterval(IntervalSecondData paramIntervalSecondData, Type paramType) {
    writeLong(paramIntervalSecondData.getSeconds());
    writeInt(paramIntervalSecondData.getNanos());
  }
  
  protected void writeOther(JavaObjectData paramJavaObjectData) {
    writeByteArray(paramJavaObjectData.getBytes());
  }
  
  protected void writeBit(BinaryData paramBinaryData) {
    writeInt((int)paramBinaryData.bitLength(null));
    write(paramBinaryData.getBytes(), 0, (paramBinaryData.getBytes()).length);
  }
  
  protected void writeBinary(BinaryData paramBinaryData) {
    writeByteArray(paramBinaryData.getBytes());
  }
  
  protected void writeClob(ClobData paramClobData, Type paramType) {
    writeLong(paramClobData.getId());
  }
  
  protected void writeBlob(BlobData paramBlobData, Type paramType) {
    writeLong(paramBlobData.getId());
  }
  
  protected void writeArray(Object[] paramArrayOfObject, Type paramType) {
    paramType = paramType.collectionBaseType();
    writeInt(paramArrayOfObject.length);
    for (byte b = 0; b < paramArrayOfObject.length; b++)
      writeData(paramType, paramArrayOfObject[b]); 
  }
  
  public void writeArray(int[] paramArrayOfint) {
    writeInt(paramArrayOfint.length);
    for (byte b = 0; b < paramArrayOfint.length; b++) {
      write(1);
      writeInt(paramArrayOfint[b]);
    } 
  }
  
  public void writeByteArray(byte[] paramArrayOfbyte) {
    writeInt(paramArrayOfbyte.length);
    write(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public void writeCharArray(char[] paramArrayOfchar) {
    writeInt(paramArrayOfchar.length);
    write(paramArrayOfchar, 0, paramArrayOfchar.length);
  }
  
  public int getSize(int[] paramArrayOfint) {
    return 4 + paramArrayOfint.length * 5;
  }
  
  public int getSize(Object[] paramArrayOfObject, int paramInt, Type[] paramArrayOfType) {
    int i = 0;
    for (byte b = 0; b < paramInt; b++) {
      Object object = paramArrayOfObject[b];
      i += getSize(object, paramArrayOfType[b]);
    } 
    return i;
  }
  
  private int getSize(Object paramObject, Type paramType) {
    BigDecimal bigDecimal;
    BigInteger bigInteger;
    Object[] arrayOfObject;
    JavaObjectData javaObjectData;
    byte b;
    int i = 1;
    if (paramObject == null)
      return i; 
    switch (paramType.typeCode) {
      case 0:
        return i;
      case 1:
      case 12:
        i += true;
        i += StringConverter.getUTFSize((String)paramObject);
      case -6:
      case 5:
        i += 2;
      case 4:
        i += 4;
      case 6:
      case 7:
      case 8:
      case 25:
        i += 8;
      case 2:
      case 3:
        i += 8;
        bigDecimal = (BigDecimal)paramObject;
        bigInteger = bigDecimal.unscaledValue();
        i += (bigInteger.toByteArray()).length;
      case 16:
        i++;
      case 91:
        i += 8;
      case 92:
        i += 8;
      case 94:
        i += 12;
      case 93:
        i += 12;
      case 95:
        i += 16;
      case 101:
      case 102:
      case 107:
        i += 8;
      case 103:
      case 104:
      case 105:
      case 106:
      case 108:
      case 109:
      case 110:
      case 111:
      case 112:
      case 113:
        i += 12;
      case 60:
      case 61:
        i += 4;
        i = (int)(i + ((BinaryData)paramObject).length(null));
      case 14:
      case 15:
        i += 4;
        i = (int)(i + ((BinaryData)paramObject).length(null));
      case 30:
      case 40:
        i += 8;
      case 50:
        i += 4;
        arrayOfObject = (Object[])paramObject;
        paramType = paramType.collectionBaseType();
        for (b = 0; b < arrayOfObject.length; b++)
          i += getSize(arrayOfObject[b], paramType); 
      case 1111:
        javaObjectData = (JavaObjectData)paramObject;
        i += 4;
        i += javaObjectData.getBytesLength();
    } 
    throw Error.runtimeError(201, "RowOutputBinary");
  }
  
  public void ensureRoom(int paramInt) {
    super.ensureRoom(paramInt);
  }
  
  public void reset() {
    super.reset();
    this.storageSize = 0;
  }
  
  public void reset(int paramInt) {
    super.reset(paramInt);
    this.storageSize = 0;
  }
  
  public void reset(byte[] paramArrayOfbyte) {
    super.reset(paramArrayOfbyte);
    this.storageSize = 0;
  }
  
  public RowOutputInterface duplicate() {
    return new RowOutputBinary(128, this.scale);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\rowio\RowOutputBinary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */