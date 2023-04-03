package org.apache.xerces.xs;

public interface XSAttributeGroupDefinition extends XSObject {
  XSObjectList getAttributeUses();
  
  XSWildcard getAttributeWildcard();
  
  XSAnnotation getAnnotation();
  
  XSObjectList getAnnotations();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xs\XSAttributeGroupDefinition.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */