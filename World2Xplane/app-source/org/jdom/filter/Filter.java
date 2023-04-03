package org.jdom.filter;

import java.io.Serializable;

public interface Filter extends Serializable {
  boolean matches(Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\filter\Filter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */