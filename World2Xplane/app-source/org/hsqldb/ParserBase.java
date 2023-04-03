package org.hsqldb;

import java.math.BigDecimal;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.IntKeyIntValueHashMap;
import org.hsqldb.map.ValuePool;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

public class ParserBase {
  protected Scanner scanner;
  
  protected Token token;
  
  protected boolean isRecording;
  
  protected HsqlArrayList recordedStatement;
  
  private final Token dummyToken = new Token();
  
  protected boolean isCheckOrTriggerCondition;
  
  protected boolean isSchemaDefinition;
  
  protected int parsePosition;
  
  static final BigDecimal LONG_MAX_VALUE_INCREMENT = BigDecimal.valueOf(Long.MAX_VALUE).add(BigDecimal.valueOf(1L));
  
  private static final IntKeyIntValueHashMap expressionTypeMap = new IntKeyIntValueHashMap(37);
  
  ParserBase(Scanner paramScanner) {
    this.scanner = paramScanner;
    this.token = this.scanner.token;
  }
  
  public Scanner getScanner() {
    return this.scanner;
  }
  
  public int getParsePosition() {
    return this.parsePosition;
  }
  
  public void setParsePosition(int paramInt) {
    this.parsePosition = paramInt;
  }
  
  void reset(String paramString) {
    this.scanner.reset(paramString);
    this.parsePosition = 0;
    this.isCheckOrTriggerCondition = false;
    this.isSchemaDefinition = false;
    this.isRecording = false;
    this.recordedStatement = null;
  }
  
  int getPosition() {
    return this.scanner.getTokenPosition();
  }
  
  void rewind(int paramInt) {
    if (paramInt == this.scanner.getTokenPosition())
      return; 
    this.scanner.position(paramInt);
    if (this.isRecording) {
      int i;
      for (i = this.recordedStatement.size() - 1; i >= 0; i--) {
        Token token = (Token)this.recordedStatement.get(i);
        if (token.position < paramInt)
          break; 
      } 
      this.recordedStatement.setSize(i + 1);
    } 
    read();
  }
  
  String getLastPart() {
    return this.scanner.getPart(this.parsePosition, this.scanner.getTokenPosition());
  }
  
  String getLastPart(int paramInt) {
    return this.scanner.getPart(paramInt, this.scanner.getTokenPosition());
  }
  
  String getLastPartAndCurrent(int paramInt) {
    return this.scanner.getPart(paramInt, this.scanner.getPosition());
  }
  
  String getStatement(int paramInt, short[] paramArrayOfshort) {
    while (this.token.tokenType != 821 && this.token.tokenType != 872 && ArrayUtil.find(paramArrayOfshort, this.token.tokenType) == -1)
      read(); 
    return this.scanner.getPart(paramInt, this.scanner.getTokenPosition());
  }
  
  String getStatementForRoutine(int paramInt, short[] paramArrayOfshort) {
    byte b = 0;
    byte b1 = -1;
    int i = -1;
    while (true) {
      if (this.token.tokenType == 821) {
        i = this.scanner.getTokenPosition();
        b1 = b;
      } else {
        if (this.token.tokenType == 872) {
          if (b1 > 0 && b1 == b - 1)
            rewind(i); 
          break;
        } 
        if (ArrayUtil.find(paramArrayOfshort, this.token.tokenType) != -1)
          break; 
      } 
      read();
      b++;
    } 
    return this.scanner.getPart(paramInt, this.scanner.getTokenPosition());
  }
  
  void startRecording() {
    this.recordedStatement = new HsqlArrayList();
    this.recordedStatement.add(this.token.duplicate());
    this.isRecording = true;
  }
  
  Token getRecordedToken() {
    return this.isRecording ? (Token)this.recordedStatement.get(this.recordedStatement.size() - 1) : this.dummyToken;
  }
  
  Token[] getRecordedStatement() {
    this.isRecording = false;
    this.recordedStatement.remove(this.recordedStatement.size() - 1);
    Token[] arrayOfToken = new Token[this.recordedStatement.size()];
    this.recordedStatement.toArray(arrayOfToken);
    this.recordedStatement = null;
    return arrayOfToken;
  }
  
  void read() {
    this.scanner.scanNext();
    if (this.token.isMalformed) {
      short s = -1;
      switch (this.token.tokenType) {
        case 880:
          s = 5587;
          break;
        case 879:
          s = 5588;
          break;
        case 881:
          s = 5586;
          break;
        case 877:
          s = 5584;
          break;
        case -1:
          s = 5582;
          break;
        case 878:
          s = 5585;
          break;
        case 882:
          s = 5589;
          break;
        case 883:
          s = 5583;
          break;
      } 
      throw Error.error(s, this.token.getFullString());
    } 
    if (this.isRecording) {
      Token token = this.token.duplicate();
      token.position = this.scanner.getTokenPosition();
      this.recordedStatement.add(token);
    } 
  }
  
  boolean isReservedKey() {
    return this.scanner.token.isReservedIdentifier;
  }
  
  boolean isCoreReservedKey() {
    return this.scanner.token.isCoreReservedIdentifier;
  }
  
  boolean isNonReservedIdentifier() {
    return (!this.scanner.token.isReservedIdentifier && (this.scanner.token.isUndelimitedIdentifier || this.scanner.token.isDelimitedIdentifier));
  }
  
  void checkIsNonReservedIdentifier() {
    if (!isNonReservedIdentifier())
      throw unexpectedToken(); 
  }
  
  boolean isNonCoreReservedIdentifier() {
    return (!this.scanner.token.isCoreReservedIdentifier && (this.scanner.token.isUndelimitedIdentifier || this.scanner.token.isDelimitedIdentifier));
  }
  
  void checkIsNonCoreReservedIdentifier() {
    if (!isNonCoreReservedIdentifier())
      throw unexpectedToken(); 
  }
  
  void checkIsIrregularCharInIdentifier() {
    if (this.scanner.token.hasIrregularChar)
      throw unexpectedToken(); 
  }
  
  boolean isIdentifier() {
    return (this.scanner.token.isUndelimitedIdentifier || this.scanner.token.isDelimitedIdentifier);
  }
  
  void checkIsIdentifier() {
    if (!isIdentifier())
      throw unexpectedToken(); 
  }
  
  boolean isDelimitedIdentifier() {
    return this.scanner.token.isDelimitedIdentifier;
  }
  
  void checkIsDelimitedIdentifier() {
    if (this.token.tokenType != 871)
      throw Error.error(5569); 
  }
  
  void checkIsNotQuoted() {
    if (this.token.tokenType == 871)
      throw unexpectedToken(); 
  }
  
  void checkIsValue() {
    if (this.token.tokenType != 869)
      throw unexpectedToken(); 
  }
  
  void checkIsValue(int paramInt) {
    if (this.token.tokenType != 869 || this.token.dataType.typeCode != paramInt)
      throw unexpectedToken(); 
  }
  
  void checkIsThis(int paramInt) {
    if (this.token.tokenType != paramInt) {
      String str = Tokens.getKeyword(paramInt);
      throw unexpectedTokenRequire(str);
    } 
  }
  
  boolean isUndelimitedSimpleName() {
    return (this.token.isUndelimitedIdentifier && this.token.namePrefix == null);
  }
  
  boolean isDelimitedSimpleName() {
    return (this.token.isDelimitedIdentifier && this.token.namePrefix == null);
  }
  
  boolean isSimpleName() {
    return (isNonCoreReservedIdentifier() && this.token.namePrefix == null);
  }
  
  void checkIsSimpleName() {
    if (!isSimpleName())
      throw unexpectedToken(); 
  }
  
  void readUnquotedIdentifier(String paramString) {
    checkIsSimpleName();
    if (!this.token.tokenString.equals(paramString))
      throw unexpectedToken(); 
    read();
  }
  
  String readQuotedString() {
    checkIsValue();
    if (this.token.dataType.typeCode != 1)
      throw Error.error(5563); 
    String str = this.token.tokenString;
    read();
    return str;
  }
  
  void readThis(int paramInt) {
    if (this.token.tokenType != paramInt) {
      String str = Tokens.getKeyword(paramInt);
      throw unexpectedTokenRequire(str);
    } 
    read();
  }
  
  boolean readIfThis(int paramInt) {
    if (this.token.tokenType == paramInt) {
      read();
      return true;
    } 
    return false;
  }
  
  Integer readIntegerObject() {
    int i = readInteger();
    return ValuePool.getInt(i);
  }
  
  int readInteger() {
    boolean bool = false;
    if (this.token.tokenType == 814) {
      bool = true;
      read();
    } 
    checkIsValue();
    if (bool && this.token.dataType.typeCode == 25 && ((Number)this.token.tokenValue).longValue() == 2147483648L) {
      read();
      return Integer.MIN_VALUE;
    } 
    if (this.token.dataType.typeCode != 4)
      throw Error.error(5563); 
    int i = ((Number)this.token.tokenValue).intValue();
    if (bool)
      i = -i; 
    read();
    return i;
  }
  
  long readBigint() {
    boolean bool = false;
    if (this.token.tokenType == 814) {
      bool = true;
      read();
    } 
    checkIsValue();
    if (bool && this.token.dataType.typeCode == 2 && LONG_MAX_VALUE_INCREMENT.equals(this.token.tokenValue)) {
      read();
      return Long.MIN_VALUE;
    } 
    if (this.token.dataType.typeCode != 4 && this.token.dataType.typeCode != 25)
      throw Error.error(5563); 
    long l = ((Number)this.token.tokenValue).longValue();
    if (bool)
      l = -l; 
    read();
    return l;
  }
  
  Expression readDateTimeIntervalLiteral(Session paramSession) {
    boolean bool;
    int i = getPosition();
    switch (this.token.tokenType) {
      case 72:
        read();
        if (this.token.tokenType == 869 && this.token.dataType.typeCode == 1) {
          String str = this.token.tokenString;
          read();
          TimestampData timestampData = this.scanner.newDate(str);
          return new ExpressionValue(timestampData, (Type)Type.SQL_DATE);
        } 
        rewind(i);
        return null;
      case 281:
        read();
        if (this.token.tokenType == 869 && this.token.dataType.typeCode == 1) {
          String str = this.token.tokenString;
          read();
          TimeData timeData = this.scanner.newTime(str);
          Type type = this.scanner.dateTimeType;
          return new ExpressionValue(timeData, type);
        } 
        rewind(i);
        return null;
      case 282:
        read();
        if (this.token.tokenType == 869 && this.token.dataType.typeCode == 1) {
          String str = this.token.tokenString;
          read();
          TimestampData timestampData = this.scanner.newTimestamp(str);
          Type type = this.scanner.dateTimeType;
          return new ExpressionValue(timestampData, type);
        } 
        rewind(i);
        return null;
      case 140:
        bool = false;
        read();
        if (this.token.tokenType == 814) {
          read();
          bool = true;
        } else if (this.token.tokenType == 817) {
          read();
        } 
        if (this.token.tokenType == 869) {
          String str = this.token.tokenString;
          if (this.token.dataType.typeCode == 4 || this.token.dataType.typeCode == 1) {
            read();
            IntervalType intervalType = readIntervalType(false);
            Object object = this.scanner.newInterval(str, intervalType);
            intervalType = (IntervalType)this.scanner.dateTimeType;
            if (bool)
              object = intervalType.negate(object); 
            return new ExpressionValue(object, (Type)intervalType);
          } 
        } 
        rewind(i);
        return null;
    } 
    throw Error.runtimeError(201, "ParserBase");
  }
  
  IntervalType readIntervalType(boolean paramBoolean) {
    int i = -1;
    int j = -1;
    int m = this.token.tokenType;
    int k = m;
    read();
    if (this.token.tokenType == 816) {
      read();
      i = readInteger();
      if (i <= 0)
        throw Error.error(5592); 
      if (this.token.tokenType == 804) {
        if (k != 250)
          throw unexpectedToken(); 
        read();
        j = readInteger();
        if (j < 0)
          throw Error.error(5592); 
      } 
      readThis(802);
    } 
    if (this.token.tokenType == 285) {
      read();
      m = this.token.tokenType;
      read();
    } 
    if (this.token.tokenType == 816) {
      if (m != 250 || m == k)
        throw unexpectedToken(); 
      read();
      j = readInteger();
      if (j < 0)
        throw Error.error(5592); 
      readThis(802);
    } 
    int n = ArrayUtil.find(Tokens.SQL_INTERVAL_FIELD_CODES, k);
    int i1 = ArrayUtil.find(Tokens.SQL_INTERVAL_FIELD_CODES, m);
    if (i == -1 && paramBoolean)
      if (n == 5) {
        i = 12;
      } else {
        i = 9;
      }  
    return IntervalType.getIntervalType(n, i1, i, j);
  }
  
  static int getExpressionType(int paramInt) {
    int i = expressionTypeMap.get(paramInt, -1);
    if (i == -1)
      throw Error.runtimeError(201, "ParserBase"); 
    return i;
  }
  
  HsqlException unexpectedToken(String paramString) {
    return Error.parseError(5581, paramString, this.scanner.getLineNumber());
  }
  
  HsqlException unexpectedTokenRequire(String paramString) {
    String str;
    if (this.token.tokenType == 872)
      return Error.parseError(5590, 1, this.scanner.getLineNumber(), new Object[] { "", paramString }); 
    if (this.token.charsetSchema != null) {
      str = this.token.charsetSchema;
    } else if (this.token.charsetName != null) {
      str = this.token.charsetName;
    } else if (this.token.namePrePrefix != null) {
      str = this.token.namePrePrefix;
    } else if (this.token.namePrefix != null) {
      str = this.token.namePrefix;
    } else {
      str = this.token.tokenString;
    } 
    return Error.parseError(5581, 1, this.scanner.getLineNumber(), new Object[] { str, paramString });
  }
  
  HsqlException unexpectedToken() {
    String str;
    if (this.token.tokenType == 872)
      return Error.parseError(5590, null, this.scanner.getLineNumber()); 
    if (this.token.charsetSchema != null) {
      str = this.token.charsetSchema;
    } else if (this.token.charsetName != null) {
      str = this.token.charsetName;
    } else if (this.token.namePrePrefix != null) {
      str = this.token.namePrePrefix;
    } else if (this.token.namePrefix != null) {
      str = this.token.namePrefix;
    } else {
      str = this.token.tokenString;
    } 
    return Error.parseError(5581, str, this.scanner.getLineNumber());
  }
  
  HsqlException tooManyIdentifiers() {
    String str;
    if (this.token.namePrePrePrefix != null) {
      str = this.token.namePrePrePrefix;
    } else if (this.token.namePrePrefix != null) {
      str = this.token.namePrePrefix;
    } else if (this.token.namePrefix != null) {
      str = this.token.namePrefix;
    } else {
      str = this.token.tokenString;
    } 
    return Error.parseError(5551, str, this.scanner.getLineNumber());
  }
  
  HsqlException unsupportedFeature() {
    return Error.error(1551, this.token.tokenString);
  }
  
  HsqlException unsupportedFeature(String paramString) {
    return Error.error(1551, paramString);
  }
  
  public Number convertToNumber(String paramString, NumberType paramNumberType) {
    return this.scanner.convertToNumber(paramString, paramNumberType);
  }
  
  static {
    expressionTypeMap.put(396, 40);
    expressionTypeMap.put(809, 43);
    expressionTypeMap.put(812, 44);
    expressionTypeMap.put(810, 41);
    expressionTypeMap.put(813, 45);
    expressionTypeMap.put(815, 46);
    expressionTypeMap.put(52, 71);
    expressionTypeMap.put(163, 74);
    expressionTypeMap.put(168, 73);
    expressionTypeMap.put(274, 72);
    expressionTypeMap.put(16, 75);
    expressionTypeMap.put(97, 76);
    expressionTypeMap.put(6, 77);
    expressionTypeMap.put(258, 77);
    expressionTypeMap.put(269, 78);
    expressionTypeMap.put(270, 79);
    expressionTypeMap.put(309, 80);
    expressionTypeMap.put(310, 81);
    expressionTypeMap.put(9, 82);
    expressionTypeMap.put(688, 83);
    expressionTypeMap.put(606, 85);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\ParserBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */