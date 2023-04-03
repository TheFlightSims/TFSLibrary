package org.hsqldb;

import java.math.BigDecimal;
import java.util.Locale;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.CharArrayWriter;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.lib.java.JavaSystem;
import org.hsqldb.map.BitMap;
import org.hsqldb.map.ValuePool;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BinaryType;
import org.hsqldb.types.BitType;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.DTIType;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.IntervalMonthData;
import org.hsqldb.types.IntervalSecondData;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

public class Scanner {
  static final char[] specials = new char[] { 
      '"', '%', '&', '\'', '(', ')', '*', '+', ',', '-', 
      '.', '/', '\\', ':', ';', '<', '=', '>', '?', '[', 
      ']', '^', '_', '|', '{', '}' };
  
  static final String[] multi = new String[] { 
      "??(", "??)", "<>", ">=", "<=", "||", "->", "::", "..", "--", 
      "/*", "*/" };
  
  static final char[] whitespace = new char[] { 
      '\t', '\n', '\013', '\f', '\r', ' ', '', ' ', ' ', ' ', 
      '᠎', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 
      ' ', ' ', ' ', ' ', '　', ' ', ' ' };
  
  static final OrderedIntHashSet whiteSpaceSet = new OrderedIntHashSet(32);
  
  String sqlString;
  
  int currentPosition;
  
  int tokenPosition;
  
  int limit;
  
  Token token = new Token();
  
  boolean nullAndBooleanAsValue;
  
  boolean backtickQuoting;
  
  private boolean hasNonSpaceSeparator;
  
  private int eolPosition;
  
  private int lineNumber;
  
  private int eolCode;
  
  private static final int maxPooledStringLength = ValuePool.getMaxStringLength();
  
  char[] charBuffer = new char[256];
  
  CharArrayWriter charWriter = new CharArrayWriter(this.charBuffer);
  
  byte[] byteBuffer = new byte[256];
  
  HsqlByteArrayOutputStream byteOutputStream = new HsqlByteArrayOutputStream(this.byteBuffer);
  
  private String intervalString;
  
  private int intervalPosition;
  
  private int intervalPrecision;
  
  private int fractionPrecision;
  
  Type dateTimeType;
  
  public Scanner() {}
  
  public Scanner(Database paramDatabase) {
    if (paramDatabase.sqlSyntaxMys)
      this.backtickQuoting = true; 
  }
  
  Scanner(String paramString) {
    reset(paramString);
  }
  
  public void reset(String paramString) {
    this.sqlString = paramString;
    this.currentPosition = 0;
    this.tokenPosition = 0;
    this.limit = this.sqlString.length();
    this.hasNonSpaceSeparator = false;
    this.eolPosition = -1;
    this.lineNumber = 1;
    this.token.reset();
    this.token.tokenType = 873;
  }
  
  void resetState() {
    this.tokenPosition = this.currentPosition;
    this.token.reset();
  }
  
  public void setNullAndBooleanAsValue() {
    this.nullAndBooleanAsValue = true;
  }
  
  public void scanNext() {
    // Byte code:
    //   0: aload_0
    //   1: getfield currentPosition : I
    //   4: aload_0
    //   5: getfield limit : I
    //   8: if_icmpne -> 26
    //   11: aload_0
    //   12: invokevirtual resetState : ()V
    //   15: aload_0
    //   16: getfield token : Lorg/hsqldb/Token;
    //   19: sipush #872
    //   22: putfield tokenType : I
    //   25: return
    //   26: aload_0
    //   27: invokevirtual scanSeparator : ()Z
    //   30: ifeq -> 33
    //   33: aload_0
    //   34: getfield currentPosition : I
    //   37: aload_0
    //   38: getfield limit : I
    //   41: if_icmpne -> 59
    //   44: aload_0
    //   45: invokevirtual resetState : ()V
    //   48: aload_0
    //   49: getfield token : Lorg/hsqldb/Token;
    //   52: sipush #872
    //   55: putfield tokenType : I
    //   58: return
    //   59: aload_0
    //   60: getfield token : Lorg/hsqldb/Token;
    //   63: getfield isDelimiter : Z
    //   66: ifne -> 73
    //   69: iconst_1
    //   70: goto -> 74
    //   73: iconst_0
    //   74: istore_1
    //   75: aload_0
    //   76: invokevirtual scanToken : ()V
    //   79: iload_1
    //   80: ifeq -> 93
    //   83: aload_0
    //   84: getfield token : Lorg/hsqldb/Token;
    //   87: getfield isDelimiter : Z
    //   90: ifne -> 93
    //   93: aload_0
    //   94: getfield token : Lorg/hsqldb/Token;
    //   97: getfield isMalformed : Z
    //   100: ifeq -> 122
    //   103: aload_0
    //   104: getfield token : Lorg/hsqldb/Token;
    //   107: aload_0
    //   108: aload_0
    //   109: getfield tokenPosition : I
    //   112: aload_0
    //   113: getfield currentPosition : I
    //   116: invokevirtual getPart : (II)Ljava/lang/String;
    //   119: putfield fullString : Ljava/lang/String;
    //   122: return
  }
  
  public void scanEnd() {
    if (this.currentPosition == this.limit) {
      resetState();
      this.token.tokenType = 872;
    } 
  }
  
  public Token getToken() {
    return this.token;
  }
  
  public String getString() {
    return this.token.tokenString;
  }
  
  public int getTokenType() {
    return this.token.tokenType;
  }
  
  public Object getValue() {
    return this.token.tokenValue;
  }
  
  public Type getDataType() {
    return this.token.dataType;
  }
  
  public int getLineNumber() {
    return this.lineNumber;
  }
  
  int getTokenPosition() {
    return this.tokenPosition;
  }
  
  int getPosition() {
    return this.tokenPosition;
  }
  
  void position(int paramInt) {
    this.currentPosition = this.tokenPosition = paramInt;
  }
  
  String getPart(int paramInt1, int paramInt2) {
    return this.sqlString.substring(paramInt1, paramInt2);
  }
  
  private int charAt(int paramInt) {
    return (paramInt >= this.limit) ? -1 : this.sqlString.charAt(paramInt);
  }
  
  void scanBinaryString() {
    this.byteOutputStream.reset(this.byteBuffer);
    while (true) {
      scanBinaryStringPart();
      if (this.token.isMalformed)
        return; 
      if (scanSeparator() && charAt(this.currentPosition) == 39)
        continue; 
      break;
    } 
    this.token.tokenValue = new BinaryData(this.byteOutputStream.toByteArray(), false);
    this.byteOutputStream.reset(this.byteBuffer);
  }
  
  static int getHexValue(int paramInt) {
    if (paramInt >= 48 && paramInt <= 57) {
      paramInt -= 48;
    } else if (paramInt > 122) {
      paramInt = 16;
    } else if (paramInt >= 97) {
      paramInt -= 87;
    } else if (paramInt > 90) {
      paramInt = 16;
    } else if (paramInt >= 65) {
      paramInt -= 55;
    } else {
      paramInt = -1;
    } 
    return paramInt;
  }
  
  public void scanBinaryStringWithQuote() {
    resetState();
    scanSeparator();
    if (charAt(this.currentPosition) != 39) {
      this.token.tokenType = 880;
      this.token.isMalformed = true;
      return;
    } 
    scanBinaryString();
  }
  
  void scanBinaryStringPart() {
    boolean bool1 = false;
    boolean bool2 = true;
    byte b = 0;
    this.currentPosition++;
    while (this.currentPosition < this.limit) {
      char c = this.sqlString.charAt(this.currentPosition);
      if (c != ' ') {
        if (c == '\'') {
          bool1 = true;
          this.currentPosition++;
          break;
        } 
        int i = getHexValue(c);
        if (i == -1) {
          this.token.tokenType = 880;
          this.token.isMalformed = true;
          return;
        } 
        if (bool2) {
          b = (byte)(i << 4);
          bool2 = false;
        } else {
          b = (byte)(b + (byte)i);
          this.byteOutputStream.writeByte(b);
          bool2 = true;
        } 
      } 
      this.currentPosition++;
    } 
    if (!bool2) {
      this.token.tokenType = 880;
      this.token.isMalformed = true;
      return;
    } 
    if (!bool1) {
      this.token.tokenType = 880;
      this.token.isMalformed = true;
      return;
    } 
  }
  
  void scanBitString() {
    BitMap bitMap = new BitMap(32, true);
    while (true) {
      scanBitStringPart(bitMap);
      if (this.token.isMalformed)
        return; 
      if (scanSeparator() && charAt(this.currentPosition) == 39)
        continue; 
      break;
    } 
    this.token.tokenValue = BinaryData.getBitData(bitMap.getBytes(), bitMap.size());
  }
  
  public void scanBitStringWithQuote() {
    resetState();
    scanSeparator();
    if (charAt(this.currentPosition) != 39) {
      this.token.tokenType = 879;
      this.token.isMalformed = true;
      return;
    } 
    scanBitString();
  }
  
  void scanBitStringPart(BitMap paramBitMap) {
    boolean bool = false;
    int i = paramBitMap.size();
    this.currentPosition++;
    while (this.currentPosition < this.limit) {
      char c = this.sqlString.charAt(this.currentPosition);
      if (c != ' ') {
        if (c == '\'') {
          bool = true;
          this.currentPosition++;
          break;
        } 
        if (c == '0') {
          i++;
        } else if (c == '1') {
          paramBitMap.set(i);
          i++;
        } else {
          this.token.tokenType = 879;
          this.token.isMalformed = true;
          return;
        } 
      } 
      this.currentPosition++;
    } 
    if (!bool) {
      this.token.tokenType = 879;
      this.token.isMalformed = true;
      return;
    } 
    paramBitMap.setSize(i);
  }
  
  void convertUnicodeString(int paramInt) {
    this.charWriter.reset(this.charBuffer);
    int i = 0;
    while (true) {
      int j = this.token.tokenString.indexOf(paramInt, i);
      if (j < 0)
        j = this.token.tokenString.length(); 
      this.charWriter.write(this.token.tokenString, i, j - i);
      if (j == this.token.tokenString.length()) {
        this.token.tokenValue = this.charWriter.toString();
        return;
      } 
      if (++j == this.token.tokenString.length()) {
        this.token.tokenType = 881;
        this.token.isMalformed = true;
        return;
      } 
      if (this.token.tokenString.charAt(j) == paramInt) {
        this.charWriter.write(paramInt);
        i = ++j;
        continue;
      } 
      if (j > this.token.tokenString.length() - 4) {
        this.token.tokenType = 881;
        this.token.isMalformed = true;
        return;
      } 
      byte b1 = 4;
      byte b2 = 0;
      int k = 0;
      if (this.token.tokenString.charAt(j) == '+') {
        if (++j > this.token.tokenString.length() - 6) {
          this.token.tokenType = 881;
          this.token.isMalformed = true;
          return;
        } 
        b2 = 2;
        b1 = 8;
      } 
      i = j;
      while (b2 < b1) {
        char c = this.token.tokenString.charAt(i++);
        int m = getHexValue(c);
        if (m == -1) {
          this.token.tokenType = 881;
          this.token.isMalformed = true;
          return;
        } 
        k |= m << (b1 - b2 - 1) * 4;
        b2++;
      } 
      if (b1 == 8)
        this.charWriter.write(k >>> 16); 
      this.charWriter.write(k & k & 0xFFFF);
    } 
  }
  
  public boolean scanSpecialIdentifier(String paramString) {
    int i = paramString.length();
    if (this.limit - this.currentPosition < i)
      return false; 
    byte b = 0;
    while (b < i) {
      char c = paramString.charAt(b);
      if (c == this.sqlString.charAt(this.currentPosition + b) || c == Character.toUpperCase(this.sqlString.charAt(this.currentPosition + b))) {
        b++;
        continue;
      } 
      return false;
    } 
    this.currentPosition += i;
    return true;
  }
  
  private int scanEscapeDefinition() {
    int i = charAt(this.currentPosition);
    if (i == 39) {
      this.currentPosition++;
      if (!scanWhitespace()) {
        i = charAt(this.currentPosition);
        if (getHexValue(i) == -1 && i != 43 && i != 39 && i != 34) {
          int j = i;
          this.currentPosition++;
          i = charAt(this.currentPosition);
          if (i == 39) {
            this.currentPosition++;
            return j;
          } 
        } 
      } 
    } 
    return -1;
  }
  
  private void scanUnicodeString() {
    int i = 92;
    scanCharacterString();
    scanSeparator();
    int j = charAt(this.currentPosition);
    if ((j == 117 || j == 85) && scanSpecialIdentifier("UESCAPE")) {
      scanSeparator();
      i = scanEscapeDefinition();
      if (i == -1) {
        this.token.tokenType = 884;
        this.token.isMalformed = true;
        return;
      } 
    } 
    convertUnicodeString(i);
  }
  
  private boolean scanUnicodeIdentifier() {
    int i = 92;
    scanStringPart('"');
    if (this.token.isMalformed)
      return false; 
    this.token.tokenString = this.charWriter.toString();
    int j = charAt(this.currentPosition);
    if ((j == 117 || j == 85) && scanSpecialIdentifier("UESCAPE")) {
      scanSeparator();
      i = scanEscapeDefinition();
      if (i == -1) {
        this.token.tokenType = 884;
        this.token.isMalformed = true;
        return false;
      } 
    } 
    convertUnicodeString(i);
    return !this.token.isMalformed;
  }
  
  boolean shiftPrefixes() {
    if (this.token.namePrePrePrefix != null)
      return false; 
    this.token.namePrePrePrefix = this.token.namePrePrefix;
    this.token.isDelimitedPrePrePrefix = this.token.isDelimitedPrePrefix;
    this.token.namePrePrefix = this.token.namePrefix;
    this.token.isDelimitedPrePrefix = this.token.isDelimitedPrefix;
    this.token.namePrefix = this.token.tokenString;
    this.token.isDelimitedPrefix = (this.token.tokenType == 871);
    return true;
  }
  
  private void scanIdentifierChain() {
    int i = charAt(this.currentPosition);
    switch (i) {
      case 96:
        if (this.backtickQuoting) {
          this.charWriter.reset(this.charBuffer);
          scanStringPart('`');
          if (this.token.isMalformed)
            return; 
          this.token.tokenType = 871;
          this.token.tokenString = this.charWriter.toString();
          this.token.isDelimiter = true;
        } 
        break;
      case 34:
        this.charWriter.reset(this.charBuffer);
        scanStringPart('"');
        if (this.token.isMalformed)
          return; 
        this.token.tokenType = 871;
        this.token.tokenString = this.charWriter.toString();
        this.token.isDelimiter = true;
        break;
      case 85:
      case 117:
        if (charAt(this.currentPosition + 1) == 38 && charAt(this.currentPosition + 1) == 34) {
          this.currentPosition += 3;
          boolean bool1 = scanUnicodeIdentifier();
          if (!bool1)
            return; 
          this.token.tokenType = 871;
          this.token.isDelimiter = false;
          break;
        } 
      default:
        bool = scanUndelimitedIdentifier();
        if (!bool)
          return; 
        this.token.tokenType = 870;
        this.token.isDelimiter = false;
        break;
    } 
    boolean bool = scanWhitespace();
    i = charAt(this.currentPosition);
    if (i == 46) {
      if (bool) {
        int j = charAt(this.currentPosition + 1);
        if (j >= 48 && j <= 57)
          return; 
      } 
      this.currentPosition++;
      scanWhitespace();
      i = charAt(this.currentPosition);
      if (i == 42) {
        this.currentPosition++;
        shiftPrefixes();
        this.token.tokenString = "*";
        this.token.tokenType = 801;
      } else {
        shiftPrefixes();
        scanIdentifierChain();
      } 
    } 
  }
  
  public boolean scanUndelimitedIdentifier() {
    if (this.currentPosition == this.limit)
      return false; 
    char c = this.sqlString.charAt(this.currentPosition);
    boolean bool = (c == '_' || c == '$') ? true : false;
    if (!bool && !Character.isLetter(c)) {
      this.token.tokenString = Character.toString(c);
      this.token.tokenType = -1;
      this.token.isMalformed = true;
      return false;
    } 
    int i;
    for (i = this.currentPosition + 1; i < this.limit; i++) {
      char c1 = this.sqlString.charAt(i);
      if (c1 == '$') {
        bool = true;
        continue;
      } 
      if (c1 == '_' || Character.isLetterOrDigit(c1))
        continue; 
    } 
    this.token.tokenString = this.sqlString.substring(this.currentPosition, i).toUpperCase(Locale.ENGLISH);
    this.currentPosition = i;
    if (this.nullAndBooleanAsValue) {
      int j = this.currentPosition - this.tokenPosition;
      if (j == 4 || j == 5)
        switch (c) {
          case 'T':
          case 't':
            if ("TRUE".equals(this.token.tokenString)) {
              this.token.tokenString = "TRUE";
              this.token.tokenType = 869;
              this.token.tokenValue = Boolean.TRUE;
              this.token.dataType = (Type)Type.SQL_BOOLEAN;
              return false;
            } 
            break;
          case 'F':
          case 'f':
            if ("FALSE".equals(this.token.tokenString)) {
              this.token.tokenString = "FALSE";
              this.token.tokenType = 869;
              this.token.tokenValue = Boolean.FALSE;
              this.token.dataType = (Type)Type.SQL_BOOLEAN;
              return false;
            } 
            break;
          case 'N':
          case 'n':
            if ("NULL".equals(this.token.tokenString)) {
              this.token.tokenString = "NULL";
              this.token.tokenType = 869;
              this.token.tokenValue = null;
              return false;
            } 
            break;
        }  
    } 
    if (bool)
      this.token.hasIrregularChar = true; 
    return true;
  }
  
  void scanNumber() {
    boolean bool1 = false;
    boolean bool2 = false;
    int i = -1;
    this.token.tokenType = 869;
    this.token.dataType = (Type)Type.SQL_INTEGER;
    int j = this.currentPosition;
    while (this.currentPosition < this.limit) {
      String str;
      boolean bool = false;
      int k = charAt(this.currentPosition);
      switch (k) {
        case 48:
        case 49:
        case 50:
        case 51:
        case 52:
        case 53:
        case 54:
        case 55:
        case 56:
        case 57:
          bool1 = true;
          break;
        case 46:
          this.token.dataType = (Type)Type.SQL_NUMERIC;
          if (bool2 || i != -1) {
            this.token.tokenString = this.sqlString.substring(j, this.currentPosition + 1);
            this.token.tokenType = 878;
            this.token.isMalformed = true;
            return;
          } 
          bool2 = true;
          break;
        case 69:
        case 101:
          this.token.dataType = (Type)Type.SQL_DOUBLE;
          if (i != -1 || !bool1) {
            this.token.tokenString = this.sqlString.substring(j, this.currentPosition + 1);
            this.token.tokenType = 878;
            this.token.isMalformed = true;
            return;
          } 
          bool2 = true;
          i = this.currentPosition;
          break;
        case 43:
        case 45:
          if (i != this.currentPosition - 1)
            bool = true; 
          break;
        case 71:
        case 75:
        case 77:
        case 80:
        case 84:
        case 103:
        case 107:
        case 109:
        case 112:
        case 116:
          if (!bool1 || bool2) {
            this.token.tokenType = 878;
            this.token.isMalformed = true;
            return;
          } 
          str = Character.toString((char)k).toUpperCase(Locale.ENGLISH);
          this.token.lobMultiplierType = Tokens.getNonKeywordID(str, 878);
          if (this.token.lobMultiplierType == 878) {
            this.token.tokenType = 878;
            this.token.isMalformed = true;
            return;
          } 
          try {
            this.token.tokenValue = ValuePool.getInt(Integer.parseInt(this.sqlString.substring(j, this.currentPosition)));
            this.token.tokenType = 876;
            this.currentPosition++;
            this.token.fullString = getPart(this.tokenPosition, this.currentPosition);
          } catch (NumberFormatException numberFormatException) {
            this.token.tokenType = 878;
            this.token.isMalformed = true;
          } 
          return;
        default:
          bool = true;
          break;
      } 
      if (bool)
        break; 
      this.currentPosition++;
    } 
    this.token.tokenString = this.sqlString.substring(j, this.currentPosition);
    switch (this.token.dataType.typeCode) {
      case 4:
        if (this.token.tokenString.length() < 11)
          try {
            this.token.tokenValue = ValuePool.getInt(Integer.parseInt(this.token.tokenString));
            return;
          } catch (Exception exception) {} 
        if (this.token.tokenString.length() < 20)
          try {
            this.token.dataType = (Type)Type.SQL_BIGINT;
            this.token.tokenValue = ValuePool.getLong(Long.parseLong(this.token.tokenString));
            return;
          } catch (Exception exception) {} 
        this.token.dataType = (Type)Type.SQL_NUMERIC;
      case 2:
        try {
          BigDecimal bigDecimal = new BigDecimal(this.token.tokenString);
          this.token.tokenValue = bigDecimal;
          this.token.dataType = (Type)NumberType.getNumberType(2, JavaSystem.precision(bigDecimal), bigDecimal.scale());
        } catch (Exception exception) {
          this.token.tokenType = 878;
          this.token.isMalformed = true;
          return;
        } 
        return;
      case 8:
        try {
          double d = Double.parseDouble(this.token.tokenString);
          long l = Double.doubleToLongBits(d);
          this.token.tokenValue = ValuePool.getDouble(l);
        } catch (Exception exception) {
          this.token.tokenType = 878;
          this.token.isMalformed = true;
          return;
        } 
        return;
    } 
  }
  
  boolean scanSeparator() {
    boolean bool = false;
    while (true) {
      boolean bool1 = scanWhitespace();
      bool |= bool1;
      if (scanCommentAsInlineSeparator()) {
        bool = true;
        this.hasNonSpaceSeparator = true;
        continue;
      } 
      return bool;
    } 
  }
  
  boolean scanCommentAsInlineSeparator() {
    int i = charAt(this.currentPosition);
    if (i == 45 && charAt(this.currentPosition + 1) == 45) {
      int j = this.sqlString.indexOf('\r', this.currentPosition + 2);
      if (j == -1) {
        j = this.sqlString.indexOf('\n', this.currentPosition + 2);
      } else if (charAt(j + 1) == 10) {
        j++;
      } 
      if (j == -1) {
        this.currentPosition = this.limit;
      } else {
        this.currentPosition = j + 1;
      } 
      return true;
    } 
    if (i == 47 && charAt(this.currentPosition + 1) == 42) {
      int j = this.sqlString.indexOf("*/", this.currentPosition + 2);
      if (j == -1) {
        this.token.tokenString = this.sqlString.substring(this.currentPosition, this.currentPosition + 2);
        this.token.tokenType = 882;
        this.token.isMalformed = true;
        return false;
      } 
      this.currentPosition = j + 2;
      return true;
    } 
    return false;
  }
  
  public boolean scanWhitespace() {
    boolean bool = false;
    while (this.currentPosition < this.limit) {
      char c = this.sqlString.charAt(this.currentPosition);
      if (c == ' ') {
        bool = true;
      } else if (whiteSpaceSet.contains(c)) {
        this.hasNonSpaceSeparator = true;
        bool = true;
        setLineNumber(c);
      } else {
        break;
      } 
      this.currentPosition++;
    } 
    return bool;
  }
  
  private void setLineNumber(int paramInt) {
    if (paramInt == 13 || paramInt == 10) {
      if (this.currentPosition == this.eolPosition + 1) {
        if (paramInt != 10 || this.eolCode == paramInt)
          this.lineNumber++; 
      } else {
        this.lineNumber++;
      } 
      this.eolPosition = this.currentPosition;
      this.eolCode = paramInt;
    } 
  }
  
  void scanCharacterString() {
    this.charWriter.reset(this.charBuffer);
    while (true) {
      scanStringPart('\'');
      if (this.token.isMalformed)
        return; 
      if (scanSeparator() && charAt(this.currentPosition) == 39)
        continue; 
      break;
    } 
    this.token.tokenString = this.charWriter.toString();
    this.token.tokenValue = this.token.tokenString;
  }
  
  public void scanStringPart(char paramChar) {
    this.currentPosition++;
    while (true) {
      int i = this.sqlString.indexOf(paramChar, this.currentPosition);
      if (i < 0) {
        this.token.tokenString = this.sqlString.substring(this.currentPosition, this.limit);
        this.token.tokenType = (paramChar == '\'') ? 877 : 883;
        this.token.isMalformed = true;
        return;
      } 
      if (charAt(i + 1) == paramChar) {
        this.charWriter.write(this.sqlString, this.currentPosition, ++i - this.currentPosition);
        this.currentPosition = i + 1;
        continue;
      } 
      this.charWriter.write(this.sqlString, this.currentPosition, i - this.currentPosition);
      this.currentPosition = i + 1;
      return;
    } 
  }
  
  void scanToken() {
    int j;
    int i = charAt(this.currentPosition);
    resetState();
    this.token.tokenType = 870;
    switch (i) {
      case 91:
        this.token.tokenString = "[";
        this.token.tokenType = 811;
        this.currentPosition++;
        this.token.isDelimiter = true;
        return;
      case 93:
        this.token.tokenString = "]";
        this.token.tokenType = 820;
        this.currentPosition++;
        this.token.isDelimiter = true;
        return;
      case 40:
        this.token.tokenString = "(";
        this.token.tokenType = 816;
        this.currentPosition++;
        this.token.isDelimiter = true;
        return;
      case 41:
        this.token.tokenString = ")";
        this.token.tokenType = 802;
        this.currentPosition++;
        this.token.isDelimiter = true;
        return;
      case 44:
        this.token.tokenString = ",";
        this.token.tokenType = 804;
        this.currentPosition++;
        this.token.isDelimiter = true;
        return;
      case 42:
        this.token.tokenString = "*";
        this.token.tokenType = 801;
        this.currentPosition++;
        this.token.isDelimiter = true;
        return;
      case 61:
        this.token.tokenString = "=";
        this.token.tokenType = 396;
        this.currentPosition++;
        this.token.isDelimiter = true;
        return;
      case 59:
        this.token.tokenString = ";";
        this.token.tokenType = 821;
        this.currentPosition++;
        this.token.isDelimiter = true;
        return;
      case 43:
        this.token.tokenString = "+";
        this.token.tokenType = 817;
        this.currentPosition++;
        this.token.isDelimiter = true;
        return;
      case 58:
        if (charAt(this.currentPosition + 1) == 58) {
          this.currentPosition += 2;
          this.token.tokenString = "::";
          this.token.tokenType = 803;
          this.token.isDelimiter = true;
          return;
        } 
        this.token.tokenString = ":";
        this.token.tokenType = 803;
        this.currentPosition++;
        this.token.isDelimiter = true;
        return;
      case 63:
        if (charAt(this.currentPosition + 1) == 63) {
          if (charAt(this.currentPosition + 2) == 40) {
            this.token.tokenString = "[";
            this.token.tokenType = 811;
            this.currentPosition += 3;
            this.token.isDelimiter = true;
            return;
          } 
          if (charAt(this.currentPosition + 2) == 41) {
            this.token.tokenString = "]";
            this.token.tokenType = 820;
            this.currentPosition += 3;
            this.token.isDelimiter = true;
            return;
          } 
        } 
        this.token.tokenString = "?";
        this.token.tokenType = 818;
        this.currentPosition++;
        this.token.isDelimiter = true;
        return;
      case 33:
        if (charAt(this.currentPosition + 1) == 61) {
          this.token.tokenString = "<>";
          this.token.tokenType = 815;
          this.currentPosition += 2;
          this.token.isDelimiter = true;
          return;
        } 
        this.token.tokenString = this.sqlString.substring(this.currentPosition, this.currentPosition + 2);
        this.token.tokenType = -1;
        this.token.isDelimiter = true;
        return;
      case 60:
        if (charAt(this.currentPosition + 1) == 62) {
          this.token.tokenString = "<>";
          this.token.tokenType = 815;
          this.currentPosition += 2;
          this.token.isDelimiter = true;
          return;
        } 
        if (charAt(this.currentPosition + 1) == 61) {
          this.token.tokenString = "<=";
          this.token.tokenType = 813;
          this.currentPosition += 2;
          this.token.isDelimiter = true;
          return;
        } 
        this.token.tokenString = "<";
        this.token.tokenType = 812;
        this.currentPosition++;
        this.token.isDelimiter = true;
        return;
      case 62:
        if (charAt(this.currentPosition + 1) == 61) {
          this.token.tokenString = ">=";
          this.token.tokenType = 810;
          this.currentPosition += 2;
          this.token.isDelimiter = true;
          return;
        } 
        this.token.tokenString = ">";
        this.token.tokenType = 809;
        this.currentPosition++;
        this.token.isDelimiter = true;
        return;
      case 124:
        if (charAt(this.currentPosition + 1) == 124) {
          this.token.tokenString = "||";
          this.token.tokenType = 805;
          this.currentPosition += 2;
          this.token.isDelimiter = true;
          return;
        } 
        this.token.tokenString = this.sqlString.substring(this.currentPosition, this.currentPosition + 2);
        this.token.tokenType = -1;
        this.token.isDelimiter = true;
        return;
      case 47:
        if (charAt(this.currentPosition + 1) == 47) {
          int k = this.sqlString.indexOf('\r', this.currentPosition + 2);
          if (k == -1)
            k = this.sqlString.indexOf('\n', this.currentPosition + 2); 
          if (k == -1)
            k = this.limit; 
          this.token.tokenString = this.sqlString.substring(this.currentPosition + 2, k);
          this.token.tokenType = 874;
          this.token.isDelimiter = true;
          return;
        } 
        if (charAt(this.currentPosition + 1) == 42) {
          int k = this.sqlString.indexOf("*/", this.currentPosition + 2);
          if (k == -1) {
            this.token.tokenString = this.sqlString.substring(this.currentPosition, this.currentPosition + 2);
            this.token.tokenType = -1;
            this.token.isDelimiter = true;
            return;
          } 
          this.token.tokenString = this.sqlString.substring(this.currentPosition + 2, k);
          this.token.tokenType = 874;
          this.token.isDelimiter = true;
          return;
        } 
        this.token.tokenString = "/";
        this.token.tokenType = 806;
        this.currentPosition++;
        this.token.isDelimiter = true;
        return;
      case 45:
        if (charAt(this.currentPosition + 1) == 45) {
          int k = this.sqlString.indexOf('\r', this.currentPosition + 2);
          if (k == -1)
            k = this.sqlString.indexOf('\n', this.currentPosition + 2); 
          if (k == -1)
            k = this.limit; 
          this.token.tokenString = this.sqlString.substring(this.currentPosition + 2, k);
          this.token.tokenType = 874;
          this.token.isDelimiter = true;
          return;
        } 
        this.token.tokenString = "-";
        this.token.tokenType = 814;
        this.currentPosition++;
        this.token.isDelimiter = true;
        return;
      case 34:
        this.token.tokenType = 871;
        break;
      case 96:
        if (this.backtickQuoting)
          this.token.tokenType = 871; 
        break;
      case 39:
        scanCharacterString();
        if (this.token.isMalformed)
          return; 
        this.token.dataType = (Type)CharacterType.getCharacterType(1, this.token.tokenString.length());
        this.token.tokenType = 869;
        this.token.isDelimiter = true;
        return;
      case 88:
      case 120:
        if (charAt(this.currentPosition + 1) == 39) {
          this.currentPosition++;
          scanBinaryString();
          if (this.token.isMalformed)
            return; 
          this.token.dataType = (Type)BinaryType.getBinaryType(61, ((BinaryData)this.token.tokenValue).length(null));
          this.token.tokenType = 869;
          return;
        } 
        break;
      case 66:
      case 98:
        if (charAt(this.currentPosition + 1) == 39) {
          this.currentPosition++;
          scanBitString();
          if (this.token.isMalformed)
            return; 
          this.token.dataType = (Type)BitType.getBitType(14, ((BinaryData)this.token.tokenValue).bitLength(null));
          this.token.tokenType = 869;
          return;
        } 
        break;
      case 78:
      case 110:
        if (charAt(this.currentPosition + 1) == 39) {
          this.currentPosition++;
          scanCharacterString();
          if (this.token.isMalformed)
            return; 
          this.token.dataType = (Type)CharacterType.getCharacterType(1, this.token.tokenString.length());
          this.token.tokenType = 869;
          return;
        } 
        break;
      case 85:
      case 117:
        if (charAt(this.currentPosition + 1) == 38 && charAt(this.currentPosition + 2) == 39) {
          this.currentPosition += 2;
          this.token.dataType = (Type)Type.SQL_CHAR;
          this.token.tokenType = 869;
          scanUnicodeString();
          if (this.token.isMalformed)
            return; 
          this.token.dataType = (Type)CharacterType.getCharacterType(1, ((String)this.token.tokenValue).length());
          return;
        } 
        break;
      case 95:
        j = this.currentPosition;
        this.currentPosition++;
        scanIdentifierChain();
        if (this.token.isMalformed)
          return; 
        if (this.token.tokenType != 870) {
          this.token.tokenType = 877;
          this.token.isMalformed = true;
          return;
        } 
        scanSeparator();
        if (charAt(this.currentPosition) == 39) {
          if (this.token.namePrePrefix != null) {
            this.token.tokenType = 877;
            this.token.isMalformed = true;
            return;
          } 
          this.token.charsetSchema = this.token.namePrefix;
          this.token.charsetName = this.token.tokenString;
          scanCharacterString();
          this.token.tokenType = 869;
          this.token.dataType = (Type)CharacterType.getCharacterType(1, this.token.tokenString.length());
          this.token.isDelimiter = true;
          return;
        } 
        position(j);
        resetState();
        break;
      case 46:
      case 48:
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
        this.token.tokenType = 869;
        scanNumber();
        return;
    } 
    scanIdentifierChain();
    setIdentifierProperties();
  }
  
  private void setIdentifierProperties() {
    if (this.token.tokenType == 870) {
      this.token.isUndelimitedIdentifier = true;
      if (this.token.namePrefix == null) {
        this.token.tokenType = Tokens.getKeywordID(this.token.tokenString, 870);
        if (this.token.tokenType == 870) {
          this.token.tokenType = Tokens.getNonKeywordID(this.token.tokenString, 870);
        } else {
          this.token.isReservedIdentifier = true;
          this.token.isCoreReservedIdentifier = Tokens.isCoreKeyword(this.token.tokenType);
        } 
      } 
    } else if (this.token.tokenType == 871) {
      this.token.isDelimitedIdentifier = true;
    } 
  }
  
  public boolean scanNull() {
    scanSeparator();
    int i = charAt(this.currentPosition);
    return ((i == 78 || i == 110) && scanSpecialIdentifier("NULL"));
  }
  
  private void scanNext(int paramInt) {
    scanNext();
    if (this.token.isMalformed)
      throw Error.error(paramInt); 
  }
  
  IntervalType scanIntervalType() {
    int i = -1;
    int j = -1;
    int m = this.token.tokenType;
    int k = m;
    scanNext(3406);
    if (this.token.tokenType == 816) {
      scanNext(3406);
      if (this.token.dataType == null || this.token.dataType.typeCode != 4)
        throw Error.error(3406); 
      i = ((Number)this.token.tokenValue).intValue();
      scanNext(3406);
      if (this.token.tokenType == 804) {
        if (k != 250)
          throw Error.error(3406); 
        scanNext(3406);
        if (this.token.dataType == null || this.token.dataType.typeCode != 4)
          throw Error.error(3406); 
        j = ((Number)this.token.tokenValue).intValue();
        scanNext(3406);
      } 
      if (this.token.tokenType != 802)
        throw Error.error(3406); 
      scanNext(3406);
    } 
    if (this.token.tokenType == 285) {
      scanNext(3406);
      m = this.token.tokenType;
      scanNext(3406);
    } 
    if (this.token.tokenType == 816) {
      if (m != 250 || m == k)
        throw Error.error(3406); 
      scanNext(3406);
      if (this.token.dataType == null || this.token.dataType.typeCode != 4)
        throw Error.error(3406); 
      j = ((Number)this.token.tokenValue).intValue();
      scanNext(3406);
      if (this.token.tokenType != 802)
        throw Error.error(3406); 
      scanNext(3406);
    } 
    int n = ArrayUtil.find(Tokens.SQL_INTERVAL_FIELD_CODES, k);
    int i1 = ArrayUtil.find(Tokens.SQL_INTERVAL_FIELD_CODES, m);
    return IntervalType.getIntervalType(n, i1, i, j);
  }
  
  public TimestampData newDate(String paramString) {
    this.intervalPosition = 0;
    this.fractionPrecision = 0;
    this.dateTimeType = null;
    this.intervalString = paramString;
    scanDateParts(2);
    if (this.intervalPosition != paramString.length())
      throw Error.error(3407); 
    long l = HsqlDateTime.getDateSeconds(paramString);
    return new TimestampData(l);
  }
  
  public TimestampData newTimestamp(String paramString) {
    long l2;
    long l1 = 0L;
    int i = 0;
    int j = paramString.length();
    boolean bool1 = false;
    this.intervalPosition = 0;
    this.fractionPrecision = 0;
    this.dateTimeType = null;
    this.intervalString = paramString;
    scanDateParts(5);
    try {
      l2 = HsqlDateTime.getTimestampSeconds(paramString.substring(0, this.intervalPosition));
    } catch (Throwable throwable) {
      throw Error.error(3407);
    } 
    i = scanIntervalFraction(9);
    int k = this.intervalPosition;
    boolean bool = scanIntervalSign();
    if (bool || k != this.intervalPosition) {
      l1 = scanIntervalValue(Type.SQL_INTERVAL_HOUR_TO_MINUTE);
      bool1 = true;
      if (bool)
        l1 = -l1; 
    } 
    if (l1 >= DTIType.yearToSecondFactors[2] || l1 > 50400L || -l1 > 50400L)
      throw Error.error(3409); 
    if (this.intervalPosition != j)
      throw Error.error(3407); 
    byte b = bool1 ? 95 : 93;
    this.dateTimeType = (Type)DateTimeType.getDateTimeType(b, this.fractionPrecision);
    if (bool1)
      l2 -= l1; 
    return new TimestampData(l2, i, (int)l1);
  }
  
  void scanDateParts(int paramInt) {
    byte[] arrayOfByte = DTIType.yearToSecondSeparators;
    int i = this.intervalPosition;
    byte b1 = 0;
    byte b2 = 0;
    while (b1 <= paramInt) {
      boolean bool = false;
      if (i == this.intervalString.length()) {
        if (b1 == paramInt) {
          bool = true;
        } else {
          throw Error.error(3407);
        } 
      } else {
        char c = this.intervalString.charAt(i);
        if (c >= '0' && c <= '9') {
          b2++;
          i++;
        } else if (c == arrayOfByte[b1]) {
          bool = true;
          if (b1 != paramInt)
            i++; 
        } else if (b1 == paramInt) {
          bool = true;
        } else {
          throw Error.error(3407);
        } 
      } 
      if (bool) {
        if (b1 == 0) {
          if (b2 != 4)
            throw Error.error(3407); 
        } else if (b2 == 0 || b2 > 2) {
          throw Error.error(3407);
        } 
        b1++;
        b2 = 0;
        if (i == this.intervalString.length())
          break; 
      } 
    } 
    this.intervalPosition = i;
  }
  
  public TimeData newTime(String paramString) {
    this.intervalPosition = 0;
    this.fractionPrecision = 0;
    this.dateTimeType = null;
    this.intervalString = paramString;
    long l1 = scanIntervalValue(Type.SQL_INTERVAL_HOUR_TO_SECOND);
    int i = scanIntervalFraction(9);
    long l2 = 0L;
    int j = this.intervalPosition;
    boolean bool = false;
    boolean bool1 = scanIntervalSign();
    if (j != this.intervalPosition) {
      l2 = scanIntervalValue(Type.SQL_INTERVAL_HOUR_TO_MINUTE);
      bool = true;
    } 
    if (this.intervalPosition != paramString.length())
      throw Error.error(3409); 
    if (l1 >= DTIType.yearToSecondFactors[2])
      throw Error.error(3408); 
    if (l2 > 50400L)
      throw Error.error(3409); 
    if (bool1)
      l2 = -l2; 
    byte b = bool ? 94 : 92;
    this.dateTimeType = (Type)DateTimeType.getDateTimeType(b, this.fractionPrecision);
    if (bool)
      l1 -= l2; 
    return new TimeData((int)l1, i, (int)l2);
  }
  
  public Object newInterval(String paramString, IntervalType paramIntervalType) {
    this.intervalPosition = 0;
    this.fractionPrecision = 0;
    this.intervalString = paramString;
    boolean bool = scanIntervalSign();
    long l = scanIntervalValue(paramIntervalType);
    int i = 0;
    if (paramIntervalType.endIntervalType == 106)
      i = scanIntervalFraction(paramIntervalType.scale); 
    if (this.intervalPosition != paramString.length())
      throw Error.error(3406); 
    if (bool) {
      l = -l;
      i = -i;
    } 
    this.dateTimeType = (Type)paramIntervalType;
    if (paramIntervalType.defaultPrecision)
      this.dateTimeType = (Type)IntervalType.getIntervalType(paramIntervalType.typeCode, paramIntervalType.startIntervalType, paramIntervalType.endIntervalType, this.intervalPrecision, this.fractionPrecision, false); 
    return (paramIntervalType.endPartIndex <= 1) ? new IntervalMonthData(l) : new IntervalSecondData(l, i);
  }
  
  public long scanIntervalValue(IntervalType paramIntervalType) {
    byte[] arrayOfByte = DTIType.yearToSecondSeparators;
    int[] arrayOfInt1 = DTIType.yearToSecondFactors;
    int[] arrayOfInt2 = DTIType.yearToSecondLimits;
    int i = paramIntervalType.startPartIndex;
    int j = paramIntervalType.endPartIndex;
    long l1 = 0L;
    long l2 = 0L;
    int k = this.intervalPosition;
    int m = i;
    byte b = 0;
    while (m <= j) {
      boolean bool = false;
      if (k == this.intervalString.length()) {
        if (m == j) {
          bool = true;
        } else {
          throw Error.error(3406);
        } 
      } else {
        char c = this.intervalString.charAt(k);
        if (c >= '0' && c <= '9') {
          int n = c - 48;
          l2 *= 10L;
          l2 += n;
          b++;
          k++;
        } else if (c == arrayOfByte[m]) {
          bool = true;
          if (m != j)
            k++; 
        } else if (m == j) {
          bool = true;
        } else {
          throw Error.error(3406);
        } 
      } 
      if (bool) {
        if (m == i) {
          if (!paramIntervalType.defaultPrecision && b > paramIntervalType.precision)
            throw Error.error(3435); 
          if (b == 0)
            throw Error.error(3406); 
          int n = arrayOfInt1[m];
          l1 += l2 * n;
          this.intervalPrecision = b;
        } else {
          if (l2 >= arrayOfInt2[m])
            throw Error.error(3435); 
          if (b == 0 || b > 2)
            throw Error.error(3406); 
          l1 += l2 * arrayOfInt1[m];
        } 
        m++;
        l2 = 0L;
        b = 0;
        if (k == this.intervalString.length())
          break; 
      } 
    } 
    this.intervalPosition = k;
    return l1;
  }
  
  boolean scanIntervalSign() {
    boolean bool = false;
    if (this.intervalPosition == this.intervalString.length())
      return false; 
    if (this.intervalString.charAt(this.intervalPosition) == '-') {
      bool = true;
      this.intervalPosition++;
    } else if (this.intervalString.charAt(this.intervalPosition) == '+') {
      this.intervalPosition++;
    } 
    return bool;
  }
  
  int scanIntervalFraction(int paramInt) {
    if (this.intervalPosition == this.intervalString.length())
      return 0; 
    if (this.intervalString.charAt(this.intervalPosition) != '.')
      return 0; 
    this.intervalPosition++;
    null = 0;
    byte b = 0;
    while (this.intervalPosition < this.intervalString.length()) {
      char c = this.intervalString.charAt(this.intervalPosition);
      if (c >= '0' && c <= '9') {
        int i = c - 48;
        null *= 10;
        null += i;
        this.intervalPosition++;
        if (++b == 9)
          break; 
      } 
    } 
    this.fractionPrecision = b;
    null *= DTIType.nanoScaleFactors[b];
    return DTIType.normaliseFraction(null, paramInt);
  }
  
  void scanIntervalSpaces() {
    while (this.intervalPosition < this.intervalString.length() && this.intervalString.charAt(this.intervalPosition) == ' ')
      this.intervalPosition++; 
  }
  
  public synchronized Number convertToNumber(String paramString, NumberType paramNumberType) {
    boolean bool = false;
    reset(paramString);
    resetState();
    scanWhitespace();
    scanToken();
    scanWhitespace();
    if (this.token.tokenType == 817) {
      scanToken();
      scanWhitespace();
    } else if (this.token.tokenType == 814) {
      bool = true;
      scanToken();
      scanWhitespace();
    } 
    if (!this.hasNonSpaceSeparator && this.token.tokenType == 869 && this.token.tokenValue instanceof Number) {
      Number number = (Number)this.token.tokenValue;
      Type type = this.token.dataType;
      if (bool)
        number = (Number)this.token.dataType.negate(number); 
      scanEnd();
      if (this.token.tokenType == 872)
        return (Number)paramNumberType.convertToType(null, number, type); 
    } 
    throw Error.error(3438);
  }
  
  public synchronized BinaryData convertToBinary(String paramString) {
    boolean bool = true;
    byte b = 0;
    reset(paramString);
    resetState();
    this.byteOutputStream.reset(this.byteBuffer);
    while (this.currentPosition < this.limit) {
      char c = this.sqlString.charAt(this.currentPosition);
      int i = getHexValue(c);
      if (i == -1) {
        this.token.tokenType = 880;
        this.token.isMalformed = true;
        break;
      } 
      if (bool) {
        b = (byte)(i << 4);
      } else {
        b = (byte)(b + (byte)i);
        this.byteOutputStream.writeByte(b);
      } 
      this.currentPosition++;
      bool = !bool ? true : false;
    } 
    if (!bool) {
      this.token.tokenType = 880;
      this.token.isMalformed = true;
    } 
    if (this.token.isMalformed)
      throw Error.error(3438); 
    BinaryData binaryData = new BinaryData(this.byteOutputStream.toByteArray(), false);
    this.byteOutputStream.reset(this.byteBuffer);
    return binaryData;
  }
  
  public synchronized BinaryData convertToBit(String paramString) {
    BitMap bitMap = new BitMap(32, true);
    byte b = 0;
    reset(paramString);
    resetState();
    this.byteOutputStream.reset(this.byteBuffer);
    while (this.currentPosition < this.limit) {
      char c = this.sqlString.charAt(this.currentPosition);
      if (c == '0') {
        b++;
      } else if (c == '1') {
        bitMap.set(b);
        b++;
      } else {
        this.token.tokenType = 879;
        this.token.isMalformed = true;
        throw Error.error(3438);
      } 
      this.currentPosition++;
    } 
    bitMap.setSize(b);
    return BinaryData.getBitData(bitMap.getBytes(), bitMap.size());
  }
  
  public synchronized Object convertToDatetimeInterval(SessionInterface paramSessionInterface, String paramString, DTIType paramDTIType) {
    TimestampData timestampData;
    TimeData timeData;
    IntervalType intervalType = null;
    int i = -1;
    char c = paramDTIType.isDateTimeType() ? '൏' : 'ൎ';
    reset(paramString);
    resetState();
    scanToken();
    scanWhitespace();
    switch (this.token.tokenType) {
      case 72:
      case 140:
      case 281:
      case 282:
        i = this.token.tokenType;
        scanToken();
        if (this.token.tokenType != 869 || this.token.dataType.typeCode != 1)
          throw Error.error(c); 
        paramString = this.token.tokenString;
        scanNext(3407);
        if (paramDTIType.isIntervalType())
          intervalType = scanIntervalType(); 
        if (this.token.tokenType != 872)
          throw Error.error(c); 
        break;
    } 
    switch (paramDTIType.typeCode) {
      case 91:
        if (i != -1 && i != 72)
          throw Error.error(c); 
        timestampData = newDate(paramString);
        return paramDTIType.convertToType(paramSessionInterface, timestampData, (Type)Type.SQL_DATE);
      case 92:
      case 94:
        if (i != -1 && i != 281)
          throw Error.error(c); 
        timeData = newTime(paramString);
        return paramDTIType.convertToType(paramSessionInterface, timeData, this.dateTimeType);
      case 93:
      case 95:
        if (i != -1 && i != 282)
          throw Error.error(c); 
        timestampData = newTimestamp(paramString);
        return paramDTIType.convertToType(paramSessionInterface, timestampData, this.dateTimeType);
    } 
    if (i != -1 && i != 140)
      throw Error.error(c); 
    if (paramDTIType.isIntervalType()) {
      Object object = newInterval(paramString, (IntervalType)paramDTIType);
      if (intervalType != null && (intervalType.startIntervalType != paramDTIType.startIntervalType || intervalType.endIntervalType != paramDTIType.endIntervalType))
        throw Error.error(c); 
      return paramDTIType.convertToType(paramSessionInterface, object, this.dateTimeType);
    } 
    throw Error.runtimeError(201, "Scanner");
  }
  
  static {
    for (byte b = 0; b < whitespace.length; b++)
      whiteSpaceSet.add(whitespace[b]); 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\Scanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */