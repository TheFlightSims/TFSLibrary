package org.apache.commons.digester;

import org.xml.sax.Attributes;

public interface ObjectCreationFactory {
  Object createObject(Attributes paramAttributes) throws Exception;
  
  Digester getDigester();
  
  void setDigester(Digester paramDigester);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\ObjectCreationFactory.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */