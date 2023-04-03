package org.apache.xerces.dom3.as;

public interface ASEntityDeclaration extends ASObject {
  public static final short INTERNAL_ENTITY = 1;
  
  public static final short EXTERNAL_ENTITY = 2;
  
  short getEntityType();
  
  void setEntityType(short paramShort);
  
  String getEntityValue();
  
  void setEntityValue(String paramString);
  
  String getSystemId();
  
  void setSystemId(String paramString);
  
  String getPublicId();
  
  void setPublicId(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom3\as\ASEntityDeclaration.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */