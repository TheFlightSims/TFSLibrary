package org.geotools.filter;

import java.util.List;
import org.geotools.factory.Factory;
import org.opengis.filter.expression.Expression;
import org.opengis.filter.expression.Function;

public interface FunctionExpression extends Expression, Factory, Function {
  int getArgCount();
  
  short getType();
  
  Expression[] getArgs();
  
  String getName();
  
  void setArgs(Expression[] paramArrayOfExpression);
  
  void setParameters(List<Expression> paramList);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FunctionExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */