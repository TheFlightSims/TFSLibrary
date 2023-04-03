package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.types.Type;

public class ExpressionAccessor extends Expression {
  ExpressionAccessor(Expression paramExpression1, Expression paramExpression2) {
    super(99);
    this.nodes = new Expression[] { paramExpression1, paramExpression2 };
  }
  
  public ColumnSchema getColumn() {
    return this.nodes[0].getColumn();
  }
  
  public HsqlList resolveColumnReferences(Session paramSession, RangeGroup paramRangeGroup, int paramInt, RangeGroup[] paramArrayOfRangeGroup, HsqlList paramHsqlList, boolean paramBoolean) {
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
    if ((this.nodes[0]).dataType == null)
      throw Error.error(5567); 
    if (!(this.nodes[0]).dataType.isArrayType())
      throw Error.error(5563); 
    this.dataType = (this.nodes[0]).dataType.collectionBaseType();
    if ((this.nodes[1]).opType == 8)
      (this.nodes[1]).dataType = (Type)Type.SQL_INTEGER; 
  }
  
  public Object getValue(Session paramSession) {
    Object[] arrayOfObject = (Object[])this.nodes[0].getValue(paramSession);
    if (arrayOfObject == null)
      return null; 
    Number number = (Number)this.nodes[1].getValue(paramSession);
    if (number == null)
      return null; 
    if (number.intValue() < 1 || number.intValue() > arrayOfObject.length)
      throw Error.error(3490); 
    return arrayOfObject[number.intValue() - 1];
  }
  
  public Object[] getUpdatedArray(Session paramSession, Object[] paramArrayOfObject, Object paramObject, boolean paramBoolean) {
    if (paramArrayOfObject == null)
      throw Error.error(3413); 
    Number number = (Number)this.nodes[1].getValue(paramSession);
    if (number == null)
      throw Error.error(3490); 
    int i = number.intValue() - 1;
    if (i < 0)
      throw Error.error(3490); 
    if (i >= (this.nodes[0]).dataType.arrayLimitCardinality())
      throw Error.error(3490); 
    Object[] arrayOfObject = paramArrayOfObject;
    if (i >= paramArrayOfObject.length) {
      arrayOfObject = new Object[i + 1];
      System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 0, paramArrayOfObject.length);
    } else if (paramBoolean) {
      arrayOfObject = new Object[paramArrayOfObject.length];
      System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 0, paramArrayOfObject.length);
    } 
    arrayOfObject[i] = paramObject;
    return arrayOfObject;
  }
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer(64);
    String str = getContextSQL(this.nodes[0]);
    stringBuffer.append(str).append('[');
    stringBuffer.append(this.nodes[1].getSQL()).append(']');
    return stringBuffer.toString();
  }
  
  protected String describe(Session paramSession, int paramInt) {
    StringBuffer stringBuffer = new StringBuffer(64);
    stringBuffer.append('\n');
    for (byte b = 0; b < paramInt; b++)
      stringBuffer.append(' '); 
    stringBuffer.append("ARRAY ACCESS");
    if (getLeftNode() != null) {
      stringBuffer.append(" array=[");
      stringBuffer.append(this.nodes[0].describe(paramSession, paramInt + 1));
      stringBuffer.append(']');
    } 
    if (getRightNode() != null) {
      stringBuffer.append(" array_index=[");
      stringBuffer.append(this.nodes[1].describe(paramSession, paramInt + 1));
      stringBuffer.append(']');
    } 
    return stringBuffer.toString();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\ExpressionAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */