package org.apache.xerces.dom3.as;

public interface ASObject {
  public static final short AS_ELEMENT_DECLARATION = 1;
  
  public static final short AS_ATTRIBUTE_DECLARATION = 2;
  
  public static final short AS_NOTATION_DECLARATION = 3;
  
  public static final short AS_ENTITY_DECLARATION = 4;
  
  public static final short AS_CONTENTMODEL = 5;
  
  public static final short AS_MODEL = 6;
  
  short getAsNodeType();
  
  ASModel getOwnerASModel();
  
  void setOwnerASModel(ASModel paramASModel);
  
  String getNodeName();
  
  void setNodeName(String paramString);
  
  String getPrefix();
  
  void setPrefix(String paramString);
  
  String getLocalName();
  
  void setLocalName(String paramString);
  
  String getNamespaceURI();
  
  void setNamespaceURI(String paramString);
  
  ASObject cloneASObject(boolean paramBoolean);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom3\as\ASObject.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */