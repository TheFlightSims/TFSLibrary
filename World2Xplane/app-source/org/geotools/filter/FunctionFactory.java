package org.geotools.filter;

import java.util.List;
import org.opengis.feature.type.Name;
import org.opengis.filter.capability.FunctionName;
import org.opengis.filter.expression.Expression;
import org.opengis.filter.expression.Function;
import org.opengis.filter.expression.Literal;

public interface FunctionFactory {
  List<FunctionName> getFunctionNames();
  
  Function function(String paramString, List<Expression> paramList, Literal paramLiteral);
  
  Function function(Name paramName, List<Expression> paramList, Literal paramLiteral);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FunctionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */