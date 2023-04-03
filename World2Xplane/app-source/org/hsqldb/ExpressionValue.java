package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.types.Type;

public class ExpressionValue extends Expression {
  ExpressionValue(Object paramObject, Type paramType) {
    super(1);
    this.dataType = paramType;
    this.valueData = paramObject;
  }
  
  public byte getNullability() {
    return (this.valueData == null) ? 1 : 0;
  }
  
  public String getSQL() {
    switch (this.opType) {
      case 1:
        return (this.valueData == null) ? "NULL" : this.dataType.convertToSQLString(this.valueData);
    } 
    throw Error.runtimeError(201, "ExpressionValue");
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
    } 
    throw Error.runtimeError(201, "ExpressionValue");
  }
  
  Object getValue(Session paramSession, Type paramType) {
    return (this.dataType == paramType || this.valueData == null) ? this.valueData : paramType.convertToType(paramSession, this.valueData, this.dataType);
  }
  
  public Object getValue(Session paramSession) {
    return this.valueData;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\ExpressionValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */