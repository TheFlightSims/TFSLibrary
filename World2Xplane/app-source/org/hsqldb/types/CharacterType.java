package org.hsqldb.types;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.lib.StringUtil;
import org.hsqldb.lib.java.JavaSystem;

public class CharacterType extends Type {
  static final int defaultCharPrecision = 256;
  
  static final int defaultVarcharPrecision = 32768;
  
  public static final long maxCharPrecision = 2147483647L;
  
  Collation collation;
  
  Charset charset;
  
  String nameString;
  
  private static final int fixedTypesLength = 32;
  
  static CharacterType[] charArray = new CharacterType[32];
  
  public CharacterType(Collation paramCollation, int paramInt, long paramLong) {
    super(12, paramInt, paramLong, 0);
    if (paramCollation == null)
      paramCollation = Collation.getDefaultInstance(); 
    this.collation = paramCollation;
    this.charset = Charset.getDefaultInstance();
    this.nameString = getNameStringPrivate();
  }
  
  public CharacterType(int paramInt, long paramLong) {
    super(12, paramInt, paramLong, 0);
    this.collation = Collation.getDefaultInstance();
    this.charset = Charset.getDefaultInstance();
    this.nameString = getNameStringPrivate();
  }
  
  public int displaySize() {
    return (this.precision > 2147483647L) ? Integer.MAX_VALUE : (int)this.precision;
  }
  
  public int getJDBCTypeCode() {
    switch (this.typeCode) {
      case 1:
        return 1;
      case 12:
        return 12;
      case 40:
        return 2005;
    } 
    throw Error.runtimeError(201, "CharacterType");
  }
  
  public Class getJDBCClass() {
    return String.class;
  }
  
  public String getJDBCClassName() {
    return "java.lang.String";
  }
  
  public int getSQLGenericTypeCode() {
    return (this.typeCode == 1) ? this.typeCode : 12;
  }
  
  public String getNameString() {
    return this.nameString;
  }
  
  private String getNameStringPrivate() {
    switch (this.typeCode) {
      case 1:
        return "CHARACTER";
      case 12:
        return "VARCHAR";
      case 40:
        return "CLOB";
    } 
    throw Error.runtimeError(201, "CharacterType");
  }
  
  public String getFullNameString() {
    switch (this.typeCode) {
      case 1:
        return "CHARACTER";
      case 12:
        return "CHARACTER VARYING";
      case 40:
        return "CHARACTER LARGE OBJECT";
    } 
    throw Error.runtimeError(201, "CharacterType");
  }
  
  public String getDefinition() {
    if (this.precision == 0L)
      return getNameString(); 
    StringBuffer stringBuffer = new StringBuffer(16);
    stringBuffer.append(getNameString());
    stringBuffer.append('(');
    stringBuffer.append(this.precision);
    stringBuffer.append(')');
    return stringBuffer.toString();
  }
  
  public boolean isCharacterType() {
    return true;
  }
  
  public long getMaxPrecision() {
    return 2147483647L;
  }
  
  public boolean acceptsPrecision() {
    return true;
  }
  
  public boolean requiresPrecision() {
    return (this.typeCode == 12);
  }
  
  public int precedenceDegree(Type paramType) {
    if (paramType.typeCode == this.typeCode)
      return 0; 
    if (!paramType.isCharacterType())
      return Integer.MIN_VALUE; 
    switch (this.typeCode) {
      case 1:
        return (paramType.typeCode == 40) ? 4 : 2;
      case 12:
        return (paramType.typeCode == 40) ? 4 : 2;
      case 40:
        return (paramType.typeCode == 1) ? -4 : -2;
    } 
    throw Error.runtimeError(201, "CharacterType");
  }
  
  public Type getAggregateType(Type paramType) {
    if (paramType == null)
      return this; 
    if (paramType == SQL_ALL_TYPES)
      return this; 
    if (this.typeCode == paramType.typeCode)
      return (this.precision >= paramType.precision) ? this : paramType; 
    switch (paramType.typeCode) {
      case 1:
        return (this.precision >= paramType.precision) ? this : getCharacterType(this.typeCode, paramType.precision, paramType.getCollation());
      case 12:
        return (this.typeCode == 40) ? ((this.precision >= paramType.precision) ? this : getCharacterType(this.typeCode, paramType.precision, paramType.getCollation())) : ((paramType.precision >= this.precision) ? paramType : getCharacterType(paramType.typeCode, this.precision, paramType.getCollation()));
      case 40:
        return (paramType.precision >= this.precision) ? paramType : getCharacterType(paramType.typeCode, this.precision, paramType.getCollation());
      case 14:
      case 15:
      case 30:
      case 60:
      case 61:
      case 1111:
        throw Error.error(5562);
    } 
    throw Error.error(5562);
  }
  
  public Type getCombinedType(Session paramSession, Type paramType, int paramInt) {
    CharacterType characterType;
    Type type;
    if (paramInt != 36)
      return getAggregateType(paramType); 
    long l = this.precision + paramType.precision;
    switch (paramType.typeCode) {
      case 0:
        return this;
      case 1:
        characterType = this;
        break;
      case 12:
        type = (this.typeCode == 40) ? this : paramType;
        break;
      case 40:
        type = paramType;
        break;
      default:
        throw Error.error(5562);
    } 
    if (l > 2147483647L)
      if (this.typeCode == 60) {
        l = 2147483647L;
      } else if (this.typeCode == 1) {
        l = 2147483647L;
      } else if (this.typeCode == 12) {
        l = 2147483647L;
      }  
    return getCharacterType(type.typeCode, l, this.collation);
  }
  
  public int compare(Session paramSession, Object paramObject1, Object paramObject2) {
    return compare(paramSession, paramObject1, paramObject2, 40);
  }
  
  public int compare(Session paramSession, Object paramObject1, Object paramObject2, int paramInt) {
    if (paramObject1 == paramObject2)
      return 0; 
    if (paramObject1 == null)
      return -1; 
    if (paramObject2 == null)
      return 1; 
    if (paramObject2 instanceof ClobData)
      return -paramSession.database.lobManager.compare(this.collation, (ClobData)paramObject2, (String)paramObject1); 
    String str1 = (String)paramObject1;
    String str2 = (String)paramObject2;
    int i = str1.length();
    int j = str2.length();
    if (i != j)
      if (i > j) {
        if (this.collation.isPadSpace() && paramInt != 42) {
          char[] arrayOfChar = new char[i];
          str2.getChars(0, j, arrayOfChar, 0);
          ArrayUtil.fillArray(arrayOfChar, j, ' ');
          str2 = String.valueOf(arrayOfChar);
        } 
      } else if (this.collation.isPadSpace() && paramInt != 42) {
        char[] arrayOfChar = new char[j];
        str1.getChars(0, i, arrayOfChar, 0);
        ArrayUtil.fillArray(arrayOfChar, i, ' ');
        str1 = String.valueOf(arrayOfChar);
      }  
    return this.collation.compare(str1, str2);
  }
  
  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject) {
    int i;
    ClobData clobData;
    char[] arrayOfChar;
    int j;
    if (paramObject == null)
      return paramObject; 
    if (this.precision == 0L)
      return paramObject; 
    switch (this.typeCode) {
      case 1:
        i = ((String)paramObject).length();
        if (i == this.precision)
          return paramObject; 
        if (i > this.precision) {
          if (getRightTrimSise((String)paramObject, ' ') <= this.precision)
            return ((String)paramObject).substring(0, (int)this.precision); 
          throw Error.error(3401);
        } 
        arrayOfChar = new char[(int)this.precision];
        ((String)paramObject).getChars(0, i, arrayOfChar, 0);
        for (j = i; j < this.precision; j++)
          arrayOfChar[j] = ' '; 
        return new String(arrayOfChar);
      case 12:
        i = ((String)paramObject).length();
        if (i > this.precision) {
          if (getRightTrimSise((String)paramObject, ' ') <= this.precision)
            return ((String)paramObject).substring(0, (int)this.precision); 
          throw Error.error(3401);
        } 
        return paramObject;
      case 40:
        clobData = (ClobData)paramObject;
        if (clobData.length(paramSessionInterface) > this.precision)
          throw Error.error(3401); 
        return paramObject;
    } 
    throw Error.runtimeError(201, "CharacterType");
  }
  
  public Object castToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType) {
    return (paramObject == null) ? paramObject : castOrConvertToType(paramSessionInterface, paramObject, paramType, true);
  }
  
  public Object castOrConvertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType, boolean paramBoolean) {
    int i;
    long l;
    ClobDataID clobDataID;
    byte[] arrayOfByte;
    switch (paramType.typeCode) {
      case 1:
      case 12:
        i = ((String)paramObject).length();
        if (this.precision != 0L && i > this.precision) {
          if (StringUtil.rightTrimSize((String)paramObject) > this.precision) {
            if (!paramBoolean)
              throw Error.error(3401); 
            paramSessionInterface.addWarning(Error.error(1004));
          } 
          paramObject = ((String)paramObject).substring(0, (int)this.precision);
        } 
        switch (this.typeCode) {
          case 1:
            return convertToTypeLimits(paramSessionInterface, paramObject);
          case 12:
            return paramObject;
          case 40:
            clobDataID = paramSessionInterface.createClob(((String)paramObject).length());
            clobDataID.setString(paramSessionInterface, 0L, (String)paramObject);
            return clobDataID;
        } 
        throw Error.runtimeError(201, "CharacterType");
      case 40:
        l = ((ClobData)paramObject).length(paramSessionInterface);
        if (this.precision != 0L && l > this.precision) {
          if (!paramBoolean)
            throw Error.error(3401); 
          paramSessionInterface.addWarning(Error.error(1004));
        } 
        switch (this.typeCode) {
          case 1:
          case 12:
            if (l > 2147483647L) {
              if (!paramBoolean)
                throw Error.error(3401); 
              l = 2147483647L;
            } 
            paramObject = ((ClobData)paramObject).getSubString(paramSessionInterface, 0L, (int)l);
            return convertToTypeLimits(paramSessionInterface, paramObject);
          case 40:
            return (this.precision != 0L && l > this.precision) ? ((ClobData)paramObject).getClob(paramSessionInterface, 0L, this.precision) : paramObject;
        } 
        throw Error.runtimeError(201, "CharacterType");
      case 1111:
        throw Error.error(5561);
      case 30:
        l = ((BlobData)paramObject).length(paramSessionInterface);
        if (this.precision != 0L && l * 2L > this.precision)
          throw Error.error(3401); 
        arrayOfByte = ((BlobData)paramObject).getBytes(paramSessionInterface, 0L, (int)l);
        paramObject = StringConverter.byteArrayToHexString(arrayOfByte);
        return convertToTypeLimits(paramSessionInterface, paramObject);
    } 
    String str = paramType.convertToString(paramObject);
    if (this.precision != 0L && str.length() > this.precision)
      throw Error.error(3401); 
    paramObject = str;
    return convertToTypeLimits(paramSessionInterface, paramObject);
  }
  
  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType) {
    return (paramObject == null) ? paramObject : castOrConvertToType(paramSessionInterface, paramObject, paramType, false);
  }
  
  public Object convertToTypeJDBC(SessionInterface paramSessionInterface, Object paramObject, Type paramType) {
    if (paramObject == null)
      return paramObject; 
    if (paramType.typeCode == 30)
      throw Error.error(5561); 
    return convertToType(paramSessionInterface, paramObject, paramType);
  }
  
  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject) {
    String str;
    if (paramObject == null)
      return paramObject; 
    if (paramObject instanceof Boolean) {
      str = paramObject.toString();
    } else if (paramObject instanceof BigDecimal) {
      str = JavaSystem.toString((BigDecimal)paramObject);
    } else if (paramObject instanceof Number) {
      str = paramObject.toString();
    } else if (paramObject instanceof String) {
      str = (String)paramObject;
    } else if (paramObject instanceof Date) {
      str = ((Date)paramObject).toString();
    } else if (paramObject instanceof Time) {
      str = ((Time)paramObject).toString();
    } else if (paramObject instanceof Timestamp) {
      str = ((Timestamp)paramObject).toString();
    } else if (paramObject instanceof java.util.Date) {
      str = HsqlDateTime.getTimestampString(((Date)paramObject).getTime());
    } else {
      throw Error.error(5561);
    } 
    return str;
  }
  
  public Object convertJavaToSQL(SessionInterface paramSessionInterface, Object paramObject) {
    return convertToDefaultType(paramSessionInterface, paramObject);
  }
  
  public String convertToString(Object paramObject) {
    int i;
    char[] arrayOfChar;
    int j;
    if (paramObject == null)
      return null; 
    switch (this.typeCode) {
      case 1:
        i = ((String)paramObject).length();
        if (this.precision == 0L || i == this.precision)
          return (String)paramObject; 
        arrayOfChar = new char[(int)this.precision];
        ((String)paramObject).getChars(0, i, arrayOfChar, 0);
        for (j = i; j < this.precision; j++)
          arrayOfChar[j] = ' '; 
        return new String(arrayOfChar);
      case 12:
        return (String)paramObject;
    } 
    throw Error.runtimeError(201, "CharacterType");
  }
  
  public String convertToSQLString(Object paramObject) {
    if (paramObject == null)
      return "NULL"; 
    String str = convertToString(paramObject);
    return StringConverter.toQuotedString(str, '\'', true);
  }
  
  public boolean canConvertFrom(Type paramType) {
    return (!paramType.isObjectType() && !paramType.isArrayType());
  }
  
  public int canMoveFrom(Type paramType) {
    if (paramType == this)
      return 0; 
    if (!paramType.isCharacterType())
      return -1; 
    switch (this.typeCode) {
      case 12:
        return (paramType.typeCode == this.typeCode) ? ((this.precision >= paramType.precision) ? 0 : 1) : ((paramType.typeCode == 1) ? ((this.precision >= paramType.precision) ? 0 : -1) : -1);
      case 40:
        return (paramType.typeCode == 40) ? ((this.precision >= paramType.precision) ? 0 : 1) : -1;
      case 1:
        return (paramType.typeCode == 1 && this.precision == paramType.precision) ? 0 : -1;
    } 
    return -1;
  }
  
  public Collation getCollation() {
    return this.collation;
  }
  
  public Charset getCharacterSet() {
    return this.charset;
  }
  
  public long position(SessionInterface paramSessionInterface, Object paramObject1, Object paramObject2, Type paramType, long paramLong) {
    if (paramObject1 == null || paramObject2 == null)
      return -1L; 
    if (paramType.typeCode == 40) {
      long l = ((ClobData)paramObject2).length(paramSessionInterface);
      if (paramLong + l > ((String)paramObject1).length())
        return -1L; 
      if (l > 2147483647L)
        throw Error.error(3459); 
      String str = ((ClobData)paramObject2).getSubString(paramSessionInterface, 0L, (int)l);
      return ((String)paramObject1).indexOf(str, (int)paramLong);
    } 
    if (paramType.isCharacterType()) {
      long l = ((String)paramObject2).length();
      return (paramLong + l > ((String)paramObject1).length()) ? -1L : ((String)paramObject1).indexOf((String)paramObject2, (int)paramLong);
    } 
    throw Error.runtimeError(201, "CharacterType");
  }
  
  public Object substring(SessionInterface paramSessionInterface, Object paramObject, long paramLong1, long paramLong2, boolean paramBoolean1, boolean paramBoolean2) {
    long l1;
    long l2 = (this.typeCode == 40) ? ((ClobData)paramObject).length(paramSessionInterface) : ((String)paramObject).length();
    if (paramBoolean2) {
      l1 = l2;
      if (paramLong2 > l2) {
        paramLong1 = 0L;
        paramLong2 = l2;
      } else {
        paramLong1 = l2 - paramLong2;
      } 
    } else if (paramBoolean1) {
      l1 = paramLong1 + paramLong2;
    } else {
      l1 = (l2 > paramLong1) ? l2 : paramLong1;
    } 
    if (l1 < paramLong1)
      throw Error.error(3431); 
    if (paramLong1 > l1 || l1 < 0L) {
      paramLong1 = 0L;
      l1 = 0L;
    } 
    if (paramLong1 < 0L)
      paramLong1 = 0L; 
    if (l1 > l2)
      l1 = l2; 
    paramLong2 = l1 - paramLong1;
    if (paramObject instanceof String)
      return ((String)paramObject).substring((int)paramLong1, (int)(paramLong1 + paramLong2)); 
    if (paramObject instanceof ClobData) {
      ClobDataID clobDataID = paramSessionInterface.createClob(paramLong2);
      if (paramLong2 > 2147483647L)
        throw Error.error(3401); 
      String str = ((ClobData)paramObject).getSubString(paramSessionInterface, paramLong1, (int)paramLong2);
      clobDataID.setString(paramSessionInterface, 0L, str);
      return clobDataID;
    } 
    throw Error.runtimeError(201, "CharacterType");
  }
  
  public Object upper(Session paramSession, Object paramObject) {
    if (paramObject == null)
      return null; 
    if (this.typeCode == 40) {
      String str = ((ClobData)paramObject).getSubString((SessionInterface)paramSession, 0L, (int)((ClobData)paramObject).length((SessionInterface)paramSession));
      str = this.collation.toUpperCase(str);
      ClobDataID clobDataID = paramSession.createClob(str.length());
      clobDataID.setString((SessionInterface)paramSession, 0L, str);
      return clobDataID;
    } 
    return this.collation.toUpperCase((String)paramObject);
  }
  
  public Object lower(Session paramSession, Object paramObject) {
    if (paramObject == null)
      return null; 
    if (this.typeCode == 40) {
      String str = ((ClobData)paramObject).getSubString((SessionInterface)paramSession, 0L, (int)((ClobData)paramObject).length((SessionInterface)paramSession));
      str = this.collation.toLowerCase(str);
      ClobDataID clobDataID = paramSession.createClob(str.length());
      clobDataID.setString((SessionInterface)paramSession, 0L, str);
      return clobDataID;
    } 
    return this.collation.toLowerCase((String)paramObject);
  }
  
  public Object trim(SessionInterface paramSessionInterface, Object paramObject, char paramChar, boolean paramBoolean1, boolean paramBoolean2) {
    String str;
    if (paramObject == null)
      return null; 
    if (this.typeCode == 40) {
      long l = ((ClobData)paramObject).length(paramSessionInterface);
      if (l > 2147483647L)
        throw Error.error(3459); 
      str = ((ClobData)paramObject).getSubString(paramSessionInterface, 0L, (int)l);
    } else {
      str = (String)paramObject;
    } 
    int i = str.length();
    if (paramBoolean2) {
      while (--i >= 0 && str.charAt(i) == paramChar)
        i--; 
      i++;
    } 
    byte b = 0;
    if (paramBoolean1)
      while (b < i && str.charAt(b) == paramChar)
        b++;  
    if (b != 0 || i != str.length())
      str = str.substring(b, i); 
    if (this.typeCode == 40) {
      ClobDataID clobDataID = paramSessionInterface.createClob(str.length());
      clobDataID.setString(paramSessionInterface, 0L, str);
      return clobDataID;
    } 
    return str;
  }
  
  public Object overlay(SessionInterface paramSessionInterface, Object paramObject1, Object paramObject2, long paramLong1, long paramLong2, boolean paramBoolean) {
    if (paramObject1 == null || paramObject2 == null)
      return null; 
    if (!paramBoolean)
      paramLong2 = (this.typeCode == 40) ? ((ClobData)paramObject2).length(paramSessionInterface) : ((String)paramObject2).length(); 
    Object object = concat((Session)null, substring(paramSessionInterface, paramObject1, 0L, paramLong1, true, false), paramObject2);
    return concat((Session)null, object, substring(paramSessionInterface, paramObject1, paramLong1 + paramLong2, 0L, false, false));
  }
  
  public Object concat(Session paramSession, Object paramObject1, Object paramObject2) {
    String str1;
    String str2;
    if (paramObject1 == null || paramObject2 == null)
      return null; 
    if (paramObject1 instanceof ClobData) {
      str1 = ((ClobData)paramObject1).getSubString((SessionInterface)paramSession, 0L, (int)((ClobData)paramObject1).length((SessionInterface)paramSession));
    } else {
      str1 = (String)paramObject1;
    } 
    if (paramObject2 instanceof ClobData) {
      str2 = ((ClobData)paramObject2).getSubString((SessionInterface)paramSession, 0L, (int)((ClobData)paramObject2).length((SessionInterface)paramSession));
    } else {
      str2 = (String)paramObject2;
    } 
    if (this.typeCode == 40) {
      ClobDataID clobDataID = paramSession.createClob((str1.length() + str2.length()));
      clobDataID.setString((SessionInterface)paramSession, 0L, str1);
      clobDataID.setString((SessionInterface)paramSession, str1.length(), str2);
      return clobDataID;
    } 
    return str1 + str2;
  }
  
  public long size(SessionInterface paramSessionInterface, Object paramObject) {
    return (this.typeCode == 40) ? ((ClobData)paramObject).length(paramSessionInterface) : ((String)paramObject).length();
  }
  
  public Boolean match(Session paramSession, String paramString, String[] paramArrayOfString) {
    if (paramString == null || paramArrayOfString == null)
      return null; 
    String str = null;
    int i = 0;
    boolean bool = true;
    for (byte b = 0; b < paramArrayOfString.length; b++) {
      if (paramArrayOfString[b] == null) {
        i++;
        bool = true;
      } else if (paramArrayOfString[b].length() == 0) {
        bool = false;
      } 
      if (bool) {
        if (i + paramArrayOfString[b].length() > paramString.length())
          return Boolean.FALSE; 
        str = paramString.substring(i, i + paramArrayOfString[b].length());
        if (this.collation.compare(str, paramArrayOfString[b]) != 0)
          return Boolean.FALSE; 
        i += paramArrayOfString[b].length();
      } else {
        int j = paramString.indexOf(paramArrayOfString[b], i);
        if (j < 0)
          return Boolean.FALSE; 
        i = j + paramArrayOfString[b].length();
        bool = true;
      } 
    } 
    return Boolean.TRUE;
  }
  
  public Type getCharacterType(long paramLong) {
    return (paramLong == this.precision) ? this : getCharacterType(this.typeCode, paramLong, this.collation);
  }
  
  public static int getRightTrimSise(String paramString, char paramChar) {
    int i;
    for (i = paramString.length(); --i >= 0 && paramString.charAt(i) == paramChar; i--);
    return ++i;
  }
  
  public static CharacterType getCharacterType(int paramInt, long paramLong) {
    switch (paramInt) {
      case 1:
        if (paramLong < 32L)
          return charArray[(int)paramLong]; 
      case 12:
        return new CharacterType(paramInt, (int)paramLong);
      case 40:
        return new ClobType(paramLong);
    } 
    throw Error.runtimeError(201, "CharacterType");
  }
  
  public static CharacterType getCharacterType(int paramInt, long paramLong, Collation paramCollation) {
    ClobType clobType;
    if (paramCollation == null)
      paramCollation = Collation.getDefaultInstance(); 
    switch (paramInt) {
      case 1:
      case 12:
        return new CharacterType(paramCollation, paramInt, (int)paramLong);
      case 40:
        clobType = new ClobType(paramLong);
        clobType.collation = paramCollation;
        return clobType;
    } 
    throw Error.runtimeError(201, "CharacterType");
  }
  
  static {
    for (byte b = 0; b < charArray.length; b++)
      charArray[b] = new CharacterType(1, b); 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\CharacterType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */