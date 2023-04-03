package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.jdbc.JDBCConnection;
import org.hsqldb.lib.ArrayListIdentity;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.lib.Set;
import org.hsqldb.map.ValuePool;
import org.hsqldb.result.Result;
import org.hsqldb.types.Type;

public class FunctionSQLInvoked extends Expression {
  RoutineSchema routineSchema;
  
  Routine routine;
  
  Expression condition = Expression.EXPR_TRUE;
  
  FunctionSQLInvoked(RoutineSchema paramRoutineSchema) {
    super(paramRoutineSchema.isAggregate() ? 98 : 27);
    this.routineSchema = paramRoutineSchema;
  }
  
  public void setArguments(Expression[] paramArrayOfExpression) {
    this.nodes = paramArrayOfExpression;
  }
  
  public HsqlList resolveColumnReferences(Session paramSession, RangeGroup paramRangeGroup, int paramInt, RangeGroup[] paramArrayOfRangeGroup, HsqlList paramHsqlList, boolean paramBoolean) {
    ArrayListIdentity arrayListIdentity;
    HsqlList hsqlList = this.condition.resolveColumnReferences(paramSession, paramRangeGroup, paramInt, paramArrayOfRangeGroup, null, false);
    if (hsqlList != null)
      ExpressionColumn.checkColumnsResolved(hsqlList); 
    if (isSelfAggregate()) {
      if (paramHsqlList == null)
        arrayListIdentity = new ArrayListIdentity(); 
      arrayListIdentity.add(this);
      return (HsqlList)arrayListIdentity;
    } 
    return super.resolveColumnReferences(paramSession, paramRangeGroup, paramInt, paramArrayOfRangeGroup, (HsqlList)arrayListIdentity, paramBoolean);
  }
  
  public void resolveTypes(Session paramSession, Expression paramExpression) {
    Type[] arrayOfType = new Type[this.nodes.length];
    byte b;
    for (b = 0; b < this.nodes.length; b++) {
      Expression expression = this.nodes[b];
      expression.resolveTypes(paramSession, this);
      arrayOfType[b] = expression.dataType;
    } 
    this.routine = this.routineSchema.getSpecificRoutine(arrayOfType);
    for (b = 0; b < this.nodes.length; b++) {
      if ((this.nodes[b]).dataType == null)
        (this.nodes[b]).dataType = this.routine.getParameterTypes()[b]; 
    } 
    this.dataType = this.routine.getReturnType();
    this.condition.resolveTypes(paramSession, null);
  }
  
  private Object getValueInternal(Session paramSession, Object[] paramArrayOfObject) {
    boolean bool1 = false;
    int i = this.routine.getVariableCount();
    byte b1 = this.routine.javaMethodWithConnection ? 1 : 0;
    Object[] arrayOfObject = ValuePool.emptyObjectArray;
    boolean bool2 = true;
    if (b1 + this.nodes.length > 0) {
      if (this.opType == 98) {
        arrayOfObject = new Object[this.routine.getParameterCount()];
        for (byte b = 0; b < paramArrayOfObject.length; b++)
          arrayOfObject[b + 1] = paramArrayOfObject[b]; 
      } else {
        arrayOfObject = new Object[this.nodes.length + b1];
      } 
      if (!this.routine.isPSM()) {
        JDBCConnection jDBCConnection = paramSession.getInternalConnection();
        if (b1 > 0)
          arrayOfObject[0] = jDBCConnection; 
      } 
    } 
    Type[] arrayOfType = this.routine.getParameterTypes();
    for (byte b2 = 0; b2 < this.nodes.length; b2++) {
      Expression expression = this.nodes[b2];
      Object object = expression.getValue(paramSession, arrayOfType[b2]);
      if (object == null) {
        if (this.routine.isNullInputOutput())
          return null; 
        if (!this.routine.getParameter(b2).isNullable())
          return Result.newErrorResult(Error.error(4811)); 
      } 
      if (this.routine.isPSM()) {
        arrayOfObject[b2] = object;
      } else {
        arrayOfObject[b2 + b1] = expression.dataType.convertSQLToJava(paramSession, object);
      } 
    } 
    Result result = this.routine.invoke(paramSession, arrayOfObject, paramArrayOfObject, bool2);
    paramSession.releaseInternalConnection();
    if (result.isError())
      throw result.getException(); 
    return bool1 ? result.valueData : result;
  }
  
  public Object getValue(Session paramSession) {
    if (this.opType == 5)
      return paramSession.sessionContext.rangeIterators[this.rangePosition].getCurrent(this.columnIndex); 
    Object object = getValueInternal(paramSession, (Object[])null);
    if (object instanceof Result) {
      Result result = (Result)object;
      if (result.isError())
        throw result.getException(); 
      if (result.isSimpleValue()) {
        object = result.getValueObject();
      } else if (result.isData()) {
        object = result;
      } else {
        throw Error.error(4605, (this.routine.getName()).name);
      } 
    } 
    return object;
  }
  
  public Result getResult(Session paramSession) {
    Object object = getValueInternal(paramSession, (Object[])null);
    return (object instanceof Result) ? (Result)object : Result.newPSMResult(object);
  }
  
  void collectObjectNames(Set paramSet) {
    paramSet.add(this.routine.getSpecificName());
  }
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(this.routineSchema.getName().getSchemaQualifiedStatementName());
    stringBuffer.append('(');
    int i = this.nodes.length;
    if (this.opType == 98)
      i = 1; 
    for (byte b = 0; b < i; b++) {
      if (b != 0)
        stringBuffer.append(','); 
      stringBuffer.append(this.nodes[b].getSQL());
    } 
    stringBuffer.append(')');
    return stringBuffer.toString();
  }
  
  public String describe(Session paramSession, int paramInt) {
    return super.describe(paramSession, paramInt);
  }
  
  boolean isSelfAggregate() {
    return this.routineSchema.isAggregate();
  }
  
  public boolean isDeterministic() {
    return this.routine.isDeterministic();
  }
  
  public boolean equals(Expression paramExpression) {
    if (!(paramExpression instanceof FunctionSQLInvoked))
      return false; 
    FunctionSQLInvoked functionSQLInvoked = (FunctionSQLInvoked)paramExpression;
    return (this.opType == paramExpression.opType && this.routineSchema == functionSQLInvoked.routineSchema && this.routine == functionSQLInvoked.routine && this.condition.equals(functionSQLInvoked.condition)) ? super.equals(paramExpression) : false;
  }
  
  public Object updateAggregatingValue(Session paramSession, Object paramObject) {
    if (!this.condition.testCondition(paramSession))
      return paramObject; 
    Object[] arrayOfObject = (Object[])paramObject;
    if (arrayOfObject == null)
      arrayOfObject = new Object[3]; 
    arrayOfObject[0] = Boolean.FALSE;
    getValueInternal(paramSession, arrayOfObject);
    return arrayOfObject;
  }
  
  public Object getAggregatedValue(Session paramSession, Object paramObject) {
    Object[] arrayOfObject = (Object[])paramObject;
    if (arrayOfObject == null)
      arrayOfObject = new Object[3]; 
    arrayOfObject[0] = Boolean.TRUE;
    Result result = (Result)getValueInternal(paramSession, arrayOfObject);
    if (result.isError())
      throw result.getException(); 
    return result.getValueObject();
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
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\FunctionSQLInvoked.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */