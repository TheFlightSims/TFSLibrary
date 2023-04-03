package org.apache.commons.math3.ode;

import java.util.Collection;

public interface Parameterizable {
  Collection<String> getParametersNames();
  
  boolean isSupported(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\Parameterizable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */