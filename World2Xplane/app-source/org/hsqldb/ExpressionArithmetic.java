package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.Type;

public class ExpressionArithmetic extends Expression {
  ExpressionArithmetic(int paramInt, Expression paramExpression1, Expression paramExpression2) {
    super(paramInt);
    this.nodes[0] = paramExpression1;
    this.nodes[1] = paramExpression2;
    switch (this.opType) {
      case 32:
      case 33:
      case 34:
      case 35:
      case 36:
        return;
    } 
    throw Error.runtimeError(201, "Expression");
  }
  
  ExpressionArithmetic(int paramInt, Expression paramExpression) {
    super(paramInt);
    this.nodes[0] = paramExpression;
    switch (this.opType) {
      case 31:
        return;
    } 
    throw Error.runtimeError(201, "Expression");
  }
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer(64);
    switch (this.opType) {
      case 1:
        if (this.valueData == null)
          return "NULL"; 
        if (this.dataType == null)
          throw Error.runtimeError(201, "Expression"); 
        return this.dataType.convertToSQLString(this.valueData);
    } 
    String str1 = getContextSQL((this.nodes.length > 0) ? this.nodes[0] : null);
    String str2 = getContextSQL((this.nodes.length > 1) ? this.nodes[1] : null);
    switch (this.opType) {
      case 91:
        stringBuffer.append(' ').append("CAST").append('(');
        stringBuffer.append(str1).append(' ').append("AS").append(' ');
        stringBuffer.append(this.dataType.getTypeDefinition());
        stringBuffer.append(')');
        return stringBuffer.toString();
      case 31:
        stringBuffer.append('-').append(str1);
        return stringBuffer.toString();
      case 32:
        stringBuffer.append(str1).append('+').append(str2);
        return stringBuffer.toString();
      case 33:
        stringBuffer.append(str1).append('-').append(str2);
        return stringBuffer.toString();
      case 34:
        stringBuffer.append(str1).append('*').append(str2);
        return stringBuffer.toString();
      case 35:
        stringBuffer.append(str1).append('/').append(str2);
        return stringBuffer.toString();
      case 36:
        stringBuffer.append(str1).append("||").append(str2);
        return stringBuffer.toString();
    } 
    throw Error.runtimeError(201, "Expression");
  }
  
  protected String describe(Session paramSession, int paramInt) {
    StringBuffer stringBuffer = new StringBuffer(64);
    stringBuffer.append('\n');
    byte b;
    for (b = 0; b < paramInt; b++)
      stringBuffer.append(' '); 
    switch (this.opType) {
      case 1:
        stringBuffer.append("VALUE = ").append(this.dataType.convertToSQLString(this.valueData));
        stringBuffer.append(", TYPE = ").append(this.dataType.getNameString());
        return stringBuffer.toString();
      case 25:
      case 26:
        stringBuffer.append("VALUELIST ");
        stringBuffer.append(" TYPE = ").append(this.dataType.getNameString());
        for (b = 0; b < this.nodes.length; b++) {
          stringBuffer.append(this.nodes[b].describe(paramSession, paramInt + paramInt));
          stringBuffer.append(' ');
        } 
        break;
      case 31:
        stringBuffer.append("NEGATE ");
        break;
      case 32:
        stringBuffer.append("ADD ");
        break;
      case 33:
        stringBuffer.append("SUBTRACT ");
        break;
      case 34:
        stringBuffer.append("MULTIPLY ");
        break;
      case 35:
        stringBuffer.append("DIVIDE ");
        break;
      case 36:
        stringBuffer.append("CONCAT ");
        break;
      case 91:
        stringBuffer.append("CAST ");
        stringBuffer.append(this.dataType.getTypeDefinition());
        stringBuffer.append(' ');
        break;
    } 
    if (getLeftNode() != null) {
      stringBuffer.append(" arg_left=[");
      stringBuffer.append(this.nodes[0].describe(paramSession, paramInt + 1));
      stringBuffer.append(']');
    } 
    if (getRightNode() != null) {
      stringBuffer.append(" arg_right=[");
      stringBuffer.append(this.nodes[1].describe(paramSession, paramInt + 1));
      stringBuffer.append(']');
    } 
    return stringBuffer.toString();
  }
  
  public HsqlList resolveColumnReferences(Session paramSession, RangeGroup paramRangeGroup, int paramInt, RangeGroup[] paramArrayOfRangeGroup, HsqlList paramHsqlList, boolean paramBoolean) {
    if (this.opType == 1)
      return paramHsqlList; 
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        paramHsqlList = this.nodes[b].resolveColumnReferences(paramSession, paramRangeGroup, paramInt, paramArrayOfRangeGroup, paramHsqlList, paramBoolean); 
    } 
    return paramHsqlList;
  }
  
  public void resolveTypes(Session paramSession, Expression paramExpression) {
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        this.nodes[b].resolveTypes(paramSession, this); 
    } 
    switch (this.opType) {
      case 1:
        return;
      case 31:
        if (this.nodes[0].isUnresolvedParam() || (this.nodes[0]).dataType == null)
          throw Error.error(5567); 
        this.dataType = (this.nodes[0]).dataType;
        if (!this.dataType.isNumberType())
          throw Error.error(5563); 
        if ((this.nodes[0]).opType == 1)
          setAsConstantValue(paramSession, paramExpression); 
      case 32:
        if (((this.nodes[0]).dataType != null && (this.nodes[0]).dataType.isCharacterType()) || ((this.nodes[1]).dataType != null && (this.nodes[1]).dataType.isCharacterType())) {
          this.opType = 36;
          resolveTypesForConcat(paramSession, paramExpression);
        } 
      case 33:
      case 34:
      case 35:
        resolveTypesForArithmetic(paramSession, paramExpression);
      case 36:
        resolveTypesForConcat(paramSession, paramExpression);
    } 
    throw Error.runtimeError(201, "Expression");
  }
  
  void resolveTypesForArithmetic(Session paramSession, Expression paramExpression) {
    if (this.nodes[0].isUnresolvedParam() && this.nodes[1].isUnresolvedParam()) {
      (this.nodes[0]).dataType = (Type)Type.SQL_INTEGER;
      (this.nodes[1]).dataType = (Type)Type.SQL_INTEGER;
    } 
    if ((this.nodes[0]).dataType == null && (this.nodes[1]).dataType == null) {
      (this.nodes[0]).dataType = (Type)Type.SQL_INTEGER;
      (this.nodes[1]).dataType = (Type)Type.SQL_INTEGER;
    } 
    if (this.nodes[0].isUnresolvedParam()) {
      if ((this.nodes[1]).dataType == null)
        throw Error.error(5567); 
      if ((this.nodes[1]).dataType.isIntervalType() && paramExpression != null) {
        byte b;
        switch (paramExpression.opType) {
          case 40:
          case 41:
          case 43:
          case 44:
          case 45:
            for (b = 0; b < paramExpression.nodes.length; b++) {
              if (paramExpression.nodes[b] != this) {
                if ((paramExpression.nodes[b]).dataType != null && (paramExpression.nodes[b]).dataType.isDateTimeType())
                  (this.nodes[0]).dataType = (paramExpression.nodes[b]).dataType; 
                break;
              } 
            } 
            break;
        } 
      } 
      if ((this.nodes[0]).dataType == null)
        switch (this.opType) {
          case 33:
            if ((this.nodes[1]).dataType.isIntervalType())
              (this.nodes[0]).dataType = (Type)Type.SQL_TIMESTAMP_WITH_TIME_ZONE; 
            break;
          case 32:
            if ((this.nodes[1]).dataType.isDateTimeType()) {
              if ((this.nodes[1]).dataType.typeComparisonGroup == 91) {
                (this.nodes[0]).dataType = (Type)Type.SQL_INTERVAL_YEAR_TO_MONTH_MAX_PRECISION;
                break;
              } 
              (this.nodes[0]).dataType = (Type)Type.SQL_INTERVAL_DAY_TO_SECOND_MAX_PRECISION;
              break;
            } 
            if ((this.nodes[1]).dataType.isIntervalType())
              (this.nodes[0]).dataType = (Type)Type.SQL_TIMESTAMP_WITH_TIME_ZONE; 
            break;
        }  
      if ((this.nodes[0]).dataType == null)
        (this.nodes[0]).dataType = (this.nodes[1]).dataType; 
    } else if (this.nodes[1].isUnresolvedParam()) {
      if ((this.nodes[0]).dataType == null)
        throw Error.error(5567); 
      switch (this.opType) {
        case 34:
        case 35:
          if ((this.nodes[0]).dataType.isIntervalType()) {
            (this.nodes[1]).dataType = (Type)Type.SQL_DECIMAL;
            break;
          } 
          (this.nodes[1]).dataType = (this.nodes[0]).dataType;
          break;
        case 32:
        case 33:
          if ((this.nodes[0]).dataType.isDateTimeType()) {
            if (this.dataType != null && this.dataType.isIntervalType()) {
              (this.nodes[1]).dataType = (this.nodes[0]).dataType;
              break;
            } 
            if ((this.nodes[0]).dataType.typeComparisonGroup == 91) {
              (this.nodes[1]).dataType = (Type)Type.SQL_INTERVAL_YEAR_TO_MONTH_MAX_PRECISION;
              break;
            } 
            (this.nodes[1]).dataType = (Type)Type.SQL_INTERVAL_DAY_TO_SECOND_MAX_PRECISION;
            break;
          } 
          (this.nodes[1]).dataType = (this.nodes[0]).dataType;
          break;
      } 
    } 
    if ((this.nodes[0]).dataType == null || (this.nodes[1]).dataType == null)
      throw Error.error(5567); 
    if (this.dataType != null && this.dataType.isIntervalType()) {
      if ((this.nodes[0]).dataType.isDateTimeType() && (this.nodes[1]).dataType.isDateTimeType()) {
        if ((this.nodes[0]).dataType.typeComparisonGroup != (this.nodes[1]).dataType.typeComparisonGroup)
          throw Error.error(5562); 
      } else {
        Type type = (this.nodes[0]).dataType.getCombinedType(paramSession, (this.nodes[1]).dataType, this.opType);
        if (type == null)
          throw Error.error(5562); 
        if (type.isIntervalType()) {
          if (type.typeCode != this.dataType.typeCode)
            throw Error.error(5562); 
        } else if (type.isNumberType()) {
          this.nodes[0] = new ExpressionOp(this.nodes[0], this.dataType);
          this.nodes[1] = new ExpressionOp(this.nodes[1], this.dataType);
          this.nodes[0].resolveTypes(paramSession, this);
          this.nodes[1].resolveTypes(paramSession, this);
        } else {
          throw Error.error(5562);
        } 
      } 
    } else {
      this.dataType = (this.nodes[0]).dataType.getCombinedType(paramSession, (this.nodes[1]).dataType, this.opType);
      if (this.dataType.isDateTimeType())
        if ((this.nodes[0]).dataType.isIntervalType()) {
          if (this.opType != 32)
            throw Error.error(5563); 
          Expression expression = this.nodes[0];
          this.nodes[0] = this.nodes[1];
          this.nodes[1] = expression;
        } else if ((this.nodes[1]).dataType.isNumberType() && !paramSession.database.sqlSyntaxOra) {
          throw Error.error(5562);
        }  
    } 
    if ((this.nodes[0]).opType == 1 && (this.nodes[1]).opType == 1)
      setAsConstantValue(paramSession, paramExpression); 
  }
  
  void resolveTypesForConcat(Session paramSession, Expression paramExpression) {
    if (this.dataType != null)
      return; 
    if (this.nodes[0].isUnresolvedParam()) {
      (this.nodes[0]).dataType = getParameterType((this.nodes[1]).dataType);
    } else if (this.nodes[1].isUnresolvedParam()) {
      (this.nodes[1]).dataType = getParameterType((this.nodes[0]).dataType);
    } 
    if ((this.nodes[0]).dataType == null)
      (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT; 
    if ((this.nodes[1]).dataType == null)
      (this.nodes[1]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT; 
    if (((this.nodes[0]).dataType.isBinaryType() ^ (this.nodes[1]).dataType.isBinaryType()) != 0)
      throw Error.error(5563); 
    if ((this.nodes[0]).dataType.isArrayType()) {
      Expression expression = this.nodes[1];
      if (expression.opType == 99) {
        if (paramExpression == null)
          throw Error.error(5563); 
        this.nodes[1] = expression.getLeftNode();
        expression.nodes[0] = this;
        paramExpression.replaceNode(this, expression);
      } 
    } 
    if (((this.nodes[0]).dataType.isArrayType() ^ (this.nodes[1]).dataType.isArrayType()) != 0)
      throw Error.error(5563); 
    if ((this.nodes[0]).dataType.isCharacterType() && !(this.nodes[1]).dataType.isCharacterType()) {
      if (paramSession.database.sqlEnforceTypes)
        throw Error.error(5562); 
      CharacterType characterType = CharacterType.getCharacterType(12, (this.nodes[1]).dataType.displaySize(), (this.nodes[0]).dataType.getCollation());
      this.nodes[1] = ExpressionOp.getCastExpression(paramSession, this.nodes[1], (Type)characterType);
    } 
    if ((this.nodes[1]).dataType.isCharacterType() && !(this.nodes[0]).dataType.isCharacterType()) {
      if (paramSession.database.sqlEnforceTypes)
        throw Error.error(5562); 
      CharacterType characterType = CharacterType.getCharacterType(12, (this.nodes[0]).dataType.displaySize(), (this.nodes[1]).dataType.getCollation());
      this.nodes[0] = ExpressionOp.getCastExpression(paramSession, this.nodes[0], (Type)characterType);
    } 
    this.dataType = (this.nodes[0]).dataType.getCombinedType(paramSession, (this.nodes[1]).dataType, 36);
    if ((this.nodes[0]).opType == 1 && (this.nodes[1]).opType == 1)
      setAsConstantValue(paramSession, paramExpression); 
  }
  
  private Type getParameterType(Type paramType) {
    if (paramType == null)
      return null; 
    switch (paramType.typeCode) {
      case 1:
      case 12:
        return (Type)Type.SQL_VARCHAR_DEFAULT;
      case 40:
        return (Type)Type.SQL_CLOB;
      case 60:
      case 61:
        return (Type)Type.SQL_VARBINARY_DEFAULT;
      case 30:
        return (Type)Type.SQL_BLOB;
      case 14:
      case 15:
        return (Type)Type.SQL_BIT_VARYING_MAX_LENGTH;
      case 50:
        return paramType;
    } 
    return null;
  }
  
  public Object getValue(Session paramSession) {
    switch (this.opType) {
      case 1:
        return this.valueData;
      case 5:
        return paramSession.sessionContext.rangeIterators[this.rangePosition].getCurrent(this.columnIndex);
      case 31:
        return ((NumberType)this.dataType).negate(this.nodes[0].getValue(paramSession, (this.nodes[0]).dataType));
    } 
    Object object1 = this.nodes[0].getValue(paramSession);
    Object object2 = this.nodes[1].getValue(paramSession);
    switch (this.opType) {
      case 32:
        return this.dataType.add(paramSession, object1, object2, (this.nodes[1]).dataType);
      case 33:
        return this.dataType.subtract(paramSession, object1, object2, (this.nodes[1]).dataType);
      case 34:
        return this.dataType.multiply(object1, object2);
      case 35:
        return this.dataType.divide(paramSession, object1, object2);
      case 36:
        if (!paramSession.database.sqlConcatNulls && (this.nodes[0]).dataType.isCharacterType())
          if (object1 == null && object2 != null) {
            object1 = "";
          } else if (object1 != null && object2 == null) {
            object2 = "";
          }  
        return this.dataType.concat(paramSession, object1, object2);
    } 
    throw Error.runtimeError(201, "Expression");
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\ExpressionArithmetic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */