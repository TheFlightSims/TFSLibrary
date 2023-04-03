package org.hsqldb;

import java.math.BigDecimal;
import org.hsqldb.error.Error;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.map.ValuePool;
import org.hsqldb.rights.Grantee;
import org.hsqldb.types.Type;

public final class NumberSequence implements SchemaObject {
  public static final NumberSequence[] emptyArray = new NumberSequence[0];
  
  private HsqlNameManager.HsqlName name;
  
  private long currValue;
  
  private long lastValue;
  
  private boolean limitReached;
  
  private long startValue;
  
  private long minValue;
  
  private long maxValue;
  
  private long increment;
  
  private Type dataType;
  
  private boolean isCycle;
  
  private boolean isAlways;
  
  private boolean restartValueDefault;
  
  public NumberSequence() {
    try {
      setDefaults(null, (Type)Type.SQL_BIGINT);
    } catch (HsqlException hsqlException) {}
  }
  
  public NumberSequence(HsqlNameManager.HsqlName paramHsqlName, Type paramType) {
    setDefaults(paramHsqlName, paramType);
  }
  
  public void setDefaults(HsqlNameManager.HsqlName paramHsqlName, Type paramType) {
    long l1;
    long l2;
    this.name = paramHsqlName;
    this.dataType = paramType;
    this.name = paramHsqlName;
    switch (this.dataType.typeCode) {
      case -6:
        l2 = 127L;
        l1 = -128L;
        break;
      case 5:
        l2 = 32767L;
        l1 = -32768L;
        break;
      case 4:
        l2 = 2147483647L;
        l1 = -2147483648L;
        break;
      case 25:
        l2 = Long.MAX_VALUE;
        l1 = Long.MIN_VALUE;
        break;
      case 2:
      case 3:
        if (paramType.scale == 0) {
          l2 = Long.MAX_VALUE;
          l1 = Long.MIN_VALUE;
          break;
        } 
      default:
        throw Error.error(5563);
    } 
    this.minValue = l1;
    this.maxValue = l2;
    this.increment = 1L;
  }
  
  public NumberSequence(HsqlNameManager.HsqlName paramHsqlName, long paramLong1, long paramLong2, Type paramType) {
    this(paramHsqlName, paramType);
    setStartValue(paramLong1);
    setIncrement(paramLong2);
  }
  
  public int getType() {
    return 7;
  }
  
  public HsqlNameManager.HsqlName getName() {
    return this.name;
  }
  
  public HsqlNameManager.HsqlName getCatalogName() {
    return this.name.schema.schema;
  }
  
  public HsqlNameManager.HsqlName getSchemaName() {
    return this.name.schema;
  }
  
  public Grantee getOwner() {
    return this.name.schema.owner;
  }
  
  public OrderedHashSet getReferences() {
    return new OrderedHashSet();
  }
  
  public OrderedHashSet getComponents() {
    return null;
  }
  
  public void compile(Session paramSession, SchemaObject paramSchemaObject) {}
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer(128);
    stringBuffer.append("CREATE").append(' ');
    stringBuffer.append("SEQUENCE").append(' ');
    stringBuffer.append(getName().getSchemaQualifiedStatementName()).append(' ');
    stringBuffer.append("AS").append(' ');
    stringBuffer.append(getDataType().getNameString()).append(' ');
    stringBuffer.append("START").append(' ');
    stringBuffer.append("WITH").append(' ');
    stringBuffer.append(this.startValue);
    if (getIncrement() != 1L) {
      stringBuffer.append(' ').append("INCREMENT").append(' ');
      stringBuffer.append("BY").append(' ');
      stringBuffer.append(getIncrement());
    } 
    if (!hasDefaultMinMax()) {
      stringBuffer.append(' ').append("MINVALUE").append(' ');
      stringBuffer.append(getMinValue());
      stringBuffer.append(' ').append("MAXVALUE").append(' ');
      stringBuffer.append(getMaxValue());
    } 
    if (isCycle())
      stringBuffer.append(' ').append("CYCLE"); 
    if (this.name == null)
      stringBuffer.append(")"); 
    return stringBuffer.toString();
  }
  
  public String getSQLColumnDefinition() {
    StringBuffer stringBuffer = new StringBuffer(128);
    stringBuffer.append("GENERATED").append(' ');
    if (this.name == null) {
      if (isAlways()) {
        stringBuffer.append("ALWAYS");
      } else {
        stringBuffer.append("BY").append(' ').append("DEFAULT");
      } 
      stringBuffer.append(' ').append("AS").append(' ').append("IDENTITY").append("(");
      stringBuffer.append("START").append(' ');
      stringBuffer.append("WITH").append(' ');
      stringBuffer.append(this.startValue);
      if (getIncrement() != 1L) {
        stringBuffer.append(' ').append("INCREMENT").append(' ');
        stringBuffer.append("BY").append(' ');
        stringBuffer.append(getIncrement());
      } 
      if (!hasDefaultMinMax()) {
        stringBuffer.append(' ').append("MINVALUE").append(' ');
        stringBuffer.append(getMinValue());
        stringBuffer.append(' ').append("MAXVALUE").append(' ');
        stringBuffer.append(getMaxValue());
      } 
      if (isCycle())
        stringBuffer.append(' ').append("CYCLE"); 
      if (this.name == null)
        stringBuffer.append(")"); 
    } else {
      stringBuffer.append("BY").append(' ').append("DEFAULT");
      stringBuffer.append(' ').append("AS").append(' ');
      stringBuffer.append("SEQUENCE").append(' ');
      stringBuffer.append(getName().getSchemaQualifiedStatementName());
    } 
    return stringBuffer.toString();
  }
  
  public long getChangeTimestamp() {
    return 0L;
  }
  
  public String getRestartSQL() {
    StringBuffer stringBuffer = new StringBuffer(128);
    stringBuffer.append("ALTER").append(' ');
    stringBuffer.append("SEQUENCE");
    stringBuffer.append(' ').append(this.name.getSchemaQualifiedStatementName());
    stringBuffer.append(' ').append("RESTART");
    stringBuffer.append(' ').append("WITH").append(' ').append(peek());
    return stringBuffer.toString();
  }
  
  public static String getRestartSQL(Table paramTable) {
    String str = (paramTable.getColumn(paramTable.identityColumn).getName()).statementName;
    NumberSequence numberSequence = paramTable.identitySequence;
    StringBuffer stringBuffer = new StringBuffer(128);
    stringBuffer.append("ALTER").append(' ').append("TABLE");
    stringBuffer.append(' ').append(paramTable.getName().getSchemaQualifiedStatementName());
    stringBuffer.append(' ').append("ALTER").append(' ');
    stringBuffer.append("COLUMN");
    stringBuffer.append(' ').append(str);
    stringBuffer.append(' ').append("RESTART");
    stringBuffer.append(' ').append("WITH").append(' ').append(numberSequence.peek());
    return stringBuffer.toString();
  }
  
  public Type getDataType() {
    return this.dataType;
  }
  
  public long getIncrement() {
    return this.increment;
  }
  
  public synchronized long getStartValue() {
    return this.startValue;
  }
  
  public synchronized long getMinValue() {
    return this.minValue;
  }
  
  public synchronized long getMaxValue() {
    return this.maxValue;
  }
  
  public synchronized boolean isCycle() {
    return this.isCycle;
  }
  
  public synchronized boolean isAlways() {
    return this.isAlways;
  }
  
  public synchronized boolean hasDefaultMinMax() {
    long l1;
    long l2;
    switch (this.dataType.typeCode) {
      case -6:
        l2 = 127L;
        l1 = -128L;
        break;
      case 5:
        l2 = 32767L;
        l1 = -32768L;
        break;
      case 4:
        l2 = 2147483647L;
        l1 = -2147483648L;
        break;
      case 25:
        l2 = Long.MAX_VALUE;
        l1 = Long.MIN_VALUE;
        break;
      case 2:
      case 3:
        l2 = Long.MAX_VALUE;
        l1 = Long.MIN_VALUE;
        break;
      default:
        throw Error.runtimeError(201, "NumberSequence");
    } 
    return (this.minValue == l1 && this.maxValue == l2);
  }
  
  synchronized void setStartValue(long paramLong) {
    if (paramLong < this.minValue || paramLong > this.maxValue)
      throw Error.error(5597); 
    this.startValue = paramLong;
    this.currValue = this.lastValue = this.startValue;
  }
  
  synchronized void setMinValue(long paramLong) {
    checkInTypeRange(paramLong);
    if (paramLong >= this.maxValue || this.currValue < paramLong)
      throw Error.error(5597); 
    this.minValue = paramLong;
  }
  
  synchronized void setDefaultMinValue() {
    this.minValue = getDefaultMinOrMax(false);
  }
  
  synchronized void setMaxValue(long paramLong) {
    checkInTypeRange(paramLong);
    if (paramLong <= this.minValue || this.currValue > paramLong)
      throw Error.error(5597); 
    this.maxValue = paramLong;
  }
  
  synchronized void setDefaultMaxValue() {
    this.maxValue = getDefaultMinOrMax(true);
  }
  
  synchronized void setIncrement(long paramLong) {
    if (paramLong < -16384L || paramLong > 16383L)
      throw Error.error(5597); 
    this.increment = paramLong;
  }
  
  synchronized void setCurrentValueNoCheck(long paramLong) {
    checkInTypeRange(paramLong);
    this.currValue = this.lastValue = paramLong;
  }
  
  synchronized void setStartValueNoCheck(long paramLong) {
    checkInTypeRange(paramLong);
    this.startValue = paramLong;
    this.currValue = this.lastValue = this.startValue;
  }
  
  synchronized void setStartValueDefault() {
    this.restartValueDefault = true;
  }
  
  synchronized void setMinValueNoCheck(long paramLong) {
    checkInTypeRange(paramLong);
    this.minValue = paramLong;
  }
  
  synchronized void setMaxValueNoCheck(long paramLong) {
    checkInTypeRange(paramLong);
    this.maxValue = paramLong;
  }
  
  synchronized void setCycle(boolean paramBoolean) {
    this.isCycle = paramBoolean;
  }
  
  synchronized void setAlways(boolean paramBoolean) {
    this.isAlways = paramBoolean;
  }
  
  private long getDefaultMinOrMax(boolean paramBoolean) {
    long l1;
    long l2;
    switch (this.dataType.typeCode) {
      case -6:
        l2 = 127L;
        l1 = -128L;
        break;
      case 5:
        l2 = 32767L;
        l1 = -32768L;
        break;
      case 4:
        l2 = 2147483647L;
        l1 = -2147483648L;
        break;
      case 25:
        l2 = Long.MAX_VALUE;
        l1 = Long.MIN_VALUE;
        break;
      case 2:
      case 3:
        l2 = Long.MAX_VALUE;
        l1 = Long.MIN_VALUE;
        break;
      default:
        throw Error.runtimeError(201, "NumberSequence");
    } 
    return paramBoolean ? l2 : l1;
  }
  
  private void checkInTypeRange(long paramLong) {
    long l1;
    long l2;
    switch (this.dataType.typeCode) {
      case -6:
        l2 = 127L;
        l1 = -128L;
        break;
      case 5:
        l2 = 32767L;
        l1 = -32768L;
        break;
      case 4:
        l2 = 2147483647L;
        l1 = -2147483648L;
        break;
      case 25:
        l2 = Long.MAX_VALUE;
        l1 = Long.MIN_VALUE;
        break;
      case 2:
      case 3:
        l2 = Long.MAX_VALUE;
        l1 = Long.MIN_VALUE;
        break;
      default:
        throw Error.runtimeError(201, "NumberSequence");
    } 
    if (paramLong < l1 || paramLong > l2)
      throw Error.error(5597); 
  }
  
  synchronized void checkValues() {
    if (this.restartValueDefault) {
      this.currValue = this.lastValue = this.startValue;
      this.restartValueDefault = false;
    } 
    if (this.minValue >= this.maxValue || this.startValue < this.minValue || this.startValue > this.maxValue || this.currValue < this.minValue || this.currValue > this.maxValue)
      throw Error.error(5597); 
  }
  
  synchronized NumberSequence duplicate() {
    NumberSequence numberSequence = new NumberSequence();
    numberSequence.name = this.name;
    numberSequence.startValue = this.startValue;
    numberSequence.currValue = this.currValue;
    numberSequence.lastValue = this.lastValue;
    numberSequence.increment = this.increment;
    numberSequence.dataType = this.dataType;
    numberSequence.minValue = this.minValue;
    numberSequence.maxValue = this.maxValue;
    numberSequence.isCycle = this.isCycle;
    numberSequence.isAlways = this.isAlways;
    return numberSequence;
  }
  
  synchronized void reset(NumberSequence paramNumberSequence) {
    this.name = paramNumberSequence.name;
    this.startValue = paramNumberSequence.startValue;
    this.currValue = paramNumberSequence.currValue;
    this.lastValue = paramNumberSequence.lastValue;
    this.increment = paramNumberSequence.increment;
    this.dataType = paramNumberSequence.dataType;
    this.minValue = paramNumberSequence.minValue;
    this.maxValue = paramNumberSequence.maxValue;
    this.isCycle = paramNumberSequence.isCycle;
    this.isAlways = paramNumberSequence.isAlways;
  }
  
  synchronized long userUpdate(long paramLong) {
    if (paramLong == this.currValue) {
      this.currValue += this.increment;
      return paramLong;
    } 
    if (this.increment > 0L) {
      if (paramLong > this.currValue)
        this.currValue += (paramLong - this.currValue + this.increment) / this.increment * this.increment; 
    } else if (paramLong < this.currValue) {
      this.currValue += (paramLong - this.currValue + this.increment) / this.increment * this.increment;
    } 
    return paramLong;
  }
  
  synchronized long systemUpdate(long paramLong) {
    if (paramLong == this.currValue) {
      this.currValue += this.increment;
      return paramLong;
    } 
    if (this.increment > 0L) {
      if (paramLong > this.currValue)
        this.currValue = paramLong + this.increment; 
    } else if (paramLong < this.currValue) {
      this.currValue = paramLong + this.increment;
    } 
    return paramLong;
  }
  
  synchronized Object getValueObject() {
    long l = getValue();
    switch (this.dataType.typeCode) {
      default:
        return ValuePool.getInt((int)l);
      case 25:
        return ValuePool.getLong(l);
      case 2:
      case 3:
        break;
    } 
    return ValuePool.getBigDecimal(new BigDecimal(l));
  }
  
  public synchronized long getValue() {
    long l1;
    if (this.limitReached)
      throw Error.error(3416); 
    if (this.increment > 0L) {
      if (this.currValue > this.maxValue - this.increment) {
        if (this.isCycle) {
          l1 = this.minValue;
        } else {
          this.limitReached = true;
          l1 = this.minValue;
        } 
      } else {
        l1 = this.currValue + this.increment;
      } 
    } else if (this.currValue < this.minValue - this.increment) {
      if (this.isCycle) {
        l1 = this.maxValue;
      } else {
        this.limitReached = true;
        l1 = this.minValue;
      } 
    } else {
      l1 = this.currValue + this.increment;
    } 
    long l2 = this.currValue;
    this.currValue = l1;
    return l2;
  }
  
  synchronized void reset() {
    this.lastValue = this.currValue = this.startValue;
  }
  
  public synchronized long peek() {
    return this.currValue;
  }
  
  synchronized boolean resetWasUsed() {
    boolean bool = (this.lastValue != this.currValue) ? true : false;
    this.lastValue = this.currValue;
    return bool;
  }
  
  public synchronized void reset(long paramLong) {
    if (paramLong < this.minValue || paramLong > this.maxValue)
      throw Error.error(5597); 
    this.startValue = this.currValue = this.lastValue = paramLong;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\NumberSequence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */