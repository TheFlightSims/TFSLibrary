package org.hsqldb.types;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.java.JavaSystem;
import org.hsqldb.map.ValuePool;

public final class NumberType extends Type {
  static final int tinyintPrecision = 3;
  
  static final int smallintPrecision = 5;
  
  static final int integerPrecision = 10;
  
  static final int bigintPrecision = 19;
  
  static final int doublePrecision = 0;
  
  public static final int defaultNumericPrecision = 128;
  
  public static final int defaultNumericScale = 32;
  
  public static final int maxNumericPrecision = 2147483647;
  
  static final int bigintSquareNumericPrecision = 40;
  
  public static final int TINYINT_WIDTH = 8;
  
  public static final int SMALLINT_WIDTH = 16;
  
  public static final int INTEGER_WIDTH = 32;
  
  public static final int BIGINT_WIDTH = 64;
  
  public static final int DOUBLE_WIDTH = 128;
  
  public static final int DECIMAL_WIDTH = 256;
  
  public static final Type SQL_NUMERIC_DEFAULT_INT = new NumberType(2, 128L, 0);
  
  public static final BigDecimal MAX_DOUBLE = BigDecimal.valueOf(Double.MAX_VALUE);
  
  public static final BigDecimal MAX_LONG = BigDecimal.valueOf(Long.MAX_VALUE);
  
  public static final BigDecimal MIN_LONG = BigDecimal.valueOf(Long.MIN_VALUE);
  
  public static final BigDecimal MAX_INT = BigDecimal.valueOf(2147483647L);
  
  public static final BigDecimal MIN_INT = BigDecimal.valueOf(-2147483648L);
  
  public static final BigInteger MIN_LONG_BI = MIN_LONG.toBigInteger();
  
  public static final BigInteger MAX_LONG_BI = MAX_LONG.toBigInteger();
  
  final int typeWidth;
  
  public NumberType(int paramInt1, long paramLong, int paramInt2) {
    super(2, paramInt1, paramLong, paramInt2);
    switch (paramInt1) {
      case -6:
        this.typeWidth = 8;
        return;
      case 5:
        this.typeWidth = 16;
        return;
      case 4:
        this.typeWidth = 32;
        return;
      case 25:
        this.typeWidth = 64;
        return;
      case 6:
      case 7:
      case 8:
        this.typeWidth = 128;
        return;
      case 2:
      case 3:
        this.typeWidth = 256;
        return;
    } 
    throw Error.runtimeError(201, "NumberType");
  }
  
  public int getPrecision() {
    switch (this.typeCode) {
      case -6:
      case 4:
      case 5:
      case 25:
        return this.typeWidth;
      case 6:
      case 7:
      case 8:
        return 64;
      case 2:
      case 3:
        return (int)this.precision;
    } 
    throw Error.runtimeError(201, "NumberType");
  }
  
  public int getDecimalPrecision() {
    switch (this.typeCode) {
      case -6:
        return 3;
      case 5:
        return 5;
      case 4:
        return 10;
      case 25:
        return 19;
      case 2:
      case 3:
      case 6:
      case 7:
      case 8:
        return displaySize() - 1;
    } 
    throw Error.runtimeError(201, "NumberType");
  }
  
  public int displaySize() {
    switch (this.typeCode) {
      case 2:
      case 3:
        return (this.scale == 0) ? ((this.precision == 0L) ? 646456995 : ((int)this.precision + 1)) : ((this.precision == this.scale) ? ((int)this.precision + 3) : ((int)this.precision + 2));
      case 6:
      case 7:
      case 8:
        return 23;
      case 25:
        return 20;
      case 4:
        return 11;
      case 5:
        return 6;
      case -6:
        return 4;
    } 
    throw Error.runtimeError(201, "NumberType");
  }
  
  public int getJDBCTypeCode() {
    return (this.typeCode == 25) ? -5 : this.typeCode;
  }
  
  public Class getJDBCClass() {
    switch (this.typeCode) {
      case -6:
      case 4:
      case 5:
        return Integer.class;
      case 25:
        return Long.class;
      case 6:
      case 7:
      case 8:
        return Double.class;
      case 2:
      case 3:
        return BigDecimal.class;
    } 
    throw Error.runtimeError(201, "NumberType");
  }
  
  public String getJDBCClassName() {
    switch (this.typeCode) {
      case -6:
      case 4:
      case 5:
        return "java.lang.Integer";
      case 25:
        return "java.lang.Long";
      case 6:
      case 7:
      case 8:
        return "java.lang.Double";
      case 2:
      case 3:
        return "java.math.BigDecimal";
    } 
    throw Error.runtimeError(201, "NumberType");
  }
  
  public int getJDBCPrecision() {
    return getPrecision();
  }
  
  public String getNameString() {
    switch (this.typeCode) {
      case -6:
        return "TINYINT";
      case 5:
        return "SMALLINT";
      case 4:
        return "INTEGER";
      case 25:
        return "BIGINT";
      case 7:
        return "REAL";
      case 6:
        return "FLOAT";
      case 8:
        return "DOUBLE";
      case 2:
        return "NUMERIC";
      case 3:
        return "DECIMAL";
    } 
    throw Error.runtimeError(201, "NumberType");
  }
  
  public String getFullNameString() {
    switch (this.typeCode) {
      case 8:
        return "DOUBLE PRECISION";
    } 
    return getNameString();
  }
  
  public String getDefinition() {
    StringBuffer stringBuffer;
    switch (this.typeCode) {
      case 2:
      case 3:
        stringBuffer = new StringBuffer(16);
        stringBuffer.append(getNameString());
        stringBuffer.append('(');
        stringBuffer.append(this.precision);
        if (this.scale != 0) {
          stringBuffer.append(',');
          stringBuffer.append(this.scale);
        } 
        stringBuffer.append(')');
        return stringBuffer.toString();
    } 
    return getNameString();
  }
  
  public long getMaxPrecision() {
    switch (this.typeCode) {
      case 2:
      case 3:
        return 2147483647L;
    } 
    return getNumericPrecisionInRadix();
  }
  
  public int getMaxScale() {
    switch (this.typeCode) {
      case 2:
      case 3:
        return 32767;
    } 
    return 0;
  }
  
  public boolean acceptsPrecision() {
    switch (this.typeCode) {
      case 2:
      case 3:
      case 6:
        return true;
    } 
    return false;
  }
  
  public boolean acceptsScale() {
    switch (this.typeCode) {
      case 2:
      case 3:
        return true;
    } 
    return false;
  }
  
  public int getPrecisionRadix() {
    return (this.typeCode == 3 || this.typeCode == 2) ? 10 : 2;
  }
  
  public boolean isNumberType() {
    return true;
  }
  
  public boolean isIntegralType() {
    switch (this.typeCode) {
      case 6:
      case 7:
      case 8:
        return false;
      case 2:
      case 3:
        return (this.scale == 0);
    } 
    return true;
  }
  
  public boolean isExactNumberType() {
    switch (this.typeCode) {
      case 6:
      case 7:
      case 8:
        return false;
    } 
    return true;
  }
  
  public boolean isDecimalType() {
    switch (this.typeCode) {
      case 2:
      case 3:
        return true;
    } 
    return false;
  }
  
  public int getNominalWidth() {
    return this.typeWidth;
  }
  
  public int precedenceDegree(Type paramType) {
    if (paramType.isNumberType()) {
      int i = ((NumberType)paramType).typeWidth;
      return i - this.typeWidth;
    } 
    return Integer.MIN_VALUE;
  }
  
  public Type getAggregateType(Type paramType) {
    if (paramType == null)
      return this; 
    if (paramType == SQL_ALL_TYPES)
      return this; 
    if (this == paramType)
      return this; 
    if (paramType.isCharacterType())
      return paramType.getAggregateType(this); 
    switch (paramType.typeCode) {
      case -6:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 25:
        break;
      default:
        throw Error.error(5562);
    } 
    if (this.typeWidth == 128)
      return this; 
    if (((NumberType)paramType).typeWidth == 128)
      return paramType; 
    if (this.typeWidth <= 64 && ((NumberType)paramType).typeWidth <= 64)
      return (this.typeWidth > ((NumberType)paramType).typeWidth) ? this : paramType; 
    int i = (this.scale > paramType.scale) ? this.scale : paramType.scale;
    long l = (this.precision - this.scale > paramType.precision - paramType.scale) ? (this.precision - this.scale) : (paramType.precision - paramType.scale);
    return getNumberType(3, l + i, i);
  }
  
  public Type getCombinedType(Session paramSession, Type paramType, int paramInt) {
    int i;
    long l;
    if (paramType.typeCode == 0)
      paramType = this; 
    switch (paramInt) {
      case 32:
      case 35:
        break;
      case 34:
        if (paramType.isIntervalType())
          return paramType.getCombinedType(paramSession, this, 34); 
        break;
      default:
        return getAggregateType(paramType);
    } 
    if (!paramType.isNumberType())
      throw Error.error(5562); 
    if (this.typeWidth == 128 || ((NumberType)paramType).typeWidth == 128)
      return Type.SQL_DOUBLE; 
    if (paramInt != 35 || paramSession.database.sqlAvgScale == 0) {
      int j = this.typeWidth + ((NumberType)paramType).typeWidth;
      if (j <= 32)
        return Type.SQL_INTEGER; 
      if (j <= 64)
        return Type.SQL_BIGINT; 
    } 
    switch (paramInt) {
      case 32:
        i = (this.scale > paramType.scale) ? this.scale : paramType.scale;
        l = (this.precision - this.scale > paramType.precision - paramType.scale) ? (this.precision - this.scale) : (paramType.precision - paramType.scale);
        l++;
        return getNumberType(3, i + l, i);
      case 35:
        l = this.precision - this.scale + paramType.scale;
        i = (this.scale > paramType.scale) ? this.scale : paramType.scale;
        if (paramSession.database.sqlAvgScale > i)
          i = paramSession.database.sqlAvgScale; 
        return getNumberType(3, i + l, i);
      case 34:
        l = this.precision - this.scale + paramType.precision - paramType.scale;
        i = this.scale + paramType.scale;
        return getNumberType(3, i + l, i);
    } 
    throw Error.runtimeError(201, "NumberType");
  }
  
  public int compare(Session paramSession, Object paramObject1, Object paramObject2) {
    double d1;
    BigDecimal bigDecimal;
    double d2;
    if (paramObject1 == paramObject2)
      return 0; 
    if (paramObject1 == null)
      return -1; 
    if (paramObject2 == null)
      return 1; 
    switch (this.typeCode) {
      case -6:
      case 4:
      case 5:
        if (paramObject2 instanceof Integer) {
          int i = ((Number)paramObject1).intValue();
          int j = ((Number)paramObject2).intValue();
          return (i > j) ? 1 : ((j > i) ? -1 : 0);
        } 
        if (paramObject2 instanceof Double) {
          double d3 = ((Number)paramObject1).doubleValue();
          double d4 = ((Number)paramObject2).doubleValue();
          return (d3 > d4) ? 1 : ((d4 > d3) ? -1 : 0);
        } 
        if (paramObject2 instanceof BigDecimal) {
          BigDecimal bigDecimal1 = convertToDecimal(paramObject1);
          return bigDecimal1.compareTo((BigDecimal)paramObject2);
        } 
      case 25:
        if (paramObject2 instanceof Long) {
          long l1 = ((Number)paramObject1).longValue();
          long l2 = ((Number)paramObject2).longValue();
          return (l1 > l2) ? 1 : ((l2 > l1) ? -1 : 0);
        } 
        if (paramObject2 instanceof Double) {
          BigDecimal bigDecimal1 = BigDecimal.valueOf(((Number)paramObject1).longValue());
          BigDecimal bigDecimal2 = new BigDecimal(((Double)paramObject2).doubleValue());
          return bigDecimal1.compareTo(bigDecimal2);
        } 
        if (paramObject2 instanceof BigDecimal) {
          BigDecimal bigDecimal1 = convertToDecimal(paramObject1);
          return bigDecimal1.compareTo((BigDecimal)paramObject2);
        } 
      case 6:
      case 7:
      case 8:
        d1 = ((Number)paramObject1).doubleValue();
        d2 = ((Number)paramObject2).doubleValue();
        return Double.isNaN(d1) ? (Double.isNaN(d2) ? 0 : -1) : (Double.isNaN(d2) ? 1 : Double.compare(d1, d2));
      case 2:
      case 3:
        bigDecimal = convertToDecimal(paramObject2);
        return ((BigDecimal)paramObject1).compareTo(bigDecimal);
    } 
    throw Error.runtimeError(201, "NumberType");
  }
  
  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject) {
    BigDecimal bigDecimal;
    int i;
    if (paramObject == null)
      return null; 
    switch (this.typeCode) {
      case -6:
      case 4:
      case 5:
      case 25:
        return paramObject;
      case 6:
      case 7:
      case 8:
        return paramObject;
      case 2:
      case 3:
        bigDecimal = (BigDecimal)paramObject;
        if (this.scale != bigDecimal.scale())
          bigDecimal = bigDecimal.setScale(this.scale, 5); 
        i = JavaSystem.precision(bigDecimal);
        if (i > this.precision)
          throw Error.error(3403); 
        return bigDecimal;
    } 
    throw Error.runtimeError(201, "NumberType");
  }
  
  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType) {
    BigDecimal bigDecimal;
    if (paramObject == null)
      return paramObject; 
    if (paramType.typeCode == this.typeCode) {
      BigDecimal bigDecimal1;
      switch (this.typeCode) {
        case 2:
        case 3:
          bigDecimal1 = (BigDecimal)paramObject;
          if (this.scale != bigDecimal1.scale())
            bigDecimal1 = bigDecimal1.setScale(this.scale, 5); 
          if (JavaSystem.precision(bigDecimal1) > this.precision)
            throw Error.error(3403); 
          return bigDecimal1;
      } 
      return paramObject;
    } 
    if (paramType.isIntervalType()) {
      double d;
      int i = ((IntervalType)paramType).startIntervalType;
      switch (i) {
        case 101:
        case 102:
        case 103:
        case 104:
        case 105:
        case 106:
          d = ((IntervalType)paramType).convertToDoubleStartUnits(paramObject);
          return convertToType(paramSessionInterface, Double.valueOf(d), Type.SQL_DOUBLE);
      } 
    } 
    switch (paramType.typeCode) {
      case 40:
        paramObject = ((ClobData)paramObject).getSubString(paramSessionInterface, 0L, (int)((ClobData)paramObject).length(paramSessionInterface));
      case 1:
      case 12:
        paramObject = paramSessionInterface.getScanner().convertToNumber((String)paramObject, this);
        paramObject = convertToDefaultType(paramSessionInterface, paramObject);
        return convertToTypeLimits(paramSessionInterface, paramObject);
      case -6:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 25:
        break;
      case 14:
      case 15:
        if (paramType.precision == 1L) {
          if (((BinaryData)paramObject).getBytes()[0] == 0) {
            paramObject = ValuePool.INTEGER_0;
            break;
          } 
          paramObject = ValuePool.INTEGER_1;
          break;
        } 
      default:
        throw Error.error(5561);
    } 
    switch (this.typeCode) {
      case -6:
      case 4:
      case 5:
        return convertToInt(paramSessionInterface, paramObject, this.typeCode);
      case 25:
        return convertToLong(paramSessionInterface, paramObject);
      case 6:
      case 7:
      case 8:
        return convertToDouble(paramObject);
      case 2:
      case 3:
        bigDecimal = null;
        if (this.scale == 0 && paramObject instanceof Double) {
          double d = ((Number)paramObject).doubleValue();
          if (paramSessionInterface instanceof Session && !((Session)paramSessionInterface).database.sqlConvertTruncate)
            d = Math.rint(d); 
          if (Double.isInfinite(d) || Double.isNaN(d))
            throw Error.error(3403); 
          bigDecimal = BigDecimal.valueOf(d);
        } 
        if (bigDecimal == null)
          bigDecimal = convertToDecimal(paramObject); 
        return convertToTypeLimits(paramSessionInterface, bigDecimal);
    } 
    throw Error.error(5561);
  }
  
  public Object convertToTypeJDBC(SessionInterface paramSessionInterface, Object paramObject, Type paramType) {
    if (paramObject == null)
      return paramObject; 
    if (paramType.isLobType())
      throw Error.error(5561); 
    switch (paramType.typeCode) {
      case 16:
        paramObject = ((Boolean)paramObject).booleanValue() ? ValuePool.INTEGER_1 : ValuePool.INTEGER_0;
        paramType = Type.SQL_INTEGER;
        break;
    } 
    return convertToType(paramSessionInterface, paramObject, paramType);
  }
  
  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject) {
    CharacterType characterType;
    if (paramObject == null)
      return paramObject; 
    if (paramObject instanceof Number) {
      BigDecimal bigDecimal;
      if (paramObject instanceof BigInteger) {
        paramObject = new BigDecimal((BigInteger)paramObject);
      } else if (paramObject instanceof Float) {
        paramObject = new Double(((Float)paramObject).doubleValue());
      } else if (paramObject instanceof Byte) {
        paramObject = ValuePool.getInt(((Byte)paramObject).intValue());
      } else if (paramObject instanceof Short) {
        paramObject = ValuePool.getInt(((Short)paramObject).intValue());
      } 
      if (paramObject instanceof Integer) {
        NumberType numberType = Type.SQL_INTEGER;
      } else if (paramObject instanceof Long) {
        NumberType numberType = Type.SQL_BIGINT;
      } else if (paramObject instanceof Double) {
        NumberType numberType = Type.SQL_DOUBLE;
      } else if (paramObject instanceof BigDecimal) {
        NumberType numberType = Type.SQL_DECIMAL_DEFAULT;
      } else {
        throw Error.error(5561);
      } 
      switch (this.typeCode) {
        case -6:
        case 4:
        case 5:
          return convertToInt(paramSessionInterface, paramObject, 4);
        case 25:
          return convertToLong(paramSessionInterface, paramObject);
        case 6:
        case 7:
        case 8:
          return convertToDouble(paramObject);
        case 2:
        case 3:
          paramObject = convertToDecimal(paramObject);
          bigDecimal = (BigDecimal)paramObject;
          if (this.scale != bigDecimal.scale())
            bigDecimal = bigDecimal.setScale(this.scale, 5); 
          return bigDecimal;
      } 
      throw Error.error(5561);
    } 
    if (paramObject instanceof String) {
      characterType = Type.SQL_VARCHAR;
    } else {
      throw Error.error(5561);
    } 
    return convertToType(paramSessionInterface, paramObject, characterType);
  }
  
  public Object convertJavaToSQL(SessionInterface paramSessionInterface, Object paramObject) {
    return convertToDefaultType(paramSessionInterface, paramObject);
  }
  
  static Integer convertToInt(SessionInterface paramSessionInterface, Object paramObject, int paramInt) {
    int i;
    if (paramObject instanceof Integer) {
      if (paramInt == 4)
        return (Integer)paramObject; 
      i = ((Integer)paramObject).intValue();
    } else if (paramObject instanceof Long) {
      long l = ((Long)paramObject).longValue();
      if (2147483647L < l || l < -2147483648L)
        throw Error.error(3403); 
      i = (int)l;
    } else if (paramObject instanceof BigDecimal) {
      BigDecimal bigDecimal = (BigDecimal)paramObject;
      if (bigDecimal.compareTo(MAX_INT) > 0 || bigDecimal.compareTo(MIN_INT) < 0)
        throw Error.error(3403); 
      i = bigDecimal.intValue();
    } else if (paramObject instanceof Double || paramObject instanceof Float) {
      double d = ((Number)paramObject).doubleValue();
      if (paramSessionInterface instanceof Session && !((Session)paramSessionInterface).database.sqlConvertTruncate)
        d = Math.rint(d); 
      if (Double.isInfinite(d) || Double.isNaN(d) || d >= 2.147483648E9D || d <= -2.147483649E9D)
        throw Error.error(3403); 
      i = (int)d;
    } else {
      throw Error.error(5561);
    } 
    if (paramInt == -6) {
      if (127 < i || i < -128)
        throw Error.error(3403); 
    } else if (paramInt == 5 && (32767 < i || i < -32768)) {
      throw Error.error(3403);
    } 
    return Integer.valueOf(i);
  }
  
  static Long convertToLong(SessionInterface paramSessionInterface, Object paramObject) {
    if (paramObject instanceof Integer)
      return ValuePool.getLong(((Integer)paramObject).intValue()); 
    if (paramObject instanceof Long)
      return (Long)paramObject; 
    if (paramObject instanceof BigDecimal) {
      BigDecimal bigDecimal = (BigDecimal)paramObject;
      if (bigDecimal.compareTo(MAX_LONG) > 0 || bigDecimal.compareTo(MIN_LONG) < 0)
        throw Error.error(3403); 
      return ValuePool.getLong(bigDecimal.longValue());
    } 
    if (paramObject instanceof Double || paramObject instanceof Float) {
      double d = ((Number)paramObject).doubleValue();
      if (paramSessionInterface instanceof Session && !((Session)paramSessionInterface).database.sqlConvertTruncate)
        d = Math.rint(d); 
      if (Double.isInfinite(d) || Double.isNaN(d) || d >= 9.223372036854776E18D || d <= -9.223372036854776E18D)
        throw Error.error(3403); 
      return ValuePool.getLong((long)d);
    } 
    throw Error.error(5561);
  }
  
  private static Double convertToDouble(Object paramObject) {
    if (paramObject instanceof Double)
      return (Double)paramObject; 
    double d = toDouble(paramObject);
    return ValuePool.getDouble(Double.doubleToLongBits(d));
  }
  
  public static double toDouble(Object paramObject) {
    double d;
    if (paramObject instanceof Double)
      return ((Double)paramObject).doubleValue(); 
    if (paramObject instanceof BigDecimal) {
      BigDecimal bigDecimal1 = (BigDecimal)paramObject;
      d = bigDecimal1.doubleValue();
      int i = bigDecimal1.signum();
      BigDecimal bigDecimal2 = new BigDecimal(d + i);
      if (bigDecimal2.compareTo(bigDecimal1) != i)
        throw Error.error(3403); 
    } else if (paramObject instanceof Number) {
      d = ((Number)paramObject).doubleValue();
    } else {
      throw Error.error(3471);
    } 
    return d;
  }
  
  private static BigDecimal convertToDecimal(Object paramObject) {
    if (paramObject instanceof BigDecimal)
      return (BigDecimal)paramObject; 
    if (paramObject instanceof Integer || paramObject instanceof Long)
      return BigDecimal.valueOf(((Number)paramObject).longValue()); 
    if (paramObject instanceof Double) {
      double d = ((Number)paramObject).doubleValue();
      if (Double.isInfinite(d) || Double.isNaN(d))
        throw Error.error(3403); 
      return BigDecimal.valueOf(d);
    } 
    throw Error.runtimeError(201, "NumberType");
  }
  
  public String convertToString(Object paramObject) {
    double d;
    String str;
    if (paramObject == null)
      return null; 
    switch (this.typeCode) {
      case -6:
      case 4:
      case 5:
      case 25:
        return paramObject.toString();
      case 7:
      case 8:
        d = ((Double)paramObject).doubleValue();
        if (d == Double.NEGATIVE_INFINITY)
          return "-1E0/0"; 
        if (d == Double.POSITIVE_INFINITY)
          return "1E0/0"; 
        if (Double.isNaN(d))
          return "0E0/0E0"; 
        str = Double.toString(d);
        if (str.indexOf('E') < 0)
          str = str.concat("E0"); 
        return str;
      case 2:
      case 3:
        return JavaSystem.toString((BigDecimal)paramObject);
    } 
    throw Error.runtimeError(201, "NumberType");
  }
  
  public String convertToSQLString(Object paramObject) {
    return (paramObject == null) ? "NULL" : convertToString(paramObject);
  }
  
  public boolean canConvertFrom(Type paramType) {
    return (paramType.typeCode == 0) ? true : (paramType.isNumberType() ? true : (paramType.isIntervalType() ? true : (paramType.isCharacterType() ? true : ((paramType.isBitType() && paramType.precision == 1L)))));
  }
  
  public int canMoveFrom(Type paramType) {
    if (paramType == this)
      return 0; 
    switch (this.typeCode) {
      case -6:
        if (paramType.typeCode == 5 || paramType.typeCode == 4)
          return 1; 
        break;
      case 5:
        if (paramType.typeCode == -6)
          return 0; 
        if (paramType.typeCode == 4)
          return 1; 
        break;
      case 4:
        if (paramType.typeCode == 5 || paramType.typeCode == -6)
          return 0; 
        break;
      case 2:
      case 3:
        if ((paramType.typeCode == 3 || paramType.typeCode == 2) && this.scale == paramType.scale)
          return (this.precision >= paramType.precision) ? 0 : 1; 
        break;
      case 6:
      case 7:
      case 8:
        if (paramType.typeCode == 7 || paramType.typeCode == 6 || paramType.typeCode == 8)
          return 0; 
        break;
    } 
    return -1;
  }
  
  public int compareToTypeRange(Object paramObject) {
    if (!(paramObject instanceof Number))
      return 0; 
    if (paramObject instanceof Integer || paramObject instanceof Long) {
      int i;
      int j;
      BigDecimal bigDecimal;
      int k;
      int m;
      long l = ((Number)paramObject).longValue();
      switch (this.typeCode) {
        case -6:
          i = -128;
          j = 127;
          break;
        case 5:
          i = -32768;
          j = 32767;
          break;
        case 4:
          i = Integer.MIN_VALUE;
          j = Integer.MAX_VALUE;
          break;
        case 25:
          return 0;
        case 2:
        case 3:
          if (this.precision - this.scale > 18L)
            return 0; 
          if (this.precision - this.scale > 9L && paramObject instanceof Integer)
            return 0; 
          bigDecimal = convertToDecimal(paramObject);
          k = bigDecimal.scale();
          m = JavaSystem.precision(bigDecimal);
          if (k < 0) {
            m -= k;
            k = 0;
          } 
          return (this.precision - this.scale >= (m - k)) ? 0 : bigDecimal.signum();
        default:
          return 0;
      } 
      return (j < l) ? 1 : ((l < i) ? -1 : 0);
    } 
    return 0;
  }
  
  public Object add(Session paramSession, Object paramObject1, Object paramObject2, Type paramType) {
    double d1;
    BigDecimal bigDecimal1;
    int i;
    long l1;
    BigDecimal bigDecimal2;
    int j;
    double d2;
    long l2;
    if (paramObject1 == null || paramObject2 == null)
      return null; 
    switch (this.typeCode) {
      case 6:
      case 7:
      case 8:
        d1 = ((Number)paramObject1).doubleValue();
        d2 = ((Number)paramObject2).doubleValue();
        return ValuePool.getDouble(Double.doubleToLongBits(d1 + d2));
      case 2:
      case 3:
        paramObject1 = convertToDefaultType((SessionInterface)null, paramObject1);
        paramObject2 = convertToDefaultType((SessionInterface)null, paramObject2);
        bigDecimal1 = (BigDecimal)paramObject1;
        bigDecimal2 = (BigDecimal)paramObject2;
        bigDecimal1 = bigDecimal1.add(bigDecimal2);
        return convertToTypeLimits((SessionInterface)null, bigDecimal1);
      case -6:
      case 4:
      case 5:
        i = ((Number)paramObject1).intValue();
        j = ((Number)paramObject2).intValue();
        return ValuePool.getInt(i + j);
      case 25:
        l1 = ((Number)paramObject1).longValue();
        l2 = ((Number)paramObject2).longValue();
        return ValuePool.getLong(l1 + l2);
    } 
    throw Error.runtimeError(201, "NumberType");
  }
  
  public Object subtract(Session paramSession, Object paramObject1, Object paramObject2, Type paramType) {
    double d1;
    BigDecimal bigDecimal1;
    int i;
    long l1;
    BigDecimal bigDecimal2;
    int j;
    double d2;
    long l2;
    if (paramObject1 == null || paramObject2 == null)
      return null; 
    switch (this.typeCode) {
      case 6:
      case 7:
      case 8:
        d1 = ((Number)paramObject1).doubleValue();
        d2 = ((Number)paramObject2).doubleValue();
        return ValuePool.getDouble(Double.doubleToLongBits(d1 - d2));
      case 2:
      case 3:
        paramObject1 = convertToDefaultType((SessionInterface)null, paramObject1);
        paramObject2 = convertToDefaultType((SessionInterface)null, paramObject2);
        bigDecimal1 = (BigDecimal)paramObject1;
        bigDecimal2 = (BigDecimal)paramObject2;
        bigDecimal1 = bigDecimal1.subtract(bigDecimal2);
        return convertToTypeLimits((SessionInterface)null, bigDecimal1);
      case -6:
      case 4:
      case 5:
        i = ((Number)paramObject1).intValue();
        j = ((Number)paramObject2).intValue();
        return ValuePool.getInt(i - j);
      case 25:
        l1 = ((Number)paramObject1).longValue();
        l2 = ((Number)paramObject2).longValue();
        return ValuePool.getLong(l1 - l2);
    } 
    throw Error.runtimeError(201, "NumberType");
  }
  
  public Object multiply(Object paramObject1, Object paramObject2) {
    double d1;
    BigDecimal bigDecimal1;
    int i;
    long l1;
    BigDecimal bigDecimal2;
    int j;
    double d2;
    BigDecimal bigDecimal3;
    long l2;
    if (paramObject1 == null || paramObject2 == null)
      return null; 
    switch (this.typeCode) {
      case 6:
      case 7:
      case 8:
        d1 = ((Number)paramObject1).doubleValue();
        d2 = ((Number)paramObject2).doubleValue();
        return ValuePool.getDouble(Double.doubleToLongBits(d1 * d2));
      case 2:
      case 3:
        if (!(paramObject1 instanceof BigDecimal))
          paramObject1 = convertToDefaultType((SessionInterface)null, paramObject1); 
        if (!(paramObject2 instanceof BigDecimal))
          paramObject2 = convertToDefaultType((SessionInterface)null, paramObject2); 
        bigDecimal1 = (BigDecimal)paramObject1;
        bigDecimal2 = (BigDecimal)paramObject2;
        bigDecimal3 = bigDecimal1.multiply(bigDecimal2);
        return convertToTypeLimits((SessionInterface)null, bigDecimal3);
      case -6:
      case 4:
      case 5:
        i = ((Number)paramObject1).intValue();
        j = ((Number)paramObject2).intValue();
        return ValuePool.getInt(i * j);
      case 25:
        l1 = ((Number)paramObject1).longValue();
        l2 = ((Number)paramObject2).longValue();
        return ValuePool.getLong(l1 * l2);
    } 
    throw Error.runtimeError(201, "NumberType");
  }
  
  public Object divide(Session paramSession, Object paramObject1, Object paramObject2) {
    double d1;
    BigDecimal bigDecimal1;
    int i;
    long l1;
    BigDecimal bigDecimal2;
    int j;
    double d2;
    BigDecimal bigDecimal3;
    long l2;
    if (paramObject1 == null || paramObject2 == null)
      return null; 
    switch (this.typeCode) {
      case 6:
      case 7:
      case 8:
        d1 = ((Number)paramObject1).doubleValue();
        d2 = ((Number)paramObject2).doubleValue();
        if (d2 == 0.0D && (paramSession == null || paramSession.database.sqlDoubleNaN))
          throw Error.error(3432); 
        return ValuePool.getDouble(Double.doubleToLongBits(d1 / d2));
      case 2:
      case 3:
        if (!(paramObject1 instanceof BigDecimal))
          paramObject1 = convertToDefaultType((SessionInterface)null, paramObject1); 
        if (!(paramObject2 instanceof BigDecimal))
          paramObject2 = convertToDefaultType((SessionInterface)null, paramObject2); 
        bigDecimal1 = (BigDecimal)paramObject1;
        bigDecimal2 = (BigDecimal)paramObject2;
        if (bigDecimal2.signum() == 0)
          throw Error.error(3432); 
        bigDecimal3 = bigDecimal1.divide(bigDecimal2, this.scale, 1);
        return convertToTypeLimits((SessionInterface)null, bigDecimal3);
      case -6:
      case 4:
      case 5:
        i = ((Number)paramObject1).intValue();
        j = ((Number)paramObject2).intValue();
        if (j == 0)
          throw Error.error(3432); 
        return ValuePool.getInt(i / j);
      case 25:
        l1 = ((Number)paramObject1).longValue();
        l2 = ((Number)paramObject2).longValue();
        if (l2 == 0L)
          throw Error.error(3432); 
        return ValuePool.getLong(l1 / l2);
    } 
    throw Error.runtimeError(201, "NumberType");
  }
  
  public Object modulo(Session paramSession, Object paramObject1, Object paramObject2, Type paramType) {
    if (!paramType.isNumberType())
      throw Error.error(5561); 
    Object object = divide((Session)null, paramObject1, paramObject2);
    object = multiply(object, paramObject2);
    object = convertToDefaultType((SessionInterface)null, object);
    object = subtract(paramSession, paramObject1, object, this);
    return convertToTypeLimits((SessionInterface)null, object);
  }
  
  public Object absolute(Object paramObject) {
    return isNegative(paramObject) ? negate(paramObject) : paramObject;
  }
  
  public Object negate(Object paramObject) {
    double d;
    int i;
    long l;
    if (paramObject == null)
      return null; 
    switch (this.typeCode) {
      case 6:
      case 7:
      case 8:
        d = -((Number)paramObject).doubleValue();
        return ValuePool.getDouble(Double.doubleToLongBits(d));
      case 2:
      case 3:
        return ((BigDecimal)paramObject).negate();
      case -6:
        i = ((Number)paramObject).intValue();
        if (i == -128)
          throw Error.error(3403); 
        return ValuePool.getInt(-i);
      case 5:
        i = ((Number)paramObject).intValue();
        if (i == -32768)
          throw Error.error(3403); 
        return ValuePool.getInt(-i);
      case 4:
        i = ((Number)paramObject).intValue();
        if (i == Integer.MIN_VALUE)
          throw Error.error(3403); 
        return ValuePool.getInt(-i);
      case 25:
        l = ((Number)paramObject).longValue();
        if (l == Long.MIN_VALUE)
          throw Error.error(3403); 
        return ValuePool.getLong(-l);
    } 
    throw Error.runtimeError(201, "NumberType");
  }
  
  public int getNumericPrecisionInRadix() {
    switch (this.typeCode) {
      case -6:
        return 8;
      case 5:
        return 16;
      case 4:
        return 32;
      case 25:
        return 64;
      case 6:
      case 7:
      case 8:
        return 64;
      case 2:
      case 3:
        return (int)this.precision;
    } 
    throw Error.runtimeError(201, "NumberType");
  }
  
  public Type getIntegralType() {
    switch (this.typeCode) {
      case 6:
      case 7:
      case 8:
        return SQL_NUMERIC_DEFAULT_INT;
      case 2:
      case 3:
        return (this.scale == 0) ? this : new NumberType(this.typeCode, this.precision, 0);
    } 
    return this;
  }
  
  public static boolean isZero(Object paramObject) {
    return (paramObject instanceof BigDecimal) ? ((((BigDecimal)paramObject).signum() == 0)) : ((paramObject instanceof Double) ? ((((Double)paramObject).doubleValue() == 0.0D || ((Double)paramObject).isNaN())) : ((((Number)paramObject).longValue() == 0L)));
  }
  
  public boolean isNegative(Object paramObject) {
    double d;
    if (paramObject == null)
      return false; 
    switch (this.typeCode) {
      case 6:
      case 7:
      case 8:
        d = ((Number)paramObject).doubleValue();
        return (d < 0.0D);
      case 2:
      case 3:
        return (((BigDecimal)paramObject).signum() < 0);
      case -6:
      case 4:
      case 5:
        return (((Number)paramObject).intValue() < 0);
      case 25:
        return (((Number)paramObject).longValue() < 0L);
    } 
    throw Error.runtimeError(201, "NumberType");
  }
  
  public int compareToZero(Object paramObject) {
    double d;
    int i;
    long l;
    if (paramObject == null)
      return 0; 
    switch (this.typeCode) {
      case 6:
      case 7:
      case 8:
        d = ((Number)paramObject).doubleValue();
        return (d == 0.0D) ? 0 : ((d < 0.0D) ? -1 : 1);
      case 2:
      case 3:
        return ((BigDecimal)paramObject).signum();
      case -6:
      case 4:
      case 5:
        i = ((Number)paramObject).intValue();
        return (i == 0) ? 0 : ((i < 0) ? -1 : 1);
      case 25:
        l = ((Number)paramObject).longValue();
        return (l == 0L) ? 0 : ((l < 0L) ? -1 : 1);
    } 
    throw Error.runtimeError(201, "NumberType");
  }
  
  public static long scaledDecimal(Object paramObject, int paramInt) {
    if (paramObject == null)
      return 0L; 
    if (paramInt == 0)
      return 0L; 
    BigDecimal bigDecimal = (BigDecimal)paramObject;
    if (bigDecimal.scale() == 0)
      return 0L; 
    bigDecimal = bigDecimal.setScale(0, 3);
    bigDecimal = ((BigDecimal)paramObject).subtract(bigDecimal);
    return bigDecimal.movePointRight(paramInt).longValue();
  }
  
  public static boolean isInLongLimits(BigDecimal paramBigDecimal) {
    return (MIN_LONG.compareTo(paramBigDecimal) <= 0 && MAX_LONG.compareTo(paramBigDecimal) >= 0);
  }
  
  public static boolean isInLongLimits(BigInteger paramBigInteger) {
    return (MIN_LONG_BI.compareTo(paramBigInteger) <= 0 && MAX_LONG_BI.compareTo(paramBigInteger) >= 0);
  }
  
  public Object ceiling(Object paramObject) {
    double d;
    if (paramObject == null)
      return null; 
    switch (this.typeCode) {
      case 6:
      case 7:
      case 8:
        d = Math.ceil(((Double)paramObject).doubleValue());
        if (Double.isInfinite(d))
          throw Error.error(3403); 
        return ValuePool.getDouble(Double.doubleToLongBits(d));
      case 2:
      case 3:
        return ((BigDecimal)paramObject).setScale(0, 2);
    } 
    return paramObject;
  }
  
  public Object floor(Object paramObject) {
    double d;
    if (paramObject == null)
      return null; 
    switch (this.typeCode) {
      case 6:
      case 7:
      case 8:
        d = Math.floor(((Double)paramObject).doubleValue());
        if (Double.isInfinite(d))
          throw Error.error(3403); 
        return ValuePool.getDouble(Double.doubleToLongBits(d));
      case 2:
      case 3:
        return ((BigDecimal)paramObject).setScale(0, 3);
    } 
    return paramObject;
  }
  
  public Object truncate(Object paramObject, int paramInt) {
    if (paramObject == null)
      return null; 
    BigDecimal bigDecimal = convertToDecimal(paramObject);
    bigDecimal = bigDecimal.setScale(paramInt, 1);
    if (this.typeCode == 3 || this.typeCode == 2)
      bigDecimal = bigDecimal.setScale(this.scale, 1); 
    paramObject = convertToDefaultType((SessionInterface)null, bigDecimal);
    return convertToTypeLimits((SessionInterface)null, paramObject);
  }
  
  public Object round(Object paramObject, int paramInt) {
    if (paramObject == null)
      return null; 
    BigDecimal bigDecimal = convertToDecimal(paramObject);
    switch (this.typeCode) {
      case 8:
        bigDecimal = bigDecimal.setScale(paramInt, 6);
        paramObject = convertToDefaultType((SessionInterface)null, bigDecimal);
        return convertToTypeLimits((SessionInterface)null, paramObject);
    } 
    bigDecimal = bigDecimal.setScale(paramInt, 4);
    bigDecimal = bigDecimal.setScale(this.scale, 1);
    paramObject = convertToDefaultType((SessionInterface)null, bigDecimal);
    return convertToTypeLimits((SessionInterface)null, paramObject);
  }
  
  public static NumberType getNumberType(int paramInt1, long paramLong, int paramInt2) {
    switch (paramInt1) {
      case 4:
        return SQL_INTEGER;
      case 5:
        return SQL_SMALLINT;
      case 25:
        return SQL_BIGINT;
      case -6:
        return TINYINT;
      case 7:
      case 8:
        return SQL_DOUBLE;
      case 2:
      case 3:
        return new NumberType(paramInt1, paramLong, paramInt2);
    } 
    throw Error.runtimeError(201, "NumberType");
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\NumberType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */