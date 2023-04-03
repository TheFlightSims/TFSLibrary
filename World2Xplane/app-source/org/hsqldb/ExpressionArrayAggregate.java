package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayListIdentity;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.RowType;
import org.hsqldb.types.Type;

public class ExpressionArrayAggregate extends Expression {
  boolean isDistinctAggregate;
  
  SortAndSlice sort;
  
  String separator = ",";
  
  ArrayType arrayDataType;
  
  Type exprType;
  
  Expression condition = Expression.EXPR_TRUE;
  
  ExpressionArrayAggregate(int paramInt, boolean paramBoolean, Expression paramExpression, SortAndSlice paramSortAndSlice, String paramString) {
    super(paramInt);
    this.isDistinctAggregate = paramBoolean;
    this.sort = paramSortAndSlice;
    if (paramString != null)
      this.separator = paramString; 
    if (paramInt == 85) {
      this.nodes = new Expression[] { paramExpression };
      return;
    } 
    if (paramSortAndSlice == null) {
      this.nodes = new Expression[] { paramExpression };
    } else {
      HsqlArrayList hsqlArrayList = paramSortAndSlice.getExpressionList();
      this.nodes = new Expression[hsqlArrayList.size() + 1];
      hsqlArrayList.toArray(this.nodes);
      this.nodes[hsqlArrayList.size()] = paramExpression;
      paramSortAndSlice.prepare(1);
    } 
  }
  
  boolean isSelfAggregate() {
    return true;
  }
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer(64);
    String str = getContextSQL((this.nodes.length > 0) ? this.nodes[0] : null);
    switch (this.opType) {
      case 82:
        stringBuffer.append(' ').append("ARRAY_AGG").append('(');
        stringBuffer.append(str).append(')');
        return stringBuffer.toString();
      case 83:
        stringBuffer.append(' ').append("GROUP_CONCAT").append('(');
        stringBuffer.append(str).append(')');
        return stringBuffer.toString();
      case 85:
        stringBuffer.append(' ').append("MEDIAN").append('(');
        stringBuffer.append(str).append(')');
        return stringBuffer.toString();
    } 
    throw Error.runtimeError(201, "ExpressionAggregate");
  }
  
  protected String describe(Session paramSession, int paramInt) {
    StringBuffer stringBuffer = new StringBuffer(64);
    stringBuffer.append('\n');
    for (byte b = 0; b < paramInt; b++)
      stringBuffer.append(' '); 
    switch (this.opType) {
      case 82:
        stringBuffer.append("ARRAY_AGG").append(' ');
        break;
      case 83:
        stringBuffer.append("GROUP_CONCAT").append(' ');
        break;
      case 85:
        stringBuffer.append("MEDIAN").append(' ');
        break;
    } 
    if (getLeftNode() != null) {
      stringBuffer.append(" arg=[");
      stringBuffer.append(this.nodes[0].describe(paramSession, paramInt + 1));
      stringBuffer.append(']');
    } 
    return stringBuffer.toString();
  }
  
  public HsqlList resolveColumnReferences(Session paramSession, RangeGroup paramRangeGroup, int paramInt, RangeGroup[] paramArrayOfRangeGroup, HsqlList paramHsqlList, boolean paramBoolean) {
    ArrayListIdentity arrayListIdentity;
    HsqlList hsqlList = this.condition.resolveColumnReferences(paramSession, paramRangeGroup, paramInt, paramArrayOfRangeGroup, null, false);
    if (hsqlList != null)
      ExpressionColumn.checkColumnsResolved(hsqlList); 
    if (paramHsqlList == null)
      arrayListIdentity = new ArrayListIdentity(); 
    arrayListIdentity.add(this);
    return (HsqlList)arrayListIdentity;
  }
  
  public void resolveTypes(Session paramSession, Expression paramExpression) {
    this.nodeDataTypes = new Type[this.nodes.length];
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null) {
        this.nodes[b].resolveTypes(paramSession, this);
        if (this.nodes[b].isUnresolvedParam())
          throw Error.error(5567); 
        if ((this.nodes[b]).dataType == null)
          throw Error.error(5567); 
        this.nodeDataTypes[b] = (this.nodes[b]).dataType;
      } 
    } 
    this.exprType = (this.nodes[this.nodes.length - 1]).dataType;
    if (this.exprType.isLobType())
      throw Error.error(5534); 
    if (this.exprType.isArrayType())
      throw Error.error(5534); 
    RowType rowType = new RowType(this.nodeDataTypes);
    switch (this.opType) {
      case 82:
        this.arrayDataType = new ArrayType((Type)rowType, 1024);
        this.dataType = (Type)new ArrayType(this.exprType, 1024);
        break;
      case 83:
        this.arrayDataType = new ArrayType((Type)rowType, 1024);
        this.dataType = (Type)Type.SQL_VARCHAR_DEFAULT;
        break;
      case 85:
        this.arrayDataType = new ArrayType(this.nodeDataTypes[0], 1024);
        this.dataType = SetFunction.getType(paramSession, 85, this.exprType);
        if (!this.exprType.isNumberType())
          throw Error.error(5563); 
        break;
    } 
    this.condition.resolveTypes(paramSession, null);
  }
  
  public boolean equals(Expression paramExpression) {
    if (!(paramExpression instanceof ExpressionArrayAggregate))
      return false; 
    ExpressionArrayAggregate expressionArrayAggregate = (ExpressionArrayAggregate)paramExpression;
    return (this.opType == paramExpression.opType && this.exprSubType == paramExpression.exprSubType && this.isDistinctAggregate == expressionArrayAggregate.isDistinctAggregate && this.separator.equals(expressionArrayAggregate.separator) && this.condition.equals(expressionArrayAggregate.condition)) ? super.equals(paramExpression) : false;
  }
  
  public Object updateAggregatingValue(Session paramSession, Object paramObject) {
    Object object;
    Object[] arrayOfObject2;
    byte b;
    if (!this.condition.testCondition(paramSession))
      return paramObject; 
    Object[] arrayOfObject1 = null;
    switch (this.opType) {
      case 82:
      case 83:
        arrayOfObject2 = new Object[this.nodes.length];
        for (b = 0; b < this.nodes.length; b++)
          arrayOfObject2[b] = this.nodes[b].getValue(paramSession); 
        if (this.opType == 83 && arrayOfObject2[arrayOfObject2.length - 1] == null)
          return paramObject; 
        arrayOfObject1 = arrayOfObject2;
        break;
      case 85:
        object = this.nodes[0].getValue(paramSession);
        break;
    } 
    HsqlArrayList hsqlArrayList = (HsqlArrayList)paramObject;
    if (hsqlArrayList == null)
      hsqlArrayList = new HsqlArrayList(); 
    hsqlArrayList.add(object);
    return hsqlArrayList;
  }
  
  public Object getAggregatedValue(Session paramSession, Object paramObject) {
    Object[] arrayOfObject2;
    StringBuffer stringBuffer;
    SortAndSlice sortAndSlice;
    byte b;
    if (paramObject == null)
      return null; 
    HsqlArrayList hsqlArrayList = (HsqlArrayList)paramObject;
    Object[] arrayOfObject1 = hsqlArrayList.toArray();
    if (this.isDistinctAggregate) {
      SortAndSlice sortAndSlice1 = new SortAndSlice();
      sortAndSlice1.prepareSingleColumn(this.nodes.length - 1);
      this.arrayDataType.sort(paramSession, arrayOfObject1, sortAndSlice1);
      int i = this.arrayDataType.deDuplicate(paramSession, arrayOfObject1, sortAndSlice1);
      arrayOfObject1 = (Object[])ArrayUtil.resizeArrayIfDifferent(arrayOfObject1, i);
    } 
    if (this.sort != null)
      this.arrayDataType.sort(paramSession, arrayOfObject1, this.sort); 
    switch (this.opType) {
      case 82:
        arrayOfObject2 = new Object[arrayOfObject1.length];
        for (b = 0; b < hsqlArrayList.size(); b++) {
          Object[] arrayOfObject = (Object[])arrayOfObject1[b];
          arrayOfObject2[b] = arrayOfObject[arrayOfObject.length - 1];
        } 
        return arrayOfObject2;
      case 83:
        stringBuffer = new StringBuffer(16 * hsqlArrayList.size());
        for (b = 0; b < arrayOfObject1.length; b++) {
          if (b > 0)
            stringBuffer.append(this.separator); 
          Object[] arrayOfObject = (Object[])arrayOfObject1[b];
          Object object = arrayOfObject[arrayOfObject.length - 1];
          String str = (String)Type.SQL_VARCHAR.convertToType(paramSession, object, this.exprType);
          stringBuffer.append(str);
        } 
        return stringBuffer.toString();
      case 85:
        sortAndSlice = new SortAndSlice();
        sortAndSlice.prepareSingleColumn(1);
        this.arrayDataType.sort(paramSession, arrayOfObject1, sortAndSlice);
        b = (arrayOfObject1.length % 2 == 0) ? 1 : 0;
        if (b != 0) {
          Object object1 = arrayOfObject1[arrayOfObject1.length / 2 - 1];
          Object object2 = arrayOfObject1[arrayOfObject1.length / 2];
          Object object3 = ((NumberType)this.dataType).add(paramSession, object1, object2, this.dataType);
          return ((NumberType)this.dataType).divide(paramSession, object3, Integer.valueOf(2));
        } 
        return this.dataType.convertToType(paramSession, arrayOfObject1[arrayOfObject1.length / 2], this.exprType);
    } 
    return null;
  }
  
  public Expression getCondition() {
    return this.condition;
  }
  
  public boolean hasCondition() {
    return (this.condition != null && !this.condition.isTrue());
  }
  
  public void setCondition(Expression paramExpression) {
    this.condition = paramExpression;
  }
  
  public Expression duplicate() {
    ExpressionArrayAggregate expressionArrayAggregate = (ExpressionArrayAggregate)super.duplicate();
    if (this.condition != null)
      expressionArrayAggregate.condition = this.condition.duplicate(); 
    return expressionArrayAggregate;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\ExpressionArrayAggregate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */