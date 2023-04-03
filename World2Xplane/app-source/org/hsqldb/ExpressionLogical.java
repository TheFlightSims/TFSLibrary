package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.DTIType;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.Type;

public class ExpressionLogical extends Expression {
  boolean noOptimisation;
  
  boolean isQuantified;
  
  boolean isTerminal;
  
  RangeVariable[] rangeArray = RangeVariable.emptyArray;
  
  ExpressionLogical(int paramInt) {
    super(paramInt);
    this.dataType = (Type)Type.SQL_BOOLEAN;
  }
  
  ExpressionLogical(boolean paramBoolean) {
    super(1);
    this.dataType = (Type)Type.SQL_BOOLEAN;
    this.valueData = paramBoolean ? Boolean.TRUE : Boolean.FALSE;
  }
  
  ExpressionLogical(RangeVariable paramRangeVariable1, int paramInt1, RangeVariable paramRangeVariable2, int paramInt2) {
    super(40);
    ExpressionColumn expressionColumn1 = new ExpressionColumn(paramRangeVariable1, paramInt1);
    ExpressionColumn expressionColumn2 = new ExpressionColumn(paramRangeVariable2, paramInt2);
    this.nodes = new Expression[2];
    this.nodes[0] = expressionColumn1;
    this.nodes[1] = expressionColumn2;
    setEqualityMode();
    this.dataType = (Type)Type.SQL_BOOLEAN;
  }
  
  ExpressionLogical(Expression paramExpression1, Expression paramExpression2) {
    super(40);
    this.nodes = new Expression[2];
    this.nodes[0] = paramExpression1;
    this.nodes[1] = paramExpression2;
    setEqualityMode();
    this.dataType = (Type)Type.SQL_BOOLEAN;
  }
  
  ExpressionLogical(int paramInt, Expression paramExpression1, Expression paramExpression2) {
    super(paramInt);
    this.nodes = new Expression[2];
    this.nodes[0] = paramExpression1;
    this.nodes[1] = paramExpression2;
    switch (this.opType) {
      case 40:
      case 41:
      case 42:
      case 43:
      case 44:
      case 45:
        setEqualityMode();
      case 46:
      case 49:
      case 50:
      case 54:
      case 56:
      case 58:
      case 59:
      case 60:
      case 61:
      case 62:
      case 63:
      case 64:
        this.dataType = (Type)Type.SQL_BOOLEAN;
        return;
    } 
    throw Error.runtimeError(201, "ExpressionLogical");
  }
  
  ExpressionLogical(int paramInt, Expression paramExpression1, Expression paramExpression2, Expression paramExpression3) {
    super(paramInt);
    this.nodes = new Expression[3];
    this.nodes[0] = paramExpression1;
    this.nodes[1] = paramExpression2;
    this.nodes[2] = paramExpression3;
  }
  
  ExpressionLogical(int paramInt, Expression paramExpression) {
    super(paramInt);
    this.nodes = new Expression[1];
    this.nodes[0] = paramExpression;
    switch (this.opType) {
      case 39:
      case 47:
      case 48:
      case 55:
      case 57:
        this.dataType = (Type)Type.SQL_BOOLEAN;
        break;
      default:
        throw Error.runtimeError(201, "ExpressionLogical");
    } 
    if (this.opType == 47 && (this.nodes[0]).opType == 2)
      this.isSingleColumnNull = true; 
    if (this.opType == 48 && (this.nodes[0]).isSingleColumnNull)
      this.isSingleColumnNotNull = true; 
  }
  
  ExpressionLogical(ColumnSchema paramColumnSchema) {
    super(48);
    this.nodes = new Expression[1];
    this.dataType = (Type)Type.SQL_BOOLEAN;
    ExpressionColumn expressionColumn = new ExpressionColumn(paramColumnSchema);
    ExpressionLogical expressionLogical = new ExpressionLogical(47, expressionColumn);
    this.nodes[0] = expressionLogical;
  }
  
  void setEqualityMode() {
    if ((this.nodes[0]).opType == 2) {
      (this.nodes[0]).nullability = 0;
      switch ((this.nodes[1]).opType) {
        case 2:
          this.isColumnCondition = true;
          if (this.opType == 40)
            this.isColumnEqual = true; 
          (this.nodes[1]).nullability = 0;
          break;
        case 1:
        case 6:
        case 7:
        case 8:
          this.isSingleColumnCondition = true;
          if (this.opType == 40)
            this.isSingleColumnEqual = true; 
          break;
      } 
    } else if ((this.nodes[1]).opType == 2) {
      (this.nodes[1]).nullability = 0;
      switch ((this.nodes[0]).opType) {
        case 1:
        case 6:
        case 7:
        case 8:
          this.isSingleColumnCondition = true;
          if (this.opType == 40)
            this.isSingleColumnEqual = true; 
          break;
      } 
    } 
  }
  
  static ExpressionLogical newNotNullCondition(Expression paramExpression) {
    paramExpression = new ExpressionLogical(47, paramExpression);
    return new ExpressionLogical(48, paramExpression);
  }
  
  static Expression andExpressions(Expression paramExpression1, Expression paramExpression2) {
    return (paramExpression1 == null) ? paramExpression2 : ((paramExpression2 == null) ? paramExpression1 : ((EXPR_FALSE.equals(paramExpression1) || EXPR_FALSE.equals(paramExpression2)) ? EXPR_FALSE : ((paramExpression1 == paramExpression2) ? paramExpression1 : new ExpressionLogical(49, paramExpression1, paramExpression2))));
  }
  
  static Expression orExpressions(Expression paramExpression1, Expression paramExpression2) {
    return (paramExpression1 == null) ? paramExpression2 : ((paramExpression2 == null) ? paramExpression1 : ((paramExpression1 == paramExpression2) ? paramExpression1 : new ExpressionLogical(50, paramExpression1, paramExpression2)));
  }
  
  public void addLeftColumnsForAllAny(RangeVariable paramRangeVariable, OrderedIntHashSet paramOrderedIntHashSet) {
    if (this.nodes.length == 0)
      return; 
    for (byte b = 0; b < (this.nodes[0]).nodes.length; b++) {
      int i = (this.nodes[0]).nodes[b].getColumnIndex();
      if (i < 0 || (this.nodes[0]).nodes[b].getRangeVariable() != paramRangeVariable) {
        paramOrderedIntHashSet.clear();
        return;
      } 
      paramOrderedIntHashSet.add(i);
    } 
  }
  
  public void setSubType(int paramInt) {
    this.exprSubType = paramInt;
    if (this.exprSubType == 51 || this.exprSubType == 52)
      this.isQuantified = true; 
  }
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer(64);
    if (this.opType == 1)
      return super.getSQL(); 
    String str1 = getContextSQL(this.nodes[0]);
    String str2 = getContextSQL((this.nodes.length > 1) ? this.nodes[1] : null);
    switch (this.opType) {
      case 48:
        if ((this.nodes[0]).opType == 47) {
          stringBuffer.append(getContextSQL((this.nodes[0]).nodes[0])).append(' ').append("IS").append(' ').append("NOT").append(' ').append("NULL");
          return stringBuffer.toString();
        } 
        if ((this.nodes[0]).opType == 58) {
          stringBuffer.append(getContextSQL((this.nodes[0]).nodes[0])).append(' ').append("IS").append(' ').append("DISTINCT").append(' ').append("FROM").append(' ').append(getContextSQL((this.nodes[0]).nodes[1]));
          return stringBuffer.toString();
        } 
        stringBuffer.append("NOT").append(' ').append(str1);
        return stringBuffer.toString();
      case 58:
        stringBuffer.append(str1).append(' ').append("IS").append(' ').append("NOT").append(' ').append("DISTINCT").append(' ').append("FROM").append(' ').append(str2);
        return stringBuffer.toString();
      case 47:
        stringBuffer.append(str1).append(' ').append("IS").append(' ').append("NULL");
        return stringBuffer.toString();
      case 57:
        stringBuffer.append(' ').append("UNIQUE").append(' ');
        return stringBuffer.toString();
      case 55:
        stringBuffer.append(' ').append("EXISTS").append(' ');
        return stringBuffer.toString();
      case 40:
        stringBuffer.append(str1).append('=').append(str2);
        return stringBuffer.toString();
      case 41:
      case 42:
        stringBuffer.append(str1).append(">=").append(str2);
        return stringBuffer.toString();
      case 43:
        stringBuffer.append(str1).append('>').append(str2);
        return stringBuffer.toString();
      case 44:
        stringBuffer.append(str1).append('<').append(str2);
        return stringBuffer.toString();
      case 45:
        stringBuffer.append(str1).append("<=").append(str2);
        return stringBuffer.toString();
      case 46:
        if ("NULL".equals(str2)) {
          stringBuffer.append(str1).append(" IS NOT ").append(str2);
        } else {
          stringBuffer.append(str1).append("!=").append(str2);
        } 
        return stringBuffer.toString();
      case 49:
        stringBuffer.append(str1).append(' ').append("AND").append(' ').append(str2);
        return stringBuffer.toString();
      case 50:
        stringBuffer.append(str1).append(' ').append("OR").append(' ').append(str2);
        return stringBuffer.toString();
      case 54:
        stringBuffer.append(str1).append(' ').append("IN").append(' ').append(str2);
        return stringBuffer.toString();
      case 59:
        stringBuffer.append(str1).append(' ').append("MATCH").append(' ').append(str2);
        return stringBuffer.toString();
      case 60:
        stringBuffer.append(str1).append(' ').append("MATCH").append(' ').append(470).append(str2);
        return stringBuffer.toString();
      case 61:
        stringBuffer.append(str1).append(' ').append("MATCH").append(' ').append(116).append(str2);
        return stringBuffer.toString();
      case 62:
        stringBuffer.append(str1).append(' ').append("MATCH").append(' ').append(299).append(str2);
        return stringBuffer.toString();
      case 63:
        stringBuffer.append(str1).append(' ').append("MATCH").append(' ').append(299).append(' ').append(470).append(str2);
        return stringBuffer.toString();
      case 64:
        stringBuffer.append(str1).append(' ').append("MATCH").append(' ').append(299).append(' ').append(116).append(str2);
        return stringBuffer.toString();
    } 
    throw Error.runtimeError(201, "ExpressionLogical");
  }
  
  protected String describe(Session paramSession, int paramInt) {
    StringBuffer stringBuffer = new StringBuffer(64);
    stringBuffer.append('\n');
    for (byte b = 0; b < paramInt; b++)
      stringBuffer.append(' '); 
    switch (this.opType) {
      case 1:
        stringBuffer.append("VALUE = ").append(this.dataType.convertToSQLString(this.valueData));
        stringBuffer.append(", TYPE = ").append(this.dataType.getNameString());
        return stringBuffer.toString();
      case 48:
        if ((this.nodes[0]).opType == 58) {
          stringBuffer.append("DISTINCT");
          return stringBuffer.toString();
        } 
        stringBuffer.append("NOT");
        break;
      case 58:
        stringBuffer.append("NOT").append(' ').append("DISTINCT");
        break;
      case 40:
        stringBuffer.append("EQUAL");
        break;
      case 41:
      case 42:
        stringBuffer.append("GREATER_EQUAL");
        break;
      case 43:
        stringBuffer.append("GREATER");
        break;
      case 44:
        stringBuffer.append("SMALLER");
        break;
      case 45:
        stringBuffer.append("SMALLER_EQUAL");
        break;
      case 46:
        stringBuffer.append("NOT_EQUAL");
        break;
      case 49:
        stringBuffer.append("AND");
        break;
      case 50:
        stringBuffer.append("OR");
        break;
      case 59:
      case 60:
      case 61:
      case 62:
      case 63:
      case 64:
        stringBuffer.append("MATCH");
        break;
      case 47:
        stringBuffer.append("IS").append(' ').append("NULL");
        break;
      case 57:
        stringBuffer.append("UNIQUE");
        break;
      case 55:
        stringBuffer.append("EXISTS");
        break;
      case 56:
        stringBuffer.append("OVERLAPS");
        break;
      default:
        throw Error.runtimeError(201, "ExpressionLogical");
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
  
  public void resolveTypes(Session paramSession, Expression paramExpression) {
    Expression[] arrayOfExpression;
    Expression expression;
    byte b2;
    if (this.isQuantified && (this.nodes[1]).opType == 30 && this.nodes[1] instanceof ExpressionTable && ((this.nodes[1]).nodes[0]).opType == 8) {
      this.nodes[0].resolveTypes(paramSession, this);
      ((this.nodes[1]).nodes[0]).dataType = (Type)new ArrayType((this.nodes[0]).dataType, 2147483647);
    } 
    for (byte b1 = 0; b1 < this.nodes.length; b1++) {
      if (this.nodes[b1] != null)
        this.nodes[b1].resolveTypes(paramSession, this); 
    } 
    switch (this.opType) {
      case 1:
      
      case 58:
        changeToRowExpression(0);
        changeToRowExpression(1);
        resolveRowTypes();
        checkRowComparison();
      case 40:
      case 41:
      case 42:
      case 43:
      case 44:
      case 45:
      case 46:
        resolveTypesForComparison(paramSession, paramExpression);
      case 49:
        resolveTypesForLogicalOp();
        if ((this.nodes[0]).opType == 1) {
          if ((this.nodes[1]).opType == 1)
            setAsConstantValue(paramSession, paramExpression); 
          Object object = this.nodes[0].getValue(paramSession);
          if (object == null || Boolean.FALSE.equals(object))
            setAsConstantValue(Boolean.FALSE, paramExpression); 
        } 
        if ((this.nodes[1]).opType == 1) {
          Object object = this.nodes[1].getValue(paramSession);
          if (object == null || Boolean.FALSE.equals(object))
            setAsConstantValue(Boolean.FALSE, paramExpression); 
        } 
      case 50:
        resolveTypesForLogicalOp();
        if ((this.nodes[0]).opType == 1) {
          if ((this.nodes[1]).opType == 1)
            setAsConstantValue(paramSession, paramExpression); 
          Object object = this.nodes[0].getValue(paramSession);
          if (Boolean.TRUE.equals(object))
            setAsConstantValue(Boolean.TRUE, paramExpression); 
        } 
        if ((this.nodes[1]).opType == 1) {
          Object object = this.nodes[1].getValue(paramSession);
          if (Boolean.TRUE.equals(object))
            setAsConstantValue(Boolean.TRUE, paramExpression); 
        } 
      case 39:
      case 47:
        switch ((this.nodes[0]).opType) {
          case 25:
            arrayOfExpression = (this.nodes[0]).nodes;
            expression = null;
            for (b2 = 0; b2 < arrayOfExpression.length; b2++) {
              ExpressionLogical expressionLogical = new ExpressionLogical(47, arrayOfExpression[b2]);
              if (this.opType == 39)
                expressionLogical = new ExpressionLogical(48, expressionLogical); 
              expression = andExpressions(expression, expressionLogical);
            } 
            this.opType = 49;
            this.nodes = expression.nodes;
            resolveTypes(paramSession, paramExpression);
          case 22:
          case 23:
            return;
        } 
        if (this.nodes[0].isUnresolvedParam()) {
          if (paramSession.database.sqlEnforceTypes)
            throw Error.error(5563); 
          (this.nodes[0]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT;
        } 
        if (this.opType == 39) {
          ExpressionLogical expressionLogical = new ExpressionLogical(47, this.nodes[0]);
          this.nodes[0] = expressionLogical;
          this.opType = 48;
          resolveTypes(paramSession, paramExpression);
        } 
        if ((this.nodes[0]).opType == 1)
          setAsConstantValue(paramSession, paramExpression); 
      case 48:
        if (this.nodes[0].isUnresolvedParam())
          (this.nodes[0]).dataType = (Type)Type.SQL_BOOLEAN; 
        if ((this.nodes[0]).opType == 1) {
          if ((this.nodes[0]).dataType.isBooleanType())
            setAsConstantValue(paramSession, paramExpression); 
          throw Error.error(5563);
        } 
        if ((this.nodes[0]).dataType == null || !(this.nodes[0]).dataType.isBooleanType())
          throw Error.error(5563); 
        this.dataType = (Type)Type.SQL_BOOLEAN;
      case 56:
        resolveTypesForOverlaps();
      case 54:
        resolveTypesForIn(paramSession);
      case 59:
      case 60:
      case 61:
      case 62:
      case 63:
      case 64:
        resolveTypesForAllAny(paramSession);
      case 55:
      case 57:
      
    } 
    throw Error.runtimeError(201, "ExpressionLogical");
  }
  
  private void resolveTypesForLogicalOp() {
    if (this.nodes[0].isUnresolvedParam())
      (this.nodes[0]).dataType = (Type)Type.SQL_BOOLEAN; 
    if (this.nodes[1].isUnresolvedParam())
      (this.nodes[1]).dataType = (Type)Type.SQL_BOOLEAN; 
    if ((this.nodes[0]).dataType == null || (this.nodes[1]).dataType == null)
      throw Error.error(5571); 
    if ((this.nodes[0]).opType == 25 || (this.nodes[1]).opType == 25)
      throw Error.error(5565); 
    if (Type.SQL_BOOLEAN != (this.nodes[0]).dataType || Type.SQL_BOOLEAN != (this.nodes[1]).dataType)
      throw Error.error(5568); 
  }
  
  private void resolveTypesForComparison(Session paramSession, Expression paramExpression) {
    if (this.exprSubType == 51 || this.exprSubType == 52) {
      resolveTypesForAllAny(paramSession);
      checkRowComparison();
      return;
    } 
    int i = this.nodes[0].getDegree();
    int j = this.nodes[1].getDegree();
    if (i > 1 || j > 1) {
      if (i != j)
        throw Error.error(5564); 
      resolveRowTypes();
      checkRowComparison();
      return;
    } 
    if (this.nodes[0].isUnresolvedParam()) {
      (this.nodes[0]).dataType = (this.nodes[1]).dataType;
    } else if (this.nodes[1].isUnresolvedParam()) {
      (this.nodes[1]).dataType = (this.nodes[0]).dataType;
    } 
    if ((this.nodes[0]).dataType == null) {
      (this.nodes[0]).dataType = (this.nodes[1]).dataType;
    } else if ((this.nodes[1]).dataType == null) {
      (this.nodes[1]).dataType = (this.nodes[0]).dataType;
    } 
    if ((this.nodes[0]).dataType == null || (this.nodes[1]).dataType == null)
      throw Error.error(5567); 
    if ((this.nodes[0]).dataType.typeComparisonGroup != (this.nodes[1]).dataType.typeComparisonGroup && !convertDateTimeLiteral(paramSession, this.nodes[0], this.nodes[1]))
      if ((this.nodes[0]).dataType.isBitType() || (this.nodes[0]).dataType.isBooleanType()) {
        if (paramSession.database.sqlEnforceTypes)
          throw Error.error(5562); 
        if ((this.nodes[0]).dataType.canConvertFrom((this.nodes[1]).dataType))
          this.nodes[1] = ExpressionOp.getCastExpression(paramSession, this.nodes[1], (this.nodes[0]).dataType); 
      } else if ((this.nodes[1]).dataType.isBitType() || (this.nodes[1]).dataType.isBooleanType()) {
        if (paramSession.database.sqlEnforceTypes)
          throw Error.error(5562); 
        if ((this.nodes[1]).dataType.canConvertFrom((this.nodes[0]).dataType))
          this.nodes[0] = ExpressionOp.getCastExpression(paramSession, this.nodes[0], (this.nodes[1]).dataType); 
      } else if ((this.nodes[0]).dataType.isNumberType()) {
        if (paramSession.database.sqlEnforceTypes)
          throw Error.error(5562); 
        if ((this.nodes[0]).dataType.canConvertFrom((this.nodes[1]).dataType))
          this.nodes[1] = ExpressionOp.getCastExpression(paramSession, this.nodes[1], (this.nodes[0]).dataType); 
      } else if ((this.nodes[1]).dataType.isNumberType()) {
        if (paramSession.database.sqlEnforceTypes)
          throw Error.error(5562); 
        if ((this.nodes[1]).dataType.canConvertFrom((this.nodes[0]).dataType))
          this.nodes[0] = ExpressionOp.getCastExpression(paramSession, this.nodes[0], (this.nodes[1]).dataType); 
      } else if ((this.nodes[0]).dataType.isDateTimeType()) {
        if (((this.nodes[0]).dataType.isDateTimeTypeWithZone() ^ (this.nodes[1]).dataType.isDateTimeTypeWithZone()) != 0)
          this.nodes[0] = new ExpressionOp(this.nodes[0]); 
      } else {
        throw Error.error(5562);
      }  
    if (this.opType != 40 && this.opType != 46 && ((this.nodes[0]).dataType.isArrayType() || (this.nodes[0]).dataType.isLobType() || (this.nodes[1]).dataType.isLobType()))
      throw Error.error(5534); 
    if ((this.nodes[0]).opType == 14 && (this.nodes[1]).opType == 1)
      this.isTerminal = true; 
    if ((this.nodes[0]).dataType.typeComparisonGroup != (this.nodes[1]).dataType.typeComparisonGroup)
      throw Error.error(5562); 
    if ((this.nodes[0]).opType == 1 && (this.nodes[1]).opType == 1)
      setAsConstantValue(paramSession, paramExpression); 
  }
  
  private void changeToRowExpression(int paramInt) {
    if ((this.nodes[paramInt]).opType != 25) {
      this.nodes[paramInt] = new Expression(25, new Expression[] { this.nodes[paramInt] });
      (this.nodes[paramInt]).nodeDataTypes = new Type[] { ((this.nodes[paramInt]).nodes[0]).dataType };
    } 
  }
  
  private void resolveRowTypes() {
    for (byte b = 0; b < (this.nodes[0]).nodeDataTypes.length; b++) {
      Type type1 = (this.nodes[0]).nodeDataTypes[b];
      Type type2 = (this.nodes[1]).nodeDataTypes[b];
      if (type1 == null) {
        type1 = (this.nodes[0]).nodeDataTypes[b] = type2;
      } else if ((this.nodes[1]).dataType == null) {
        type2 = (this.nodes[1]).nodeDataTypes[b] = type1;
      } 
      if (type1 == null || type2 == null)
        throw Error.error(5567); 
      if (type1.typeComparisonGroup != type2.typeComparisonGroup)
        throw Error.error(5562); 
      if (type1.isDateTimeType() && (type1.isDateTimeTypeWithZone() ^ type2.isDateTimeTypeWithZone()) != 0) {
        (this.nodes[0]).nodes[b] = new ExpressionOp((this.nodes[0]).nodes[b]);
        (this.nodes[0]).nodeDataTypes[b] = ((this.nodes[0]).nodes[b]).dataType;
      } 
    } 
  }
  
  void checkRowComparison() {
    if (this.opType == 40 || this.opType == 46)
      return; 
    for (byte b = 0; b < (this.nodes[0]).nodeDataTypes.length; b++) {
      Type type1 = (this.nodes[0]).nodeDataTypes[b];
      Type type2 = (this.nodes[1]).nodeDataTypes[b];
      if (type1.isArrayType() || type1.isLobType() || type2.isLobType())
        throw Error.error(5534); 
    } 
  }
  
  private boolean convertDateTimeLiteral(Session paramSession, Expression paramExpression1, Expression paramExpression2) {
    if (!paramExpression1.dataType.isDateTimeType())
      if (paramExpression2.dataType.isDateTimeType()) {
        Expression expression = paramExpression1;
        paramExpression1 = paramExpression2;
        paramExpression2 = expression;
      } else {
        return false;
      }  
    if (paramExpression1.dataType.isDateTimeTypeWithZone())
      return false; 
    if (paramExpression2.opType == 1 && paramExpression2.dataType.isCharacterType()) {
      try {
        paramExpression2.valueData = paramExpression1.dataType.castToType(paramSession, paramExpression2.valueData, paramExpression2.dataType);
        paramExpression2.dataType = paramExpression1.dataType;
      } catch (HsqlException hsqlException) {
        if (paramExpression1.dataType == Type.SQL_DATE) {
          paramExpression2.valueData = Type.SQL_TIMESTAMP.castToType(paramSession, paramExpression2.valueData, paramExpression2.dataType);
          paramExpression2.dataType = (Type)Type.SQL_TIMESTAMP;
        } 
      } 
      return true;
    } 
    return false;
  }
  
  void resolveTypesForOverlaps() {
    if ((this.nodes[0]).nodes[0].isUnresolvedParam())
      ((this.nodes[0]).nodes[0]).dataType = ((this.nodes[1]).nodes[0]).dataType; 
    if ((this.nodes[1]).nodes[0].isUnresolvedParam())
      ((this.nodes[1]).nodes[0]).dataType = ((this.nodes[0]).nodes[0]).dataType; 
    if (((this.nodes[0]).nodes[0]).dataType == null) {
      ((this.nodes[0]).nodes[0]).dataType = (Type)Type.SQL_TIMESTAMP;
      ((this.nodes[1]).nodes[0]).dataType = (Type)Type.SQL_TIMESTAMP;
    } 
    if ((this.nodes[0]).nodes[1].isUnresolvedParam())
      ((this.nodes[0]).nodes[1]).dataType = ((this.nodes[1]).nodes[0]).dataType; 
    if ((this.nodes[1]).nodes[1].isUnresolvedParam())
      ((this.nodes[1]).nodes[1]).dataType = ((this.nodes[0]).nodes[0]).dataType; 
    if (!DTIType.isValidDatetimeRange(((this.nodes[0]).nodes[0]).dataType, ((this.nodes[0]).nodes[1]).dataType) || !DTIType.isValidDatetimeRange(((this.nodes[1]).nodes[0]).dataType, ((this.nodes[1]).nodes[1]).dataType))
      throw Error.error(5563); 
    if (!DTIType.isValidDatetimeRange(((this.nodes[0]).nodes[0]).dataType, ((this.nodes[0]).nodes[1]).dataType))
      throw Error.error(5563); 
    (this.nodes[0]).nodeDataTypes[0] = ((this.nodes[0]).nodes[0]).dataType;
    (this.nodes[0]).nodeDataTypes[1] = ((this.nodes[0]).nodes[1]).dataType;
    (this.nodes[1]).nodeDataTypes[0] = ((this.nodes[1]).nodes[0]).dataType;
    (this.nodes[1]).nodeDataTypes[1] = ((this.nodes[1]).nodes[1]).dataType;
  }
  
  void resolveTypesForAllAny(Session paramSession) {
    // Byte code:
    //   0: aload_0
    //   1: getfield nodes : [Lorg/hsqldb/Expression;
    //   4: iconst_0
    //   5: aaload
    //   6: invokevirtual getDegree : ()I
    //   9: istore_2
    //   10: iload_2
    //   11: iconst_1
    //   12: if_icmpne -> 57
    //   15: aload_0
    //   16: getfield nodes : [Lorg/hsqldb/Expression;
    //   19: iconst_0
    //   20: aaload
    //   21: getfield opType : I
    //   24: bipush #25
    //   26: if_icmpeq -> 57
    //   29: aload_0
    //   30: getfield nodes : [Lorg/hsqldb/Expression;
    //   33: iconst_0
    //   34: new org/hsqldb/Expression
    //   37: dup
    //   38: bipush #25
    //   40: iconst_1
    //   41: anewarray org/hsqldb/Expression
    //   44: dup
    //   45: iconst_0
    //   46: aload_0
    //   47: getfield nodes : [Lorg/hsqldb/Expression;
    //   50: iconst_0
    //   51: aaload
    //   52: aastore
    //   53: invokespecial <init> : (I[Lorg/hsqldb/Expression;)V
    //   56: aastore
    //   57: aload_0
    //   58: getfield nodes : [Lorg/hsqldb/Expression;
    //   61: iconst_1
    //   62: aaload
    //   63: getfield opType : I
    //   66: bipush #26
    //   68: if_icmpne -> 100
    //   71: aload_0
    //   72: getfield nodes : [Lorg/hsqldb/Expression;
    //   75: iconst_1
    //   76: aaload
    //   77: aload_1
    //   78: aload_0
    //   79: getfield nodes : [Lorg/hsqldb/Expression;
    //   82: iconst_0
    //   83: aaload
    //   84: iload_2
    //   85: invokevirtual prepareTable : (Lorg/hsqldb/Session;Lorg/hsqldb/Expression;I)V
    //   88: aload_0
    //   89: getfield nodes : [Lorg/hsqldb/Expression;
    //   92: iconst_1
    //   93: aaload
    //   94: getfield table : Lorg/hsqldb/TableDerived;
    //   97: invokevirtual prepareTable : ()V
    //   100: aload_0
    //   101: getfield nodes : [Lorg/hsqldb/Expression;
    //   104: iconst_1
    //   105: aaload
    //   106: getfield nodeDataTypes : [Lorg/hsqldb/types/Type;
    //   109: ifnonnull -> 129
    //   112: aload_0
    //   113: getfield nodes : [Lorg/hsqldb/Expression;
    //   116: iconst_1
    //   117: aaload
    //   118: aload_1
    //   119: aload_0
    //   120: getfield nodes : [Lorg/hsqldb/Expression;
    //   123: iconst_0
    //   124: aaload
    //   125: iload_2
    //   126: invokevirtual prepareTable : (Lorg/hsqldb/Session;Lorg/hsqldb/Expression;I)V
    //   129: iload_2
    //   130: aload_0
    //   131: getfield nodes : [Lorg/hsqldb/Expression;
    //   134: iconst_1
    //   135: aaload
    //   136: getfield nodeDataTypes : [Lorg/hsqldb/types/Type;
    //   139: arraylength
    //   140: if_icmpeq -> 150
    //   143: sipush #5564
    //   146: invokestatic error : (I)Lorg/hsqldb/HsqlException;
    //   149: athrow
    //   150: aload_0
    //   151: getfield nodes : [Lorg/hsqldb/Expression;
    //   154: iconst_1
    //   155: aaload
    //   156: getfield opType : I
    //   159: bipush #26
    //   161: if_icmpne -> 164
    //   164: aload_0
    //   165: getfield nodes : [Lorg/hsqldb/Expression;
    //   168: iconst_0
    //   169: aaload
    //   170: getfield nodeDataTypes : [Lorg/hsqldb/types/Type;
    //   173: ifnonnull -> 198
    //   176: aload_0
    //   177: getfield nodes : [Lorg/hsqldb/Expression;
    //   180: iconst_0
    //   181: aaload
    //   182: aload_0
    //   183: getfield nodes : [Lorg/hsqldb/Expression;
    //   186: iconst_0
    //   187: aaload
    //   188: getfield nodes : [Lorg/hsqldb/Expression;
    //   191: arraylength
    //   192: anewarray org/hsqldb/types/Type
    //   195: putfield nodeDataTypes : [Lorg/hsqldb/types/Type;
    //   198: iconst_0
    //   199: istore_3
    //   200: iload_3
    //   201: aload_0
    //   202: getfield nodes : [Lorg/hsqldb/Expression;
    //   205: iconst_0
    //   206: aaload
    //   207: getfield nodeDataTypes : [Lorg/hsqldb/types/Type;
    //   210: arraylength
    //   211: if_icmpge -> 324
    //   214: aload_0
    //   215: getfield nodes : [Lorg/hsqldb/Expression;
    //   218: iconst_0
    //   219: aaload
    //   220: getfield nodes : [Lorg/hsqldb/Expression;
    //   223: iload_3
    //   224: aaload
    //   225: getfield dataType : Lorg/hsqldb/types/Type;
    //   228: astore #4
    //   230: aload #4
    //   232: ifnonnull -> 248
    //   235: aload_0
    //   236: getfield nodes : [Lorg/hsqldb/Expression;
    //   239: iconst_1
    //   240: aaload
    //   241: getfield nodeDataTypes : [Lorg/hsqldb/types/Type;
    //   244: iload_3
    //   245: aaload
    //   246: astore #4
    //   248: aload #4
    //   250: ifnonnull -> 260
    //   253: sipush #5567
    //   256: invokestatic error : (I)Lorg/hsqldb/HsqlException;
    //   259: athrow
    //   260: aload #4
    //   262: getfield typeComparisonGroup : I
    //   265: aload_0
    //   266: getfield nodes : [Lorg/hsqldb/Expression;
    //   269: iconst_1
    //   270: aaload
    //   271: getfield nodeDataTypes : [Lorg/hsqldb/types/Type;
    //   274: iload_3
    //   275: aaload
    //   276: getfield typeComparisonGroup : I
    //   279: if_icmpeq -> 289
    //   282: sipush #5563
    //   285: invokestatic error : (I)Lorg/hsqldb/HsqlException;
    //   288: athrow
    //   289: aload_0
    //   290: getfield nodes : [Lorg/hsqldb/Expression;
    //   293: iconst_0
    //   294: aaload
    //   295: getfield nodeDataTypes : [Lorg/hsqldb/types/Type;
    //   298: iload_3
    //   299: aload #4
    //   301: aastore
    //   302: aload_0
    //   303: getfield nodes : [Lorg/hsqldb/Expression;
    //   306: iconst_0
    //   307: aaload
    //   308: getfield nodes : [Lorg/hsqldb/Expression;
    //   311: iload_3
    //   312: aaload
    //   313: aload #4
    //   315: putfield dataType : Lorg/hsqldb/types/Type;
    //   318: iinc #3, 1
    //   321: goto -> 200
    //   324: return
  }
  
  void resolveTypesForIn(Session paramSession) {
    resolveTypesForAllAny(paramSession);
  }
  
  public Object getValue(Session paramSession) {
    Object[] arrayOfObject1;
    Boolean bool1;
    Object object1;
    byte b;
    Object[] arrayOfObject2;
    Boolean bool2;
    Object object2;
    switch (this.opType) {
      case 1:
        return this.valueData;
      case 5:
        return paramSession.sessionContext.rangeIterators[this.rangePosition].getCurrent(this.columnIndex);
      case 31:
        return ((NumberType)this.dataType).negate(this.nodes[0].getValue(paramSession, (this.nodes[0]).dataType));
      case 39:
      case 47:
        switch ((this.nodes[0]).opType) {
          case 22:
          case 23:
          case 25:
            arrayOfObject1 = this.nodes[0].getRowValue(paramSession);
            for (b = 0; b < arrayOfObject1.length; b++) {
              if (arrayOfObject1[b] == null) {
                if (this.opType == 39)
                  return Boolean.FALSE; 
              } else if (this.opType == 47) {
                return Boolean.FALSE;
              } 
            } 
            return Boolean.TRUE;
        } 
        return (this.nodes[0].getValue(paramSession) == null) ? Boolean.TRUE : Boolean.FALSE;
      case 56:
        arrayOfObject1 = this.nodes[0].getRowValue(paramSession);
        arrayOfObject2 = this.nodes[1].getRowValue(paramSession);
        return DateTimeType.overlaps(paramSession, arrayOfObject1, (this.nodes[0]).nodeDataTypes, arrayOfObject2, (this.nodes[1]).nodeDataTypes);
      case 54:
        return testInCondition(paramSession);
      case 59:
      case 60:
      case 61:
      case 62:
      case 63:
      case 64:
        return testMatchCondition(paramSession);
      case 58:
        return testNotDistinctCondition(paramSession);
      case 57:
        this.nodes[0].materialise(paramSession);
        return (this.nodes[0]).table.hasUniqueNotNullRows(paramSession) ? Boolean.TRUE : Boolean.FALSE;
      case 55:
        return testExistsCondition(paramSession);
      case 48:
        bool1 = (Boolean)this.nodes[0].getValue(paramSession);
        return (bool1 == null) ? null : (bool1.booleanValue() ? Boolean.FALSE : Boolean.TRUE);
      case 49:
        bool1 = (Boolean)this.nodes[0].getValue(paramSession);
        if (Boolean.FALSE.equals(bool1))
          return Boolean.FALSE; 
        bool2 = (Boolean)this.nodes[1].getValue(paramSession);
        return Boolean.FALSE.equals(bool2) ? Boolean.FALSE : ((bool1 == null || bool2 == null) ? null : Boolean.TRUE);
      case 50:
        bool1 = (Boolean)this.nodes[0].getValue(paramSession);
        if (Boolean.TRUE.equals(bool1))
          return Boolean.TRUE; 
        bool2 = (Boolean)this.nodes[1].getValue(paramSession);
        return Boolean.TRUE.equals(bool2) ? Boolean.TRUE : ((bool1 == null || bool2 == null) ? null : Boolean.FALSE);
      case 40:
      case 41:
      case 42:
      case 43:
      case 44:
      case 45:
      case 46:
        if (this.exprSubType == 52 || this.exprSubType == 51)
          return testAllAnyCondition(paramSession); 
        object1 = this.nodes[0].getValue(paramSession);
        object2 = this.nodes[1].getValue(paramSession);
        if ((this.nodes[0]).dataType != null && (this.nodes[0]).dataType.isArrayType())
          return compareValues(paramSession, object1, object2); 
        if (object1 instanceof Object[]) {
          if (object2 != null && !(object2 instanceof Object[]))
            throw Error.runtimeError(201, "ExpressionLogical"); 
          return compareValues(paramSession, (Object[])object1, (Object[])object2);
        } 
        if (object2 instanceof Object[])
          object2 = ((Object[])object2)[0]; 
        return compareValues(paramSession, object1, object2);
    } 
    throw Error.runtimeError(201, "ExpressionLogical");
  }
  
  private Boolean compareValues(Session paramSession, Object paramObject1, Object paramObject2) {
    int i = 0;
    if (paramObject1 == null || paramObject2 == null)
      return null; 
    i = (this.nodes[0]).dataType.compare(paramSession, paramObject1, paramObject2, this.opType);
    switch (this.opType) {
      case 40:
        return (i == 0) ? Boolean.TRUE : Boolean.FALSE;
      case 46:
        return (i != 0) ? Boolean.TRUE : Boolean.FALSE;
      case 43:
        return (i > 0) ? Boolean.TRUE : Boolean.FALSE;
      case 41:
      case 42:
        return (i >= 0) ? Boolean.TRUE : Boolean.FALSE;
      case 45:
        return (i <= 0) ? Boolean.TRUE : Boolean.FALSE;
      case 44:
        return (i < 0) ? Boolean.TRUE : Boolean.FALSE;
    } 
    throw Error.runtimeError(201, "ExpressionLogical");
  }
  
  private Boolean compareValues(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2) {
    int i = 0;
    boolean bool = false;
    if (paramArrayOfObject1 == null || paramArrayOfObject2 == null)
      return null; 
    Object[] arrayOfObject1 = paramArrayOfObject1;
    Object[] arrayOfObject2 = paramArrayOfObject2;
    for (byte b = 0; b < (this.nodes[0]).nodes.length; b++) {
      if (arrayOfObject1[b] == null) {
        if (this.opType == 60 || this.opType == 63)
          continue; 
        bool = true;
      } 
      if (arrayOfObject2[b] == null)
        bool = true; 
      Object object1 = arrayOfObject1[b];
      Object object2 = arrayOfObject2[b];
      Type[] arrayOfType = (this.nodes[0]).nodeDataTypes;
      i = arrayOfType[b].compare(paramSession, object1, object2);
      if (i != 0)
        break; 
      continue;
    } 
    switch (this.opType) {
      case 58:
      case 59:
      case 60:
      case 61:
      case 62:
      case 63:
      case 64:
        return (i == 0) ? Boolean.TRUE : Boolean.FALSE;
      case 40:
      case 54:
        return bool ? null : ((i == 0) ? Boolean.TRUE : Boolean.FALSE);
      case 46:
        return bool ? null : ((i != 0) ? Boolean.TRUE : Boolean.FALSE);
      case 43:
        return bool ? null : ((i > 0) ? Boolean.TRUE : Boolean.FALSE);
      case 41:
      case 42:
        return bool ? null : ((i >= 0) ? Boolean.TRUE : Boolean.FALSE);
      case 45:
        return bool ? null : ((i <= 0) ? Boolean.TRUE : Boolean.FALSE);
      case 44:
        return bool ? null : ((i < 0) ? Boolean.TRUE : Boolean.FALSE);
    } 
    throw Error.runtimeError(201, "ExpressionLogical");
  }
  
  private Boolean testInCondition(Session paramSession) {
    Object[] arrayOfObject = this.nodes[0].getRowValue(paramSession);
    if (arrayOfObject == null)
      return null; 
    if (Expression.countNulls(arrayOfObject) != 0)
      return null; 
    if ((this.nodes[1]).opType == 26) {
      int i = (this.nodes[1]).nodes.length;
      for (byte b = 0; b < i; b++) {
        Object[] arrayOfObject1 = (this.nodes[1]).nodes[b].getRowValue(paramSession);
        if (Boolean.TRUE.equals(compareValues(paramSession, arrayOfObject, arrayOfObject1)))
          return Boolean.TRUE; 
      } 
      return Boolean.FALSE;
    } 
    throw Error.runtimeError(201, "ExpressionLogical");
  }
  
  private Boolean testNotDistinctCondition(Session paramSession) {
    Object[] arrayOfObject1 = this.nodes[0].getRowValue(paramSession);
    Object[] arrayOfObject2 = this.nodes[1].getRowValue(paramSession);
    return (arrayOfObject1 == null || arrayOfObject2 == null) ? Boolean.valueOf((arrayOfObject1 == arrayOfObject2)) : compareValues(paramSession, arrayOfObject1, arrayOfObject2);
  }
  
  private Boolean testMatchCondition(Session paramSession) {
    int j;
    PersistentStore persistentStore;
    boolean bool;
    RowIterator rowIterator;
    byte b;
    boolean bool1;
    Object[] arrayOfObject2;
    Object[] arrayOfObject1 = this.nodes[0].getRowValue(paramSession);
    if (arrayOfObject1 == null)
      return Boolean.TRUE; 
    int i = countNulls(arrayOfObject1);
    if (i != 0)
      switch (this.opType) {
        case 59:
        case 62:
          return Boolean.TRUE;
        case 60:
        case 63:
          if (i == arrayOfObject1.length)
            return Boolean.TRUE; 
          break;
        case 61:
        case 64:
          return (i == arrayOfObject1.length) ? Boolean.TRUE : Boolean.FALSE;
      }  
    switch ((this.nodes[1]).opType) {
      case 26:
        j = (this.nodes[1]).nodes.length;
        bool = false;
        for (b = 0; b < j; b++) {
          Object[] arrayOfObject = (this.nodes[1]).nodes[b].getRowValue(paramSession);
          Boolean bool2 = compareValues(paramSession, arrayOfObject1, arrayOfObject);
          if (bool2 != null && bool2.booleanValue())
            switch (this.opType) {
              case 59:
              case 60:
              case 61:
                return Boolean.TRUE;
              case 62:
              case 63:
              case 64:
                if (bool)
                  return Boolean.FALSE; 
                bool = true;
                break;
            }  
        } 
        return bool ? Boolean.TRUE : Boolean.FALSE;
      case 23:
        persistentStore = this.nodes[1].getTable().getRowStore(paramSession);
        this.nodes[1].materialise(paramSession);
        convertToType(paramSession, arrayOfObject1, (this.nodes[0]).nodeDataTypes, (this.nodes[1]).nodeDataTypes);
        if (i != 0 && (this.opType == 60 || this.opType == 63)) {
          bool = false;
          RowIterator rowIterator1 = this.nodes[1].getTable().rowIterator(paramSession);
          while (rowIterator1.hasNext()) {
            Object[] arrayOfObject = rowIterator1.getNextRow().getData();
            Boolean bool2 = compareValues(paramSession, arrayOfObject1, arrayOfObject);
            if (bool2 != null && bool2.booleanValue()) {
              if (this.opType == 60)
                return Boolean.TRUE; 
              if (bool)
                return Boolean.FALSE; 
              bool = true;
            } 
          } 
          return bool ? Boolean.TRUE : Boolean.FALSE;
        } 
        rowIterator = this.nodes[1].getTable().getPrimaryIndex().findFirstRow(paramSession, persistentStore, arrayOfObject1);
        bool1 = rowIterator.hasNext();
        if (!bool1)
          return Boolean.FALSE; 
        switch (this.opType) {
          case 59:
          case 60:
          case 61:
            return Boolean.TRUE;
        } 
        rowIterator.getNextRow();
        bool1 = rowIterator.hasNext();
        if (!bool1)
          return Boolean.TRUE; 
        arrayOfObject2 = rowIterator.getNextRow().getData();
        return Boolean.TRUE.equals(compareValues(paramSession, arrayOfObject1, arrayOfObject2)) ? Boolean.FALSE : Boolean.TRUE;
    } 
    throw Error.runtimeError(201, "ExpressionLogical");
  }
  
  private Boolean testExistsCondition(Session paramSession) {
    this.nodes[0].materialise(paramSession);
    return this.nodes[0].getTable().isEmpty(paramSession) ? Boolean.FALSE : Boolean.TRUE;
  }
  
  private Boolean testAllAnyCondition(Session paramSession) {
    Object[] arrayOfObject = this.nodes[0].getRowValue(paramSession);
    TableDerived tableDerived = (this.nodes[1]).table;
    tableDerived.materialiseCorrelated(paramSession);
    return getAllAnyValue(paramSession, arrayOfObject, tableDerived);
  }
  
  private Boolean getAllAnyValue(Session paramSession, Object[] paramArrayOfObject, TableDerived paramTableDerived) {
    RowIterator rowIterator;
    Row row1;
    Row row2;
    Object[] arrayOfObject1;
    Object[] arrayOfObject2;
    Boolean bool3;
    Boolean bool4;
    TableDerived tableDerived = paramTableDerived;
    boolean bool1 = tableDerived.isEmpty(paramSession);
    Index index = tableDerived.getFullIndex();
    PersistentStore persistentStore = tableDerived.getRowStore(paramSession);
    boolean bool2 = false;
    for (byte b = 0; b < tableDerived.columnCount; b++)
      bool2 |= persistentStore.hasNull(b); 
    switch (this.exprSubType) {
      case 52:
        if (bool1)
          return Boolean.FALSE; 
        if (countNulls(paramArrayOfObject) == paramArrayOfObject.length)
          return null; 
        convertToType(paramSession, paramArrayOfObject, (this.nodes[0]).nodeDataTypes, (this.nodes[1]).nodeDataTypes);
        if (this.opType == 40) {
          rowIterator = index.findFirstRow(paramSession, persistentStore, paramArrayOfObject);
          return rowIterator.hasNext() ? Boolean.TRUE : (bool2 ? null : Boolean.FALSE);
        } 
        if (this.opType == 46) {
          rowIterator = index.firstRow(paramSession, persistentStore, 0);
        } else {
          rowIterator = index.findFirstRowNotNull(paramSession, persistentStore);
        } 
        row1 = rowIterator.getNextRow();
        if (row1 == null)
          return null; 
        arrayOfObject1 = row1.getData();
        row2 = index.lastRow(paramSession, persistentStore, 0).getNextRow();
        arrayOfObject2 = row2.getData();
        bool3 = compareValues(paramSession, paramArrayOfObject, arrayOfObject1);
        bool4 = compareValues(paramSession, paramArrayOfObject, arrayOfObject2);
        switch (this.opType) {
          case 46:
            if (Boolean.TRUE.equals(bool3) || Boolean.TRUE.equals(bool4))
              return Boolean.TRUE; 
            if (Boolean.FALSE.equals(bool3) && Boolean.FALSE.equals(bool4)) {
              rowIterator = index.findFirstRow(paramSession, persistentStore, paramArrayOfObject);
              return Boolean.FALSE;
            } 
            return null;
          case 43:
            return bool3;
          case 41:
          case 42:
            return bool3;
          case 44:
            return bool4;
          case 45:
            return bool4;
        } 
        break;
      case 51:
        if (bool1)
          return Boolean.TRUE; 
        if (countNulls(paramArrayOfObject) == paramArrayOfObject.length)
          return null; 
        rowIterator = index.firstRow(paramSession, persistentStore, 0);
        row1 = rowIterator.getNextRow();
        arrayOfObject1 = row1.getData();
        if (countNulls(arrayOfObject1) == paramArrayOfObject.length)
          return null; 
        convertToType(paramSession, paramArrayOfObject, (this.nodes[0]).nodeDataTypes, (this.nodes[1]).nodeDataTypes);
        rowIterator = index.findFirstRow(paramSession, persistentStore, paramArrayOfObject);
        if (this.opType == 40)
          return rowIterator.hasNext() ? ((persistentStore.elementCount(paramSession) == 1L) ? Boolean.TRUE : Boolean.FALSE) : Boolean.FALSE; 
        if (this.opType == 46)
          return rowIterator.hasNext() ? Boolean.FALSE : Boolean.TRUE; 
        row2 = index.lastRow(paramSession, persistentStore, 0).getNextRow();
        arrayOfObject2 = row2.getData();
        bool3 = compareValues(paramSession, paramArrayOfObject, arrayOfObject1);
        bool4 = compareValues(paramSession, paramArrayOfObject, arrayOfObject2);
        switch (this.opType) {
          case 43:
            return bool4;
          case 41:
          case 42:
            return bool4;
          case 44:
            return bool3;
          case 45:
            return bool3;
        } 
        break;
    } 
    return null;
  }
  
  void distributeOr() {
    if (this.opType != 50)
      return; 
    if ((this.nodes[0]).opType == 49) {
      this.opType = 49;
      ExpressionLogical expressionLogical = new ExpressionLogical(50, (this.nodes[0]).nodes[1], this.nodes[1]);
      (this.nodes[0]).opType = 50;
      (this.nodes[0]).nodes[1] = this.nodes[1];
      this.nodes[1] = expressionLogical;
    } else if ((this.nodes[1]).opType == 49) {
      Expression expression = this.nodes[0];
      this.nodes[0] = this.nodes[1];
      this.nodes[1] = expression;
      distributeOr();
      return;
    } 
    ((ExpressionLogical)this.nodes[0]).distributeOr();
    ((ExpressionLogical)this.nodes[1]).distributeOr();
  }
  
  public boolean isIndexable(RangeVariable paramRangeVariable) {
    switch (this.opType) {
      case 49:
        return (this.nodes[0].isIndexable(paramRangeVariable) || this.nodes[1].isIndexable(paramRangeVariable));
      case 50:
        return (this.nodes[0].isIndexable(paramRangeVariable) && this.nodes[1].isIndexable(paramRangeVariable));
    } 
    Expression expression = getIndexableExpression(paramRangeVariable);
    return (expression != null);
  }
  
  Expression getIndexableExpression(RangeVariable paramRangeVariable) {
    switch (this.opType) {
      case 47:
        return ((this.nodes[0]).opType == 2 && this.nodes[0].isIndexable(paramRangeVariable)) ? this : null;
      case 48:
        return ((this.nodes[0]).opType == 47 && ((this.nodes[0]).nodes[0]).opType == 2 && (this.nodes[0]).nodes[0].isIndexable(paramRangeVariable)) ? this : null;
      case 40:
        if (this.exprSubType == 52) {
          if (this.nodes[1].isCorrelated())
            return null; 
          for (byte b = 0; b < (this.nodes[0]).nodes.length; b++) {
            if (((this.nodes[0]).nodes[b]).opType == 2 && (this.nodes[0]).nodes[b].isIndexable(paramRangeVariable))
              return this; 
          } 
          return null;
        } 
      case 41:
      case 42:
      case 43:
      case 44:
      case 45:
        if (this.exprSubType != 0)
          return null; 
        if (this.nodes[1].isCorrelated())
          return null; 
        if ((this.nodes[0]).opType == 2 && this.nodes[0].isIndexable(paramRangeVariable))
          return this.nodes[1].hasReference(paramRangeVariable) ? null : this; 
        if (this.nodes[0].hasReference(paramRangeVariable))
          return null; 
        if ((this.nodes[1]).opType == 2 && this.nodes[1].isIndexable(paramRangeVariable)) {
          swapCondition();
          return this;
        } 
        return null;
      case 50:
        return isIndexable(paramRangeVariable) ? this : null;
    } 
    return null;
  }
  
  boolean isSimpleBound() {
    if (this.opType == 47)
      return true; 
    if (this.nodes[1] != null) {
      if ((this.nodes[1]).opType == 1)
        return true; 
      if ((this.nodes[1]).opType == 28 && ((FunctionSQL)this.nodes[1]).isValueFunction())
        return true; 
    } 
    return false;
  }
  
  boolean convertToSmaller() {
    switch (this.opType) {
      case 41:
      case 43:
        swapCondition();
        return true;
      case 44:
      case 45:
        return true;
    } 
    return false;
  }
  
  void swapCondition() {
    byte b = 40;
    switch (this.opType) {
      case 41:
      case 42:
        b = 45;
        break;
      case 45:
        b = 41;
        break;
      case 44:
        b = 43;
        break;
      case 43:
        b = 44;
        break;
      case 58:
        b = 58;
        break;
      case 40:
        break;
      default:
        throw Error.runtimeError(201, "ExpressionLogical");
    } 
    this.opType = b;
    Expression expression = this.nodes[0];
    this.nodes[0] = this.nodes[1];
    this.nodes[1] = expression;
  }
  
  boolean reorderComparison(Session paramSession, Expression paramExpression) {
    Expression expression1 = null;
    Expression expression2 = null;
    boolean bool1 = false;
    boolean bool2 = false;
    byte b = 0;
    if ((this.nodes[0]).opType == 32) {
      b = 33;
      bool1 = true;
    } else if ((this.nodes[0]).opType == 33) {
      b = 32;
      bool1 = true;
    } else if ((this.nodes[1]).opType == 32) {
      b = 33;
    } else if ((this.nodes[1]).opType == 33) {
      b = 32;
    } 
    if (b == 0)
      return false; 
    if (bool1) {
      if (((this.nodes[0]).nodes[0]).opType == 2) {
        expression1 = (this.nodes[0]).nodes[0];
        expression2 = (this.nodes[0]).nodes[1];
      } else if (((this.nodes[0]).nodes[1]).opType == 2) {
        bool2 = (b == 32) ? true : false;
        expression1 = (this.nodes[0]).nodes[1];
        expression2 = (this.nodes[0]).nodes[0];
      } 
    } else if (((this.nodes[1]).nodes[0]).opType == 2) {
      expression1 = (this.nodes[1]).nodes[0];
      expression2 = (this.nodes[1]).nodes[1];
    } else if (((this.nodes[1]).nodes[1]).opType == 2) {
      bool2 = (b == 32) ? true : false;
      expression1 = (this.nodes[1]).nodes[1];
      expression2 = (this.nodes[1]).nodes[0];
    } 
    if (expression1 == null)
      return false; 
    Expression expression3 = bool1 ? this.nodes[1] : this.nodes[0];
    ExpressionArithmetic expressionArithmetic = null;
    if (!bool2) {
      expressionArithmetic = new ExpressionArithmetic(b, expression3, expression2);
      expressionArithmetic.resolveTypesForArithmetic(paramSession, paramExpression);
    } 
    if (bool1) {
      if (bool2) {
        this.nodes[1] = expression1;
        (this.nodes[0]).nodes[1] = expression3;
        ((ExpressionArithmetic)this.nodes[0]).resolveTypesForArithmetic(paramSession, paramExpression);
      } else {
        this.nodes[0] = expression1;
        this.nodes[1] = expressionArithmetic;
      } 
    } else if (bool2) {
      this.nodes[0] = expression1;
      (this.nodes[1]).nodes[1] = expression3;
      ((ExpressionArithmetic)this.nodes[1]).resolveTypesForArithmetic(paramSession, paramExpression);
    } else {
      this.nodes[1] = expression1;
      this.nodes[0] = expressionArithmetic;
    } 
    return true;
  }
  
  boolean isConditionRangeVariable(RangeVariable paramRangeVariable) {
    return (this.nodes[0].getRangeVariable() == paramRangeVariable) ? true : ((this.nodes[1].getRangeVariable() == paramRangeVariable));
  }
  
  RangeVariable[] getJoinRangeVariables(RangeVariable[] paramArrayOfRangeVariable) {
    OrderedHashSet orderedHashSet = collectRangeVariables(paramArrayOfRangeVariable, null);
    if (orderedHashSet != null) {
      this.rangeArray = new RangeVariable[orderedHashSet.size()];
      orderedHashSet.toArray((Object[])this.rangeArray);
    } 
    return this.rangeArray;
  }
  
  double costFactor(Session paramSession, RangeVariable paramRangeVariable, int paramInt) {
    PersistentStore persistentStore;
    switch (this.opType) {
      case 50:
        return this.nodes[0].costFactor(paramSession, paramRangeVariable, this.opType) + this.nodes[1].costFactor(paramSession, paramRangeVariable, this.opType);
      case 54:
      case 56:
      case 58:
      case 59:
      case 60:
      case 61:
      case 62:
      case 63:
      case 64:
        persistentStore = paramRangeVariable.rangeTable.getRowStore(paramSession);
        null = persistentStore.elementCount();
        if (null < 16.0D)
          null = 16.0D; 
        return null;
      case 47:
      case 48:
        return costFactorUnaryColumn(paramSession, paramRangeVariable);
      case 40:
        switch (this.exprSubType) {
          case 52:
            if ((this.nodes[0]).opType == 2 && this.nodes[0].getRangeVariable() == paramRangeVariable) {
              double d = costFactorColumns(paramSession, paramRangeVariable);
              d *= 1024.0D;
              return d;
            } 
          case 51:
            persistentStore = paramRangeVariable.rangeTable.getRowStore(paramSession);
            null = persistentStore.elementCount();
            if (null < 16.0D)
              null = 16.0D; 
            null *= 1024.0D;
            return null;
        } 
        return costFactorColumns(paramSession, paramRangeVariable);
      case 41:
      case 42:
      case 43:
      case 44:
      case 45:
        return costFactorColumns(paramSession, paramRangeVariable);
    } 
    throw Error.runtimeError(201, "ExpressionLogical");
  }
  
  double costFactorUnaryColumn(Session paramSession, RangeVariable paramRangeVariable) {
    if ((this.nodes[0]).opType == 2 && this.nodes[0].getRangeVariable() == paramRangeVariable)
      return this.nodes[0].costFactor(paramSession, paramRangeVariable, this.opType); 
    PersistentStore persistentStore = paramRangeVariable.rangeTable.getRowStore(paramSession);
    double d = persistentStore.elementCount();
    return (d < 16.0D) ? 16.0D : d;
  }
  
  double costFactorColumns(Session paramSession, RangeVariable paramRangeVariable) {
    double d = 0.0D;
    if ((this.nodes[0]).opType == 2 && this.nodes[0].getRangeVariable() == paramRangeVariable) {
      if (!this.nodes[1].hasReference(paramRangeVariable))
        d = this.nodes[0].costFactor(paramSession, paramRangeVariable, this.opType); 
    } else if ((this.nodes[1]).opType == 2 && this.nodes[1].getRangeVariable() == paramRangeVariable) {
      if (!this.nodes[0].hasReference(paramRangeVariable))
        d = this.nodes[1].costFactor(paramSession, paramRangeVariable, this.opType); 
    } else {
      PersistentStore persistentStore = paramRangeVariable.rangeTable.getRowStore(paramSession);
      d = persistentStore.elementCount();
    } 
    if (d == 0.0D) {
      PersistentStore persistentStore = paramRangeVariable.rangeTable.getRowStore(paramSession);
      d = persistentStore.elementCount();
    } 
    if (d < 16.0D)
      d = 16.0D; 
    return d;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\ExpressionLogical.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */