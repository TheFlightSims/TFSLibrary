package org.hsqldb;

import org.hsqldb.types.Type;

public class Token {
  String tokenString = "";
  
  int tokenType = -1;
  
  Type dataType;
  
  Object tokenValue;
  
  String namePrefix;
  
  String namePrePrefix;
  
  String namePrePrePrefix;
  
  String charsetSchema;
  
  String charsetName;
  
  String fullString;
  
  int lobMultiplierType = -1;
  
  boolean isDelimiter;
  
  boolean isDelimitedIdentifier;
  
  boolean isDelimitedPrefix;
  
  boolean isDelimitedPrePrefix;
  
  boolean isDelimitedPrePrePrefix;
  
  boolean isUndelimitedIdentifier;
  
  boolean hasIrregularChar;
  
  boolean isReservedIdentifier;
  
  boolean isCoreReservedIdentifier;
  
  boolean isHostParameter;
  
  boolean isMalformed;
  
  int position;
  
  Object expression;
  
  void reset() {
    this.tokenString = "";
    this.tokenType = -1;
    this.dataType = null;
    this.tokenValue = null;
    this.namePrefix = null;
    this.namePrePrefix = null;
    this.namePrePrePrefix = null;
    this.charsetSchema = null;
    this.charsetName = null;
    this.fullString = null;
    this.expression = null;
    this.lobMultiplierType = -1;
    this.isDelimiter = false;
    this.isDelimitedIdentifier = false;
    this.isDelimitedPrefix = false;
    this.isDelimitedPrePrefix = false;
    this.isDelimitedPrePrePrefix = false;
    this.isUndelimitedIdentifier = false;
    this.hasIrregularChar = false;
    this.isReservedIdentifier = false;
    this.isCoreReservedIdentifier = false;
    this.isHostParameter = false;
    this.isMalformed = false;
  }
  
  Token duplicate() {
    Token token = new Token();
    token.tokenString = this.tokenString;
    token.tokenType = this.tokenType;
    token.dataType = this.dataType;
    token.tokenValue = this.tokenValue;
    token.namePrefix = this.namePrefix;
    token.namePrePrefix = this.namePrePrefix;
    token.namePrePrePrefix = this.namePrePrePrefix;
    token.charsetSchema = this.charsetSchema;
    token.charsetName = this.charsetName;
    token.fullString = this.fullString;
    token.lobMultiplierType = this.lobMultiplierType;
    token.isDelimiter = this.isDelimiter;
    token.isDelimitedIdentifier = this.isDelimitedIdentifier;
    token.isDelimitedPrefix = this.isDelimitedPrefix;
    token.isDelimitedPrePrefix = this.isDelimitedPrePrefix;
    token.isDelimitedPrePrePrefix = this.isDelimitedPrePrePrefix;
    token.isUndelimitedIdentifier = this.isUndelimitedIdentifier;
    token.hasIrregularChar = this.hasIrregularChar;
    token.isReservedIdentifier = this.isReservedIdentifier;
    token.isCoreReservedIdentifier = this.isCoreReservedIdentifier;
    token.isHostParameter = this.isHostParameter;
    token.isMalformed = this.isMalformed;
    return token;
  }
  
  public String getFullString() {
    return this.fullString;
  }
  
  public void setExpression(Object paramObject) {
    this.expression = paramObject;
  }
  
  String getSQL() {
    if (this.expression instanceof ExpressionColumn) {
      if (this.tokenType == 801) {
        StringBuffer stringBuffer1 = new StringBuffer();
        Expression expression = (Expression)this.expression;
        if (expression != null && expression.opType == 97 && expression.nodes.length > 0) {
          stringBuffer1.append(' ');
          for (byte b = 0; b < expression.nodes.length; b++) {
            Expression expression1 = expression.nodes[b];
            ColumnSchema columnSchema = expression1.getColumn();
            if (expression1.opType == 3) {
              if (b > 0)
                stringBuffer1.append(','); 
              stringBuffer1.append(expression1.getColumnName());
            } else {
              String str;
              if ((expression1.getRangeVariable()).tableAlias == null) {
                str = columnSchema.getName().getSchemaQualifiedStatementName();
              } else {
                RangeVariable rangeVariable = expression1.getRangeVariable();
                str = rangeVariable.tableAlias.getStatementName() + '.' + (columnSchema.getName()).statementName;
              } 
              if (b > 0)
                stringBuffer1.append(','); 
              stringBuffer1.append(str);
            } 
          } 
          stringBuffer1.append(' ');
        } else {
          return this.tokenString;
        } 
        return stringBuffer1.toString();
      } 
    } else {
      if (this.expression instanceof Type) {
        this.isDelimiter = false;
        Type type = (Type)this.expression;
        return (type.isDistinctType() || type.isDomainType()) ? type.getName().getSchemaQualifiedStatementName() : type.getNameString();
      } 
      if (this.expression instanceof SchemaObject) {
        this.isDelimiter = false;
        return ((SchemaObject)this.expression).getName().getSchemaQualifiedStatementName();
      } 
    } 
    if (this.namePrefix == null && this.isUndelimitedIdentifier)
      return this.tokenString; 
    if (this.tokenType == 869)
      return this.dataType.convertToSQLString(this.tokenValue); 
    StringBuffer stringBuffer = new StringBuffer();
    if (this.namePrePrefix != null) {
      if (this.isDelimitedPrePrefix) {
        stringBuffer.append('"');
        stringBuffer.append(this.namePrePrefix);
        stringBuffer.append('"');
      } else {
        stringBuffer.append(this.namePrePrefix);
      } 
      stringBuffer.append('.');
    } 
    if (this.namePrefix != null) {
      if (this.isDelimitedPrefix) {
        stringBuffer.append('"');
        stringBuffer.append(this.namePrefix);
        stringBuffer.append('"');
      } else {
        stringBuffer.append(this.namePrefix);
      } 
      stringBuffer.append('.');
    } 
    if (this.isDelimitedIdentifier) {
      stringBuffer.append('"');
      stringBuffer.append(this.tokenString);
      stringBuffer.append('"');
      this.isDelimiter = false;
    } else {
      stringBuffer.append(this.tokenString);
    } 
    return stringBuffer.toString();
  }
  
  static String getSQL(Token[] paramArrayOfToken) {
    boolean bool = true;
    StringBuffer stringBuffer = new StringBuffer();
    for (byte b = 0; b < paramArrayOfToken.length; b++) {
      String str = paramArrayOfToken[b].getSQL();
      if (!(paramArrayOfToken[b]).isDelimiter && !bool)
        stringBuffer.append(' '); 
      stringBuffer.append(str);
      bool = (paramArrayOfToken[b]).isDelimiter;
    } 
    return stringBuffer.toString();
  }
  
  static Object[] getSimplifiedTokens(Token[] paramArrayOfToken) {
    Object[] arrayOfObject = new Object[paramArrayOfToken.length];
    for (byte b = 0; b < paramArrayOfToken.length; b++) {
      if ((paramArrayOfToken[b]).expression == null) {
        arrayOfObject[b] = paramArrayOfToken[b].getSQL();
      } else {
        arrayOfObject[b] = (paramArrayOfToken[b]).expression;
      } 
    } 
    return arrayOfObject;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\Token.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */