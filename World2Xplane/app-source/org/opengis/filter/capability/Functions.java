package org.opengis.filter.capability;

import java.util.Collection;

public interface Functions {
  Collection<FunctionName> getFunctionNames();
  
  FunctionName getFunctionName(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\capability\Functions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */