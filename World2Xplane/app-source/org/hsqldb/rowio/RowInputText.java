package org.hsqldb.rowio;

import java.io.IOException;
import java.math.BigDecimal;
import org.hsqldb.Scanner;
import org.hsqldb.error.Error;
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

public class RowInputText extends RowInputBase implements RowInputInterface {
  private String fieldSep;
  
  private String varSep;
  
  private String longvarSep;
  
  private int fieldSepLen;
  
  private int varSepLen;
  
  private int longvarSepLen;
  
  private boolean fieldSepEnd;
  
  private boolean varSepEnd;
  
  private boolean longvarSepEnd;
  
  private int textLen;
  
  protected String text;
  
  protected int line;
  
  protected int field;
  
  protected int next = 0;
  
  protected boolean allQuoted;
  
  protected Scanner scanner = new Scanner();
  
  private int maxPooledStringLength = ValuePool.getMaxStringLength();
  
  public RowInputText(String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
    super(new byte[0]);
    if (paramString1.endsWith("\n")) {
      this.fieldSepEnd = true;
      paramString1 = paramString1.substring(0, paramString1.length() - 1);
    } 
    if (paramString2.endsWith("\n")) {
      this.varSepEnd = true;
      paramString2 = paramString2.substring(0, paramString2.length() - 1);
    } 
    if (paramString3.endsWith("\n")) {
      this.longvarSepEnd = true;
      paramString3 = paramString3.substring(0, paramString3.length() - 1);
    } 
    this.allQuoted = paramBoolean;
    this.fieldSep = paramString1;
    this.varSep = paramString2;
    this.longvarSep = paramString3;
    this.fieldSepLen = paramString1.length();
    this.varSepLen = paramString2.length();
    this.longvarSepLen = paramString3.length();
  }
  
  public void setSource(String paramString, long paramLong, int paramInt) {
    this.size = paramInt;
    this.text = paramString;
    this.textLen = paramString.length();
    this.filePos = paramLong;
    this.next = 0;
    this.line++;
    this.field = 0;
  }
  
  protected String getField(String paramString, int paramInt, boolean paramBoolean) throws IOException {
    String str = null;
    try {
      int i = this.next;
      this.field++;
      if (paramBoolean) {
        if (this.next >= this.textLen && paramInt > 0)
          throw Error.error(488); 
        if (this.text.endsWith(paramString)) {
          this.next = this.textLen - paramInt;
        } else {
          throw Error.error(488);
        } 
      } else {
        this.next = this.text.indexOf(paramString, i);
        if (this.next == -1)
          this.next = this.textLen; 
      } 
      if (i > this.next)
        i = this.next; 
      str = this.text.substring(i, this.next);
      this.next += paramInt;
      int j = str.trim().length();
      if (j == 0) {
        str = null;
      } else if (j < str.length()) {
        for (j = str.length() - 1; str.charAt(j) < ' '; j--);
        str = str.substring(0, j + 1);
      } 
    } catch (Exception exception) {
      Object[] arrayOfObject = { new Integer(this.field), exception.toString() };
      throw new IOException(Error.getMessage(41, 0, arrayOfObject));
    } 
    return str;
  }
  
  public String readString() throws IOException {
    return getField(this.fieldSep, this.fieldSepLen, this.fieldSepEnd);
  }
  
  private String readVarString() throws IOException {
    return getField(this.varSep, this.varSepLen, this.varSepEnd);
  }
  
  private String readLongVarString() throws IOException {
    return getField(this.longvarSep, this.longvarSepLen, this.longvarSepEnd);
  }
  
  public short readShort() throws IOException {
    return (short)readInt();
  }
  
  public int readInt() throws IOException {
    String str = readString();
    if (str == null)
      return 0; 
    str = str.trim();
    return (str.length() == 0) ? 0 : Integer.parseInt(str);
  }
  
  public long readLong() throws IOException {
    throw Error.runtimeError(201, "RowInputText");
  }
  
  public int readType() throws IOException {
    return 0;
  }
  
  protected boolean readNull() {
    return false;
  }
  
  protected String readChar(Type paramType) throws IOException {
    String str = null;
    switch (paramType.typeCode) {
      case 1:
        str = readString();
        break;
      case 12:
        str = readVarString();
        break;
      default:
        str = readLongVarString();
        break;
    } 
    return (str == null) ? null : ((str.length() > this.maxPooledStringLength) ? new String(str) : ValuePool.getString(str));
  }
  
  protected Integer readSmallint() throws IOException {
    String str = readString();
    if (str == null)
      return null; 
    str = str.trim();
    return (str.length() == 0) ? null : ValuePool.getInt(Integer.parseInt(str));
  }
  
  protected Integer readInteger() throws IOException {
    String str = readString();
    if (str == null)
      return null; 
    str = str.trim();
    return (str.length() == 0) ? null : ValuePool.getInt(Integer.parseInt(str));
  }
  
  protected Long readBigint() throws IOException {
    String str = readString();
    if (str == null)
      return null; 
    str = str.trim();
    return (str.length() == 0) ? null : ValuePool.getLong(Long.parseLong(str));
  }
  
  protected Double readReal() throws IOException {
    String str = readString();
    if (str == null)
      return null; 
    str = str.trim();
    return (str.length() == 0) ? null : Double.valueOf(str);
  }
  
  protected BigDecimal readDecimal(Type paramType) throws IOException {
    String str = readString();
    if (str == null)
      return null; 
    str = str.trim();
    return (str.length() == 0) ? null : new BigDecimal(str);
  }
  
  protected TimeData readTime(Type paramType) throws IOException {
    String str = readString();
    if (str == null)
      return null; 
    str = str.trim();
    return (str.length() == 0) ? null : this.scanner.newTime(str);
  }
  
  protected TimestampData readDate(Type paramType) throws IOException {
    String str = readString();
    if (str == null)
      return null; 
    str = str.trim();
    return (str.length() == 0) ? null : this.scanner.newDate(str);
  }
  
  protected TimestampData readTimestamp(Type paramType) throws IOException {
    String str = readString();
    if (str == null)
      return null; 
    str = str.trim();
    return (str.length() == 0) ? null : this.scanner.newTimestamp(str);
  }
  
  protected IntervalMonthData readYearMonthInterval(Type paramType) throws IOException {
    String str = readString();
    if (str == null)
      return null; 
    str = str.trim();
    return (str.length() == 0) ? null : (IntervalMonthData)this.scanner.newInterval(str, (IntervalType)paramType);
  }
  
  protected IntervalSecondData readDaySecondInterval(Type paramType) throws IOException {
    String str = readString();
    if (str == null)
      return null; 
    str = str.trim();
    return (str.length() == 0) ? null : (IntervalSecondData)this.scanner.newInterval(str, (IntervalType)paramType);
  }
  
  protected Boolean readBoole() throws IOException {
    String str = readString();
    if (str == null)
      return null; 
    str = str.trim();
    return (str.length() == 0) ? null : (str.equalsIgnoreCase("TRUE") ? Boolean.TRUE : Boolean.FALSE);
  }
  
  protected Object readOther() throws IOException {
    String str = readString();
    if (str == null)
      return null; 
    BinaryData binaryData = this.scanner.convertToBinary(str);
    return (binaryData.length(null) == 0L) ? null : new JavaObjectData(binaryData.getBytes());
  }
  
  protected BinaryData readBit() throws IOException {
    String str = readString();
    return (str == null) ? null : this.scanner.convertToBit(str);
  }
  
  protected BinaryData readBinary() throws IOException {
    String str = readString();
    return (str == null) ? null : this.scanner.convertToBinary(str);
  }
  
  protected ClobData readClob() throws IOException {
    String str = readString();
    if (str == null)
      return null; 
    str = str.trim();
    if (str.length() == 0)
      return null; 
    long l = Long.parseLong(str);
    return (ClobData)new ClobDataID(l);
  }
  
  protected BlobData readBlob() throws IOException {
    String str = readString();
    if (str == null)
      return null; 
    str = str.trim();
    if (str.length() == 0)
      return null; 
    long l = Long.parseLong(str);
    return (BlobData)new BlobDataID(l);
  }
  
  protected Object[] readArray(Type paramType) {
    throw Error.runtimeError(201, "RowInputText");
  }
  
  public int getLineNumber() {
    return this.line;
  }
  
  public void skippedLine() {
    this.line++;
  }
  
  public void reset() {
    this.text = "";
    this.textLen = 0;
    this.filePos = 0L;
    this.next = 0;
    this.field = 0;
    this.line = 0;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\rowio\RowInputText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */