package org.opengis.filter.capability;

import java.util.List;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.feature.type.Name;
import org.opengis.parameter.Parameter;

public interface FunctionName extends Operator {
  Name getFunctionName();
  
  @UML(identifier = "argumentCount", specification = Specification.UNSPECIFIED)
  int getArgumentCount();
  
  List<String> getArgumentNames();
  
  List<Parameter<?>> getArguments();
  
  Parameter<?> getReturn();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\capability\FunctionName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */