package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.map.ValuePool;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BinaryType;
import org.hsqldb.types.BlobData;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.Type;

public class ExpressionOp extends Expression {
  static final ExpressionOp limitOneExpression = new ExpressionOp(95, new ExpressionValue(ValuePool.INTEGER_0, (Type)Type.SQL_INTEGER), new ExpressionValue(ValuePool.INTEGER_1, (Type)Type.SQL_INTEGER));
  
  ExpressionOp(int paramInt, Expression[] paramArrayOfExpression) {
    super(paramInt);
    switch (this.opType) {
      case 86:
        this.nodes = paramArrayOfExpression;
        return;
    } 
    throw Error.runtimeError(201, "ExpressionOp");
  }
  
  ExpressionOp(int paramInt, Expression paramExpression1, Expression paramExpression2) {
    super(paramInt);
    this.nodes = new Expression[2];
    this.nodes[0] = paramExpression1;
    this.nodes[1] = paramExpression2;
    switch (this.opType) {
      case 37:
      case 92:
      case 93:
      case 95:
      case 96:
        return;
      case 84:
        this.dataType = paramExpression1.dataType;
        return;
    } 
    throw Error.runtimeError(201, "ExpressionOp");
  }
  
  ExpressionOp(Expression paramExpression, Type paramType) {
    super(91);
    this.nodes = new Expression[1];
    this.nodes[0] = paramExpression;
    this.dataType = paramType;
    this.alias = paramExpression.alias;
  }
  
  ExpressionOp(Expression paramExpression) {
    super(paramExpression.dataType.isDateTimeTypeWithZone() ? 91 : 92);
    switch (paramExpression.dataType.typeCode) {
      case 94:
        this.nodes = new Expression[1];
        this.nodes[0] = new ExpressionOp(92, paramExpression, null);
        (this.nodes[0]).dataType = paramExpression.dataType;
        this.dataType = (Type)DateTimeType.getDateTimeType(92, paramExpression.dataType.scale);
        break;
      case 95:
        this.nodes = new Expression[1];
        this.nodes[0] = new ExpressionOp(92, paramExpression, null);
        (this.nodes[0]).dataType = paramExpression.dataType;
        this.dataType = (Type)DateTimeType.getDateTimeType(93, paramExpression.dataType.scale);
        break;
      case 92:
        this.nodes = new Expression[2];
        this.nodes[0] = paramExpression;
        (this.nodes[0]).dataType = paramExpression.dataType;
        this.dataType = (Type)DateTimeType.getDateTimeType(94, paramExpression.dataType.scale);
        break;
      case 93:
        this.nodes = new Expression[2];
        this.nodes[0] = paramExpression;
        (this.nodes[0]).dataType = paramExpression.dataType;
        this.dataType = (Type)DateTimeType.getDateTimeType(95, paramExpression.dataType.scale);
        break;
      default:
        throw Error.runtimeError(201, "ExpressionOp");
    } 
    this.alias = paramExpression.alias;
  }
  
  public static Expression getCastExpression(Session paramSession, Expression paramExpression, Type paramType) {
    if (paramExpression.getType() == 1) {
      Object object = paramType.castToType(paramSession, paramExpression.getValue(paramSession), paramExpression.getDataType());
      return new ExpressionValue(object, paramType);
    } 
    return new ExpressionOp(paramExpression, paramType);
  }
  
  public String getSQL() {
    byte b;
    StringBuffer stringBuffer = new StringBuffer(64);
    String str1 = getContextSQL((this.nodes.length > 0) ? this.nodes[0] : null);
    String str2 = getContextSQL((this.nodes.length > 1) ? this.nodes[1] : null);
    switch (this.opType) {
      case 1:
        if (this.valueData == null)
          return "NULL"; 
        if (this.dataType == null)
          throw Error.runtimeError(201, "ExpressionOp"); 
        return this.dataType.convertToSQLString(this.valueData);
      case 37:
        stringBuffer.append(' ').append("LIKE").append(' ');
        stringBuffer.append(str1).append(' ').append(str2).append(' ');
      case 91:
        stringBuffer.append(' ').append("CAST").append('(');
        stringBuffer.append(str1).append(' ').append("AS").append(' ');
        stringBuffer.append(this.dataType.getTypeDefinition());
        stringBuffer.append(')');
        return stringBuffer.toString();
      case 93:
        stringBuffer.append(' ').append("CASEWHEN").append('(');
        stringBuffer.append(str1).append(',').append(str2).append(')');
        return stringBuffer.toString();
      case 96:
        stringBuffer.append(str1).append(',').append(str2);
        return stringBuffer.toString();
      case 95:
        if (str1 != null) {
          stringBuffer.append(' ').append("OFFSET").append(' ');
          stringBuffer.append(str1).append(' ');
        } 
        if (str2 != null) {
          stringBuffer.append(' ').append("FETCH").append(' ');
          stringBuffer.append("FIRST");
          stringBuffer.append(str2).append(' ').append(str2).append(' ');
          stringBuffer.append("ROWS").append(' ').append("ONLY");
          stringBuffer.append(' ');
        } 
        return stringBuffer.toString();
      case 92:
        stringBuffer.append(str1).append(' ').append("AT").append(' ');
        if (this.nodes[1] == null) {
          stringBuffer.append("LOCAL").append(' ');
        } else {
          stringBuffer.append("TIME").append(' ').append("ZONE");
          stringBuffer.append(' ');
          stringBuffer.append(str2);
        } 
        return stringBuffer.toString();
      case 86:
        stringBuffer.append("CONCAT_WS").append(816);
        stringBuffer.append(str1);
        for (b = 0; b < this.nodes.length; b++)
          stringBuffer.append(',').append(this.nodes[b].getSQL()); 
        stringBuffer.append(802);
        return stringBuffer.toString();
    } 
    throw Error.runtimeError(201, "ExpressionOp");
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
      case 37:
        stringBuffer.append("LIKE").append(' ').append("ARG ");
        stringBuffer.append(this.dataType.getTypeDefinition());
        stringBuffer.append(' ');
        break;
      case 26:
        stringBuffer.append("VALUE").append(' ').append("LIST ");
        for (b = 0; b < this.nodes.length; b++) {
          stringBuffer.append(this.nodes[b].describe(paramSession, paramInt + 1));
          stringBuffer.append(' ');
        } 
        return stringBuffer.toString();
      case 91:
        stringBuffer.append("CAST").append(' ');
        stringBuffer.append(this.dataType.getTypeDefinition());
        stringBuffer.append(' ');
        break;
      case 93:
        stringBuffer.append("CASEWHEN").append(' ');
        break;
      case 86:
        stringBuffer.append("CONCAT_WS").append(' ');
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
    switch (this.opType) {
      case 93:
        paramBoolean = false;
        break;
    } 
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        paramHsqlList = this.nodes[b].resolveColumnReferences(paramSession, paramRangeGroup, paramInt, paramArrayOfRangeGroup, paramHsqlList, paramBoolean); 
    } 
    return paramHsqlList;
  }
  
  public void resolveTypes(Session paramSession, Expression paramExpression) {
    byte b2;
    Expression expression;
    byte b1;
    Type type;
    switch (this.opType) {
      case 93:
        break;
      default:
        for (b2 = 0; b2 < this.nodes.length; b2++) {
          if (this.nodes[b2] != null)
            this.nodes[b2].resolveTypes(paramSession, this); 
        } 
        break;
    } 
    switch (this.opType) {
      case 1:
        return;
      case 37:
        this.dataType = (this.nodes[0]).dataType;
        if ((this.nodes[0]).opType == 1 && (this.nodes[1] == null || (this.nodes[1]).opType == 1))
          setAsConstantValue(paramSession, paramExpression); 
      case 91:
        expression = this.nodes[0];
        type = expression.dataType;
        if (type != null && !this.dataType.canConvertFrom(type))
          throw Error.error(5561); 
        if (expression.opType == 1) {
          setAsConstantValue(paramSession, paramExpression);
          expression.dataType = this.dataType;
          expression.valueData = this.valueData;
          if (paramExpression != null)
            paramExpression.replaceNode(this, expression); 
        } else if ((this.nodes[0]).opType == 8) {
          expression.dataType = this.dataType;
        } 
      case 92:
        if ((this.nodes[0]).dataType == null)
          throw Error.error(5567); 
        if (this.nodes[1] != null) {
          if ((this.nodes[1]).dataType == null)
            (this.nodes[1]).dataType = (Type)Type.SQL_INTERVAL_HOUR_TO_MINUTE; 
          if ((this.nodes[1]).dataType.typeCode != 111)
            if ((this.nodes[1]).opType == 1) {
              (this.nodes[1]).valueData = Type.SQL_INTERVAL_HOUR_TO_MINUTE.castToType(paramSession, (this.nodes[1]).valueData, (this.nodes[1]).dataType);
              (this.nodes[1]).dataType = (Type)Type.SQL_INTERVAL_HOUR_TO_MINUTE;
            } else {
              throw Error.error(5563);
            }  
        } 
        switch ((this.nodes[0]).dataType.typeCode) {
          case 92:
            this.dataType = (Type)DateTimeType.getDateTimeType(94, (this.nodes[0]).dataType.scale);
          case 93:
            this.dataType = (Type)DateTimeType.getDateTimeType(95, (this.nodes[0]).dataType.scale);
          case 94:
          case 95:
            this.dataType = (this.nodes[0]).dataType;
        } 
        throw Error.error(5563);
      case 93:
        resolveTypesForCaseWhen(paramSession);
      case 86:
        for (b1 = 0; b1 < this.nodes.length; b1++)
          (this.nodes[b1]).dataType = (Type)Type.SQL_VARCHAR_DEFAULT; 
        this.dataType = (Type)Type.SQL_VARCHAR_DEFAULT;
      case 96:
        return;
      case 95:
        if (this.nodes[0] != null) {
          if ((this.nodes[0]).dataType == null)
            throw Error.error(5567); 
          if (!(this.nodes[0]).dataType.isIntegralType())
            throw Error.error(5563); 
        } 
        if (this.nodes[1] != null) {
          if ((this.nodes[1]).dataType == null)
            throw Error.error(5567); 
          if (!(this.nodes[1]).dataType.isIntegralType())
            throw Error.error(5563); 
        } 
      case 84:
        return;
    } 
    throw Error.runtimeError(201, "ExpressionOp");
  }
  
  void resolveTypesForCaseWhen(Session paramSession) {
    if (this.dataType != null)
      return; 
    ExpressionOp expressionOp = this;
    while (expressionOp.opType == 93) {
      expressionOp.nodes[0].resolveTypes(paramSession, expressionOp);
      if (expressionOp.nodes[0].isUnresolvedParam())
        (expressionOp.nodes[0]).dataType = (Type)Type.SQL_BOOLEAN; 
      (expressionOp.nodes[1]).nodes[0].resolveTypes(paramSession, expressionOp.nodes[1]);
      if (((expressionOp.nodes[1]).nodes[1]).opType != 93)
        (expressionOp.nodes[1]).nodes[1].resolveTypes(paramSession, expressionOp.nodes[1]); 
      Expression expression = (expressionOp.nodes[1]).nodes[1];
    } 
    if (this.exprSubType == 91 && ((this.nodes[1]).nodes[1]).dataType != null && ((this.nodes[1]).nodes[1]).dataType != ((this.nodes[1]).nodes[0]).dataType) {
      CharacterType characterType;
      Type type = ((this.nodes[1]).nodes[1]).dataType;
      if (type.isCharacterType())
        characterType = Type.SQL_VARCHAR_DEFAULT; 
      (this.nodes[1]).nodes[0] = new ExpressionOp((this.nodes[1]).nodes[0], (Type)characterType);
    } 
    expressionOp = this;
    while (expressionOp.opType == 93) {
      this.dataType = Type.getAggregateType(((expressionOp.nodes[1]).nodes[0]).dataType, this.dataType);
      this.dataType = Type.getAggregateType(((expressionOp.nodes[1]).nodes[1]).dataType, this.dataType);
      Expression expression = (expressionOp.nodes[1]).nodes[1];
    } 
    expressionOp = this;
    while (expressionOp.opType == 93) {
      if (((expressionOp.nodes[1]).nodes[0]).dataType == null)
        ((expressionOp.nodes[1]).nodes[0]).dataType = this.dataType; 
      if (((expressionOp.nodes[1]).nodes[1]).dataType == null)
        ((expressionOp.nodes[1]).nodes[1]).dataType = this.dataType; 
      if ((expressionOp.nodes[1]).dataType == null)
        (expressionOp.nodes[1]).dataType = this.dataType; 
      Expression expression = (expressionOp.nodes[1]).nodes[1];
    } 
    if (this.dataType == null || this.dataType.typeCode == 0)
      throw Error.error(5567); 
  }
  
  public Object getValue(Session paramSession) {
    boolean bool1;
    BinaryData binaryData;
    Object object1;
    int i;
    long l1;
    StringBuffer stringBuffer;
    Object object2;
    String str;
    boolean bool2;
    long l2;
    char[] arrayOfChar1;
    BinaryType binaryType;
    byte b1;
    char[] arrayOfChar2;
    boolean bool3;
    byte b2;
    byte b3;
    byte b4;
    switch (this.opType) {
      case 1:
        return this.valueData;
      case 37:
        bool1 = (this.nodes[1] != null) ? true : false;
        i = Integer.MAX_VALUE;
        if (this.dataType.isBinaryType()) {
          BinaryData binaryData1 = (BinaryData)this.nodes[0].getValue(paramSession);
          if (binaryData1 == null)
            return null; 
          if (bool1) {
            BinaryData binaryData2 = (BinaryData)this.nodes[1].getValue(paramSession);
            if (binaryData2 == null)
              return null; 
            if (binaryData2.length(paramSession) != 1L)
              throw Error.error(3412); 
            i = binaryData2.getBytes()[0];
          } 
          byte[] arrayOfByte1 = binaryData1.getBytes();
          byte[] arrayOfByte2 = new byte[arrayOfByte1.length];
          boolean bool = false;
          byte b5 = 0;
          byte b6 = 0;
          byte b7 = 0;
          while (b6 < arrayOfByte1.length) {
            if (arrayOfByte1[b6] == i) {
              if (bool) {
                b5++;
                arrayOfByte2[b7++] = arrayOfByte1[b6];
                bool = false;
              } else {
                bool = true;
                if (b6 == arrayOfByte1.length - 1)
                  throw Error.error(3458); 
              } 
            } else if (arrayOfByte1[b6] == 95 || arrayOfByte1[b6] == 37) {
              if (bool) {
                b5++;
                arrayOfByte2[b7++] = arrayOfByte1[b6];
                bool = false;
              } else {
                break;
              } 
            } else {
              if (bool)
                throw Error.error(3458); 
              arrayOfByte2[b7++] = arrayOfByte1[b6];
            } 
            b6++;
          } 
          arrayOfByte2 = (byte[])ArrayUtil.resizeArrayIfDifferent(arrayOfByte2, b7);
          return new BinaryData(arrayOfByte2, false);
        } 
        str = (String)Type.SQL_VARCHAR.convertToType(paramSession, this.nodes[0].getValue(paramSession), this.nodes[0].getDataType());
        if (str == null)
          return null; 
        if (bool1) {
          String str1 = (String)Type.SQL_VARCHAR.convertToType(paramSession, this.nodes[1].getValue(paramSession), this.nodes[1].getDataType());
          if (str1 == null)
            return null; 
          if (str1.length() != 1)
            throw Error.error(3439); 
          i = str1.getBytes()[0];
        } 
        arrayOfChar1 = str.toCharArray();
        arrayOfChar2 = new char[arrayOfChar1.length];
        bool3 = false;
        b2 = 0;
        b3 = 0;
        b4 = 0;
        while (b3 < arrayOfChar1.length) {
          if (arrayOfChar1[b3] == i) {
            if (bool3) {
              b2++;
              arrayOfChar2[b4++] = arrayOfChar1[b3];
              bool3 = false;
            } else {
              bool3 = true;
              if (b3 == arrayOfChar1.length - 1)
                throw Error.error(3458); 
            } 
          } else if (arrayOfChar1[b3] == '_' || arrayOfChar1[b3] == '%') {
            if (bool3) {
              b2++;
              arrayOfChar2[b4++] = arrayOfChar1[b3];
              bool3 = false;
            } else {
              break;
            } 
          } else {
            if (bool3)
              throw Error.error(3458); 
            arrayOfChar2[b4++] = arrayOfChar1[b3];
          } 
          b3++;
        } 
        return new String(arrayOfChar2, 0, b4);
      case 5:
        return paramSession.sessionContext.rangeIterators[this.rangePosition].getCurrent(this.columnIndex);
      case 94:
        return this.nodes[0].getValue(paramSession);
      case 84:
        if ((this.nodes[0]).dataType.isCharacterType()) {
          Object object = this.nodes[1].getValue(paramSession);
          if (object == null)
            return null; 
          CharacterType characterType = (CharacterType)(this.nodes[1]).dataType;
          long l = ((CharacterType)(this.nodes[1]).dataType).size(paramSession, object);
          characterType = (CharacterType)(this.nodes[0]).dataType;
          object = this.nodes[0].getValue(paramSession);
          return (object == null) ? null : characterType.substring(paramSession, object, 0L, l, true, false);
        } 
        binaryData = (BinaryData)this.nodes[1].getValue(paramSession);
        if (binaryData == null)
          return null; 
        l1 = binaryData.length(paramSession);
        binaryType = (BinaryType)(this.nodes[0]).dataType;
        binaryData = (BinaryData)this.nodes[0].getValue(paramSession);
        return (binaryData == null) ? null : binaryType.substring(paramSession, (BlobData)binaryData, 0L, l1, true);
      case 91:
        object1 = this.dataType.castToType(paramSession, this.nodes[0].getValue(paramSession), (this.nodes[0]).dataType);
        if (this.dataType.userTypeModifier != null) {
          Constraint[] arrayOfConstraint = this.dataType.userTypeModifier.getConstraints();
          for (byte b = 0; b < arrayOfConstraint.length; b++)
            arrayOfConstraint[b].checkCheckConstraint(paramSession, null, null, object1); 
        } 
        return object1;
      case 93:
        object1 = this.nodes[0].getValue(paramSession);
        return Boolean.TRUE.equals(object1) ? (this.nodes[1]).nodes[0].getValue(paramSession, this.dataType) : (this.nodes[1]).nodes[1].getValue(paramSession, this.dataType);
      case 86:
        object1 = this.nodes[0].getValue(paramSession);
        if (object1 == null)
          return null; 
        stringBuffer = new StringBuffer("");
        bool2 = false;
        for (b1 = 1; b1 < this.nodes.length; b1++) {
          String str1 = (String)this.nodes[b1].getValue(paramSession);
          if (str1 != null) {
            if (bool2)
              stringBuffer.append((String)object1); 
            stringBuffer.append(str1);
            bool2 = true;
          } 
        } 
        return stringBuffer.toString();
      case 92:
        object1 = this.nodes[0].getValue(paramSession);
        object2 = (this.nodes[1] == null) ? null : this.nodes[1].getValue(paramSession);
        if (object1 == null)
          return null; 
        if (this.nodes[1] != null && object2 == null)
          return null; 
        l2 = (this.nodes[1] == null) ? paramSession.getZoneSeconds() : ((IntervalType)(this.nodes[1]).dataType).getSeconds(object2);
        return ((DateTimeType)this.dataType).changeZone(object1, (this.nodes[0]).dataType, (int)l2, paramSession.getZoneSeconds());
    } 
    throw Error.runtimeError(201, "ExpressionOp");
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\ExpressionOp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */