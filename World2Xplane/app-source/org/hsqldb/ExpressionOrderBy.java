package org.hsqldb;

import org.hsqldb.error.Error;

public class ExpressionOrderBy extends Expression {
  private boolean isDescending;
  
  private boolean isNullsLast;
  
  ExpressionOrderBy(Expression paramExpression) {
    super(94);
    this.nodes[0] = paramExpression;
    this.collation = paramExpression.collation;
    paramExpression.collation = null;
  }
  
  void setDescending() {
    this.isDescending = true;
  }
  
  boolean isDescending() {
    return this.isDescending;
  }
  
  void setNullsLast(boolean paramBoolean) {
    this.isNullsLast = paramBoolean;
  }
  
  boolean isNullsLast() {
    return this.isNullsLast;
  }
  
  public Object getValue(Session paramSession) {
    return this.nodes[0].getValue(paramSession);
  }
  
  public void resolveTypes(Session paramSession, Expression paramExpression) {
    this.nodes[0].resolveTypes(paramSession, paramExpression);
    if (this.nodes[0].isUnresolvedParam())
      throw Error.error(5567); 
    this.dataType = (this.nodes[0]).dataType;
    if (this.collation != null && !this.dataType.isCharacterType())
      throw Error.error(4650, (this.collation.getName()).statementName); 
  }
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("ORDER").append(' ').append("BY").append(' ');
    if ((this.nodes[0]).alias != null) {
      stringBuffer.append((this.nodes[0]).alias.name);
    } else {
      stringBuffer.append(this.nodes[0].getSQL());
    } 
    if (this.collation != null)
      stringBuffer.append(' ').append(this.collation.getName().getSchemaQualifiedStatementName()); 
    if (this.isDescending)
      stringBuffer.append(' ').append("DESC"); 
    return stringBuffer.toString();
  }
  
  protected String describe(Session paramSession, int paramInt) {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(getLeftNode().describe(paramSession, paramInt));
    if (this.isDescending) {
      for (byte b = 0; b < paramInt; b++)
        stringBuffer.append(' '); 
      stringBuffer.append("DESC").append('\n');
    } 
    return stringBuffer.toString();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\ExpressionOrderBy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */