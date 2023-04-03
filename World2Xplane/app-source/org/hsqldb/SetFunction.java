package org.hsqldb;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.hsqldb.error.Error;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.ObjectComparator;
import org.hsqldb.map.ValuePool;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.DTIType;
import org.hsqldb.types.IntervalMonthData;
import org.hsqldb.types.IntervalSecondData;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.RowType;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

public class SetFunction implements Serializable {
  private HashSet distinctValues;
  
  private boolean isDistinct;
  
  private int setType;
  
  private int typeCode;
  
  private Type type;
  
  private ArrayType arrayType;
  
  private Type returnType;
  
  private long count;
  
  private boolean hasNull;
  
  private boolean every = true;
  
  private boolean some = false;
  
  private long currentLong;
  
  private double currentDouble;
  
  private BigDecimal currentBigDecimal;
  
  private Object currentValue;
  
  static final BigInteger multiplier = BigInteger.valueOf(4294967296L);
  
  long hi;
  
  long lo;
  
  private double sk;
  
  private double vk;
  
  private long n;
  
  private boolean initialized;
  
  private boolean sample;
  
  SetFunction(Session paramSession, int paramInt, Type paramType1, Type paramType2, boolean paramBoolean, ArrayType paramArrayType) {
    this.setType = paramInt;
    this.type = paramType1;
    this.returnType = paramType2;
    if (paramBoolean) {
      this.isDistinct = true;
      this.arrayType = paramArrayType;
      this.distinctValues = new HashSet();
      if (paramType1.isRowType() || paramType1.isArrayType()) {
        Type.TypedComparator typedComparator = Type.newComparator(paramSession);
        SortAndSlice sortAndSlice = new SortAndSlice();
        boolean bool = paramType1.isRowType() ? (((RowType)paramType1).getTypesArray()).length : true;
        sortAndSlice.prepareMultiColumn(bool);
        typedComparator.setType(paramType1, sortAndSlice);
        this.distinctValues.setComparator((ObjectComparator)typedComparator);
      } 
    } 
    if (paramInt == 81 || paramInt == 79)
      this.sample = true; 
    if (paramType1 != null) {
      this.typeCode = paramType1.typeCode;
      if (paramType1.isIntervalType())
        this.typeCode = 10; 
    } 
  }
  
  void add(Session paramSession, Object paramObject) {
    if (paramObject == null) {
      this.hasNull = true;
      return;
    } 
    if (this.isDistinct && !this.distinctValues.add(paramObject))
      return; 
    this.count++;
    switch (this.setType) {
      case 71:
        return;
      case 72:
      case 75:
        switch (this.typeCode) {
          case -6:
          case 4:
          case 5:
            this.currentLong += ((Number)paramObject).intValue();
            return;
          case 10:
            if (paramObject instanceof IntervalSecondData) {
              addLong(((IntervalSecondData)paramObject).getSeconds());
              this.currentLong += ((IntervalSecondData)paramObject).getNanos();
              if (Math.abs(this.currentLong) >= DTIType.nanoScaleFactors[0]) {
                addLong(this.currentLong / DTIType.nanoScaleFactors[0]);
                this.currentLong %= DTIType.nanoScaleFactors[0];
              } 
            } else if (paramObject instanceof IntervalMonthData) {
              addLong(((IntervalMonthData)paramObject).units);
            } 
            return;
          case 91:
          case 93:
          case 95:
            addLong(((TimestampData)paramObject).getSeconds());
            this.currentLong += ((TimestampData)paramObject).getNanos();
            if (Math.abs(this.currentLong) >= DTIType.nanoScaleFactors[0]) {
              addLong(this.currentLong / DTIType.nanoScaleFactors[0]);
              this.currentLong %= DTIType.nanoScaleFactors[0];
            } 
            this.currentDouble = ((TimestampData)paramObject).getZone();
            return;
          case 25:
            addLong(((Number)paramObject).longValue());
            return;
          case 6:
          case 7:
          case 8:
            this.currentDouble += ((Number)paramObject).doubleValue();
            return;
          case 2:
          case 3:
            if (this.currentBigDecimal == null) {
              this.currentBigDecimal = (BigDecimal)paramObject;
            } else {
              this.currentBigDecimal = this.currentBigDecimal.add((BigDecimal)paramObject);
            } 
            return;
        } 
        throw Error.error(5563);
      case 73:
        if (this.currentValue == null) {
          this.currentValue = paramObject;
          return;
        } 
        if (this.type.compare(paramSession, this.currentValue, paramObject) > 0)
          this.currentValue = paramObject; 
        return;
      case 74:
        if (this.currentValue == null) {
          this.currentValue = paramObject;
          return;
        } 
        if (this.type.compare(paramSession, this.currentValue, paramObject) < 0)
          this.currentValue = paramObject; 
        return;
      case 76:
        if (!(paramObject instanceof Boolean))
          throw Error.error(5563); 
        this.every = (this.every && ((Boolean)paramObject).booleanValue());
        return;
      case 77:
        if (!(paramObject instanceof Boolean))
          throw Error.error(5563); 
        this.some = (this.some || ((Boolean)paramObject).booleanValue());
        return;
      case 78:
      case 79:
      case 80:
      case 81:
        addDataPoint((Number)paramObject);
        return;
      case 98:
        this.currentValue = paramObject;
        return;
    } 
    throw Error.runtimeError(201, "SetFunction");
  }
  
  Object getValue(Session paramSession) {
    long l;
    BigInteger bigInteger;
    if (this.hasNull)
      paramSession.addWarning(Error.error(1003)); 
    if (this.setType == 71) {
      if (this.count > 0L && this.isDistinct && this.type.isCharacterType()) {
        Object[] arrayOfObject = new Object[this.distinctValues.size()];
        this.distinctValues.toArray(arrayOfObject);
        SortAndSlice sortAndSlice = new SortAndSlice();
        sortAndSlice.prepareSingleColumn(0);
        this.arrayType.sort(paramSession, arrayOfObject, sortAndSlice);
        this.count = this.arrayType.deDuplicate(paramSession, arrayOfObject, sortAndSlice);
      } 
      return ValuePool.getLong(this.count);
    } 
    if (this.count == 0L)
      return null; 
    switch (this.setType) {
      case 75:
        switch (this.typeCode) {
          case -6:
          case 4:
          case 5:
            return (this.returnType.scale != 0) ? this.returnType.divide(paramSession, Long.valueOf(this.currentLong), Long.valueOf(this.count)) : Long.valueOf(this.currentLong / this.count);
          case 25:
            l = getLongSum().divide(BigInteger.valueOf(this.count)).longValue();
            return Long.valueOf(l);
          case 6:
          case 7:
          case 8:
            return new Double(this.currentDouble / this.count);
          case 2:
          case 3:
            return (this.returnType.scale == this.type.scale) ? this.currentBigDecimal.divide(new BigDecimal(this.count), 1) : this.returnType.divide(paramSession, this.currentBigDecimal, Long.valueOf(this.count));
          case 10:
            bigInteger = getLongSum().divide(BigInteger.valueOf(this.count));
            if (!NumberType.isInLongLimits(bigInteger))
              throw Error.error(3435); 
            return ((IntervalType)this.type).isDaySecondIntervalType() ? new IntervalSecondData(bigInteger.longValue(), this.currentLong, (IntervalType)this.type, true) : IntervalMonthData.newIntervalMonth(bigInteger.longValue(), (IntervalType)this.type);
          case 91:
          case 93:
          case 95:
            bigInteger = getLongSum().divide(BigInteger.valueOf(this.count));
            if (!NumberType.isInLongLimits(bigInteger))
              throw Error.error(3435); 
            return new TimestampData(bigInteger.longValue(), (int)this.currentLong, (int)this.currentDouble);
        } 
        throw Error.runtimeError(201, "SetFunction");
      case 72:
        switch (this.typeCode) {
          case -6:
          case 4:
          case 5:
            return Long.valueOf(this.currentLong);
          case 25:
            return new BigDecimal(getLongSum());
          case 6:
          case 7:
          case 8:
            return new Double(this.currentDouble);
          case 2:
          case 3:
            return this.currentBigDecimal;
          case 10:
            bigInteger = getLongSum();
            if (!NumberType.isInLongLimits(bigInteger))
              throw Error.error(3435); 
            return ((IntervalType)this.type).isDaySecondIntervalType() ? new IntervalSecondData(bigInteger.longValue(), this.currentLong, (IntervalType)this.type, true) : IntervalMonthData.newIntervalMonth(bigInteger.longValue(), (IntervalType)this.type);
        } 
        throw Error.runtimeError(201, "SetFunction");
      case 73:
      case 74:
        return this.currentValue;
      case 76:
        return this.every ? Boolean.TRUE : Boolean.FALSE;
      case 77:
        return this.some ? Boolean.TRUE : Boolean.FALSE;
      case 78:
      case 79:
        return getStdDev();
      case 80:
      case 81:
        return getVariance();
      case 98:
        return this.currentValue;
    } 
    throw Error.runtimeError(201, "SetFunction");
  }
  
  static Type getType(Session paramSession, int paramInt, Type paramType) {
    int i;
    int j;
    if (paramInt == 71)
      return (Type)Type.SQL_BIGINT; 
    boolean bool = paramType.isIntervalType() ? true : paramType.typeCode;
    switch (paramInt) {
      case 75:
      case 85:
        switch (bool) {
          case true:
          case true:
          case true:
          case true:
          case true:
          case true:
            i = paramSession.database.sqlAvgScale;
            if (i <= paramType.scale)
              return paramType; 
            j = ((NumberType)paramType).getDecimalPrecision();
            return (Type)NumberType.getNumberType(3, (j + i), i);
          case true:
          case true:
          case true:
          case true:
          case true:
          case true:
          case true:
            return paramType;
        } 
        throw Error.error(5563);
      case 72:
        switch (bool) {
          case true:
          case true:
          case true:
            return (Type)Type.SQL_BIGINT;
          case true:
            return (Type)Type.SQL_DECIMAL_BIGINT_SQR;
          case true:
          case true:
          case true:
            return (Type)Type.SQL_DOUBLE;
          case true:
          case true:
            return Type.getType(paramType.typeCode, null, null, paramType.precision * 2L, paramType.scale);
          case true:
            return (Type)IntervalType.newIntervalType(paramType.typeCode, 9L, paramType.scale);
        } 
        throw Error.error(5563);
      case 73:
      case 74:
        if (paramType.isArrayType() || paramType.isLobType())
          throw Error.error(5563); 
        return paramType;
      case 76:
      case 77:
        if (paramType.isBooleanType())
          return (Type)Type.SQL_BOOLEAN; 
        throw Error.error(5563);
      case 78:
      case 79:
      case 80:
      case 81:
        if (paramType.isNumberType())
          return (Type)Type.SQL_DOUBLE; 
        throw Error.error(5563);
      case 98:
        return paramType;
    } 
    throw Error.runtimeError(201, "SetFunction");
  }
  
  void addLong(long paramLong) {
    if (paramLong != 0L)
      if (paramLong > 0L) {
        this.hi += paramLong >> 32L;
        this.lo += paramLong & 0xFFFFFFFFL;
      } else if (paramLong == Long.MIN_VALUE) {
        this.hi -= 2147483648L;
      } else {
        long l = (paramLong ^ 0xFFFFFFFFFFFFFFFFL) + 1L;
        this.hi -= l >> 32L;
        this.lo -= l & 0xFFFFFFFFL;
      }  
  }
  
  BigInteger getLongSum() {
    BigInteger bigInteger1 = BigInteger.valueOf(this.lo);
    BigInteger bigInteger2 = BigInteger.valueOf(this.hi);
    return bigInteger2.multiply(multiplier).add(bigInteger1);
  }
  
  private void addDataPoint(Number paramNumber) {
    if (paramNumber == null)
      return; 
    double d1 = paramNumber.doubleValue();
    if (!this.initialized) {
      this.n = 1L;
      this.sk = d1;
      this.vk = 0.0D;
      this.initialized = true;
      return;
    } 
    this.n++;
    long l = this.n - 1L;
    double d2 = this.sk - d1 * l;
    this.vk += d2 * d2 / this.n / l;
    this.sk += d1;
  }
  
  private Number getVariance() {
    return !this.initialized ? null : (this.sample ? ((this.n == 1L) ? null : new Double(this.vk / (this.n - 1L))) : new Double(this.vk / this.n));
  }
  
  private Number getStdDev() {
    return !this.initialized ? null : (this.sample ? ((this.n == 1L) ? null : new Double(Math.sqrt(this.vk / (this.n - 1L)))) : new Double(Math.sqrt(this.vk / this.n)));
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\SetFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */