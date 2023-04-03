package org.geotools.filter;

public interface FilterVisitor {
  void visit(Filter paramFilter);
  
  void visit(BetweenFilter paramBetweenFilter);
  
  void visit(CompareFilter paramCompareFilter);
  
  void visit(GeometryFilter paramGeometryFilter);
  
  void visit(LikeFilter paramLikeFilter);
  
  void visit(LogicFilter paramLogicFilter);
  
  void visit(NullFilter paramNullFilter);
  
  void visit(FidFilter paramFidFilter);
  
  void visit(AttributeExpression paramAttributeExpression);
  
  void visit(Expression paramExpression);
  
  void visit(LiteralExpression paramLiteralExpression);
  
  void visit(MathExpression paramMathExpression);
  
  void visit(FunctionExpression paramFunctionExpression);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */