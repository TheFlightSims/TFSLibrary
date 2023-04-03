package org.hsqldb.types;

import java.math.BigDecimal;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.Tokens;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;

public final class IntervalType extends DTIType {
  public final boolean defaultPrecision;
  
  public final boolean isYearMonth;
  
  public static final NumberType factorType = NumberType.getNumberType(3, 40L, 9);
  
  private IntervalType(int paramInt1, int paramInt2, long paramLong, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean) {
    super(paramInt1, paramInt2, paramLong, paramInt3, paramInt4, paramInt5);
    if (paramInt5 != 106 && paramInt3 != 0)
      throw Error.error(3406); 
    switch (paramInt4) {
      case 101:
      case 102:
        this.isYearMonth = true;
        break;
      default:
        this.isYearMonth = false;
        break;
    } 
    this.defaultPrecision = paramBoolean;
  }
  
  public int displaySize() {
    switch (this.typeCode) {
      case 101:
        return (int)this.precision + 1;
      case 107:
        return (int)this.precision + 4;
      case 102:
        return (int)this.precision + 1;
      case 103:
        return (int)this.precision + 1;
      case 108:
        return (int)this.precision + 4;
      case 109:
        return (int)this.precision + 7;
      case 110:
        return (int)this.precision + 10 + ((this.scale == 0) ? 0 : (this.scale + 1));
      case 104:
        return (int)this.precision + 1;
      case 111:
        return (int)this.precision + 4;
      case 112:
        return (int)this.precision + 7 + ((this.scale == 0) ? 0 : (this.scale + 1));
      case 105:
        return (int)this.precision + 1;
      case 113:
        return (int)this.precision + 4 + ((this.scale == 0) ? 0 : (this.scale + 1));
      case 106:
        return (int)this.precision + 1 + ((this.scale == 0) ? 0 : (this.scale + 1));
    } 
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public int getJDBCTypeCode() {
    return this.typeCode;
  }
  
  public Class getJDBCClass() {
    switch (this.typeCode) {
      case 101:
      case 102:
      case 107:
        return IntervalMonthData.class;
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
        return IntervalSecondData.class;
    } 
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public String getJDBCClassName() {
    switch (this.typeCode) {
      case 101:
      case 102:
      case 107:
        return IntervalMonthData.class.getName();
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
        return IntervalSecondData.class.getName();
    } 
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public int getJDBCPrecision() {
    return displaySize();
  }
  
  public int getSQLGenericTypeCode() {
    return 10;
  }
  
  public String getNameString() {
    return "INTERVAL " + getQualifier(this.typeCode);
  }
  
  public static String getQualifier(int paramInt) {
    switch (paramInt) {
      case 101:
        return "YEAR";
      case 107:
        return "YEAR TO MONTH";
      case 102:
        return "MONTH";
      case 103:
        return "DAY";
      case 108:
        return "DAY TO HOUR";
      case 109:
        return "DAY TO MINUTE";
      case 110:
        return "DAY TO SECOND";
      case 104:
        return "HOUR";
      case 111:
        return "HOUR TO MINUTE";
      case 112:
        return "HOUR TO SECOND";
      case 105:
        return "MINUTE";
      case 113:
        return "MINUTE TO SECOND";
      case 106:
        return "SECOND";
    } 
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public String getDefinition() {
    if (this.precision == 2L && (this.endIntervalType != 106 || this.scale == 6))
      return getNameString(); 
    StringBuffer stringBuffer = new StringBuffer(32);
    stringBuffer.append("INTERVAL").append(' ');
    stringBuffer.append(getQualifier(this.startIntervalType));
    if (this.typeCode == 106) {
      stringBuffer.append('(');
      stringBuffer.append(this.precision);
      if (this.scale != 6) {
        stringBuffer.append(',');
        stringBuffer.append(this.scale);
      } 
      stringBuffer.append(')');
      return stringBuffer.toString();
    } 
    if (this.precision != 2L) {
      stringBuffer.append('(');
      stringBuffer.append(this.precision);
      stringBuffer.append(')');
    } 
    if (this.startIntervalType != this.endIntervalType) {
      stringBuffer.append(' ');
      stringBuffer.append("TO");
      stringBuffer.append(' ');
      stringBuffer.append(Tokens.SQL_INTERVAL_FIELD_NAMES[this.endPartIndex]);
      if (this.endIntervalType == 106 && this.scale != 6) {
        stringBuffer.append('(');
        stringBuffer.append(this.scale);
        stringBuffer.append(')');
      } 
    } 
    return stringBuffer.toString();
  }
  
  public boolean isIntervalType() {
    return true;
  }
  
  public boolean isYearMonthIntervalType() {
    switch (this.typeCode) {
      case 101:
      case 102:
      case 107:
        return true;
    } 
    return false;
  }
  
  public boolean isDaySecondIntervalType() {
    switch (this.typeCode) {
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
        return true;
    } 
    return false;
  }
  
  public boolean acceptsPrecision() {
    return true;
  }
  
  public boolean acceptsFractionalPrecision() {
    return (this.endIntervalType == 106);
  }
  
  public Type getAggregateType(Type paramType) {
    if (paramType == null)
      return this; 
    if (paramType == SQL_ALL_TYPES)
      return this; 
    if (this.typeCode == paramType.typeCode) {
      if (this.precision >= paramType.precision && this.scale >= paramType.scale)
        return this; 
      if (this.precision <= paramType.precision && this.scale <= paramType.scale)
        return paramType; 
    } 
    if (paramType.isCharacterType())
      return paramType.getAggregateType(this); 
    if (!paramType.isIntervalType())
      throw Error.error(5562); 
    int i = (((IntervalType)paramType).startIntervalType > this.startIntervalType) ? this.startIntervalType : ((IntervalType)paramType).startIntervalType;
    int j = (((IntervalType)paramType).endIntervalType > this.endIntervalType) ? ((IntervalType)paramType).endIntervalType : this.endIntervalType;
    int k = getCombinedIntervalType(i, j);
    long l = (this.precision > paramType.precision) ? this.precision : paramType.precision;
    int m = (this.scale > paramType.scale) ? this.scale : paramType.scale;
    try {
      return getIntervalType(k, i, j, l, m, false);
    } catch (RuntimeException runtimeException) {
      throw Error.error(5562);
    } 
  }
  
  public Type getCombinedType(Session paramSession, Type paramType, int paramInt) {
    switch (paramInt) {
      case 34:
        if (paramType.isNumberType())
          return getIntervalType(this, 9L, this.scale); 
        throw Error.error(5562);
      case 35:
        if (paramType.isNumberType())
          return this; 
        if (paramType.isIntervalType()) {
          IntervalType intervalType = (IntervalType)paramType;
          if (this.isYearMonth == intervalType.isYearMonth)
            return this.isYearMonth ? Type.SQL_BIGINT : factorType; 
        } 
        throw Error.error(5562);
      case 32:
        if (paramType.isDateTimeType())
          return paramType.getCombinedType(paramSession, this, paramInt); 
        if (paramType.isIntervalType()) {
          IntervalType intervalType = (IntervalType)getAggregateType(paramType);
          return getIntervalType(intervalType, 9L, 0);
        } 
        throw Error.error(5562);
    } 
    return getAggregateType(paramType);
  }
  
  public int compare(Session paramSession, Object paramObject1, Object paramObject2) {
    if (paramObject1 == paramObject2)
      return 0; 
    if (paramObject1 == null)
      return -1; 
    if (paramObject2 == null)
      return 1; 
    switch (this.typeCode) {
      case 101:
      case 102:
      case 107:
        return ((IntervalMonthData)paramObject1).compareTo((IntervalMonthData)paramObject2);
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
        return ((IntervalSecondData)paramObject1).compareTo((IntervalSecondData)paramObject2);
    } 
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject) {
    if (paramObject == null)
      return null; 
    if (paramObject instanceof IntervalMonthData) {
      IntervalMonthData intervalMonthData = (IntervalMonthData)paramObject;
      if (intervalMonthData.units > getIntervalValueLimit())
        throw Error.error(3435); 
    } else if (paramObject instanceof IntervalSecondData) {
      IntervalSecondData intervalSecondData = (IntervalSecondData)paramObject;
      if (intervalSecondData.units > getIntervalValueLimit())
        throw Error.error(3435); 
    } 
    return paramObject;
  }
  
  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType) {
    long l;
    int i;
    if (paramObject == null)
      return null; 
    switch (paramType.typeCode) {
      case 40:
        paramObject = paramObject.toString();
      case 1:
      case 12:
        return paramSessionInterface.getScanner().convertToDatetimeInterval(paramSessionInterface, (String)paramObject, this);
      case -6:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 25:
        if (paramObject instanceof BigDecimal && !NumberType.isInLongLimits((BigDecimal)paramObject))
          throw Error.error(3435); 
        l = ((Number)paramObject).longValue();
        switch (this.endIntervalType) {
          case 101:
            return IntervalMonthData.newIntervalYear(l, this);
          case 102:
            return IntervalMonthData.newIntervalMonth(l, this);
          case 103:
            return IntervalSecondData.newIntervalDay(l, this);
          case 104:
            return IntervalSecondData.newIntervalHour(l, this);
          case 105:
            return IntervalSecondData.newIntervalMinute(l, this);
          case 106:
            i = 0;
            if (this.scale > 0 && paramObject instanceof BigDecimal)
              i = (int)NumberType.scaledDecimal(paramObject, 9); 
            return new IntervalSecondData(l, i, this);
        } 
        throw Error.error(5561);
      case 101:
        l = (((IntervalMonthData)paramObject).units / 12 * 12);
        return new IntervalMonthData(l, this);
      case 102:
      case 107:
        l = ((IntervalMonthData)paramObject).units;
        return new IntervalMonthData(l, this);
      case 103:
        l = ((IntervalSecondData)paramObject).units;
        l = l / DTIType.yearToSecondFactors[2] * DTIType.yearToSecondFactors[2];
        return new IntervalSecondData(l, 0, this);
      case 104:
      case 105:
      case 108:
      case 109:
      case 111:
        l = ((IntervalSecondData)paramObject).units;
        l = l / DTIType.yearToSecondFactors[this.endPartIndex] * DTIType.yearToSecondFactors[this.endPartIndex];
        return new IntervalSecondData(l, 0, this);
      case 106:
      case 110:
      case 112:
      case 113:
        l = ((IntervalSecondData)paramObject).units;
        i = ((IntervalSecondData)paramObject).nanos;
        if (this.scale == 0) {
          i = 0;
        } else {
          i = i / DTIType.nanoScaleFactors[this.scale] * DTIType.nanoScaleFactors[this.scale];
        } 
        return new IntervalSecondData(l, i, this);
    } 
    throw Error.error(5561);
  }
  
  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject) {
    if (paramObject == null)
      return null; 
    if (paramObject instanceof String)
      return convertToType(paramSessionInterface, paramObject, Type.SQL_VARCHAR); 
    if (paramObject instanceof Integer)
      return convertToType(paramSessionInterface, paramObject, Type.SQL_INTEGER); 
    if (paramObject instanceof Long)
      return convertToType(paramSessionInterface, paramObject, Type.SQL_BIGINT); 
    if (paramObject instanceof BigDecimal)
      return convertToType(paramSessionInterface, paramObject, Type.SQL_DECIMAL); 
    throw Error.error(5561);
  }
  
  public Object convertJavaToSQL(SessionInterface paramSessionInterface, Object paramObject) {
    return convertToDefaultType(paramSessionInterface, paramObject);
  }
  
  public String convertToString(Object paramObject) {
    if (paramObject == null)
      return null; 
    switch (this.typeCode) {
      case 101:
      case 102:
      case 107:
        return intervalMonthToString(paramObject);
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
        return intervalSecondToString(paramObject);
    } 
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public String convertToSQLString(Object paramObject) {
    if (paramObject == null)
      return "NULL"; 
    StringBuffer stringBuffer = new StringBuffer(32);
    stringBuffer.append("INTERVAL").append(' ');
    stringBuffer.append('\'').append(convertToString(paramObject)).append('\'').append(' ');
    stringBuffer.append(Tokens.SQL_INTERVAL_FIELD_NAMES[this.startPartIndex]);
    if (this.startPartIndex != this.endPartIndex) {
      stringBuffer.append(' ');
      stringBuffer.append("TO");
      stringBuffer.append(' ');
      stringBuffer.append(Tokens.SQL_INTERVAL_FIELD_NAMES[this.endPartIndex]);
    } 
    return stringBuffer.toString();
  }
  
  public boolean canConvertFrom(Type paramType) {
    return (paramType.typeCode == 0) ? true : (paramType.isCharacterType() ? true : (paramType.isNumberType() ? true : (!paramType.isIntervalType() ? false : (((isYearMonthIntervalType() ^ ((IntervalType)paramType).isYearMonthIntervalType()) == 0)))));
  }
  
  public int canMoveFrom(Type paramType) {
    if (paramType == this)
      return 0; 
    if (this.typeCode == paramType.typeCode)
      return (this.scale >= paramType.scale) ? 0 : -1; 
    if (!paramType.isIntervalType())
      return -1; 
    if (this.isYearMonth == ((IntervalType)paramType).isYearMonth) {
      if (this.scale < paramType.scale)
        return -1; 
      if (this.endPartIndex >= ((IntervalType)paramType).endPartIndex)
        return (this.precision >= paramType.precision && this.startPartIndex <= ((IntervalType)paramType).startPartIndex) ? 0 : 1; 
    } 
    return -1;
  }
  
  public int compareToTypeRange(Object paramObject) {
    long l2;
    long l1 = precisionLimits[(int)this.precision];
    if (paramObject instanceof IntervalMonthData) {
      l2 = ((IntervalMonthData)paramObject).units;
    } else if (paramObject instanceof IntervalSecondData) {
      l2 = ((IntervalSecondData)paramObject).units;
    } else {
      return 0;
    } 
    return (l2 >= l1) ? 1 : ((l2 < 0L && -l2 >= l1) ? -1 : 0);
  }
  
  public Object absolute(Object paramObject) {
    if (paramObject == null)
      return null; 
    if (paramObject instanceof IntervalMonthData) {
      if (((IntervalMonthData)paramObject).units < 0)
        return negate(paramObject); 
    } else if (((IntervalSecondData)paramObject).units < 0L || ((IntervalSecondData)paramObject).nanos < 0) {
      return negate(paramObject);
    } 
    return paramObject;
  }
  
  public Object negate(Object paramObject) {
    if (paramObject == null)
      return null; 
    if (paramObject instanceof IntervalMonthData) {
      long l1 = ((IntervalMonthData)paramObject).units;
      return new IntervalMonthData(-l1, this);
    } 
    long l = ((IntervalSecondData)paramObject).units;
    int i = ((IntervalSecondData)paramObject).nanos;
    return new IntervalSecondData(-l, -i, this, true);
  }
  
  public Object add(Session paramSession, Object paramObject1, Object paramObject2, Type paramType) {
    long l1;
    long l2;
    long l3;
    if (paramObject1 == null || paramObject2 == null)
      return null; 
    switch (this.typeCode) {
      case 101:
      case 102:
      case 107:
        l1 = (((IntervalMonthData)paramObject1).units + ((IntervalMonthData)paramObject2).units);
        return new IntervalMonthData(l1, this);
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
        l2 = ((IntervalSecondData)paramObject1).units + ((IntervalSecondData)paramObject2).units;
        l3 = (((IntervalSecondData)paramObject1).nanos + ((IntervalSecondData)paramObject2).nanos);
        return new IntervalSecondData(l2, l3, this, true);
    } 
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public Object subtract(Session paramSession, Object paramObject1, Object paramObject2, Type paramType) {
    if (paramObject1 == null || paramObject2 == null)
      return null; 
    switch (this.typeCode) {
      case 101:
      case 102:
      case 107:
        if (paramObject1 instanceof IntervalMonthData && paramObject2 instanceof IntervalMonthData) {
          long l = (((IntervalMonthData)paramObject1).units - ((IntervalMonthData)paramObject2).units);
          return new IntervalMonthData(l, this);
        } 
        if (paramObject1 instanceof TimestampData && paramObject2 instanceof TimestampData) {
          boolean bool = (this.typeCode == 101) ? true : false;
          long l = DateTimeType.subtractMonths((TimestampData)paramObject1, (TimestampData)paramObject2, bool);
          return new IntervalMonthData(l, this);
        } 
        throw Error.runtimeError(201, "IntervalType");
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
        if (paramObject1 instanceof IntervalSecondData && paramObject2 instanceof IntervalSecondData) {
          long l1 = ((IntervalSecondData)paramObject1).units - ((IntervalSecondData)paramObject2).units;
          long l2 = (((IntervalSecondData)paramObject1).nanos - ((IntervalSecondData)paramObject2).nanos);
          return new IntervalSecondData(l1, l2, this, true);
        } 
        if (paramObject1 instanceof TimeData && paramObject2 instanceof TimeData) {
          long l1 = ((TimeData)paramObject1).getSeconds();
          long l2 = ((TimeData)paramObject2).getSeconds();
          long l3 = (((TimeData)paramObject1).getNanos() - ((TimeData)paramObject2).getNanos());
          return subtract(l1, l2, l3);
        } 
        if (paramObject1 instanceof TimestampData && paramObject2 instanceof TimestampData) {
          long l1 = ((TimestampData)paramObject1).getSeconds();
          long l2 = ((TimestampData)paramObject2).getSeconds();
          long l3 = (((TimestampData)paramObject1).getNanos() - ((TimestampData)paramObject2).getNanos());
          return subtract(l1, l2, l3);
        } 
        break;
    } 
    throw Error.runtimeError(201, "IntervalType");
  }
  
  private IntervalSecondData subtract(long paramLong1, long paramLong2, long paramLong3) {
    if (this.endIntervalType != 106) {
      paramLong1 = HsqlDateTime.getTruncatedPart(paramLong1 * 1000L, this.endIntervalType) / 1000L;
      paramLong2 = HsqlDateTime.getTruncatedPart(paramLong2 * 1000L, this.endIntervalType) / 1000L;
      paramLong3 = 0L;
    } 
    return new IntervalSecondData(paramLong1 - paramLong2, paramLong3, this, true);
  }
  
  public Object multiply(Object paramObject1, Object paramObject2) {
    return multiplyOrDivide(paramObject1, paramObject2, false);
  }
  
  public Object divide(Session paramSession, Object paramObject1, Object paramObject2) {
    return multiplyOrDivide(paramObject1, paramObject2, true);
  }
  
  private Object multiplyOrDivide(Object paramObject1, Object paramObject2, boolean paramBoolean) {
    BigDecimal bigDecimal2;
    if (paramObject1 == null || paramObject2 == null)
      return null; 
    if (paramObject1 instanceof Number) {
      Object object = paramObject1;
      paramObject1 = paramObject2;
      paramObject2 = object;
    } 
    boolean bool = paramObject2 instanceof Number;
    if (paramBoolean)
      if (bool) {
        if (NumberType.isZero(paramObject2))
          throw Error.error(3432); 
      } else if (this.isYearMonth) {
        if (((IntervalMonthData)paramObject2).units == 0)
          throw Error.error(3432); 
      } else if (((IntervalSecondData)paramObject2).units == 0L) {
        throw Error.error(3432);
      }  
    BigDecimal bigDecimal1 = (BigDecimal)factorType.convertToDefaultType(null, paramObject2);
    if (this.isYearMonth) {
      bigDecimal2 = BigDecimal.valueOf(((IntervalMonthData)paramObject1).units);
    } else {
      long l = ((IntervalSecondData)paramObject1).units * DTIType.nanoScaleFactors[0] + ((IntervalSecondData)paramObject1).nanos;
      bigDecimal2 = BigDecimal.valueOf(l, 9);
    } 
    BigDecimal bigDecimal3 = paramBoolean ? (BigDecimal)factorType.divide(null, bigDecimal2, bigDecimal1) : (BigDecimal)factorType.multiply(bigDecimal2, bigDecimal1);
    if (!NumberType.isInLongLimits(bigDecimal3))
      throw Error.error(3435); 
    if (bool) {
      if (this.isYearMonth)
        return new IntervalMonthData(bigDecimal3.longValue(), this); 
      int i = (int)NumberType.scaledDecimal(bigDecimal3, 9);
      return new IntervalSecondData(bigDecimal3.longValue(), i, this, true);
    } 
    return this.isYearMonth ? Long.valueOf(bigDecimal3.longValue()) : bigDecimal3;
  }
  
  String intervalMonthToString(Object paramObject) {
    StringBuffer stringBuffer = new StringBuffer(8);
    long l = ((IntervalMonthData)paramObject).units;
    if (l < 0L) {
      l = -l;
      stringBuffer.append('-');
    } 
    for (int i = this.startPartIndex; i <= this.endPartIndex; i++) {
      int j = DTIType.yearToSecondFactors[i];
      long l1 = l / j;
      if (i == this.startPartIndex) {
        int k = (int)this.precision - getPrecisionExponent(l1);
      } else if (l1 < 10L) {
        stringBuffer.append('0');
      } 
      stringBuffer.append(l1);
      l %= j;
      if (i < this.endPartIndex)
        stringBuffer.append((char)DTIType.yearToSecondSeparators[i]); 
    } 
    return stringBuffer.toString();
  }
  
  String intervalSecondToString(Object paramObject) {
    long l = ((IntervalSecondData)paramObject).units;
    int i = ((IntervalSecondData)paramObject).nanos;
    return intervalSecondToString(l, i, false);
  }
  
  public int precedenceDegree(Type paramType) {
    if (paramType.isIntervalType()) {
      int i = ((IntervalType)paramType).endPartIndex;
      return i - this.endPartIndex;
    } 
    return Integer.MIN_VALUE;
  }
  
  public int getStartIntervalType() {
    return this.startIntervalType;
  }
  
  public int getEndIntervalType() {
    return this.endIntervalType;
  }
  
  public static IntervalType newIntervalType(int paramInt1, long paramLong, int paramInt2) {
    int i = getStartIntervalType(paramInt1);
    int j = getEndIntervalType(paramInt1);
    byte b = (i > 102) ? 106 : 102;
    return new IntervalType(b, paramInt1, paramLong, paramInt2, i, j, false);
  }
  
  public static IntervalType getIntervalType(IntervalType paramIntervalType, long paramLong, int paramInt) {
    return (paramIntervalType.precision >= paramLong && paramIntervalType.scale >= paramInt) ? paramIntervalType : getIntervalType(paramIntervalType.typeCode, paramLong, paramInt);
  }
  
  public static IntervalType getIntervalType(int paramInt1, long paramLong, int paramInt2) {
    int i = getStartIntervalType(paramInt1);
    int j = getEndIntervalType(paramInt1);
    return getIntervalType(paramInt1, i, j, paramLong, paramInt2, false);
  }
  
  public static IntervalType getIntervalType(int paramInt1, int paramInt2, long paramLong, int paramInt3) {
    boolean bool = (paramLong == -1L) ? true : false;
    if (paramInt1 == -1 || paramInt2 == -1)
      throw Error.error(3406); 
    if (paramInt1 > paramInt2)
      throw Error.error(3406); 
    if (paramInt1 <= 1 && paramInt2 > 1)
      throw Error.error(3406); 
    int i = DTIType.intervalParts[paramInt1];
    int j = DTIType.intervalParts[paramInt2];
    int k = DTIType.intervalTypes[paramInt1][paramInt2];
    if (paramLong == 0L || paramInt3 > 9)
      throw Error.error(5592); 
    if (paramInt1 == 5) {
      if (paramLong > 12L)
        throw Error.error(5592); 
    } else if (paramLong > 9L) {
      throw Error.error(5592);
    } 
    if (paramLong == -1L)
      paramLong = 2L; 
    if (paramInt3 == -1)
      paramInt3 = (j == 106) ? 6 : 0; 
    return getIntervalType(k, i, j, paramLong, paramInt3, bool);
  }
  
  public static IntervalType getIntervalType(int paramInt1, int paramInt2, int paramInt3, long paramLong, int paramInt4, boolean paramBoolean) {
    byte b = (paramInt2 > 102) ? 106 : 102;
    if (paramBoolean)
      return new IntervalType(b, paramInt1, paramLong, paramInt4, paramInt2, paramInt3, paramBoolean); 
    switch (paramInt1) {
      case 101:
        return (paramLong == 2L) ? SQL_INTERVAL_YEAR : new IntervalType(b, paramInt1, paramLong, paramInt4, paramInt2, paramInt3, paramBoolean);
      case 107:
        return (paramLong == 2L) ? SQL_INTERVAL_YEAR_TO_MONTH : new IntervalType(b, paramInt1, paramLong, paramInt4, paramInt2, paramInt3, paramBoolean);
      case 102:
        return (paramLong == 2L) ? SQL_INTERVAL_MONTH : new IntervalType(b, paramInt1, paramLong, paramInt4, paramInt2, paramInt3, paramBoolean);
      case 103:
        return (paramLong == 2L) ? SQL_INTERVAL_DAY : new IntervalType(b, paramInt1, paramLong, paramInt4, paramInt2, paramInt3, paramBoolean);
      case 108:
        return (paramLong == 2L) ? SQL_INTERVAL_DAY_TO_HOUR : new IntervalType(b, paramInt1, paramLong, paramInt4, paramInt2, paramInt3, paramBoolean);
      case 109:
        return (paramLong == 2L) ? SQL_INTERVAL_DAY_TO_MINUTE : new IntervalType(b, paramInt1, paramLong, paramInt4, paramInt2, paramInt3, paramBoolean);
      case 110:
        return (paramLong == 2L && paramInt4 == 6) ? SQL_INTERVAL_DAY_TO_SECOND : new IntervalType(b, paramInt1, paramLong, paramInt4, paramInt2, paramInt3, paramBoolean);
      case 104:
        return (paramLong == 2L) ? SQL_INTERVAL_HOUR : new IntervalType(b, paramInt1, paramLong, paramInt4, paramInt2, paramInt3, paramBoolean);
      case 111:
        return (paramLong == 2L) ? SQL_INTERVAL_HOUR_TO_MINUTE : new IntervalType(b, paramInt1, paramLong, paramInt4, paramInt2, paramInt3, paramBoolean);
      case 105:
        return (paramLong == 2L) ? SQL_INTERVAL_MINUTE : new IntervalType(b, paramInt1, paramLong, paramInt4, paramInt2, paramInt3, paramBoolean);
      case 112:
        return (paramLong == 2L && paramInt4 == 6) ? SQL_INTERVAL_HOUR_TO_SECOND : new IntervalType(b, paramInt1, paramLong, paramInt4, paramInt2, paramInt3, paramBoolean);
      case 113:
        return (paramLong == 2L && paramInt4 == 6) ? SQL_INTERVAL_MINUTE_TO_SECOND : new IntervalType(b, paramInt1, paramLong, paramInt4, paramInt2, paramInt3, paramBoolean);
      case 106:
        return (paramLong == 2L && paramInt4 == 6) ? SQL_INTERVAL_SECOND : new IntervalType(b, paramInt1, paramLong, paramInt4, paramInt2, paramInt3, paramBoolean);
    } 
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public static int getStartIntervalType(int paramInt) {
    switch (paramInt) {
      case 101:
      case 107:
        return 101;
      case 102:
        return 102;
      case 103:
      case 108:
      case 109:
      case 110:
        return 103;
      case 104:
      case 111:
      case 112:
        return 104;
      case 105:
      case 113:
        return 105;
      case 106:
        return 106;
    } 
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public static int getEndIntervalType(int paramInt) {
    switch (paramInt) {
      case 101:
        return 101;
      case 107:
        return 102;
      case 102:
        return 102;
      case 103:
        return 103;
      case 108:
        return 104;
      case 109:
        return 105;
      case 110:
        return 106;
      case 104:
        return 104;
      case 111:
        return 105;
      case 112:
        return 106;
      case 105:
        return 105;
      case 113:
        return 106;
      case 106:
        return 106;
    } 
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public static Type getCombinedIntervalType(IntervalType paramIntervalType1, IntervalType paramIntervalType2) {
    int i = (paramIntervalType2.startIntervalType > paramIntervalType1.startIntervalType) ? paramIntervalType1.startIntervalType : paramIntervalType2.startIntervalType;
    int j = (paramIntervalType2.endIntervalType > paramIntervalType1.endIntervalType) ? paramIntervalType2.endIntervalType : paramIntervalType1.endIntervalType;
    int k = getCombinedIntervalType(i, j);
    long l = (paramIntervalType1.precision > paramIntervalType2.precision) ? paramIntervalType1.precision : paramIntervalType2.precision;
    int m = (paramIntervalType1.scale > paramIntervalType2.scale) ? paramIntervalType1.scale : paramIntervalType2.scale;
    return getIntervalType(k, i, j, l, m, false);
  }
  
  public static int getCombinedIntervalType(int paramInt1, int paramInt2) {
    if (paramInt1 == paramInt2)
      return paramInt1; 
    switch (paramInt1) {
      case 101:
        if (paramInt2 == 102)
          return 107; 
        break;
      case 103:
        switch (paramInt2) {
          case 104:
            return 108;
          case 105:
            return 109;
          case 106:
            return 110;
        } 
        break;
      case 104:
        switch (paramInt2) {
          case 105:
            return 111;
          case 106:
            return 112;
        } 
        break;
      case 105:
        if (paramInt2 == 106)
          return 113; 
        break;
    } 
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public static int getIntervalType(String paramString) {
    int i = ArrayUtil.find((Object[])Tokens.SQL_INTERVAL_FIELD_NAMES, paramString);
    if (i < 0)
      throw Error.error(5562); 
    return intervalParts[i];
  }
  
  long getIntervalValueLimit() {
    switch (this.typeCode) {
      case 101:
        return DTIType.precisionLimits[(int)this.precision] * 12L;
      case 107:
        null = DTIType.precisionLimits[(int)this.precision] * 12L;
        null += 12L;
        return null;
      case 102:
        return DTIType.precisionLimits[(int)this.precision];
      case 103:
        return DTIType.precisionLimits[(int)this.precision] * 24L * 60L * 60L;
      case 108:
        return DTIType.precisionLimits[(int)this.precision] * 24L * 60L * 60L;
      case 109:
        return DTIType.precisionLimits[(int)this.precision] * 24L * 60L * 60L;
      case 110:
        return DTIType.precisionLimits[(int)this.precision] * 24L * 60L * 60L;
      case 104:
        return DTIType.precisionLimits[(int)this.precision] * 60L * 60L;
      case 111:
        return DTIType.precisionLimits[(int)this.precision] * 60L * 60L;
      case 112:
        return DTIType.precisionLimits[(int)this.precision] * 60L * 60L;
      case 105:
        return DTIType.precisionLimits[(int)this.precision] * 60L;
      case 113:
        return DTIType.precisionLimits[(int)this.precision] * 60L;
      case 106:
        return DTIType.precisionLimits[(int)this.precision];
    } 
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public int getPart(Session paramSession, Object paramObject, int paramInt) {
    long l;
    switch (paramInt) {
      case 101:
        return ((IntervalMonthData)paramObject).units / 12;
      case 102:
        l = ((IntervalMonthData)paramObject).units;
        return (paramInt == this.startIntervalType) ? (int)l : (int)(l % 12L);
      case 103:
        return (int)(((IntervalSecondData)paramObject).units / 86400L);
      case 104:
        l = ((IntervalSecondData)paramObject).units / 3600L;
        return (paramInt == this.startIntervalType) ? (int)l : (int)(l % 24L);
      case 105:
        l = ((IntervalSecondData)paramObject).units / 60L;
        return (paramInt == this.startIntervalType) ? (int)l : (int)(l % 60L);
      case 106:
        l = ((IntervalSecondData)paramObject).units;
        return (paramInt == this.startIntervalType) ? (int)l : (int)(l % 60L);
    } 
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public long getSeconds(Object paramObject) {
    return ((IntervalSecondData)paramObject).units;
  }
  
  public BigDecimal getSecondPart(Object paramObject) {
    long l = ((IntervalSecondData)paramObject).units;
    if (this.typeCode != 106)
      l %= 60L; 
    int i = ((IntervalSecondData)paramObject).nanos;
    return getSecondPart(l, i);
  }
  
  public long convertToLongEndUnits(Object paramObject) {
    long l1;
    long l2;
    switch (this.endIntervalType) {
      case 101:
      case 102:
        l1 = ((IntervalMonthData)paramObject).units;
        return l1 / DTIType.yearToSecondFactors[this.endPartIndex];
      case 103:
      case 104:
      case 105:
      case 106:
        l2 = ((IntervalSecondData)paramObject).units;
        return l2 / DTIType.yearToSecondFactors[this.endPartIndex];
    } 
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public double convertToDoubleStartUnits(Object paramObject) {
    double d1;
    double d2;
    switch (this.startIntervalType) {
      case 101:
      case 102:
        d1 = ((IntervalMonthData)paramObject).units;
        return d1 / DTIType.yearToSecondFactors[this.startPartIndex];
      case 103:
      case 104:
      case 105:
      case 106:
        d2 = ((IntervalSecondData)paramObject).units;
        return d2 / DTIType.yearToSecondFactors[this.startPartIndex];
    } 
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public CharacterType getCharacterType() {
    CharacterType characterType = CharacterType.getCharacterType(12, displaySize());
    characterType.nameString = getNameString();
    return characterType;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\IntervalType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */