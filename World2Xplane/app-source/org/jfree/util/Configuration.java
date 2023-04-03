package org.jfree.util;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Iterator;

public interface Configuration extends Serializable {
  String getConfigProperty(String paramString);
  
  String getConfigProperty(String paramString1, String paramString2);
  
  Iterator findPropertyKeys(String paramString);
  
  Enumeration getConfigProperties();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\Configuration.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */