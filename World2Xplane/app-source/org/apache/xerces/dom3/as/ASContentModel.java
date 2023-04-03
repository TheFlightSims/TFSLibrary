package org.apache.xerces.dom3.as;

public interface ASContentModel extends ASObject {
  public static final int AS_UNBOUNDED = 2147483647;
  
  public static final short AS_SEQUENCE = 0;
  
  public static final short AS_CHOICE = 1;
  
  public static final short AS_ALL = 2;
  
  public static final short AS_NONE = 3;
  
  short getListOperator();
  
  void setListOperator(short paramShort);
  
  int getMinOccurs();
  
  void setMinOccurs(int paramInt);
  
  int getMaxOccurs();
  
  void setMaxOccurs(int paramInt);
  
  ASObjectList getSubModels();
  
  void setSubModels(ASObjectList paramASObjectList);
  
  void removesubModel(ASObject paramASObject);
  
  void insertsubModel(ASObject paramASObject) throws DOMASException;
  
  int appendsubModel(ASObject paramASObject) throws DOMASException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom3\as\ASContentModel.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */