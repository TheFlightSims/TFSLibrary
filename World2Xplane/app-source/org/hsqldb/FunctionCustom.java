package org.hsqldb;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.IntKeyIntValueHashMap;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.lib.StringUtil;
import org.hsqldb.map.BitMap;
import org.hsqldb.map.ValuePool;
import org.hsqldb.persist.Crypto;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BinaryType;
import org.hsqldb.types.BlobData;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.ClobData;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.IntervalMonthData;
import org.hsqldb.types.IntervalSecondData;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.LobData;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

public class FunctionCustom extends FunctionSQL {
  public static final String[] openGroupNumericFunctions = new String[] { 
      "ABS", "ACOS", "ASIN", "ATAN", "ATAN2", "BITAND", "BITOR", "BITXOR", "CEILING", "COS", 
      "COT", "DEGREES", "EXP", "FLOOR", "LOG", "LOG10", "MOD", "PI", "POWER", "RADIANS", 
      "RAND", "ROUND", "ROUNDMAGIC", "SIGN", "SIN", "SQRT", "TAN", "TRUNCATE" };
  
  public static final String[] openGroupStringFunctions = new String[] { 
      "ASCII", "CHAR", "CONCAT", "DIFFERENCE", "HEXTORAW", "INSERT", "LCASE", "LEFT", "LENGTH", "LOCATE", 
      "LTRIM", "RAWTOHEX", "REPEAT", "REPLACE", "RIGHT", "RTRIM", "SOUNDEX", "SPACE", "SUBSTR", "UCASE" };
  
  public static final String[] openGroupDateTimeFunctions = new String[] { 
      "CURDATE", "CURTIME", "DATEDIFF", "DAYNAME", "DAYOFMONTH", "DAYOFWEEK", "DAYOFYEAR", "HOUR", "MINUTE", "MONTH", 
      "MONTHNAME", "NOW", "QUARTER", "SECOND", "SECONDS_SINCE_MIDNIGHT", "TIMESTAMPADD", "TIMESTAMPDIFF", "TO_CHAR", "WEEK", "YEAR" };
  
  public static final String[] openGroupSystemFunctions = new String[] { "DATABASE", "IFNULL", "USER" };
  
  private static final int FUNC_ACOS = 71;
  
  private static final int FUNC_ACTION_ID = 72;
  
  private static final int FUNC_ADD_MONTHS = 73;
  
  private static final int FUNC_ASCII = 74;
  
  private static final int FUNC_ASIN = 75;
  
  private static final int FUNC_ATAN = 76;
  
  private static final int FUNC_ATAN2 = 77;
  
  private static final int FUNC_BITAND = 78;
  
  private static final int FUNC_BITANDNOT = 79;
  
  private static final int FUNC_BITNOT = 80;
  
  private static final int FUNC_BITOR = 81;
  
  private static final int FUNC_BITXOR = 82;
  
  private static final int FUNC_CHAR = 83;
  
  private static final int FUNC_CONCAT = 84;
  
  private static final int FUNC_COS = 85;
  
  private static final int FUNC_COT = 86;
  
  private static final int FUNC_CRYPT_KEY = 87;
  
  private static final int FUNC_DATABASE = 88;
  
  private static final int FUNC_DATABASE_ISOLATION_LEVEL = 89;
  
  private static final int FUNC_DATABASE_NAME = 90;
  
  private static final int FUNC_DATABASE_TIMEZONE = 91;
  
  private static final int FUNC_DATABASE_VERSION = 92;
  
  private static final int FUNC_DATE_ADD = 93;
  
  private static final int FUNC_DATE_SUB = 94;
  
  private static final int FUNC_DATEADD = 95;
  
  private static final int FUNC_DATEDIFF = 96;
  
  private static final int FUNC_DAYS = 97;
  
  private static final int FUNC_DBTIMEZONE = 98;
  
  private static final int FUNC_DEGREES = 99;
  
  private static final int FUNC_DIAGNOSTICS = 100;
  
  private static final int FUNC_DIFFERENCE = 101;
  
  private static final int FUNC_FROM_TZ = 102;
  
  private static final int FUNC_HEXTORAW = 103;
  
  private static final int FUNC_IDENTITY = 104;
  
  private static final int FUNC_INSTR = 105;
  
  private static final int FUNC_ISAUTOCOMMIT = 106;
  
  private static final int FUNC_ISOLATION_LEVEL = 107;
  
  private static final int FUNC_ISREADONLYDATABASE = 108;
  
  private static final int FUNC_ISREADONLYDATABASEFILES = 109;
  
  private static final int FUNC_ISREADONLYSESSION = 110;
  
  private static final int FUNC_LAST_DAY = 111;
  
  private static final int FUNC_LEFT = 112;
  
  private static final int FUNC_LOAD_FILE = 113;
  
  private static final int FUNC_LOB_ID = 114;
  
  private static final int FUNC_LOCATE = 115;
  
  private static final int FUNC_LOG10 = 116;
  
  private static final int FUNC_LPAD = 117;
  
  private static final int FUNC_LTRIM = 118;
  
  private static final int FUNC_MONTHS_BETWEEN = 119;
  
  private static final int FUNC_NEW_TIME = 120;
  
  private static final int FUNC_NEXT_DAY = 121;
  
  private static final int FUNC_NUMTODSINTERVAL = 122;
  
  private static final int FUNC_NUMTOYMINTERVAL = 123;
  
  private static final int FUNC_PI = 124;
  
  private static final int FUNC_POSITION_ARRAY = 125;
  
  private static final int FUNC_RADIANS = 126;
  
  private static final int FUNC_RAND = 127;
  
  private static final int FUNC_RAWTOHEX = 128;
  
  private static final int FUNC_REGEXP_MATCHES = 129;
  
  private static final int FUNC_REGEXP_SUBSTRING = 130;
  
  private static final int FUNC_REGEXP_SUBSTRING_ARRAY = 131;
  
  private static final int FUNC_REPEAT = 132;
  
  private static final int FUNC_REPLACE = 133;
  
  private static final int FUNC_REVERSE = 134;
  
  private static final int FUNC_RIGHT = 135;
  
  private static final int FUNC_ROUND = 136;
  
  private static final int FUNC_ROUNDMAGIC = 137;
  
  private static final int FUNC_RPAD = 138;
  
  private static final int FUNC_RTRIM = 139;
  
  private static final int FUNC_SECONDS_MIDNIGHT = 140;
  
  private static final int FUNC_SEQUENCE_ARRAY = 141;
  
  private static final int FUNC_SESSION_ID = 142;
  
  private static final int FUNC_SESSION_ISOLATION_LEVEL = 143;
  
  private static final int FUNC_SESSION_TIMEZONE = 144;
  
  private static final int FUNC_SESSIONTIMEZONE = 145;
  
  private static final int FUNC_SIGN = 146;
  
  private static final int FUNC_SIN = 147;
  
  private static final int FUNC_SOUNDEX = 148;
  
  private static final int FUNC_SORT_ARRAY = 149;
  
  private static final int FUNC_SPACE = 150;
  
  private static final int FUNC_SUBSTR = 151;
  
  private static final int FUNC_SYS_EXTRACT_UTC = 152;
  
  private static final int FUNC_SYSDATE = 153;
  
  private static final int FUNC_SYSTIMESTAMP = 154;
  
  private static final int FUNC_TAN = 155;
  
  private static final int FUNC_TIMESTAMP = 156;
  
  private static final int FUNC_TIMESTAMP_WITH_ZONE = 157;
  
  private static final int FUNC_TIMESTAMPADD = 158;
  
  private static final int FUNC_TIMESTAMPDIFF = 159;
  
  private static final int FUNC_TIMEZONE = 160;
  
  private static final int FUNC_TO_CHAR = 161;
  
  private static final int FUNC_TO_DATE = 162;
  
  private static final int FUNC_TO_DSINTERVAL = 163;
  
  private static final int FUNC_TO_YMINTERVAL = 164;
  
  private static final int FUNC_TO_NUMBER = 165;
  
  private static final int FUNC_TO_TIMESTAMP = 166;
  
  private static final int FUNC_TO_TIMESTAMP_TZ = 167;
  
  private static final int FUNC_TRANSACTION_CONTROL = 168;
  
  private static final int FUNC_TRANSACTION_ID = 169;
  
  private static final int FUNC_TRANSACTION_SIZE = 170;
  
  private static final int FUNC_TRANSLATE = 171;
  
  private static final int FUNC_TRUNC = 172;
  
  private static final int FUNC_TRUNCATE = 173;
  
  private static final int FUNC_UUID = 174;
  
  private static final int FUNC_UNIX_TIMESTAMP = 175;
  
  private static final int FUNC_UNIX_MILLIS = 176;
  
  static final IntKeyIntValueHashMap customRegularFuncMap = new IntKeyIntValueHashMap();
  
  static final IntKeyIntValueHashMap customValueFuncMap = new IntKeyIntValueHashMap();
  
  private int extractSpec;
  
  private Pattern pattern;
  
  private IntKeyIntValueHashMap charLookup;
  
  public static FunctionSQL newCustomFunction(String paramString, int paramInt) {
    int i = customRegularFuncMap.get(paramInt, -1);
    if (i == -1)
      i = customValueFuncMap.get(paramInt, -1); 
    if (i == -1)
      return null; 
    switch (paramInt) {
      case 431:
      case 651:
      case 698:
      case 703:
      case 719:
      case 779:
        return new FunctionSQL(i);
      case 661:
      case 662:
      case 716:
      case 772:
        functionSQL = new FunctionSQL(i);
        functionSQL.parseList = optionalNoParamList;
        return functionSQL;
      case 756:
        functionSQL = new FunctionSQL(i);
        functionSQL.parseList = tripleParamList;
        return functionSQL;
    } 
    FunctionSQL functionSQL = new FunctionCustom(i);
    if (i == 31)
      switch (paramInt) {
        case 706:
          ((FunctionCustom)functionSQL).extractSpec = 151;
          break;
        case 733:
          ((FunctionCustom)functionSQL).extractSpec = 286;
          break;
      }  
    if (i == 5)
      switch (paramInt) {
        case 675:
          ((FunctionCustom)functionSQL).extractSpec = 671;
          break;
        case 709:
          ((FunctionCustom)functionSQL).extractSpec = 708;
          break;
        case 676:
          ((FunctionCustom)functionSQL).extractSpec = 672;
          break;
        case 677:
          ((FunctionCustom)functionSQL).extractSpec = 673;
          break;
        case 678:
          ((FunctionCustom)functionSQL).extractSpec = 674;
          break;
        case 790:
          ((FunctionCustom)functionSQL).extractSpec = 791;
          break;
        default:
          ((FunctionCustom)functionSQL).extractSpec = paramInt;
          break;
      }  
    if (((FunctionCustom)functionSQL).name == null)
      ((FunctionCustom)functionSQL).name = paramString; 
    return functionSQL;
  }
  
  public static boolean isRegularFunction(int paramInt) {
    return (customRegularFuncMap.get(paramInt, -1) != -1);
  }
  
  public static boolean isValueFunction(int paramInt) {
    return (customValueFuncMap.get(paramInt, -1) != -1);
  }
  
  private FunctionCustom(int paramInt) {
    this.funcType = paramInt;
    this.isDeterministic = !nonDeterministicFuncSet.contains(paramInt);
    switch (paramInt) {
      case 153:
      case 154:
        this.parseList = optionalNoParamList;
        return;
      case 72:
      case 88:
      case 89:
      case 90:
      case 91:
      case 92:
      case 98:
      case 106:
      case 107:
      case 108:
      case 109:
      case 110:
      case 124:
      case 142:
      case 143:
      case 144:
      case 145:
      case 160:
      case 168:
      case 169:
      case 170:
        this.parseList = emptyParamList;
        return;
      case 71:
      case 74:
      case 75:
      case 76:
      case 80:
      case 83:
      case 85:
      case 86:
      case 97:
      case 99:
      case 103:
      case 111:
      case 114:
      case 116:
      case 126:
      case 128:
      case 134:
      case 137:
      case 146:
      case 147:
      case 148:
      case 150:
      case 152:
      case 155:
      case 157:
      case 163:
      case 164:
      case 165:
        this.parseList = singleParamList;
        return;
      case 73:
      case 77:
      case 78:
      case 79:
      case 81:
      case 82:
      case 84:
      case 87:
      case 101:
      case 102:
      case 112:
      case 119:
      case 121:
      case 122:
      case 123:
      case 129:
      case 130:
      case 131:
      case 132:
      case 135:
      case 161:
        this.parseList = doubleParamList;
        return;
      case 113:
      case 136:
      case 156:
      case 162:
      case 166:
      case 167:
      case 172:
      case 173:
        this.parseList = optionalDoubleParamList;
        return;
      case 96:
        this.parseList = new short[] { 816, 818, 804, 818, 864, 2, 804, 818, 802 };
        return;
      case 93:
      case 94:
        this.parseList = doubleParamList;
        return;
      case 95:
      case 120:
      case 141:
      case 171:
        this.parseList = tripleParamList;
        return;
      case 1:
      case 117:
      case 133:
      case 138:
        this.parseList = new short[] { 816, 818, 804, 818, 864, 2, 804, 818, 802 };
        return;
      case 174:
      case 175:
      case 176:
        this.parseList = optionalSingleParamList;
        return;
      case 5:
        this.name = "EXTRACT";
        this.parseList = singleParamList;
        return;
      case 31:
        this.name = "TRIM";
        this.parseList = singleParamList;
        return;
      case 32:
        this.name = "OVERLAY";
        this.parseList = quadParamList;
        return;
      case 104:
        this.name = "IDENTITY";
        this.parseList = emptyParamList;
        return;
      case 100:
        this.parseList = new short[] { 816, 495, 802 };
        return;
      case 125:
        this.parseList = new short[] { 816, 818, 130, 818, 864, 2, 115, 818, 802 };
        return;
      case 149:
        this.parseList = new short[] { 
            816, 818, 864, 4, 863, 2, 338, 389, 864, 5, 
            451, 863, 2, 401, 430, 802 };
        return;
      case 158:
        this.name = "TIMESTAMPADD";
        this.parseList = new short[] { 
            816, 863, 10, 852, 853, 854, 855, 856, 857, 858, 
            859, 860, 861, 804, 818, 804, 818, 802 };
        return;
      case 159:
        this.name = "TIMESTAMPDIFF";
        this.parseList = new short[] { 
            816, 863, 10, 852, 853, 854, 855, 856, 857, 858, 
            859, 860, 861, 804, 818, 804, 818, 802 };
        return;
      case 127:
        this.parseList = optionalSingleParamList;
        return;
    } 
    throw Error.runtimeError(201, "FunctionCustom");
  }
  
  public void setArguments(Expression[] paramArrayOfExpression) {
    Expression[] arrayOfExpression2;
    Expression expression1;
    Expression[] arrayOfExpression1;
    Expression expression2;
    switch (this.funcType) {
      case 1:
        arrayOfExpression2 = new Expression[4];
        if ("LOCATE".equals(this.name)) {
          arrayOfExpression2[0] = paramArrayOfExpression[0];
          arrayOfExpression2[1] = paramArrayOfExpression[1];
          arrayOfExpression2[3] = paramArrayOfExpression[2];
          paramArrayOfExpression = arrayOfExpression2;
          break;
        } 
        if ("INSTR".equals(this.name)) {
          arrayOfExpression2[0] = paramArrayOfExpression[1];
          arrayOfExpression2[1] = paramArrayOfExpression[0];
          arrayOfExpression2[3] = paramArrayOfExpression[2];
          paramArrayOfExpression = arrayOfExpression2;
        } 
        break;
      case 32:
        expression1 = paramArrayOfExpression[1];
        expression2 = paramArrayOfExpression[2];
        paramArrayOfExpression[1] = paramArrayOfExpression[3];
        paramArrayOfExpression[2] = expression1;
        paramArrayOfExpression[3] = expression2;
        break;
      case 5:
        arrayOfExpression1 = new Expression[2];
        arrayOfExpression1[0] = new ExpressionValue(ValuePool.getInt(this.extractSpec), (Type)Type.SQL_INTEGER);
        arrayOfExpression1[1] = paramArrayOfExpression[0];
        paramArrayOfExpression = arrayOfExpression1;
        break;
      case 31:
        arrayOfExpression1 = new Expression[3];
        arrayOfExpression1[0] = new ExpressionValue(ValuePool.getInt(this.extractSpec), (Type)Type.SQL_INTEGER);
        arrayOfExpression1[1] = new ExpressionValue(" ", (Type)Type.SQL_CHAR);
        arrayOfExpression1[2] = paramArrayOfExpression[0];
        paramArrayOfExpression = arrayOfExpression1;
        break;
    } 
    super.setArguments(paramArrayOfExpression);
  }
  
  public Expression getFunctionExpression() {
    switch (this.funcType) {
      case 84:
        return new ExpressionArithmetic(36, this.nodes[0], this.nodes[1]);
    } 
    return super.getFunctionExpression();
  }
  
  Object getValue(Session paramSession, Object[] paramArrayOfObject) {
    int i;
    LobData lobData;
    Number number;
    byte b1;
    HsqlArrayList hsqlArrayList1;
    int n;
    IntervalSecondData intervalSecondData1;
    int m;
    SimpleDateFormat simpleDateFormat;
    boolean bool1;
    Calendar calendar;
    long l1;
    TimestampData timestampData1;
    double d2;
    int k;
    double d1;
    String str1;
    int j;
    Object object2;
    long l3;
    TimestampData timestampData4;
    Date date;
    TimestampData timestampData3;
    long l2;
    int i2;
    String str5;
    byte b6;
    byte[] arrayOfByte2;
    byte b5;
    char[] arrayOfChar1;
    BlobData blobData;
    byte b4;
    String str4;
    byte b3;
    String str3;
    int i1;
    StringBuffer stringBuffer1;
    byte b2;
    Pattern pattern;
    byte[] arrayOfByte1;
    String str2;
    Object[] arrayOfObject;
    ArrayType arrayType1;
    TimestampData timestampData2;
    IntervalSecondData intervalSecondData2;
    Object object1;
    Type type1;
    TimestampData timestampData5;
    TimeData timeData;
    double d3;
    byte[] arrayOfByte4;
    char[] arrayOfChar3;
    byte[] arrayOfByte3;
    int i6;
    String str6;
    char[] arrayOfChar2;
    Matcher matcher;
    int i5;
    ArrayType arrayType2;
    SortAndSlice sortAndSlice;
    int i4;
    IntervalSecondData intervalSecondData3;
    int i3;
    boolean bool3;
    TimestampData timestampData6;
    IntervalType intervalType1;
    boolean bool2;
    byte[] arrayOfByte5;
    byte b7;
    StringBuffer stringBuffer2;
    String str8;
    boolean bool;
    HsqlArrayList hsqlArrayList2;
    String str7;
    Type type2;
    Object object3;
    int i7;
    IntervalType intervalType2;
    IntervalSecondData intervalSecondData4;
    int i9;
    StringBuffer stringBuffer3;
    int i8;
    double d4;
    IntervalSecondData intervalSecondData5;
    IntervalMonthData intervalMonthData;
    int i10;
    long l4;
    int i11;
    switch (this.funcType) {
      case 1:
      case 5:
      case 31:
      case 32:
        return super.getValue(paramSession, paramArrayOfObject);
      case 88:
        return paramSession.getDatabase().getPath();
      case 90:
        return paramSession.getDatabase().getUniqueName();
      case 106:
        return paramSession.isAutoCommit() ? Boolean.TRUE : Boolean.FALSE;
      case 110:
        return paramSession.isReadOnlyDefault() ? Boolean.TRUE : Boolean.FALSE;
      case 108:
        return (paramSession.getDatabase()).databaseReadOnly ? Boolean.TRUE : Boolean.FALSE;
      case 109:
        return paramSession.getDatabase().isFilesReadOnly() ? Boolean.TRUE : Boolean.FALSE;
      case 107:
        return Session.getIsolationString(paramSession.isolationLevel);
      case 143:
        return Session.getIsolationString(paramSession.isolationLevelDefault);
      case 89:
        return Session.getIsolationString(paramSession.database.defaultIsolationLevel);
      case 168:
        switch (paramSession.database.txManager.getTransactionControl()) {
          case 2:
            return "MVCC";
          case 1:
            return "MVLOCKS";
        } 
        return "LOCKS";
      case 160:
        return new IntervalSecondData(paramSession.getZoneSeconds(), 0);
      case 144:
        return new IntervalSecondData(paramSession.sessionTimeZoneSeconds, 0);
      case 91:
        i = HsqlDateTime.getZoneSeconds(HsqlDateTime.tempCalDefault);
        return new IntervalSecondData(i, 0);
      case 92:
        return "2.3.0";
      case 142:
        return Long.valueOf(paramSession.getId());
      case 72:
        return Long.valueOf(paramSession.actionTimestamp);
      case 169:
        return Long.valueOf(paramSession.transactionTimestamp);
      case 170:
        return Long.valueOf(paramSession.actionIndex);
      case 114:
        lobData = (LobData)paramArrayOfObject[0];
        return (lobData == null) ? null : Long.valueOf(lobData.getId());
      case 104:
        number = paramSession.getLastIdentity();
        return (number instanceof Long) ? number : ValuePool.getLong(number.longValue());
      case 100:
        return paramSession.sessionContext.diagnosticsVariables[this.exprSubType];
      case 141:
        for (b1 = 0; b1 < paramArrayOfObject.length; b1++) {
          if (paramArrayOfObject[b1] == null)
            return null; 
        } 
        hsqlArrayList1 = new HsqlArrayList();
        object2 = paramArrayOfObject[0];
        type1 = this.nodes[0].getDataType();
        bool3 = (type1.compare(paramSession, paramArrayOfObject[1], paramArrayOfObject[0]) >= 0) ? true : false;
        while (true) {
          i9 = type1.compare(paramSession, object2, paramArrayOfObject[1]);
          if (bool3 ? (i9 > 0) : (i9 < 0))
            break; 
          hsqlArrayList1.add(object2);
          Object object = type1.add(paramSession, object2, paramArrayOfObject[2], this.nodes[2].getDataType());
          i9 = type1.compare(paramSession, object2, object);
          if (bool3 ? (i9 >= 0) : (i9 <= 0))
            break; 
          object2 = object;
        } 
        return hsqlArrayList1.toArray();
      case 158:
        if (paramArrayOfObject[1] == null || paramArrayOfObject[2] == null)
          return null; 
        paramArrayOfObject[1] = Type.SQL_BIGINT.convertToType(paramSession, paramArrayOfObject[1], this.nodes[1].getDataType());
        n = ((Number)(this.nodes[0]).valueData).intValue();
        l3 = ((Number)paramArrayOfObject[1]).longValue();
        timestampData6 = (TimestampData)paramArrayOfObject[2];
        switch (n) {
          case 852:
            l4 = l3 / 1000000000L;
            i11 = (int)(l3 % 1000000000L);
            intervalType2 = Type.SQL_INTERVAL_SECOND_MAX_FRACTION;
            intervalSecondData5 = new IntervalSecondData(l4, i11, intervalType2);
            return this.dataType.add(paramSession, timestampData6, intervalSecondData5, (Type)intervalType2);
          case 853:
            l4 = l3 / 1000L;
            i11 = (int)(l3 % 1000L) * 1000000;
            intervalType2 = Type.SQL_INTERVAL_SECOND_MAX_FRACTION;
            intervalSecondData5 = new IntervalSecondData(l4, i11, intervalType2);
            return this.dataType.add(paramSession, timestampData6, intervalSecondData5, (Type)intervalType2);
          case 854:
            intervalType2 = Type.SQL_INTERVAL_SECOND_MAX_PRECISION;
            intervalSecondData5 = IntervalSecondData.newIntervalSeconds(l3, intervalType2);
            return this.dataType.add(paramSession, timestampData6, intervalSecondData5, (Type)intervalType2);
          case 855:
            intervalType2 = Type.SQL_INTERVAL_MINUTE_MAX_PRECISION;
            intervalSecondData5 = IntervalSecondData.newIntervalMinute(l3, intervalType2);
            return this.dataType.add(paramSession, timestampData6, intervalSecondData5, (Type)intervalType2);
          case 856:
            intervalType2 = Type.SQL_INTERVAL_HOUR_MAX_PRECISION;
            intervalSecondData5 = IntervalSecondData.newIntervalHour(l3, intervalType2);
            return this.dataType.add(paramSession, timestampData6, intervalSecondData5, (Type)intervalType2);
          case 857:
            intervalType2 = Type.SQL_INTERVAL_DAY_MAX_PRECISION;
            intervalSecondData5 = IntervalSecondData.newIntervalDay(l3, intervalType2);
            return this.dataType.add(paramSession, timestampData6, intervalSecondData5, (Type)intervalType2);
          case 858:
            intervalType2 = Type.SQL_INTERVAL_DAY_MAX_PRECISION;
            intervalSecondData5 = IntervalSecondData.newIntervalDay(l3 * 7L, intervalType2);
            return this.dataType.add(paramSession, timestampData6, intervalSecondData5, (Type)intervalType2);
          case 859:
            intervalType2 = Type.SQL_INTERVAL_MONTH_MAX_PRECISION;
            intervalMonthData = IntervalMonthData.newIntervalMonth(l3, intervalType2);
            return this.dataType.add(paramSession, timestampData6, intervalMonthData, (Type)intervalType2);
          case 860:
            intervalType2 = Type.SQL_INTERVAL_MONTH_MAX_PRECISION;
            intervalMonthData = IntervalMonthData.newIntervalMonth(l3 * 3L, intervalType2);
            return this.dataType.add(paramSession, timestampData6, intervalMonthData, (Type)intervalType2);
          case 861:
            intervalType2 = Type.SQL_INTERVAL_YEAR_MAX_PRECISION;
            intervalMonthData = IntervalMonthData.newIntervalMonth(l3 * 12L, intervalType2);
            return this.dataType.add(paramSession, timestampData6, intervalMonthData, (Type)intervalType2);
        } 
        throw Error.runtimeError(201, "FunctionCustom");
      case 159:
        if (paramArrayOfObject[1] == null || paramArrayOfObject[2] == null)
          return null; 
        n = ((Number)(this.nodes[0]).valueData).intValue();
        timestampData4 = (TimestampData)paramArrayOfObject[2];
        timestampData5 = (TimestampData)paramArrayOfObject[1];
        if ((this.nodes[2]).dataType.isDateTimeTypeWithZone())
          timestampData4 = (TimestampData)Type.SQL_TIMESTAMP.convertToType(paramSession, timestampData4, (Type)Type.SQL_TIMESTAMP_WITH_TIME_ZONE); 
        if ((this.nodes[1]).dataType.isDateTimeTypeWithZone())
          timestampData5 = (TimestampData)Type.SQL_TIMESTAMP.convertToType(paramSession, timestampData5, (Type)Type.SQL_TIMESTAMP_WITH_TIME_ZONE); 
        switch (n) {
          case 852:
            intervalType1 = Type.SQL_INTERVAL_SECOND_MAX_PRECISION;
            intervalSecondData4 = (IntervalSecondData)intervalType1.subtract(paramSession, timestampData4, timestampData5, null);
            return new Long(1000000000L * intervalSecondData4.getSeconds() + intervalSecondData4.getNanos());
          case 853:
            intervalType1 = Type.SQL_INTERVAL_SECOND_MAX_PRECISION;
            intervalSecondData4 = (IntervalSecondData)intervalType1.subtract(paramSession, timestampData4, timestampData5, null);
            return new Long(1000L * intervalSecondData4.getSeconds() + (intervalSecondData4.getNanos() / 1000000));
          case 854:
            intervalType1 = Type.SQL_INTERVAL_SECOND_MAX_PRECISION;
            return new Long(intervalType1.convertToLongEndUnits(intervalType1.subtract(paramSession, timestampData4, timestampData5, null)));
          case 855:
            intervalType1 = Type.SQL_INTERVAL_MINUTE_MAX_PRECISION;
            return new Long(intervalType1.convertToLongEndUnits(intervalType1.subtract(paramSession, timestampData4, timestampData5, null)));
          case 856:
            intervalType1 = Type.SQL_INTERVAL_HOUR_MAX_PRECISION;
            return new Long(intervalType1.convertToLongEndUnits(intervalType1.subtract(paramSession, timestampData4, timestampData5, null)));
          case 857:
            intervalType1 = Type.SQL_INTERVAL_DAY_MAX_PRECISION;
            return new Long(intervalType1.convertToLongEndUnits(intervalType1.subtract(paramSession, timestampData4, timestampData5, null)));
          case 858:
            intervalType1 = Type.SQL_INTERVAL_DAY_MAX_PRECISION;
            return new Long(intervalType1.convertToLongEndUnits(intervalType1.subtract(paramSession, timestampData4, timestampData5, null)) / 7L);
          case 859:
            intervalType1 = Type.SQL_INTERVAL_MONTH_MAX_PRECISION;
            return new Long(intervalType1.convertToLongEndUnits(intervalType1.subtract(paramSession, timestampData4, timestampData5, null)));
          case 860:
            intervalType1 = Type.SQL_INTERVAL_MONTH_MAX_PRECISION;
            return new Long(intervalType1.convertToLongEndUnits(intervalType1.subtract(paramSession, timestampData4, timestampData5, null)) / 3L);
          case 861:
            intervalType1 = Type.SQL_INTERVAL_YEAR_MAX_PRECISION;
            return new Long(intervalType1.convertToLongEndUnits(intervalType1.subtract(paramSession, timestampData4, timestampData5, null)));
        } 
        throw Error.runtimeError(201, "FunctionCustom");
      case 93:
        return (paramArrayOfObject[0] == null || paramArrayOfObject[1] == null) ? null : this.dataType.add(paramSession, paramArrayOfObject[0], paramArrayOfObject[1], (this.nodes[1]).dataType);
      case 94:
        return (paramArrayOfObject[0] == null || paramArrayOfObject[1] == null) ? null : this.dataType.subtract(paramSession, paramArrayOfObject[0], paramArrayOfObject[1], (this.nodes[1]).dataType);
      case 97:
        if (paramArrayOfObject[0] == null)
          return null; 
        intervalSecondData1 = (IntervalSecondData)Type.SQL_INTERVAL_DAY_MAX_PRECISION.subtract(paramSession, paramArrayOfObject[0], DateTimeType.epochTimestamp, (Type)Type.SQL_DATE);
        return ValuePool.getInt((int)(intervalSecondData1.getSeconds() / 86400L + 1L));
      case 136:
      case 172:
        m = 103;
        if (paramArrayOfObject[0] == null)
          return null; 
        if (this.dataType.isDateTimeType()) {
          DateTimeType dateTimeType = (DateTimeType)this.dataType;
          if (this.nodes.length > 1 && this.nodes[1] != null) {
            if (paramArrayOfObject[1] == null)
              return null; 
            m = HsqlDateTime.toStandardIntervalPart((String)paramArrayOfObject[1]);
          } 
          if (m < 0)
            throw Error.error(5566, (String)paramArrayOfObject[1]); 
          return (this.funcType == 136) ? dateTimeType.round(paramArrayOfObject[0], m) : dateTimeType.truncate(paramArrayOfObject[0], m);
        } 
      case 173:
        m = 0;
        if (paramArrayOfObject[0] == null)
          return null; 
        if (this.nodes.length > 1) {
          if (paramArrayOfObject[1] == null)
            return null; 
          paramArrayOfObject[1] = Type.SQL_INTEGER.convertToType(paramSession, paramArrayOfObject[1], this.nodes[1].getDataType());
          m = ((Number)paramArrayOfObject[1]).intValue();
        } 
        return (this.funcType == 136) ? ((NumberType)this.dataType).round(paramArrayOfObject[0], m) : ((NumberType)this.dataType).truncate(paramArrayOfObject[0], m);
      case 161:
        if (paramArrayOfObject[0] == null || paramArrayOfObject[1] == null)
          return null; 
        simpleDateFormat = paramSession.getSimpleDateFormatGMT();
        date = (Date)((DateTimeType)(this.nodes[0]).dataType).convertSQLToJavaGMT(paramSession, paramArrayOfObject[0]);
        return HsqlDateTime.toFormattedDate(date, (String)paramArrayOfObject[1], simpleDateFormat);
      case 165:
        return (paramArrayOfObject[0] == null) ? null : this.dataType.convertToType(paramSession, paramArrayOfObject[0], (this.nodes[0]).dataType);
      case 162:
      case 166:
        if (paramArrayOfObject[0] == null || paramArrayOfObject[1] == null)
          return null; 
        simpleDateFormat = paramSession.getSimpleDateFormatGMT();
        timestampData3 = HsqlDateTime.toDate((String)paramArrayOfObject[0], (String)paramArrayOfObject[1], simpleDateFormat);
        if (this.funcType == 162)
          timestampData3.clearNanos(); 
        return timestampData3;
      case 156:
        bool1 = (this.nodes[1] == null) ? true : false;
        if (paramArrayOfObject[0] == null)
          return null; 
        if (bool1) {
          if ((this.nodes[0]).dataType.isNumberType())
            return new TimestampData(((Number)paramArrayOfObject[0]).longValue()); 
          try {
            return Type.SQL_TIMESTAMP.convertToType(paramSession, paramArrayOfObject[0], (this.nodes[0]).dataType);
          } catch (HsqlException hsqlException) {
            return Type.SQL_DATE.convertToType(paramSession, paramArrayOfObject[0], (this.nodes[0]).dataType);
          } 
        } 
        if (paramArrayOfObject[1] == null)
          return null; 
        timestampData3 = (TimestampData)Type.SQL_DATE.convertToType(paramSession, paramArrayOfObject[0], (this.nodes[0]).dataType);
        timeData = (TimeData)Type.SQL_TIME.convertToType(paramSession, paramArrayOfObject[1], (this.nodes[1]).dataType);
        return new TimestampData(timestampData3.getSeconds() + timeData.getSeconds(), timeData.getNanos());
      case 157:
        calendar = paramSession.getCalendar();
        bool2 = false;
        if (paramArrayOfObject[0] == null)
          return null; 
        if ((this.nodes[0]).dataType.isNumberType()) {
          l2 = ((Number)paramArrayOfObject[0]).longValue();
        } else if ((this.nodes[0]).dataType.typeCode == 93) {
          l2 = ((TimestampData)paramArrayOfObject[0]).getSeconds();
          l2 = HsqlDateTime.convertMillisToCalendar(calendar, l2 * 1000L) / 1000L;
        } else if ((this.nodes[0]).dataType.typeCode == 95) {
          l2 = ((TimestampData)paramArrayOfObject[0]).getSeconds();
        } else {
          throw Error.error(5566, (String)paramArrayOfObject[1]);
        } 
        synchronized (calendar) {
          calendar.setTimeInMillis(l2 * 1000L);
          i9 = HsqlDateTime.getZoneSeconds(calendar);
        } 
        return new TimestampData(l2, bool2, i9);
      case 124:
        return new Double(Math.PI);
      case 127:
        if (this.nodes[0] == null)
          return new Double(paramSession.random()); 
        paramArrayOfObject[0] = Type.SQL_BIGINT.convertToType(paramSession, paramArrayOfObject[0], this.nodes[0].getDataType());
        l1 = ((Number)paramArrayOfObject[0]).longValue();
        return new Double(paramSession.random(l1));
      case 174:
        if (this.nodes[0] == null) {
          UUID uUID = UUID.randomUUID();
          l2 = uUID.getMostSignificantBits();
          long l = uUID.getLeastSignificantBits();
          return new BinaryData(ArrayUtil.toByteArray(l2, l), false);
        } 
        return (paramArrayOfObject[0] == null) ? null : (this.dataType.isBinaryType() ? new BinaryData(StringConverter.toBinaryUUID((String)paramArrayOfObject[0]), false) : StringConverter.toStringUUID(((BinaryData)paramArrayOfObject[0]).getBytes()));
      case 176:
        if (this.nodes[0] == null) {
          timestampData1 = paramSession.getCurrentTimestamp(true);
        } else {
          if (paramArrayOfObject[0] == null)
            return null; 
          timestampData1 = (TimestampData)paramArrayOfObject[0];
        } 
        l2 = timestampData1.getSeconds() * 1000L + (timestampData1.getNanos() / 1000000);
        return Long.valueOf(l2);
      case 175:
        if (this.nodes[0] == null) {
          timestampData1 = paramSession.getCurrentTimestamp(true);
        } else {
          if (paramArrayOfObject[0] == null)
            return null; 
          timestampData1 = (TimestampData)paramArrayOfObject[0];
        } 
        return Long.valueOf(timestampData1.getSeconds());
      case 71:
        if (paramArrayOfObject[0] == null)
          return null; 
        d2 = NumberType.toDouble(paramArrayOfObject[0]);
        return new Double(Math.acos(d2));
      case 75:
        if (paramArrayOfObject[0] == null)
          return null; 
        d2 = NumberType.toDouble(paramArrayOfObject[0]);
        return new Double(Math.asin(d2));
      case 76:
        if (paramArrayOfObject[0] == null)
          return null; 
        d2 = NumberType.toDouble(paramArrayOfObject[0]);
        return new Double(Math.atan(d2));
      case 85:
        if (paramArrayOfObject[0] == null)
          return null; 
        d2 = NumberType.toDouble(paramArrayOfObject[0]);
        return new Double(Math.cos(d2));
      case 86:
        if (paramArrayOfObject[0] == null)
          return null; 
        d2 = NumberType.toDouble(paramArrayOfObject[0]);
        d3 = 1.0D / Math.tan(d2);
        return new Double(d3);
      case 99:
        if (paramArrayOfObject[0] == null)
          return null; 
        d2 = NumberType.toDouble(paramArrayOfObject[0]);
        return new Double(Math.toDegrees(d2));
      case 147:
        if (paramArrayOfObject[0] == null)
          return null; 
        d2 = NumberType.toDouble(paramArrayOfObject[0]);
        return new Double(Math.sin(d2));
      case 155:
        if (paramArrayOfObject[0] == null)
          return null; 
        d2 = NumberType.toDouble(paramArrayOfObject[0]);
        return new Double(Math.tan(d2));
      case 116:
        if (paramArrayOfObject[0] == null)
          return null; 
        d2 = NumberType.toDouble(paramArrayOfObject[0]);
        return new Double(Math.log10(d2));
      case 126:
        if (paramArrayOfObject[0] == null)
          return null; 
        d2 = NumberType.toDouble(paramArrayOfObject[0]);
        return new Double(Math.toRadians(d2));
      case 146:
        if (paramArrayOfObject[0] == null)
          return null; 
        k = ((NumberType)(this.nodes[0]).dataType).compareToZero(paramArrayOfObject[0]);
        return ValuePool.getInt(k);
      case 77:
        if (paramArrayOfObject[0] == null)
          return null; 
        d1 = NumberType.toDouble(paramArrayOfObject[0]);
        d3 = NumberType.toDouble(paramArrayOfObject[1]);
        return new Double(Math.atan2(d1, d3));
      case 74:
        if (paramArrayOfObject[0] == null)
          return null; 
        if ((this.nodes[0]).dataType.isLobType()) {
          str1 = ((ClobData)paramArrayOfObject[0]).getSubString(paramSession, 0L, 1);
        } else {
          str1 = (String)paramArrayOfObject[0];
        } 
        return (str1.length() == 0) ? null : ValuePool.getInt(str1.charAt(0));
      case 83:
        if (paramArrayOfObject[0] == null)
          return null; 
        paramArrayOfObject[0] = Type.SQL_INTEGER.convertToType(paramSession, paramArrayOfObject[0], this.nodes[0].getDataType());
        j = ((Number)paramArrayOfObject[0]).intValue();
        if (Character.isValidCodePoint(j) && Character.isValidCodePoint((char)j))
          return String.valueOf((char)j); 
        throw Error.error(3472);
      case 137:
        i2 = 0;
        if (paramArrayOfObject[0] == null)
          return null; 
        if (this.nodes.length > 1) {
          if (paramArrayOfObject[1] == null)
            return null; 
          i2 = ((Number)paramArrayOfObject[1]).intValue();
        } 
        return ((NumberType)this.dataType).round(paramArrayOfObject[0], i2);
      case 148:
        if (paramArrayOfObject[0] == null)
          return null; 
        str5 = (String)paramArrayOfObject[0];
        return new String(soundex(str5), 0, 4);
      case 78:
      case 79:
      case 80:
      case 81:
      case 82:
        for (b6 = 0; b6 < paramArrayOfObject.length; b6++) {
          if (paramArrayOfObject[b6] == null)
            return null; 
        } 
        if (this.dataType.isNumberType()) {
          long l5 = 0L;
          long l7 = 0L;
          paramArrayOfObject[0] = Type.SQL_BIGINT.convertToType(paramSession, paramArrayOfObject[0], this.nodes[0].getDataType());
          long l6 = ((Number)paramArrayOfObject[0]).longValue();
          if (this.funcType != 80) {
            paramArrayOfObject[1] = Type.SQL_BIGINT.convertToType(paramSession, paramArrayOfObject[1], this.nodes[1].getDataType());
            l7 = ((Number)paramArrayOfObject[1]).longValue();
          } 
          switch (this.funcType) {
            case 78:
              l5 = l6 & l7;
              break;
            case 79:
              l5 = l6 & (l7 ^ 0xFFFFFFFFFFFFFFFFL);
              break;
            case 80:
              l5 = l6 ^ 0xFFFFFFFFFFFFFFFFL;
              break;
            case 81:
              l5 = l6 | l7;
              break;
            case 82:
              l5 = l6 ^ l7;
              break;
          } 
          switch (this.dataType.typeCode) {
            case 2:
            case 3:
              return BigDecimal.valueOf(l5);
            case 25:
              return ValuePool.getLong(l5);
            case -6:
            case 4:
            case 5:
              return ValuePool.getInt((int)l5);
          } 
          throw Error.error(5561);
        } 
        arrayOfByte2 = ((BinaryData)paramArrayOfObject[0]).getBytes();
        arrayOfByte4 = null;
        if (this.funcType != 80)
          arrayOfByte4 = ((BinaryData)paramArrayOfObject[1]).getBytes(); 
        switch (this.funcType) {
          case 78:
            arrayOfByte5 = BitMap.and(arrayOfByte2, arrayOfByte4);
            return new BinaryData(arrayOfByte5, this.dataType.precision);
          case 79:
            arrayOfByte4 = BitMap.not(arrayOfByte4);
            arrayOfByte5 = BitMap.and(arrayOfByte2, arrayOfByte4);
            return new BinaryData(arrayOfByte5, this.dataType.precision);
          case 80:
            arrayOfByte5 = BitMap.not(arrayOfByte2);
            return new BinaryData(arrayOfByte5, this.dataType.precision);
          case 81:
            arrayOfByte5 = BitMap.or(arrayOfByte2, arrayOfByte4);
            return new BinaryData(arrayOfByte5, this.dataType.precision);
          case 82:
            arrayOfByte5 = BitMap.xor(arrayOfByte2, arrayOfByte4);
            return new BinaryData(arrayOfByte5, this.dataType.precision);
        } 
        throw Error.error(5561);
      case 101:
        for (b5 = 0; b5 < paramArrayOfObject.length; b5++) {
          if (paramArrayOfObject[b5] == null)
            return null; 
        } 
        arrayOfChar1 = soundex((String)paramArrayOfObject[0]);
        arrayOfChar3 = soundex((String)paramArrayOfObject[1]);
        b7 = 0;
        if (arrayOfChar1[0] == arrayOfChar3[0])
          b7++; 
        i9 = 1;
        for (i10 = 1; i10 < 4; i10++) {
          for (int i12 = i9; i12 < 4; i12++) {
            if (arrayOfChar1[i12] == arrayOfChar3[i10]) {
              b7++;
              i9 = i12 + 1;
              break;
            } 
          } 
        } 
        return ValuePool.getInt(b7);
      case 103:
        return (paramArrayOfObject[0] == null) ? null : this.dataType.convertToType(paramSession, paramArrayOfObject[0], (this.nodes[0]).dataType);
      case 128:
        if (paramArrayOfObject[0] == null)
          return null; 
        blobData = (BlobData)paramArrayOfObject[0];
        arrayOfByte3 = blobData.getBytes(paramSession, 0L, (int)blobData.length(paramSession));
        return StringConverter.byteArrayToHexString(arrayOfByte3);
      case 132:
        for (b4 = 0; b4 < paramArrayOfObject.length; b4++) {
          if (paramArrayOfObject[b4] == null)
            return null; 
        } 
        paramArrayOfObject[1] = Type.SQL_INTEGER.convertToType(paramSession, paramArrayOfObject[1], this.nodes[1].getDataType());
        str4 = (String)paramArrayOfObject[0];
        i6 = ((Number)paramArrayOfObject[1]).intValue();
        stringBuffer2 = new StringBuffer(str4.length() * i6);
        while (i6-- > 0)
          stringBuffer2.append(str4); 
        return stringBuffer2.toString();
      case 133:
        for (b3 = 0; b3 < paramArrayOfObject.length; b3++) {
          if (paramArrayOfObject[b3] == null)
            return null; 
        } 
        str3 = (String)paramArrayOfObject[0];
        str6 = (String)paramArrayOfObject[1];
        str8 = (String)paramArrayOfObject[2];
        stringBuffer3 = new StringBuffer();
        i10 = 0;
        if (str6.length() == 0)
          return str3; 
        while (true) {
          int i12 = str3.indexOf(str6, i10);
          if (i12 == -1) {
            stringBuffer3.append(str3.substring(i10));
          } else {
            stringBuffer3.append(str3.substring(i10, i12));
            stringBuffer3.append(str8);
            i10 = i12 + str6.length();
            continue;
          } 
          return stringBuffer3.toString();
        } 
      case 112:
      case 135:
        for (i1 = 0; i1 < paramArrayOfObject.length; i1++) {
          if (paramArrayOfObject[i1] == null)
            return null; 
        } 
        i1 = ((Number)paramArrayOfObject[1]).intValue();
        return ((CharacterType)this.dataType).substring(paramSession, paramArrayOfObject[0], 0L, i1, true, (this.funcType == 135));
      case 150:
        if (paramArrayOfObject[0] == null)
          return null; 
        paramArrayOfObject[0] = Type.SQL_INTEGER.convertToType(paramSession, paramArrayOfObject[0], this.nodes[0].getDataType());
        i1 = ((Number)paramArrayOfObject[0]).intValue();
        arrayOfChar2 = new char[i1];
        ArrayUtil.fillArray(arrayOfChar2, 0, ' ');
        return String.valueOf(arrayOfChar2);
      case 134:
        if (paramArrayOfObject[0] == null)
          return null; 
        stringBuffer1 = new StringBuffer((String)paramArrayOfObject[0]);
        stringBuffer1 = stringBuffer1.reverse();
        return stringBuffer1.toString();
      case 129:
      case 130:
      case 131:
        for (b2 = 0; b2 < paramArrayOfObject.length; b2++) {
          if (paramArrayOfObject[b2] == null)
            return null; 
        } 
        pattern = this.pattern;
        if (pattern == null) {
          String str = (String)paramArrayOfObject[1];
          pattern = Pattern.compile(str);
        } 
        matcher = pattern.matcher((String)paramArrayOfObject[0]);
        switch (this.funcType) {
          case 129:
            bool = matcher.matches();
            return Boolean.valueOf(bool);
          case 130:
            bool = matcher.find();
            return bool ? matcher.group() : null;
          case 131:
            hsqlArrayList2 = new HsqlArrayList();
            while (matcher.find())
              hsqlArrayList2.add(matcher.group()); 
            return hsqlArrayList2.toArray();
        } 
      case 87:
        arrayOfByte1 = Crypto.getNewKey((String)paramArrayOfObject[0], (String)paramArrayOfObject[1]);
        return StringConverter.byteArrayToHexString(arrayOfByte1);
      case 113:
        str2 = (String)paramArrayOfObject[0];
        if (str2 == null)
          return null; 
        switch (this.dataType.typeCode) {
          case 40:
            return paramSession.sessionData.createClobFromFile(str2, (String)paramArrayOfObject[1]);
        } 
        return paramSession.sessionData.createBlobFromFile(str2);
      case 117:
      case 138:
        if (paramArrayOfObject[0] == null || paramArrayOfObject[1] == null)
          return null; 
        if ((this.nodes[0]).dataType.typeCode == 40) {
          str2 = (String)Type.SQL_VARCHAR.convertToType(paramSession, paramArrayOfObject[0], (this.nodes[0]).dataType);
        } else if ((this.nodes[0]).dataType.isCharacterType()) {
          str2 = (String)paramArrayOfObject[0];
        } else {
          str2 = (this.nodes[0]).dataType.convertToString(paramArrayOfObject[0]);
        } 
        i5 = ((Integer)Type.SQL_INTEGER.convertToType(paramSession, paramArrayOfObject[1], (this.nodes[1]).dataType)).intValue();
        str7 = " ";
        if (this.nodes[2] != null) {
          str7 = (this.nodes[2]).dataType.convertToString(paramArrayOfObject[2]);
          if (str7.length() == 0)
            str7 = " "; 
        } 
        str2 = (String)Type.SQL_VARCHAR.trim(paramSession, str2, ' ', true, true);
        str2 = StringUtil.toPaddedString(str2, i5, str7, (this.funcType == 138));
        return this.dataType.isLobType() ? this.dataType.convertToType(paramSession, str2, (Type)Type.SQL_VARCHAR) : str2;
      case 125:
        if (paramArrayOfObject[1] == null)
          return null; 
        if (paramArrayOfObject[2] == null)
          return null; 
        arrayOfObject = (Object[])paramArrayOfObject[1];
        arrayType2 = (ArrayType)(this.nodes[1]).dataType;
        type2 = arrayType2.collectionBaseType();
        i8 = ((Number)Type.SQL_INTEGER.convertToType(paramSession, paramArrayOfObject[2], (this.nodes[2]).dataType)).intValue();
        if (i8 <= 0)
          throw Error.error(3403); 
        for (i10 = --i8; i10 < arrayOfObject.length; i10++) {
          if (type2.compare(paramSession, paramArrayOfObject[0], arrayOfObject[i10]) == 0)
            return ValuePool.getInt(i10 + 1); 
        } 
        return ValuePool.INTEGER_0;
      case 149:
        if (paramArrayOfObject[0] == null)
          return null; 
        arrayType1 = (ArrayType)this.dataType;
        sortAndSlice = new SortAndSlice();
        sortAndSlice.prepareSingleColumn(1);
        sortAndSlice.sortDescending[0] = (((Number)paramArrayOfObject[1]).intValue() == 389);
        sortAndSlice.sortNullsLast[0] = (((Number)paramArrayOfObject[2]).intValue() == 430);
        object3 = ArrayUtil.duplicateArray(paramArrayOfObject[0]);
        arrayType1.sort(paramSession, object3, sortAndSlice);
        return object3;
      case 73:
        if (paramArrayOfObject[0] == null)
          return null; 
        if (paramArrayOfObject[1] == null)
          return null; 
        timestampData2 = (TimestampData)paramArrayOfObject[0];
        i4 = ((Number)paramArrayOfObject[1]).intValue();
        return Type.SQL_TIMESTAMP_NO_FRACTION.addMonthsSpecial(paramSession, timestampData2, i4);
      case 98:
        timestampData2 = paramSession.getSystemTimestamp(true);
        intervalSecondData3 = new IntervalSecondData(timestampData2.getZone(), 0);
        return Type.SQL_INTERVAL_HOUR_TO_MINUTE.convertToString(intervalSecondData3);
      case 102:
        if (paramArrayOfObject[0] == null || paramArrayOfObject[1] == null)
          return null; 
        timestampData2 = (TimestampData)paramArrayOfObject[0];
        intervalSecondData3 = (IntervalSecondData)Type.SQL_INTERVAL_HOUR_TO_MINUTE.convertToDefaultType(paramSession, paramArrayOfObject[1]);
        return new TimestampData(timestampData2.getSeconds() - intervalSecondData3.getSeconds(), timestampData2.getNanos(), (int)intervalSecondData3.getSeconds());
      case 111:
        return (paramArrayOfObject[0] == null) ? null : Type.SQL_TIMESTAMP_NO_FRACTION.getLastDayOfMonth(paramSession, paramArrayOfObject[0]);
      case 119:
        return (paramArrayOfObject[0] == null || paramArrayOfObject[1] == null) ? null : DateTimeType.subtractMonthsSpecial(paramSession, (TimestampData)paramArrayOfObject[0], (TimestampData)paramArrayOfObject[1]);
      case 120:
        if (paramArrayOfObject[0] == null || paramArrayOfObject[1] == null || paramArrayOfObject[2] == null)
          return null; 
        intervalSecondData2 = (IntervalSecondData)Type.SQL_INTERVAL_HOUR_TO_MINUTE.convertToDefaultType(paramSession, paramArrayOfObject[1]);
        intervalSecondData3 = (IntervalSecondData)Type.SQL_INTERVAL_HOUR_TO_MINUTE.convertToDefaultType(paramSession, paramArrayOfObject[1]);
        object3 = Type.SQL_TIMESTAMP_WITH_TIME_ZONE.changeZone(paramArrayOfObject[0], (Type)Type.SQL_TIMESTAMP, (int)intervalSecondData3.getSeconds(), (int)intervalSecondData2.getSeconds());
        return Type.SQL_TIMESTAMP.convertToType(paramSession, object3, (Type)Type.SQL_TIMESTAMP_WITH_TIME_ZONE);
      case 121:
        if (paramArrayOfObject[0] == null || paramArrayOfObject[1] == null)
          return null; 
      case 122:
        if (paramArrayOfObject[0] == null || paramArrayOfObject[1] == null)
          return null; 
        object1 = Type.SQL_VARCHAR.trim(paramSession, paramArrayOfObject[1], ' ', true, true);
        object1 = Type.SQL_VARCHAR.upper(paramSession, object1);
        object1 = Type.SQL_VARCHAR.convertToDefaultType(paramSession, object1);
        i3 = Tokens.get((String)object1);
        i7 = IntervalType.getFieldNameTypeForToken(i3);
        switch (i7) {
          case 103:
          case 104:
          case 105:
          case 106:
            d4 = ((Number)paramArrayOfObject[0]).doubleValue();
            return IntervalSecondData.newInterval(d4, i7);
        } 
        throw Error.error(5566);
      case 123:
        if (paramArrayOfObject[0] == null || paramArrayOfObject[1] == null)
          return null; 
        object1 = Type.SQL_VARCHAR.trim(paramSession, paramArrayOfObject[1], ' ', true, true);
        object1 = Type.SQL_VARCHAR.upper(paramSession, object1);
        object1 = Type.SQL_VARCHAR.convertToDefaultType(paramSession, object1);
        i3 = Tokens.get((String)object1);
        i7 = IntervalType.getFieldNameTypeForToken(i3);
        switch (i7) {
          case 101:
          case 102:
            d4 = ((Number)paramArrayOfObject[0]).doubleValue();
            return IntervalMonthData.newInterval(d4, i7);
        } 
        throw Error.error(5566);
      case 145:
        object1 = new IntervalSecondData(paramSession.sessionTimeZoneSeconds, 0);
        return Type.SQL_INTERVAL_HOUR_TO_MINUTE.convertToString(object1);
      case 152:
        return (paramArrayOfObject[0] == null) ? null : Type.SQL_TIMESTAMP_WITH_TIME_ZONE.changeZone(paramArrayOfObject[0], (Type)Type.SQL_TIMESTAMP_WITH_TIME_ZONE, 0, 0);
      case 153:
        object1 = paramSession.getSystemTimestamp(false);
        return Type.SQL_TIMESTAMP_NO_FRACTION.convertToType(paramSession, object1, (Type)Type.SQL_TIMESTAMP);
      case 154:
        return paramSession.getSystemTimestamp(true);
      case 163:
        return (paramArrayOfObject[0] == null) ? null : Type.SQL_INTERVAL_DAY_TO_SECOND.convertToType(paramSession, paramArrayOfObject[0], (Type)Type.SQL_VARCHAR);
      case 164:
        return (paramArrayOfObject[0] == null) ? null : Type.SQL_INTERVAL_YEAR_TO_MONTH_MAX_PRECISION.convertToType(paramSession, paramArrayOfObject[0], (Type)Type.SQL_VARCHAR);
      case 167:
        if (paramArrayOfObject[0] == null || paramArrayOfObject[1] == null)
          return null; 
      case 171:
        if (paramArrayOfObject[0] == null || paramArrayOfObject[1] == null || paramArrayOfObject[2] == null)
          return null; 
        object1 = this.charLookup;
        if (object1 == null)
          object1 = getTranslationMap((String)paramArrayOfObject[1], (String)paramArrayOfObject[2]); 
        return translateWithMap((String)paramArrayOfObject[0], (IntKeyIntValueHashMap)object1);
    } 
    throw Error.runtimeError(201, "FunctionCustom");
  }
  
  public void resolveTypes(Session paramSession, Expression paramExpression) {
    Number number;
    Type type;
    boolean bool;
    int i;
    for (i = 0; i < this.nodes.length; i++) {
      if (this.nodes[i] != null)
        this.nodes[i].resolveTypes(paramSession, this); 
    } 
    switch (this.funcType) {
      case 1:
      case 5:
      case 31:
      case 32:
        super.resolveTypes(paramSession, paramExpression);
        return;
      case 88:
        this.dataType = (Type)Type.SQL_VARCHAR_DEFAULT;
        return;
      case 90:
        this.dataType = (Type)Type.SQL_VARCHAR_DEFAULT;
        return;
      case 106:
      case 108:
      case 109:
      case 110:
        this.dataType = (Type)Type.SQL_BOOLEAN;
        return;
      case 89:
      case 92:
      case 107:
      case 143:
      case 168:
        this.dataType = (Type)Type.SQL_VARCHAR_DEFAULT;
        return;
      case 91:
      case 144:
      case 160:
        this.dataType = (Type)Type.SQL_INTERVAL_HOUR_TO_MINUTE;
        return;
      case 72:
      case 104:
      case 114:
      case 142:
      case 169:
      case 170:
        this.dataType = (Type)Type.SQL_BIGINT;
        return;
      case 100:
        this.exprSubType = 2;
        this.dataType = (Type)Type.SQL_INTEGER;
        return;
      case 141:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (this.nodes[1]).dataType; 
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (this.nodes[0]).dataType; 
        if ((this.nodes[0]).dataType == null) {
          (this.nodes[0]).dataType = (Type)Type.SQL_INTEGER;
          (this.nodes[1]).dataType = (Type)Type.SQL_INTEGER;
        } 
        if ((this.nodes[0]).dataType.isNumberType()) {
          if ((this.nodes[2]).dataType == null)
            (this.nodes[2]).dataType = (this.nodes[0]).dataType; 
        } else if ((this.nodes[0]).dataType.isDateTimeType()) {
          if ((this.nodes[2]).dataType == null)
            throw Error.error(5561); 
          if (!(this.nodes[2]).dataType.isIntervalType())
            throw Error.error(5561); 
        } 
        this.dataType = (Type)new ArrayType(this.nodes[0].getDataType(), 2147483647);
        return;
      case 95:
        if ((this.nodes[0]).dataType == null)
          throw Error.error(5575); 
        if (!(this.nodes[0]).dataType.isCharacterType())
          throw Error.error(5561); 
        i = getTSIToken((String)(this.nodes[0]).valueData);
        (this.nodes[0]).valueData = ValuePool.getInt(i);
        (this.nodes[0]).dataType = (Type)Type.SQL_INTEGER;
        this.funcType = 158;
      case 158:
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (Type)Type.SQL_BIGINT; 
        if ((this.nodes[2]).dataType == null)
          (this.nodes[2]).dataType = (Type)Type.SQL_TIMESTAMP; 
        if (!(this.nodes[1]).dataType.isNumberType())
          throw Error.error(5561); 
        if ((this.nodes[2]).dataType.typeCode != 91 && (this.nodes[2]).dataType.typeCode != 93 && (this.nodes[2]).dataType.typeCode != 95)
          throw Error.error(5561); 
        this.dataType = (this.nodes[2]).dataType;
        return;
      case 96:
        if (this.nodes[2] == null) {
          this.nodes[2] = this.nodes[0];
          this.nodes[0] = new ExpressionValue(ValuePool.getInt(857), (Type)Type.SQL_INTEGER);
        } else {
          if (!(this.nodes[0]).dataType.isCharacterType())
            throw Error.error(5563); 
          i = getTSIToken((String)(this.nodes[0]).valueData);
          (this.nodes[0]).valueData = ValuePool.getInt(i);
          (this.nodes[0]).dataType = (Type)Type.SQL_INTEGER;
        } 
        this.funcType = 159;
      case 159:
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (this.nodes[2]).dataType; 
        if ((this.nodes[2]).dataType == null)
          (this.nodes[2]).dataType = (this.nodes[1]).dataType; 
        if ((this.nodes[1]).dataType == null) {
          (this.nodes[1]).dataType = (Type)Type.SQL_TIMESTAMP;
          (this.nodes[2]).dataType = (Type)Type.SQL_TIMESTAMP;
        } 
        switch ((this.nodes[1]).dataType.typeCode) {
          case 91:
            if ((this.nodes[2]).dataType.typeCode == 92 || (this.nodes[2]).dataType.typeCode == 94)
              throw Error.error(5563); 
            switch (((Integer)(this.nodes[0]).valueData).intValue()) {
              case 857:
              case 858:
              case 859:
              case 860:
              case 861:
                break;
            } 
            throw Error.error(5563);
          case 93:
          case 95:
            if ((this.nodes[2]).dataType.typeCode == 92 || (this.nodes[2]).dataType.typeCode == 94)
              throw Error.error(5563); 
            break;
          default:
            throw Error.error(5563);
        } 
        this.dataType = (Type)Type.SQL_BIGINT;
        return;
      case 93:
      case 94:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_DATE; 
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (Type)Type.SQL_INTEGER; 
        if ((this.nodes[0]).dataType.isCharacterType())
          this.nodes[0] = new ExpressionOp(this.nodes[0], (Type)Type.SQL_TIMESTAMP); 
        if ((this.nodes[1]).dataType.isIntegralType())
          this.nodes[1] = new ExpressionOp(this.nodes[1], (Type)Type.SQL_INTERVAL_DAY); 
        this.nodes[0].resolveTypes(paramSession, this);
        this.nodes[1].resolveTypes(paramSession, this);
        this.dataType = (this.nodes[0]).dataType;
        return;
      case 97:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_DATE; 
        switch ((this.nodes[0]).dataType.typeCode) {
          case 91:
          case 93:
          case 95:
            break;
          default:
            throw Error.error(5563);
        } 
        this.dataType = (Type)Type.SQL_INTEGER;
        return;
      case 136:
      case 172:
        i = (this.nodes.length == 1 || this.nodes[1] == null) ? 1 : 0;
        if ((this.nodes[0]).dataType == null)
          if (i != 0) {
            if (paramExpression instanceof ExpressionLogical || paramExpression instanceof ExpressionArithmetic)
              for (byte b = 0; b < paramExpression.nodes.length; b++) {
                if ((paramExpression.nodes[b]).dataType != null) {
                  (this.nodes[0]).dataType = (paramExpression.nodes[b]).dataType;
                  break;
                } 
              }  
            if ((this.nodes[0]).dataType == null)
              (this.nodes[0]).dataType = (Type)Type.SQL_DECIMAL; 
            if ((this.nodes[0]).dataType.isNumberType())
              (this.nodes[0]).dataType = (Type)Type.SQL_DECIMAL; 
          } else {
            if ((this.nodes[1]).dataType == null)
              (this.nodes[1]).dataType = (Type)Type.SQL_INTEGER; 
            if ((this.nodes[1]).dataType.isNumberType()) {
              (this.nodes[0]).dataType = (Type)Type.SQL_DECIMAL;
            } else {
              (this.nodes[0]).dataType = (Type)Type.SQL_TIMESTAMP;
            } 
          }  
        if ((this.nodes[0]).dataType.isDateTimeType()) {
          if (i == 0 && !(this.nodes[1]).dataType.isCharacterType())
            throw Error.error(5566); 
          this.dataType = (this.nodes[0]).dataType;
        } 
        if (!(this.nodes[0]).dataType.isNumberType())
          throw Error.error(5561); 
      case 173:
        number = null;
        if ((this.nodes[0]).dataType == null)
          throw Error.error(5567); 
        if (!(this.nodes[0]).dataType.isNumberType())
          throw Error.error(5563); 
        if (this.nodes[1] == null) {
          this.nodes[1] = new ExpressionValue(ValuePool.INTEGER_0, (Type)Type.SQL_INTEGER);
          number = ValuePool.INTEGER_0;
        } else {
          if ((this.nodes[1]).dataType == null) {
            (this.nodes[1]).dataType = (Type)Type.SQL_INTEGER;
          } else if (!(this.nodes[1]).dataType.isIntegralType()) {
            throw Error.error(5563);
          } 
          if ((this.nodes[1]).opType == 1)
            number = (Number)this.nodes[1].getValue(paramSession); 
        } 
        this.dataType = (this.nodes[0]).dataType;
        if (number != null) {
          int j = number.intValue();
          if (j < 0) {
            j = 0;
          } else if (j > this.dataType.scale) {
            j = this.dataType.scale;
          } 
          if ((this.dataType.typeCode == 3 || this.dataType.typeCode == 2) && j != this.dataType.scale)
            this.dataType = (Type)new NumberType(this.dataType.typeCode, this.dataType.precision - this.dataType.scale + j, j); 
        } 
        return;
      case 161:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_TIMESTAMP; 
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT; 
        if (!(this.nodes[1]).dataType.isCharacterType())
          throw Error.error(5563); 
        if (!(this.nodes[0]).dataType.isDateTimeType())
          throw Error.error(5563); 
        this.dataType = (Type)CharacterType.getCharacterType(12, 64L);
        return;
      case 165:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT; 
        if (!(this.nodes[0]).dataType.isCharacterType())
          throw Error.error(5563); 
        this.dataType = (Type)Type.SQL_DECIMAL_DEFAULT;
        return;
      case 162:
      case 166:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT; 
        if (this.nodes[1] == null) {
          String str = "DD-MON-YYYY HH24:MI:SS";
          if (this.funcType == 166)
            str = "DD-MON-YYYY HH24:MI:SS.FF"; 
          this.nodes[1] = new ExpressionValue(str, (Type)Type.SQL_VARCHAR);
        } 
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT; 
        if (!(this.nodes[0]).dataType.isCharacterType() || !(this.nodes[1]).dataType.isCharacterType())
          throw Error.error(5567); 
        this.dataType = (this.funcType == 162) ? (Type)Type.SQL_TIMESTAMP_NO_FRACTION : (Type)Type.SQL_TIMESTAMP;
        return;
      case 156:
        type = (this.nodes[0]).dataType;
        if (this.nodes[1] == null) {
          if (type == null)
            type = (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT; 
          if (!type.isCharacterType() && type.typeCode != 93 && type.typeCode != 95 && !type.isNumberType())
            throw Error.error(5561); 
        } else {
          if (type == null)
            if ((this.nodes[1]).dataType == null) {
              type = (this.nodes[1]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT;
            } else if ((this.nodes[1]).dataType.isCharacterType()) {
              type = (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT;
            } else {
              type = (this.nodes[0]).dataType = (Type)Type.SQL_DATE;
            }  
          if ((this.nodes[1]).dataType == null)
            if (type.isCharacterType()) {
              (this.nodes[1]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT;
            } else if (type.typeCode == 91) {
              (this.nodes[1]).dataType = (Type)Type.SQL_TIME;
            }  
          if ((type.typeCode != 91 || (this.nodes[1]).dataType.typeCode != 92) && (!type.isCharacterType() || !(this.nodes[1]).dataType.isCharacterType()))
            throw Error.error(5561); 
        } 
        this.dataType = (Type)Type.SQL_TIMESTAMP;
        return;
      case 157:
        type = (this.nodes[0]).dataType;
        if (type == null)
          type = (this.nodes[0]).dataType = (Type)Type.SQL_BIGINT; 
        if (type.typeCode == 93 || type.typeCode == 95 || type.isNumberType()) {
          this.dataType = (Type)Type.SQL_TIMESTAMP_WITH_TIME_ZONE;
          return;
        } 
        throw Error.error(5561);
      case 124:
        this.dataType = (Type)Type.SQL_DOUBLE;
      case 174:
        if (this.nodes[0] == null)
          this.dataType = (Type)Type.SQL_BINARY_16; 
        if ((this.nodes[0]).dataType == null) {
          (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR;
          this.dataType = (Type)Type.SQL_BINARY_16;
        } 
        if ((this.nodes[0]).dataType.isCharacterType())
          this.dataType = (Type)Type.SQL_BINARY_16; 
        if ((this.nodes[0]).dataType.isBinaryType() && !(this.nodes[0]).dataType.isLobType())
          this.dataType = (Type)Type.SQL_CHAR_16; 
        throw Error.error(5563);
      case 175:
      case 176:
        if (this.nodes[0] != null)
          if ((this.nodes[0]).dataType == null) {
            (this.nodes[0]).dataType = (Type)Type.SQL_TIMESTAMP;
          } else if (!(this.nodes[0]).dataType.isDateTimeType() || (this.nodes[0]).dataType.typeCode == 92 || (this.nodes[0]).dataType.typeCode == 94) {
            throw Error.error(5563);
          }  
        this.dataType = (Type)Type.SQL_BIGINT;
      case 127:
        if (this.nodes[0] != null)
          if ((this.nodes[0]).dataType == null) {
            (this.nodes[0]).dataType = (Type)Type.SQL_BIGINT;
          } else if (!(this.nodes[0]).dataType.isExactNumberType()) {
            throw Error.error(5563);
          }  
        this.dataType = (Type)Type.SQL_DOUBLE;
      case 71:
      case 75:
      case 76:
      case 85:
      case 86:
      case 99:
      case 116:
      case 126:
      case 137:
      case 147:
      case 155:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_DOUBLE; 
        if (!(this.nodes[0]).dataType.isNumberType())
          throw Error.error(5561); 
        this.dataType = (Type)Type.SQL_DOUBLE;
      case 146:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_DOUBLE; 
        if (!(this.nodes[0]).dataType.isNumberType())
          throw Error.error(5561); 
        this.dataType = (Type)Type.SQL_INTEGER;
      case 77:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_DOUBLE; 
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (Type)Type.SQL_DOUBLE; 
        if (!(this.nodes[0]).dataType.isNumberType() || !(this.nodes[1]).dataType.isNumberType())
          throw Error.error(5561); 
        this.dataType = (Type)Type.SQL_DOUBLE;
      case 148:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR; 
        if (!(this.nodes[0]).dataType.isCharacterType())
          throw Error.error(5561); 
        this.dataType = (Type)CharacterType.getCharacterType(12, 4L);
      case 78:
      case 79:
      case 80:
      case 81:
      case 82:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (this.nodes[1]).dataType; 
        if (this.funcType == 80) {
          if ((this.nodes[0]).dataType == null)
            (this.nodes[0]).dataType = (Type)Type.SQL_INTEGER; 
          this.dataType = (this.nodes[0]).dataType;
        } else {
          this.dataType = (this.nodes[0]).dataType;
          if ((this.nodes[1]).dataType == null)
            (this.nodes[1]).dataType = (this.nodes[0]).dataType; 
          for (byte b = 0; b < this.nodes.length; b++) {
            if ((this.nodes[b]).dataType == null)
              (this.nodes[b]).dataType = (Type)Type.SQL_INTEGER; 
          } 
          this.dataType = (this.nodes[0]).dataType.getAggregateType((this.nodes[1]).dataType);
        } 
        switch (this.dataType.typeCode) {
          case -6:
          case 2:
          case 3:
          case 4:
          case 5:
          case 8:
          case 25:
          case 14:
          case 15:
            return;
        } 
        throw Error.error(5561);
      case 74:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR; 
        if (!(this.nodes[0]).dataType.isCharacterType())
          throw Error.error(5561); 
        this.dataType = (Type)Type.SQL_INTEGER;
      case 83:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_INTEGER; 
        if (!(this.nodes[0]).dataType.isExactNumberType())
          throw Error.error(5561); 
        this.dataType = (Type)CharacterType.getCharacterType(12, 1L);
      case 101:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR; 
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (Type)Type.SQL_VARCHAR; 
        this.dataType = (Type)Type.SQL_INTEGER;
      case 103:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR; 
        if (!(this.nodes[0]).dataType.isCharacterType())
          throw Error.error(5561); 
        this.dataType = ((this.nodes[0]).dataType.precision == 0L) ? (Type)Type.SQL_VARBINARY_DEFAULT : (Type)BinaryType.getBinaryType(61, (this.nodes[0]).dataType.precision / 2L);
      case 128:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_VARBINARY; 
        if (!(this.nodes[0]).dataType.isBinaryType())
          throw Error.error(5561); 
        this.dataType = ((this.nodes[0]).dataType.precision == 0L) ? (Type)Type.SQL_VARCHAR_DEFAULT : (Type)CharacterType.getCharacterType(12, (this.nodes[0]).dataType.precision * 2L);
      case 132:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR; 
        bool = (this.nodes[0]).dataType.isCharacterType();
        if (!bool && !(this.nodes[0]).dataType.isBinaryType())
          throw Error.error(5561); 
        if (!(this.nodes[1]).dataType.isExactNumberType())
          throw Error.error(5561); 
        this.dataType = bool ? (Type)Type.SQL_VARCHAR_DEFAULT : (Type)Type.SQL_VARBINARY_DEFAULT;
      case 133:
        if (this.nodes[2] == null)
          this.nodes[2] = new ExpressionValue("", (Type)Type.SQL_VARCHAR); 
        for (bool = false; bool < this.nodes.length; bool++) {
          if ((this.nodes[bool]).dataType == null) {
            (this.nodes[bool]).dataType = (Type)Type.SQL_VARCHAR;
          } else if (!(this.nodes[bool]).dataType.isCharacterType()) {
            throw Error.error(5561);
          } 
        } 
        this.dataType = (Type)Type.SQL_VARCHAR_DEFAULT;
      case 112:
      case 135:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR; 
        if (!(this.nodes[0]).dataType.isCharacterType())
          throw Error.error(5561); 
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (Type)Type.SQL_INTEGER; 
        if (!(this.nodes[1]).dataType.isExactNumberType())
          throw Error.error(5561); 
        this.dataType = ((this.nodes[0]).dataType.precision == 0L) ? (Type)Type.SQL_VARCHAR_DEFAULT : ((CharacterType)(this.nodes[0]).dataType).getCharacterType((this.nodes[0]).dataType.precision);
      case 150:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_INTEGER; 
        if (!(this.nodes[0]).dataType.isIntegralType())
          throw Error.error(5561); 
        this.dataType = (Type)Type.SQL_VARCHAR_DEFAULT;
      case 134:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT; 
        this.dataType = (this.nodes[0]).dataType;
        if (!this.dataType.isCharacterType() || this.dataType.isLobType())
          throw Error.error(5561); 
      case 129:
      case 130:
      case 131:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT; 
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT; 
        if (!(this.nodes[0]).dataType.isCharacterType() || !(this.nodes[1]).dataType.isCharacterType() || (this.nodes[1]).dataType.isLobType())
          throw Error.error(5561); 
        if ((this.nodes[1]).exprSubType == 1) {
          String str = (String)this.nodes[1].getValue(paramSession);
          this.pattern = Pattern.compile(str);
        } 
        switch (this.funcType) {
          case 129:
            this.dataType = (Type)Type.SQL_BOOLEAN;
          case 130:
            this.dataType = (Type)Type.SQL_VARCHAR_DEFAULT;
          case 131:
            this.dataType = (Type)Type.getDefaultArrayType(12);
        } 
      case 87:
        for (bool = false; bool < this.nodes.length; bool++) {
          if ((this.nodes[bool]).dataType == null) {
            (this.nodes[bool]).dataType = (Type)Type.SQL_VARCHAR;
          } else if (!(this.nodes[bool]).dataType.isCharacterType()) {
            throw Error.error(5561);
          } 
        } 
        this.dataType = (Type)Type.SQL_VARCHAR_DEFAULT;
      case 113:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT; 
        if (!(this.nodes[0]).dataType.isCharacterType())
          throw Error.error(5561); 
        if (this.nodes[1] == null)
          this.dataType = (Type)Type.SQL_BLOB; 
        this.dataType = (Type)Type.SQL_CLOB;
        if ((this.nodes[1]).dataType == null || !(this.nodes[1]).dataType.isCharacterType())
          throw Error.error(5561); 
      case 117:
      case 138:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT; 
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (Type)Type.SQL_INTEGER; 
        if (!(this.nodes[1]).dataType.isIntegralType())
          throw Error.error(5561); 
        if (this.nodes[2] != null) {
          if ((this.nodes[2]).dataType == null)
            (this.nodes[2]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT; 
          if (!(this.nodes[2]).dataType.isCharacterType())
            throw Error.error(5561); 
        } 
        this.dataType = (this.nodes[0]).dataType;
        if (this.dataType.typeCode != 40)
          this.dataType = (Type)Type.SQL_VARCHAR_DEFAULT; 
        if ((this.nodes[1]).opType == 1) {
          Number number1 = (Number)this.nodes[1].getValue(paramSession);
          if (number1 != null)
            this.dataType = ((CharacterType)this.dataType).getCharacterType(number1.longValue()); 
        } 
      case 125:
        if ((this.nodes[1]).dataType == null)
          throw Error.error(5567); 
        if (!(this.nodes[1]).dataType.isArrayType())
          throw Error.error(5563); 
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = ((ArrayType)(this.nodes[1]).dataType).collectionBaseType(); 
        if ((((ArrayType)(this.nodes[1]).dataType).collectionBaseType()).typeComparisonGroup != (this.nodes[0]).dataType.typeComparisonGroup)
          throw Error.error(5563); 
        if (this.nodes[2] == null)
          this.nodes[2] = new ExpressionValue(ValuePool.INTEGER_1, (Type)Type.SQL_INTEGER); 
        if ((this.nodes[2]).dataType == null)
          (this.nodes[2]).dataType = (Type)Type.SQL_INTEGER; 
        if (!(this.nodes[2]).dataType.isIntegralType())
          throw Error.error(5563); 
        this.dataType = (Type)Type.SQL_INTEGER;
      case 149:
        if ((this.nodes[0]).dataType == null)
          throw Error.error(5567); 
        if (!(this.nodes[0]).dataType.isArrayType())
          throw Error.error(5563); 
        if (this.nodes[1] == null)
          this.nodes[1] = new ExpressionValue(ValuePool.getInt(338), (Type)Type.SQL_INTEGER); 
        if (this.nodes[2] == null)
          this.nodes[2] = new ExpressionValue(ValuePool.getInt(401), (Type)Type.SQL_INTEGER); 
        this.dataType = (this.nodes[0]).dataType;
      case 73:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_TIMESTAMP_NO_FRACTION; 
        if (!(this.nodes[0]).dataType.isDateOrTimestampType())
          throw Error.error(5563); 
        this.dataType = (Type)Type.SQL_TIMESTAMP_NO_FRACTION;
      case 98:
        this.dataType = (Type)CharacterType.getCharacterType(12, 6L);
      case 102:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_TIMESTAMP; 
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (Type)Type.SQL_VARCHAR; 
        this.dataType = (Type)Type.SQL_TIMESTAMP_WITH_TIME_ZONE;
      case 111:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_TIMESTAMP_NO_FRACTION; 
        if (!(this.nodes[0]).dataType.isDateOrTimestampType())
          throw Error.error(5563); 
        this.dataType = (Type)Type.SQL_TIMESTAMP_NO_FRACTION;
      case 119:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_TIMESTAMP_NO_FRACTION; 
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (Type)Type.SQL_TIMESTAMP_NO_FRACTION; 
        if (!(this.nodes[0]).dataType.isDateOrTimestampType())
          throw Error.error(5563); 
        if (!(this.nodes[1]).dataType.isDateOrTimestampType())
          throw Error.error(5563); 
        this.dataType = (Type)Type.SQL_DECIMAL_DEFAULT;
      case 120:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_TIMESTAMP_NO_FRACTION; 
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (Type)Type.SQL_VARCHAR; 
        if ((this.nodes[2]).dataType == null)
          (this.nodes[2]).dataType = (Type)Type.SQL_VARCHAR; 
        this.dataType = (Type)Type.SQL_TIMESTAMP_NO_FRACTION;
      case 121:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_TIMESTAMP_NO_FRACTION; 
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (Type)Type.SQL_VARCHAR; 
        this.dataType = (Type)Type.SQL_TIMESTAMP_NO_FRACTION;
      case 122:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_DOUBLE; 
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (Type)Type.SQL_VARCHAR; 
        if (!(this.nodes[0]).dataType.isNumberType())
          throw Error.error(5563); 
        if (!(this.nodes[1]).dataType.isCharacterType())
          throw Error.error(5563); 
        this.dataType = (Type)Type.SQL_INTERVAL_DAY_TO_SECOND_MAX_PRECISION;
      case 123:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_DOUBLE; 
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (Type)Type.SQL_VARCHAR; 
        if (!(this.nodes[0]).dataType.isNumberType())
          throw Error.error(5563); 
        if (!(this.nodes[1]).dataType.isCharacterType())
          throw Error.error(5563); 
        this.dataType = (Type)Type.SQL_INTERVAL_YEAR_TO_MONTH_MAX_PRECISION;
      case 145:
        this.dataType = (Type)CharacterType.getCharacterType(12, 6L);
      case 152:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_TIMESTAMP_WITH_TIME_ZONE; 
        this.dataType = (Type)Type.SQL_TIMESTAMP;
      case 153:
        this.dataType = (Type)Type.SQL_TIMESTAMP_NO_FRACTION;
      case 154:
        this.dataType = (Type)Type.SQL_TIMESTAMP_WITH_TIME_ZONE;
      case 163:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR; 
        this.dataType = (Type)Type.SQL_INTERVAL_DAY_TO_SECOND_MAX_PRECISION;
      case 164:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR; 
        this.dataType = (Type)Type.SQL_INTERVAL_YEAR_TO_MONTH_MAX_PRECISION;
      case 167:
        if ((this.nodes[0]).dataType == null)
          (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT; 
        if (this.nodes[1] == null) {
          String str = "DD-MON-YYYY HH24:MI:SS:FF TZH:TZM";
          this.nodes[1] = new ExpressionValue(str, (Type)Type.SQL_VARCHAR);
        } 
        if ((this.nodes[1]).dataType == null)
          (this.nodes[1]).dataType = (Type)Type.SQL_VARCHAR; 
        if (!(this.nodes[0]).dataType.isCharacterType() || !(this.nodes[1]).dataType.isCharacterType())
          throw Error.error(5567); 
        this.dataType = (Type)Type.SQL_TIMESTAMP_WITH_TIME_ZONE;
      case 171:
        for (bool = false; bool < this.nodes.length; bool++) {
          if ((this.nodes[bool]).dataType == null)
            (this.nodes[bool]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT; 
          if (!(this.nodes[bool]).dataType.isCharacterType() || (this.nodes[bool]).dataType.isLobType())
            throw Error.error(5563); 
        } 
        if ((this.nodes[1]).valueData != null && (this.nodes[2]).valueData != null)
          this.charLookup = getTranslationMap((String)(this.nodes[1]).valueData, (String)(this.nodes[2]).valueData); 
        this.dataType = (this.nodes[0]).dataType;
    } 
    throw Error.runtimeError(201, "FunctionCustom");
  }
  
  public String getSQL() {
    StringBuffer stringBuffer2;
    String str;
    StringBuffer stringBuffer1;
    switch (this.funcType) {
      case 1:
        stringBuffer2 = (new StringBuffer("LOCATE")).append("(").append(this.nodes[0].getSQL()).append(",").append(this.nodes[1].getSQL());
        if (this.nodes.length > 3 && this.nodes[3] != null)
          stringBuffer2.append(",").append(this.nodes[3].getSQL()); 
        stringBuffer2.append(")").toString();
        return stringBuffer2.toString();
      case 117:
      case 138:
        stringBuffer2 = new StringBuffer(this.name);
        stringBuffer2.append("(").append(this.nodes[0].getSQL());
        stringBuffer2.append(",").append(this.nodes[1].getSQL());
        if (this.nodes[2] != null)
          stringBuffer2.append(",").append(this.nodes[2].getSQL()); 
        stringBuffer2.append(")").toString();
        return stringBuffer2.toString();
      case 5:
      case 31:
      case 32:
        return super.getSQL();
      case 125:
        stringBuffer2 = (new StringBuffer(this.name)).append('(');
        stringBuffer2.append(this.nodes[0].getSQL()).append(' ').append("IN");
        stringBuffer2.append(' ').append(this.nodes[1].getSQL());
        if (((Number)(this.nodes[1]).valueData).intValue() == 389) {
          stringBuffer2.append(' ').append("FROM");
          stringBuffer2.append(' ').append(this.nodes[2].getSQL());
        } 
        stringBuffer2.append(')');
        return stringBuffer2.toString();
      case 149:
        stringBuffer2 = (new StringBuffer(this.name)).append('(');
        stringBuffer2.append(this.nodes[0].getSQL());
        if (((Number)(this.nodes[1]).valueData).intValue() == 389)
          stringBuffer2.append(' ').append("DESC"); 
        if (((Number)(this.nodes[2]).valueData).intValue() == 430) {
          stringBuffer2.append(' ').append("NULLS").append(' ');
          stringBuffer2.append("LAST");
        } 
        stringBuffer2.append(')');
        return stringBuffer2.toString();
      case 72:
      case 88:
      case 89:
      case 90:
      case 91:
      case 92:
      case 104:
      case 106:
      case 107:
      case 108:
      case 109:
      case 110:
      case 124:
      case 142:
      case 143:
      case 144:
      case 160:
      case 168:
      case 169:
      case 170:
        return this.name + "(" + ")";
      case 158:
        str = Tokens.getSQLTSIString(((Number)this.nodes[0].getValue(null)).intValue());
        return "TIMESTAMPADD" + "(" + str + "," + this.nodes[1].getSQL() + "," + this.nodes[2].getSQL() + ")";
      case 159:
        str = Tokens.getSQLTSIString(((Number)this.nodes[0].getValue(null)).intValue());
        return "TIMESTAMPDIFF" + "(" + str + "," + this.nodes[1].getSQL() + "," + this.nodes[2].getSQL() + ")";
      case 93:
        return this.nodes[0].getSQL() + ' ' + '+' + this.nodes[1].getSQL();
      case 94:
        return this.nodes[0].getSQL() + ' ' + '-' + this.nodes[1].getSQL();
      case 127:
      case 175:
      case 176:
        stringBuffer1 = (new StringBuffer(this.name)).append('(');
        if (this.nodes[0] != null)
          stringBuffer1.append(this.nodes[0].getSQL()); 
        stringBuffer1.append(')');
        return stringBuffer1.toString();
      case 113:
      case 136:
      case 156:
      case 162:
      case 165:
      case 166:
      case 167:
      case 172:
      case 173:
        stringBuffer1 = (new StringBuffer(this.name)).append('(');
        stringBuffer1.append(this.nodes[0].getSQL());
        if (this.nodes.length > 1 && this.nodes[1] != null)
          stringBuffer1.append(',').append(this.nodes[1].getSQL()); 
        stringBuffer1.append(')');
        return stringBuffer1.toString();
      case 71:
      case 74:
      case 75:
      case 76:
      case 83:
      case 85:
      case 86:
      case 97:
      case 99:
      case 103:
      case 114:
      case 116:
      case 126:
      case 128:
      case 134:
      case 137:
      case 146:
      case 147:
      case 148:
      case 150:
      case 155:
        return this.name + '(' + this.nodes[0].getSQL() + ')';
      case 77:
      case 78:
      case 79:
      case 80:
      case 81:
      case 82:
      case 87:
      case 101:
      case 112:
      case 129:
      case 130:
      case 131:
      case 132:
      case 135:
      case 161:
        return this.name + '(' + this.nodes[0].getSQL() + "," + this.nodes[1].getSQL() + ')';
      case 100:
        stringBuffer1 = (new StringBuffer(this.name)).append('(');
        stringBuffer1.append("ROW_COUNT");
        stringBuffer1.append(')');
        return stringBuffer1.toString();
      case 133:
      case 141:
        return this.name + '(' + this.nodes[0].getSQL() + "," + this.nodes[1].getSQL() + "," + this.nodes[2].getSQL() + ')';
      case 73:
      case 98:
      case 102:
      case 111:
      case 119:
      case 120:
      case 121:
      case 122:
      case 123:
      case 145:
      case 152:
      case 153:
      case 154:
      case 163:
      case 164:
      case 171:
        return getSQLSimple();
    } 
    return super.getSQL();
  }
  
  private String getSQLSimple() {
    StringBuffer stringBuffer = (new StringBuffer(this.name)).append('(');
    for (byte b = 0; b < this.nodes.length; b++) {
      if (b > 0)
        stringBuffer.append(','); 
      stringBuffer.append(this.nodes[b].getSQL());
    } 
    stringBuffer.append(')');
    return stringBuffer.toString();
  }
  
  public static char[] soundex(String paramString) {
    if (paramString == null)
      return null; 
    paramString = paramString.toUpperCase(Locale.ENGLISH);
    int i = paramString.length();
    char[] arrayOfChar = { '0', '0', '0', '0' };
    byte b1 = 48;
    byte b2 = 0;
    byte b3 = 0;
    while (b2 < i && b3 < 4) {
      byte b;
      char c = paramString.charAt(b2);
      if ("AEIOUY".indexOf(c) != -1) {
        b = 55;
      } else if (c == 'H' || c == 'W') {
        b = 56;
      } else if ("BFPV".indexOf(c) != -1) {
        b = 49;
      } else if ("CGJKQSXZ".indexOf(c) != -1) {
        b = 50;
      } else if (c == 'D' || c == 'T') {
        b = 51;
      } else if (c == 'L') {
        b = 52;
      } else if (c == 'M' || c == 'N') {
        b = 53;
      } else if (c == 'R') {
        b = 54;
      } else {
        continue;
      } 
      if (b3 == 0) {
        arrayOfChar[b3++] = c;
        b1 = b;
      } else if (b <= 54) {
        if (b != b1) {
          arrayOfChar[b3++] = b;
          b1 = b;
        } 
      } else if (b == 55) {
        b1 = b;
      } 
      continue;
      b2++;
    } 
    return arrayOfChar;
  }
  
  int getTSIToken(String paramString) {
    char c;
    if ("yy".equalsIgnoreCase(paramString) || "year".equalsIgnoreCase(paramString)) {
      c = '͝';
    } else if ("mm".equalsIgnoreCase(paramString) || "month".equalsIgnoreCase(paramString)) {
      c = '͛';
    } else if ("dd".equalsIgnoreCase(paramString) || "day".equalsIgnoreCase(paramString)) {
      c = '͙';
    } else if ("hh".equalsIgnoreCase(paramString) || "hour".equalsIgnoreCase(paramString)) {
      c = '͘';
    } else if ("mi".equalsIgnoreCase(paramString) || "minute".equalsIgnoreCase(paramString)) {
      c = '͗';
    } else if ("ss".equalsIgnoreCase(paramString) || "second".equalsIgnoreCase(paramString)) {
      c = '͖';
    } else if ("ms".equalsIgnoreCase(paramString) || "millisecond".equalsIgnoreCase(paramString)) {
      c = '͕';
    } else {
      throw Error.error(5566, paramString);
    } 
    return c;
  }
  
  IntKeyIntValueHashMap getTranslationMap(String paramString1, String paramString2) {
    IntKeyIntValueHashMap intKeyIntValueHashMap = new IntKeyIntValueHashMap();
    for (byte b = 0; b < paramString1.length(); b++) {
      char c = paramString1.charAt(b);
      if (b >= paramString2.length()) {
        intKeyIntValueHashMap.put(c, -1);
      } else {
        char c1 = paramString2.charAt(b);
        intKeyIntValueHashMap.put(c, c1);
      } 
    } 
    return intKeyIntValueHashMap;
  }
  
  String translateWithMap(String paramString, IntKeyIntValueHashMap paramIntKeyIntValueHashMap) {
    StringBuffer stringBuffer = new StringBuffer(paramString.length());
    for (byte b = 0; b < paramString.length(); b++) {
      char c = paramString.charAt(b);
      int i = paramIntKeyIntValueHashMap.get(c, -2);
      if (i == -2) {
        stringBuffer.append((char)c);
      } else if (i != -1) {
        stringBuffer.append((char)i);
      } 
    } 
    return stringBuffer.toString();
  }
  
  static {
    customValueFuncMap.put(772, 43);
    customValueFuncMap.put(716, 52);
  }
  
  static {
    nonDeterministicFuncSet.add(72);
    nonDeterministicFuncSet.add(87);
    nonDeterministicFuncSet.add(88);
    nonDeterministicFuncSet.add(89);
    nonDeterministicFuncSet.add(91);
    nonDeterministicFuncSet.add(104);
    nonDeterministicFuncSet.add(106);
    nonDeterministicFuncSet.add(110);
    nonDeterministicFuncSet.add(108);
    nonDeterministicFuncSet.add(109);
    nonDeterministicFuncSet.add(107);
    nonDeterministicFuncSet.add(142);
    nonDeterministicFuncSet.add(143);
    nonDeterministicFuncSet.add(144);
    nonDeterministicFuncSet.add(145);
    nonDeterministicFuncSet.add(153);
    nonDeterministicFuncSet.add(154);
    nonDeterministicFuncSet.add(156);
    nonDeterministicFuncSet.add(160);
    nonDeterministicFuncSet.add(168);
    nonDeterministicFuncSet.add(169);
    nonDeterministicFuncSet.add(170);
    nonDeterministicFuncSet.add(174);
    nonDeterministicFuncSet.add(175);
    nonDeterministicFuncSet.add(176);
    customRegularFuncMap.put(640, 71);
    customRegularFuncMap.put(641, 72);
    customRegularFuncMap.put(644, 73);
    customRegularFuncMap.put(642, 149);
    customRegularFuncMap.put(643, 74);
    customRegularFuncMap.put(645, 75);
    customRegularFuncMap.put(646, 76);
    customRegularFuncMap.put(647, 77);
    customRegularFuncMap.put(649, 78);
    customRegularFuncMap.put(650, 79);
    customRegularFuncMap.put(651, 6);
    customRegularFuncMap.put(652, 80);
    customRegularFuncMap.put(653, 81);
    customRegularFuncMap.put(654, 82);
    customRegularFuncMap.put(33, 83);
    customRegularFuncMap.put(655, 83);
    customRegularFuncMap.put(656, 84);
    customRegularFuncMap.put(658, 85);
    customRegularFuncMap.put(659, 86);
    customRegularFuncMap.put(660, 87);
    customRegularFuncMap.put(661, 43);
    customRegularFuncMap.put(662, 51);
    customRegularFuncMap.put(582, 88);
    customRegularFuncMap.put(664, 90);
    customRegularFuncMap.put(663, 89);
    customRegularFuncMap.put(665, 91);
    customRegularFuncMap.put(666, 92);
    customRegularFuncMap.put(667, 93);
    customRegularFuncMap.put(668, 94);
    customRegularFuncMap.put(669, 95);
    customRegularFuncMap.put(670, 96);
    customRegularFuncMap.put(73, 5);
    customRegularFuncMap.put(675, 5);
    customRegularFuncMap.put(676, 5);
    customRegularFuncMap.put(677, 5);
    customRegularFuncMap.put(678, 5);
    customRegularFuncMap.put(679, 97);
    customRegularFuncMap.put(680, 98);
    customRegularFuncMap.put(682, 99);
    customRegularFuncMap.put(391, 100);
    customRegularFuncMap.put(683, 101);
    customRegularFuncMap.put(685, 102);
    customRegularFuncMap.put(686, 103);
    customRegularFuncMap.put(127, 5);
    customRegularFuncMap.put(128, 104);
    customRegularFuncMap.put(135, 32);
    customRegularFuncMap.put(690, 1);
    customRegularFuncMap.put(691, 106);
    customRegularFuncMap.put(692, 108);
    customRegularFuncMap.put(693, 109);
    customRegularFuncMap.put(694, 110);
    customRegularFuncMap.put(695, 107);
    customRegularFuncMap.put(697, 111);
    customRegularFuncMap.put(698, 26);
    customRegularFuncMap.put(153, 112);
    customRegularFuncMap.put(431, 7);
    customRegularFuncMap.put(700, 113);
    customRegularFuncMap.put(707, 114);
    customRegularFuncMap.put(701, 1);
    customRegularFuncMap.put(703, 14);
    customRegularFuncMap.put(704, 116);
    customRegularFuncMap.put(705, 117);
    customRegularFuncMap.put(706, 31);
    customRegularFuncMap.put(169, 5);
    customRegularFuncMap.put(173, 5);
    customRegularFuncMap.put(709, 5);
    customRegularFuncMap.put(710, 119);
    customRegularFuncMap.put(713, 120);
    customRegularFuncMap.put(717, 122);
    customRegularFuncMap.put(718, 123);
    customRegularFuncMap.put(719, 8);
    customRegularFuncMap.put(720, 124);
    customRegularFuncMap.put(721, 125);
    customRegularFuncMap.put(722, 5);
    customRegularFuncMap.put(723, 126);
    customRegularFuncMap.put(724, 127);
    customRegularFuncMap.put(725, 128);
    customRegularFuncMap.put(726, 129);
    customRegularFuncMap.put(727, 130);
    customRegularFuncMap.put(728, 131);
    customRegularFuncMap.put(234, 132);
    customRegularFuncMap.put(729, 133);
    customRegularFuncMap.put(730, 134);
    customRegularFuncMap.put(240, 135);
    customRegularFuncMap.put(731, 136);
    customRegularFuncMap.put(732, 137);
    customRegularFuncMap.put(734, 138);
    customRegularFuncMap.put(733, 31);
    customRegularFuncMap.put(250, 5);
    customRegularFuncMap.put(735, 5);
    customRegularFuncMap.put(736, 141);
    customRegularFuncMap.put(737, 142);
    customRegularFuncMap.put(738, 143);
    customRegularFuncMap.put(739, 144);
    customRegularFuncMap.put(740, 145);
    customRegularFuncMap.put(741, 146);
    customRegularFuncMap.put(742, 147);
    customRegularFuncMap.put(743, 149);
    customRegularFuncMap.put(744, 148);
    customRegularFuncMap.put(514, 150);
    customRegularFuncMap.put(756, 23);
    customRegularFuncMap.put(757, 152);
    customRegularFuncMap.put(758, 153);
    customRegularFuncMap.put(759, 154);
    customRegularFuncMap.put(760, 155);
    customRegularFuncMap.put(282, 156);
    customRegularFuncMap.put(761, 157);
    customRegularFuncMap.put(762, 158);
    customRegularFuncMap.put(763, 159);
    customRegularFuncMap.put(764, 160);
    customRegularFuncMap.put(765, 161);
    customRegularFuncMap.put(766, 162);
    customRegularFuncMap.put(767, 163);
    customRegularFuncMap.put(768, 164);
    customRegularFuncMap.put(769, 165);
    customRegularFuncMap.put(770, 166);
    customRegularFuncMap.put(774, 168);
    customRegularFuncMap.put(775, 169);
    customRegularFuncMap.put(776, 170);
    customRegularFuncMap.put(287, 171);
    customRegularFuncMap.put(777, 172);
    customRegularFuncMap.put(295, 173);
    customRegularFuncMap.put(779, 27);
    customRegularFuncMap.put(780, 176);
    customRegularFuncMap.put(781, 175);
    customRegularFuncMap.put(782, 174);
    customRegularFuncMap.put(790, 5);
    customRegularFuncMap.put(323, 5);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\FunctionCustom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */