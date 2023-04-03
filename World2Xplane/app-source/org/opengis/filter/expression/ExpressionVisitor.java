package org.opengis.filter.expression;

public interface ExpressionVisitor {
  Object visit(NilExpression paramNilExpression, Object paramObject);
  
  Object visit(Add paramAdd, Object paramObject);
  
  Object visit(Divide paramDivide, Object paramObject);
  
  Object visit(Function paramFunction, Object paramObject);
  
  Object visit(Literal paramLiteral, Object paramObject);
  
  Object visit(Multiply paramMultiply, Object paramObject);
  
  Object visit(PropertyName paramPropertyName, Object paramObject);
  
  Object visit(Subtract paramSubtract, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\expression\ExpressionVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */