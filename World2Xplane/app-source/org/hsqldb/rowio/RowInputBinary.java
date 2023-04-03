package org.hsqldb.rowio;

import java.io.EOFException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.map.ValuePool;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BlobData;
import org.hsqldb.types.BlobDataID;
import org.hsqldb.types.ClobData;
import org.hsqldb.types.ClobDataID;
import org.hsqldb.types.IntervalMonthData;
import org.hsqldb.types.IntervalSecondData;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.JavaObjectData;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

public class RowInputBinary extends RowInputBase implements RowInputInterface {
  public boolean ignoreDataErrors;
  
  private RowOutputBinary out;
  
  public RowInputBinary(byte[] paramArrayOfbyte) {
    super(paramArrayOfbyte);
  }
  
  public RowInputBinary(RowOutputBinary paramRowOutputBinary) {
    super(paramRowOutputBinary.getBuffer());
    this.out = paramRowOutputBinary;
  }
  
  public int readType() throws IOException {
    return readShort();
  }
  
  public String readString() throws IOException {
    int i = readInt();
    String str = StringConverter.readUTF(this.buffer, this.pos, i);
    str = ValuePool.getString(str);
    this.pos += i;
    return str;
  }
  
  public boolean readNull() throws IOException {
    byte b = readByte();
    return (b == 0);
  }
  
  protected String readChar(Type paramType) throws IOException {
    return readString();
  }
  
  protected Integer readSmallint() throws IOException {
    return ValuePool.getInt(readShort());
  }
  
  protected Integer readInteger() throws IOException {
    return ValuePool.getInt(readInt());
  }
  
  protected Long readBigint() throws IOException {
    return ValuePool.getLong(readLong());
  }
  
  protected Double readReal() throws IOException {
    return ValuePool.getDouble(readLong());
  }
  
  protected BigDecimal readDecimal(Type paramType) throws IOException {
    byte[] arrayOfByte = readByteArray();
    int i = readInt();
    BigInteger bigInteger = new BigInteger(arrayOfByte);
    return ValuePool.getBigDecimal(new BigDecimal(bigInteger, i));
  }
  
  protected Boolean readBoole() throws IOException {
    return readBoolean() ? Boolean.TRUE : Boolean.FALSE;
  }
  
  protected TimeData readTime(Type paramType) throws IOException {
    return (paramType.typeCode == 92) ? new TimeData(readInt(), readInt(), 0) : new TimeData(readInt(), readInt(), readInt());
  }
  
  protected TimestampData readDate(Type paramType) throws IOException {
    long l = readLong();
    return new TimestampData(l);
  }
  
  protected TimestampData readTimestamp(Type paramType) throws IOException {
    return (paramType.typeCode == 93) ? new TimestampData(readLong(), readInt()) : new TimestampData(readLong(), readInt(), readInt());
  }
  
  protected IntervalMonthData readYearMonthInterval(Type paramType) throws IOException {
    long l = readLong();
    return new IntervalMonthData(l, (IntervalType)paramType);
  }
  
  protected IntervalSecondData readDaySecondInterval(Type paramType) throws IOException {
    long l = readLong();
    int i = readInt();
    return new IntervalSecondData(l, i, (IntervalType)paramType);
  }
  
  protected Object readOther() throws IOException {
    return new JavaObjectData(readByteArray());
  }
  
  protected BinaryData readBit() throws IOException {
    int i = readInt();
    byte[] arrayOfByte = new byte[(i + 7) / 8];
    readFully(arrayOfByte);
    return BinaryData.getBitData(arrayOfByte, i);
  }
  
  protected BinaryData readBinary() throws IOException {
    return new BinaryData(readByteArray(), false);
  }
  
  protected ClobData readClob() throws IOException {
    long l = readLong();
    return (ClobData)new ClobDataID(l);
  }
  
  protected BlobData readBlob() throws IOException {
    long l = readLong();
    return (BlobData)new BlobDataID(l);
  }
  
  protected Object[] readArray(Type paramType) throws IOException {
    paramType = paramType.collectionBaseType();
    int i = readInt();
    Object[] arrayOfObject = new Object[i];
    for (byte b = 0; b < i; b++)
      arrayOfObject[b] = readData(paramType); 
    return arrayOfObject;
  }
  
  public int[] readIntArray() throws IOException {
    int i = readInt();
    int[] arrayOfInt = new int[i];
    for (byte b = 0; b < i; b++) {
      if (!readNull())
        arrayOfInt[b] = readInt(); 
    } 
    return arrayOfInt;
  }
  
  public Object[] readData(Type[] paramArrayOfType) throws IOException {
    return this.ignoreDataErrors ? new Object[paramArrayOfType.length] : super.readData(paramArrayOfType);
  }
  
  public byte[] readByteArray() throws IOException {
    byte[] arrayOfByte = new byte[readInt()];
    readFully(arrayOfByte);
    return arrayOfByte;
  }
  
  public char[] readCharArray() throws IOException {
    char[] arrayOfChar = new char[readInt()];
    if (this.count - this.pos < arrayOfChar.length) {
      this.pos = this.count;
      throw new EOFException();
    } 
    for (byte b = 0; b < arrayOfChar.length; b++) {
      int i = this.buffer[this.pos++] & 0xFF;
      int j = this.buffer[this.pos++] & 0xFF;
      arrayOfChar[b] = (char)((i << 8) + j);
    } 
    return arrayOfChar;
  }
  
  public void resetRow(int paramInt) {
    if (this.out != null) {
      this.out.reset(paramInt);
      this.buffer = this.out.getBuffer();
    } 
    reset();
  }
  
  public void resetRow(long paramLong, int paramInt) {
    if (this.out != null) {
      this.out.reset(paramInt);
      this.buffer = this.out.getBuffer();
    } 
    super.resetRow(paramLong, paramInt);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\rowio\RowInputBinary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */