package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.lib.StringUtil;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BlobData;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.ClobData;
import org.hsqldb.types.LobData;
import org.hsqldb.types.Type;

class Like implements Cloneable {
  private static final BinaryData maxByteValue = new BinaryData(new byte[] { Byte.MIN_VALUE }, false);
  
  private char[] cLike;
  
  private int[] wildCardType;
  
  private int iLen;
  
  private boolean isIgnoreCase;
  
  private int iFirstWildCard;
  
  private boolean isNull;
  
  int escapeChar;
  
  boolean hasCollation;
  
  static final int UNDERSCORE_CHAR = 1;
  
  static final int PERCENT_CHAR = 2;
  
  boolean isVariable = true;
  
  boolean isBinary = false;
  
  Type dataType;
  
  void setParams(boolean paramBoolean) {
    this.hasCollation = paramBoolean;
  }
  
  void setIgnoreCase(boolean paramBoolean) {
    this.isIgnoreCase = paramBoolean;
  }
  
  private Object getStartsWith() {
    if (this.iLen == 0)
      return this.isBinary ? BinaryData.zeroLengthBinary : ""; 
    StringBuffer stringBuffer = null;
    HsqlByteArrayOutputStream hsqlByteArrayOutputStream = null;
    if (this.isBinary) {
      hsqlByteArrayOutputStream = new HsqlByteArrayOutputStream();
    } else {
      stringBuffer = new StringBuffer();
    } 
    byte b;
    for (b = 0; b < this.iLen && this.wildCardType[b] == 0; b++) {
      if (this.isBinary) {
        hsqlByteArrayOutputStream.writeByte(this.cLike[b]);
      } else {
        stringBuffer.append(this.cLike[b]);
      } 
    } 
    return (b == 0) ? null : (this.isBinary ? new BinaryData(hsqlByteArrayOutputStream.toByteArray(), false) : stringBuffer.toString());
  }
  
  Boolean compare(Session paramSession, Object paramObject) {
    if (paramObject == null)
      return null; 
    if (this.isNull)
      return null; 
    if (this.isIgnoreCase)
      paramObject = ((CharacterType)this.dataType).upper(paramSession, paramObject); 
    int i = getLength(paramSession, paramObject);
    if (paramObject instanceof ClobData)
      paramObject = ((ClobData)paramObject).getChars(paramSession, 0L, (int)((ClobData)paramObject).length(paramSession)); 
    return compareAt(paramSession, paramObject, 0, 0, this.iLen, i, this.cLike, this.wildCardType) ? Boolean.TRUE : Boolean.FALSE;
  }
  
  char getChar(Session paramSession, Object paramObject, int paramInt) {
    char c;
    if (this.isBinary) {
      c = (char)((BlobData)paramObject).getBytes()[paramInt];
    } else if (paramObject instanceof char[]) {
      c = ((char[])paramObject)[paramInt];
    } else if (paramObject instanceof ClobData) {
      c = ((ClobData)paramObject).getChars(paramSession, paramInt, 1)[0];
    } else {
      c = ((String)paramObject).charAt(paramInt);
    } 
    return c;
  }
  
  int getLength(SessionInterface paramSessionInterface, Object paramObject) {
    int i;
    if (paramObject instanceof LobData) {
      i = (int)((LobData)paramObject).length(paramSessionInterface);
    } else {
      i = ((String)paramObject).length();
    } 
    return i;
  }
  
  private boolean compareAt(Session paramSession, Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4, char[] paramArrayOfchar, int[] paramArrayOfint) {
    while (paramInt1 < paramInt3) {
      switch (paramArrayOfint[paramInt1]) {
        case 0:
          if (paramInt2 >= paramInt4 || paramArrayOfchar[paramInt1] != getChar(paramSession, paramObject, paramInt2++))
            return false; 
          break;
        case 1:
          if (paramInt2++ >= paramInt4)
            return false; 
          break;
        case 2:
          if (++paramInt1 >= paramInt3)
            return true; 
          while (paramInt2 < paramInt4) {
            if (paramArrayOfchar[paramInt1] == getChar(paramSession, paramObject, paramInt2) && compareAt(paramSession, paramObject, paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfchar, paramArrayOfint))
              return true; 
            paramInt2++;
          } 
          return false;
      } 
      paramInt1++;
    } 
    return !(paramInt2 != paramInt4);
  }
  
  void setPattern(Session paramSession, Object paramObject1, Object paramObject2, boolean paramBoolean) {
    this.isNull = (paramObject1 == null);
    if (!paramBoolean) {
      this.escapeChar = -1;
    } else {
      if (paramObject2 == null) {
        this.isNull = true;
        return;
      } 
      int j = getLength(paramSession, paramObject2);
      if (j != 1) {
        if (this.isBinary)
          throw Error.error(3412); 
        throw Error.error(3439);
      } 
      this.escapeChar = getChar(paramSession, paramObject2, 0);
    } 
    if (this.isNull)
      return; 
    if (this.isIgnoreCase)
      paramObject1 = ((CharacterType)this.dataType).upper(null, paramObject1); 
    this.iLen = 0;
    this.iFirstWildCard = -1;
    int i = getLength(paramSession, paramObject1);
    this.cLike = new char[i];
    this.wildCardType = new int[i];
    boolean bool1 = false;
    boolean bool2 = false;
    byte b;
    for (b = 0; b < i; b++) {
      char c = getChar(paramSession, paramObject1, b);
      if (!bool1) {
        if (this.escapeChar == c) {
          bool1 = true;
          continue;
        } 
        if (c == '_') {
          this.wildCardType[this.iLen] = 1;
          if (this.iFirstWildCard == -1)
            this.iFirstWildCard = this.iLen; 
        } else if (c == '%') {
          if (bool2)
            continue; 
          bool2 = true;
          this.wildCardType[this.iLen] = 2;
          if (this.iFirstWildCard == -1)
            this.iFirstWildCard = this.iLen; 
        } else {
          bool2 = false;
        } 
      } else if (c == this.escapeChar || c == '_' || c == '%') {
        bool2 = false;
        bool1 = false;
      } else {
        throw Error.error(3458);
      } 
      this.cLike[this.iLen++] = c;
      continue;
    } 
    if (bool1)
      throw Error.error(3458); 
    for (b = 0; b < this.iLen - 1; b++) {
      if (this.wildCardType[b] == 2 && this.wildCardType[b + 1] == 1) {
        this.wildCardType[b] = 1;
        this.wildCardType[b + 1] = 2;
      } 
    } 
  }
  
  boolean isEquivalentToUnknownPredicate() {
    return (!this.isVariable && this.isNull);
  }
  
  boolean isEquivalentToEqualsPredicate() {
    return (!this.isVariable && this.iFirstWildCard == -1);
  }
  
  boolean isEquivalentToNotNullPredicate() {
    if (this.isVariable || this.isNull || this.iFirstWildCard == -1)
      return false; 
    for (byte b = 0; b < this.wildCardType.length; b++) {
      if (this.wildCardType[b] != 2)
        return false; 
    } 
    return true;
  }
  
  int getFirstWildCardIndex() {
    return this.iFirstWildCard;
  }
  
  Object getRangeLow() {
    return getStartsWith();
  }
  
  Object getRangeHigh(Session paramSession) {
    Object object = getStartsWith();
    return (object == null) ? null : (this.isBinary ? new BinaryData(paramSession, (BlobData)object, (BlobData)maxByteValue) : this.dataType.concat(paramSession, object, "￿"));
  }
  
  public String describe(Session paramSession) {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(toString()).append("[\n");
    stringBuffer.append("escapeChar=").append(this.escapeChar).append('\n');
    stringBuffer.append("isNull=").append(this.isNull).append('\n');
    stringBuffer.append("isIgnoreCase=").append(this.isIgnoreCase).append('\n');
    stringBuffer.append("iLen=").append(this.iLen).append('\n');
    stringBuffer.append("iFirstWildCard=").append(this.iFirstWildCard).append('\n');
    stringBuffer.append("cLike=");
    if (this.cLike != null)
      stringBuffer.append(StringUtil.arrayToString(this.cLike)); 
    stringBuffer.append('\n');
    stringBuffer.append("wildCardType=");
    if (this.wildCardType != null)
      stringBuffer.append(StringUtil.arrayToString(this.wildCardType)); 
    stringBuffer.append(']');
    return stringBuffer.toString();
  }
  
  public Like duplicate() {
    try {
      return (Like)clone();
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      throw Error.runtimeError(201, "Expression");
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\Like.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */