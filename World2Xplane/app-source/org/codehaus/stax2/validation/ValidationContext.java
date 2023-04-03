package org.codehaus.stax2.validation;

import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;

public interface ValidationContext {
  String getXmlVersion();
  
  QName getCurrentElementName();
  
  String getNamespaceURI(String paramString);
  
  int getAttributeCount();
  
  String getAttributeLocalName(int paramInt);
  
  String getAttributeNamespace(int paramInt);
  
  String getAttributePrefix(int paramInt);
  
  String getAttributeValue(int paramInt);
  
  String getAttributeValue(String paramString1, String paramString2);
  
  String getAttributeType(int paramInt);
  
  int findAttributeIndex(String paramString1, String paramString2);
  
  boolean isNotationDeclared(String paramString);
  
  boolean isUnparsedEntityDeclared(String paramString);
  
  String getBaseUri();
  
  Location getValidationLocation();
  
  void reportProblem(XMLValidationProblem paramXMLValidationProblem) throws XMLStreamException;
  
  int addDefaultAttribute(String paramString1, String paramString2, String paramString3, String paramString4);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\validation\ValidationContext.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */