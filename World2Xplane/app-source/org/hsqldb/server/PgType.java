package org.hsqldb.server;

import java.sql.SQLException;
import org.hsqldb.HsqlException;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.jdbc.JDBCUtil;
import org.hsqldb.types.Type;

public class PgType {
  private int oid;
  
  private int typeWidth = -1;
  
  private int lpConstraint = -1;
  
  private Type hType;
  
  public static final int TYPE_BOOL = 16;
  
  public static final int TYPE_BYTEA = 17;
  
  public static final int TYPE_CHAR = 18;
  
  public static final int TYPE_NAME = 19;
  
  public static final int TYPE_INT8 = 20;
  
  public static final int TYPE_INT2 = 21;
  
  public static final int TYPE_INT2VECTOR = 22;
  
  public static final int TYPE_INT4 = 23;
  
  public static final int TYPE_REGPROC = 24;
  
  public static final int TYPE_TEXT = 25;
  
  public static final int TYPE_OID = 26;
  
  public static final int TYPE_TID = 27;
  
  public static final int TYPE_XID = 28;
  
  public static final int TYPE_CID = 29;
  
  public static final int TYPE_OIDVECTOR = 30;
  
  public static final int TYPE_SET = 32;
  
  public static final int TYPE_XML = 142;
  
  public static final int TYPE_XMLARRAY = 143;
  
  public static final int TYPE_CHAR2 = 409;
  
  public static final int TYPE_CHAR4 = 410;
  
  public static final int TYPE_CHAR8 = 411;
  
  public static final int TYPE_POINT = 600;
  
  public static final int TYPE_LSEG = 601;
  
  public static final int TYPE_PATH = 602;
  
  public static final int TYPE_BOX = 603;
  
  public static final int TYPE_POLYGON = 604;
  
  public static final int TYPE_FILENAME = 605;
  
  public static final int TYPE_CIDR = 650;
  
  public static final int TYPE_FLOAT4 = 700;
  
  public static final int TYPE_FLOAT8 = 701;
  
  public static final int TYPE_ABSTIME = 702;
  
  public static final int TYPE_RELTIME = 703;
  
  public static final int TYPE_TINTERVAL = 704;
  
  public static final int TYPE_UNKNOWN = 705;
  
  public static final int TYPE_MONEY = 790;
  
  public static final int TYPE_OIDINT2 = 810;
  
  public static final int TYPE_MACADDR = 829;
  
  public static final int TYPE_INET = 869;
  
  public static final int TYPE_OIDINT4 = 910;
  
  public static final int TYPE_OIDNAME = 911;
  
  public static final int TYPE_TEXTARRAY = 1009;
  
  public static final int TYPE_BPCHARARRAY = 1014;
  
  public static final int TYPE_VARCHARARRAY = 1015;
  
  public static final int TYPE_BPCHAR = 1042;
  
  public static final int TYPE_VARCHAR = 1043;
  
  public static final int TYPE_DATE = 1082;
  
  public static final int TYPE_TIME = 1083;
  
  public static final int TYPE_TIMESTAMP_NO_TMZONE = 1114;
  
  public static final int TYPE_DATETIME = 1184;
  
  public static final int TYPE_TIME_WITH_TMZONE = 1266;
  
  public static final int TYPE_TIMESTAMP = 1296;
  
  public static final int TYPE_NUMERIC = 1700;
  
  public static final int TYPE_RECORD = 2249;
  
  public static final int TYPE_VOID = 2278;
  
  public static final int TYPE_UUID = 2950;
  
  public static final int TYPE_BLOB = 9998;
  
  public static final int TYPE_TINYINT = 9999;
  
  public static final int TYPE_BIT = 1560;
  
  public static final int TYPE_VARBIT = 1562;
  
  protected static final PgType tinyIntSingleton = new PgType((Type)Type.TINYINT, 9999, 1);
  
  protected static final PgType int2singleton = new PgType((Type)Type.SQL_SMALLINT, 21, 2);
  
  protected static final PgType int4singleton = new PgType((Type)Type.SQL_INTEGER, 23, 4);
  
  protected static final PgType int8singleton = new PgType((Type)Type.SQL_BIGINT, 20, 8);
  
  protected static final PgType doubleSingleton = new PgType((Type)Type.SQL_DOUBLE, 701, 8);
  
  protected static final PgType boolSingleton = new PgType((Type)Type.SQL_BOOLEAN, 16, 1);
  
  protected static final PgType textSingleton = new PgType((Type)Type.SQL_VARCHAR, 25);
  
  protected static final PgType dateSingleton = new PgType((Type)Type.SQL_DATE, 1082, 4);
  
  protected static final PgType unknownSingleton = new PgType((Type)Type.SQL_CHAR_DEFAULT, 705, -2);
  
  protected static final PgType bitSingleton = new PgType((Type)Type.SQL_BIT, 1560);
  
  protected static final PgType bitVaryingSingleton = new PgType((Type)Type.SQL_BIT_VARYING, 1562);
  
  protected static final PgType daySecIntervalSingleton = new PgType((Type)Type.SQL_INTERVAL_DAY_TO_SECOND, 704, 16);
  
  protected static final PgType hourSecIntervalSingleton = new PgType((Type)Type.SQL_INTERVAL_HOUR_TO_SECOND, 704, 16);
  
  protected static final PgType minSecIntervalSingleton = new PgType((Type)Type.SQL_INTERVAL_MINUTE_TO_SECOND, 704, 16);
  
  protected static final PgType secIntervalSingleton = new PgType((Type)Type.SQL_INTERVAL_SECOND, 704, 16);
  
  public int getOid() {
    return this.oid;
  }
  
  public int getTypeWidth() {
    return this.typeWidth;
  }
  
  public int getLPConstraint() {
    return this.lpConstraint;
  }
  
  protected PgType(Type paramType, int paramInt) {
    this(paramType, paramInt, (Integer)null, (Integer)null);
  }
  
  protected PgType(Type paramType, int paramInt1, int paramInt2) {
    this(paramType, paramInt1, new Integer(paramInt2), (Integer)null);
  }
  
  protected PgType(Type paramType, int paramInt, Integer paramInteger, long paramLong) throws RecoverableOdbcFailure {
    this(paramType, paramInt, paramInteger, new Integer((int)paramLong));
    if (paramLong < 0L)
      throw new RecoverableOdbcFailure("Length/Precision value is below minimum value of 0"); 
    if (paramLong > 2147483647L)
      throw new RecoverableOdbcFailure("Length/Precision value is above maximum value of 2147483647"); 
  }
  
  protected PgType(Type paramType, int paramInt, Integer paramInteger1, Integer paramInteger2) {
    this.hType = paramType;
    this.oid = paramInt;
    this.typeWidth = (paramInteger1 == null) ? -1 : paramInteger1.intValue();
    this.lpConstraint = (paramInteger2 == null) ? -1 : paramInteger2.intValue();
  }
  
  public static PgType getPgType(Type paramType, boolean paramBoolean) throws RecoverableOdbcFailure {
    switch (paramType.typeCode) {
      case -6:
        return tinyIntSingleton;
      case 5:
        return int2singleton;
      case 4:
        return int4singleton;
      case 25:
        return int8singleton;
      case 2:
      case 3:
        return new PgType(paramType, 1700, null, (paramType.precision << 16L) + paramType.scale + 4L);
      case 6:
      case 7:
      case 8:
        return doubleSingleton;
      case 16:
        return boolSingleton;
      case 1:
        return paramBoolean ? new PgType(paramType, 1042, null, paramType.precision + 4L) : unknownSingleton;
      case 12:
        if (paramType.precision < 0L)
          throw new RecoverableOdbcFailure("Length/Precision value is below minimum value of 0"); 
        if (paramType.precision > 2147483647L)
          throw new RecoverableOdbcFailure("Length/Precision value is above maximum value of 2147483647"); 
        return (paramType.precision != 0L && paramBoolean) ? new PgType(paramType, 1043, null, paramType.precision + 4L) : textSingleton;
      case 40:
        throw new RecoverableOdbcFailure("Driver doesn't support type 'CLOB' yet");
      case 30:
        return new PgType(paramType, 9998, null, paramType.precision);
      case 60:
      case 61:
        return new PgType(paramType, 17, null, paramType.precision);
      case 1111:
        throw new RecoverableOdbcFailure("Driver doesn't support type 'OTHER' yet");
      case 14:
        return bitSingleton;
      case 15:
        return bitVaryingSingleton;
      case 91:
        return dateSingleton;
      case 92:
        return new PgType(paramType, 1083, new Integer(8), paramType.precision);
      case 94:
        return new PgType(paramType, 1266, new Integer(12), paramType.precision);
      case 93:
        return new PgType(paramType, 1114, new Integer(8), paramType.precision);
      case 95:
        return new PgType(paramType, 1296, new Integer(8), paramType.precision);
      case 101:
      case 102:
      case 107:
        throw new RecoverableOdbcFailure("Driver doesn't support month-resolution 'INTERVAL's yet");
      case 103:
      case 104:
      case 105:
      case 108:
      case 109:
      case 111:
        throw new RecoverableOdbcFailure("Driver doesn't support non-second-resolution 'INTERVAL's yet");
      case 110:
        ignoredConstraintWarning(paramType);
        return daySecIntervalSingleton;
      case 112:
        ignoredConstraintWarning(paramType);
        return hourSecIntervalSingleton;
      case 113:
        ignoredConstraintWarning(paramType);
        return minSecIntervalSingleton;
      case 106:
        ignoredConstraintWarning(paramType);
        return secIntervalSingleton;
    } 
    throw new RecoverableOdbcFailure("Unsupported type: " + paramType.getNameString());
  }
  
  public Object getParameter(String paramString, Session paramSession) throws SQLException, RecoverableOdbcFailure {
    if (paramString == null)
      return null; 
    Object object = paramString;
    switch (this.hType.typeCode) {
      case 16:
        if (paramString.length() == 1) {
          switch (paramString.charAt(0)) {
            case '1':
            case 'T':
            case 'Y':
            case 't':
            case 'y':
              return Boolean.TRUE;
          } 
          return Boolean.FALSE;
        } 
        return Boolean.valueOf(paramString);
      case 30:
      case 60:
      case 61:
        throw new RecoverableOdbcFailure("This data type should be transmitted to server in binary format: " + this.hType.getNameString());
      case 40:
      case 1111:
        throw new RecoverableOdbcFailure("Type not supported yet: " + this.hType.getNameString());
      case 91:
      case 92:
      case 93:
      case 94:
      case 95:
        try {
          object = this.hType.convertToType((SessionInterface)paramSession, object, (Type)Type.SQL_VARCHAR);
        } catch (HsqlException hsqlException) {
          throwError(hsqlException);
        } 
        return object;
      case -6:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 25:
        try {
          object = this.hType.convertToType((SessionInterface)paramSession, object, (Type)Type.SQL_VARCHAR);
        } catch (HsqlException hsqlException) {
          throwError(hsqlException);
        } 
        return object;
    } 
    try {
      object = this.hType.convertToDefaultType((SessionInterface)paramSession, object);
    } catch (HsqlException hsqlException) {
      throwError(hsqlException);
    } 
    return object;
  }
  
  public String valueString(Object paramObject) {
    String str = this.hType.convertToString(paramObject);
    switch (this.hType.typeCode) {
      case 16:
        return String.valueOf(((Boolean)paramObject).booleanValue() ? 116 : 102);
      case 60:
      case 61:
        str = OdbcUtil.hexCharsToOctalOctets(str);
        break;
    } 
    return str;
  }
  
  static final void throwError(HsqlException paramHsqlException) throws SQLException {
    throw JDBCUtil.sqlException(paramHsqlException.getMessage(), paramHsqlException.getSQLState(), paramHsqlException.getErrorCode(), paramHsqlException);
  }
  
  private static void ignoredConstraintWarning(Type paramType) {
    if (paramType.precision == 0L && paramType.scale == 0)
      return; 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\server\PgType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */