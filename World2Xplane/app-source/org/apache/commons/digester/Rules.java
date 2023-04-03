package org.apache.commons.digester;

import java.util.List;

public interface Rules {
  Digester getDigester();
  
  void setDigester(Digester paramDigester);
  
  String getNamespaceURI();
  
  void setNamespaceURI(String paramString);
  
  void add(String paramString, Rule paramRule);
  
  void clear();
  
  List match(String paramString);
  
  List match(String paramString1, String paramString2);
  
  List rules();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\Rules.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */