package org.apache.xerces.xni.grammars;

import org.apache.xerces.xni.XMLResourceIdentifier;

public interface XMLGrammarDescription extends XMLResourceIdentifier {
  public static final String XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
  
  public static final String XML_DTD = "http://www.w3.org/TR/REC-xml";
  
  String getGrammarType();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xni\grammars\XMLGrammarDescription.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */