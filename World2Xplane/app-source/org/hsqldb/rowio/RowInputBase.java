package org.hsqldb.rowio;

import java.io.IOException;
import java.math.BigDecimal;
import org.hsqldb.HsqlException;
import org.hsqldb.error.Error;
import org.hsqldb.lib.HsqlByteArrayInputStream;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BlobData;
import org.hsqldb.types.ClobData;
import org.hsqldb.types.IntervalMonthData;
import org.hsqldb.types.IntervalSecondData;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

abstract class RowInputBase extends HsqlByteArrayInputStream {
  static final int NO_POS = -1;
  
  protected long filePos = -1L;
  
  protected int size;
  
  RowInputBase() {
    this(new byte[4]);
  }
  
  RowInputBase(byte[] paramArrayOfbyte) {
    super(paramArrayOfbyte);
    this.size = paramArrayOfbyte.length;
  }
  
  public long getPos() {
    if (this.filePos == -1L);
    return this.filePos;
  }
  
  public int getSize() {
    return this.size;
  }
  
  public abstract int readType() throws IOException;
  
  public abstract String readString() throws IOException;
  
  protected abstract boolean readNull() throws IOException;
  
  protected abstract String readChar(Type paramType) throws IOException;
  
  protected abstract Integer readSmallint() throws IOException;
  
  protected abstract Integer readInteger() throws IOException;
  
  protected abstract Long readBigint() throws IOException;
  
  protected abstract Double readReal() throws IOException;
  
  protected abstract BigDecimal readDecimal(Type paramType) throws IOException;
  
  protected abstract Boolean readBoole() throws IOException;
  
  protected abstract TimeData readTime(Type paramType) throws IOException;
  
  protected abstract TimestampData readDate(Type paramType) throws IOException;
  
  protected abstract TimestampData readTimestamp(Type paramType) throws IOException;
  
  protected abstract IntervalMonthData readYearMonthInterval(Type paramType) throws IOException;
  
  protected abstract IntervalSecondData readDaySecondInterval(Type paramType) throws IOException;
  
  protected abstract Object readOther() throws IOException;
  
  protected abstract BinaryData readBinary() throws IOException, HsqlException;
  
  protected abstract BinaryData readBit() throws IOException;
  
  protected abstract ClobData readClob() throws IOException;
  
  protected abstract BlobData readBlob() throws IOException;
  
  protected abstract Object[] readArray(Type paramType) throws IOException;
  
  public Object[] readData(Type[] paramArrayOfType) throws IOException {
    int i = paramArrayOfType.length;
    Object[] arrayOfObject = new Object[i];
    for (byte b = 0; b < i; b++) {
      Type type = paramArrayOfType[b];
      arrayOfObject[b] = readData(type);
    } 
    return arrayOfObject;
  }
  
  public Object readData(Type paramType) throws IOException {
    Integer integer;
    Long long_;
    Double double_;
    BigDecimal bigDecimal;
    TimestampData timestampData2;
    TimeData timeData;
    TimestampData timestampData1;
    IntervalMonthData intervalMonthData;
    IntervalSecondData intervalSecondData;
    Boolean bool;
    Object object;
    String str = null;
    if (readNull())
      return null; 
    switch (paramType.typeCode) {
      case 0:
        return str;
      case 1:
      case 12:
        str = readChar(paramType);
      case -6:
      case 5:
        integer = readSmallint();
      case 4:
        integer = readInteger();
      case 25:
        long_ = readBigint();
      case 6:
      case 7:
      case 8:
        double_ = readReal();
      case 2:
      case 3:
        bigDecimal = readDecimal(paramType);
      case 91:
        timestampData2 = readDate(paramType);
      case 92:
      case 94:
        timeData = readTime(paramType);
      case 93:
      case 95:
        timestampData1 = readTimestamp(paramType);
      case 101:
      case 102:
      case 107:
        intervalMonthData = readYearMonthInterval(paramType);
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
        intervalSecondData = readDaySecondInterval(paramType);
      case 16:
        bool = readBoole();
      case 1111:
        object = readOther();
      case 40:
        object = readClob();
      case 30:
        object = readBlob();
      case 50:
        object = readArray(paramType);
      case 60:
      case 61:
        object = readBinary();
      case 14:
      case 15:
        object = readBit();
    } 
    throw Error.runtimeError(201, "RowInputBase - " + paramType.getNameString());
  }
  
  public void resetRow(long paramLong, int paramInt) {
    this.mark = 0;
    reset();
    if (this.buffer.length < paramInt)
      this.buffer = new byte[paramInt]; 
    this.filePos = paramLong;
    this.size = this.count = paramInt;
    this.pos = 4;
    this.buffer[0] = (byte)(paramInt >>> 24 & 0xFF);
    this.buffer[1] = (byte)(paramInt >>> 16 & 0xFF);
    this.buffer[2] = (byte)(paramInt >>> 8 & 0xFF);
    this.buffer[3] = (byte)(paramInt >>> 0 & 0xFF);
  }
  
  public void resetBlock(long paramLong, int paramInt) {
    this.mark = 0;
    reset();
    if (this.buffer.length < paramInt)
      this.buffer = new byte[paramInt]; 
    this.filePos = paramLong;
    this.size = this.count = paramInt;
  }
  
  public byte[] getBuffer() {
    return this.buffer;
  }
  
  public int skipBytes(int paramInt) throws IOException {
    throw Error.runtimeError(201, "RowInputBase");
  }
  
  public String readLine() throws IOException {
    throw Error.runtimeError(201, "RowInputBase");
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\rowio\RowInputBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */