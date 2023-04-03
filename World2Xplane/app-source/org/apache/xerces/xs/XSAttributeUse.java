package org.apache.xerces.xs;

public interface XSAttributeUse extends XSObject {
  boolean getRequired();
  
  XSAttributeDeclaration getAttrDeclaration();
  
  short getConstraintType();
  
  String getConstraintValue();
  
  Object getActualVC() throws XSException;
  
  short getActualVCType() throws XSException;
  
  ShortList getItemValueTypes() throws XSException;
  
  XSObjectList getAnnotations();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xs\XSAttributeUse.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */