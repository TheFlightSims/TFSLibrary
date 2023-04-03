package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayListIdentity;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.map.ValuePool;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.RowType;
import org.hsqldb.types.Type;

public class ExpressionAggregate extends Expression {
  boolean isDistinctAggregate;
  
  ArrayType arrayType;
  
  ExpressionAggregate(int paramInt, boolean paramBoolean, Expression paramExpression) {
    super(paramInt);
    this.isDistinctAggregate = paramBoolean;
    this.nodes[0] = paramExpression;
    this.nodes[1] = Expression.EXPR_TRUE;
  }
  
  boolean isSelfAggregate() {
    return true;
  }
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer(64);
    String str = getContextSQL((this.nodes.length > 0) ? this.nodes[0] : null);
    switch (this.opType) {
      case 71:
        stringBuffer.append(' ').append("COUNT").append('(');
        return stringBuffer.toString();
      case 72:
        stringBuffer.append(' ').append("SUM").append('(');
        stringBuffer.append(str).append(')');
        return stringBuffer.toString();
      case 73:
        stringBuffer.append(' ').append("MIN").append('(');
        stringBuffer.append(str).append(')');
        return stringBuffer.toString();
      case 74:
        stringBuffer.append(' ').append("MAX").append('(');
        stringBuffer.append(str).append(')');
        return stringBuffer.toString();
      case 75:
        stringBuffer.append(' ').append("AVG").append('(');
        stringBuffer.append(str).append(')');
        return stringBuffer.toString();
      case 76:
        stringBuffer.append(' ').append("EVERY").append('(');
        stringBuffer.append(str).append(')');
        return stringBuffer.toString();
      case 77:
        stringBuffer.append(' ').append("SOME").append('(');
        stringBuffer.append(str).append(')');
        return stringBuffer.toString();
      case 78:
        stringBuffer.append(' ').append("STDDEV_POP").append('(');
        stringBuffer.append(str).append(')');
        return stringBuffer.toString();
      case 79:
        stringBuffer.append(' ').append("STDDEV_SAMP").append('(');
        stringBuffer.append(str).append(')');
        return stringBuffer.toString();
      case 80:
        stringBuffer.append(' ').append("VAR_POP").append('(');
        stringBuffer.append(str).append(')');
        return stringBuffer.toString();
      case 81:
        stringBuffer.append(' ').append("VAR_SAMP").append('(');
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
      case 71:
        stringBuffer.append("COUNT").append(' ');
        break;
      case 72:
        stringBuffer.append("SUM").append(' ');
        break;
      case 73:
        stringBuffer.append("MIN").append(' ');
        break;
      case 74:
        stringBuffer.append("MAX").append(' ');
        break;
      case 75:
        stringBuffer.append("AVG").append(' ');
        break;
      case 76:
        stringBuffer.append("EVERY").append(' ');
        break;
      case 77:
        stringBuffer.append("SOME").append(' ');
        break;
      case 78:
        stringBuffer.append("STDDEV_POP").append(' ');
        break;
      case 79:
        stringBuffer.append("STDDEV_SAMP").append(' ');
        break;
      case 80:
        stringBuffer.append("VAR_POP").append(' ');
        break;
      case 81:
        stringBuffer.append("VAR_SAMP").append(' ');
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
    HsqlList hsqlList = this.nodes[1].resolveColumnReferences(paramSession, paramRangeGroup, paramInt, paramArrayOfRangeGroup, null, false);
    if (hsqlList != null)
      ExpressionColumn.checkColumnsResolved(hsqlList); 
    if (paramHsqlList == null)
      arrayListIdentity = new ArrayListIdentity(); 
    arrayListIdentity.add(this);
    return (HsqlList)arrayListIdentity;
  }
  
  public void resolveTypes(Session paramSession, Expression paramExpression) {
    for (byte b = 0; b < this.nodes.length; b++) {
      if (this.nodes[b] != null)
        this.nodes[b].resolveTypes(paramSession, this); 
    } 
    if (this.nodes[0].getDegree() > 1)
      (this.nodes[0]).dataType = (Type)new RowType((this.nodes[0]).nodeDataTypes); 
    if (this.nodes[0].isUnresolvedParam())
      throw Error.error(5567); 
    if (this.isDistinctAggregate) {
      if ((this.nodes[0]).dataType.isLobType())
        throw Error.error(5534); 
      if ((this.nodes[0]).dataType.isCharacterType())
        this.arrayType = new ArrayType((this.nodes[0]).dataType, 2147483647); 
    } 
    this.dataType = SetFunction.getType(paramSession, this.opType, (this.nodes[0]).dataType);
    this.nodes[1].resolveTypes(paramSession, null);
  }
  
  public boolean equals(Expression paramExpression) {
    if (!(paramExpression instanceof ExpressionAggregate))
      return false; 
    ExpressionAggregate expressionAggregate = (ExpressionAggregate)paramExpression;
    return (this.isDistinctAggregate == expressionAggregate.isDistinctAggregate) ? super.equals(paramExpression) : false;
  }
  
  public Object updateAggregatingValue(Session paramSession, Object paramObject) {
    if (!this.nodes[1].testCondition(paramSession))
      return paramObject; 
    if (paramObject == null)
      paramObject = new SetFunction(paramSession, this.opType, (this.nodes[0]).dataType, this.dataType, this.isDistinctAggregate, this.arrayType); 
    Object object = ((this.nodes[0]).opType == 11) ? ValuePool.INTEGER_1 : this.nodes[0].getValue(paramSession);
    ((SetFunction)paramObject).add(paramSession, object);
    return paramObject;
  }
  
  public Object getAggregatedValue(Session paramSession, Object paramObject) {
    return (paramObject == null) ? ((this.opType == 71) ? Long.valueOf(0L) : null) : ((SetFunction)paramObject).getValue(paramSession);
  }
  
  public Expression getCondition() {
    return this.nodes[1];
  }
  
  public boolean hasCondition() {
    return !this.nodes[1].isTrue();
  }
  
  public void setCondition(Expression paramExpression) {
    this.nodes[1] = paramExpression;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\ExpressionAggregate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */