package org.hsqldb.rowio;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.HsqlException;
import org.hsqldb.Scanner;
import org.hsqldb.error.Error;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.map.ValuePool;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BlobData;
import org.hsqldb.types.BlobDataID;
import org.hsqldb.types.ClobData;
import org.hsqldb.types.ClobDataID;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.IntervalMonthData;
import org.hsqldb.types.IntervalSecondData;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.JavaObjectData;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

public class RowInputTextLog extends RowInputBase implements RowInputInterface {
  Scanner scanner = new Scanner();
  
  String tableName = null;
  
  String schemaName = null;
  
  int statementType;
  
  Object value;
  
  boolean version18;
  
  boolean noSeparators;
  
  Calendar tempCalDefault = new GregorianCalendar();
  
  public RowInputTextLog() {
    super(new byte[0]);
  }
  
  public RowInputTextLog(boolean paramBoolean) {
    super(new byte[0]);
    this.version18 = paramBoolean;
  }
  
  public void setSource(String paramString) {
    this.scanner.reset(paramString);
    this.statementType = 1;
    this.scanner.scanNext();
    String str = this.scanner.getString();
    if (str.equals("INSERT")) {
      this.statementType = 3;
      this.scanner.scanNext();
      this.scanner.scanNext();
      this.tableName = this.scanner.getString();
      this.scanner.scanNext();
    } else if (str.equals("DELETE")) {
      this.statementType = 2;
      this.scanner.scanNext();
      this.scanner.scanNext();
      this.tableName = this.scanner.getString();
    } else if (str.equals("COMMIT")) {
      this.statementType = 4;
    } else if (str.equals("SET")) {
      this.scanner.scanNext();
      if ("SCHEMA".equals(this.scanner.getString())) {
        this.scanner.scanNext();
        this.schemaName = this.scanner.getString();
        this.statementType = 6;
      } 
    } 
  }
  
  public int getStatementType() {
    return this.statementType;
  }
  
  public String getTableName() {
    return this.tableName;
  }
  
  public String getSchemaName() {
    return this.schemaName;
  }
  
  protected void readField() {
    readFieldPrefix();
    this.scanner.scanNext();
    this.value = this.scanner.getValue();
  }
  
  protected void readNumberField(Type paramType) {
    readFieldPrefix();
    this.scanner.scanNext();
    boolean bool = (this.scanner.getTokenType() == 814) ? true : false;
    if (bool)
      this.scanner.scanNext(); 
    this.value = this.scanner.getValue();
    if (bool)
      try {
        this.value = ((NumberType)this.scanner.getDataType()).negate(this.value);
      } catch (HsqlException hsqlException) {} 
  }
  
  protected void readFieldPrefix() {
    if (!this.noSeparators) {
      this.scanner.scanNext();
      if (this.statementType == 2) {
        this.scanner.scanNext();
        this.scanner.scanNext();
      } 
    } 
  }
  
  public String readString() throws IOException {
    readField();
    return (String)this.value;
  }
  
  public short readShort() throws IOException {
    throw Error.runtimeError(201, "");
  }
  
  public int readInt() throws IOException {
    throw Error.runtimeError(201, "");
  }
  
  public long readLong() throws IOException {
    throw Error.runtimeError(201, "");
  }
  
  public int readType() throws IOException {
    return 0;
  }
  
  protected boolean readNull() {
    return false;
  }
  
  protected String readChar(Type paramType) throws IOException {
    readField();
    return (String)this.value;
  }
  
  protected Integer readSmallint() throws IOException {
    readNumberField((Type)Type.SQL_SMALLINT);
    return (Integer)this.value;
  }
  
  protected Integer readInteger() throws IOException {
    readNumberField((Type)Type.SQL_INTEGER);
    if (this.value instanceof Long)
      this.value = Type.SQL_INTEGER.convertToDefaultType(null, this.value); 
    return (Integer)this.value;
  }
  
  protected Long readBigint() throws IOException {
    readNumberField((Type)Type.SQL_BIGINT);
    return (this.value == null) ? null : ((this.value instanceof BigDecimal) ? (Long)Type.SQL_BIGINT.convertToDefaultType(null, this.value) : ValuePool.getLong(((Number)this.value).longValue()));
  }
  
  protected Double readReal() throws IOException {
    readNumberField((Type)Type.SQL_DOUBLE);
    if (this.value == null)
      return null; 
    if (this.scanner.scanSpecialIdentifier("/")) {
      this.scanner.scanNext();
      Object object = this.scanner.getValue();
      double d = ((Number)object).doubleValue();
      if (d == 0.0D) {
        if (((Number)this.value).doubleValue() == 1.0D) {
          d = Double.NEGATIVE_INFINITY;
        } else if (((Number)this.value).doubleValue() == -1.0D) {
          d = Double.POSITIVE_INFINITY;
        } else if (((Number)this.value).doubleValue() == 0.0D) {
          d = Double.NaN;
        } else {
          throw Error.error(5584);
        } 
      } else {
        throw Error.error(5584);
      } 
      this.value = Double.valueOf(d);
    } 
    return (Double)this.value;
  }
  
  protected BigDecimal readDecimal(Type paramType) throws IOException {
    readNumberField(paramType);
    return (this.value == null) ? null : (BigDecimal)paramType.convertToDefaultType(null, this.value);
  }
  
  protected TimeData readTime(Type paramType) throws IOException {
    readField();
    if (this.value == null)
      return null; 
    if (this.version18) {
      Time time = Time.valueOf((String)this.value);
      long l = HsqlDateTime.convertMillisFromCalendar(this.tempCalDefault, time.getTime());
      l = HsqlDateTime.getNormalisedTime(l);
      return new TimeData((int)l / 1000, 0, 0);
    } 
    return this.scanner.newTime((String)this.value);
  }
  
  protected TimestampData readDate(Type paramType) throws IOException {
    readField();
    if (this.value == null)
      return null; 
    if (this.version18) {
      Date date = Date.valueOf((String)this.value);
      long l = HsqlDateTime.convertMillisFromCalendar(this.tempCalDefault, date.getTime());
      l = HsqlDateTime.getNormalisedDate(l);
      return new TimestampData(l / 1000L);
    } 
    return this.scanner.newDate((String)this.value);
  }
  
  protected TimestampData readTimestamp(Type paramType) throws IOException {
    readField();
    if (this.value == null)
      return null; 
    if (this.version18) {
      Timestamp timestamp = Timestamp.valueOf((String)this.value);
      long l = HsqlDateTime.convertMillisFromCalendar(this.tempCalDefault, timestamp.getTime());
      int i = timestamp.getNanos();
      (DateTimeType)paramType;
      i = DateTimeType.normaliseFraction(i, paramType.scale);
      return new TimestampData(l / 1000L, i, 0);
    } 
    return this.scanner.newTimestamp((String)this.value);
  }
  
  protected IntervalMonthData readYearMonthInterval(Type paramType) throws IOException {
    readField();
    return (this.value == null) ? null : (IntervalMonthData)this.scanner.newInterval((String)this.value, (IntervalType)paramType);
  }
  
  protected IntervalSecondData readDaySecondInterval(Type paramType) throws IOException {
    readField();
    return (this.value == null) ? null : (IntervalSecondData)this.scanner.newInterval((String)this.value, (IntervalType)paramType);
  }
  
  protected Boolean readBoole() throws IOException {
    readFieldPrefix();
    this.scanner.scanNext();
    String str = this.scanner.getString();
    this.value = null;
    if (str.equalsIgnoreCase("TRUE")) {
      this.value = Boolean.TRUE;
    } else if (str.equalsIgnoreCase("FALSE")) {
      this.value = Boolean.FALSE;
    } 
    return (Boolean)this.value;
  }
  
  protected Object readOther() throws IOException {
    readFieldPrefix();
    if (this.scanner.scanNull())
      return null; 
    this.scanner.scanBinaryStringWithQuote();
    if (this.scanner.getTokenType() == 880)
      throw Error.error(5587); 
    this.value = this.scanner.getValue();
    return new JavaObjectData(((BinaryData)this.value).getBytes());
  }
  
  protected BinaryData readBit() throws IOException {
    readFieldPrefix();
    if (this.scanner.scanNull())
      return null; 
    this.scanner.scanBitStringWithQuote();
    if (this.scanner.getTokenType() == 879)
      throw Error.error(5587); 
    this.value = this.scanner.getValue();
    return (BinaryData)this.value;
  }
  
  protected BinaryData readBinary() throws IOException {
    readFieldPrefix();
    if (this.scanner.scanNull())
      return null; 
    this.scanner.scanBinaryStringWithQuote();
    if (this.scanner.getTokenType() == 880)
      throw Error.error(5587); 
    this.value = this.scanner.getValue();
    return (BinaryData)this.value;
  }
  
  protected ClobData readClob() throws IOException {
    readNumberField((Type)Type.SQL_BIGINT);
    if (this.value == null)
      return null; 
    long l = ((Number)this.value).longValue();
    return (ClobData)new ClobDataID(l);
  }
  
  protected BlobData readBlob() throws IOException {
    readNumberField((Type)Type.SQL_BIGINT);
    if (this.value == null)
      return null; 
    long l = ((Number)this.value).longValue();
    return (BlobData)new BlobDataID(l);
  }
  
  protected Object[] readArray(Type paramType) throws IOException {
    paramType = paramType.collectionBaseType();
    readFieldPrefix();
    this.scanner.scanNext();
    String str = this.scanner.getString();
    this.value = null;
    if (str.equalsIgnoreCase("NULL"))
      return null; 
    if (!str.equalsIgnoreCase("ARRAY"))
      throw Error.error(5584); 
    this.scanner.scanNext();
    str = this.scanner.getString();
    if (!str.equalsIgnoreCase("["))
      throw Error.error(5584); 
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    this.noSeparators = true;
    for (byte b = 0; !this.scanner.scanSpecialIdentifier("]"); b++) {
      if (b && !this.scanner.scanSpecialIdentifier(","))
        throw Error.error(5584); 
      Object object = readData(paramType);
      hsqlArrayList.add(object);
    } 
    this.noSeparators = false;
    Object[] arrayOfObject = new Object[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfObject);
    return arrayOfObject;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\rowio\RowInputTextLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */